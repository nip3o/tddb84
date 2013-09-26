package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Represents the square shape.
 *
 * @author Peter Sunnergren
 */
public class Square extends AbstractSquare {

	protected Vector<AbstractShape> children;

	public Square() {

		children = new Vector<AbstractShape>();
	}

	/**
	 * Gets the marked shape.
	 * Checks if this or its children is marked.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {

		AbstractShape result = null;
		Iterator<AbstractShape> iter = children.iterator();

		while (iter.hasNext()) {
			AbstractShape s = iter.next();
			s = s.getMarkedShape(cx, cy);
			if (null != s) {
				/**
				 * To make sure that all children is checked.
				 */
				result = s;
			}
		}

		if (result != null) return result;

		if ((getX() < cx) && (getX() + getWidth() > cx) &&
			(getY() < cy) && (getY() + getHeight() > cy)) {

			return this;
		}

		return null;
	}

	/**
	 * Draws the square.
	 */
	public void paint(Graphics g) {

		g.setColor(new Color((200 - getDepth() * 20),
			(200 - getDepth() * 20), (200 - getDepth() * 20)));
		g.fillRect(getX(), getY(), getWidth(), getHeight());

		if (paintChildren) paintChildren(g);
	}

	/**
	 * Draws the children.
	 */
	public void paintChildren(Graphics g) {

		Iterator<AbstractShape> iter = children.iterator();
		while (iter.hasNext())
			iter.next().paint(g);
	}

	/**
	 * Accepts a visitor.
	 */
	public void accept(AbstractVisitor v) {

		v.visit(this);
		Iterator<AbstractShape> iter = children.iterator();
		while (iter.hasNext()) iter.next().accept(v);
	}

	/**
	 * Adds the square and its children to the list of shapes.
	 */
	public AbstractList<AbstractShape>
		getListOfShapes(AbstractList<AbstractShape> list) {

		list.add(this);

		Iterator<AbstractShape> iter = children.iterator();
		while (iter.hasNext())
			iter.next().getListOfShapes(list);

		return list;
	}

	/**
	 * Adds a child.
	 */
	public void addChild(AbstractShape child) {

		children.add(child);
	}

	/**
	 * Used to check if there are any children.
	 */
	public boolean hasChildren() {

		return !children.isEmpty();
	}

	/**
	 * Gets the last child in the list of children.
	 */
	public AbstractShape getLastChild() {

		return children.lastElement();
	}

	/**
	 * Sets the width and make sure that the square is square.
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
	 * Sets the height and make sure that the square is square.
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
