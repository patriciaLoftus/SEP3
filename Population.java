import java.util.Vector;

public class Population {
	
	public Vector<CandidateSolution> solutions ;
	
	public Population(int amount) {
		solutions = new Vector<CandidateSolution>();
		PreferenceTable pref = new PreferenceTable();
		for (int i=0; i<amount; i++) {
			solutions.add(new CandidateSolution(pref));
		}
	}
	
	public void order(){
		//order from fittest to weakest
	}
	
	public void cull(){
		//remove x weakest
	}
	
	public void mate(){
		//mate x fittest
	}
	
	public Vector<CandidateSolution> getWeakest(int x) {
		Vector<CandidateSolution> weakest = new Vector<CandidateSolution>();
		for(int i = 0; i < x; i++) {
			weakest.add(solutions.elementAt(i));
		}
		return weakest;
	}
	
	public Vector<CandidateSolution> getFittest(int x) {
		Vector<CandidateSolution> fittest = new Vector<CandidateSolution>();
		for(int i = 0; i < x; i++) {
			fittest.add(solutions.elementAt(i));
		}
		return fittest;
	}
}
