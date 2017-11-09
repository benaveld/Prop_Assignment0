//Benjamin Avery, Email Benaveld@gmail.com
package prop.assignment0.src.node;

import java.io.IOException;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.ParserException;
import prop.assignment0.Token;
import prop.assignment0.TokenizerException;
import prop.assignment0.src.Varible;

import static prop.assignment0.src.Util.*;

public class StatementsNode implements INode {
	private INode assNode = null;
	private INode statNode = null;

	public StatementsNode(ITokenizer tokenizer) throws IOException, TokenizerException, ParserException {
		if (tokenizer.current().token() != Token.RIGHT_CURLY) {
			assNode = new AssignmentNode(tokenizer);
			statNode = new StatementsNode(tokenizer);
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if (assNode != null) {
			Varible v = (Varible) assNode.evaluate(args);
			Object[] newArgs;
			if (args == null) {
				newArgs = new Object[] { v };
			} else {
				newArgs = appendArray(args, v);
			}
			return statNode.evaluate(newArgs);
		} else {
			return args;
		}
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		addTabs(builder, tabs);
		builder.append("StatementsNode\n");
		if (assNode != null) {
			assNode.buildString(builder, tabs + 1);
			statNode.buildString(builder, tabs + 1);
		}
	}

}
