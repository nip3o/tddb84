package lab;

import java.awt.event.KeyEvent;


public class StableBuilder {
	Stable stable;
	final int[] SIDES = {KeyEvent.VK_DOWN, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
	
	public StableBuilder() {
		stable = Stable.instance();
	}

	public void addRoom(int x, int y) {
		Room room = new Room(x, y);
		stable.addRoom(room);
		for(Room adjacentRoom : stable.findAdjucentRooms(room)) {
			room.setSide(adjacentRoom);
			adjacentRoom.setSide(room);
		}
	}

	public void addWall(int x, int y, int side) {
		stable.getRoom(x, y).setSide(new Wall(side));
	}

	public void addDoor(int x1, int y1, int x2, int y2, DoorChoice choice) {
		Room r1 = stable.getRoom(x1, y1);
		Room r2 = stable.getRoom(x2, y2);
		
		MapSite door = getDoor(r1, r2, choice);
		r1.setSide(door);
		r2.setSide(door);
	}

	public void addOuterDoors() {
		for(Room room : stable.rooms) {
			for(int direction : SIDES) {
				if(room.getSide(direction) instanceof NullSide) {
					this.addWall(room.getX(), room.getY(), direction);
				}
			}
			
		}
	}
	
	private MapSite getDoor(Room r1, Room r2, DoorChoice choice) {
		switch (choice) {
		case door:
			return new Door(r1, r2);
		case boxDoor:
			return new BoxDoor(r1, r2);
		default:
			return null;
		}
	}
	
}
