package lab;

public class LadybugStateTurning extends AbstractState {
	
	@Override
	public void nextAction(LadyBird bird) {
		if(bird.turn()) {
			bird.setState(bird.getGoingState());
		}
		nextState.nextAction(bird);
	}
}
