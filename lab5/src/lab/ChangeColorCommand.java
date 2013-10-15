package lab;

import java.awt.Color;

public class ChangeColorCommand extends AbstractCommand {
	private LadyBird bug;
	private Color oldColor, oldDotColor;
	
	@Override
	public void Execute() {
		bug = LadyBirdManager.instance().getMarkedLadyBird();
		oldColor = bug.getColor();
		oldDotColor = bug.getDotColor();
		bug.setColors(Color.YELLOW, Color.BLACK);
	}

	@Override
	public void Unexecute() {
		bug.setColors(oldColor, oldDotColor);
	}

}