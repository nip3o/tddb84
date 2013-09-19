package lab;

import java.awt.Rectangle;
import java.util.AbstractList;
import java.util.ArrayList;

// YOUR CODE HERE
// Extends? Implements?
public class ABCFacade extends AbstractGraphics {
	private ModuleA a;
	private ModuleB b;
	private ModuleC c;

	public ABCFacade() {
		a = new ModuleA();
		b = new ModuleB();
		c = new ModuleC();
		new Thread(a).start();
	}
	// YOUR CODE HERE
	// Overwrite some methods that you inherit/implement.
	
	@Override
	public void putBackground() {
		a.drawBackground();
	}
	
	@Override
	public void putBody(AbstractList<Rectangle> body) {
		b.drawSnake(body);
	}
	
	@Override
	public void putBonus(AbstractList<Rectangle> bonus) {
		ArrayList<Rectangle> list = new ArrayList<Rectangle>(bonus);
		c.putBonus(list);
	}
	
	@Override
	public void stop() {}
}
