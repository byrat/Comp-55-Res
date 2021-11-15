import javax.print.attribute.standard.Media;

import acm.graphics.GImage;

public class Player {
	
	
	public static final String IMG_FILENAME_PATH = "images/";
	public static final String IMG_EXTENSION = ".png";
	public static final String VERTICAL_IMG_FILENAME = "_vert";


	private int health;
	private int ammo;
	private int x = 0;
	private int y = 0;
	private double dx , dy;
	
	private boolean HasJumped;
	private Weapon banana;
	private Location location;
	private MainApplication program;
	
	private GImage image;
	// private GImage image2 = new GImage("media/MonkeyPics/banana.png");
	
	
	
	public Player (MainApplication app, int x, int y, int health, int ammo) {
		program = app;
		image = new GImage("media/characters/monkey.png", x , y);
		image.setSize(100,100);
		// EACH BLOCK IN MAP IS ABOUT 20 PX. -JT
		//image.setLocation(0, 510);
		location = new Location(x, y);
		this.x = x;
		this.y = y;
		this.health = health;
		this.ammo = ammo;
		HasJumped = false;
		
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
	public void update() {
		image.move(dx, dy);
	}
	public void movePlayer(double x, double y) {
			image.move(x, y);
	}
	public void updateVel(double x, double y) {
		if(dy != 0 && HasJumped == true) {
			dy += -5;
		}
		else if(dy != 0 && HasJumped == false) {
			dy += 5;
		}
		else if (dy >= 30) {
			HasJumped = true;
		}
		else if(dy == 0) {
			HasJumped = false;
			dy += y;
		}
		dx += x;
		
	}
	
	public void show() {
		program.add(image);
	}
	public void hide() {
		program.remove(image);
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
