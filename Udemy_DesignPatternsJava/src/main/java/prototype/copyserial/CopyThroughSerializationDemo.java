package prototype.copyserial;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

//some libraries use reflection (no need for Serializable)
class Foo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7466022205451241379L;
	
	public int stuff;
	public String whatever;

	public Foo(int stuff, String whatever) {
		this.stuff = stuff;
		this.whatever = whatever;
	}

	@Override
	public String toString() {
		return "Foo{" + "stuff=" + stuff + ", whatever='" + whatever + '\'' + '}';
	}
}

public class CopyThroughSerializationDemo {
	public static void main(String[] args) {
		Foo foo = new Foo(42, "life");
		// use apache commons!
		Foo foo2 = SerializationUtils.roundtrip(foo);

		foo2.whatever = "xyz";

		System.out.println(foo);
		System.out.println(foo2);
	}
}
