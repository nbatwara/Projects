import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleComponents {
	public static void main(String[] args) {
	
	// This is a HEAVYWEIGHT CONTAINER!
	// TODO: Change the title!
	JFrame frame = new JFrame("My Frame Title");
	
	// TODO: Play with the WIDTH and HEIGHT to see how it changes the
	// shape of the frame.
    frame.setSize(400,400);
    
    
    JButton b1 = new JButton("Press this");
    JLabel l1 = new JLabel("Enter your name");
    JTextField t1 = new JTextField(10);

    JPanel p = new JPanel();
    p.add(b1);
    p.add(l1);
    p.add(t1);
    frame.setContentPane(p);
    //frame.pack();

    
    
    
    
    
    // TODO: Comment this out and try! 
    // You will find that the window vanishes but the program does not exit!
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // TODO: Uncomment this out and try! 
    // You will find that the window gets "packed" and vanishes (because it has nothing in it).
    // Only the controls are visible and you can exit by clicking on the x.
    //frame.pack();
    
    frame.setVisible(true);
    
}


}
