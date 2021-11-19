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
				if (sprite.getLocation().getX() < 0 || sprite.getLocation().getY() < 0 || detectCollision() == true) {
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
	
	public boolean detectCollision() {
//		double bulletX = sprite.getX();
//		double bulletY = sprite.getY();
////		if (getElementAt(bulletX, bulletY) instanceof Player) {
////			
////		}
//		GObject test = app.getElementAt(null)
		
		return false;
	}
	
	public static void main(String[] args) {
		;
	}
	
}
