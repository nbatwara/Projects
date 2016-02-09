import javax.swing.JFrame;

public class TopLevelContainer_1 {
	public static void main(String[] args) {
	
	// This is a HEAVYWEIGHT CONTAINER!
	// TODO: Change the title!
	JFrame frame = new JFrame("My Frame Title");
	
	// TODO: Play with the WIDTH and HEIGHT to see how it changes the
	// shape of the frame.
    frame.setSize(400,400);
    
    // TODO: Comment this out and try! 
    // You will find that the window vanishes but the program does not exit!
   // note that the main thread has an infinite loop in this program.
   // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // TODO: Uncomment this out and try! 
    // You will find that the window gets "packed" and vanishes (because it has nothing in it).
    // Only the controls are visible and you can exit by clicking on the x.
    //frame.pack();
    
    frame.setLocation(500,200);
    
    frame.setVisible(true);
    int i = 1;
    while (true) {
    	System.out.println(i=i+1);
    }
}


}
