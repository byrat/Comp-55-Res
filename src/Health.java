import acm.graphics.GImage;

public class Health {
	//Gimage of health bar: one full bar, decreasing bar
	//if player gets hit, health needs to decrease
	
	private Projectiles isHit;
	private Player player;
	private GImage firstHealth, secondHealth, thirdHealth;
	private MainApplication app;
	
	
	public Health(MainApplication app, int health) {
		this.app = app;
		player = new Player(app, health, health, health, health);
		player.setHealth(health);
		firstHealth = new GImage("media/Health/FullHealthBar.png", 700 , 800);
		firstHealth.setSize(20, 30);
	}
	
//	public void playerGetsHit() {
//		if(isHit.detectCollision() == true) {
//			
//		}
//	}
	
}
