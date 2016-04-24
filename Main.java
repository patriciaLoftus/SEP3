import java.io.IOException;
import java.util.Vector;

/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */

public class Main {
	public static void main(String args[]) throws IOException {
		String filename = "src/ProjectAllocationData.tsv";
		PreferenceTable table = new PreferenceTable(filename);

		for (Vector<String> vector : table.getTextFile()) { // iterate through
															// each line
			for (String string : vector) {
				System.out.print(string + "\t"); // print each cell, as would be
													// displayed in the file
			}
			System.out.println();
		}

		System.out.println("\n\n");

		for (StudentEntry student : table.getAllStudentEntries()) {
			System.out.println(student);
		}

		System.out.println("\n\n");
		System.out.println(table.getEntryFor("Jesus Christ"));

		table.fillPreferencesOfAll(10);

		System.out.println("Random Student:"
				+ table.getRandomStudent().getStudentName());
		System.out.println("Random Student:"
				+ table.getRandomStudent().getStudentName());
		System.out.println("Random Student:"
				+ table.getRandomStudent().getStudentName());
		System.out.println();

		StudentEntry student = table.getRandomStudent();

		System.out.println("Random project of " + student.getStudentName()
				+ "\t" + student.getRandomPreference());
		System.out.println("Random project of " + student.getStudentName()
				+ "\t" + student.getRandomPreference());
		System.out.println("Random project of " + student.getStudentName()
				+ "\t" + student.getRandomPreference());
		System.out.println();
		student = table.getRandomStudent();

		System.out.println("After Filled");

		System.out
				.println(student.getStudentName() + ", stated preferences: "
						+ student.getNumberedOfStatedPreferences()
						+ " all preferences: "
						+ student.getOrderedPreferences().size());
		System.out.println();

		CandidateSolution sol = new CandidateSolution(table);
		CandidateAssignment cand = sol.getRandomAssignment();

		System.out.println("New Candidate");
		System.out.println(cand);
		cand.randomizeAssignment();
		System.out.println("New Project");
		System.out.println(cand);
		cand.undoChange();
		System.out.println("Previous Project");
		System.out.println(cand);
		System.out.println();

		String name = table.getRandomStudent().getStudentName();
		System.out.println("Get Assignment For: " + name);
		System.out.println(sol.getAssignmentFor(name));
		System.out.println("Get Assignment For Random Student");
		System.out.println(sol.getRandomAssignment());

		// Assignment 5 Tests
//		System.out.println("\n\n Assignment 5 \n\n");
//		CandidateSolution best = new CandidateSolution(table);
//		int n = 1000000;
//		for (int i = 0; i < n; i++) {
//			sol = new CandidateSolution(table);
//			if (sol.getEnergy() < best.getEnergy()) {
//				best = sol;
//			}
//		}
//
//		System.out.println("Best out of "+ n +" solutions: " + best.getEnergy() + " penalties: " + best.getPenalties());
//	}
	
	System.out.println("***************BITS****************************");
	sol.setBit();
	sol.order();
	sol.print();
	}
}