package lab1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelloWorldSwing2 extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private int size = 8;
	
	private  String textField;
	
	private  int font;
			
	private  JTextField jt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelloWorldSwing2 frame = new HelloWorldSwing2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HelloWorldSwing2() {
		setTitle("HelloWorldSwing!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(21, 26, 46, 14);
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblText);
		
		jt = new JTextField();
		jt.setBounds(106, 25, 91, 20);
		jt.setText("Hello World!");
		contentPane.add(jt);
		jt.setColumns(10);
		
		JComboBox <String>comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox<String> jb = (JComboBox<String>)e.getSource();
				String selected = (String)jb.getSelectedItem();
				
				if(selected.equals("SMALL"))
				{
					size = 12;
				} else if (selected.equals("MEDIUM"))
				{
					size = 20;
				}
				else if (selected.equals("TINY"))
				{
					size = 8;
				}
				else 
				{
					size = 28;
				}
				
			}
		});
		comboBox.setBounds(248, 25, 86, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TINY", "SMALL", "MEDIUM", "LARGE"}));
		comboBox.setToolTipText("tiny\r\nsmall\r\nmedium\r\nlarge");
		comboBox.setMaximumRowCount(4);
		contentPane.add(comboBox);
		
		JRadioButton rbPlain = new JRadioButton("Plain");
		rbPlain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				font = Font.PLAIN;
				textField = jt.getText();
			}
		});
		buttonGroup.add(rbPlain);
		rbPlain.setBounds(18, 59, 70, 23);
		contentPane.add(rbPlain);
		
		JRadioButton rbBold = new JRadioButton("Bold");
		rbBold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				font = Font.BOLD;
				textField = jt.getText();
			}
		});
		buttonGroup.add(rbBold);
		rbBold.setBounds(18, 85, 57, 23);
		contentPane.add(rbBold);
		
		JRadioButton rbItalic = new JRadioButton("Italic");
		rbItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				font = Font.ITALIC;
				textField = jt.getText();
			}
		});
		buttonGroup.add(rbItalic);
		rbItalic.setBounds(18, 111, 70, 23);
		contentPane.add(rbItalic);
		
		JRadioButton rbBoldItalic = new JRadioButton("Bold Italic");
		rbBoldItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				font = Font.BOLD + Font.ITALIC;
				textField = jt.getText();
			}
		});
		buttonGroup.add(rbBoldItalic);
		rbBoldItalic.setBounds(18, 137, 96, 23);
		contentPane.add(rbBoldItalic);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(245, 202, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Hello World!");
		lblNewLabel.setBounds(190, 56, 234, 97);
		
		JButton btnNewButton = new JButton("Show!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField = jt.getText();
				lblNewLabel.setText(textField);
				lblNewLabel.setFont(new Font("Arial", font, size));
			}
		});
		
		btnNewButton.setBounds(68, 202, 89, 23);
		contentPane.add(lblNewLabel);
		contentPane.add(btnNewButton);
	}
}
