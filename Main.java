import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreferenceTable pt = new PreferenceTable("src/ProjectAllocationData.tsv");
					CandidateSolution cand = new CandidateSolution(pt);
					StartGUI s = new StartGUI(cand);
					s.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
