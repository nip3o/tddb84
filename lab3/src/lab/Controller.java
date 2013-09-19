package lab;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * An interface to object that can control the game.
 *
 * @author Peter Sunnergren
 */
public interface Controller {

	/**
	 * Updates the body of the snake at a time step
	 */
	public void step(ArrayList<Rectangle> body);

	/**
	 * Gets the current direction.
	 */
	public int getDirection();

	/**
	 * Sets the moving direction of the snake.
	 */
	public void setDirection(int dir);
}
