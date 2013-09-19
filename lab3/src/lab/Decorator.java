package lab;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.AbstractList;
import java.util.Iterator;

// YOUR CODE HERE
// extends? implements?
class Decorator implements Renderer {

	private Renderer decorated;

	public Decorator(Renderer r) {
		decorated = r;
	}

	private void decorateRectangle(Rectangle r) {

		java.awt.Graphics g = GamePanel.getBuffer();
		g.drawLine(r.x + 2, r.y - 2, r.x + r.width + 2, r.y - 2);
		g.drawLine(r.x + r.width + 2, r.y - 2,
				r.x + r.width + 2, r.y - 2 + r.height);
	}

	// YOUR CODE HERE
	// Overwrite some of the inherited/implemented methods.
	
	@Override
	public void outside() {
		decorated.outside();
	}
	
	@Override
	public void setSnakeColor(Color color) {
		decorated.setSnakeColor(color);
	}
	
	@Override
	public void putBackground() {
		decorated.putBackground();
	}
	
	@Override
	public void putBody(AbstractList<Rectangle> body) {
		decorated.putBody(body);
		Iterator<Rectangle> iterator = body.iterator();
		while (iterator.hasNext())
			decorateRectangle(iterator.next());			
	}
	
	@Override
	public void putBonus(AbstractList<Rectangle> bonus) {
		decorated.putBonus(bonus);
		Iterator<Rectangle> iterator = bonus.iterator();
		while (iterator.hasNext())
			decorateRectangle(iterator.next());
	}
	
	@Override
	public void stop() { }
}
