//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src.node;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.Lexeme;
import prop.assignment0.ParserException;
import prop.assignment0.TokenizerException;
import prop.assignment0.src.Varible;

import static prop.assignment0.Token.*;
import static prop.assignment0.src.Util.*;

import java.io.IOException;

public class FactorNode implements INode {
	private Lexeme num = null, name = null, leftParen = null, rightParen = null;;
	private ExpressionNode expNode = null;

	public FactorNode(ITokenizer tokenizer) throws ParserException, IOException, TokenizerException {
		Lexeme token = tokenizer.current();

		if (token.token() == INT_LIT) {
			num = token;
		} else if (token.token() == IDENT) {
			name = token;
		} else if (token.token() == LEFT_PAREN) {
			leftParen = token;
			tokenizer.moveNext();
			expNode = new ExpressionNode(tokenizer);
			if (tokenizer.current().token() != RIGHT_PAREN) {
				throw new ParserException("Missing Right Paren");
			} else {
				rightParen = tokenizer.current();
			}
		} else {
			throw new ParserException("Missing Int or Left Paren");
		}
		tokenizer.moveNext();
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if (num != null) {
			return num.value();
		} else if (name != null) {
			int index = indexOf(args, name);
			if(index == -1) {
				return 0d;
			} else {
				return ((Varible) args[index]).value();
			}
		} else {
			return expNode.evaluate(args);
		}
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		addTabs(builder, tabs);
		builder.append("FactorNode\n");
		addTabs(builder, tabs + 1);
		if (num != null) {
			builder.append(num.toString() + '\n');
		} else if (name != null) {
			builder.append(name.toString() + '\n');
		} else {
			builder.append(leftParen.toString() + '\n');
			expNode.buildString(builder, tabs + 1);
			addTabs(builder, tabs + 1);
			builder.append(rightParen.toString() + '\n');
		}

	}
}
