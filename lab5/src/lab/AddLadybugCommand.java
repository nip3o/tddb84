package lab;

public class AddLadybugCommand extends AbstractCommand {
	private LadyBird bug; // Oh noes, there is a bug in our code...! :)
	
	@Override
	public void Execute() {
		bug = LadyBirdManager.instance().createLadyBird();	
	}

	@Override
	public void Unexecute() {
		LadyBirdManager.instance().removeLadyBird(bug);
	}

}
