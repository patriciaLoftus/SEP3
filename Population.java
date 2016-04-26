import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Population {

	public Vector<CandidateSolution> solutions;
	//public static final int FACTOR = 50;
	public static final double CULLFACTOR = 0.5;
	public static final double MATEFACTOR = 0.01;
	public PreferenceTable pref;
	

	public Population(int amount, String filename) {
		solutions = new Vector<CandidateSolution>();
		try {
			pref = new PreferenceTable(filename);
			for (int i = 0; i < amount; i++) {
				CandidateSolution sol = new CandidateSolution(pref);
				solutions.add(sol);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giveRandomProjects();
	}

	public void giveRandomProjects() {
		for (CandidateSolution sol : solutions) {
			sol.fillPreferences();
			sol.giveRandomProjects();
		}
	}

	public void add(CandidateSolution sol) {
		solutions.add(sol);
	}

	public void order() {
		// order from fittest to weakest
		int j;
		boolean flag = true;
		while (flag) {
			flag = false;
			for (j = 0; j < solutions.size() - 1; j ++){
				if (solutions.get(j).getFitness() < solutions.get(j+1).getFitness()){
					CandidateSolution temp = solutions.get(j);
					solutions.set(j, solutions.get(j+1));
					solutions.set(j+1, temp);
					flag = true;
				}
			}
		}
	}

	public void cull() {
		System.out.println("SIZE: " + solutions.size());
		int amount = (int) (solutions.size() * CULLFACTOR);
		for (int i = 0; i < amount; i++){
			solutions.remove(solutions.size()-1);
		}
	}
	
	public void mate(){
		boolean flag = true;
		double a = solutions.size() * MATEFACTOR;
		long amount = Math.round(a);
		if(amount < 2){amount = 3;}
		Vector<CandidateSolution> sols = getFittest(amount);
		Vector<CandidateSolution> newsols = new Vector<CandidateSolution>();
		System.out.println("Amount "+ amount);
		for (int i = 0; i < amount; i++){
			for (int j = i; j < amount ; j++){
				if(j < solutions.size()){
					CandidateSolution newSol  = pushMate(sols.get(i),sols.get(j), flag);
					newsols.add(newSol);
					flag = !flag;
				}
			}
			for(int j = 0; j < solutions.size(); j++){
				CandidateSolution newSol  =  pushMate(sols.get(i),solutions.get(j), flag);
				newsols.add(newSol);
				flag = !flag;
			}
		}	
		solutions = newsols;
	}
	
	public CandidateSolution pushMate(CandidateSolution a, CandidateSolution b, boolean flag){
		if (flag){
			CandidateSolution temp = b;
			b = a;
			a = temp;
		}
		Random r = new Random();
		a.setBits();
		b.setBits();
		Vector<Bit> newbits = new Vector<Bit>();
		CandidateSolution sol = new CandidateSolution(pref);
		int i = 0;
		int pivot = r.nextInt(51);
		for (Bit aBits : a.getBits()) {
			Bit newProjectCode = new Bit(aBits.getBitCode());
			Bit bBits = b.getBits().get(i);
				if( i ==  pivot){
					newProjectCode = aBits.merge(bBits, r.nextInt(bBits.getBitCode().length));
				}
				else if(i > pivot){
					newProjectCode = new Bit(bBits.getBitCode());
				}
				
			i++;
			newbits.add(newProjectCode);
		}
		sol.setBits(newbits);
		return sol;
	}
	
	public void mutate(){
			Random r = new Random();
			int x = r.nextInt(solutions.size());
			if (x > 2){
				CandidateSolution sol = solutions.elementAt(x);
				sol.giveRandomProjects();
				order();
			}
	}

	public Vector<CandidateSolution> getFittest(long amount) {
		Vector<CandidateSolution> fittest = new Vector<CandidateSolution>();
		for (int i = 0; i < amount; i++) {
			if(solutions.size() > i){
				fittest.add(solutions.elementAt(i));
			}
		}
		for (CandidateSolution fit: fittest){
			solutions.remove(fit);
		}
		return fittest;
	}
	public int getBestFitness(){
		return solutions.elementAt(0).getFitness();
	}
	public CandidateSolution getBestSol(){
		return solutions.elementAt(0);
	}
	
	public void GA(){
		boolean flag = true;
		int fit = solutions.elementAt(0).getFitness();
		int prev = -1000;
		int i = 1;
		while (prev < fit || i == 1){
			order();
			cull();
			mate();
			order();
			prev = fit;
			fit = solutions.elementAt(0).getFitness();
			System.out.println("GEN " + i + " BEST: " + getBestFitness() + " worst "+ solutions.get(solutions.size()-1).getFitness());
			i++;
		}
		
		System.out.println("END");
		CandidateSolution sol = getBestSol();
		System.out.println(sol.getFitness());
	}
}
