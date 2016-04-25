import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Population {

	public Vector<CandidateSolution> solutions;
	public static final int FACTOR = 50;
	public static final double CULLFACTOR = 0.1;
	public static final double MATEFACTOR = 0.1;
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
			sol.giveRandomProjects();
		}
	}

	public CandidateSolution mate(CandidateSolution a, CandidateSolution b) {
		Vector<Bit> newbits = new Vector<Bit>();
		CandidateSolution sol = new CandidateSolution(pref);
		Random r= new Random();
		int i = 0;
		double x = 0.0;
		for (Bit aBits : a.getBits()) {
			Bit newProjectCode = new Bit(aBits.getBitCode());
			Bit bBits = b.getBits().get(i);
			int j = 0;
			for (int aBit : aBits.getBitCode()) {
				if (aBit!= bBits.getBitCode()[j])
				{
					x = r.nextInt(100)/100.0;
					if (x > 0.5){
						newProjectCode.flip(j);
					}
				}
				j++;
			}
			i++;
			newbits.add(newProjectCode);
		}
		sol.setBits(newbits);
		return sol;
	}

	public void add(CandidateSolution sol) {
		solutions.add(sol);
	}

	public void order() {
		// order from fittest to weakest
		for (int i = 0; i < solutions.size() - 1; i++) {
			for (int j = 0; j < solutions.size() - i - 1; j++) {
				if (solutions.get(j).getFitness() < solutions.get(j + 1)
						.getFitness()) {
					Collections.swap(solutions, j, j + 1);
				}
			}
		}
	}

	public void cull() {
		order();
		int amount = (int) (solutions.size() * CULLFACTOR);
		Vector<CandidateSolution> sols = getWeakest(amount);
		for (CandidateSolution indiv: sols){
			solutions.remove(indiv);
		}
	}
	
	public void mate(){
		Random r = new Random();
		int amount = (int) (solutions.size() * MATEFACTOR);
		Vector<CandidateSolution> sols = getFittest(amount/2);
		for ( int i = 0; i < amount/2; i++){
			sols.addElement(solutions.get(r.nextInt(solutions.size())));
		}	
		for(int i = 0; i < amount/2; i++ ){
			CandidateSolution newSOL = pushMate(sols.elementAt(i), sols.elementAt(sols.size()- 1 - i));
			System.out.println("NEW SOL " + newSOL.getFitness());
			solutions.add(newSOL);
		}
	}
	
	public CandidateSolution pushMate(CandidateSolution a, CandidateSolution b){
		a.setBits();
		b.setBits();
		Vector<Bit> newbits = new Vector<Bit>();
		CandidateSolution sol = new CandidateSolution(pref);
		Random r= new Random();
		int i = 0;
		int x = 0;
		for (Bit aBits : a.getBits()) {
			Bit newProjectCode = new Bit(aBits.getBitCode());
			Bit bBits = b.getBits().get(i);
			if (aBits.getValue() != bBits.getValue()){
				x = r.nextInt(100);
				if( x > FACTOR){
					newProjectCode = new Bit(bBits.getBitCode());
				}
			}
			i++;
			newbits.add(newProjectCode);
		}
		sol.setBits(newbits);
		return sol;
	}
	
	public void mutate(){
			CandidateSolution sol = new CandidateSolution(pref);
			sol.giveRandomProjects();
			System.out.println("MUTATION: " + sol.getFitness());
			solutions.add(sol);
	}

	public Vector<CandidateSolution> getWeakest(int x) {
		order();
		Vector<CandidateSolution> weakest = new Vector<CandidateSolution>();
		order();
		for (int i = 0; i < x; i++) {
			if (solutions.size() - i > 0) {
				weakest.add(solutions.elementAt(solutions.size()- 1 - i));
			}
		}
		return weakest;
	}

	public Vector<CandidateSolution> getFittest(int x) {
		Vector<CandidateSolution> fittest = new Vector<CandidateSolution>();
		order();
		for (int i = 0; i < x; i++) {
			fittest.add(solutions.elementAt(i));
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
		for(int i = 0; i < 15; i++){
			mate();
			mutate();
			cull();
			System.out.println("GEN " + i + " " + getBestFitness());
		}
		CandidateSolution sol = getBestSol();
	}
}
