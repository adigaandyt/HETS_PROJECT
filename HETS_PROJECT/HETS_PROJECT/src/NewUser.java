import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import javax.swing.JTextField;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;


public class NewUser {

	private JFrame frmUser;
	private JTextField txtId;
	private JPasswordField txtPassword;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser window = new NewUser();
					window.frmUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public NewUser() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */	private void initialize() throws IOException {
		 frmUser = new JFrame();
		 frmUser.setTitle("Add User");
		 frmUser.setBounds(100, 100, 450, 300);
		 frmUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frmUser.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		JButton btnBack = new JButton("Back");
		
		
		//COMMANDS FOR TXT VERSION, REPLACED WITH MONGODB////////////////////////////////////////////////////////////////////////////////////
		
		//Create usernames file
	//	File file = new File("users.txt");
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*button login onclick*/
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				//we didn't add checks to see whether or not the name field is a number etc, we kept it basic and simple
				if(!txtId.getText().isEmpty() || !txtPassword.getText().isEmpty() || !txtEmail.getText().isEmpty()) {
					//TXT commands
					try {
						
						MongoClient mng = new MongoClient("localhost",27017);
						DB db = mng.getDB("test");
					
						System.out.println("connected!!!");
						DBCollection usercoll = db.getCollection("users");
						BasicDBObject d1 = new BasicDBObject("name",txtId.getText()).append("password", txtPassword.getText()).append("email", txtEmail.getText());
						System.out.println(d1);
						usercoll.insert(d1);
				
						mng.close();
						
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please fill all fields");
				}
			}
		}
		);
		
		btnBack.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				LogIn lgn=new LogIn();
				LogIn.main(null); 
				frmUser.dispose();			
			}
		}
		);
		
		
		btnAdd.setBounds(213, 190, 68, 23);
		frmUser.getContentPane().add(btnAdd);
		
		btnBack.setBounds(300, 220, 100, 23);
		frmUser.getContentPane().add(btnBack);
		
		txtId = new JTextField();
		txtId.setBounds(130, 90, 155, 20);
		frmUser.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblId = new JLabel("Tester ID:");
		lblId.setBounds(50, 93, 74, 14);
		frmUser.getContentPane().add(lblId);
		
		JLabel lblLogIn = new JLabel("Add User");
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogIn.setBounds(150, 30, 120, 28);
		frmUser.getContentPane().add(lblLogIn);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(50, 123, 74, 14);
		frmUser.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(130, 120, 155, 20);
		frmUser.getContentPane().add(txtPassword);
		
		JLabel Email = new JLabel("Email:");
		Email.setBounds(50, 153, 74, 14);
		frmUser.getContentPane().add(Email);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(130, 150, 155, 20);
		frmUser.getContentPane().add(txtEmail);
		

	}
}
