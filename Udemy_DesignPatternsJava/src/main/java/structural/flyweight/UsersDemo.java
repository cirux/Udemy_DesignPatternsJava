package structural.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * without flyweight it wastes memory for repeated names and surnames
 */
class User {
	private String fullName;

	/**
	 * The API expects one string with name and surname separated by " "
	 * @param fullName
	 */
	public User(String fullName) {
		this.fullName = fullName;
	}
}

/**
 * flyweight
 *
 */
class User2 {
	// shared cache among all instances
	static List<String> strings = new ArrayList<>();
	// each object has its own indexes
	private int[] names;

	/**
	 * keeps the API with single string
	 * @param fullName
	 */
	public User2(String fullName) {
		// returns the index of the name in the cache or adds the new name
		Function<String, Integer> getOrAdd = (String s) -> {
			int idx = strings.indexOf(s);
			if (idx != -1)
				return idx;
			else {
				strings.add(s);
				return strings.size() - 1;
			}
		};

		names = Arrays.stream(fullName.split(" ")).mapToInt(s -> getOrAdd.apply(s)).toArray();
	}

	public String getFullName() {
		return Arrays.stream(names).mapToObj(i -> strings.get(i)).collect(Collectors.joining(","));
	}
}

public class UsersDemo {

	public static void main(String[] args) {
		User2 user = new User2("John Smith");
		User2 user1 = new User2("Jane Smith");
		
		System.out.println(user.getFullName());
		System.out.println(user1.getFullName());
		// have "Smith" in common
	}
}
