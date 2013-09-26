package lab;

/**
 * An abstract class describing how an iterator looks like.
 *
 * @author Peter Sunnergren
 */
public abstract class AbstractIterator {

	/**
	 * Reset the iterator and make it point to the first item.
	 */
	public abstract void first();

	/**
	 * Make the iterator take a step to the next item in the list.
	 */
	public abstract void next();

	/**
	 * Checks if the iteration has reached the end of the list.
	 */
	public abstract boolean isDone();

	/**
	 * Returns the current item in the list.
	 */
	public abstract Object currentItem();
}
