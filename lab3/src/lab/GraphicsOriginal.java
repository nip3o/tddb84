package lab;

import java.awt.Rectangle;
import java.util.AbstractList;
import java.util.Iterator;

/**
 * Base graphics to show the snake on the screen.
 *
 * @author Peter Sunnergren
 */
public class GraphicsOriginal extends AbstractGraphics {

	/**
	 * Puts the snake on the screen
	 *
	 * @param body The snake body.
	 */
	public void putBody(AbstractList<Rectangle> body) {

		Rectangle r;
		java.awt.Graphics g = GamePanel.getBuffer();
		g.setColor(snakeColor);
		Iterator<Rectangle> iterator = body.iterator();
		while (iterator.hasNext()) {
			r = iterator.next();
			g.fillRect(r.x, r.y, r.height, r.width);
		}
	}

	/**
	 * Puts the bonus on the screen.
	 *
	 * @param bonuses A list of the bonuses.
	 */
	public void putBonus(AbstractList<Rectangle> bonuses) {

		Rectangle r;
		java.awt.Graphics g = GamePanel.getBuffer();
		Iterator<Rectangle> iterator = bonuses.iterator();
		g.setColor(bonusColor);
		while (iterator.hasNext()) {
			r = iterator.next();
			g.fillRect(r.x, r.y, r.height, r.width);
		}
	}

	public void stop() {

	}
}
