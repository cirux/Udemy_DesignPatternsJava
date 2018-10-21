package structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
	void walk();

	void talk();
}

class Person implements Human {
	@Override
	public void walk() {
		System.out.println("I am walking");
	}

	@Override
	public void talk() {
		System.out.println("I am talking");
	}
}

class LoggingHandler implements InvocationHandler{
	
	private final Object target;
	//<methodName, calls>
	private Map<String, Integer> calls = new HashMap<>();
	
	public LoggingHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		// proxy's additional operations
		
		String name = method.getName();
		
		// output the number of calls made to this method on toString call
		if(name.contains("toString")) {
			return calls.toString();
		}
		
		calls.merge(name, 1, Integer::sum);
		
		// actually invokes target method
		return method.invoke(target, args);
	}
	
}

public class DynamicLoggingProxyDemo {

	@SuppressWarnings("unchecked")
	public static <T> T withLogging(T target, Class<T> itf) {

		// we use Java API
		return (T) Proxy.newProxyInstance(
				itf.getClassLoader(), 
				new Class<?>[] { itf }, 
				new LoggingHandler(target));
	}

	public static void main(String[] args) {
		
		Person person = new Person();
		Human logged = withLogging(person, Human.class);
		logged.talk();
		logged.walk();
		logged.talk();
		System.out.println(logged);
	}

}
