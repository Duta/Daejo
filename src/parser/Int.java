package parser;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 00:57
 * To change this template use File | Settings | File Templates.
 */
public class Int extends Expression {
	private int value;

	public Int(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
