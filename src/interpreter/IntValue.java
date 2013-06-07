package interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class IntValue extends Value {
	private int value;

	public IntValue(int value) {
		this.value = value;
	}

	public DaejoClass getType() {
		return DaejoClass.INT;
	}

	public Integer getValue() {
		return value;
	}
}
