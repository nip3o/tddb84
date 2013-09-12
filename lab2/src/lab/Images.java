package lab;

import java.awt.Image;
import java.awt.MediaTracker;
import javax.swing.ImageIcon;

/**
 * A Singleton Factory that creates and stores images and is used as
 * an image repository that only has a single copy of every image loaded.
 *
 * @author Peter Sunnergren
 */
public class Images {

	private static Images instance;
	private static StableApplet applet;
	private MediaTracker tracker;
	private Image room;
	private Image wallUp;
	private Image wallRight;
	private Image wallDown;
	private Image wallLeft;
	private Image personUp;
	private Image personRight;
	private Image personDown;
	private Image personLeft;
	private Image doorVeriOpen;
	private Image doorVeriClosed;
	private Image doorHoriOpen;
	private Image doorHoriClosed;
	private Image boxdoorVeriOpen;
	private Image boxdoorVeriClosed;
	private Image boxdoorHoriOpen;
	private Image boxdoorHoriClosed;
	private Image gunnarHorse;
	private Image rayaHorse;
	private Image gulliverHorse;

	private Images(StableApplet a) {

		applet = a;
		tracker = new MediaTracker(applet);
	}

	/**
	 * Gets an instance of the images. If no instance exists it create
	 * one using an StableApplet in the constructor.
	 */
	public static Images instance() {

		if (null != instance) return instance;

		if (null != applet) {
			instance = new Images(applet);
			return instance;
		} else {
			System.out.println("Please initialize with an applet.");
			return null;
		}
	}

	/**
	 * Sets the applet and MUST be called before
	 * the images is used for the first time.
	 */
	public static void setApplet(StableApplet a) {

		applet = a;
	}

	private Image loadImage(String imageName, int width, int height) {

		Image dest;

		String fullImagePath =
			Images.class.getResource("assets/" + imageName).getPath();
		dest = (new ImageIcon(fullImagePath)).getImage();

		if ((-1 == dest.getWidth(null)) || (-1 == dest.getWidth(null))) {
			System.out.println("The image " + imageName + " is not found.");
			return dest;
		}

		if ((width != dest.getWidth(null)) || (height != dest.getWidth(null))) {
			dest = dest.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		}

		tracker.addImage(dest, 0);

		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			System.out.println("Image loading has been interrupted.");
		}

		return dest;
	}

	/**
	 * Gets the room image.
	 *
	 * @param size The size of the room.
	 * @return An image of a room.
	 */
	public Image getRoomImage(int size) {

		if ((room == null) || (-1 == room.getWidth(null)))
			room = loadImage("Room1.JPG", size, size);

		return room;
	}

	/**
	 * Gets the image of the upside wall.
	 */
	public Image getWallUpImage(int width, int height) {

		if ((wallUp == null) || (-1 == wallUp.getWidth(null)))
			wallUp = loadImage("WallUp1.JPG", width, height);

		return wallUp;
	}

	/**
	 * Gets the image of the right hand side wall.
	 */
	public Image getWallRightImage(int width, int height) {

		if ((wallRight == null) || (-1 == wallRight.getWidth(null)))
			wallRight = loadImage("WallRight1.JPG", width, height);

		return wallRight;
	}

	/**
	 * Gets the image of the downside wall.
	 */
	public Image getWallDownImage(int width, int height) {

		if ((wallDown == null) || (-1 == wallDown.getWidth(null)))
			wallDown = loadImage("WallDown1.JPG", width, height);

		return wallDown;
	}

	/**
	 * Gets the image of the left hand side wall.
	 */
	public Image getWallLeftImage(int width, int height) {

		if ((wallLeft == null) || (-1 == wallLeft.getWidth(null)))
			wallLeft = loadImage("WallLeft1.JPG", width, height);

		return wallLeft;
	}

	/**
	 * Gets the image of the person looking up.
	 */
	public Image getPersonUpImage(int size) {

		if ((personUp == null) || (-1 == personUp.getWidth(null)))
			personUp = loadImage("PersonUp.gif", size, size);

		return personUp;
	}

	/**
	 * Gets the image of the person looking right.
	 */
	public Image getPersonRightImage(int size) {

		if ((personRight == null) || (-1 == personRight.getWidth(null)))
			personRight = loadImage("PersonRight.gif", size, size);

		return personRight;
	}

	/**
	 * Gets the image of the person looking down.
	 */
	public Image getPersonDownImage(int size) {

		if ((personDown == null) || (-1 == personDown.getWidth(null)))
			personDown = loadImage("PersonDown.gif", size, size);

		return personDown;
	}

	/**
	 * Gets the image of the person looking left.
	 */
	public Image getPersonLeftImage(int size) {

		if ((personLeft == null) || (-1 == personLeft.getWidth(null)))
			personLeft = loadImage("PersonLeft.gif", size, size);

		return personLeft;
	}

	/**
	 * Gets a horizontal (upside or downside in a room) door.
	 */
	public Image getDoorHorizontalImage(int width, int height, boolean open) {

		if (open) {
			if ((doorHoriOpen == null) || (-1 == doorHoriOpen.getWidth(null)))
				doorHoriOpen = loadImage("DoorHoriOpen.gif", width, height);

			return doorHoriOpen;
		} else {
			if ((doorHoriClosed == null) || (-1 == doorHoriClosed.getWidth(null)))
				doorHoriClosed = loadImage("DoorHori1.gif", width, height);

			return doorHoriClosed;
		}
	}

	/**
	 * Gets a vertical (left side or right side in a room) door.
	 */
	public Image getDoorVerticalImage(int width, int height, boolean open) {

		if (open) {
			if ((doorVeriOpen == null) || (-1 == doorVeriOpen.getWidth(null)))
				doorVeriOpen = loadImage("DoorVertOpen.gif", width, height);

			return doorVeriOpen;
		} else {
			if ((doorVeriClosed == null) || (-1 == doorVeriClosed.getWidth(null)))
				doorVeriClosed = loadImage("DoorVert3.gif", width, height);

			return doorVeriClosed;
		}
	}

	/**
	 * Gets a vertical boxdoor.
	 */
	public Image getBoxDoorVerticalImage(int width, int height, boolean open) {

		if (open) {
			if ((boxdoorVeriOpen == null) || (-1 == boxdoorVeriOpen.getWidth(null)))
				boxdoorVeriOpen = loadImage("BoxDoorVertOpen.gif", width, height);

			return boxdoorVeriOpen;
		} else {
			if ((boxdoorVeriClosed == null) || (-1 == boxdoorVeriClosed.getWidth(null)))
				boxdoorVeriClosed = loadImage("BoxDoorVert.gif", width, height);

			return boxdoorVeriClosed;
		}
	}

	/**
	 * Gets a horizontal boxdoor.
	 */
	public Image getBoxDoorHorizontalImage(int width, int height, boolean open) {

		if (open) {
			if ((boxdoorHoriOpen == null) || (-1 == boxdoorHoriOpen.getWidth(null)))
				boxdoorHoriOpen = loadImage("BoxDoorHoriOpen.gif", width, height);

			return boxdoorHoriOpen;
		} else {
			if ((boxdoorHoriClosed == null) || (-1 == boxdoorHoriClosed.getWidth(null)))
				boxdoorHoriClosed = loadImage("BoxDoorHori.gif", width, height);

			return boxdoorHoriClosed;
		}
	}

	/**
	 * Gets an image of the horse called Gunnar.
	 */
	public Image getGunnarImage(int size) {

		if ((gunnarHorse == null) || (-1 == gunnarHorse.getWidth(null)))
			gunnarHorse = loadImage("gunnar.gif", size, size);

		return gunnarHorse;
	}

	/**
	 * Gets an image of the horse called Raya.
	 */
	public Image getRayaImage(int size) {

		if ((rayaHorse == null) || (-1 == rayaHorse.getWidth(null)))
			rayaHorse = loadImage("raya.gif", size, size);

		return rayaHorse;
	}

	/**
	 * Gets an image of the horse called Gulliver.
	 */
	public Image getGulliverImage(int size) {

		if ((gulliverHorse == null) || (-1 == gulliverHorse.getWidth(null)))
			gulliverHorse = loadImage("gulliver.gif", size, size);

		return gulliverHorse;
	}
}
