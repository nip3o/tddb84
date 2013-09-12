package lab;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Is class represents a wall in the stable.
 * If someone tries to enter a wall nothing happens
 *
 * @author Peter Sunnergren
 */
public class Wall extends NullSide {

	private int wallSize;

	public Wall (int dir) {

		super(dir);
	}

	public Wall(Wall wall) {

		super(wall.direction);
	}

	public MapSite clone() {

		return new Wall(this);
	}

	/**
	 * If someone tries to enter a wall nothing happends.
	 */
	public void enter(MovingObject p) {

		// Nothing happens.
	}

	/**
	 * Draws the wall.
	 *
	 * @param g The graphics to draw into
	 * @param r A room that is used to calculate were to draw the wall.
	 */
	public void paint(Graphics g, Room r) {

		Images images = Images.instance();

		wallSize = (int)Math.round(Room.getSize() * 0.15);

		switch (direction) {
		case KeyEvent.VK_UP :
			g.drawImage(images.getWallUpImage(Room.getSize() - 2 * wallSize, wallSize),
				r.getOffsetX() + wallSize, r.getOffsetY(), null);
			break;

		case KeyEvent.VK_RIGHT:
			g.drawImage(images.getWallRightImage(wallSize, Room.getSize() - 2 * wallSize),
				r.getOffsetX() + Room.getSize() - wallSize, r.getOffsetY() + wallSize, null);
			break;

		case KeyEvent.VK_DOWN:
			g.drawImage(images.getWallDownImage(Room.getSize() - 2 * wallSize, wallSize),
					r.getOffsetX() + wallSize, r.getOffsetY() + Room.getSize() - wallSize, null);
			break;

		case KeyEvent.VK_LEFT:
			g.drawImage(images.getWallLeftImage(wallSize, Room.getSize() - 2 * wallSize),
					r.getOffsetX(), r.getOffsetY() + wallSize, null);
			break;
		}
	}
}
