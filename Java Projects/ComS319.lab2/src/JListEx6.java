import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;


/**
 * In example 5, we used the DefaultListModel. We had no control over how our data was stored.
 * Here, we use our Own Data Model (mapped to any entity like ArrayList etc)
 * Now, we can add/remove from OUR data model (which stores an arraylist) and have it fire events
 * 
 * Note how events are being FIRED!
 * @author smitra
 * 
 *
 */
public class JListEx6 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Simple List Example");
		frame.setSize(400, 400);
		frame.getContentPane().add(createPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	static JPanel createPanel() {
		JPanel panel = new JPanel();
		
		//Create a JList 
		JList <String>myList = new JList<String>();
		
			
		// Usually lists/tables/trees can be big (depends on data) and
		// it makes sense to put them in a scroll pane
		JScrollPane scrollPane = new JScrollPane(myList);
		panel.add(scrollPane);
		
		// USE OUR OWN MODEL!
		MyListModel2  listModel;
		
		//Create and populate the list model
		ArrayList<String> l = new ArrayList<String>();
		
		listModel = new MyListModel2(l);
		myList.setModel(listModel);
		listModel.addListDataListener(new MyListDataListener2());
		
		// Play with adding and deleting elements
		listModel.addElement("abc");
		listModel.addElement("def");
		listModel.addElement("ghi");
		listModel.removeElement(0);
		
		// Check items in our arraylist
		for (String s: l) {
			System.out.println("In arraylist:" + s);
		}
		return panel;
	}

}

class MyListModel2 extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;
	
	// Here we use our OWN Model which is an ArrayList
	ArrayList<String> aList;
	
	public MyListModel2(ArrayList <String> l) {
		aList = l;
	}
	
	@Override
	public int getSize() {
		return aList.size();
	}

	@Override
	public String getElementAt(int index) {
		return aList.get(index);
	}
	
	public void addElement (String s) {
		aList.add(s);
		
		// NOTE HOW EVENT IS BEING FIRED!
		// TODO: Comment out the below line - what happens?
		fireIntervalAdded(this,aList.size()-1, aList.size()-1);
	}
	
	public void removeElement (int index) {
		aList.remove(index);
		
		// NOTE HOW EVENT IS BEING FIRED!
		// TODO: Comment out the below line - what happens?
		this.fireIntervalRemoved(this, aList.size()-1, aList.size()-1);
	}
	
	
	
}

class MyListDataListener2 implements ListDataListener {
    public void contentsChanged(ListDataEvent e) {
       System.out.println("contentsChanged: " + e.getIndex0() +
                   ", " + e.getIndex1());
    }
    public void intervalAdded(ListDataEvent e) {
    		ListModel l = (ListModel) e.getSource();
      	System.out.println("intervalAdded: " + e.getIndex0() +
                   ", " + e.getIndex1() + " " + l.getElementAt(e.getIndex0()));
    }
    public void intervalRemoved(ListDataEvent e) {
      	System.out.println("intervalRemoved: " + e.getIndex0() +
                   ", " + e.getIndex1());
    }
}
