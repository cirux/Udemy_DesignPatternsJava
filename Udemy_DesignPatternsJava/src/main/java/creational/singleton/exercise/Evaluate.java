package creational.singleton.exercise;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Evaluate {
	
	@Test
	  public void test()
	  {
	    Object obj = new Object();
	    assertTrue(SingletonTester.isSingleton(() -> obj));
	    
	    // the :: syntax was introduced in Java 8 to reference methods.
	    // Note that this will be interpreted by the compiler, not by the JVM at runtime.
	    // :: is called Method Reference. It is basically a reference to a single method. i.e. it refers to an existing method by name.
	    // Method references are expressions which have the same treatment as lambda expressions (...), but instead of providing a method body, they refer an existing method by name.
	    
	    assertFalse(SingletonTester.isSingleton(Object::new));
	  }

}
