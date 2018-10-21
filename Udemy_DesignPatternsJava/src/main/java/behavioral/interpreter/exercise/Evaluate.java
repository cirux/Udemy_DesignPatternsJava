package behavioral.interpreter.exercise;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Evaluate {
	
	ExpressionProcessor ep;
	
	@Before
	public void init() {
		ep = new ExpressionProcessor();
		ep.variables.put(Character.valueOf('x'), java.lang.Integer.valueOf(5));
	}

	@Test
	public void testSingleValue() {
		
		assertEquals(1, ep.calculate("1"));
	}
	
	@Test
	public void testAddition() {
		
		assertEquals(3, ep.calculate("1+2"));
	}
	
	@Test
	public void testVariableAddition() {
		assertEquals(6, ep.calculate("1+x"));
	}
	
	@Test
	public void testInvalidVariable() {
		assertEquals(0, ep.calculate("1+xy"));
	}

}
