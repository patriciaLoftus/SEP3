/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

public class PreferenceTable {
	private Vector<Vector<String>> textFile = new Vector<Vector<String>>();
	private Vector<StudentEntry> entries = new Vector<StudentEntry>();
	private Hashtable<String, StudentEntry> studentLookup = new Hashtable<String, StudentEntry>();
	private Vector<String> projects = new Vector<String>();

	public PreferenceTable() {

	}

	public PreferenceTable(String fileName) throws IOException {
		textFile = loadContentFromFile(fileName);
	}

	public Vector<Vector<String>> getTextFile() {
		return textFile;
	}

	private Vector<Vector<String>> loadContentFromFile(String fileName) {
		/*
		 * Returns a vector of vectors of strings, such that, the file is split
		 * up into lines and each line is split up into cells.
		 */

		Vector<Vector<String>> fullVector = new Vector<Vector<String>>();
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(fileName);

			InputStreamReader streamReader = new InputStreamReader(stream);
			BufferedReader input = new BufferedReader(streamReader);
			String line = null;
			line = input.readLine();
			line = input.readLine(); // skips the first line
			while (line != null) {
				Vector<String> stringVector = new Vector<String>();
				// split into cells
				StringTokenizer tokens = new StringTokenizer(line, "\t");
				int i = 0;
				Vector<String> pro = new Vector<String>();
				while (tokens.hasMoreTokens()) {
					String next = tokens.nextToken();
					if (i > 1) {
						pro.add(next);
					}
					if (i > 1 && stringVector.get(1).equals("No")) {
						if (!projects.contains(next.intern())) {
							projects.add(next.intern());
						}
					}
					stringVector.add(next);
					i++;
				}
				StudentEntry std = new StudentEntry(stringVector.get(0)); // make															// new
				std.setPreassigned(stringVector.get(1).equals("Yes"));															// student
				for (String project : pro) {
					std.addPreference(project);
				}
				entries.addElement(std);
				studentLookup.put(stringVector.get(0), std);

				fullVector.add(stringVector);
				line = input.readLine();
			}
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fullVector;
	}

	public Vector<String> getProjects(){
		return projects;
	}
	
	public StudentEntry getEntryFor(String name) {
		return studentLookup.get(name);
	}

	public Vector<StudentEntry> getAllStudentEntries() {
		return entries;
	}

	public StudentEntry getRandomStudent() {
		Random r = new Random();
		return entries.elementAt(r.nextInt(entries.size()));

	}

	public void fillPreferencesOfAll(int maxPrefs) {
		/*
		 * Fills entries with projects if student hasn't a preassigned project
		 */
		Random r = new Random();
		for (StudentEntry student : entries) {
			if (!student.hasPreassignedProject()) {
				for (int i = student.getOrderedPreferences().size(); i < maxPrefs; i++) {
					student.addProject(projects.elementAt(r.nextInt(projects
							.size())));
				}
			}
		}
	}

	public String getRandomPreference() {
		StudentEntry student = getRandomStudent();
		Random r = new Random();
		Vector<String> projects = student.getOrderedPreferences();
		return projects.elementAt(r.nextInt(projects.size()));
	}
}