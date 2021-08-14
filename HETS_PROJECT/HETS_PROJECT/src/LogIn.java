import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.text.Document;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.Arrays;
import javax.swing.JOptionPane;
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
		JButton btnAddusr = new JButton("New User");

		JButton btnLogin = new JButton("log in");
		/*button login onclick*/
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				try {
				
					MongoClient mng = new MongoClient("localhost",27017);
					DB db = mng.getDB("test");
				
					System.out.println("connected!!!");
					DBCollection usercoll = db.getCollection("users");
					BasicDBObject d1 = new BasicDBObject("name",txtId.getText()).append("password", txtPassword.getText());
				
					if((usercoll.find(d1).next()!=null)) {
						System.out.println((usercoll.find(d1).next()));
						Home home=new Home();
						Home.main(null); 
		            	frmLogIn.dispose();	
					}
					
					System.out.println("**********");
					System.out.println("-----");
			
					mng.close();
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "The ID or password is incorrect");
				}
				
				
			}
		}
		);
		btnAddusr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
					NewUser newusr=new NewUser();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				NewUser.main(null); 
            	frmLogIn.dispose();			
			}
		}
		);

		btnLogin.setBounds(213, 190, 68, 23);
		frmLogIn.getContentPane().add(btnLogin);
		
		btnAddusr.setBounds(300, 220, 100, 23);
		frmLogIn.getContentPane().add(btnAddusr);
		
		txtId = new JTextField();
		txtId.setBounds(172, 90, 155, 20);
		frmLogIn.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblId = new JLabel("Tester ID:");
		lblId.setBounds(76, 93, 74, 14);
		frmLogIn.getContentPane().add(lblId);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogIn.setBounds(172, 30, 68, 28);
		frmLogIn.getContentPane().add(lblLogIn);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(76, 134, 74, 14);
		frmLogIn.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(172, 131, 155, 20);
		frmLogIn.getContentPane().add(txtPassword);

	}
}
