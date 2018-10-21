package structural.adapter.exercise;

// you are given a Rectangle interface and an exstension method on it.
// try to define a SquareToRectangleAdapter that adapts the Square to the Rectangle interface.

class Square {
	public int side;

	public Square(int side) {
		this.side = side;
	}
}

interface Rectangle {
	int getWidth();

	int getHeight();

	default int getArea() {
		return getWidth() * getHeight();
	}
}

class SquareToRectangleAdapter implements Rectangle {
	
	/**
	 * the Adapter is COMPOSED with the adaptee
	 */
	private Square square;

	public SquareToRectangleAdapter(Square square) {
		this.square = square;
	}

	@Override
	public int getWidth() {
		return square.side;
	}

	@Override
	public int getHeight() {
		return square.side;
	}
}
