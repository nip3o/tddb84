package lab;

/**
 * The superclass for all commands.
 *
 * @author Peter Sunnergren
 */
public abstract class AbstractCommand {

	/**
	 * Executes the command.
	 */
	public abstract void Execute();

	/**
	 * Undoes the command. Make sure to have saved enough
	 * in Execute() to be able to undo.
	 */
	public abstract void Unexecute();
}
