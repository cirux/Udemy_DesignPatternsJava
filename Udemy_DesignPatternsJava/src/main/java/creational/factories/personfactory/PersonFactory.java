package creational.factories.personfactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Section 4 - Exercise: implement a non-static PersonFactory.
 * The id of the person returned should be set as a 0-based index of the object
 * created by that factory.
 * @author ciro
 *
 */

public class PersonFactory {

	List<Person> persons = new ArrayList<Person>();
	
	public Person createPerson(String name) {

		Person person = new Person(persons.size(), name);
		persons.add(person);
		return person;
	}

}
