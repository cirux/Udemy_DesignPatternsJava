package structural.facade;

import java.util.ArrayList;
import java.util.List;

// low level components

class Buffer{
	private char [] characters;
	private int lineWidth;
	
	public Buffer(int linheHeight, int lineWidth) {
		this.lineWidth = lineWidth;
		characters = new char[lineWidth * linheHeight];
	}
	
	public char charAt(int x, int y) {
		return characters[y*lineWidth+x];
	}
}

class ViewPort{
	
	private Buffer buffer;
	private int width;
	private int height;
	private int offsetX;
	private int offsetY;

	public ViewPort(Buffer buffer, int width, int height, int offsetX, int offsetY) {
		this.buffer = buffer;
		this.width = width;
		this.height = height;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		
	}
	
	/**
	 * applies the offset
	 * @param x
	 * @param y
	 * @return
	 */
	public char charAt(int x, int y) {
		return buffer.charAt(x+offsetX, y+offsetY);
	}
}


class Console{
	
	private List<ViewPort> viewports = new ArrayList<>();
	int width, heigth;
	
	public Console(int width, int heigth) {
		super();
		this.width = width;
		this.heigth = heigth;
	}
	
	public void addViewPort(ViewPort vp) {
		this.viewports.add(vp);
	}
	
	/**
	 * simplified facade API
	 * 
	 * @param width
	 * @param heigth
	 * @return
	 */
	public static Console newConsole(int width, int heigth) {
		Buffer buffer = new Buffer(width,heigth);
		ViewPort viewport = new ViewPort(buffer, width, heigth, 0, 0);
		Console console = new Console(width, heigth);
		console.addViewPort(viewport);
		return console;
	}
	
	public void render() {
		
		for(int y=0; y<heigth; y++) {
			for(int x=0; x<width; x++) {
				for(ViewPort vp : viewports)
					System.out.print(vp.charAt(x, y));
			}
			System.out.println();
		}
	}
}

public class FacadeDemo {

	public static void main(String[] args) {
		
		// the initialization of the console is very low level
		Buffer buffer = new Buffer(30,20);
		ViewPort viewport = new ViewPort(buffer, 30, 20, 0, 0);
		Console console = new Console(30, 20);
		console.addViewPort(viewport);
		console.render();
		
		// using facade
		Console console2 = Console.newConsole(30, 20);
		console2.render();

	}

}
