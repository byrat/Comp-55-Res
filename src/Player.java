import javax.print.attribute.standard.Media;

import acm.graphics.GImage;

public class Player {
	
	
	public static final String IMG_FILENAME_PATH = "images/";
	public static final String IMG_EXTENSION = ".png";
	public static final String VERTICAL_IMG_FILENAME = "_vert";


	private int health;
	private int ammo;
	private int xCoordinate = 0;
	private int yCoordinate = 0;
	
	
	private Weapon banana;
	private Location location;
	
	private GImage image = new GImage("media/characters/monkey.png");
	// private GImage image2 = new GImage("media/MonkeyPics/banana.png");
	
	
	
	public Player (int xCordinate, int yCoordinate, int health, int ammo) {
		location = new Location(xCordinate, yCoordinate);
		this.xCoordinate = xCordinate;
		this.yCoordinate = yCoordinate;
		this.health = health;
		this.ammo = ammo;
	}
		

	
	
	public void setHealth(int hp) {
		this.health = hp;
	}
	
	public void setAmmo (int ammo) {
		this.ammo = ammo;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Weapon getWeapon() {
		
		return banana;
	}
	
	//! Temp Function
	public void updatePlayerLocation(int xCoordinate, int yCoordinate) {
		location.updateLocation(xCoordinate, yCoordinate);
	}

	// public void updateLocation() {
	// 	location.setXAxis(location.getXAxis());
	// 	location.setYAxis(location.getYAxis());
	// }
	
	public static void main(String[] args) {
		Location firstTest = new Location(2,3);
		System.out.print("X Location: " + firstTest.getXAxis() + "\n");
		
		System.out.print("Y Location: " + firstTest.getYAxis());
	}
}
