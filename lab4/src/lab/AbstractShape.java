package lab;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.AbstractList;

/**
 * The abstract class for all shapes.
 * Holds the information about the placement, size, and
 * family of an instance of a shape.
 *
 * @author Peter Sunnergren
 */
abstract public class AbstractShape {

	private int x;
	private int y;
	private int width;
	private int height;
	private AbstractSquare parent;
	private AbstractShape sibling;
	private double scale = 1.0;

	/**
	 * True if the square should paint its children in the paint method.
	 */
	protected static boolean paintChildren = false;

	/**
	 * Draws the shape.
	 */
	abstract public void paint(Graphics g);

	// YOUR CODE HERE
	// removeOverlap is private and should stay private.
	// You might want to implement a supplementary public method
	// in order to facilitate the corresponding design pattern.
	// END OF YOUR CODE

	/**
	 * Removes the overlap between this shape and the new shape by
	 * resizing the bounds of the latter.
	 *
	 * @param bounds The bounds of the new shape.
	 */
	private void removeOverlap(Rectangle bounds) {

		Rectangle thisBounds = new Rectangle(
			getX(), getY(), getWidth(), getHeight());

		if (thisBounds.contains(bounds) ||
			bounds.contains(thisBounds)) {

			bounds.setRect(0, 0, 0, 0);
		} else {
			Rectangle intersection = bounds.intersection(thisBounds);

			if (!intersection.isEmpty()) {
				if (getX() == bounds.x) {
					if (getY() < bounds.y) {
						bounds.y += intersection.height;
						bounds.height -= intersection.height;
					} else {
						bounds.height -= intersection.height;
					}
				} else if (getY() == bounds.y) {
					if (getX() < bounds.x) {
						bounds.x += intersection.width;
						bounds.width -= intersection.width;
					} else {
						bounds.width -= intersection.width;
					}
				} else if ((getX() < bounds.x) && (getY() < bounds.y )) {
					bounds.y += intersection.height;
					bounds.x += intersection.width;
					bounds.height -= intersection.height;
					bounds.width -= intersection.width;
				} else if ((getX() < bounds.x + bounds.width) && (getY() < bounds.y)) {
					bounds.width -= intersection.width;
					bounds.y += intersection.height;
					bounds.height -= intersection.height;
				} else if ((getX() < bounds.x) && (getY() < bounds.y + bounds.height)) {
					bounds.height -= intersection.height;
					bounds.x += intersection.width;
					bounds.width -= intersection.width;
				} else if ((getX() <  bounds.x + bounds.width) && (getY() < bounds.y + bounds.height)) {
					bounds.height -= intersection.height;
					bounds.width -= intersection.width;
				} else {
					System.out.print("**************************");
				}
			}
		}
	}

	/**
	 * Gets the marked shape.
	 *
	 * @param x X coordinate where the mouse was clicked.
	 * @param y Y coordinate where the mouse was clicked.
	 * @return The marked shape.
	 */
	abstract public AbstractShape getMarkedShape(int x, int y);

	/**
	 * Accepts a visitor.
	 */
	abstract public void accept(AbstractVisitor v);

	/**
	 * Adds the shape to a list of shapes.
	 */
	abstract public AbstractList<AbstractShape>
		getListOfShapes(AbstractList<AbstractShape> list);

	/**
	 * Sets the X coordinate.
	 */
	protected void setX(int x) {

		this.x = x;
	}

	/**
	 * Gets the X coordinate.
	 */
	protected int getX() {

		return x;
	}

	/**
	 * Sets the Y coordinate.
	 */
	protected void setY(int y) {

		this.y = y;
	}

	/**
	 * Gets the Y coordinate.
	 */
	protected int getY() {

		return y;
	}

	/**
	 * Sets the width.
	 */
	protected void setWidth(int w) {
		this.width = w;
	}

	/**
	 * Gets the width.
	 */
	protected int getWidth() {

		return width;
	}

	/**
	 * Sets the height.
	 */
	protected void setHeight(int h) {

		this.height = h;
	}

	/**
	 * Gets the height.
	 */
	protected int getHeight() {

		return height;
	}

	/**
	 * Sets the parent.
	 */
	protected void setParent(AbstractSquare p) {

		parent = p;
	}

	/**
	 * Gets the parent.
	 */
	protected AbstractSquare getParent() {

		return parent;
	}

	/**
	 * Sets the sibling.
	 */
	protected void setSibling(AbstractShape s) {

		sibling = s;
	}

	/**
	 * Gets the sibling.
	 */
	protected AbstractShape getSibling() {

		return sibling;
	}

	/**
	 * Sets the scale.
	 */
	protected void setScale(double s) {

		scale = s;
	}

	/**
	 * Gets the scale.
	 */
	protected double getScale() {

		return scale;
	}
}
