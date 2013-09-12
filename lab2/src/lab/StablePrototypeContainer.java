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
			Door newDoor = (Door) door.clone();
			newDoor.move(r1, r2);
			return newDoor;
		case boxDoor:
			BoxDoor newBoxDoor = (BoxDoor) boxDoor.clone();
			newBoxDoor.move(r1, r2);
			return newBoxDoor;
		default:
			return null;
		}
	}
	
	public Wall getWall(int direction) {
		Wall newWall = (Wall) wall.clone();
		newWall.orientate(direction);
		return newWall;
	}
	
	public Room getRoom(int x, int y) {
		Room newRoom = (Room) room.clone();
		newRoom.move(x, y);
		return newRoom;
	}
}
