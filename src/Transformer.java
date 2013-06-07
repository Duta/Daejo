import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 06/06/13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public class Transformer {
	private String input;

	public Transformer(String input) {
		this.input = input;
	}

	public Queue<Statement> transform()
			throws LexException, ParseException {
		// The lexer takes a list of characters
		// and returns a list of tokens
		// The parser takes that list of tokens
		// and returns a list of statements

		// Lex the input
		Lexer lexer = new Lexer(input);
		Queue<Token> tokens = lexer.lex();
		// Parse the tokens
		Parser parser = new Parser(tokens);
		Queue<Statement> statements = parser.parse();

		return statements;
	}
}
