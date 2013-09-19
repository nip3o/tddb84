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
		dimension = new Dimension(800, 600);
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
        Room room01 = new Room(0,1);
        Room room11 = new Room(1,1);
        Room room21 = new Room(2,1);
        Room room31 = new Room(3,1);
        Room room41 = new Room(4,1);
        Room room30 = new Room(3,0);
        Room room40 = new Room(4,0);
        Room room22 = new Room(2,2);
        Room room32 = new Room(3,2);
        Room room42 = new Room(4,2);
        
        Stable.instance().addRoom(room01);
        Stable.instance().addRoom(room11);
        Stable.instance().addRoom(room21);
        Stable.instance().addRoom(room31);
        Stable.instance().addRoom(room41);
        Stable.instance().addRoom(room30);
        Stable.instance().addRoom(room40);
        Stable.instance().addRoom(room22);
        Stable.instance().addRoom(room32);
        Stable.instance().addRoom(room42);
        
        // Outer walls
        room01.setSide(new Wall(KeyEvent.VK_UP));
        room01.setSide(new Wall(KeyEvent.VK_DOWN));
        room01.setSide(new Wall(KeyEvent.VK_LEFT));
        room11.setSide(new Wall(KeyEvent.VK_UP));
        room11.setSide(new Wall(KeyEvent.VK_DOWN));
        room21.setSide(new Wall(KeyEvent.VK_UP));
        room30.setSide(new Wall(KeyEvent.VK_LEFT));
        room30.setSide(new Wall(KeyEvent.VK_UP));
        room40.setSide(new Wall(KeyEvent.VK_UP));
        room40.setSide(new Wall(KeyEvent.VK_RIGHT));
        room41.setSide(new Wall(KeyEvent.VK_RIGHT));
        room42.setSide(new Wall(KeyEvent.VK_RIGHT));
        room42.setSide(new Wall(KeyEvent.VK_DOWN));
        room32.setSide(new Wall(KeyEvent.VK_DOWN));
        room22.setSide(new Wall(KeyEvent.VK_DOWN));
        room22.setSide(new Wall(KeyEvent.VK_LEFT));
        
        // Box walls
        room30.setSide(new Wall(KeyEvent.VK_RIGHT));
        room40.setSide(new Wall(KeyEvent.VK_LEFT));
        room22.setSide(new Wall(KeyEvent.VK_RIGHT));
        room32.setSide(new Wall(KeyEvent.VK_LEFT));
        room32.setSide(new Wall(KeyEvent.VK_RIGHT));
        room42.setSide(new Wall(KeyEvent.VK_LEFT));
        
        // Create doors
        Door door0111 = new Door(room01, room11);      
        BoxDoor door2122 = new BoxDoor(room21, room22);
        BoxDoor door3132 = new BoxDoor(room31, room32);
        BoxDoor door3130 = new BoxDoor(room31, room30);
        BoxDoor door4142 = new BoxDoor(room41, room42);
        BoxDoor door4140 = new BoxDoor(room41, room40);
        
        // Link doors
        room01.setSide(door0111);
        room11.setSide(door0111);
        room21.setSide(door2122);
        room22.setSide(door2122);
        room31.setSide(door3132);
        room32.setSide(door3132);
        room31.setSide(door3130);
        room30.setSide(door3130);
        room41.setSide(door4142);
        room42.setSide(door4142);
        room41.setSide(door4140);
        room40.setSide(door4140);
        
        // Link rooms
        room11.setSide(room21);
        room21.setSide(room11);
        room21.setSide(room31);
        room31.setSide(room21);
        room31.setSide(room41);
        room41.setSide(room31);
	}

	/**
	 * Construction is made using an Factory.
	 */
	public void factoryConstruction() {
		StableFactory f = new StableFactory();

		Room room01 = f.getRoom(0,1);
		Room room11 = f.getRoom(1,1);
		Room room21 = f.getRoom(2,1);
		Room room31 = f.getRoom(3,1);
		Room room41 = f.getRoom(4,1);
		Room room30 = f.getRoom(3,0);
		Room room40 = f.getRoom(4,0);
		Room room22 = f.getRoom(2,2);
		Room room32 = f.getRoom(3,2);
		Room room42 = f.getRoom(4,2);
		
		Stable.instance().addRoom(room01);
		Stable.instance().addRoom(room11);
		Stable.instance().addRoom(room21);
		Stable.instance().addRoom(room31);
		Stable.instance().addRoom(room41);
		Stable.instance().addRoom(room30);
		Stable.instance().addRoom(room40);
		Stable.instance().addRoom(room22);
		Stable.instance().addRoom(room32);
		Stable.instance().addRoom(room42);
		
		// Outer walls
		room01.setSide(f.getWall(KeyEvent.VK_UP));
		room01.setSide(f.getWall(KeyEvent.VK_DOWN));
		room01.setSide(f.getWall(KeyEvent.VK_LEFT));
		room11.setSide(f.getWall(KeyEvent.VK_UP));
		room11.setSide(f.getWall(KeyEvent.VK_DOWN));
		room21.setSide(f.getWall(KeyEvent.VK_UP));
		room30.setSide(f.getWall(KeyEvent.VK_LEFT));
		room30.setSide(f.getWall(KeyEvent.VK_UP));
		room40.setSide(f.getWall(KeyEvent.VK_UP));
		room40.setSide(f.getWall(KeyEvent.VK_RIGHT));
		room41.setSide(f.getWall(KeyEvent.VK_RIGHT));
		room42.setSide(f.getWall(KeyEvent.VK_RIGHT));
		room42.setSide(f.getWall(KeyEvent.VK_DOWN));
		room32.setSide(f.getWall(KeyEvent.VK_DOWN));
		room22.setSide(f.getWall(KeyEvent.VK_DOWN));
		room22.setSide(f.getWall(KeyEvent.VK_LEFT));
		
		// Box walls
		room30.setSide(f.getWall(KeyEvent.VK_RIGHT));
		room40.setSide(f.getWall(KeyEvent.VK_LEFT));
		room22.setSide(f.getWall(KeyEvent.VK_RIGHT));
		room32.setSide(f.getWall(KeyEvent.VK_LEFT));
		room32.setSide(f.getWall(KeyEvent.VK_RIGHT));
		room42.setSide(f.getWall(KeyEvent.VK_LEFT));
		
		// Create doors
		MapSite door0111 = f.getDoor(DoorChoice.door, room01, room11);		
		MapSite door2122 = f.getDoor(DoorChoice.boxDoor, room21, room22);
		MapSite door3132 = f.getDoor(DoorChoice.boxDoor, room31, room32);
		MapSite door3130 = f.getDoor(DoorChoice.boxDoor, room31, room30);
		MapSite door4142 = f.getDoor(DoorChoice.boxDoor, room41, room42);
		MapSite door4140 = f.getDoor(DoorChoice.boxDoor, room41, room40);
		
		// Link doors
		room01.setSide(door0111);
		room11.setSide(door0111);
		room21.setSide(door2122);
		room22.setSide(door2122);
		room31.setSide(door3132);
		room32.setSide(door3132);
		room31.setSide(door3130);
		room30.setSide(door3130);
		room41.setSide(door4142);
		room42.setSide(door4142);
		room41.setSide(door4140);
		room40.setSide(door4140);
		
		// Link rooms
		room11.setSide(room21);
		room21.setSide(room11);
		room21.setSide(room31);
		room31.setSide(room21);
		room31.setSide(room41);
		room41.setSide(room31);
	}

	/**
	 * Construction using the Builder pattern.
	 */
	public void builderConstruction() {
		StableBuilder b = new StableBuilder();
		
		for (int x = 0; x <= 4; x++) {
			b.addRoom(x, 1);
		}
		
		b.addRoom(3, 0);
		b.addRoom(4, 0);
		
		for (int x = 2; x <= 4; x++) {
			b.addRoom(x, 2);
			b.addDoor(x, 1, x, 2, DoorChoice.boxDoor);
		}
		
		b.addDoor(0, 1, 1, 1, DoorChoice.door);
		b.addDoor(3, 1, 3, 0, DoorChoice.boxDoor);
		b.addDoor(4, 1, 4, 0, DoorChoice.boxDoor);
		
		b.addWall(3, 0, KeyEvent.VK_RIGHT);
		b.addWall(2, 2, KeyEvent.VK_RIGHT);
		b.addWall(3, 2, KeyEvent.VK_RIGHT);
		
		b.addWall(4, 0, KeyEvent.VK_LEFT);
		b.addWall(3, 2, KeyEvent.VK_LEFT);
		b.addWall(4, 2, KeyEvent.VK_LEFT);
		
		b.addOuterDoors();
	}

	/**
	 * Construction using prototypes.
	 */
	public void prototypeConstruction() {
		StablePrototypeContainer c = StablePrototypeContainer.instance();
		
		Room room1 = c.getRoom(0,0);
		Room room2 = c.getRoom(1,0);
		Room room3 = c.getRoom(0,1);
		Room room4 = c.getRoom(1,1);

		MapSite door13 = c.getDoor(DoorChoice.door, room1, room3);
		MapSite boxDoor24 = c.getDoor(DoorChoice.boxDoor, room2, room4);
		MapSite boxDoor34 = c.getDoor(DoorChoice.boxDoor, room3, room4);

		Stable.instance().addRoom(room1);
		Stable.instance().addRoom(room4);
		Stable.instance().addRoom(room3);
		Stable.instance().addRoom(room2);

		room1.setSide(c.getWall(KeyEvent.VK_UP));
		room1.setSide(c.getWall(KeyEvent.VK_LEFT));

		room2.setSide(c.getWall(KeyEvent.VK_UP));
		room2.setSide(c.getWall(KeyEvent.VK_RIGHT));

		room3.setSide(c.getWall(KeyEvent.VK_DOWN));
		room3.setSide(c.getWall(KeyEvent.VK_LEFT));

		room4.setSide(c.getWall(KeyEvent.VK_RIGHT));
		room4.setSide(c.getWall(KeyEvent.VK_DOWN));

		room1.setSide(room2);
		room2.setSide(room1);

		room1.setSide(door13);
		room3.setSide(door13);

		room2.setSide(boxDoor24);
		room4.setSide(boxDoor24);

		room3.setSide(boxDoor34);
		room4.setSide(boxDoor34);
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
