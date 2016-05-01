import java.util.Random;
import java.util.Vector;

/* * Patricia Loftus
 * 13381946
 * Assignment 5
 */
public class CandidateAssignment {
	private String project = null;
	private String prev_project = null;
	private StudentEntry student;
	private Boolean preassigned;

	public CandidateAssignment(StudentEntry student) {
		this.student = student;
		randomizeAssignment();
		preassigned = student.hasPreassignedProject();
	}
	public CandidateAssignment(CandidateAssignment cand) {
		this.student = cand.student;
		this.project = cand.project;
		this.preassigned = student.hasPreassignedProject();
		this.prev_project = cand.prev_project;
	}
	
	public CandidateAssignment clone() throws CloneNotSupportedException{
		return (CandidateAssignment) super.clone();
	}

	public boolean isPreassigned() {
		return preassigned;
	}

	public String toS() {
		return student.getStudentName();
	}

	public void randomizeAssignment() {
		prev_project = project;
		project = student.getRandomPreference();
	}

	public void undoChange() {
		project = prev_project;
	}

	public StudentEntry getStudentEntry() {
		return student;
	}

	public String getAssignedProject() {
		return project;
	}

	public int getEnergy() {
		int rank = student.getRanking(project);
		if (rank != -1) {
			return (int) Math.pow(rank, 2);
		} else {
			return 11*11;
		}
	}

	public String toString() {
		return student + "\tProject: " + project;
	}
	
	public void setProject(String pName){
		prev_project = project;
		project = pName;
	}

	public void fillPreferences(Vector<String> projects) {
		Random rand = new Random();
		while (student.getOrderedPreferences().size() < 10){
			String choice = projects.get(rand.nextInt(projects.size()));
			if (!student.getOrderedPreferences().contains(choice)){
				student.addProject(choice);
			}
		}
		
	}
}