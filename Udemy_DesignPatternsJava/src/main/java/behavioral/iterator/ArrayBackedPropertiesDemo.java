package behavioral.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Different fields
 * 
 * @author ciro
 *
 */
class SimpleCreature {

	// each property has a different field
	private int strength, agility, intelligence;

	// if you add other fields, you have to udate this
	public int max() {
		return Math.max(strength, Math.max(agility, intelligence));
	}

	// if you add other fields, you have to udate this
	public int sum() {
		return strength + agility + intelligence;
	}

	// if you add other fields, you have to udate this
	public double average() {
		return sum() / 3.0;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;

	}
}

/**
 * Has array-backed properties.
 * 
 * @see {@link IntStream}
 * @see {@link Consumer}
 * @author ciro
 *
 */
class Creature implements Iterable<Integer> {

	// all the properties in the same array
	private int[] stats = new int[3];

	private final int str = 0;

	public int getStrength() {
		return stats[str];
	}

	public void setStrength(int value) {
		stats[str] = value;
	}

	public int getAgility() {
		return stats[1];
	}

	public void setAgility(int value) {
		stats[1] = value;
	}

	public int getIntelligence() {
		return stats[2];
	}

	public void setIntelligence(int value) {
		stats[2] = value;
	}

	// statistics implementation is more maintainable

	public int sum() {
		return IntStream.of(stats).sum();
	}

	public int max() {
		return IntStream.of(stats).max().getAsInt();
	}

	public double average() {
		return IntStream.of(stats).average().getAsDouble();
	}

	@Override
	public void forEach(Consumer<? super Integer> action) {
		for (int x : stats)
			action.accept(x);
	}

	@Override
	public Spliterator<Integer> spliterator() {
		return Arrays.spliterator(stats);
	}

	@Override
	public Iterator<Integer> iterator() {
		return IntStream.of(stats).iterator();
	}

}

public class ArrayBackedPropertiesDemo {

	public static void main(String[] args) {

		Creature creature = new Creature();
		creature.setAgility(12);
		creature.setIntelligence(13);
		creature.setStrength(17);

		System.out.println("Creature has a max stat of " + creature.max() + ", total stats of " + creature.sum()
				+ " and average stat of " + creature.average());

	}

}
