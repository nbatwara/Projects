import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Here we add handlers to the components in BasicGUI03.
 * @author smitra
 *
 */
public class BasicGUI04 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("My Frame Title");
	    frame.setSize(new Dimension(400, 400));
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    frame.setContentPane(createContainers());
	    
	    //frame.pack();
	    frame.setVisible(true);
	}
	
	static JPanel createContainers() {
		
		JPanel mainPanel = new JPanel();		
		mainPanel.setBackground(Color.YELLOW);
	  mainPanel.setLayout(new BoxLayout (mainPanel, BoxLayout.Y_AXIS));

		
		JPanel statusPanel = new JPanel();
		JLabel statusMessage = new JLabel("Nothing to report!");
		statusMessage.setForeground(Color.BLUE);
		statusPanel.add(statusMessage);
		mainPanel.add(statusPanel);
		

	  JButton button1 = new JButton("OK Button");
	    
	    // add handler for button!
	    // all this one will do is to change the status message
	    // TODO: Make the status Message background RED
	    button1.addActionListener(
	    		// This is an ANONYMOUS class
	    		new ActionListener() {
	    				@Override
					public void actionPerformed(ActionEvent e) {
						statusMessage.setText("Button Pressed!");
					}
	    		}
	    	);
	    mainPanel.add(button1);
		
	    JTextField text1 = new JTextField("Type your name here");
	    
	    // add handler for text field
	    // TODO: make the status message forebackground GREEN
	    text1.addActionListener(
	    		// This is an ANONYMOUS class
	    		new ActionListener() {
	    				@Override
					public void actionPerformed(ActionEvent e) {
						statusMessage.setText(e.getActionCommand());
					}
	    		}
	    		
	    	);
	    
	    mainPanel.add(text1);
	    
		// Create a Checkbox and add it to the panel
	    JCheckBox chk1 = new JCheckBox("Like Soccer?");
      
      // Note that the listener is different for check boxes!
	    chk1.addChangeListener(
	    		// This is an ANONYMOUS class
	    		new ChangeListener() {
	    				@Override
					public void stateChanged(ChangeEvent e) {
					  if(chk1.isSelected()) {
							statusMessage.setText("Like soccer");
					  } else {
						  statusMessage.setText("Do not like soccer!");
					  }
					}
	    		}
	    		
	    	);
	    mainPanel.add(chk1);
	    
		
		return mainPanel;
		
	}
	
}


