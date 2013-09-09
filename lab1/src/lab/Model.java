package lab;

import java.util.Observable;

// YOUR CODE HERE
// Replace "extends Object" with something more suitable.
class Model extends Observable implements Runnable {
// END OF YOUR CODE

	protected Coord x0, v0, x1, v1;
	protected double m0, m1;
	protected static final double h = 0.001;
	protected static final int STEPS = 5000;
	private static final long SLEEP_INT = 10;
	protected boolean suspended;
	protected boolean running;

	Model(Coord x0, Coord v0, Coord x1, Coord v1, double m0, double m1) {

		this.x0 = x0;
		this.v0 = v0;
		this.x1 = x1;
		this.v1 = v1;
		this.m0 = m0;
		this.m1 = m1;

		running = true;
		suspended = true;
	}

	public void run() {

		double[] x00 = new double[3];
		double[] x10 = new double[3];
		double[] v00 = new double[3];
		double[] v10 = new double[3];
		double[] x0  = new double[3];
		double[] x1  = new double[3];
		double[] v0  = new double[3];
		double[] v1  = new double[3];
		double t;
		int k;

		t = 0.0;
		x00[0] = this.x0.x;
		x00[1] = this.x0.y;
		x00[2] = this.x0.z;
		v00[0] = this.v0.x;
		v00[1] = this.v0.y;
		v00[2] = this.v0.z;
		x10[0] = this.x1.x;
		x10[1] = this.x1.y;
		x10[2] = this.x1.z;
		v10[0] = this.v1.x;
		v10[1] = this.v1.y;
		v10[2] = this.v1.z;

		ModelState newState = new ModelState(
			new Coord(x00[0], x00[1]), new Coord(x10[0], x10[1]));

		// YOUR CODE HERE
		// The model has a new state. What does it do with it?
		this.setChanged();
		this.notifyObservers(newState);
		
		// END OF YOUR CODE

		while (running) {
			synchronized (this) {
				while (suspended)
					try {
						wait();
					} catch (InterruptedException e) {
					}
			}

			for (k = 0; k < STEPS / 2; k++, t += 2.0 * h) {
				DifEqSolver.solve(x00, v00, x10, v10, m0, m1, t, h, x0, v0, x1, v1);
				DifEqSolver.solve(x0, v0, x1, v1, m0, m1, t + h, h, x00, v00, x10, v10);
			}

			newState = new ModelState(new Coord(x00[0], x00[1]),
					new Coord(x10[0], x10[1]));

			// YOUR CODE HERE
			// The model has a new state. What does it do with it?
			this.setChanged();
			this.notifyObservers(newState);
			
			// END OF YOUR CODE

			try {
				// Just to lose some time so that the animation is not too fast.
				Thread.sleep(SLEEP_INT);
			} catch (InterruptedException e) {
			}
		}
	}

	synchronized void suspend() {

		suspended = true;
	}

	synchronized void resume() {

		suspended = false;
		notify();
	}

	void stop() {

		running = false;
	}

	boolean isSuspended() {

		return suspended;
	}
}
