package behavioral.observer.exercise;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

// In this game one or more rats can attack a player. Each rat has an attack value of 1.
// However, rats attack as a swarm, so each rat's attack value is equal to the total number of 
// rats in play.

class Event<T> {
	private List<BiConsumer<Object, T>> consumers = new ArrayList<>();

	/**
	 * {@code BiConsumer} Represents an operation that accepts two input arguments
	 * and returns no result. This is the two-arity specialization of Consumer.
	 * Unlike most other functional interfaces, BiConsumer is expected to operate
	 * via side-effects.
	 * 
	 * @param consumer
	 */
	public void subscribe(BiConsumer<Object, T> consumer) {
		consumers.add(consumer);
	}

	public void invoke(Object sender, T arg) {
		for (BiConsumer<Object, T> consumer : consumers)
			consumer.accept(sender, arg);
	}
}

class Game {
	public Event<Void> ratEnters = new Event<>();
	public Event<Void> ratDies = new Event<>();
	public Event<Rat> notifyRat = new Event<>();
}

class Rat implements Closeable {
	private Game game;
	public int attack = 1;

	public Rat(Game game) {
		// rat enters game here
		this.game = game;
		game.ratEnters.subscribe((sender, arg) -> {
			if (sender != this) {
				++attack;
				game.notifyRat.invoke(this, (Rat) sender);
			}
		});
		game.notifyRat.subscribe((sender, rat) -> {
			if (rat == this)
				++attack;
		});
		game.ratDies.subscribe((sender, arg) -> --attack);
		game.ratEnters.invoke(this, null);

	}

	@Override
	public void close() throws IOException {
		// todo: rat dies ;(
		game.ratDies.invoke(this, null);
	}
}

public class ObserverExercise {

}
