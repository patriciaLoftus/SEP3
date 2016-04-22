/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */

import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

public class CandidateSolution {
	private Vector<CandidateAssignment> assignments = new Vector<CandidateAssignment>();
	private PreferenceTable pref;
	private Hashtable<String, Integer> table = new Hashtable<String, Integer>();
	private static final int Penalty = 1000;

	public CandidateSolution(PreferenceTable pref) {
		this.pref = pref;
		for (StudentEntry std : pref.getAllStudentEntries()) { // add all
																// students
			assignments.addElement(new CandidateAssignment(std));
		}
	}

	public CandidateAssignment getAssignmentFor(String name) {
		for (CandidateAssignment cand : assignments) {
			if (cand.getStudentEntry().getStudentName().equals(name)) {
				return cand;
			}
		}
		return null;
	}

	public CandidateAssignment getRandomAssignment() {
		Random rand = new Random();
		return assignments.get(rand.nextInt(assignments.size()));
	}

	public int getEnergy() {
		table = new Hashtable<String, Integer>();
		int energy = 0;
		int penaltyCount = 0;
		for (CandidateAssignment cand : assignments) {
			energy += cand.getEnergy();
			String project = cand.getAssignedProject().intern();
			if (table.containsKey(project)) {
				penaltyCount++;
				int v = table.get(project);
				table.replace(project, v + 1);			
				//keep track of number of collisions in a project
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
}
