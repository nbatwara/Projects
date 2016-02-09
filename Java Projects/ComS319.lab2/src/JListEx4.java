import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * Here we get the data from the model maintained by JList.
 * @author smitra
 * 
 *
 */
public class JListEx4 {

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
		String [] data = {"abc","def","ghi"};
		JList <String>myList = new JList<String>(data);
		
			
		// Usually lists/tables/trees can be big (depends on data) and
		// it makes sense to put them in a scroll pane
		JScrollPane scrollPane = new JScrollPane(myList);
		panel.add(scrollPane);
		
		// THIS is where we get the data model from the JList.
		// The List Model is an interface that provide getSize() and getElementAt() methods.
		ListModel <String> l = myList.getModel();
		for (int i = 0; i < l.getSize(); i++) {
			System.out.println(l.getElementAt(i));
		}
		// NOTE - Cannot add or remove data from the ListModel
		// ListModel is an interface 
		
		return panel;
	}

}
