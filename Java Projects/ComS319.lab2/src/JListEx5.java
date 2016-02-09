import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * Changeable data in list!
 * Here we use the DefaultListModel which provides a way to add and delete items.
 * Note that we have no control over how DefaultListModel stores the data.
 * 
 * We also listen for changes to the data model! <we are not talking about listening for 
 * events on the view (as in previous examples)>.
 * @author smitra
 * 
 *
 */
public class JListEx5 {

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
		
		DefaultListModel <String> listModel;
		
		//Create and populate the list model. NOTE, we have no control over how our Data is stored.
		listModel = new DefaultListModel<String>();
		myList.setModel(listModel);
		
		// LISTEN FOR CHANGES TO THE DATA (i.e. the MODEL)
		listModel.addListDataListener(new MyListDataListener());
		
		// ADD and REMOVED elements. This will cause events to be fired and then handled.
		listModel.addElement("abc");
		listModel.addElement("def");
		listModel.addElement("ghi");
		listModel.remove(0);
		
		return panel;
	}

}

class MyListDataListener implements ListDataListener {
    // TODO: on changes make it do more interesting things?
	// for example: pop up a confirmation box
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
