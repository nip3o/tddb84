package lab;

import java.awt.Graphics;
import java.awt.event.KeyEvent;


/**
 * A door is a connection between two room that can be opened or closed.
 *
 * @author Peter Sunnergren
 */

public class Door extends MapSite {

	protected Room room1;
	protected Room room2;
	protected int halfDoorSize;
	protected int wallSize;
	public boolean opened = false;

	public Door (Room r1, Room r2) {

		halfDoorSize = Room.getSize() / 4;
		wallSize = (int)Math.round(Room.getSize() * 0.15);
		move(r1, r2);
	}

	public Door(Door door) {

		this(door.room1, door.room2);
	}

	public MapSite clone() {

		return new Door(this);
	}

	/**
	 * Gets the direction to the door form inside a room.
	 *
	 * @param r The room.
	 */
	public int getDirection(Room r) {

		if (r.getOffsetY() < otherSideFrom(r).getOffsetY())
			return KeyEvent.VK_DOWN;

		if (r.getOffsetY() > otherSideFrom(r).getOffsetY())
			return KeyEvent.VK_UP;

		if (r.getOffsetX() < otherSideFrom(r).getOffsetX())
			return KeyEvent.VK_RIGHT;

		if (r.getOffsetX() > otherSideFrom(r).getOffsetX())
			return KeyEvent.VK_LEFT;

		return -1;
	}

	/**
	 * Makes the horse or person enter the door and the room on the other side.
	 */
	public void enter(MovingObject p) {

		if (!opened) return;

		otherSideFrom(Stable.instance().getRoom(
			p.currentX, p.currentY)).enter(p);
	}

	/**
	 * Gets the room on the otherside of the door.
	 *
	 * @param r The room.
	 * @return The room on the otherside.
	 */
	protected Room otherSideFrom(Room r) {

		if (room1 == r) return room2;
		else return room1;
	}

	/**
	 * Draws the door.
	 *
	 * @param g Graphics.
	 * @param r The room that draws the door.
	 */
	public void paint(Graphics g, Room r) {

		Images images = Images.instance();

		if (r.getOffsetY() < otherSideFrom(r).getOffsetY()) {
			// Down
			g.drawImage(images.getDoorHorizontalImage(Room.getSize() - 2 * wallSize, 2 * halfDoorSize, opened),
				r.getOffsetX() + wallSize, r.getOffsetY() + Room.getSize() - halfDoorSize, null);
		}
		if (r.getOffsetY() > otherSideFrom(r).getOffsetY()) {
			// Up
			g.drawImage(images.getDoorHorizontalImage(Room.getSize() - 2 * wallSize, 2 * halfDoorSize, opened),
				r.getOffsetX() + wallSize, r.getOffsetY() - halfDoorSize, null);
		}
		if (r.getOffsetX() < otherSideFrom(r).getOffsetX()) {
			//Left
			g.drawImage(images.getDoorVerticalImage(2 * halfDoorSize, Room.getSize() - 2 * wallSize, opened),
				r.getOffsetX() + Room.getSize() - halfDoorSize, r.getOffsetY() + wallSize, null);
		}
		if (r.getOffsetX() > otherSideFrom(r).getOffsetX()) {
			//Right
			g.drawImage(images.getDoorVerticalImage(2 * halfDoorSize, Room.getSize() - 2 * wallSize, opened),
				r.getOffsetX() - halfDoorSize, r.getOffsetY() + wallSize, null);
		}
	}

	/**
	 * Moves the door to be between two rooms.
	 *
	 * @param r1 The first room.
	 * @param r2 The second room.
	 */
	public void move(Room r1, Room r2) {

		room1 = r1;
		room2 = r2;
	}

	/**
	 * Operates the door, if it is closed its opened and if it is open it is closed.
	 */
	public void operateDoor() {

		opened = !opened;
	}

	public boolean isOpen() {

		return opened;
	}
}
