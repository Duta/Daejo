import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 00:04
 * To change this template use File | Settings | File Templates.
 */
public class ListLiteral extends Expression {
	private List<Expression> elements;

	public ListLiteral(List<Expression> elements) {
		this.elements = elements;
	}

	public List<Expression> getElements() {
		return elements;
	}
}
