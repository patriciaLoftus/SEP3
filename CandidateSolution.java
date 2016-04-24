/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */

import java.util.*;

public class CandidateSolution {
	private Vector<CandidateAssignment> assignments = new Vector<CandidateAssignment>();
	private PreferenceTable pref;
	private Hashtable<String, Integer> table = new Hashtable<String, Integer>();
	private static final int Penalty = 1000;
	public Vector<Bit> bits;

	public CandidateSolution(PreferenceTable pref) {
		this.pref = pref;
		for (StudentEntry std : pref.getAllStudentEntries()) { // add all
			// students
			CandidateAssignment cand = new CandidateAssignment(std);
			assignments.addElement(cand);
			bits = new Vector<Bit>();
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
	
	public void setBit(){
		for (CandidateAssignment cand : assignments){
			bits.add(cand.getBits());
		}
	}
	public void order(){
		int j;
	     boolean flag = true;   // set flag to true to begin first pass
	     Bit temp;   //holding variable

	     while ( flag )
	     {
	            flag= false;    //set flag to false awaiting a possible swap
	            for( j=0;  j < bits.size() -1;  j++ )
	            {
	            		Bit a = bits.get(j);
	            		Bit b = bits.get(j + 1);
	                   if ( a.getValue() > b.getValue() )   // change to > for ascending sort
	                   {               //swap elements
	                           bits.set(j, b);
	                           bits.set(j+1, a);
	                          flag = true;              //shows a swap occurred  
	                  } 
	            } 
	      } 
	} 
	
	public void print() {
		for (Bit b: bits) {
			//System.out.println(b.getValue());
			for (int bs: b.getBitCode()){
				System.out.print(bs);
			}
			System.out.println();
		}
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