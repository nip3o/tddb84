package lab;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class Mediator implements Observer {
	private Vector<LadyBird> bugs;
	
	public Mediator(Vector<LadyBird> bugs) {
		this.bugs = bugs;
	}
	
	public void checkCollissions(LadyBird bug) {
		for (LadyBird other : bugs) {
			if(!other.equals(bug)) {
				bug.collide(other);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		LadyBird metabug = (LadyBird) o;
		
		for (LadyBird bug : bugs) {
			bug.deleteObserver(this);
			
			bug.setColors(metabug.getColor(), metabug.getDotColor());
			bug.setSize(metabug.getSize());
			
			bug.addObserver(this);
		}
	}

}
