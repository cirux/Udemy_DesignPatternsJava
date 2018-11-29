package behavioral.templatemethod.exercise;

class Creature {
	public int attack, health;

	public Creature(int attack, int health) {
		this.attack = attack;
		this.health = health;
	}
}

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
		// todo: determine who won and return either creature1 or creature2
		return creature2;
	}

	// attacker hits other creature
	protected abstract void hit(Creature attacker, Creature other);
}

class TemporaryCardDamageGame extends CardGame {

	public TemporaryCardDamageGame(Creature[] creatures) {
		super(creatures);
		// TODO Auto-generated constructor stub
	}
	// todo

	@Override
	protected void hit(Creature attacker, Creature other) {
		// TODO Auto-generated method stub
		
	}
}

class PermanentCardDamageGame extends CardGame {

	public PermanentCardDamageGame(Creature[] creatures) {
		super(creatures);
		// TODO Auto-generated constructor stub
	}
	// todo

	@Override
	protected void hit(Creature attacker, Creature other) {
		// TODO Auto-generated method stub
		
	}
}

public class TemplateMethodExercise {

}
