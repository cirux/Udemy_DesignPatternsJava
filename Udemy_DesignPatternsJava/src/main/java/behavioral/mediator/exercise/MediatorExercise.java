package behavioral.mediator.exercise;

import java.util.ArrayList;
import java.util.List;

class Participant {
	
	int value = 0;
	Mediator mediator;
	
	public Participant(Mediator mediator) {
		this.mediator = mediator;
		mediator.addParticipant(this);
	}

	public void say(int n) {
		mediator.broadcast(this, n);
	}

	public void notify(Participant sender, int n) {
		if(sender != this)
			value += n;
	}
}

class Mediator {
	
	List<Participant> participants = new ArrayList<>();
	
	void broadcast(Participant sender, int n) {
		for (Participant p : participants) {
				p.notify(sender, n);
		}
	}

	public void addParticipant(Participant participant) {
		participants.add(participant);
	}
}

public class MediatorExercise {

}
