//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src.node;

import java.io.IOException;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.Lexeme;
import prop.assignment0.ParserException;
import prop.assignment0.TokenizerException;

import static prop.assignment0.Token.*;
import static prop.assignment0.src.Util.*;

public class ExpressionNode implements INode {
	private TermNode termNode = null;
	private Lexeme op = null;
	private ExpressionNode expNode = null;
	
	public ExpressionNode(ITokenizer tokenizer) throws ParserException, IOException, TokenizerException {
		termNode = new TermNode(tokenizer);
		Lexeme token = tokenizer.current();
		if(token.token() == ADD_OP || token.token() == SUB_OP) {
			op = token;
			tokenizer.moveNext();
			expNode = new ExpressionNode(tokenizer);
		}
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		return evaluate(args, (double) termNode.evaluate(args));
	}

	private Object evaluate(Object[] args, double value) throws Exception {
		if(op == null) {
			return value;
		} else {
			if(op.token() == ADD_OP) {
				return expNode.evaluate(args, value + (double) expNode.termNode.evaluate(args));
			} else {
				//System.out.println(value - (double) expNode.termNode.evaluate(args));
				return expNode.evaluate(args, value - (double) expNode.termNode.evaluate(args));
			}
		}
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		addTabs(builder, tabs);
		builder.append("ExpressionNode\n");
		termNode.buildString(builder, tabs + 1);
		if(op != null) {
			addTabs(builder, tabs + 1);
			builder.append(op.toString() + '\n');
			expNode.buildString(builder, tabs + 1);
		}
	}

}
