import javax.print.attribute.standard.Media;

import acm.graphics.GImage;

public class Player {
	
	
	public static final String IMG_FILENAME_PATH = "images/";
	public static final String IMG_EXTENSION = ".png";
	public static final String VERTICAL_IMG_FILENAME = "_vert";


	private int health = 100;
	private int ammoAmt = 0;
	private int x = 0;
	private int y = 0;
	
	
	private Weapon banana;
	private Location location;
	
	private GImage image = new GImage("media/MonkeyPics/MonkeySprite.png");
	private GImage image2 = new GImage("media/MonkeyPics/banana.png");
	
	
	
	public Player (Location xCordinate, Location yCoordinate, int health, int ammo) {
		xCordinate.setXAxis(x);
		yCoordinate.setYAxis(y);
		
	}
		

	
	
	public void setHealth(int hp) {
		this.health = hp;
	}
	
	public void setAmmo (int ammo) {
		this.ammoAmt = ammo;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Weapon getWeapon() {
		
		return banana;
	}
	
	public void updateLocation() {
		location.setXAxis(location.getXAxis());
		location.setYAxis(location.getYAxis());
	}
	
	public static void main(String[] args) {
		Location firstTest = new Location(2,3);
		System.out.print("X Location: " + firstTest.getXAxis() + "\n");
		
		System.out.print("Y Location: " + firstTest.getYAxis());
		
		
		
	
	}

	
	
	
}
