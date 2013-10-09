package lab;

public class RemoveLadyBirdCommand extends AbstractCommand {
	private LadyBird bug;
	
	@Override
	public void Execute() {
		LadyBirdManager manager = LadyBirdManager.instance();
		bug = manager.getMarkedLadyBird();
		manager.removeLadyBird(bug);
	}

	@Override
	public void Unexecute() {
		LadyBirdManager.instance().addLadyBird(bug);
	}
}
