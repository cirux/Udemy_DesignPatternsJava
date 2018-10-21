package structural.decorator.exercise;

class Bird {
	public int age;

	public String fly() {
		return age < 10 ? "flying" : "too old";
	}
}

class Lizard {
	public int age;

	public String crawl() {
		return (age > 1) ? "crawling" : "too young";
	}
}

/**
 * A Dragon is both a Bird and a Lizard
 * @author ciro
 *
 */
class Dragon {
	private int age;
	private Bird bird = new Bird();
	private Lizard lizard = new Lizard();
	
	public Dragon() {
		this.setAge(1);
	}

	public void setAge(int age) {
		this.age = age;
		this.bird.age = age;
		this.lizard.age = age;
	}

	public String fly() {
		return this.bird.fly();
	}

	public String crawl() {
		return this.lizard.crawl();
	}
}

public class DecoratorExercise {

}
