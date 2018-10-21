/**
 * 
 */
package behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * The Event broker acts as Mediator. It uses RxJava, Reactive Streams
 * Implementations for Java: ReactiveX is an event processing library and it is
 * a combination of the best ideas from the Observer pattern, the Iterator
 * pattern, and functional programming. ReactiveX is An API for asynchronous
 * programming with observable streams
 * 
 * @author ciro
 *
 */
class EventBroker extends Observable<Integer> {
	private List<Observer<? super Integer>> observers = new ArrayList<>();

	@Override
	protected void subscribeActual(Observer<? super Integer> observer) {
		observers.add(observer);
	}

	public void publish(int n) {
		for (Observer<? super Integer> o : observers)
			o.onNext(n);
	}
}

class FootballPlayer {
	private int goalsScored = 0;
	private EventBroker broker;
	public String name;

	public FootballPlayer(EventBroker broker, String name) {
		this.broker = broker;
		this.name = name;
	}

	public void score() {
		broker.publish(++goalsScored);
	}
}

class FootballCoach {
	public FootballCoach(EventBroker broker) {
		broker.subscribe(i -> {
			System.out.println("Hey, you scored " + i + " goals!");
		});
	}
}

/**
 * Situation: you have a football game with players running around the field and
 * scoring goals and the football coach who wants to congratulates the players
 * as they score goals.
 * 
 * @author ciro
 *
 */
public class RxEventBrokerMediatorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventBroker broker = new EventBroker();
		FootballPlayer player = new FootballPlayer(broker, "jones");
		FootballCoach coach = new FootballCoach(broker);

		player.score();
		player.score();
		player.score();

		FootballPlayer player2 = new FootballPlayer(broker, "jane");
		player2.score();

		player.score();

	}

}
