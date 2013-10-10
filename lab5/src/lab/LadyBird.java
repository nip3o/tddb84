package lab;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Observable;

/**
 * The class representing a ladybird.
 *
 * @author Peter Sunnergren
 */
public class LadyBird extends Observable {

	private double angle = 0.0;
	private int x;
	private int y;
	private int goalX;
	private int goalY;
	private double turnAngle = Math.PI / 16;

	private AbstractState state;
	private LadyBirdSettings settings;
	
	private LadybugStateTurning stateTurning;
	private LadybugStateStanding stateStanding;
	private LadybugStateGoing stateGoing;

	public LadyBird() {		
		settings = new LadyBirdSettings(31, Color.red, Color.black);

		x = (int)Math.round(Math.random() * 400);
		y = (int)Math.round(Math.random() * 400);
		angle = Math.round(Math.random() * 2 * Math.PI);
		state = new NullState();

		// YOUR CODE HERE
		// You might want to add something here.
		stateTurning = new LadybugStateTurning();
		stateStanding = new LadybugStateStanding();
		stateGoing = new LadybugStateGoing();
		// END OF YOUR CODE
	}

	// YOUR CODE HERE
	// You might want to add something here.
	public AbstractState getGoingState() {
		return stateGoing;
	}
	
	public AbstractState getTurningState() {
		return stateTurning;
	}
	
	public AbstractState getStandingState() {
		return stateStanding;
	}
	
	// END OF YOUR CODE

	/**
	 * Makes the ladybird take the next action depending
	 * on which state it is in.
	 */
	public void nextAction() {
		state.nextAction(this);
	}

	/**
	 * Draws the ladybird.
	 */
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D)g;
		int halfGoalSize = 4;

		if (0 != goalX && 0 != goalY) {
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.black);
			g2.drawLine(goalX - halfGoalSize, goalY - 2 * halfGoalSize,
				goalX - halfGoalSize, goalY + 2 * halfGoalSize);
			g2.drawLine(goalX, goalY - 2 * halfGoalSize, goalX,
				goalY + 2 * halfGoalSize);
			g2.drawLine(goalX + halfGoalSize, goalY - 2 * halfGoalSize,
				goalX + halfGoalSize, goalY + 2 * halfGoalSize);
			g2.setColor(Color.green);
			g2.fillOval(goalX - 2 * halfGoalSize, goalY  - halfGoalSize,
				4 * halfGoalSize, 2 * halfGoalSize);
		}

		g2.setColor(settings.getColor());
		g2.fillOval(x - settings.getHalfLadyBirdSize(),
			y - settings.getHalfLadyBirdSize(),
			2 * settings.getHalfLadyBirdSize(),
			2 * settings.getHalfLadyBirdSize());

		g2.setColor(settings.getDotColor());
		double a = Math.PI/(settings.getNumberOfDots() + 1);
		for (int i = 1; i <= settings.getNumberOfDots(); i++) {
			g2.fillOval(
				x + (int)(Math.round((settings.getHalfLadyBirdSize() / 1.5) *
				Math.cos(angle + i * a))) - settings.getHalfSizeOfSpot(),
				y + (int)(Math.round((settings.getHalfLadyBirdSize() / 1.5) *
				Math.sin(angle + i * a))) - settings.getHalfSizeOfSpot(),
				2 * settings.getHalfSizeOfSpot(),
				2 * settings.getHalfSizeOfSpot());

			g2.fillOval(
				x + (int)(Math.round((settings.getHalfLadyBirdSize() / 1.5) *
				Math.cos(angle - i * a))) - settings.getHalfSizeOfSpot(),
				y + (int)(Math.round((settings.getHalfLadyBirdSize() / 1.5) *
				Math.sin(angle - i * a))) - settings.getHalfSizeOfSpot(),
				2 * settings.getHalfSizeOfSpot(),
				2 * settings.getHalfSizeOfSpot());
		}
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(x - settings.getHalfLadyBirdSize() + 1,
			y - settings.getHalfLadyBirdSize() + 1,
			2 * settings.getHalfLadyBirdSize() - 1,
			2 * settings.getHalfLadyBirdSize() - 1);
		g2.drawLine(
			x + (int)(Math.round(
				settings.getHalfLadyBirdSize() * Math.cos(angle))),
			y + (int)(Math.round(
				settings.getHalfLadyBirdSize() * Math.sin(angle))),
			x - (int)(Math.round(
				settings.getHalfLadyBirdSize() * Math.cos(angle))),
			y - (int)(Math.round(
				settings.getHalfLadyBirdSize() * Math.sin(angle))));
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(
			x + (int)(Math.round(
				settings.getHalfLadyBirdSize() * Math.cos(angle))),
			y + (int)(Math.round(
				settings.getHalfLadyBirdSize() * Math.sin(angle))),
			x + (int)(Math.round(
				settings.getHalfLadyBirdSize() / 2.0 * Math.cos(angle))),
			y + (int)(Math.round(
				settings.getHalfLadyBirdSize() / 2.0 * Math.sin(angle))));
	}

	/**
	 * This method changes the ladybird's position depending on how
	 * the other ladybird interferes with this one.
	 *
	 * @param other The colliding ladybird.
	 */
	public void collide(LadyBird other) {

		if (Point2D.distance(other.getX(), other.getY(), x, y) <
			(other.getSize() + settings.getHalfLadyBirdSize())) {

			double overLapAngle = Math.atan2(other.getX() - this.x,
				other.getY() -  this.y);
			double overLap = (other.getSize() +
				settings.getHalfLadyBirdSize()) -
				Point2D.distance(this.x, this.y, other.getX(), other.getY());

			x = x - (int)(Math.round(overLap *
				Math.cos(overLapAngle - Math.PI / 2)));
			y = y + (int)(Math.round(overLap *
				Math.sin(overLapAngle - Math.PI / 2)));
		}
	}

	/**
	 * Turns the ladybird slightly towards the goal.
	 *
	 * @return True if the ladybird faces the goal.
	 */
	public boolean turn() {

		double distToGoal = Point2D.distance(x,  y, goalX, goalY);

		if (Point2D.distance(x + distToGoal*Math.cos(angle + turnAngle),
			y + distToGoal*Math.sin(angle + turnAngle), goalX, goalY) <
			Point2D.distance(x + distToGoal*Math.cos(angle - turnAngle),
			y + distToGoal*Math.sin(angle - turnAngle), goalX, goalY)) {

			angle += turnAngle;
			if (Point2D.distance(x + distToGoal*Math.cos(angle + turnAngle),
				y + distToGoal*Math.sin(angle + turnAngle), goalX, goalY) >
				Point2D.distance(x + distToGoal*Math.cos(angle),
				y + distToGoal*Math.sin(angle), goalX, goalY)) {

				return true;
			}
		} else {
			angle -= turnAngle;
			if (Point2D.distance(x + distToGoal*Math.cos(angle - turnAngle),
				y + distToGoal*Math.sin(angle - turnAngle), goalX, goalY) >
				Point2D.distance(x + distToGoal*Math.cos(angle),
				y + distToGoal*Math.sin(angle), goalX, goalY)) {

				return true;
			}
		}

		return false;
	}

	/**
	 * Makes the ladybird take one step towards the goal.
	 *
	 * @return True if the ladybird has reached the goal.
	 */
	public boolean go() {

		int newX = x + (int)(Math.round(
			settings.getStepSize() * Math.cos(angle)));
		int newY = y + (int)(Math.round(
			settings.getStepSize() * Math.sin(angle)));

		if (Point2D.distance(newX, newY,  goalX, goalY) <
			Point2D.distance(x, y, goalX, goalY)) {

			x = newX;
			y = newY;

			return false;
		}

		return true;
	}

	/**
	 * Sets the state of the ladybird.
	 */
	public void setState(AbstractState s) {

		state = s;
	}

	/**
	 * Sets the size of the ladybird, which is its radius.
	 */
	public void setSize(int size) {

		settings = new LadyBirdSettings(
			size, settings.getColor(), settings.getDotColor());

		// YOUR CODE HERE
		// You might want to add something here.
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Gets the size of the ladybird, which is its radius.
	 */
	public int getSize() {

		return settings.getHalfLadyBirdSize();
	}

	/**
	 * Gets the X coordinate.
	 */
	public int getX() {

		return x;
	}

	/**
	 * Gets the Y coordinate.
	 */
	public int getY() {

		return y;
	}

	/**
	 * Sets the color of the ladybird.
	 *
	 * @param color The color of the body.
	 * @param dotColor The color of the dots and border.
	 */
	public void setColors(Color color, Color dotColor) {

		settings = new LadyBirdSettings(
			settings.getHalfLadyBirdSize(), color, dotColor);

		// YOUR CODE HERE
		// You might want to add something here.
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Gets the color of the body.
	 */
	public Color getColor() {

		return settings.getColor();
	}

	/**
	 * Gets the color of the dots.
	 */
	public Color getDotColor() {

		return settings.getDotColor();
	}

	/**
	 * Set the goal for the ladybird.
	 *
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 */
	public void setGoal(int x, int y) {

		goalX = x;
		goalY = y;
	}
}
