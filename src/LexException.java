/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 06/06/13
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class LexException extends Exception {
	private String input;
	private int inputPtr;
	private int line;
	private int column;

	public LexException(String input, int inputPtr) {
		this.input = input;
		this.inputPtr = inputPtr;
		calcLocation();
	}

	private void calcLocation() {
		int numNewlines = 0;
		int lastNewlineIndex = 0;
		for(int i = 0; i < inputPtr; i++) {
			char ch = input.charAt(i);
			if(ch == '\n') {
				numNewlines++;
				lastNewlineIndex = i;
			}
		}
		line = numNewlines + 1;
		column = inputPtr - lastNewlineIndex;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}
}
