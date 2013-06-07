package lexer;

import exceptions.LexException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Bertie
 * Date: 06/06/13
 * Time: 18:26
 * To change this template use File | Settings | File Templates.
 */
public class Lexer {
	private static final char EOF_CHAR = (char)-1;
	private String input;
	private int inputPtr;
	private char currentChar;

	public Lexer(String input) {
		this.input = input;
		reset();
	}

	public List<Token> lex()
			throws LexException {
		List<Token> tokens = new ArrayList<Token>();
		Token token = null;
		do {
			token = getNext();
			tokens.add(token);
		} while(!(token instanceof EOFToken));
		reset();
		return tokens;
	}

	private void reset() {
		inputPtr = 0;
		updateCurrentChar();
	}

	private Token getNext()
			throws LexException {
		// Match whitespace
		if(Character.isWhitespace(currentChar)) {
			consumeWhitespace();
			return new WhitespaceToken();
		}
		// Match identifier
		if(Character.isAlphabetic(currentChar)) {
			return readIdentifier();
		}
		// Match number
		if(Character.isDigit(currentChar)) {
			return readNumber();
		}
		// Match EOF
		if(currentChar == EOF_CHAR) {
			return new EOFToken();
		}
		// Match symbols
		Map<Character, Token> charMap = new HashMap<Character, Token>();
		charMap.put(',', new CommaToken());
		charMap.put('[', new LSqBrackToken());
		charMap.put(']', new RSqBrackToken());
		charMap.put('=', new EqualsToken());
		charMap.put('>', new GreaterThanToken());
		charMap.put('<', new LessThanToken());
		charMap.put(';', new SemicolonToken());
		charMap.put('+', new PlusToken());
		for(char ch : charMap.keySet()) {
			if(matchCurrent(ch)) {
				return charMap.get(ch);
			}
		}
		// Don't understand the input
		throw new LexException(input, inputPtr);
	}

	private Token readNumber() {
		return readInt();
	}

	private Token readInt() {
		StringBuilder sb = new StringBuilder();
		while(Character.isDigit(currentChar)) {
			sb.append(currentChar);
			increment();
		}
		return new IntToken(sb.toString());
	}

	private void consumeWhitespace() {
		while(Character.isWhitespace(currentChar)) {
			increment();
		}
	}

	private Token readIdentifier() {
		StringBuilder sb = new StringBuilder();
		while(Character.isAlphabetic(currentChar)) {
			sb.append(currentChar);
			increment();
		}
		return new WordToken(sb.toString());
	}

	private boolean matchCurrent(char toMatch) {
		boolean isCurrent = toMatch == currentChar;
		if(isCurrent) {
			increment();
		}
		return isCurrent;
	}

	private void increment() {
		inputPtr++;
		updateCurrentChar();
	}

	private void updateCurrentChar() {
		currentChar = inputPtr < input.length()
			? input.charAt(inputPtr)
			: EOF_CHAR;
	}
}
