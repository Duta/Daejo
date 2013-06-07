package lexer;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 06/06/13
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class Token {
	private String text;

	public Token(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		String className = getClass().getCanonicalName();
		return className + "{" + text + "}";
	}
}
