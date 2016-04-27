import java.io.IOException;
import java.util.Vector;

/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */

public class Main {
	public static void main(String args[]) throws IOException {
		PreferenceTable pt = new PreferenceTable("src/ProjectAllocationData.tsv");
		CandidateSolution cand = new CandidateSolution(pt);
		SimulatedAnnealing sa = new SimulatedAnnealing();
		sa.getBestSolution(cand, 1000, 1);
		
//		Population p = new Population(200,"src/ProjectAllocationData.tsv");
//		p.GA();
	}
}