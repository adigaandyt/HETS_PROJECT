import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reporting {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reporting window = new Reporting();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Reporting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblResultsOfTesting = new JLabel("Results of Testing:");
		lblResultsOfTesting.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblResultsOfTesting.setBounds(37, 27, 175, 23);
		frame.getContentPane().add(lblResultsOfTesting);
		
		JLabel lblfilestested = new JLabel(Testing.counttested+".c files were tested");
		lblfilestested.setBounds(77, 87, 231, 14);
		frame.getContentPane().add(lblfilestested);
		
		JLabel lblcFilesSuccessfully = new JLabel(Testing.countcompiled+".c files successfully compiled");
		lblcFilesSuccessfully.setBounds(77, 135, 231, 14);
		frame.getContentPane().add(lblcFilesSuccessfully);
	}

}
