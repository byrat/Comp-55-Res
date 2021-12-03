import acm.graphics.GImage;

public class Health {
	//Gimage of health bar: one full bar, decreasing bar
	//if player gets hit, health needs to decrease
	
	private Projectiles isHit;
	private Player player;
	private GImage firstHealth, secondHealth, thirdHealth;
	private MainApplication app;
	
	
	public Health(MainApplication app, int health,int x, int y) {
		this.app = app;
		player = new Player(app, health, health, health, health);
		player.setHealth(health);
		firstHealth = new GImage("media/Health/FullHealthBar-removebg-preview.png", 700 , 800);
		firstHealth.setLocation(x, y);
		firstHealth.setSize(140, 225);
	}
	
	public void playerGetsHit() {
		if(isHit.detectCollision(isHit.getIsPlayer()) == true) {
			player.decrementHeatlh();
		}
	}
	
	
	
	public GImage decreasedHealthBar() {
		secondHealth = new GImage("media/Health/decreasedHealthBar.png", 700, 800);
		secondHealth.setSize(200, 300);
		return secondHealth;
		
	}
	
	public void show() {
		app.add(firstHealth);
	}
	public void hide() {
		app.remove(firstHealth);
	}
}
