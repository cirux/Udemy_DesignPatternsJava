package structural.composite.exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

interface ValueContainer extends Iterable<Integer> {
}

class SingleValue implements ValueContainer {
	public int value;

	// please leave this constructor as-is
	public SingleValue(int value) {
		this.value = value;
	}

	@Override
	public Iterator<Integer> iterator() {
		return Collections.singleton(value).iterator();
	}
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {
}

class MyList extends ArrayList<ValueContainer> {
	// please leave this constructor as-is
	public MyList(Collection<? extends ValueContainer> c) {
		super(c);
	}

	public int sum() {
		int result = 0;
		for(ValueContainer vc : this) {
			for(int value : vc) {
				result += value;
			}
		}
		return result;
	}
}

public class CompositeExercise {

}
