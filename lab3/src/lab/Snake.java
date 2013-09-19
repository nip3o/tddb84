package lab;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is what make the game tick. It updates the position
 * according to the controller.
 *
 * @author Peter Sunnergren
 */

public class Snake implements Runnable {

	/**
	 * A lock to prevent concurrent access to body and bonus.
	 */
	public final ReentrantLock lock;
	/**
	 * The size of an element in the snakes body
	 */
	public static int boxSize = 25;
	/**
	 * The distance between to elements in the snakes body
	 */
	public static int borderSize = 2;

	private int speed = 200;
	private boolean outside = false;
	private ArrayList<Rectangle> body;
	private ArrayList<Rectangle> bonus;
	private Renderer renderer;
	private Controller controller;

	/**
	 * Initializes the snake.
	 */
	public Snake(int startSize, int startX, int startY, int startDir) {

		lock = new ReentrantLock();

		setOriginalGraphics();

		body = new ArrayList<Rectangle>();
		bonus = new ArrayList<Rectangle>();

		setDemoControll();

		controller.setDirection(startDir);

		for (int i = 0; i < startSize; i++) {
			body.add(new Rectangle(startX + boxSize * i, startY,
				boxSize - borderSize, boxSize - borderSize));
		}
	}

	/**
	 * Overloads run() in the Runnable interface.
	 * Updates the position of the snake and check whether
	 * it is outside or takes a bonus. Also randomizes bonuses
	 * randomly in time.
	 */
	public void run() {

		while (true) {
			outside = false;
			int eatenCount = 0;

			lock.lock();

			controller.step(body);

			Iterator<Rectangle> bodyIterator = body.iterator();
			while (bodyIterator.hasNext()) {
				Rectangle r = bodyIterator.next();

				if (r.x < 0 || r.y < 0 ||
					r.x > GamePanel.getDimension().width ||
					r.y > GamePanel.getDimension().height) {

					outside = true;
				}

				Iterator<Rectangle> bonusIterator = bonus.iterator();
				while (bonusIterator.hasNext()) {
					if (!r.intersects(bonusIterator.next())) continue;
					bonusIterator.remove();
					eatenCount++;
				}
			}

			for (int i = 0; i < eatenCount; i++)
				body.add(new Rectangle());

			if (bonus.size() < 10 && Math.random() < 0.05) {
				bonus.add(new Rectangle(
					(int)Math.round(Math.random() * (400 - boxSize)),
					(int)Math.round(Math.random() * (400 - boxSize)),
					boxSize, boxSize));
			}

			lock.unlock();

			GamePanel.rePaint();

			try {
				Thread.sleep(speed);
			}
			catch (InterruptedException e) {
				System.out.println("Snake stopped");
			}
		}
	}

	/**
	 * Uses the current graphics to draw the background,
	 * snake and bonuses on the screen
	 */
	public void paint() {

		lock.lock();

		renderer.putBackground();
		renderer.putBody(body);
		if (outside) renderer.outside();
		renderer.putBonus(bonus);

		lock.unlock();
	}

	/**
	 * Turns the snake to a direction.
	 */
	public void turn(int dir) {

		controller.setDirection(dir);
	}

	public AbstractList<Rectangle> getBonus() {

		return bonus;
	}

	/**
	 * Sets that the snake should be controlled by the keyboard.
	 */
	public void setKeyControll() {

		int direction;

		if (controller == null)
			direction = KeyEvent.VK_LEFT;
		else
			direction = controller.getDirection();

		// YOUR CODE HERE
		// Create the new controller, set the old direction to
		// the new controller.
	}

	/**
	 * Set the snake in demo mode.
	 */
	public void setDemoControll() {

		int dir;
		if (controller == null) dir = KeyEvent.VK_LEFT;
		else dir = controller.getDirection();
		controller = new ControllerDemo(this);
		controller.setDirection(dir);
	}

	/**
	 * Sets that the original graphics are used to draw the snake.
	 */
	public void setOriginalGraphics() {

		if (renderer != null) renderer.stop();
		renderer = new GraphicsOriginal();
		renderer.setSnakeColor(Color.blue);
	}

	/**
	 * Sets that the "3D" graphics are used.
	 * This method should be implemented during the labs.
	 */
	public void set3dGraphics() {

		if (renderer != null) renderer.stop();

		// YOUR CODE HERE
		// Create the new renderer.
		// Initialize the color of the snake.
		// Otherwise, it is null and you'll get a NullPointerException.
	}

	/**
	 * Sets that the package graphics are used.
	 * This method should be implemented during the labs.
	 */
	public void setABCGraphics() {

		if (renderer != null) renderer.stop();

		// YOUR CODE HERE
		// Create the new renderer.
		// Initialize the color of the snake.
		// Otherwise, it is null and you'll get a NullPointerException.
	}

	/**
	 * Sets that the graphics with shadows are used.
	 * This method should be implemented during the labs.
	 */
	public void setShadowGraphics() {

		// YOUR CODE HERE
		// Create the new renderer.
		// Initialize the color of the snake.
		// Otherwise, it is null and you'll get a NullPointerException.
	}
}
