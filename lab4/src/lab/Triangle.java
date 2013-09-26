package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.AbstractList;


/**
 * Represents the triangle shape.
 *
 * @author Peter Sunnergren
 */
public class Triangle extends AbstractShape {

	private Polygon polygon;

	/**
	 * Returns this if this is the marked shape and null otherwise.
	 */
	public AbstractShape getMarkedShape(int cx, int cy) {

		if (polygon.contains(cx, cy)) return this;
		return null;
	}

	/**
	 * Draws the triangle.
	 */
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D)g;

		if (polygon == null) {
			polygon = new Polygon();
			polygon.addPoint(getX() + getWidth() / 2, getY());
			polygon.addPoint(getX(), getY() + getHeight());
			polygon.addPoint(getX() + getWidth(), getY() + getHeight());
		}

		g2.setColor(Color.green);
		g2.fill(polygon);
	}

	/**
	 * Accepts a visitor.
	 */
	public void accept(AbstractVisitor v) {

		v.visit(this);
	}

	/**
	 * Adds the triangle to the list of shapes.
	 */
	public AbstractList<AbstractShape>
		getListOfShapes(AbstractList<AbstractShape> list) {

		list.add(this);
		return list;
	}
}
