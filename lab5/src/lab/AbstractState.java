package lab;

/**
 * The superclass for all states.
 *
 * @author Peter Sunnergren
 */
public abstract class AbstractState {

	protected AbstractState nextState;

	/**
	 * Sets the state that should be attained after this one.
	 */
	public void setNextState(AbstractState state) {

		nextState = state;
	}

	/**
	 * Apply the next action determined by the state to the ladybird.
	 */
	public abstract void nextAction(LadyBird bird);
}
