package prop.assignment0.src.node;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.Lexeme;
import prop.assignment0.ParserException;
import prop.assignment0.TokenizerException;

import static prop.assignment0.Token.*;
import static prop.assignment0.src.Util.*;

import java.io.IOException;

public class TermNode implements INode {
	private INode facNode = null;
	private Lexeme op = null;
	private INode termNode = null;
	
	public TermNode(ITokenizer tokenizer) throws ParserException, IOException, TokenizerException {
		facNode = new FactorNode(tokenizer);
		
		Lexeme token = tokenizer.current();
		if(token.token() == MULT_OP || token.token() == DIV_OP) {
			op = token;
			tokenizer.moveNext();
			termNode = new TermNode(tokenizer);
		}
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		double a = (double) facNode.evaluate(args);
		if(op == null) {
			return a;
		} else {
			if(op.token() == MULT_OP) {
				return a * (double) termNode.evaluate(args);
			} else if(op.token() == DIV_OP) {
				return a / (double) termNode.evaluate(args);
			} else {
				throw new Exception();
			}
		}
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		addTabs(builder, tabs);
		builder.append("TermNode\n");
		facNode.buildString(builder, tabs + 1);
		if(op != null) {
			addTabs(builder, tabs + 1);
			builder.append(op.toString() + '\n');
			termNode.buildString(builder, tabs + 1);
		}
	}

}
