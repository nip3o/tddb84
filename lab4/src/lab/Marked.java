package lab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Vector;

/**
 * The class that holds the list of marked shapes.
 *
 * @author Peter Sunnergren
 */
public class Marked {

	private static Vector<AbstractShape> markedShapes = new Vector<AbstractShape>();
	private static boolean controlIsDown;

	private Marked () {

	}

	/**
	 * Gets an instance of the marked shape. If there are more than one,
	 * the last added (in the list) is returned.
	 */
	public static AbstractShape instance() {

		if (markedShapes == null || markedShapes.isEmpty()) {
			System.out.println("No shape set as marked.");
			return null;
		}

		return markedShapes.lastElement();
	}

	/**
	 * Marks a shape by placing it in the list of marked shapes.
	 * If the control key is not pressed, the list is emptied first.
	 */
	public static void markShape(AbstractShape s) {

		if (s == null) {
			System.out.println("Trying to add a null shape.");
			return;
		}

		if (!controlIsDown) markedShapes.clear();

		markedShapes.add(s);
	}

	/**
	 * Draws a border around the marked shapes.
	 */
	public static void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);

		Iterator<AbstractShape> iter = markedShapes.iterator();
		while (iter.hasNext()) {
			AbstractShape shape = iter.next();
			g2.setStroke(new BasicStroke(shape.getWidth() / 70 + 1));

			if (shape instanceof Square)
				g2.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
			else if (shape instanceof Circle)
				g2.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
			else if (shape instanceof SquareProxy)
				g2.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
			else if (shape instanceof Triangle) {
				g2.drawLine(shape.getX() + shape.getWidth() / 2,
					shape.getY(), shape.getX(), shape.getY() + shape.getHeight());
				g2.drawLine(shape.getX(), shape.getY() + shape.getHeight(),
					shape.getX() + shape.getWidth(), shape.getY() + shape.getHeight());
				g2.drawLine(shape.getX() + shape.getWidth(), shape.getY() +
					shape.getHeight(), shape.getX() + shape.getWidth() / 2 , shape.getY());
			} else if (shape instanceof Rectangle)
				g2.drawRect(shape.getX(), shape.getY(),
					shape.getWidth(), shape.getHeight());
		}
	}

	/**
	 * Sets that the control key is down.
	 */
	public static void setControlDown() {

		controlIsDown = true;
	}

	/**
	 * Sets that the control key is up.
	 */
	public static void setControlUp() {

		controlIsDown = false;
	}

	/**
	 * Used to check if the control key is down.
	 */
	public static boolean isControlDown() {

		return controlIsDown;
	}
}
