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
	
	private boolean isMoving = false;
	private boolean hasJumped;
	private Weapon banana;
	private Location location;
	private MainApplication program;
	private double maxDX;
	private double maxDY;
	
	private GImage image;

	public GImage getImage() {
		return image;
	}
	
	
	
	public Player (MainApplication app, int x, int y, int health, int ammo) {
		program = app;
		image = new GImage("media/characters/monkey.png", x , y);
		image.setSize(100,100);
		// EACH BLOCK IN MAP IS ABOUT 20 PX. -JT
		//image.setLocation(0, 510);
		maxDX = 8;
		location = new Location(x, y);
		this.x = x;
		this.y = y;
		this.health = health;
		this.ammo = ammo;
		hasJumped = false;
		dx = dy = 0;
		
	}
	public double getX() {
		return image.getX();
	}
	public double getY() {
		return image.getY();
	}
	public double getHeight() {
		return image.getHeight();
	}
	public double getWidth() {
		return image.getWidth();
	}
		

	public boolean getHasJumped() {
		return hasJumped;
	}
	
	public void setHasJumped(boolean hasJumped) {
		this.hasJumped = hasJumped;
	}
	
	public void setHealth(int hp) {
		this.health = hp;
	}
	
	public int getHealth() { return health; }
	
	public void decrementHeatlh() { health--; }
	
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
		if (dx < 1 && dx > -1 && isMoving == false){
			dx = 0;
		}
		if(isMoving == false && dx != 0) {
			if(dx > 0) {
				dx += -0.5;
			}
			else {
				dx += 0.5;
			
			
		}
	}
		
		if(dy <= -10 && hasJumped == true) {
			hasJumped = false;
		}
		image.move(dx, dy);
		isMoving = false;
	}
	public void movePlayer(double x, double y) {
			System.out.println("movePlayer called \n");
			image.move(x, y);
	}
	public void updateXVel(double x) {
		System.out.println("player x velocity updated with " + x + "\n");
		
		if(dx == 0) {
			if(x > 0) {
				dx = 3;
			}
			else {
				dx = 3;
			}
		}
		else {
			dx += x;
		}
		isMoving = true;
		if(dx > maxDX) {
			dx = maxDX;
		}
		else if(dx < maxDX * -1) {
			dx = maxDX * -1;
		}
	}
	
	public double getXVel() {
		return dx;
	}
	public void updateYVel(double y) {
		this.dy += y;
	}
	public double getYVel() {
		return dy;
	}
	public void setVelY(double d) {
		dy = d;
		
	}
	public void setVelX(double d) {
		System.out.println("player x velocity set to:  " + d + "\n");
		dx = d;
		
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
