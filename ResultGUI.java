import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class ResultGUI 
{
	
	JFrame frame;
	private JTable tbpat;
	String head2[] ={ "Name","Project Title"};	//define table's header  
	DefaultTableModel dtm;	//initialize table controller variable 
	JScrollPane jsp;	//initialize scroll bar controller variable
	ResultSet rs1, rs2;
	CandidateSolution table;
	
	public void showRecords()
	{
		dtm.setRowCount(0);
		System.gc();
		try
		{
			int len=table.getAssignments().size()-2;
			Vector<CandidateAssignment> a=table.getAssignments();
			while (len>=0)	//iterate until last row 
			{
				String temp;
				String array[] = new String[2];
				temp=a.get(len).toString();
				String[] temp1=temp.split("Project:");
				array[0]=temp1[0].replace("Name:", "").replace(" Name", "");
				array[1]=temp1[1];
				//System.out.println(len+" "+array[0]+" p= "+array[1]);
				
                len--;
                
				dtm.addRow(array);	//add row into table
			}

			}
		catch (Exception e)
		{
			System.out.println("Error :" + e); 
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Happiness= "+ table.getEnergy() +"%");
	}

	
	/**
	 * @wbp.parser.entryPoint
	 */
	/*Creates table with result from solution1.*/
	public ResultGUI(CandidateSolution solution1) 
	{
		initialize();
		table = solution1;
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(head2);	//set column header
		tbpat = new JTable(dtm);
		jsp = new JScrollPane(tbpat);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(21, 26, 515, 334);
		frame.getContentPane().add(jsp);	//add scrollbar 
		tbpat.setRowHeight(20);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		btnNewButton.setBounds(232, 385, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		tbpat = new JTable();
		tbpat.setBounds(21, 26, 473, 323);
		tbpat.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		tbpat.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(tbpat);
		showRecords();	//call showRecords() method 
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(150, 130, 579, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
}



