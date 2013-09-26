package lab;

/**
 * A class that creates shapes according to rules about size.
 * Also adds the new shape to its parent.
 *
 * @author Peter Sunnergren
 */
public class ShapeFactory {

	private static ShapeFactory instance;

	/**
	 * Used to get the instance of the factory.
	 *
	 * @return An instance of the factory.
	 */
	public static ShapeFactory instance() {

		if (null == instance)
			instance = new ShapeFactory();
		return instance;
	}

	private AbstractSquare getMarkedParent() {

		AbstractShape marked = Marked.instance();

		if ((marked instanceof Square) ||
			(marked instanceof SquareProxy))

			return (AbstractSquare)marked;

		if ((marked instanceof Circle) ||
			(marked instanceof Triangle) ||
			(marked instanceof Rectangle))

			return marked.getParent();

		return null;
	}

	private int getMaxSize(AbstractShape s) {

		if (s instanceof Square) return 300;
		if (s instanceof SquareProxy) return 300;
		if (s instanceof Circle) return 150;
		if (s instanceof Triangle) return 150;
		if (s instanceof Rectangle) return 150;

		return 0;
	}

	private boolean isToSmall(AbstractShape s,
		double width, double height) {

		double parentScale = s.getParent().getScale();

		if ((width / parentScale == 0) || (height / parentScale == 0))
			return true;

		if (s instanceof Square)
			return ((width / parentScale < 100) || (height / parentScale < 100));

		if (s instanceof SquareProxy)
			return ((width / parentScale < 100) || (height / parentScale < 100));

		if (s instanceof Circle)
			return ((width / parentScale < 50) || (height / parentScale < 50));

		if (s instanceof Triangle)
			return ((width / parentScale < 50) || (height / parentScale < 50));

		if (s instanceof Rectangle)
			return ((width / parentScale < 50) || (height / parentScale < 50));

		return false;
	}

	/**
	 * Loops until the shape can be randomly placed somewhere.
	 *
	 * @param s A shape.
	 */
	private void create(AbstractShape s) {

		int counter = 0;
		java.awt.Rectangle r = new java.awt.Rectangle();

		AbstractSquare parent = getMarkedParent();
		s.setParent(parent);

		AbstractShape sibling = null;
		if (s.getParent().hasChildren()) {
			sibling = s.getParent().getLastChild();
			s.setSibling(sibling);
		}

		do {
			counter++;
			if ((s instanceof Square) ||
				(s instanceof SquareProxy) ||
				(s instanceof Circle)) {

				r.height = r.width = (int)Math.round(Math.random() *
					getMaxSize(s) * parent.getScale());
			} else {
				r.width = (int)Math.round(Math.random() *
					getMaxSize(s) * parent.getScale());
				r.height = (int)Math.round(Math.random() *
					getMaxSize(s) * parent.getScale());
			}

			r.x = (int)Math.round(Math.random() *
				(parent.getWidth() - r.width)) + parent.getX();
			r.y = (int)Math.round(Math.random() *
				(parent.getHeight() - r.height)) + parent.getY();

			// YOUR CODE HERE
			// Remove the overlap caused by the bounding rectangle of
			// the new shape.
			// END OF YOUR CODE
		} while (isToSmall(s, r.width, r.height) && (counter < 100));

		if (!(counter < 100 )) {
			ShapeApplet.setOutputText(
				s.getClass().toString() + " can not be placed.");
			System.out.println(
				s.getClass().toString() + " can not be placed.");
		} else {
			s.setX(r.x);
			s.setY(r.y);
			s.setWidth(r.width);
			s.setHeight(r.height);
			s.setScale((s.getWidth() / 400.0) * parent.getScale());

			if (s instanceof AbstractSquare)
				((AbstractSquare)s).setDepth(((AbstractSquare)parent).getDepth() + 1);

			parent.addChild(s);
		}
	}

	/**
	 * Creates a square.
	 */
	public Square createSquare() {

		Square square = new Square();
		create(square);

		return square;
	}
	/**
	 * Creates a square proxy.
	 */
	public SquareProxy createSquareProxy() {

		SquareProxy proxy = new SquareProxy();
		create(proxy);

		return proxy;
	}

	/**
	 * Creates a circle.
	 */
	public void createCircle() {

		create(new Circle());
	}

	/**
	 * Create a triangle.
	 */
	public void createTriangle() {

		create(new Triangle());
	}

	/**
	 * Creates a rectangle.
	 */
	public void createRectangle () {

		create(new Rectangle());
	}
}
