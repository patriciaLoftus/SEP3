import java.io.IOException;
import java.util.Vector;

/*
 * Patricia Loftus
 * 13381946
 * Assignment 5
 */

public class Main {
	public static void main(String args[]) throws IOException {
		Population p = new Population(100, "src/ProjectAllocationData.tsv");
		p.GA();
	}
}