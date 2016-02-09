package lab1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class HelloWorldSwing{

	private static String textField;
		
	private static int font;
	
	private static int size = 8;
		
	private static JTextField jt;
	
	public HelloWorldSwing(){

		
	}
	
	private static JFrame createAndSetupFrame() {

	JFrame f = new JFrame("HelloWorldSwing!");
    f.setSize(new Dimension(370, 220));

	return f;
}

	private static JPanel createSubPanel1() {

	JPanel sub1 = new JPanel();

	sub1.setLayout(new FlowLayout(FlowLayout.LEADING));

	JLabel jl = new JLabel("Text: ");
	jt = new JTextField("Hello World!");
	textField = jt.getText();
	jt.setPreferredSize(new Dimension(100,30));
	String[] list = {"tiny","small", "medium", "large"};
	JComboBox<String> jcb = new JComboBox<String>(list);
	jcb.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {

			JComboBox<String> jb = (JComboBox<String>)event.getSource();
			String selected = (String)jb.getSelectedItem();
			
			if(selected.equals("small"))
			{
				size = 12;
			} else if (selected.equals("medium"))
			{
				size = 20;
			}
			else if (selected.equals("tiny"))
			{
				size = 8;
			}
			else 
			{
				size = 28;
			}
			
			
		}});
	jcb.setMaximumSize(jcb.getPreferredSize());
	sub1.add(jl);
	sub1.add(Box.createRigidArea(new Dimension(25,0)));
	sub1.add(jt);	
	sub1.add(Box.createRigidArea(new Dimension(10,0)));
	sub1.add(jcb);
	return sub1;
}

	private static JPanel createSubPanel2() {
	JPanel container = new JPanel();
	container.setLayout(new FlowLayout(FlowLayout.LEADING));
	
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

	JRadioButton jrb1 = new JRadioButton("Plain", true);
	JRadioButton jrb2 = new JRadioButton("Bold", false);
	JRadioButton jrb3 = new JRadioButton("Italic", false);
	JRadioButton jrb4 = new JRadioButton("Bold Italic", false);
	
	ButtonGroup group = new ButtonGroup();
	group.add(jrb1);
	group.add(jrb2);
	group.add(jrb3);
	group.add(jrb4);
		
	JLabel label = new JLabel(textField);
	
	label.setFont(new Font("Arial", Font.PLAIN, size));

	
	jrb1.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			font = Font.PLAIN;
			textField = jt.getText();

		}
		
	});
	
	jrb2.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			font = Font.BOLD;
			textField = jt.getText();
			

		}
		
	});
	
	jrb3.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			font = Font.ITALIC;
			textField = jt.getText();
			
		}
		
	});
	
	jrb4.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			font = Font.BOLD + Font.ITALIC;
			textField = jt.getText();
		}
		
	});
	panel.add(jrb1);
	panel.add(jrb2);
	panel.add(jrb3);
	panel.add(jrb4);
	
	JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
	JButton jb1 = new JButton("Show!");
	JButton jb2 = new JButton("Exit");
	panel2.add(Box.createRigidArea(new Dimension(50,0)));
	panel2.add(jb1);
	panel2.add(Box.createRigidArea(new Dimension(90,0)));
	panel2.add(jb2);
	
	jb1.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			textField = jt.getText();
			label.setText(textField);
			label.setFont(new Font("Arial", font, size));
		}});
	
	jb2.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}});
	
	container.add(panel);
	container.add(Box.createRigidArea(new Dimension(50,0)));
	container.add(label);
	container.add(panel2);

	return container;
}

	public static void main(String[] args){
		
		JFrame frame = createAndSetupFrame();
		frame.setResizable(false);
		JPanel mainPanel = new JPanel();	
		mainPanel.setLayout(new BorderLayout());
		JPanel subPanel1 = createSubPanel1();

		JPanel subPanel2 = createSubPanel2();
		
		mainPanel.add(subPanel1, BorderLayout.PAGE_START);
		mainPanel.add(subPanel2, BorderLayout.CENTER);
		
	
		frame.setContentPane(mainPanel);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    frame.setVisible(true);
	}


}
