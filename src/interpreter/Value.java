package interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public abstract class Value {
	public abstract DaejoClass getType();
	public abstract Object getValue();
}
