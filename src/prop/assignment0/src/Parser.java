//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src;

import java.io.IOException;

import prop.assignment0.INode;
import prop.assignment0.IParser;
import prop.assignment0.ITokenizer;
import prop.assignment0.ParserException;
import prop.assignment0.Token;
import prop.assignment0.TokenizerException;
import prop.assignment0.src.node.BlockNode;

public class Parser implements IParser {
	private ITokenizer tokenizer = new Tokenizer();
	
	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		tokenizer.open(fileName);
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		tokenizer.moveNext();
		INode result = new BlockNode(tokenizer);
		if(tokenizer.current().token() != Token.EOF) {
			throw new ParserException("End of file not reathed.");
		}
		return result;
	}

	@Override
	public void close() throws IOException {
		tokenizer.close();
	}

}
