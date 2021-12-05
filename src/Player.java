import javax.print.attribute.standard.Media;

import acm.graphics.GImage;
import java.util.Timer;
import java.util.TimerTask;

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
	Direction playerDirection;
	
	private boolean canShoot = true;

	public GImage getImage() {
		return image;
	}
	
	
	
	public Player (MainApplication app, int x, int y, int health, int ammo) {
		program = app;
		image = new GImage("media/characters/monkey_east.png", x , y);
		playerDirection = Direction.EAST;
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
	
	public void setCanShoot(boolean canShoot) { 
		this.canShoot = canShoot;
		Timer shootTimer = new Timer();
		shootTimer.schedule(new TimerTask() {
			int counter = 0;
			@Override
			public void run() {
				counter++;
				System.out.println("Counter = " + counter);
				if (counter == 3) { // After 3 seconds, the player is allowed to shoot again
					shootTimer.cancel();
					Player.this.canShoot = true;
				}
				
			}
		}, 0, Math.abs(1 * 1000));
	} 
	
	public boolean getCanShoot() { return canShoot; } 
	
	void setPlayerDirectionSprite(Direction d) {
		int tempX = (int) getImage().getX();
		int tempY = (int) getImage().getY();
		program.remove(image);
		if (d == Direction.WEST) {
			playerDirection = Direction.WEST;
			image = new GImage("media/characters/monkey_west.png", tempX,tempY);
		} else if (d == Direction.EAST) {
			playerDirection = Direction.EAST;
			image = new GImage("media/characters/monkey_east.png", tempX,tempY);
		}
		image.setSize(100, 100);
		program.add(image);
	}
	
//	public void shoot() {
//		new Projectiles(program, new Location((int) image.getX(), (int)image.getY()), , ammo, IMG_EXTENSION)
//	}

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
	
	public void decrementHealth() { health--; }
	
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
//			System.out.println("movePlayer called \n");
			image.move(x, y);
	}
	
	public void updateXVel(double x) {
//		System.out.println("player x velocity updated with " + x + "\n");
		//JT
		
		if(dx == 0) {
			if(x > 0) {
				dx = 3;
			}
			else {
				dx = -3;
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
	
	public double getXValupdated() {
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
//		System.out.println("player x velocity set to:  " + d + "\n");
		dx = d;
		
	}
	
	public Direction getDirection() {
		return playerDirection;
	}
	
	public void show() {
		program.add(image);
	}
	public void hide() {
		program.remove(image);
	}
	
	public static void main(String[] args) {
		Location firstTest = new Location(2,3);
//		System.out.print("X Location: " + firstTest.getXAxis() + "\n");
		
//		System.out.print("Y Location: " + firstTest.getYAxis());
		
//		System.out.print("X Location: " + firstTest.getXAxis() + firstTest.getXAxis()+ "\n");
		
//		System.out.print("Y Location: " + firstTest.getYAxis() + firstTest.getYAxis());
	}
	
	// public void updateLocation() {
	// 	location.setXAxis(location.getXAxis());
	// 	location.setYAxis(location.getYAxis());
	// }
}
