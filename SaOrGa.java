import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;

public class SaOrGa {

	JFrame frame;
	CandidateSolution cand;
	JFrame jf;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					PreferenceTable pt = new PreferenceTable("src/ProjectAllocationData.tsv");
					CandidateSolution cand = new CandidateSolution(pt);
					SaOrGa s=new SaOrGa(cand);
					//SaOrGa window = new SaOrGa();
					s.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @param cand 
	 */
	public SaOrGa(CandidateSolution cand) 
	{
		this.cand=cand;
		initialize();
	}
	public SaOrGa() 
	{
		try {
			this.cand = new CandidateSolution(new PreferenceTable("src/ProjectAllocationData.tsv"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSimulatin = new JButton("Simulated Annealing");
		btnSimulatin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
                int DResult = JOptionPane.showConfirmDialog(null,"Do you wish to change the cooling schedule?");
                if (DResult == JOptionPane.NO_OPTION) 
                {
                   // JOptionPane.showMessageDialog(null,"Deletion Cancel", "DELETION",JOptionPane.DEFAULT_OPTION);
    				SimulatedAnnealing s=new SimulatedAnnealing();
    				CandidateSolution solution1=s.getBestSolution(cand, 1000, 1);
                	ResultGUI dc=new ResultGUI(solution1);
                	dc.frame.setVisible(true);   	
                }
                if (DResult == JOptionPane.YES_OPTION)
			    {
                    String temperature = JOptionPane.showInputDialog("Enter temperature:");
                    SimulatedAnnealing s=new SimulatedAnnealing();
                    CandidateSolution solution1=s.getBestSolution(cand, Integer.parseInt(temperature), 1);
                	ResultGUI dc=new ResultGUI(solution1);
                	dc.frame.setVisible(true);

			    }
			
			}
		});
		btnSimulatin.setBounds(60, 184, 178, 29);
		frame.getContentPane().add(btnSimulatin);
		
		/*If the user chooses the Genetic Algorithm method.*/
		JButton btnNewButton = new JButton("Genetic Algorithm");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
                int DResult = JOptionPane.showConfirmDialog(null,"Would you like to change the number of generations?");
                
                /*If the user doesn't wanna change the number of generations, the program runs with the default number.*/
                if (DResult == JOptionPane.NO_OPTION) 
                {
                   // JOptionPane.showMessageDialog(null,"Deletion Cancel", "DELETION",JOptionPane.DEFAULT_OPTION);
    				GeneticAlgorithm g = new GeneticAlgorithm(200, "src/ProjectAllocationData.tsv"); 
    				CandidateSolution solution1 = g.getBestSolution(); 
    				ResultGUI outcome = new ResultGUI(solution1);
                	outcome.frame.setVisible(true);   	
                }
                
                /*If the yes button is pressed, asks the user what amount of generations they want to change to.*/
                if (DResult == JOptionPane.YES_OPTION)
			    {
                    String generations = JOptionPane.showInputDialog("Enter the amount of times you want the algorithm to run:");
    				GeneticAlgorithm g = new GeneticAlgorithm(200, "src/ProjectAllocationData.tsv"); 
    				CandidateSolution solution1 = g.getBestSolution(); 
    				ResultGUI outcome = new ResultGUI(solution1);
                	outcome.frame.setVisible(true);
			    }			
			}
		});
		btnNewButton.setBounds(250, 184, 139, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Please choose a method: ");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel.setBounds(57, 63, 387, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		panel_1.setBounds(23, 19, 400, 222);
		frame.getContentPane().add(panel_1);
	}
}
