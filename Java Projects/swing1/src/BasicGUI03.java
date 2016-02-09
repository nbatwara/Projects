import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Here we take a look at a few components JButton, JTextField, JCheckbox
 * We also use a layout manager to place the items in a container
 * Click on the Button and checkbox etc to see what happens! (Nothing)
 * @author smitra
 *
 */
public class BasicGUI03 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("My Frame Title");
	    frame.setSize(new Dimension(400, 400));
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Here we add a PANEL into the frame
	    frame.setContentPane(createContainers());
	    
	    // TODO: Uncomment and see what happens!
	    //frame.pack();
	    frame.setVisible(true);
	}
	
	static JPanel createContainers() {
		
		JPanel mainPanel = new JPanel();		
		mainPanel.setBackground(Color.YELLOW);
		
		// This sets layout to vertical placement of items in container
		// TODO: change layout to X_AXIS - i.e. horizontal placement!
		// Also - learn about other layout managers
	    mainPanel.setLayout(new BoxLayout (mainPanel, BoxLayout.Y_AXIS));

		// Create a JButton and add it to the panel.
	    JButton button1 = new JButton("OK Button");
	    mainPanel.add(button1);
		
	    // Create a TextField and add it to the panel.
	    JTextField text1 = new JTextField("Type your name here");
	    mainPanel.add(text1);
	    
		// Create a Checkbox and add it to the panel
	    JCheckBox chk1 = new JCheckBox("Like Soccer?");
	    mainPanel.add(chk1);
		
		return mainPanel;
		
	}
	
}


