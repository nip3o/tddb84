package lab;

import java.util.Vector;
import java.awt.Point;

class Trajectory extends Vector<Point> {

	private static final long serialVersionUID = 1L;
	protected Point x;
	protected static final int SINCE_PERIOD = 10;
	protected int lastSincePeriod = 0;
	protected boolean firstUpdate = true;
	protected int pass = 0;
	protected int k = 0;

	protected boolean veryClose(Point p0, Point p1) {

		if (p1.x <= p0.x + 2 && p1.x >= p0.x - 2 &&
			p1.y <= p0.y + 2 && p1.y >= p0.y - 2)

			return true;
		else
			return false;
	}

	protected void trim(Point c) {

		if (lastSincePeriod > SINCE_PERIOD && veryClose(x, c)) {
			lastSincePeriod = 0;
			if (pass == 1) removeRange(0, k);
			else pass++;
			k = size();
			x = c;
		} else {
			lastSincePeriod++;
		}

		if (firstUpdate) {
			x = c;
			firstUpdate = false;
		}
	}
}
