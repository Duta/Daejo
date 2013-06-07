package statements;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */
public class Identifier extends Expression {
	private String label;

	public Identifier(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
