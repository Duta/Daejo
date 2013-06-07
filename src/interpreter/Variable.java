package interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class Variable {
	private String label;
	private Value value;

	public Variable(String label) {
		this(label, null);
	}

	public Variable(String label, Value value) {
		this.label = label;
		this.value = value;
	}
	public String getLabel() {
		return label;
	}

	public Value getValue() {
		return value;
	}
}
