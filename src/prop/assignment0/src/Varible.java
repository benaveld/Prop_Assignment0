//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src;

public class Varible {
	private String name;
	private double value;
	
	public Varible(String name, double value) {
		this.name = name;
		this.value = Math.round(value*100d) / 100d;
	}

	@Override
	public String toString() {
		return name + " = " + value;
		
	}
	
	public String name() {
		return name;
	}

	public double value() {
		return value;
	}
}
