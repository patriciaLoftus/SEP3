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

	public Bit getBits() {
		return projectCode;
	}
	
	public boolean giveNextPref(Vector<String> projects){
		Vector<String> projectsOccured = new Vector<String>();
		for (String pro: student.getOrderedPreferences()){
			boolean flag = true;
			for (String other:projects){
				if (other.equals(pro)){
					flag = false;
				}
			}
			if (flag){
				this.setProject(pro);
				return true;
			}
		}
		return false;

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


	public void giveRandom(Vector<String> pro, Vector<String> projects) {
		Random rand = new Random();
		int i = rand.nextInt(projects.size());
		String project = projects.get(i);
		while(pro.contains(project.intern())){
			i = rand.nextInt(projects.size());
			project = projects.get(i);
		}
		this.project = project;
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