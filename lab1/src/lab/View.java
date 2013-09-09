package lab;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

// YOUR CODE HERE
// The View plays a particular role in the Subject-Observer relationship.
// Therefore, the View will implement a particular interface.
class View extends Canvas implements Observer {
// END OF YOUR CODE

	private static final long serialVersionUID = 1L;
	protected int width, height;
	protected static final int DFLT_WIDTH  = 300;
	protected static final int DFLT_HEIGHT = 300;
	protected static final int R = 10;
	protected Trajectory trajectory1, trajectory2;

	View(Model model) {

		width = DFLT_WIDTH;
		height = DFLT_HEIGHT;
		trajectory1 = new Trajectory();
		trajectory2 = new Trajectory();

		// YOUR CODE HERE
		// Let the Subject know that we want to observe it.
		
		model.addObserver(this);
		
		// END OF YOUR CODE
	}

	View(Model model, int width, int height) {

		this.width = width;
		this.height = height;
		trajectory1 = new Trajectory();
		trajectory2 = new Trajectory();

		// YOUR CODE HERE
		// Let the Subject know that we want to observe it.
		
		model.addObserver(this);
		
		// END OF YOUR CODE
	}

	protected Point transformCoord(Coord c) {

		return new Point(
			(int)(c.x + width / 2),
			(int)(c.y + height / 2));
	}

	protected void handleNewState(ModelState s) {

		Point c0 = transformCoord(s.o1);
		Point c1 = transformCoord(s.o2);

		trajectory1.trim(c0);
		trajectory1.add(c0);

		trajectory2.add(c1);
		trajectory2.trim(c1);
		repaint();
	}

	// YOUR CODE HERE
	// Put here the method that implements the interface that
	// you added when you declared the class View.
	// This method will just call handleNewState.

	@Override
	public void update(Observable o, Object arg) {
		this.handleNewState((ModelState) arg);
	}
	
	// END OF YOUR CODE

	private void paint1(Graphics g, Trajectory trajectory) {

		Enumeration<Point> e = trajectory.elements();
		Point o = null, n;

		if (e.hasMoreElements())
			o = e.nextElement();

		while (e.hasMoreElements()) {
			n = e.nextElement();
			g.drawLine(o.x, o.y, n.x, n.y);
			o = n;
		}

		if (o != null)
			g.fillOval(o.x - R / 2, o.y - R / 2, R, R);
	}

	public void paint(Graphics g) {

		g.setColor(Color.RED);
		paint1(g, trajectory1);
		g.setColor(Color.BLUE);
		paint1(g, trajectory2);

	}

	public Dimension getSize() {

		return new Dimension(width, height);
	}

	public void update(Graphics g) {

		Dimension d = getSize();
		Image im = createImage(d.width, d.height);
		Graphics g2 = im.getGraphics();

		paint(g2);
		g.drawImage(im, 0, 0, this);
	}
}
