package interpreter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class ListValue extends Value {
	private List<Value> values;

	public ListValue(List<Value> values) {
		this.values = values;
	}

	public DaejoClass getType() {
		return DaejoClass.LIST;
	}

	public List<Value> getValue() {
		return values;
	}
}
