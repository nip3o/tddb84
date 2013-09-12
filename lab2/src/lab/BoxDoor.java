package lab;

import java.awt.Graphics;

/**
 * A boxdoor is a connection between two room.
 *
 * @author Peter Sunnergren
 */
public class BoxDoor extends Door {

	public BoxDoor (Room r1, Room r2) {

		super(r1, r2);
	}

	public BoxDoor(BoxDoor door) {

		this(door.room1, door.room2);
	}

	public MapSite clone() {

		return new BoxDoor(this);
	}

	/**
	 * Draws the box door.
	 *
	 * @param g Graphics.
	 * @param r The room that draws the box door.
	 */
	public void paint(Graphics g, Room r) {

		Images images = Images.instance();

		if (r.getOffsetY() < otherSideFrom(r).getOffsetY()) {
			// Down
			g.drawImage(images.getBoxDoorHorizontalImage(Room.getSize() - 2 * wallSize, 2 * halfDoorSize, opened),
				r.getOffsetX() + wallSize, r.getOffsetY() + Room.getSize() - halfDoorSize, null);
		}

		if (r.getOffsetY() > otherSideFrom(r).getOffsetY()) {
			// Up
			g.drawImage(images.getBoxDoorHorizontalImage(Room.getSize() - 2 * wallSize, 2 * halfDoorSize, opened),
				r.getOffsetX() + wallSize, r.getOffsetY() - halfDoorSize, null);
		}

		if (r.getOffsetX() < otherSideFrom(r).getOffsetX()) {
			// Left
			g.drawImage(images.getBoxDoorVerticalImage(2 * halfDoorSize, Room.getSize() - 2 * wallSize, opened),
				r.getOffsetX() + Room.getSize() - halfDoorSize, r.getOffsetY() + wallSize, null);
		}

		if (r.getOffsetX() > otherSideFrom(r).getOffsetX()) {
			// Right
			g.drawImage(images.getBoxDoorVerticalImage(2 * halfDoorSize, Room.getSize() - 2 * wallSize, opened),
				r.getOffsetX() - halfDoorSize, r.getOffsetY() + wallSize, null);
		}
	}
}
