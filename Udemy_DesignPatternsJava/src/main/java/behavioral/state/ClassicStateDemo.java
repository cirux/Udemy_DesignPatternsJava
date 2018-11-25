package behavioral.state;

/**
 * State base class
 */
class State{
	void on(LightSwitch ls) {
		System.out.println("Light is already on");
	}
	
	void off(LightSwitch ls) {
		System.out.println("Light is already off");
	}
}

/**
 * ConcreteState
 */
class OnState extends State{
	
	public OnState() {
		System.out.println("Light turned on");
	}
	
	@Override
	void off(LightSwitch ls) {
		System.out.println("Switching light off..");
		ls.setState(new OffState());
	}
	
}

/**
 * ConcreteState
 */
class OffState extends State{
	
	public OffState() {
		System.out.println("Light turned off");
	}
	
	@Override
	void on(LightSwitch ls) {
		System.out.println("Switching light on..");
		ls.setState(new OnState());
	}
	
}

/**
 * Context
 */
class LightSwitch {
	
	private State state;
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public LightSwitch() {
		state = new OffState();
	}
	
	void on() {
		state.on(this);
	}
	
	void off() {
		state.off(this);
	}
}

public class ClassicStateDemo {

	public static void main(String[] args) {
		
		LightSwitch ls = new LightSwitch();
		ls.on();
		ls.off();
		ls.off();

	}

}
