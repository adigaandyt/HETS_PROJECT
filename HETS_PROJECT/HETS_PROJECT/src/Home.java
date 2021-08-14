import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Home {

	private JFrame frmHome;
    public static String pathOfFile=""; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHome = new JFrame();
		frmHome.setTitle("Home");
		frmHome.setBounds(100, 100, 450, 300);
		frmHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHome.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Tester");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(141, 26, 148, 33);
		frmHome.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("choose your home exercises file:");
		lblNewLabel_1.setBounds(53, 90, 216, 14);
		frmHome.getContentPane().add(lblNewLabel_1);
		
		JLabel lblSelectTestingMethod = new JLabel("select testing method:");
		lblSelectTestingMethod.setBounds(53, 129, 216, 14);
		frmHome.getContentPane().add(lblSelectTestingMethod);
		
		JLabel pathLabel = new JLabel("");
		pathLabel.setBounds(63, 115, 285, 14);
		frmHome.getContentPane().add(pathLabel);
		
		
		JButton btnchoose = new JButton("Browse");
		btnchoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser FileChooser=new JFileChooser();
				FileChooser.setFileSelectionMode(1);
				int choosingOption = FileChooser.showOpenDialog(null);
				if(choosingOption==JFileChooser.APPROVE_OPTION)
				{
					File myFile=FileChooser.getSelectedFile();
					pathLabel.setText(myFile.getAbsolutePath());
					pathOfFile=pathLabel.getText();
					
				}
			
			}
		});
		btnchoose.setBounds(290, 86, 114, 23);
		frmHome.getContentPane().add(btnchoose);
		
		JRadioButton rdHETS1 = new JRadioButton("HETS_1");
		rdHETS1.setSelected(true);
		rdHETS1.setBounds(53, 148, 155, 23);
		frmHome.getContentPane().add(rdHETS1);
		
		JRadioButton rdHETS2 = new JRadioButton("HETS_2");
		rdHETS2.setBounds(53, 170, 155, 23);
		frmHome.getContentPane().add(rdHETS2);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(rdHETS1);
	    group.add(rdHETS2);
	    JButton btnnext = new JButton("Next");
		btnnext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pathLabel.getText().equals(""))
					JOptionPane.showMessageDialog(null, "You must choose the file!");

				if(rdHETS1.isSelected() && !(pathLabel.getText().equals("")))
				{
					HETS_1 hets1=new HETS_1();
					hets1.main(null);
	            	frmHome.dispose();	
				}
				if(rdHETS2.isSelected()&& !(pathLabel.getText().equals("")))
				{
					HETS_2 hets2=new HETS_2();
					hets2.main(null);
	            	frmHome.dispose();	
				}
			}
		});
		btnnext.setBounds(172, 227, 76, 23);
		frmHome.getContentPane().add(btnnext);
		
		
	   
	}
}
