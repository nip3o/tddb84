package lab;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Person is the moving object controlled by the user using the arrow keys.
 *
 * @author Peter Sunnergren
 */
public class Person extends MovingObject {

	private static Person instance;
	private int halfPersonSize;

	private Person () {

		reset();
	}

	/**
	 * Gets the instance of the person
	 *
	 * @return The singlton instance.
	 */
	public static Person instance() {

		if (null != instance) return instance;

		instance = new Person();
		return instance;
	}

	/**
	 * Draws the person.
	 *
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {

		Room room = Stable.instance().getRoom(currentX, currentY);
		if (room == null) return;

		Images images = Images.instance();

		halfPersonSize = (int)Math.round(Room.getSize() * 0.35);

		switch (facing) {
		case KeyEvent.VK_UP:
			g.drawImage(images.getPersonUpImage(2 * halfPersonSize), room.getOffsetX()  + Room.getSize() / 2 - halfPersonSize,
				room.getOffsetY() + Room.getSize() / 2 - halfPersonSize, null);
    		break;

    	case KeyEvent.VK_RIGHT:
			g.drawImage(images.getPersonRightImage(2 * halfPersonSize), room.getOffsetX()  + Room.getSize() / 2 - halfPersonSize,
				room.getOffsetY() + Room.getSize() / 2 - halfPersonSize, null);
    		break;

    	case KeyEvent.VK_DOWN:
			g.drawImage(images.getPersonDownImage(2 * halfPersonSize), room.getOffsetX()  + Room.getSize() / 2 - halfPersonSize,
				room.getOffsetY() + Room.getSize() / 2 - halfPersonSize, null);
    		break;

    	case KeyEvent.VK_LEFT:
			g.drawImage(images.getPersonLeftImage(2 * halfPersonSize), room.getOffsetX()  + Room.getSize() / 2 - halfPersonSize,
				room.getOffsetY() + Room.getSize() / 2 - halfPersonSize, null);
    		break;
		}
	}

	/**
	 * Makes the person go one step in a direction or turn to that direction.
	 *
	 * @param dir The direction.
	 */
	public void go(int dir) {

		if (facing == dir) {
			Room room = Stable.instance().getRoom(currentX, currentY);
			if (room == null) return;
			room.getSide(dir).enter(this);
		} else {
			facing = dir;
		}
	}

	/**
	 * Makes the person operate the door in front of her, if she
	 * is not facing a door of any kind nothing happens.
	 */
	public void operateDoor() {

		Room room = Stable.instance().getRoom(currentX, currentY);
		if (room == null) return;

		MapSite m = room.getSide(facing);

		if (Door.class == m.getClass() || BoxDoor.class == m.getClass())
			((Door)m).operateDoor();
	}

	public void reset() {

		Room room = Stable.instance().getRandomRoom();
		if (room == null) return;

		currentX = room.getX();
		currentY = room.getY();
		facing = KeyEvent.VK_RIGHT;
	}
}
