package lab;

/**
 * An abstract class describing how a visitor looks like.
 *
 * @author Peter Sunnergren
 */
public abstract class AbstractVisitor {

	/**
	 * Visits a square.
	 */
	public abstract void visit(Square s);

	/**
	 * Visits a rectangle.
	 */
	public abstract void visit(Rectangle r);

	/**
	 * Visits a circle.
	 */
	public abstract void visit(Circle c);

	/**
	 * Visits a triangle.
	 */
	public abstract void visit(Triangle t);

	// YOUR CODE HERE
	// May be something else should be added?
}
