
public class Bit {
	private int[] bitcode;
	private int value;
	public Bit(int [] bitcode, int value) {
		this.bitcode = bitcode;
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public int[] getBitCode(){
		return bitcode;
	}
	
	public void flip(){
		//flip bits
	}
}
