package structural.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * It's a square or a circle or a combination of shapes.
 * NB isn't abstract
 * 
 * @author ciro
 *
 */
class GraphicObject {

	/**
	 * by default a {@link GraphicObject} is a collection of other object unless
	 * somebody inherited from it specifies something different
	 */
	protected String name = "Group";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GraphicObject() {
	}

	public String color;

	/**
	 * the core of the composite design pattern is being able to treat individual
	 * objects and groups of objects in a uniform fashion
	 */
	public List<GraphicObject> children = new ArrayList<>();

	private void print(StringBuilder stringBuilder, int depth) {
		stringBuilder.append(String.join("", Collections.nCopies(depth, "*"))).append(depth > 0 ? " " : "")
				.append((color == null || color.isEmpty()) ? "" : color + " ").append(getName())
				.append(System.lineSeparator());
		for (GraphicObject child : children)
			child.print(stringBuilder, depth + 1); // increase depth
	}

	@Override
	public String toString() {
		// recursive
		StringBuilder sb = new StringBuilder();
		print(sb, 0);	// initial depth is zero
		return sb.toString();
	}
}

class Circle extends GraphicObject {
	public Circle(String color) {
		name = "Circle";
		this.color = color;
	}
}

class Square extends GraphicObject {
	public Square(String color) {
		name = "Square";
		this.color = color;
	}
}

public class GeometricShapesDemo {

	public static void main(String[] args) {
		GraphicObject drawing = new GraphicObject();
		drawing.setName("My Drawing");
		drawing.children.add(new Square("Red"));
		drawing.children.add(new Circle("Yellow"));

		GraphicObject group = new GraphicObject();
		group.children.add(new Circle("Blue"));
		group.children.add(new Square("Blue"));
		drawing.children.add(group);

		System.out.println(drawing);
	}

}
