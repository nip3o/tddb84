package lab;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Module that make the bonuses move
 *
 * @author Peter Sunnergren
 */
public class ModuleC {

	private class MovingRectangle extends Rectangle {

		private static final long serialVersionUID = 1L;
		public int speedX;
		public int speedY;

		public MovingRectangle(Rectangle r, int speedX, int speedY) {

			super(r);
			this.speedX = speedX;
			this.speedY = speedY;
		}
	}

	/**
	 * Puts the bonuses in the screen. Actually it replaces all occurrences of
	 * Rectangle in the body with a subclass of Rectangle.
	 *
	 * @param bonuses
	 */
	public void putBonus(ArrayList<Rectangle> bonuses) {

		Iterator<Rectangle> iter = bonuses.iterator();

		while (iter.hasNext()) {
			Rectangle r = iter.next();
			MovingRectangle m = new MovingRectangle(r,
				(int)Math.round(Math.random()*10), (int)Math.round(Math.random()*10));

			if (m.x + m.speedX < 0) {
				m.speedX = -m.speedX;
			}
			if ((m.x + m.width + m.speedX) > GamePanel.getDimension().width) {
				m.speedX = -m.speedX;
			}
			if (m.y + m.speedY < 0) {
				m.speedY = -m.speedY;
			}
			if ((m.y + m.height + m.speedY) > GamePanel.getDimension().height) {
				m.speedY = -m.speedY;
			}
			m.x += m.speedX;
			m.y += m.speedY;
			GamePanel.getBuffer().setColor(Color.red);
			GamePanel.getBuffer().fillRect(m.x, m.y, m.width, m.height);
		}
	}
}
