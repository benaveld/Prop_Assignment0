//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src;

import static prop.assignment0.Token.*;

import java.io.IOException;

import prop.assignment0.*;

public class Tokenizer implements ITokenizer {
	private IScanner scanner = new Scanner();
	private Lexeme currentValue;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner.open(fileName);
	}

	@Override
	public Lexeme current() {
		return currentValue;
	}

	@Override
	public void moveNext() throws IOException, TokenizerException {
		char c = scanner.current();

		// Skips whitespace and new line
		while (isSkipt(c)) {
			scanner.moveNext();
			c = scanner.current();
		}

		if (c >= 'a' && c <= 'z') {
			currentValue = new Lexeme(getWord('a', 'z'), IDENT);
			return;
		} else if (c >= '0' && c <= '9') {
			currentValue = new Lexeme(Double.parseDouble(getWord('0', '9')), INT_LIT);
			return;
		}
		
		Token token = null;
		switch (c) {
		case Scanner.EOF:
			token = EOF;
			break;

		case '=':
			token = ASSIGN_OP;
			break;

		case '+':
			token = ADD_OP;
			break;

		case '-':
			token = SUB_OP;
			break;

		case '*':
			token = MULT_OP;
			break;

		case '/':
			token = DIV_OP;
			break;

		case ';':
			token = SEMICOLON;
			break;

		case '(':
			token = LEFT_PAREN;
			break;

		case ')':
			token = RIGHT_PAREN;
			break;

		case '{':
			token = LEFT_CURLY;
			break;

		case '}':
			token = RIGHT_CURLY;
			break;

		default:
			throw new TokenizerException("Char: \"" + c + "\" is not in the Tokenizer.");
		}

		currentValue = new Lexeme(c, token);
		scanner.moveNext();
	}

	private boolean isSkipt(char c) {
		return c == ' ' || c == 0x0A || c == 0x0D || c == 0x0;
	}

	private String getWord(char start, char end) throws IOException {
		StringBuilder str = new StringBuilder(1);
		char c = scanner.current();

		while (!isSkipt(c) && c >= start && c <= end) {
			str.append(c);
			scanner.moveNext();
			c = scanner.current();
		}

		return str.toString();
	}

	@Override
	public void close() throws IOException {
		scanner.close();
	}

}
