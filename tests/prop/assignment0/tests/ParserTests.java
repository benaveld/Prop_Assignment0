package prop.assignment0.tests;

import java.io.IOException;

import org.junit.*;

import prop.assignment0.IParser;
import prop.assignment0.ParserException;
import prop.assignment0.TokenizerException;
import prop.assignment0.src.Parser;

public class ParserTests {
	@Test
	public void testParser() throws IOException, TokenizerException, ParserException {
		IParser p = new Parser();
		p.open("lib/program1.txt");
		p.parse();
		p.close();
	}
}
