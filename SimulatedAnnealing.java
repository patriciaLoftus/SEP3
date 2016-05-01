import java.util.Random;

/*
 * Group: CSNoodle
 * Author: Seán O' Doherty
 */

public class SimulatedAnnealing {
	private static final double E = 2.71828;
	Random random = new Random();
	
	public CandidateSolution change(CandidateSolution solution, double temperature) {	//Will return an accepted better/worse solution
		int prevEnergy = solution.getEnergy();
		CandidateAssignment newAssign = solution.getRandomAssignment();
		newAssign.randomizeAssignment();
		CandidateSolution acceptedSolution;
		if (solution.getEnergy() < prevEnergy) {
			acceptedSolution = solution;
		}
		else {
			int change = solution.getEnergy() - prevEnergy;
			double probability = 1 / Math.pow(E ,(change / temperature));
			if (random.nextDouble() < probability) {
				acceptedSolution = solution;
			}
			else {
				newAssign.undoChange();
				acceptedSolution = solution;
			}
		}
		return acceptedSolution;
	}
	
	public CandidateSolution getBestSolution(CandidateSolution solution, double temperature, double decrement) {
		boolean flag = true;
		CandidateSolution bestSol = new CandidateSolution(solution);
		int best = solution.getEnergy();
		while (temperature > 0 && flag) {
			int prev = solution.getEnergy();
			solution = change(solution, temperature);
			int curr = solution.getEnergy();
			if (curr < best){
				best = curr;
				bestSol = new CandidateSolution(solution);
			}
			else if (prev < curr){
				temperature = cool(temperature, curr, prev, decrement);
			
			} 

		}
		return bestSol;
	}

	private double cool(double temperature, int curr, int prev, double decrement) {
		if (temperature > 800){
			if (curr - prev > 275){
			temperature = temperature - decrement;
			}
		}
		
		else if (temperature > 400) {
			if (curr - prev > 130){
				temperature = temperature - decrement;
				}
		}
		
		else if (temperature > 200) {
			if (curr - prev > 66){
				temperature = temperature - decrement;
				}
		}
		else {
			if (curr - prev > 33){
				temperature = temperature - decrement;
			}
		}
		return temperature;
	}
}

