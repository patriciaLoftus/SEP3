
public class GeneticAlgorithm {
	private Population p;
	public GeneticAlgorithm(int size, String file){
		p = new Population(size, file);
	}
	public CandidateSolution getBestSolution(){
		boolean flag = true;
		p.order();
		int fit = 0;
		int i = 0;
		int prev = p.getBestFitness();
		int prevWorst = p.solutions.get(p.solutions.size()-1).getFitness();
		int worst = p.solutions.get(p.solutions.size()-1).getFitness();
		while (flag){
			p.cull();
			p.mate();
			p.order();
			//upate fitness values
			prev = fit;
			prevWorst = worst;
			worst = p.solutions.get(p.solutions.size()-1).getFitness();
			fit = p.getBestFitness();
			if (prev == fit){
				i++;
			}
			else {
				i = 0;
			}
			flag = (prevWorst != worst || prev != fit)&& fit != worst && i != 10; 
		}
		
		return p.getBestSol();
	}
}
