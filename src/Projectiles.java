import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GImage;
import acm.graphics.GObject;


public class Projectiles {
//	private Direction direction; 
	private static int DIFFICULTY; 
	private GImage sprite;
	private String IMG_PATH = "media/weapons/";
	private String IMG_PATH_SUFFIX = ".png";
	private boolean isPlayer;  
	
	private int ENEMY_DAMAGE = 1 * DIFFICULTY; 
	private int PLAYER_DAMAGE = 1;
	private int DAMAGE;
	private double speed;
	private boolean isCollided;
	
	private Location positon, start, target;

	private double slopeNum, slopeDen, slope;
	
	private boolean isPlayerShooting;
	
//	private Health health;
	
	MainApplication app;
	Player player;
	Timer projectileTimer;
	private double seconds = 0.1;
	private int updateTime = (int) Math.abs(seconds * 1000);
	
	ArrayList<Enemy> enemiesArr;
	
	public Projectiles(MainApplication app, Location position, Location target, double speed, String spriteString, boolean isPlayerShooting) {
		this.app = app;
		this.positon = position;
		this.start = new Location(position.getXAxis(), position.getYAxis());
		this.target = target;
		this.isPlayer = false;
		this.isPlayerShooting = isPlayerShooting;
		enemiesArr = app.getGame().getEnemyArr();
		
		
		slopeNum = target.getYAxis() - start.getYAxis();
		slopeDen = target.getXAxis() - start.getXAxis();
		slope = slopeNum / slopeDen;
		
		if (spriteString == "banana_b" && isPlayerShooting == true) {
			IMG_PATH = "media/fruits/";
			System.out.println("Path= " + IMG_PATH + spriteString + IMG_PATH_SUFFIX);
		}
		sprite = new GImage(IMG_PATH + spriteString + IMG_PATH_SUFFIX);
		sprite.setLocation(start.getXAxis(), start.getYAxis());
		sprite.scale(0.1);
		
		if (spriteString == "banana_b" && isPlayerShooting == true) { sprite.scale(2.5); } 
		
		show();

		projectileTimer = new Timer();
		projectileTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				sprite.move(slopeDen * seconds, slopeNum * seconds);
				player = app.getGame().getPlayer();
				if (sprite.getLocation().getX() < 0 || sprite.getLocation().getY() < 0 ||
						sprite.getLocation().getX() > 1920 || sprite.getLocation().getY() > 1080) {
					projectileTimer.cancel();
					hide();
					System.out.println("Projectile Flew Out of Bounds");
				} else if (detectCollision(false) == true && isPlayerShooting == false) { // Enemy shot at player
					System.out.println("Detected Collision");
//					health = new Health(app, player.getHealth(), 1100, -88, player);
					player.decrementHealth();
//					System.out.println("health");
					projectileTimer.cancel();
					hide();
					//health = new Health(app, player.getHealth(), 1100, -88, player);
					// player.decrementHeatlh(); will add this line in health class so don't need to repeat it
				} else if (detectCollision(true) == true && isPlayerShooting == true) { // player is shooting at enemies
					
					System.out.println("Enemy was hit");
					player.increaseScore();
					projectileTimer.cancel();
					hide();
				}
			}
		}, 0, updateTime); 
	}
	
	
	
	public GImage getSprite() {
		return sprite;
	}
	
	public void setSprite(GImage s) {
		sprite = s;
	}
	
	
	public boolean setIsPlayer(boolean s) {
		return s = isPlayer;
	}
	
	public boolean getIsPlayer() {
		return isPlayer;
	}
	
	public void setDamage(int dm) {
		this.DAMAGE = dm;
	}
	
	public int getDamage() {
		return DAMAGE; 
	}
	
	public void show() {
		app.add(sprite);
	}
	
	public void hide() {
		app.remove(sprite);
	}
	
	public boolean detectCollision(boolean isPlayer) {
		double spriteLeftSideX= Math.floor(sprite.getLocation().getX() - 1);
		
		
		double spriteTopCornerY = Math.floor(sprite.getLocation().getY());
		double spriteMiddleY = Math.floor(sprite.getLocation().getY() + (sprite.getHeight() / 2));
		double spriteBottomCornerY = Math.floor(sprite.getLocation().getY() + sprite.getHeight());
		
		if (isPlayer == false) { // player is the one being targeted
			double spriteRightSideX = Math.floor((sprite.getLocation().getX() + sprite.getWidth()) + 10);
			if (player.getImage().contains(spriteLeftSideX, spriteTopCornerY) ||
					player.getImage().contains(spriteLeftSideX, spriteMiddleY) || 
					player.getImage().contains(spriteLeftSideX, spriteBottomCornerY) ||
					player.getImage().contains(spriteRightSideX, spriteTopCornerY) ||
					player.getImage().contains(spriteRightSideX, spriteMiddleY) ||
					player.getImage().contains(spriteRightSideX, spriteBottomCornerY))  {
				return true;
			}
		} else {
			for (Enemy e:enemiesArr) {
				boolean flag = false;
				double spriteRightSideX = Math.floor((sprite.getLocation().getX() + sprite.getWidth()) - 10);
				if (e.getImage().contains(spriteLeftSideX, spriteTopCornerY) ||
						e.getImage().contains(spriteLeftSideX, spriteMiddleY) || 
						e.getImage().contains(spriteLeftSideX, spriteBottomCornerY) ||
						e.getImage().contains(spriteRightSideX, spriteTopCornerY) ||
						e.getImage().contains(spriteRightSideX, spriteMiddleY) ||
						e.getImage().contains(spriteRightSideX, spriteBottomCornerY))  {
					e.decrementHealth();
					if (e.getHealth() == 0) {
						System.out.println("Enemy is dead!");
						e.pauseTimer();
						app.remove(e.getImage());
						flag = true;
						enemiesArr.remove(e);
					}
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		;
	}
	
}
