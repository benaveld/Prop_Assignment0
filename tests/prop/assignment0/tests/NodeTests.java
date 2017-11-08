package prop.assignment0.tests;

import org.junit.Test;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.src.Tokenizer;
import prop.assignment0.src.node.TermNode;

public class NodeTests {
	@Test
	public void testNode() throws Exception {
		ITokenizer t = new Tokenizer();
		t.open("tests/EvelTestData.txt");
		try {
			t.moveNext();
			INode root = new TermNode(t);
			
			StringBuilder str = new StringBuilder();
			root.buildString(str, 0);
			System.out.println(str.toString());
			
			System.out.println(root.evaluate(null));
		} finally {
			t.close();
		}
		
	}
}
