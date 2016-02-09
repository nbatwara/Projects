import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * A very simple JList + Change a Few properties + ListSelectionEvents handle.
 * @author smitra
 * 
 * 1. create a simple JList
 * 2. change a few properties of the JList
 * 3. add ListSelection listener (i.e. do something when item selected)
 *
 */
public class JListEx2 {

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
		
		// Set Properties of the JList
		// TODO: Find out what each of the below options do!
		
		//myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//myList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // this is default
		
		//myList.setVisibleRowCount(3);   // number of visible rows
		//myList.setFixedCellHeight(20);  // height of each cell
	    //myList.setFixedCellWidth(100);  // width of each cell
			
		// Usually lists/tables/trees can be big (depends on data) and
		// it makes sense to put them in a scroll pane
		JScrollPane scrollPane = new JScrollPane(myList);
		panel.add(scrollPane);
		
		// Add a listener to your JList 
		myList.addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						// when we click - we usually end up clicking a few times
						// Here we wait for things to stabilize - ignoring some of the events
						if (!e.getValueIsAdjusting()) {
							
							// this gives us the specific list that had the event
							JList l = (JList) e.getSource();
							
							// this gives us the specific item from the list that was selected
							System.out.println(l.getSelectedValue());
							
							// TODO: make your listener do more interesting things? 
							// for example: open a dialog with information about the specific item!
						}
					}	
				}				
		);
		
		return panel;
	}

}
