import javax.swing.JDialog;

public class TopLevelContainer_4 extends JDialog {
	public static void main(String[] args) {
	
	// This is a HEAVYWEIGHT CONTAINER!
	// TODO: Change the title!
	JDialog dialog = new JDialog();
	
	// TODO: Play with the WIDTH and HEIGHT to see how it changes the
	// shape of the dialog.
    dialog.setSize(400,400);
    
    // TODO: Comment this out and try! 
    // You will find that the window vanishes but the program does not exit!
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    
    // TODO: Uncomment this out and try! 
    // You will find that the window gets "packed" and vanishes (because it has nothing in it).
    // Only the controls are visible and you can exit by clicking on the x.
    //dialog.pack();
    
    dialog.setVisible(true);

}

}
