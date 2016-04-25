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
	private Bit projectCode;
	private Boolean preassigned;

	public CandidateAssignment(StudentEntry student) {
		this.student = student;
		randomizeAssignment();
		preassigned = student.hasPreassignedProject();
	}

	public boolean isPreassigned() {
		return preassigned;
	}

	public String toS() {
		return student.getStudentName();
	}

	public void setBits(int a) {
		projectCode = new Bit(a);

	}

	public Bit getBits() {
		return projectCode;
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
	
	public void setBit(int b) {
		projectCode = new Bit(b);
	}

	public void setBit(Bit bit) {
		projectCode = bit;
	}
}