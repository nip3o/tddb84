package lab;

/**
 * The abstract super class to everything used to build the stable.
 *
 * @author Peter Sunnergren
 */
public abstract class MapSite {
	/**
	 * Makes a movingobject enter the site.
	 *
	 * @param p Entering movingobject.
	 */
	protected abstract void enter(MovingObject p);

	/**
	 * Gets the direction to the site from inside a specific room.
	 *
	 * @param room The room.
	 * @return A direction.
	 */
	protected abstract int getDirection(Room room);

	/**
	 * Clones the site.
	 *
	 * @return A copy of the site.
	 */
	public abstract MapSite clone();
}
