package lab;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;

/**
 * Horse is an automatically moving object.
 *
 * @author Peter Sunnergen
 */
public class Horse extends MovingObject implements Runnable {

	private int halfHorseSize;
	private int horseSpeed = 400;
	private StableApplet applet;
	private static int horseCounter;
	private int horseType;
	private boolean running = true;

	public Horse(StableApplet a) {

		applet = a;
		horseType = horseCounter;
		horseCounter++;

		do {
			currentX = (int)Math.round(Math.random() * 3);
			currentY = (int)Math.round(Math.random() * 3);
		} while (null == Stable.instance().getRoom(currentX, currentY) ||
			Person.instance().sameAs(currentX, currentY));
	}

	/**
	 * Draws the horse. There is a number if different type of horses.
	 *
	 * @param g Graphics.
	 */
	public void paint(Graphics g) {

		Images images = Images.instance();
		Stable stable = Stable.instance();

		halfHorseSize = (int)Math.round(Room.getSize() * 0.30);

		switch (horseType) {
		case 0:
			g.drawImage(images.getGunnarImage(2 * halfHorseSize),
				stable.getRoom(currentX, currentY).getOffsetX() + Room.getSize() / 2 - halfHorseSize,
				stable.getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfHorseSize, null);
			break;

		case 1:
			g.drawImage(images.getRayaImage(2 * halfHorseSize),
				stable.getRoom(currentX, currentY).getOffsetX() + Room.getSize() / 2 - halfHorseSize,
				stable.getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfHorseSize, null);
			break;

		case 2:
			g.drawImage(images.getGulliverImage(2 * halfHorseSize),
				stable.getRoom(currentX, currentY).getOffsetX() + Room.getSize() / 2 - halfHorseSize,
				stable.getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfHorseSize, null);
			break;

		default:
			g.setColor(Color.gray);
			g.fillRoundRect(stable.getRoom(currentX, currentY).getOffsetX() + Room.getSize() / 2 - halfHorseSize,
				stable.getRoom(currentX, currentY).getOffsetY() + Room.getSize() / 2 - halfHorseSize,
				2 * halfHorseSize, 2 * halfHorseSize, 20, 20);
			break;
		}
	}

	/**
	 * Makes the horse go one step in a direction or turn to that direction.
	 *
	 * @param dir The direction.
	 */
	public void go(int dir) {

		if (facing == dir)
			Stable.instance().getRoom(currentX, currentY).getSide(dir).enter(this);
		else
			facing = dir;

		applet.repaint();
	}

	public void operateDoor() {

		MapSite m = Stable.instance().getRoom(currentX, currentY).getSide(facing);

		if ((m instanceof BoxDoor) && (!((BoxDoor)m).isOpen()))
			((BoxDoor)m).operateDoor();
	}

	/**
	 * Start a continues loop that moves the horse around.
	 */
	public void run() {

		Stable stable = Stable.instance();

		while (running) {
			try {
				Thread.sleep((int)(Math.random() * horseSpeed));
			} catch (InterruptedException e) {
			}

			switch ((int)Math.round(Math.random() * 3)) {
			case 0:
				facing = KeyEvent.VK_UP;
				break;

			case 1:
				facing = KeyEvent.VK_RIGHT;
				break;

			case 2:
				facing = KeyEvent.VK_DOWN;
				break;

			case 3:
				facing = KeyEvent.VK_LEFT;
				break;
			}

			int X = currentX;
			int Y = currentY;

			Room room = stable.getRoom(X, Y);
			if (room == null) continue;

			MapSite site = room.getSide(facing);
			if (site == null) continue;

			if (site instanceof BoxDoor) {
				BoxDoor b = (BoxDoor)site;

				if (!b.isOpen()) b.operateDoor();

				switch (facing) {
				case KeyEvent.VK_UP:
					Y--;
					break;

				case KeyEvent.VK_RIGHT:
					X++;
					break;

				case KeyEvent.VK_DOWN:
					Y++;
					break;

				case KeyEvent.VK_LEFT:
					X--;
					break;
				}
			} else if (site instanceof Door) {
				Door d = (Door)site;

				if (d.isOpen()) {
					switch (facing) {
					case KeyEvent.VK_UP:
						Y--;
						break;

					case KeyEvent.VK_RIGHT:
						X++;
						break;

					case KeyEvent.VK_DOWN:
						Y++;
						break;

					case KeyEvent.VK_LEFT:
						X--;
						break;
					}
				}
			} else if (site instanceof Room) {
				Room r = (Room)site;
				X = (r.getOffsetX() - 25) / Room.getSize();
				Y = (r.getOffsetY() - 25) / Room.getSize();
			}

			// Only go into if the person does not stand in the new room.
			if (!Person.instance().sameAs(X, Y)) go(facing);
		}
	}

	public void stop() {

		running = false;
	}
}
