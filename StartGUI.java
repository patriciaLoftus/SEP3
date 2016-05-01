import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class StartGUI 
{
	JFrame frame;
	CandidateSolution cand;
	JFrame jf;

	/**
	 * Creates the application.
	 * @param cand 
	 */
	public StartGUI(CandidateSolution cand) 
	{
		this.cand=cand;
		initialize();
	}
	public StartGUI() 
	{
		initialize();
	}
	/**
	 * Initializes the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*Lets the user chooses which method they want to use.*/
		JButton btnSimulatin = new JButton("Simulated Annealing");
		
		/*If the Simulated Annealing button is pressed, asks the user if they want to change the cooling schedule.*/
		btnSimulatin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
                int DResult = JOptionPane.showConfirmDialog(null,"Do you wish to change the cooling schedule?");
                
                /*If the user doesn't want to change the schedule, the program runs with the default schedule.*/
                if (DResult == JOptionPane.NO_OPTION) 
                {
                   // JOptionPane.showMessageDialog(null,"Deletion Cancel", "DELETION",JOptionPane.DEFAULT_OPTION);
    				SimulatedAnnealing s = new SimulatedAnnealing();
    				CandidateSolution solution1 = s.getBestSolution(cand, 1000, 1);
                	ResultGUI outcome = new ResultGUI(solution1);
                	outcome.frame.setVisible(true);   	
                }
                
                /*If the yes button is pressed, asks the user what temperature they want to change to.*/
                if (DResult == JOptionPane.YES_OPTION)
			    {
                    String temperature = JOptionPane.showInputDialog("Enter temperature:");
                    SimulatedAnnealing s=new SimulatedAnnealing();
                	//s.change(cand, temperature);
                    CandidateSolution solution1=s.getBestSolution(cand, Integer.parseInt(temperature), 1);
                	ResultGUI outcome = new ResultGUI(solution1);
                	outcome.frame.setVisible(true);
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
                int DResult = JOptionPane.showConfirmDialog(null,"Would you like to change the population size?");
                
                /*If the user doesn't want to  change the number of population size, the program runs with the default number.*/
                if (DResult == JOptionPane.NO_OPTION) 
                {
    				GeneticAlgorithm g = new GeneticAlgorithm(200, "src/ProjectAllocationData.tsv"); 
    				CandidateSolution solution1 = g.getBestSolution(); 
                	ResultGUI outcome=new ResultGUI(solution1);
                	outcome.frame.setVisible(true);   	
                }
                
                /*If the yes button is pressed, asks the user what population size they want to change to.*/
                if (DResult == JOptionPane.YES_OPTION)
			    {
                    String popszie = JOptionPane.showInputDialog("Enter the population size:");
    				GeneticAlgorithm g = new GeneticAlgorithm(Integer.parseInt(popszie), "src/ProjectAllocationData.tsv");
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


