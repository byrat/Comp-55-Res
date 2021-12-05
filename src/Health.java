import acm.graphics.GImage;
//import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;

public class Health {
	//Gimage of health bar: one full bar, decreasing bar
	//if player gets hit, health needs to decrease
	
	private Projectiles isHit;
	//private Player player;
	private GImage firstHealth, secondHealth, thirdHealth;
	static MainApplication app;
	
	
	public Health(MainApplication app, int health,int x, int y, Player player) {
		this.app = app;
		player.setHealth(health);
		if(player.getHealth() == 3) {
			firstHealth = new GImage("media/Health/FullHealthBar.png", 700 , 800);
			firstHealth.setLocation(x, y);
			firstHealth.setSize(140, 225);
			player.decrementHealth();
			showFirstHealth();
		}
		else if(player.getHealth() == 2) {
			//hideFirstHealth();
			secondHealth = new GImage("media/Health/DecreasedHealthBar.png", 700, 800);
			secondHealth.setLocation(x, y);
			secondHealth.setSize(140, 225);
			player.decrementHealth();
			showSecondHealth();
		}
		else if(player.getHealth() == 1) {
			//hideSecondHealth();
			thirdHealth = new GImage("media/Health/OneLifeRemaining.png", 700, 800);
			thirdHealth.setLocation(x, y);
			thirdHealth.setSize(140, 225);
			player.decrementHealth();
			showThirdHealth();
		}
	}
	
//	public void playerGetsHitOnce(Player player) {
//		if(isHit.detectCollision(isHit.getIsPlayer()) == true) {
//			player.decrementHealth();
//			secondHealth = new GImage("media/Health/DecreasedHealthBar.png", 700, 800);
//			secondHealth.setLocation(140, 225);
//			secondHealth.setSize(140, 225);
////			System.out.println("picture printed");
////			System.out.println("picture did not print");
//
//		}
//	}
	

//	public void show() { //created individual show's and hide's for each health bar
//		app.add(firstHealth);
//	}
//	public void hide() {
//		app.remove(firstHealth);
//	}
	
//	public void playerGetsHitTwice(Player player) {
//		if(isHit.detectCollision(isHit.getIsPlayer()) == true) { // removed isHit.getIsPlayer before == true
//			player.decrementHeatlh();
//			thirdHealth = new GImage("media/Health/OneLifeRemaining.png", 700, 800);
//			thirdHealth.setLocation(140, 225);
//			thirdHealth.setSize(140, 225);
////			System.out.println("picture also printed");
//		}
//	}
	
	
//	public GImage decreasedHealthBar() {
//		secondHealth = new GImage("media/Health/decreasedHealthBar.png", 700, 800);
//		secondHealth.setSize(200, 300);
//		return secondHealth;
//	}
	
	public void showFirstHealth() {
		app.add(firstHealth);
	}
	public void hideFirstHealth() {
		app.remove(firstHealth);
	}
	public void showSecondHealth() {
		app.add(secondHealth);
	}
	public void hideSecondHealth() {
		app.remove(secondHealth);
	}
	public void showThirdHealth() {
		app.add(thirdHealth);
	}
//	public void hideThirdHealth() {
//		app.remove(thirdHealth);
//	}
	
//	public static void main(String[] args) {
//		//Player player = new Player(app,0,510,600,50);
//		Player player = new Player(app, 0, 510, 600, 50);
//	}
}