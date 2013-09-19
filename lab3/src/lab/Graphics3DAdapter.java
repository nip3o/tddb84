package lab;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.AbstractList;
import java.util.Iterator;

// YOUR CODE HERE

/*
 * In this task, we want to replace the interface each graphics module, with a common
 * interface for all renderers. This is a typical use-case of the Adapter pattern,
 * since we want to completely replace the old interface of one or multiple classes
 * with a new interface.
 */

class Graphics3DAdapter extends AbstractGraphics {
	Graphics3D graphics = new Graphics3D();

	@Override
	public void putBackground() {
		graphics.printBase(Color.lightGray);
	}

	@Override
	public void putBody(AbstractList<Rectangle> body) {
		Iterator<Rectangle> iterator = body.iterator();
		while (iterator.hasNext()) {
			Rectangle r = iterator.next();
			graphics.printBox(new Box(r.x, r.y, r.x + r.width, r.y + r.height), snakeColor);
		}
	}

	@Override
	public void putBonus(AbstractList<Rectangle> bonus) {
		Iterator<Rectangle> iterator = bonus.iterator();
		while (iterator.hasNext()) {
			Rectangle r = iterator.next();
			graphics.printBonus(new Box(r.x, r.y, r.x + r.width, r.y + r.height), bonusColor);
		}
	}

	@Override
	public void stop() {}
}
