package lab;

public class MoveCommand extends AbstractCommand {
	private LadyBird bug;
	private int newX, newY, oldX, oldY;
	
	public MoveCommand(int x, int y) {
		newX = x;
		newY = y;
	}

	@Override
	public void Execute() {
		bug = LadyBirdManager.instance().getMarkedLadyBird();
		oldX = bug.getX();
		oldY = bug.getY();
		
		bug.setGoal(newX, newY);
		bug.setState(bug.getTurningState());
	}

	@Override
	public void Unexecute() {
		bug.setGoal(oldX, oldY);
		bug.setState(bug.getTurningState());
	}

}