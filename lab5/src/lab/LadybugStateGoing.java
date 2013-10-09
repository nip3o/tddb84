package lab;

public class LadybugStateGoing extends AbstractState {
	@Override
	public void nextAction(LadyBird bird) {
		if(bird.go()) {
			bird.setState(bird.getStandingState());
			setNextState(bird.getStandingState());
		} else {
			setNextState(this);
		}
	}
}
