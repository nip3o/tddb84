package lab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JApplet;

/**
 * This is the class that displays the farm of ladybird on the screen.
 * It does double buffering and can be used as an invoker.
 *
 * @author Peter Sunnergren
 */
public class FarmApplet extends JApplet implements MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private Vector<AbstractCommand> commands;
	private Graphics offScreenGraphics;
	private Image offScreenImage;

	public void init() {

		this.setSize(400, 400);

		commands = new Vector<AbstractCommand>();

		offScreenImage = createImage(getWidth(), getHeight());
		offScreenGraphics = offScreenImage.getGraphics();

		LadyBirdManager.setApplet(this);
		LadyBirdManager.instance().start();

		addMouseListener(this);
		addKeyListener(this);

		setFocusable(true);
		requestFocus();
	}

	/**
	 * Draws the farm.
	 */
	public void paint(Graphics g) {

		super.paint(offScreenGraphics);

		offScreenGraphics.setColor(new Color(189, 183, 107));
		offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());

		LadyBirdManager.instance().paint(offScreenGraphics);
		g.drawImage(offScreenImage, 0, 0, this);
	}

	private void addCommand(AbstractCommand c) {

		commands.add(c);
		c.Execute();
	}

	private void undoCommand() {

		if (commands.isEmpty()) return;

		commands.lastElement().Unexecute();
		commands.removeElement(commands.lastElement());
	}

	/**
	 * If the mouse is clicked on a ladybird, the ladybird gets selected.
	 * If the mouse is clicked on the canvas, and there is a ladybird
	 * selected, the ladybird move to the clicked position.
	 */
	public void mousePressed(MouseEvent evt) {

		LadyBirdManager manager = LadyBirdManager.instance();

		if (manager.getMarkedLadyBird() == null) {
			manager.markLadyBirdAt(evt.getX(), evt.getY());
		} else {
			if (manager.isLadyBirdAt(evt.getX(), evt.getY())) {
				manager.markLadyBirdAt(evt.getX(), evt.getY());
			} else {
				// YOUR CODE HERE
				// Write the code to move the marked ladybird.
				LadyBird bug = manager.getMarkedLadyBird();
				bug.setGoal(evt.getX(), evt.getY());
				bug.setState(bug.getTurningState());
				// END OF YOUR CODE
			}
		}

		repaint();
	}

	/**
	 * Creates commands depending on which key was pressed.
	 */
	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyChar()) {
		case 'a':
			// YOUR CODE HERE
			// Write the code to add a new ladybird.
			this.addCommand(new AddLadybugCommand());
			// END OF YOUR CODE
			break;

		case 'r':
			// YOUR CODE HERE
			this.addCommand(new RemoveLadyBirdCommand());
			// END OF YOUR CODE
			break;

		case 'b':
			// YOUR CODE HERE
			// Write the code to make the marked ladybird bigger.
			// END OF YOUR CODE
			break;

		case 's':
			// YOUR CODE HERE
			// Write the code to make the marked ladybird smaller.
			// END OF YOUR CODE
			break;

		case 'c':
			// YOUR CODE HERE
			// Write the code to change to the color of the marked ladybird.
			// END OF YOUR CODE
			break;

		case 'u':
			// YOUR CODE HERE
			// Write the code to undo the last command.
			this.undoCommand();
			// END OF YOUR CODE
			break;
		}

		repaint();
	}

	/**
	 * Not used but required by the KeyListener interface.
	 */
	public void keyTyped(KeyEvent evt) {

	}

	/**
	 * Not used but required by the KeyListener interface.
	 */
	public void keyReleased(KeyEvent evt) {

	}

	/**
	 * Not used but required by the MouseListener interface.
	 */
	public void mouseReleased(MouseEvent evt) {

	}

	/**
	 * Not used but required by the MouseListener interface.
	 */
	public void mouseClicked(MouseEvent evt) {

	}

	/**
	 * Not used but required by the MouseListener interface.
	 */
	public void mouseEntered(MouseEvent evt) {

	}

	/**
	 * Not used but required by the MouseListener interface.
	 */
	public void mouseExited(MouseEvent evt) {

	}
}
