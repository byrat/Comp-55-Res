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
	
	private int ENEMY_DAMAGE = 1 * DIFFICULTY; 
	private int PLAYER_DAMAGE = 1;
	private int DAMAGE;
	private double speed;
	private boolean isCollided;
	
	private Location positon, start, target;

	private double slopeNum, slopeDen, slope;

	MainApplication app;
	Player player;
	Timer projectileTimer;
	private double seconds = 0.1;
	private int updateTime = (int) Math.abs(seconds * 1000);
	
	public Projectiles(MainApplication app, Location position, Location target, double speed, String spriteString) {
		this.app = app;
		this.positon = position;
		this.start = new Location(position.getXAxis(), position.getYAxis());
		this.target = target;
		
		
		
		slopeNum = target.getYAxis() - start.getYAxis();
		slopeDen = target.getXAxis() - start.getXAxis();
		slope = slopeNum / slopeDen;

		sprite = new GImage(IMG_PATH + spriteString + IMG_PATH_SUFFIX);
		sprite.setLocation(start.getXAxis(), start.getYAxis());
		sprite.scale(0.1);
		
		show();

		projectileTimer = new Timer();
		projectileTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				sprite.move(slopeDen * seconds, slopeNum * seconds);
				player = app.getGame().getPlayer();
				if (sprite.getLocation().getX() < 0 || sprite.getLocation().getY() < 0) {
					projectileTimer.cancel();
					hide();
					System.out.println("Projectile Flew Out of Bounds");
				} else if (detectCollision(false) == true) {
					System.out.println("Detected Collision");
					projectileTimer.cancel();
					hide();
					player.decrementHeatlh();
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
		if (isPlayer == false) { // player is the one being targeted
			double spriteLeftSideX= Math.floor(sprite.getLocation().getX() - 1);
			double spriteRightSideX = Math.floor((sprite.getLocation().getX() + sprite.getWidth()) + 1);
			
			double spriteTopCornerY = Math.floor(sprite.getLocation().getY());
			double spriteMiddleY = Math.floor(sprite.getLocation().getY() + (sprite.getHeight() / 2));
			double spriteBottomCornerY = Math.floor(sprite.getLocation().getY() + sprite.getHeight());
			
			if (player.getImage().contains(spriteLeftSideX, spriteTopCornerY) ||
					player.getImage().contains(spriteLeftSideX, spriteMiddleY) || 
					player.getImage().contains(spriteLeftSideX, spriteBottomCornerY) ||
					player.getImage().contains(spriteRightSideX, spriteTopCornerY) ||
					player.getImage().contains(spriteRightSideX, spriteMiddleY) ||
					player.getImage().contains(spriteRightSideX, spriteBottomCornerY))  {
				return true;
			}
		} 
		
		return false;
	}
	
	public static void main(String[] args) {
		;
	}
	
}
