package lab;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Contains the settings of a ladybird.
 *
 * @author Peter Sunnergren
 */
public class LadyBirdSettings {

	private int halfLadyBirdSize;
	private int halfSizeOfSpot;
	private int stepSize;
	private int numberOfDots;
	private Color color;
	private Color dotColor;

	/**
	 * Calculates and sets values depending on the input.
	 *
	 * @param halfLadyBirdSize The radius of the ladybird.
	 * @param color The body color.
	 * @param dotColor The color of the dots and lines.
	 */
	public LadyBirdSettings(int halfLadyBirdSize,
		Color color, Color dotColor) {

		this.halfLadyBirdSize = halfLadyBirdSize;
		this.numberOfDots = halfLadyBirdSize / 10;
		this.halfSizeOfSpot = (int)(Math.round(Point2D.distance(
			(halfLadyBirdSize / 1.5) *
				Math.cos(Math.PI / (numberOfDots + 1)),
			(halfLadyBirdSize / 1.5) *
				Math.sin(Math.PI / (numberOfDots + 1)),
			(halfLadyBirdSize / 1.5) *
				Math.cos(Math.PI / (numberOfDots + 1) * 2),
			(halfLadyBirdSize / 1.5) *
				Math.sin(Math.PI / (numberOfDots + 1) * 2))) / 2.2);
		this.stepSize = halfLadyBirdSize / 3;
		this.color = color;
		this.dotColor = dotColor;
	}

	public LadyBirdSettings(LadyBirdSettings settings) {

		this(settings.halfLadyBirdSize,
			settings.color, settings.dotColor);
	}

	/**
	 * Gets the color.
	 */
	public Color getColor() {

		return color;
	}

	/**
	 * Gets the color of the dots.
	 */
	public Color getDotColor() {

		return dotColor;
	}

	/**
	 * Gets the ladybird radius.
	 */
	public int getHalfLadyBirdSize() {

		return halfLadyBirdSize;
	}

	/**
	 * Gets the spot radius.
	 */
	public int getHalfSizeOfSpot() {

		return halfSizeOfSpot;
	}

	/**
	 * Gets the number of dots on a ladybird.
	 */
	public int getNumberOfDots() {

		return numberOfDots;
	}

	/**
	 * Gets the size of each step.
	 */
	public int getStepSize() {

		return stepSize;
	}
}
