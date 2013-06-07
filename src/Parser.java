import exceptions.ParseException;
import statements.*;
import tokens.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 06/06/13
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
public class Parser {
	private List<Token> tokens;
	private List<Statement> statements;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}

	public List<Statement> parse()
			throws ParseException {
		statements = new ArrayList<Statement>();
		matchOptionalWhitespace();
		statements.add(matchInitializeStatement());
		matchOptionalWhitespace();
		return statements;
	}

	private InitializeStatement matchInitializeStatement()
			throws ParseException {
		Identifier type = matchIdentifier();
		matchWhitespace();
		Identifier label = matchIdentifier();
		matchOptionalWhitespace();
		matchType(EqualsToken.class);
		matchOptionalWhitespace();
		Expression expr = matchExpression();
		matchOptionalWhitespace();
		matchType(SemicolonToken.class);
		return new InitializeStatement(type, label, expr);
	}

	private Token matchType(Class clss)
			throws ParseException {
		Token token = getLookaheadToken();
		if(clss.isInstance(token)) {
			consume();
			return token;
		} else {
			String expected = clss.getCanonicalName();
			throw new ParseException(
				"Expecting " + expected + ", found " + token);
		}
	}

	private void consume()
			throws ParseException {
		try {
			tokens.remove(0);
		} catch(IndexOutOfBoundsException e) {
			throw new ParseException(
				"Reached end of token stream unexpectedly while parsing");
		}
	}

	private Token getLookaheadToken()
			throws ParseException {
		return getLookaheadToken(0);
	}

	private Token getLookaheadToken(int i)
			throws ParseException {
		try {
			return tokens.get(i);
		} catch(IndexOutOfBoundsException e) {
			throw new ParseException(
				"Reached end of token stream unexpectedly while parsing");
		}
	}

	private void matchWhitespace()
			throws ParseException {
		do {
			matchType(WhitespaceToken.class);
		} while(getLookaheadToken() instanceof WhitespaceToken);
	}

	private void matchOptionalWhitespace()
			throws ParseException {
		while(getLookaheadToken() instanceof WhitespaceToken) {
			matchType(WhitespaceToken.class);
		}
	}

	private ListLiteral matchListLiteral()
			throws ParseException {
		List<Expression> elements = new ArrayList<Expression>();
		matchType(LSqBrackToken.class);
		boolean first = true;
		do {
			if(first) {
				first = false;
			} else {
				matchOptionalWhitespace();
				matchType(CommaToken.class);
			}
			matchOptionalWhitespace();
			elements.add(matchExpression());
		} while(getLookaheadToken() instanceof CommaToken);
		matchOptionalWhitespace();
		matchType(RSqBrackToken.class);
		return new ListLiteral(elements);
	}

	private Identifier matchIdentifier()
			throws ParseException {
		Token idToken = matchType(WordToken.class);
		return new Identifier(idToken.getText());
	}

	private Int matchInt()
			throws ParseException {
		Token intToken = matchType(IntToken.class);
		return new Int(Integer.parseInt(intToken.getText()));
	}

	private Expression matchExpression()
			throws ParseException {
		Token token = getLookaheadToken();
		if(token instanceof WordToken) {
			return matchIdentifier();
		}
		if(token instanceof IntToken) {
			return matchInt();
		}
		if(token instanceof LSqBrackToken) {
			return matchListLiteral();
		}
		throw new ParseException(
			"Expecting expression, found " + token.getText());
	}
}
