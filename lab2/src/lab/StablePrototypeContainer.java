package lab;

public class StablePrototypeContainer {
	private static StablePrototypeContainer instance;
	private Room room;
	private Wall wall;
	private Door door;
	private BoxDoor boxDoor;

	private StablePrototypeContainer() {
		room = new Room(0, 0);
		door = new Door(room, room);
		boxDoor = new BoxDoor(room, room);
		wall = new Wall(0);
	}

	public static StablePrototypeContainer instance() {
		if (null != instance) return instance;

		instance = new StablePrototypeContainer();
		return instance;
	}
	
	
	public MapSite getDoor(DoorChoice choice, Room r1, Room r2) {
		switch (choice) {
		case door:
			return door.clone();
		case boxDoor:
			return boxDoor.clone();
		default:
			return null;
		}
	}
	
	public MapSite getWall() {
		return wall.clone();
	}
	
	public MapSite getRoom() {
		return room.clone();
	}
}
