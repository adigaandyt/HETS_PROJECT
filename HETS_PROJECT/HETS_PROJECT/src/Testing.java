import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;

public class Testing {

	private JFrame frmTesting;
	public File   Cfile;//file of directory source c
	public File[] arrayofC;//array of files source c
	public String direxefile="";//path directory exe file
	public String diroutputfile="";//path directory output file
	public String dirreportfile="";//path directory report file
	public static String inputfile="";//path inout file
	public static String expectedoutput="";//expected output
	public File report;//file csv report
	public  FileWriter fileWriter;
	public BufferedWriter bufferedWriter;
	public String source=Home.pathOfFile;
	public static int counttested=0;//count file c is tested
	public static int countcompiled=0;//count file is compiled successfull
	
	Thread thread=new Thread(()->checkfilehomework());
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testing window = new Testing();
					window.frmTesting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Testing() {
		initialize();
		
	
		
	}
	/*remove extension file*/
	public String RemoveExtension(String filename)
	{
		return filename.substring(0, filename.lastIndexOf('.'));
	}
	/*get extension file*/
	public  String fileExtension(String name) {
		   
		   if(name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0)//check extension file
		      return name.substring(name.lastIndexOf(".") + 1);//extension file
		   else
		      return "";
		}
	/*create directory*/
	public String createdir(String mkdir) 
	{
		String dirpath=source+"\\"+mkdir;
		File dir=new File(dirpath);
			if(dir.exists())
			dir.delete();
			boolean b=dir.mkdir();
			//if(b)
		return dir.getAbsolutePath();
		//	else
			//	return "";
	}
	/*check file c to compile*/
	public boolean compile(String dirC,String direxe) {
		String  cmd="powershell.exe cd C:\\MinGW\\bin ; ./gcc.exe"+" "+dirC +" "+"-o"+" "+direxe;
		try {
			Process run=Runtime.getRuntime().exec(cmd);
			 BufferedReader stdout = new BufferedReader(new InputStreamReader(run.getInputStream()));
			// stdout.close();
			BufferedReader stderr = new BufferedReader(new InputStreamReader(run.getErrorStream()));
			String line;
			while ((line = stderr.readLine()) != null) {//error compile
				  if(line.contains("error"))//error compiled
				  {
					  return false;
				  }
			}
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*run exe file*/
	public boolean run(String direxe,String inputfile,String diroutput)
	{
		String cmd="powershell.exe cat "+inputfile+" "+"|"+" "+direxe+" "+">"+" "+diroutput;
		try {
			Process run=Runtime.getRuntime().exec(cmd);
			BufferedReader stdout = new BufferedReader(new InputStreamReader(run.getInputStream()));
			//run.getOutputStream().close();
			BufferedReader stderr = new BufferedReader(new InputStreamReader(
				    run.getErrorStream()));
			String line;
			while ((line = stderr.readLine()) != null)//error run
			{
				return false;
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	/*check valid output*/
	public boolean validoutput(String diroutput,String diroutputexpec)
	{
		String cmd="powershell.exe diff "+"( cat "+diroutput+")"+" "+"( cat "+diroutputexpec+")";
		try {
			Process run=Runtime.getRuntime().exec(cmd);
			BufferedReader stderr = new BufferedReader(new InputStreamReader(run.getErrorStream()));
			BufferedReader stdout = new BufferedReader(new InputStreamReader(run.getInputStream()));
			String line;
			while ((line = stderr.readLine()) != null)//output not valid 
			{
				return false;
				  
			}
			while ((line = stdout.readLine()) != null)//output not valid
			{
				return false;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	/*create report*/
	public boolean createreport(String reportfile)
	{
		try {
			report=new File(dirreportfile+"/"+reportfile+".csv");
			fileWriter=new FileWriter(report);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
			return false;
		}
		bufferedWriter=new BufferedWriter(fileWriter);
		try {
			bufferedWriter.write("source c "+","+"\t compile "+","+"\t outvalid");
			bufferedWriter.newLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			return false;
		}
		return true;
	}
	/*report csv file*/
	public boolean report(String dirC,boolean comp,boolean outputvalid)
	{
		try {
			String compiled,outvalid;
			if(comp)
			{
				compiled="yes";
			}
			else
			{
				compiled="no";
			}
			if(outputvalid)
			{
				outvalid="valid output";
			}
			else
			{
				outvalid="invalid output";
			}
			bufferedWriter.write(dirC+","+compiled+","+outvalid);
			bufferedWriter.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	/*check files homework*/
	public void checkfilehomework() {
		boolean isrun=false,iscompile = false,isvalid = false;
		if(arrayofC!=null)
		{
			for(int i=0;i<arrayofC.length;i++)
			{
				String namfile=arrayofC[i].getName();
				if(fileExtension(namfile).equals("c")) {
					counttested++;
					String direxe=direxefile+"\\"+RemoveExtension(namfile)+".exe";
					 iscompile=compile(source+"\\"+namfile,direxe);
					if(iscompile)
					{
						System.out.println(namfile+" compiled successfully");
						String diroutput=diroutputfile+"\\"+RemoveExtension(namfile)+".text";
							
						 isrun=run(direxe, inputfile, diroutput);
						 countcompiled++;
						if(isrun)
						{
							System.out.println(namfile+" run successfully");
							if(HETS_2.hets2==false)
							isvalid=validoutput(diroutput, expectedoutput);
						if(isvalid)
						{
							System.out.println(namfile+" valid output");
						}
						else
						{
							System.out.println(namfile+" not valid output");
						}
						}
						else
						{
							System.out.println(namfile+" run failed");
						}
						
					}
			
					else {
						System.out.println(namfile+" compiled error");
					}
					report(namfile,iscompile,isvalid);
				}
			}
			System.out.println("====>");
			System.out.println("end check all the file");
			try {
				bufferedWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTesting = new JFrame();
		frmTesting.setTitle("Testing");
		frmTesting.setBounds(100, 100, 450, 300);
		frmTesting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTesting.getContentPane().setLayout(null);
		
		JLabel lblexercises = new JLabel(".c home exercises:");
		lblexercises.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblexercises.setBounds(23, 30, 169, 23);
		frmTesting.getContentPane().add(lblexercises);
		
		/*init file*/
		Cfile=new File(Home.pathOfFile);
		 arrayofC=Cfile.listFiles();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home home=new Home();
				Home.main(null);
            	frmTesting.dispose();	
			}
		});
		btnBack.setBounds(23, 227, 75, 23);
		frmTesting.getContentPane().add(btnBack);
		JButton btnStartTesting = new JButton("Start testing");
		/*onclick testing*/
		btnStartTesting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*init directory*/
			direxefile=createdir("exefile");
			diroutputfile=createdir("outputfile");
			dirreportfile=createdir("reportfile");
			if(direxefile!=""&&diroutputfile!=""&&dirreportfile!="")
			{
			createreport("homework");
			thread.start();
			}
			//checkfilehomework();
			}
		});
		btnStartTesting.setBounds(121, 227, 104, 23);
		frmTesting.getContentPane().add(btnStartTesting);
		JPanel panel = new JPanel(new BorderLayout());
		JList list = new JList();
		
		list.setBounds(10, 64, 395, 127);
		frmTesting.getContentPane().add(list);
		
	     //list file in c 
		DefaultListModel<String> model=new DefaultListModel<String>();
		if(arrayofC!=null)
			for(int i=0;i<arrayofC.length;i++)
			{
				if(fileExtension(arrayofC[i].getName()).equals("c"))
				{
				model.addElement(arrayofC[i].getName());
				}
			}
		list.setModel(model);
		
		JButton btnReporting = new JButton("Reporting");
		btnReporting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reporting Reporting=new Reporting();
				Reporting.main(null);
            	frmTesting.dispose();	
			}
			
		});
		btnReporting.setBounds(254, 227, 89, 23);
		frmTesting.getContentPane().add(btnReporting);
		
		
	}
}
