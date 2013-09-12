package lab;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;


/**
 * This is the class where the students should make changes.
 * Does the double buffering of the graphics.
 *
 * @author Peter Sunnergren
 */
public class StablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static Image offscreen;
	private static Graphics buffer;
	private Dimension dimension;
	private ArrayList<Horse> horses;

	public StablePanel(StableApplet a) {

		dimension = new Dimension(400, 400);
		setMinimumSize(dimension);
		offscreen = a.createImage((int)dimension.getWidth(), (int)dimension.getHeight());
		buffer = offscreen.getGraphics();
		horses = new ArrayList<Horse>();
	}

	/**
	 * This method draws the stable, the person and the horses.
	 */
	public void paint(Graphics g) {

		buffer.clearRect(0, 0, dimension.width, dimension.height);
		Stable.instance().paint(buffer);

		Iterator<Horse> iterator = horses.iterator();

		while (iterator.hasNext())
			iterator.next().paint(buffer);

		Person.instance().paint(buffer);

		g.drawImage(offscreen, 0, 0, this);
	}

	/**
	 * This is called when the user presses 'h' and adds another horse.
	 */
	public void addHorse(StableApplet a) {

		Horse horse1 = new Horse(a);
		horses.add(horse1);
		new Thread(horse1).start();
	}

	/**
	 * Constructs the stable using the default implementation.
	 * In this method the students can see how a small stable
	 * is constructed in a nonflexible manner.
	 */
	public void defaultConstruction() {

		Room room1 = new Room(0,0);
		Room room2 = new Room(1,0);
		Room room3 = new Room(0,1);
		Room room4 = new Room(1,1);

		Door door13 = new Door(room1, room3);
		BoxDoor boxDoor24 = new BoxDoor(room2, room4);
		BoxDoor boxDoor34 = new BoxDoor(room3, room4);

		Stable.instance().addRoom(room1);
		Stable.instance().addRoom(room4);
		Stable.instance().addRoom(room3);
		Stable.instance().addRoom(room2);

		room1.setSide(new Wall(KeyEvent.VK_UP));
		room1.setSide(new Wall(KeyEvent.VK_LEFT));

		room2.setSide(new Wall(KeyEvent.VK_UP));
		room2.setSide(new Wall(KeyEvent.VK_RIGHT));

		room3.setSide(new Wall(KeyEvent.VK_DOWN));
		room3.setSide(new Wall(KeyEvent.VK_LEFT));

		room4.setSide(new Wall(KeyEvent.VK_RIGHT));
		room4.setSide(new Wall(KeyEvent.VK_DOWN));

		room1.setSide(room2);
		room2.setSide(room1);

		room1.setSide(door13);
		room3.setSide(door13);

		room2.setSide(boxDoor24);
		room4.setSide(boxDoor24);

		room3.setSide(boxDoor34);
		room4.setSide(boxDoor34);
	}

	/**
	 * Construction is made using an Factory.
	 */
	public void factoryConstruction() {
		StableFactory f = new StableFactory();
		
		Room room1 = f.getRoom(0,0);
		Room room2 = f.getRoom(1,0);
		Room room3 = f.getRoom(0,1);
		Room room4 = f.getRoom(1,1);

		MapSite door13 = f.getDoor(DoorChoice.door, room1, room3);
		MapSite boxDoor24 = f.getDoor(DoorChoice.boxDoor, room2, room4);
		MapSite boxDoor34 = f.getDoor(DoorChoice.boxDoor, room3, room4);

		Stable.instance().addRoom(room1);
		Stable.instance().addRoom(room4);
		Stable.instance().addRoom(room3);
		Stable.instance().addRoom(room2);

		room1.setSide(f.getWall(KeyEvent.VK_UP));
		room1.setSide(f.getWall(KeyEvent.VK_LEFT));

		room2.setSide(f.getWall(KeyEvent.VK_UP));
		room2.setSide(f.getWall(KeyEvent.VK_RIGHT));

		room3.setSide(f.getWall(KeyEvent.VK_DOWN));
		room3.setSide(f.getWall(KeyEvent.VK_LEFT));

		room4.setSide(f.getWall(KeyEvent.VK_RIGHT));
		room4.setSide(f.getWall(KeyEvent.VK_DOWN));

		room1.setSide(room2);
		room2.setSide(room1);

		room1.setSide(door13);
		room3.setSide(door13);

		room2.setSide(boxDoor24);
		room4.setSide(boxDoor24);

		room3.setSide(boxDoor34);
		room4.setSide(boxDoor34);
	}

	/**
	 * Construction using the Builder pattern.
	 */
	public void builderConstruction() {
		StableBuilder b = new StableBuilder();
		
		b.addRoom(0, 0);
		b.addRoom(1, 0);
		b.addRoom(0, 1);
		b.addRoom(1, 1);
		
		b.addDoor(0, 0, 0, 1, DoorChoice.door);
		b.addDoor(1, 0, 1, 1, DoorChoice.boxDoor);
		b.addDoor(0, 1, 1, 1, DoorChoice.boxDoor);
		
		b.addOuterDoors();
	}

	/**
	 * Construction using prototypes.
	 */
	public void prototypeConstruction() {

		// YOUR CODE HERE
	}

	public void deconstruct() {

		Iterator<Horse> iterator = horses.iterator();

		while(iterator.hasNext())
			iterator.next().stop();

		horses.clear();
		Stable.instance().deconstruct();
	}

	// YOUR CODE HERE
	// May be something else is missing?
}
