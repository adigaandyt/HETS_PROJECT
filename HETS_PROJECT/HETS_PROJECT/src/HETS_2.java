import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionEvent;

public class HETS_2 {

	private JFrame frmHets;
	private JTextField textLower;
	private JTextField textHigher;
	static boolean  hets2=false;//if hets2 not choose
	private int countofbound=0;//count of boundary
	File filehets2;//file boundary
	File fileinput;//file boundary choose
	FileWriter fileWriterhets2;
	BufferedWriter bufferedWriterhets2;
	FileWriter fileWriterinput;
	BufferedWriter bufferedWriterinput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HETS_2 window = new HETS_2();
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
	public HETS_2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHets = new JFrame();
		frmHets.setTitle("HETS_2");
		frmHets.setBounds(100, 100, 450, 300);
		frmHets.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHets.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Boundary Value Method");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(110, 24, 296, 33);
		frmHets.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Input type:");
		lblNewLabel_1.setBounds(27, 84, 108, 14);
		frmHets.getContentPane().add(lblNewLabel_1);
		
		JLabel lblLowerBoundary = new JLabel("Lower boundary:");
		lblLowerBoundary.setBounds(27, 122, 108, 14);
		frmHets.getContentPane().add(lblLowerBoundary);
		
		JLabel lblHigherBoundary = new JLabel("Higher boundary:");
		lblHigherBoundary.setBounds(27, 163, 108, 14);
		frmHets.getContentPane().add(lblHigherBoundary);
		
		JButton btnBack = new JButton("Back");
		/*button back onclick*/
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home=new Home();
				Home.main(null);
            	frmHets.dispose();	
			}
		});
		btnBack.setBounds(97, 227, 72, 23);
	frmHets.getContentPane().add(btnBack);
	
	textLower = new JTextField();
		textLower.setBounds(166, 119, 113, 20);
		frmHets.getContentPane().add(textLower);
		textLower.setColumns(10);
		
		textHigher = new JTextField();
		textHigher.setColumns(10);
		textHigher.setBounds(166, 160, 113, 20);
		frmHets.getContentPane().add(textHigher);
		
		
		JButton btnNext = new JButton("Next");
		/*button next onclick*/
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(countofbound>=1) {
					try {
						bufferedWriterhets2.flush();
						bufferedWriterinput.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Testing.inputfile=fileinput.getAbsolutePath();
					hets2=true;
				Testing testing=new Testing();
				testing.main(null);
            	frmHets.dispose();	
			}
				else
				{
					JOptionPane.showMessageDialog(null, "you must to define boundary");
				}
				
			}
		});
		btnNext.setBounds(218, 227, 72, 23);
		frmHets.getContentPane().add(btnNext);
		JLabel lblcountinput = new JLabel("Count of input:");
		lblcountinput.setBounds(145, 192, 261, 14);
		frmHets.getContentPane().add(lblcountinput);
	
		JComboBox InputType = new JComboBox();
		InputType.setModel(new DefaultComboBoxModel(new String[] {"int", "double", "float"}));
		InputType.setBounds(166, 80, 113, 22);
		frmHets.getContentPane().add(InputType);
		
		JButton btninsertbound = new JButton("Insert Boundary");
		/*btn insert boundary onclick*/
		btninsertbound.addActionListener(new ActionListener() {
			Random random=new Random();
			public void actionPerformed(ActionEvent e) {
			if(textLower.getText().equals("")||textHigher.getText().equals(""))//is not empty
			JOptionPane.showMessageDialog(null, "failed boundary");
			else
			{
				double min=Double.parseDouble(textLower.getText());
				double max=Double.parseDouble(textHigher.getText());
				if(min>max)//minboundary biggest maxboundary
				{
					JOptionPane.showMessageDialog(null, "failed boundary");
				}
			
				else
				{
					countofbound++;
					lblcountinput.setText("count of input boundary: "+countofbound);
					
					if(countofbound==1)//at least write one boundary
					{
						filehets2=new File(Home.pathOfFile+"/hets2.txt").getAbsoluteFile();
						fileinput=new File(Home.pathOfFile+"/hets2input.txt").getAbsoluteFile();
						try {
							fileWriterhets2=new FileWriter(filehets2);
							fileWriterinput=new FileWriter(fileinput);
							bufferedWriterhets2=new BufferedWriter(fileWriterhets2);
							bufferedWriterinput=new BufferedWriter(fileWriterinput);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				if(InputType.getSelectedItem().equals("int"))//type boundary
				{
					int minint=(int)min;
					int maxint=(int)max;
					try {
						bufferedWriterhets2.write("int");
						bufferedWriterhets2.write(" ");
						bufferedWriterhets2.write(minint+"");
						bufferedWriterhets2.write(" ");
						bufferedWriterhets2.write(maxint+"");
						bufferedWriterhets2.newLine();
						int randint=minint+random.nextInt(maxint-minint+1);
						bufferedWriterinput.write(randint+"");
						bufferedWriterinput.newLine();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(InputType.getSelectedItem().equals("float"))//type boundary
				{
					float minfloat=(float)min;
					float maxfloat=(float)max;
					try {
						bufferedWriterhets2.write("int");
						bufferedWriterhets2.write(" ");
						bufferedWriterhets2.write(minfloat+"");
						bufferedWriterhets2.write(" ");
						bufferedWriterhets2.write(maxfloat+"");
						bufferedWriterhets2.newLine();
						float randfloat=minfloat+(maxfloat-minfloat)*random.nextFloat();
						bufferedWriterinput.write(randfloat+"");
						bufferedWriterinput.newLine();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				if(InputType.getSelectedItem().equals("double"))//type boundary
				{
					double mindouble=(double)min;
					double maxdouble=(double)max;
					try {
						bufferedWriterhets2.write("double");
						bufferedWriterhets2.write(" ");
						bufferedWriterhets2.write(mindouble+"");
						bufferedWriterhets2.write(" ");
						bufferedWriterhets2.write(maxdouble+"");
						bufferedWriterhets2.newLine();
						double randdouble=mindouble+(maxdouble-mindouble)*random.nextDouble();
						bufferedWriterinput.write(randdouble+"");
						bufferedWriterinput.newLine();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
					
				}
			}
			}
			
			
		});
		btninsertbound.setBounds(22, 188, 113, 23);
		frmHets.getContentPane().add(btninsertbound);
		
		
	}
}
