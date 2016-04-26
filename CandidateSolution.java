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
	private Vector<Bit> bits;

	public CandidateSolution(PreferenceTable pref) {
		this.pref = pref;
		int i = 0;
		for (StudentEntry std : pref.getAllStudentEntries()) { // add all
			// students
			CandidateAssignment cand = new CandidateAssignment(std);
			allCandidates.add(cand);
			i++;
		}
		bits = new Vector<Bit>();
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
		return s;
	}

	public CandidateAssignment getRandomAssignment() {
		Random rand = new Random();
		return allCandidates.get(rand.nextInt(allCandidates.size()));
	}

	public Vector<Bit> getBits() {
		return bits;
	}

	public void setBits(Vector<Bit> bits) {
		int i = 0;
		Random r = new Random();
		Vector<Integer> ints = new Vector<Integer>();
		Vector<Integer> left = new Vector<Integer>();
		for (Bit bit : bits) {
			boolean flag = false;
			for (int j = 0; j < ints.size(); j++) {
				if (ints.get(j) - bit.getValue() == 0) {
					flag = true;
					break;
				}
			}
			if (flag || bit.getValue() >= 69) {
				left.add(i);
			} else {
				allCandidates.get(i).setBit(bit);
				allCandidates.get(i).setProject(pref.getProjects().get(bit.getValue()));
				ints.add(bit.getValue());
			}
			i++;
		}
		i = 0;
		int x = r.nextInt(69);
		for (int l : left) {
			while (ints.contains(x)) {
				x = r.nextInt(69);
			}
			ints.add(x);
			allCandidates.get(l).setBit(x);
			allCandidates.get(l).setProject(pref.getProjects().get(x));
		}
		setBits();
	}

	public void setBits() {
		bits = new Vector<Bit>();
		for (CandidateAssignment cand : allCandidates) {
			bits.add(cand.getBits());
		}
	}

	public int getEnergy() {
		table = new Hashtable<String, Integer>();
		int energy = 0;
		int penaltyCount = 0;
		for (CandidateAssignment cand : allCandidates) {
			energy += cand.getEnergy();
			String project = cand.getAssignedProject().intern();
			if (table.containsKey(project)) {
				System.out.println("FUUUUUUUUUUUUUUUCK");
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

	public int getFitness() {
		return -getEnergy();
	}
	
	public void giveRandomProjects() {
		Random rand = new Random();
		Vector<String> pro = new Vector<String>();
		for(CandidateAssignment cand: allCandidates){
			cand.randomizeAssignment();
			String current = cand.getAssignedProject();
			while(pro.contains(current)){
				int x = rand.nextInt(pref.getProjects().size());
				current = pref.getProjects().get(x);
			}
			cand.setProject(current);
			pro.add(current);
			cand.setBit(pref.getProjects().indexOf(current));
		}
	}

	private Vector<CandidateAssignment> getRC(
			Vector<CandidateAssignment> candidates) {
		Random rand = new Random();
		int i = rand.nextInt(allCandidates.size());
		CandidateAssignment cand = allCandidates.get(i);
		while (candidates.contains(cand)){
			i = rand.nextInt(allCandidates.size());
			cand = allCandidates.get(i);
		}
		candidates.add(cand);
		return candidates;
	}

	public void fillPreferences() {
		for (CandidateAssignment cand : allCandidates) {
			cand.fillPreferences(pref.getProjects());
		}
		
	}
}