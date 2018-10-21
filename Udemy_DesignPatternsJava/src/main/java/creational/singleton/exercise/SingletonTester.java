package creational.singleton.exercise;

import java.util.function.Supplier;

public class SingletonTester {

	/**
	 * This methos takes a factory method that returns an object and it's up to you
	 * to determine whether or not that object is a singleton instance
	 * 
	 * 
	 * uses Java Lambda - Supplier<br>
	 * A lambda expression is an unnamed function with parameters and a body. <br>
	 * The lambda expression body can be a block statement or an expression. <br>
	 * -> separates the parameters and the body. <br>
	 * (int x) -> x + 1 takes an int parameter and returns the parameter value
	 * incremented by 1.<br>
	 * Why Lambda Expressions? The lambda expressions allows us to pass logic in a
	 * compact way.
	 * 
	 * @param func
	 * @return
	 */
	public static boolean isSingleton(Supplier<Object> func) {
		
		return func.get() == func.get();
	}

}
