/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 00:45
 * To change this template use File | Settings | File Templates.
 */
public class InitializeStatement extends Statement {
	private Identifier type;
	private Identifier label;
	private Expression expr;

	public InitializeStatement(Identifier type, Identifier label, Expression expr) {
		this.type = type;
		this.label = label;
		this.expr = expr;
	}

	public Identifier getType() {
		return type;
	}

	public Identifier getLabel() {
		return label;
	}

	public Expression getExpr() {
		return expr;
	}
}
