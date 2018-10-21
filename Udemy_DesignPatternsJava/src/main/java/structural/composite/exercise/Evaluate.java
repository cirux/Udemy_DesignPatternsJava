package structural.composite.exercise;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class Evaluate {

	@Test
	public void test() {
		SingleValue singleValue = new SingleValue(11);
		ManyValues otherValues = new ManyValues();
		otherValues.add(22);
		otherValues.add(33);
		assertEquals(66, new MyList(List.of(singleValue, otherValues)).sum());
	}

}
