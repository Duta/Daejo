package parser;

import exceptions.ParseException;
import lexer.*;

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
	private String[] reservedWords = {
		"var"
	};
	private List<Token> tokens;
	private List<Statement> statements;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}

	public List<Statement> parse()
			throws ParseException {
		statements = new ArrayList<Statement>();
		matchOptionalWhitespace();
		statements.add(matchStatement());
		matchOptionalWhitespace();
		return statements;
	}

	private Statement matchStatement()
			throws ParseException {
		Token token = lookahead();
		if(token instanceof WordToken) {
			WordToken wordToken = (WordToken)token;
			if("var".equals(wordToken.getText())) {
				return matchInitializeStatement();
			}
		}
		throw new ParseException("Couldn't parse statement");
	}

	private InitializeStatement matchInitializeStatement()
			throws ParseException {
		Word var = matchWord();
		if(!"var".equals(var.getWord())) {
			throw new ParseException(
				"Expecting var, found " + var.getWord());
		}
		matchWhitespace();
		Word label = matchWord();
		for(String reservedWord : reservedWords) {
			if(reservedWord.equals(label.getWord())) {
				throw new ParseException(
					"Label \"" + label.getWord() + "\" is a reserved word.");
			}
		}
		matchOptionalWhitespace();
		matchType(EqualsToken.class);
		matchOptionalWhitespace();
		Expression expr = matchExpression();
		matchOptionalWhitespace();
		matchType(SemicolonToken.class);
		return new InitializeStatement(label, expr);
	}

	private Token matchType(Class clss)
			throws ParseException {
		Token token = lookahead();
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

	private Token lookahead()
			throws ParseException {
		return lookahead(0);
	}

	private Token lookahead(int i)
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
		} while(lookahead() instanceof WhitespaceToken);
	}

	private void matchOptionalWhitespace()
			throws ParseException {
		while(lookahead() instanceof WhitespaceToken) {
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
		} while(lookahead() instanceof CommaToken);
		matchOptionalWhitespace();
		matchType(RSqBrackToken.class);
		return new ListLiteral(elements);
	}

	private Word matchWord()
			throws ParseException {
		Token idToken = matchType(WordToken.class);
		return new Word(idToken.getText());
	}

	private Int matchInt()
			throws ParseException {
		Token intToken = matchType(IntToken.class);
		return new Int(Integer.parseInt(intToken.getText()));
	}

	private Expression matchExpression()
			throws ParseException {
		Token token = lookahead();
		if(token instanceof WordToken) {
			return matchWord();
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
