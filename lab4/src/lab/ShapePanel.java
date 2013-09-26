package lab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * The panel that contains the root shape.
 *
 * @author Peter Sunnergren
 */
public class ShapePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Square root;
	private boolean paintVisitor = false;
	private boolean paintIterator = false;

	public ShapePanel () {

		setMinimumSize(new Dimension(400, 400));
		setMaximumSize(new Dimension(400, 400));
		setBackground(Color.yellow);
		root = new Square();
		root.setX(0);
		root.setY(0);
		root.setHeight(400);
		root.setWidth(400);
		Marked.markShape(root);
	}

	/**
	 * Draws the shapes and the borders around them.
	 * This is where the functionality to draw with a Visitor or
	 * an Iterator should be implemented.
	 */
	public void paint(Graphics g) {

		super.paint(g);

		if (AbstractShape.paintChildren) {
			root.paint(g);
		} else if (paintVisitor) {
			// YOUR CODE HERE
			// Place the code to draw with the Visitor.
			// END OF YOUR CODE
		} else if (paintIterator) {
			// YOUR CODE HERE
			// Place the code to draw with the Iterator.
			// END OF YOUR CODE
		}

		Marked.paint(g);
	}

	/**
	 * Switches to the default drawing method.
	 */
	public void setPaintDefault() {

		System.out.println("Switching to the default drawing method.");
		AbstractShape.paintChildren = true;
		paintVisitor = false;
		paintIterator = false;
	}

	/**
	 * Switches to the Visitor pattern.
	 */
	public void setPaintVisitor() {

		System.out.println("Switching to the visitor drawing method.");
		AbstractShape.paintChildren = false;
		paintVisitor = true;
		paintIterator = false;
	}

	/**
	 * Switches to the Iterator pattern.
	 */
	public void setPaintIterator() {

		System.out.println("Switching to the iterator drawing method.");
		AbstractShape.paintChildren = false;
		paintVisitor = false;
		paintIterator = true;
	}

	/**
	 * Applies the Visitor to the root shape.
	 */
	public void applyVisitor() {

		int totalNumber = 0;

		// YOUR CODE HERE
		// Place the code to count the shapes using the Visitor.
		// END OF YOUR CODE

		ShapeApplet.setOutputText(
			"Number of shapes: " + String.valueOf(totalNumber));
	}

	/**
	 * Applies the Iterator to the root shape.
	 */
	public void applyIterator() {

		int totalNumber = 0;

		// YOUR CODE HERE
		// Place the code to count the shapes using the Iterator.
		// END OF YOUR CODE

		ShapeApplet.setOutputText(
			"Number of shapes: " + String.valueOf(totalNumber));
	}

	/**
	 * Marks the clicked shape by placing it in the class Marked.
	 *
	 * @param evt A mouse event that contains the position of the click.
	 */
	public void markClickedShape(MouseEvent evt) {

		if ((evt.getX() > getBounds().x) &&
			(evt.getX() < (getBounds().x + getBounds().width)) &&
			(evt.getY() > getBounds().y) &&
			(evt.getY() < (getBounds().y + getBounds().height))) {

			Marked.markShape(root.getMarkedShape(
				evt.getX() - getLocation().x, evt.getY() - 24 - getLocation().y));
		}
	}

	/**
	 * Adds some test shapes. Useful when testing.
	 *
	 * @param number The number of layers of shapes.
	 */
	public void makeTestShapes(int number) {

		AbstractShape shape = root;
		ShapeFactory factory = ShapeFactory.instance();

		for (int i = 0; i < number; i++) {
			factory.createCircle();
			factory.createTriangle();
			factory.createRectangle();
			Marked.markShape(factory.createSquareProxy());
			factory.createCircle();
			factory.createTriangle();
			factory.createRectangle();
			Marked.markShape(shape);
			Marked.markShape(shape = factory.createSquare());
		}

		Marked.markShape(root);
	}
}
