import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class HETS_1 {

	private JFrame frmHets;
	public static String lblofinput;
	public static String lblofoutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HETS_1 window = new HETS_1();
					window.frmHets.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HETS_1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHets = new JFrame();
		frmHets.setTitle("HETS_1");
		frmHets.setBounds(100, 100, 450, 300);
		frmHets.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHets.getContentPane().setLayout(null);
		
		JLabel lblhest1 = new JLabel("HETS_1");
		lblhest1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblhest1.setBounds(187, 36, 61, 27);
		frmHets.getContentPane().add(lblhest1);
		
		JLabel lblChooseinputfile = new JLabel("choose input file:");
		lblChooseinputfile.setBounds(24, 91, 164, 14);
		frmHets.getContentPane().add(lblChooseinputfile);
		
		JLabel lblChooseOutputFile = new JLabel("choose output file:");
		lblChooseOutputFile.setBounds(24, 136, 164, 14);
	
		frmHets.getContentPane().add(lblChooseOutputFile);
		JLabel lblinput = new JLabel("");
		lblinput.setBounds(10, 111, 367, 14);
		frmHets.getContentPane().add(lblinput);
		
		JButton btninput = new JButton("Browse");
		btninput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser FileChooser=new JFileChooser();
				FileChooser.setFileSelectionMode(0);
				int choosingOption = FileChooser.showOpenDialog(null);
				if(choosingOption==JFileChooser.APPROVE_OPTION)
				{
					File myFile=FileChooser.getSelectedFile();
					lblinput.setText(myFile.getAbsolutePath());
					lblofinput=lblinput.getText();
					
				}
				
			}
		});
		btninput.setBounds(198, 87, 104, 23);
		frmHets.getContentPane().add(btninput);
	
		JLabel lbloutput = new JLabel("");
		lbloutput.setBounds(10, 161, 367, 14);
		frmHets.getContentPane().add(lbloutput);
		
		JButton btnoutput = new JButton("Browse");
		btnoutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser FileChooser=new JFileChooser();
				FileChooser.setFileSelectionMode(0);
				int choosingOption = FileChooser.showOpenDialog(null);
				if(choosingOption==JFileChooser.APPROVE_OPTION)
				{
					File myFile=FileChooser.getSelectedFile();
					lbloutput.setText(myFile.getAbsolutePath());
					lblofoutput=lbloutput.getText();
					
				}
				
			}
		});
		btnoutput.setBounds(198, 132, 104, 23);
		frmHets.getContentPane().add(btnoutput);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home=new Home();
				Home.main(null);
            	frmHets.dispose();	
			}
		});
		btnBack.setBounds(116, 207, 72, 23);
		frmHets.getContentPane().add(btnBack);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(lblinput.getText().equals(""))&& !(lbloutput.getText().equals(""))) {
					Testing testing=new Testing();
					testing.main(null);
	            	frmHets.dispose();	
	            	Testing.expectedoutput=lblofoutput;
	            	Testing.inputfile=lblofinput;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "at least one of the files is missing");
					}
					
			}
		});
		btnNext.setBounds(237, 207, 72, 23);
		frmHets.getContentPane().add(btnNext);
		
		
	}
}
