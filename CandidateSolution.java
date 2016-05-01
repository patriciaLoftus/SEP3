/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */

import java.util.*;

public class CandidateSolution {
	private Vector<CandidateAssignment> allCandidates = new Vector<CandidateAssignment>();
	private PreferenceTable pref;
	private Hashtable<String, Integer> table = new Hashtable<String, Integer>();
	private static final int Penalty = 1000;

	public CandidateSolution(PreferenceTable pref) {
		this.pref = pref;
		int i = 0;
		for (StudentEntry std : pref.getAllStudentEntries()) { // add all
			// students
			CandidateAssignment cand = new CandidateAssignment(std);
			allCandidates.add(cand);
			i++;
		}
	}
	public CandidateSolution(CandidateSolution sol) {
		for (CandidateAssignment cand : sol.allCandidates){
			this.allCandidates.add(new CandidateAssignment(cand));
		}
	}	
	public Vector<CandidateAssignment> getAssignments(){
		return allCandidates;
	}
	public int size(){
		return allCandidates.size();
	}

	public CandidateAssignment getAssignmentFor(String name) {
		for (CandidateAssignment cand : allCandidates) {
			if (cand.getStudentEntry().getStudentName().equals(name)) {
				return cand;
			}
		}
		return null;
	}

	public String toString() {
		String s = "";
		int i = 0;
		for (CandidateAssignment c : allCandidates) {
			String name = c.toS();
			s = s.concat(name + " :- "
					+ allCandidates.get(i).getAssignedProject());
			s = s.concat("\n");
			i++;
		}
		s = s + "\nTOTAL FITNESS: " + getFitness();
		s = s + "\nTOTAL Energy: " + getEnergy();
		return s;
	}

	public CandidateAssignment getRandomAssignment() {
		Random rand = new Random();
		return allCandidates.get(rand.nextInt(allCandidates.size()));
	}

	public int getEnergy() {
		table = new Hashtable<String, Integer>();
		int energy = 0;
		int penaltyCount = 0;
		for (CandidateAssignment cand : allCandidates) {
			energy += cand.getEnergy();
			String project = cand.getAssignedProject().intern();
			if (table.containsKey(project)) {
				// System.out.println("FUUUUUUUUUUUUUUUCK");
				penaltyCount++;
				int v = table.get(project);
				table.replace(project, v + 1);
				// keep track of number of collisions in a project
			} else {
				table.put(cand.getAssignedProject(), 0);
			}
		}
		return energy + (penaltyCount * Penalty);
	}

	public int getPenalties() {
		int count = 0;
		for (String key : table.keySet()) {
			count += table.get(key);
		}
		return count;
	}
	
	public int getRandomProjectsAssigned(){
		int count = 0;
		for (CandidateAssignment c: allCandidates){
			if (c.getEnergy() > 100){
				count++;
			}
		}
		return count;
	}

	public int getFitness() {
		return -getEnergy();
	}
	
	public void replace(CandidateAssignment cand){
		for (CandidateAssignment c: allCandidates){
			if (c.getStudentEntry().getStudentName().equals(cand.getStudentEntry().getStudentName())){
				c.setProject(cand.getAssignedProject());
			}
		}
	}
	public void fillPreferences() {
		for (CandidateAssignment cand : allCandidates) {
			cand.fillPreferences(pref.getProjects());
		}

	}

	public void removeClashes() {
		Vector<String> projects = new Vector<String>();
		Vector<Integer> index = new Vector<Integer>();
		for(int i = 0; i < allCandidates.size(); i++){
			index.add(i);
		}
		Collections.shuffle(index);
		for(int j :index){
			CandidateAssignment c = allCandidates.get(j);
			int i = 0;
			Random r = new Random();
			while(projects.contains(c.getAssignedProject())){
				if (i == 15){
					for (String str: pref.getProjects()){
						if (!projects.contains(str)) {
							c.setProject(str);
						}
					}
				}
				else{
					c.randomizeAssignment();
					i++;
				}
			}
			projects.add(c.getAssignedProject());
		}
	}
}