import acm.graphics.GImage;
//import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;

public class Health {
	//Gimage of health bar: one full bar, decreasing bar
	//if player gets hit, health needs to decrease
	
	private Projectiles isHit;
	private Player player;
	private GImage firstHealth, secondHealth, thirdHealth;
	private MainApplication app;
	private int hitCount;
	
	
	public Health(MainApplication app, int health,int x, int y, Player player) {
		this.app = app;
		player.setHealth(health);
		firstHealth = new GImage("media/Health/FullHealthBar-removebg-preview.png", 700 , 800);
		firstHealth.setLocation(x, y);
		firstHealth.setSize(140, 225);
	}
	
	public void playerGetsHitOnce() {
		if(isHit.detectCollision(isHit.getIsPlayer()) == true) {
			player.decrementHeatlh();
			secondHealth = new GImage("media/Health/DecreasedHealthBar-removebg-preview.png", 700, 800);
			secondHealth.setLocation(140, 225);
			secondHealth.setSize(140, 225);
			hitCount++;
		}
	}
	
	private void playerGetsHitTwice() {
		if ((isHit.detectCollision(isHit.getIsPlayer()) == true) && hitCount == 1) {
			player.decrementHeatlh();
			thirdHealth = new GImage("media/Health/OneLifeRemaining-removebg-preview.png", 700, 800);
			thirdHealth.setLocation(140, 225);
			thirdHealth.setSize(140, 225);
			hitCount++;
		}
	}

	
	
	public GImage decreasedHealthBar() {
		secondHealth = new GImage("media/Health/decreasedHealthBar.png", 700, 800);
		secondHealth.setSize(200, 300);
		return secondHealth;
		
	}
	
	public void show() {
		app.add(firstHealth);
		app.add(secondHealth);
		app.add(thirdHealth);
	}
	public void hide() {
		app.remove(firstHealth);
		app.remove(secondHealth);
		app.remove(thirdHealth);
	}
}
