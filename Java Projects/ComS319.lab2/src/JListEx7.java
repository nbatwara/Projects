import java.util.ArrayList;
import java.util.Scanner;

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
 * Same as Example 6, but rewriting to show controller, view, and model relationships
 * @author smitra
 * 
 *
 */
public class JListEx7 {

	// This is the CONTROLLER CODE
	public static void main(String[] args) {
		// Creates the window
		JFrame frame = createFrame();
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
			
		//Create a JList (the View) (VIEW CODE IS IN JLIST)
		JList <String>myList = new JList<String>();
				
		//Create and populate the list model (MODEL CODE IS LISTMODEL)
		ArrayList<String> l = new ArrayList<String>();
		MyListModel3  listModel = new MyListModel3(l);
		myList.setModel(listModel);
		
		// Add a listener to the model
		listModel.addListDataListener(new MyListDataListener3());
					
		// Usually lists/tables/trees can be big (depends on data) and
		// it makes sense to put them in a scroll pane
		JScrollPane scrollPane = new JScrollPane(myList);
		panel.add(scrollPane);
		
		// make the window visible
		frame.setVisible(true);
		
		// Ask the user for input
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			System.out.println("Please enter some item!");
			String s = in.nextLine();
			listModel.addElement(s);
		}	
		
		System.out.println("Which item do you want to delete?");
		int idx = in.nextInt();
		listModel.removeElement(idx);

		// TODO: add more controller actions!
		
		// END OF CONTROLLER CODE
	}
	
	static JFrame createFrame() {
		JFrame frame = new JFrame("Simple List Example");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
	
}

class MyListModel3 extends AbstractListModel<String> {
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> aList;
	
	public MyListModel3(ArrayList <String> l) {
		aList = l;
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return aList.size();
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return aList.get(index);
	}
	
	public void addElement (String s) {
		aList.add(s);
		fireIntervalAdded(this,aList.size()-1, aList.size()-1);
	}
	
	public void removeElement (int index) {
		aList.remove(index);
		this.fireIntervalRemoved(this, aList.size()-1, aList.size()-1);
	}
	
	
	
}

class MyListDataListener3 implements ListDataListener {
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
