import exceptions.LexException;
import exceptions.ParseException;
import exceptions.RunException;
import interpreter.Interpreter;
import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import parser.Statement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 06/06/13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public class Daejo {
	private String input;

	public Daejo(String input) {
		this.input = input;
	}

	public void parseAndRun()
			throws LexException, ParseException, RunException {
		// The lexer takes a list of characters
		// and returns a list of lexer
		// The parser takes that list of lexer
		// and returns a list of parser

		// Lex the input
		Lexer lexer = new Lexer(input);
		List<Token> tokens = lexer.lex();
		// Parse the lexer
		Parser parser = new Parser(tokens);
		List<Statement> statements = parser.parse();
		// Run the parser
		Interpreter interpreter = new Interpreter();
		interpreter.runStatements(statements);
	}
}
