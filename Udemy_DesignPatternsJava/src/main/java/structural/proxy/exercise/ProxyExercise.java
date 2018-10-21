package structural.proxy.exercise;

class Person {
	private int age;

	public Person(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String drink() {
		return "drinking";
	}

	public String drive() {
		return "driving";
	}

	public String drinkAndDrive() {
		return "driving while drunk";
	}
}

class ResponsiblePerson {
	private Person person;

	public ResponsiblePerson(Person person) {
		this.person = person;
	}

	public String drive() {
		return (person.getAge() < 16) ? "too young" : person.drive();
	}

	public String drink() {
		return (person.getAge() < 18) ? "too young" : person.drink();
	}

	public String drinkAndDrive() {
		return "dead";
	}

	public void setAge(int age) {
		person.setAge(age);
	}
}
