package behavioral.chainofresponsibility.broker;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import behavioral.chainofresponsibility.broker.Query.Argument;

// cor + observer + mediator + memento = EVENT BROKER

// command - query separation
// I'm going to have an ability to subscribe, unsubscribe, fire an event
class Event<Args> {
	private int index = 0;
	private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

	public int subscribe(Consumer<Args> handler) {
		int i = index;
		handlers.put(index++, handler);
		return i;
	}

	public void unsubscribe(int key) {
		handlers.remove(key);
	}

	/**
	 * fire the event on every single consumer
	 * 
	 * @param args
	 */
	public void fire(Args args) {

		for (Consumer<Args> handler : handlers.values()) {
			handler.accept(args);
		}

	}
}

class Query {
	public String creatureName;

	enum Argument {
		ATTACK, DEFENSE
	};

	public Argument argument;
	public int result;

	public Query(String creatureName, Argument argument, int result) {
		super();
		this.creatureName = creatureName;
		this.argument = argument;
		this.result = result;
	}

}

/**
 * The mediator
 * 
 * @author ciro
 *
 */
class Game {
	public Event<Query> queries = new Event<>();
}

class Creature {
	private Game game;
	public String name;
	public int baseAttack, baseDefense;

	public Creature(Game game, String name, int baseAttack, int baseDefense) {
		super();
		this.game = game;
		this.name = name;
		this.baseAttack = baseAttack;
		this.baseDefense = baseDefense;
	}

	int getAttack() {
		Query q = new Query(name, Argument.ATTACK, baseAttack);
		game.queries.fire(q);
		return q.result;
	}

	int getDefense() {
		Query q = new Query(name, Argument.DEFENSE, baseDefense);
		game.queries.fire(q);
		return q.result;
	}

	@Override
	public String toString() {
		return "Creature [name=" + name + ", attack=" + getAttack() + ", defense=" + getDefense() + "]";
	}

}

class CreatureModifier
{
	protected Game game;
	protected Creature creature;
	
	public CreatureModifier(Game game, Creature creature) {
		super();
		this.game = game;
		this.creature = creature;
	}
	
}

class DoubleAttackModifier extends CreatureModifier implements AutoCloseable
{
	private final int token;

	public DoubleAttackModifier(Game game, Creature creature) {
		super(game, creature);
		
		token = game.queries.subscribe(q -> {
			// the handler is a lambda
			if(q.creatureName.equals(creature.name) && q.argument == Argument.ATTACK) {
				q.result *= 2;
			}
		});
	}

	@Override
	public void close()  {
		game.queries.unsubscribe(token);
	}
	
}

class IncreaseDefenseModifier extends CreatureModifier implements AutoCloseable
{
	private final int token;

	public IncreaseDefenseModifier(Game game, Creature creature) {
		super(game, creature);
		
		token = game.queries.subscribe(q -> {
			// the handler is a lambda
			if(q.creatureName.equals(creature.name) && q.argument == Argument.DEFENSE) {
				q.result += 3;
			}
		});
	}

	@Override
	public void close() throws Exception {
		game.queries.unsubscribe(token);
		
	}
	
}

public class BrokerChain {

	public static void main(String[] args) {
		Game game = new Game();
		Creature goblin = new Creature(game, "Strong goblin", 2, 2);
		
		System.out.println(goblin);
		
		IncreaseDefenseModifier idm = new IncreaseDefenseModifier(game, goblin);
		
		DoubleAttackModifier dam = new DoubleAttackModifier(game, goblin);
		
		// try with resources statement 
		try(dam){
			System.out.println(goblin);
		}
		// outside try with resources
		System.out.println(goblin);
		
	}

}
