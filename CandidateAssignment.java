import java.util.Random;

/* * Patricia Loftus
 * 13381946
 * Assignment 5
 */
public class CandidateAssignment {
	private String project = null;
	private String prev_project = null;
	private StudentEntry student;
	private Bit bitcode;

	public CandidateAssignment(StudentEntry student) {
		this.student = student;
		randomizeAssignment();
		bitcode = setBits();
	}
	
	public Bit setBits() {
		//int name = Integer.parseInt(String.valueOf(student.getStudentName().hashCode()));
		int name = new Random().nextInt(2147483647);
		name = Math.abs(name);
		String code = Integer.toBinaryString(name);
		int index = 0;
		int[] bits = new int[31];
		for (int j = 0; j < 31 ; j++) {
			if (code.length() > j) {
				if (code.charAt(j) == '0') {
					bits[index] = 0;
				}
				else if (code.charAt(j) == '1') {
					bits[index] = 1;
				}
			}
			else {
				bits[index] = 0;
			}
			index++;
		}
		return new Bit(bits, name);
	}
	
	public Bit getBits(){
		return bitcode;
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