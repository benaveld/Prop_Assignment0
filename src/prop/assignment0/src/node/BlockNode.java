//Benjamin Avery, Email Benaveld@gmail.com
package prop.assignment0.src.node;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.Lexeme;
import prop.assignment0.ParserException;
import prop.assignment0.TokenizerException;
import prop.assignment0.src.Varibles;

import static prop.assignment0.Token.*;
import static prop.assignment0.src.Util.*;

import java.io.IOException;

public class BlockNode implements INode {
	private Lexeme leftCurly = null, rightCurly = null;
	private StatementsNode statNode;
	
	public BlockNode(ITokenizer tokenizer) throws ParserException, IOException, TokenizerException {
		if(tokenizer.current().token() != LEFT_CURLY) {
			throw new ParserException("Missing Left Curly at start of the program.");
		}
		leftCurly = tokenizer.current();
		tokenizer.moveNext();
		
		statNode = new StatementsNode(tokenizer);
		
		if(tokenizer.current().token() != RIGHT_CURLY) {
			throw new ParserException("Missing Right Curly at end of the program.");
		}
		rightCurly = tokenizer.current();
		tokenizer.moveNext();
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		return new Varibles(statNode.evaluate(args));
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		addTabs(builder, tabs);
		builder.append("BlockNode\n");
		addTabs(builder, tabs + 1);
		builder.append(leftCurly.toString() + '\n');
		statNode.buildString(builder, tabs + 1);
		addTabs(builder, tabs + 1);
		builder.append(rightCurly.toString() + '\n');
	}

}
