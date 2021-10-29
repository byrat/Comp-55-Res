import acm.graphics.GImage;

public class Projectiles {
//	private Direction direction; 
	
	private GImage sprite;
	private static int DIFFICULTY; 
	private float xAxis, yAxis;
	private int ENEMY_DAMAGE = 1 * DIFFICULTY; 
	private int PLAYER_DAMAGE = 1;
	private int DAMAGE;
	private boolean isCollided;
	private String BULLET_TYPE; 
	
	
	
	public Projectiles(String bullet, Direction d, int damage) {
//		this.direction = d;
		this.DAMAGE = damage; 
		this.BULLET_TYPE = bullet; 
		
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
//	public Direction getDirection() {
////		return direction;
//	}
//	
//	public void setDirection(Direction d) {
//		this.direction = d;
//	}
//	
	public boolean detecCollision() {
		return isCollided;
	}
	public static void main(String[] args) {
	}
	
}
