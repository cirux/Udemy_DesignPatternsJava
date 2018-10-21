package structural.decorator.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Evaluate {

	@Test
	public void test() {
		Dragon dragon = new Dragon();

		assertEquals("flying", dragon.fly());
		assertEquals("too young", dragon.crawl());

		dragon.setAge(20);

		assertEquals("too old", dragon.fly());
		assertEquals("crawling", dragon.crawl());
	}

}
