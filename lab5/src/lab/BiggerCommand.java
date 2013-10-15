package lab;

public class BiggerCommand extends AbstractCommand {
	private LadyBird bug;
	private int oldSize;
	
	@Override
	public void Execute() {
		bug = LadyBirdManager.instance().getMarkedLadyBird();
		oldSize = bug.getSize();
		bug.setSize(oldSize + 1);
	}

	@Override
	public void Unexecute() {
		bug.setSize(oldSize);
	}

}