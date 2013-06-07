package parser;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 00:45
 * To change this template use File | Settings | File Templates.
 */
public class InitializeStatement extends Statement {
	private Word label;
	private Expression expr;

	public InitializeStatement(Word label, Expression expr) {
		this.label = label;
		this.expr = expr;
	}

	public Word getLabel() {
		return label;
	}

	public Expression getExpr() {
		return expr;
	}

	public String toString() {
		return super.toString() + "{" + label.getWord() + "}";
	}
}
