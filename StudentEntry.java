/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */

import java.util.Random;
import java.util.Vector;

public class StudentEntry {
	private String name;
	private Boolean preassigned = false;
	private Vector<String> projects = new Vector<String>();
	private int added = 0;

	public StudentEntry(String myName) {
		name = myName;
	}

	public int getRanking(String pName) {
		pName = pName.intern();
		if (projects.contains(pName)) {
			return projects.indexOf(pName);
		}
		return -1;
	}

	public void addPreference(String project) {
		if (!project.isEmpty()) {
			projects.add(project);
		}
	}

	public String getStudentName() {
		return name;
	}

	public Boolean hasPreassignedProject() {
		return preassigned;
	}

	public int getNumberedOfStatedPreferences() {
		return projects.size() - added;
	}

	public Vector<String> getOrderedPreferences() {
		return projects;
	}

	public void preassignProject(String projectName) {
		projects.removeAllElements();
		projects.addElement(projectName.intern());
		preassigned = true;
	}

	public void addProject(String project) {
		projects.addElement(project.intern());
		added++;
	}

	public String toString() {
		return "Name: " + name;
	}

	public String getRandomPreference() {
		Random r = new Random();
		return projects.elementAt(r.nextInt(projects.size()));
	}

	public boolean hasPreference(String pref) {
		for (String project : projects) {
			if (project.equals(pref))
				return true;
		}
		return false;
	}
}
