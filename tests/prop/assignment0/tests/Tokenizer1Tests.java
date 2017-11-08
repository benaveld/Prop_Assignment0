package prop.assignment0.tests;

import org.junit.*;

import prop.assignment0.ITokenizer;
import prop.assignment0.TokenizerException;
import prop.assignment0.src.Tokenizer;

import static prop.assignment0.Token.*;
import static org.junit.Assert.*;

import java.io.IOException;

public class Tokenizer1Tests {
	@Test
	public void testMoveNext() throws IOException, TokenizerException {
		ITokenizer t = new Tokenizer();
		t.open("lib/program1.txt");
		try {
			do {
				t.moveNext();
				assertNotNull("moveNext() did not put a value into current()", t.current());
			} while (t.current().token() != EOF);
		} finally {
			t.close();
		}
	}
	
}
