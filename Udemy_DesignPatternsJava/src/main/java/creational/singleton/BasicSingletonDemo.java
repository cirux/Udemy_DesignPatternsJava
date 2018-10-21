package creational.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class BasicSingleton implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5979057554321630742L;

	// cannot new this class, however
	// * instance can be created deliberately (reflection)
	// * instance can be created accidentally (serialization)
	private BasicSingleton() {
		System.out.println("Singleton is initializing");
	}

	private static final BasicSingleton INSTANCE = new BasicSingleton();

	private int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	// 

	/**
	 * required for correct serialization,readResolve is used for _replacing_ the
	 * object read from the stream <br>
	 * For Serializable and Externalizable classes, the readResolve method allows a
	 * class to replace/resolve the object read from the stream before it is
	 * returned to the caller. By implementing the readResolve method, a class can
	 * directly control the types and instances of its own instances being
	 * deserialized.
	 * 
	 * @return
	 */
	protected Object readResolve() {
		return INSTANCE;
	}

	// generated getter
	public static BasicSingleton getInstance() {
		return INSTANCE;
	}
}

class BasicSingletonDemo {
	static void saveToFile(BasicSingleton singleton, String filename) throws Exception {
		try (FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(singleton);
		}
	}

	static BasicSingleton readFromFile(String filename) throws Exception {
		try (FileInputStream fileIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			return (BasicSingleton) in.readObject();
		}
	}

	public static void main(String[] args) throws Exception {
		BasicSingleton singleton = BasicSingleton.getInstance();
		singleton.setValue(111);

		String filename = "singleton.bin";
		saveToFile(singleton, filename);

		singleton.setValue(222);

		BasicSingleton singleton2 = readFromFile(filename);

		System.out.println(singleton == singleton2);

		System.out.println(singleton.getValue());
		System.out.println(singleton2.getValue());
	}
}
