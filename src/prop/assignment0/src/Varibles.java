//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src;

public class Varibles {
	private Object[] arr;
	
	public Varibles(Object object) {
		this.arr = (Object[]) object;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(Object v : arr){
			str.append(v.toString() + '\n');
		}
		return str.toString();
	}
}
