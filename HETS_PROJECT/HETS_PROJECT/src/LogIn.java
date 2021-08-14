import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn {

	private JFrame frmLogIn;
	private JTextField txtId;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frmLogIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */	private void initialize() {
		frmLogIn = new JFrame();
		frmLogIn.setTitle("Login");
		frmLogIn.setBounds(100, 100, 450, 300);
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogIn.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("log in");
		/*button login onclick*/
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				if(txtId.getText().equals("rash")&&txtPassword.getText().equals("123")) {
				Home home=new Home();
				Home.main(null); 
            	frmLogIn.dispose();		
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The ID or password is incorrect");
				}
			}
		}
		);
		btnLogin.setBounds(213, 190, 68, 23);
		frmLogIn.getContentPane().add(btnLogin);
		
		txtId = new JTextField();
		txtId.setBounds(172, 90, 155, 20);
		frmLogIn.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblId = new JLabel("Tester ID:");
		lblId.setBounds(76, 93, 74, 14);
		frmLogIn.getContentPane().add(lblId);
		
		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogIn.setBounds(172, 30, 68, 28);
		frmLogIn.getContentPane().add(lblLogIn);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(76, 134, 74, 14);
		frmLogIn.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(172, 131, 155, 20);
		frmLogIn.getContentPane().add(txtPassword);
	}
}
