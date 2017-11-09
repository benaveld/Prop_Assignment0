//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src;

import prop.assignment0.Lexeme;

public class Util {
	public static void addTabs(StringBuilder builder ,int tabs) {
		for(int i = 0; i < tabs; i++) {
			builder.append('\t');
		}
	}
	
	public static Object[] appendArray(Object[] a, Object b) {
		Object[] result = new Object[a.length + 1];
		System.arraycopy(a, 0, result, 0, a.length);
		result[a.length] = b;
		return result;
	}
	
	public static int indexOf(Object[] v, Lexeme lex) {
		for(int i = 0; i < v.length; i++) {
			if(((Varible) v[i]).name().equals(lex.value())) {
				return i;
			}
		}
		return -1;
	}
}
