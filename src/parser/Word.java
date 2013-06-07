package parser;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 07/06/13
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */
public class Word extends Expression {
	private String word;

	public Word(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}
}
