package lab;

public class StableFactory {
	public MapSite getDoor(DoorChoice choice, Room r1, Room r2) {
		switch (choice) {
		case door:
			return new Door(r1, r2);
		case boxDoor:
			return new BoxDoor(r1, r2);
		default:
			return null;
		}
	}
	
	public Wall getWall(int direction) {
		return new Wall(direction);
	}
	
	public Room getRoom(int x, int y) {
		return new Room(x, y);
	}
}
