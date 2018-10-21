package structural.bridge;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

// Shape -> Circle, Square
// Rendering -> Vector, Raster

// VectorCircleRenderer, VectorSquareRenderer, RasterCircleRenderer...

/**
 * Defines how something is rendered
 * 
 * @author ciro
 *
 */
interface Renderer {
	void renderCircle(float radius);
}

// some implementations

class VectorRenderer implements Renderer {
	@Override
	public void renderCircle(float radius) {
		System.out.println("Drawing a circle of radius " + radius);
	}
}

class RasterRenderer implements Renderer {
	@Override
	public void renderCircle(float radius) {
		System.out.println("Drawing pixels for a circle of radius " + radius);
	}
}

abstract class Shape {
	protected Renderer renderer;

	public Shape(Renderer renderer) {
		this.renderer = renderer;
	}

	/**
	 * Draws a shape using the specified {@link #renderer}
	 */
	public abstract void draw();

	public abstract void resize(float factor);
}

class Circle extends Shape {
	public float radius;

	@Inject
	public Circle(Renderer renderer) {
		super(renderer);
	}

	public Circle(Renderer renderer, float radius) {
		super(renderer);
		this.radius = radius;
	}

	@Override
	public void draw() {
		renderer.renderCircle(radius);
	}

	@Override
	public void resize(float factor) {
		radius *= factor;
	}
}

/**
 * Whenever somebody requests a renderer to be injected we're going to make a
 * new instance of the VectorRenderer
 * 
 * @author ciro
 *
 */
class ShapeModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(Renderer.class).to(VectorRenderer.class);
	}
}

/**
 * Uses Google Guice (pronounced 'juice'), a lightweight dependency injection
 * framework for Java 6 and above, brought to you by Google.
 * https://github.com/google/guice
 * 
 * @author ciro
 *
 */
public class BridgeDemo {

	public static void main(String[] args) {
		// Manual dependency injection
		// RasterRenderer rasterRenderer = new RasterRenderer();
		// VectorRenderer vectorRenderer = new VectorRenderer();
		// Circle circle = new Circle(vectorRenderer, 5);
		// circle.draw();
		// circle.resize(2);
		// circle.draw();

		// todo: Google Guice
		Injector injector = Guice.createInjector(new ShapeModule());
		Circle instance = injector.getInstance(Circle.class);
		instance.radius = 3;
		instance.draw();
		instance.resize(2);
		instance.draw();
	}

}
