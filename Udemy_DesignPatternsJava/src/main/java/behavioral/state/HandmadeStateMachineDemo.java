package behavioral.state;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;


enum PhoneState {

	/**
	 * sganciato
	 */
	OFF_HOOK, // initial state
	/**
	 * agganciato
	 */
	ON_HOOK, // terminal state
	CONNECTING, CONNECTED,
	/**
	 * in attesa
	 */
	ON_HOLD
}

/**
 * Action I can perform in order to cause a transition from one state to another
 */
enum Trigger {
	CALL_DIALED,
	/**
	 * riattaccare
	 */
	HUNG_UP, CALL_CONNECTED, PLACED_ON_HOLD, TAKEN_OFF_HOLD, LEFT_MESSAGE, STOP_USING_PHONE
}

public class HandmadeStateMachineDemo {

	/**
	 * the rules wich make up the state machine
	 */
	private static Map<PhoneState, List<Pair<Trigger, PhoneState>>> rules = new HashMap<>();

	static {
		rules.put(PhoneState.OFF_HOOK, List.of(new Pair<>(Trigger.CALL_DIALED, PhoneState.CONNECTING),
				new Pair<>(Trigger.STOP_USING_PHONE, PhoneState.ON_HOOK)));
		rules.put(PhoneState.CONNECTING, List.of(new Pair<>(Trigger.HUNG_UP, PhoneState.OFF_HOOK),
				new Pair<>(Trigger.CALL_CONNECTED, PhoneState.CONNECTED)));
		rules.put(PhoneState.CONNECTED,
				List.of(new Pair<>(Trigger.LEFT_MESSAGE, PhoneState.OFF_HOOK),
						new Pair<>(Trigger.HUNG_UP, PhoneState.OFF_HOOK),
						new Pair<>(Trigger.PLACED_ON_HOLD, PhoneState.ON_HOLD)));
		rules.put(PhoneState.ON_HOLD, List.of(new Pair<>(Trigger.TAKEN_OFF_HOLD, PhoneState.CONNECTED),
				new Pair<>(Trigger.HUNG_UP, PhoneState.OFF_HOOK)));
	}

	private static PhoneState currentState = PhoneState.OFF_HOOK;
	private static PhoneState exitState = PhoneState.ON_HOOK;

	public static void main(String[] args) {

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("The phone is currentrly " + currentState);
			System.out.println("Select a trigger:");

			for (int i = 0; i < rules.get(currentState).size(); i++) {

				Trigger trigger = rules.get(currentState).get(i).getKey();
				System.out.println("" + i + ". " + trigger);
			}

			boolean parseOK;
			int choice = 0;
			do {
				try {
					System.out.println("Please enter your choice");

					choice = Integer.parseInt(console.readLine());
					parseOK = choice >= 0 && choice < rules.get(currentState).size();

				} catch (Exception e) {
					parseOK = false;
				}
			} while (!parseOK);

			currentState = rules.get(currentState).get(choice).getValue();

			if (currentState == exitState)
				break;

		}
		System.out.println("And we are done");

	}

}
