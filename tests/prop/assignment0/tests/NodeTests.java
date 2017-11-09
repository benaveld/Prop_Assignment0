//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import prop.assignment0.INode;
import prop.assignment0.ITokenizer;
import prop.assignment0.src.Tokenizer;
import prop.assignment0.src.Util;
import prop.assignment0.src.node.BlockNode;

public class NodeTests {
	@Test
	public void testNode() throws Exception {
		ITokenizer t = new Tokenizer();
		t.open("lib/program2.txt");
		try {
			t.moveNext();
			INode root = new BlockNode(t);
			
			StringBuilder str = new StringBuilder();
			root.buildString(str, 0);
			//System.out.println(str.toString());
			
			System.out.println(root.evaluate(null));
		} finally {
			t.close();
		}
		
	}
	
	@Test
	public void testAppendArray() {
		Object[] a = {'a', 'b'};
		assertEquals(3, Util.appendArray(a, 'c').length);
	}
}
