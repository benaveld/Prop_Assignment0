//Benjamin Avery, Email: Benaveld@gmail.com
package prop.assignment0.src;

public class Varible {
	private String name;
	private double value;
	
	public Varible(String name, double value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return name + " = " + value;
	}
	
	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}
}