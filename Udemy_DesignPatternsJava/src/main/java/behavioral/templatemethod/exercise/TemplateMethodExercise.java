package behavioral.templatemethod.exercise;

class Creature {
	public int attack, health;

	public Creature(int attack, int health) {
		this.attack = attack;
		this.health = health;
	}
}

/**
 * This abstract class is the template method: the algoritm is defined at high
 * level
 *
 */
abstract class CardGame {
	public Creature[] creatures;

	public CardGame(Creature[] creatures) {
		this.creatures = creatures;
	}

	// return s-1 if no clear winner (both alive or both dead)
	public int combat(int creature1, int creature2) {
		Creature first = creatures[creature1];
		Creature second = creatures[creature2];
		hit(first, second);
		hit(second, first);
		// determine who won and return either creature1 or creature2
		boolean creature1Alive = first.health > 0;
		boolean creature2Alive = second.health > 0;
		if (creature1Alive == creature2Alive)
			return -1; // empasse
		return creature1Alive ? creature1 : creature2;
	}

	// attacker hits other creature
	protected abstract void hit(Creature attacker, Creature other);
}

/**
 * Unless the Creature has been killed, its health returns to the original value
 * at the end of combat
 *
 */
class TemporaryCardDamageGame extends CardGame {

	public TemporaryCardDamageGame(Creature[] creatures) {
		super(creatures);
	}

	@Override
	protected void hit(Creature attacker, Creature other) {
		int oldHotherHealth = other.health;
		other.health -= attacker.attack;
		if (other.health > 0)
			other.health = oldHotherHealth; // restore

	}
}

/**
 * health damage persists
 *
 */
class PermanentCardDamageGame extends CardGame {

	public PermanentCardDamageGame(Creature[] creatures) {
		super(creatures);
	}

	@Override
	protected void hit(Creature attacker, Creature other) {
		other.health -= attacker.attack;
	}
}

public class TemplateMethodExercise {

}
