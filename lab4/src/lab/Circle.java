package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Arc2D;
import java.util.AbstractList;

/**
 * Represents the circle shape.
 *
 * @author Peter Sunnergren
 */
public class Circle extends AbstractShape {

	/**
	 * Returns this if this is the marked shape and null otherwise.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {

		if (new Arc2D.Double(getX(), getY(), getWidth(),
			getHeight(), 0.0, 360.0, Arc2D.CHORD).contains(cx, cy)) {
			return this;
		}

		return null;
	}

	/**
	 * Draws the circle.
	 */
	public void paint(Graphics g) {

		g.setColor(Color.red);
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Accepts a visitor.
	 */
	public void accept(AbstractVisitor v) {

		v.visit(this);
	}

	/**
	 * Add the circle to the list of shapes.
	 */
	public AbstractList<AbstractShape>
		getListOfShapes(AbstractList<AbstractShape> list) {

		list.add(this);
		return list;
	}

	/**
	 * Sets the width and makes sure that the circle is round.
	 */
	protected void setWidth(int w) {

		if (getHeight() != 0) {
			super.setHeight(Math.min(w, getHeight()));
			super.setWidth(Math.min(w, getHeight()));
		} else {
			super.setWidth(w);
		}
	}

	/**
	 * Sets the height of the circle and makes sure the it is round.
	 */
	protected void setHeight(int h) {

		if (getWidth() != 0) {
			super.setHeight(Math.min(h, getWidth()));
			super.setWidth(Math.min(h, getWidth()));
		} else {
			super.setHeight(h);
		}
	}
}
