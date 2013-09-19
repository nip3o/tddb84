package lab;

import java.awt.event.KeyEvent;

public class StableDirector {
	StableBuilder b;
	
	public StableDirector() {
		b = new StableBuilder();
	}
	
	public void makeStable() {
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
}