package lab;

import java.awt.Graphics;
import java.util.AbstractList;

/**
 * This class is an abstraction of the square class
 * according to the proxy pattern.
 *
 * @author Peter Sunnergren
 */
public abstract class AbstractSquare extends AbstractShape {

	private int depth = 0;

	/**
	 * Gets the marked shape.
	 *
	 * @param x Mouse X-coordinate.
	 * @param y Mouse Y-coordinate.
	 * @return This if it is marked and null otherwise.
	 */
	abstract public AbstractShape getMarkedShape(int x, int y);

	/**
	 * Draws the square.
	 */
	abstract public void paint(Graphics g);

	/**
	 * Draws the children.
	 */
	abstract public void paintChildren(Graphics g);

	/**
	 * Accepts a visitor.
	 */
	abstract public void accept(AbstractVisitor v);

	/**
	 * Adds this shape to the list of shapes.
	 */
	abstract public AbstractList<AbstractShape>
		getListOfShapes(AbstractList<AbstractShape> list);

	/**
	 * Adds a child.
	 */
	abstract public void addChild(AbstractShape child);

	/**
	 * Used to check if there are any children.
	 */
	abstract public boolean hasChildren();

	/**
	 * Gets the last child in the list of children.
	 */
	abstract public AbstractShape getLastChild();

	/**
	 * Sets the depth of the square.
	 */
	public void setDepth(int d) {

		depth = d;
	}

	/**
	 * Gets the depth of the square.
	 */
	public int getDepth() {

		return depth;
	}
}
