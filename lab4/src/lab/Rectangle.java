package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.AbstractList;

/**
 * Represents the rectangular shape.
 *
 * @author Peter Sunnergren
 */
public class Rectangle extends AbstractShape {

	/**
	 * Returns this if it is clicked returns null otherwise.
	 *
	 * @param cx Mouse clicked X-coordinate.
	 * @param cy Mouse clicked Y-coordinate.
	 * @return The rectangle or null.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {

		if ((getX() < cx) && (getX() + getWidth() > cx) &&
			(getY() < cy) && (getY() + getHeight() > cy)) {

			return this;
		}

		return null;
	}

	/**
	 * Draws the rectangle.
	 */
	public void paint(Graphics g) {

		g.setColor(Color.blue);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Accepts a visitor.
	 */
	public void accept(AbstractVisitor v) {

		v.visit(this);
	}

	/**
	 * Adds the rectangle to the list of shapes.
	 */
	public AbstractList<AbstractShape>
		getListOfShapes(AbstractList<AbstractShape> list) {

		list.add(this);
		return list;
	}
}
