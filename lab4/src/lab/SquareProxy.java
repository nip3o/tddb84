package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.AbstractList;

/**
 * The SquareProxy acts as if it would have been a Square. It inherits from
 * Square and reuses the Square behaviour when it is supposed to be the same
 * (when the proxy is open) via super.someMethod() calls and provides a 
 * separate behaviour when the proxy is closed.
 * 
 * The methods 'getMarkedShape', 'paint', 'accept' and 'getListOfShapes' 
 * provide new behaviour, while the other methods are redundant since they 
 * call the same method of the base class, but they are included since they 
 * were part of the template.
 */
public class SquareProxy extends Square {
	private boolean open = true;

	// YOUR CODE HERE
	// Any missing methods?
	// END OF YOUR CODE

	/**
	 * Gets the marked shape. Checks this shape if it is open.
	 * 
	 * @return This if it is marked and null otherwise.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {

		if ((getX() > cx) || (getX() + getWidth() < cx) || (getY() > cy)
				|| (getY() + getHeight() < cy))
			return null;

		AbstractShape shape = null;

		if (!open) {
			open = true;
			shape = this;
		} else {
			// YOUR CODE HERE
			// Any additions?
			// END OF YOUR CODE

			if (shape == null) {
				open = false;
				shape = this;
			}
		}

		return shape;
	}

	/**
	 * Draws the proxy.
	 */
	public void paint(Graphics g) {
		if (open) {
			g.setColor(Color.orange);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			// YOUR CODE HERE
			paintChildren(g);
			// END OF YOUR CODE
		} else {
			g.setColor(Color.black);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		}
	}

	/**
	 * Adds the proxy to the list of shapes. Only adds the children if the proxy
	 * is open.
	 */
	public AbstractList<AbstractShape> getListOfShapes(
			AbstractList<AbstractShape> list) {
		// YOUR CODE HERE
		if (open) {
			super.getListOfShapes(list);
		} else {
			list.add(this);
		}
		// END OF YOUR CODE
		return list;
	}
	
	/**
	 * Accepts a Visitor.
	 */
	public void accept(AbstractVisitor v) {
		// YOUR CODE HERE
		if (open) {
			super.accept(v);
		} else {
			v.visit(this);
		}
		// END OF YOUR CODE
	}
	
	/*
	 * All the methods below can be removed since they call the same method
	 * of the base class, but they are included since they were added in the
	 * given template.
	 */
	
	/**
	 * Draws the children of the proxy.
	 */
	public void paintChildren(Graphics g) {
		// YOUR CODE HERE
		super.paintChildren(g);
		// END OF YOUR CODE
	}

	/**
	 * Adds a child.
	 */
	public void addChild(AbstractShape child) {
		// YOUR CODE HERE
		super.addChild(child);
		// END OF YOUR CODE
	}

	/**
	 * Used to check if there are any children.
	 */
	public boolean hasChildren() {
		// YOUR CODE HERE
		return super.hasChildren();
		// END OF YOUR CODE
	}

	/**
	 * Gets the last child in the list of children.
	 */
	public AbstractShape getLastChild() {
		// YOUR CODE HERE
		return super.getLastChild();
		// END OF YOUR CODE
	}
}
