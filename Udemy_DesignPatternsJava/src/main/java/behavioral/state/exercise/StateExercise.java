package behavioral.state.exercise;

class CombinationLock {
	private int[] combination;
	public String status;
	private int digits = 0;
	private boolean failed = false;

	public CombinationLock(int[] combination) {
		this.combination = combination;
		init();
	}

	private void init() {
		status = "LOCKED";
		digits = 0;
	}

	public void enterDigit(int digit) {
		// check digit and update status here
		if (status.equals("LOCKED")) status = "";
		
		status += digit;
		
		if (combination[digits] != digit)
	    {
	      failed  = true;
	    }
	    digits++;
	    
	    if (digits == combination.length)
	        status = failed ? "ERROR" : "OPEN";
	}
}

public class StateExercise {

}
