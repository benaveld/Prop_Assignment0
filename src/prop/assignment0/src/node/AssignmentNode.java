package prop.assignment0.src.node;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.Lexeme;
import prop.assignment0.ParserException;
import prop.assignment0.TokenizerException;

import static prop.assignment0.Token.*;
import static prop.assignment0.src.Util.*;

import java.io.IOException;

public class AssignmentNode implements INode {
	private Lexeme id, assignOp, semicolon;
	private ExpressionNode expNode;
	
	public AssignmentNode(ITokenizer tokenizer) throws ParserException, IOException, TokenizerException {
		id = tokenizer.current();
		if(id.token() != IDENT) {
			throw new ParserException("Missing IDENT");
		}
		tokenizer.moveNext();
		assignOp = tokenizer.current();
		if(assignOp.token() != ASSIGN_OP) {
			throw new ParserException("Missing ASSIGN_OP");
		}
		tokenizer.moveNext();
		expNode = new ExpressionNode(tokenizer);

		semicolon = tokenizer.current();
		if(semicolon.token() != SEMICOLON) {
			throw new ParserException("Missing SEMICOLON");
		}
		tokenizer.moveNext();
	}
	
	@Override
	public Object evaluate(Object[] args) throws Exception {
		
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		addTabs(builder, tabs);
		builder.append("AssignmentNode\n");
		addTabs(builder, tabs + 1);
		builder.append(id.toString() + '\n');
		addTabs(builder, tabs + 1);
		builder.append(assignOp.toString() + '\n');
		expNode.buildString(builder, tabs + 1);
		builder.append(semicolon.toString() + '\n');
	}

}
