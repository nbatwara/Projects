import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Here we get started using containers and adding components to a container.
 * @author smitra
 *
 */
public class BasicGUI02 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("My Frame Title");
	    frame.setSize(new Dimension(400, 400));
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Here we add a PANEL into the frame
	    frame.setContentPane(createContainers());
	    
	    frame.pack();
	    frame.setVisible(true);
	}
	
	static JPanel createContainers() {
		
		// This is a LIGHTWEIGHT container which is initially empty
		JPanel mainPanel = new JPanel();		

		// JLabel is a COMPONENT. It is a simple text label.
		mainPanel.add(new JLabel("This is the main Panel"));
		mainPanel.setBackground(Color.BLUE);
		
		// This is ANOTHER container.
		JPanel innerPanel1 = new JPanel();
		innerPanel1.add(new JLabel("This is inner Panel1"));
		innerPanel1.setBackground(Color.GREEN);
		// We add the container to the main container.
		mainPanel.add(innerPanel1);
		
		// Here is yet another container.
		JPanel innerPanel2 = new JPanel();
		innerPanel2.add(new JLabel("This is inner Panel2"));
		innerPanel2.setBackground(Color.ORANGE);
		// We add this container to the main container too!
		mainPanel.add(innerPanel2);
		
		// TODO: You can play with adding more JLabels to each 
		// of the containers and seeing the effect.
		
		// Note that how the items are placed inside a container is determined
		// by a "LAYOUT MANAGER". The default one is a flow manager.
		return mainPanel;
		
	}
	
}


