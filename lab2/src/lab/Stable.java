package lab;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.Vector;

/**
 * The root class of the stable. Contains all the rooms.
 *
 * @author Peter Sunnergren
 */
public class Stable {

	private static Stable instance;
	Vector<Room> rooms;

	private Stable() {

		rooms = new Vector<Room>();
	}

	/**
	 * Returns the stable instance.
	 *
	 * @return the singleton instance
	 */
	public static Stable instance() {

		if (null != instance) return instance;

		instance = new Stable();
		return instance;
	}

	/**
	 * Adds a room to the stable
	 *
	 * @param room
	 */
	public void addRoom(Room room) {

		rooms.add(room);
	}

	/**
	 * Gets the room at a specific position.
	 *
	 * @param x The rooms X
	 * @param y The rooms Y
	 * @return The room or null if no room has specified position.
	 */
	public Room getRoom(int x, int y) {

		Iterator<Room> iterator = rooms.iterator();

		while (iterator.hasNext()){
			Room room = iterator.next();
			if (room.correctRoom(x, y)) {
				return room;
			}
		}

		return null;
	}

	/**
	 * Draws the stable.
	 *
	 * @param g graphics
	 */
	public void paint(Graphics g) {

		Iterator<Room> iterator = rooms.iterator();

		while (iterator.hasNext())
			iterator.next().paint(g);
	}

	public void deconstruct() {

		rooms.clear();
	}

	public Room getRandomRoom() {

		try {
			return rooms.elementAt((int)(Math.random() * rooms.size()));
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public Vector<Room> findAdjucentRooms(Room room) {

		Vector<Room> adjucentRooms = new Vector<Room>();

		int x = room.getX();
		int y = room.getY();

		Iterator<Room> iterator = rooms.iterator();

		while (iterator.hasNext()) {
			Room another = iterator.next();
			if (another == room) continue;

			int dx = Math.abs(x - another.getX());
			int dy = Math.abs(y - another.getY());

			if (dx == 0 && dy == 1 || dy == 0 && dx == 1)
				adjucentRooms.add(another);
		}

		return adjucentRooms;
	}
}
