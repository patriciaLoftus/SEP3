import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI s = new GUI();
//					s.jf.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		GeneticAlgorithm g = new GeneticAlgorithm(200, "src/ProjectAllocationData.tsv");
		System.out.println(g.getBestSolution());
	}
}
