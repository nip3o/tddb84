package lab;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A room class that holds the walls or doors.
 *
 * @author Peter Sunnergren
 */
public class Room extends MapSite {

	private ArrayList<MapSite> sides;
	private static int roomSize = 100;
	private int offsetX;
	private int offsetY;
	private int x;
	private int y;

	/**
	 * Constructor that sets all the walls to be a NullSide.
	 *
	 * @param x The X coordinate of the room.
	 * @param y The X coordinate of the room.
	 */
	public Room (int x, int y) {

		this.x = x;
		this.y = y;
		offsetX = 25 + x*roomSize;
		offsetY = 25 + y*roomSize;
		sides = new ArrayList<MapSite>();
		sides.add(new NullSide(KeyEvent.VK_DOWN));
		sides.add(new NullSide(KeyEvent.VK_UP));
		sides.add(new NullSide(KeyEvent.VK_LEFT));
		sides.add(new NullSide(KeyEvent.VK_RIGHT));
	}

	public Room(Room room) {

		this(room.x, room.y);
		Iterator<MapSite> iterator = room.sides.iterator();
		while (iterator.hasNext()) {
			setSide(iterator.next().clone());
		}
	}

	public MapSite clone() {

		return new Room(this);
	}

	/**
	 * Called when a moving object wants to enter the room,
	 * and it places the moving object in the room.
	 *
	 * @param p The moving object (Person or Horse).
	 */
	public void enter(MovingObject p) {

		p.currentX = x;
		p.currentY = y;
	}

	/**
	 * Gets the direction to this room seen from another room.
	 *
	 * @param room The other room.
	 */
	public int getDirection(Room room) {

		if (room.offsetY < this.offsetY) return KeyEvent.VK_DOWN;
		if (room.offsetY > this.offsetY) return KeyEvent.VK_UP;
		if (room.offsetX > this.offsetX) return KeyEvent.VK_LEFT;
		if (room.offsetX < this.offsetX) return KeyEvent.VK_RIGHT;

		return -1;
	}

	/**
	 * Draws the room.
	 */
	public void paint(Graphics g) {

		g.drawImage(Images.instance().getRoomImage(roomSize),
			offsetX, offsetY, null);

		Iterator<MapSite> iter = sides.iterator();

		while (iter.hasNext()) {
			MapSite o = iter.next();
			if (o instanceof Room) {}
			else if (o instanceof Wall)
				((Wall)o).paint(g, this);
			else if (o instanceof BoxDoor)
				((BoxDoor)o).paint(g, this);
			else if (o instanceof Door)
				((Door)o).paint(g, this);
		}
	}

	/**
	 * Moves the room.
	 *
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 */
	public void move(int x, int y) {

		this.x = x;
		this.y = y;
		offsetX = 25 + x * roomSize;
		offsetY = 25 + y * roomSize;
	}

	/**
	 * Sets a side of the room. If there is already
	 * a side in this direction, it is replaced.
	 *
	 * @param side The new side.
	 */
	public void setSide(MapSite side) {

		Iterator<MapSite> iter = sides.iterator();

		while (iter.hasNext()) {
			MapSite m = iter.next();
			if (side.getDirection(this) == m.getDirection(this)) {
				sides.remove(m);
				iter = sides.iterator();
			}
		}

		sides.add(side);
	}

	/**
	 * Gets the side in a given direction in the room.
	 *
	 * @param dir The direction
	 * @return The side if it exists and null otherwise.
	 */
	public MapSite getSide(int dir) {

		Iterator<MapSite> iter = sides.iterator();

		while(iter.hasNext()) {
			MapSite m = iter.next();
			if (dir == m.getDirection(this)) {
				return m;
			}
		}

		return null;
	}

	/**
	 * Gets the X offset.
	 */
	public int getOffsetX() {

		return offsetX;
	}

	/**
	 * Gets the Y offset.
	 */
	public int getOffsetY() {

		return offsetY;
	}

	/**
	 * Gets the room size.
	 */
	public static int getSize() {

		return roomSize;
	}

	/**
	 * Decides whether or not this is the correct room, which means
	 * that the room is placed at the X and Y coordinates.
	 *
	 * @param x The searched rooms X coordinate.
	 * @param y The searched rooms Y coordinate.
	 * @return True if this is the correct room and false otherwise.
	 */
	public boolean correctRoom(int x, int y) {

		return (x == this.x) && (y == this.y);
	}

	public int getX() {

		return x;
	}

	public int getY() {

		return y;
	}
}
