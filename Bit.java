import java.util.Random;

public class Bit {
	private int[] bitcode;
	private int value;

	public Bit(int[] bitcode) {
		this.bitcode = bitcode;
		this.value = makeValue();
	}

	public int makeValue() {
		String binaryString = "";
		for (int bit : bitcode) {
			binaryString += bit + "";
		}
		int value = Integer.parseInt(binaryString, 2);
		if (value >= 70) {
			Random r = new Random();
			value = r.nextInt(70);
		}
		return value;
	}

	public Bit(int value) {
		this.value = value;
		this.bitcode = makeBitCode();
	}

	public int[] makeBitCode() {
		String code = Integer.toBinaryString(value);
		while (code.length() < 7) {
			code = "0" + code;
		}
		int[] bitcode = new int[7];
		for (int i = 0; i < 7; i++) {
			if (code.charAt(i) == '0' || code.charAt(i) == 48) {
				bitcode[i] = 0;
			} else if (code.charAt(i) == '1' || code.charAt(i) == 49) {
				bitcode[i] = 1;
			}
		}

		return bitcode;
	}

	public int getValue() {
		return value;
	}

	public int[] getBitCode() {
		return bitcode;
	}

	public void flip(int i) {
		if (bitcode[i] == 0) {
			bitcode[i] = 1;
		} else {
			bitcode[i] = 0;
		}
	}
	
	public Bit merge(Bit other, int pivot){
		int[] newBitCode = new int[7];
		Random rand = new Random();
		int x = rand.nextInt(2);
		if (x == 0) {
		for (int i = 0; i < pivot; i++){
			newBitCode[i] = bitcode[i];
		}
		for (int i = pivot; i < 7; i++){
			newBitCode[i] = other.bitcode[i];
		}
		}
		else {
			for (int i = 0; i < pivot; i++){
				newBitCode[i] = other.bitcode[i];
			}
			for (int i = pivot; i < 7; i++){
				newBitCode[i] = bitcode[i];
			}
		}
		return new Bit(newBitCode);
	}
}
