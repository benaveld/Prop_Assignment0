//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src.node;

import static prop.assignment0.Token.DIV_OP;
import static prop.assignment0.Token.MULT_OP;
import static prop.assignment0.src.Util.addTabs;

import java.io.IOException;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.Lexeme;
import prop.assignment0.ParserException;
import prop.assignment0.TokenizerException;

public class TermNode implements INode {
	private INode facNode = null;
	private Lexeme op = null;
	private TermNode termNode = null;
	
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
		return evaluate(args, (double) facNode.evaluate(args));
	}
	
	private Object evaluate(Object[] args, double value) throws Exception {
		if(op == null) {
			return value;
		} else {
			if(op.token() == MULT_OP) {
				return termNode.evaluate(args, value * (double) termNode.facNode.evaluate(args));
			} else if(op.token() == DIV_OP) {
				return termNode.evaluate(args, value / (double) termNode.facNode.evaluate(args));
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
