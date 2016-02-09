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
 * Here we customize the way JList draws each item
 * @author smitra
 * 
 *
 */
public class JListEx3 {

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
		
		// HERE we add the cell renderer which draws each item in list
		myList.setCellRenderer(new SimpleRenderer());
		
		// Here is our listener
		myList.addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
							JList l = (JList) e.getSource();
							System.out.println(l.getSelectedValue());
						}
					}	
				}				
		);
		
		return panel;
	}

}

class SimpleRenderer implements ListCellRenderer <Object>
{
	@Override
	public Component getListCellRendererComponent(JList <?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
	
		// Note how EACH cell of the JList is really a JLabel with a value.
		// TODO: Instead of a JLabel, can we use a JButton with a handler?
		JLabel label = new JLabel((String)value);
		label.setOpaque(true);
		
		// TODO: change the way it is rendered! Switch them!
		if (isSelected)
		{
			label.setBackground(Color.red);
			label.setForeground(Color.white);
			
		}
		else {
			label.setFont(new Font("Helvetica", Font.ITALIC, 36));
		}
		return label;
	}
}

