import java.util.Random;

/*
 * Group: CSNoodle
 * Author: Seán O' Doherty
 */

public class SimulatedAnnealing {
	private static final double E = 2.71828;
	Random random = new Random();
	
	public CandidateSolution change(CandidateSolution solution, int temperature) {	//Will return an accepted better/worse solution
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
	
	public CandidateSolution getBestSolution(CandidateSolution solution, int temperature, int decrement) {
		boolean flag = true;
		while (temperature > 0 && flag) {
			int prev = solution.getEnergy();
			solution = change(solution, temperature);
			int curr = solution.getEnergy();
		
				temperature = temperature - decrement; 

				if (solution.getEnergy() > 270){
					if (temperature < 10){
						temperature += temperature*.5;
					}
				}

		}
		System.out.println(solution);
		return solution;
	}
}

