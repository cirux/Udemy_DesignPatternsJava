package structural.bridge.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Evaluate {
	@Test
	public void test() {
		assertEquals("Drawing Square as lines", new Square(new VectorRenderer()).toString());
	}
}
