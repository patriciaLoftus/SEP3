/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */
public class CandidateAssignment {
	private String project = null;
	private String prev_project = null;
	private StudentEntry student;

	public CandidateAssignment(StudentEntry student) {
		this.student = student;
		randomizeAssignment();
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
	
	public int getEnergy(){
		int rank = student.getRanking(project);
		if (rank != -1) {
			return (rank + 1)^2;
		}
		else {
			return 121;
		}
	}

	public String toString() {
		return student + "\tProject: " + project;
	}
}
