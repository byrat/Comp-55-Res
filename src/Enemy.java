// import java.util.Timer;
// import java.awt.*;
// import java.awt.event.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.*;
// import javax.swing.Timer;
import acm.graphics.GImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import acm.graphics.GLabel;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.util.Timer;
import java.util.TimerTask;


public class Enemy { // TODO Implement the other classes this one needs
	MainApplication app;
	Player player;
	private Timer enemyTimer;
    private Location lastKnownLocation;

    private double firingRate;
    private Weapon enemyWeapon;
    private GImage enemySprite;
    private String IMG_PATH = "media/characters/";
    private String IMG_PNG_SUFFIX = ".png";
    
    private double seconds;

    private Difficulty difficulty;
    private double difficultyWeight;
    private boolean isAlive = true;
    private int health;

    public Enemy(MainApplication app, Location enemyLocation, Difficulty difficulty, boolean isBoss) { //! Delete the String MSG Parameter
    	this.app = app;
        // firingRate = 1 * difficulty=0.75 ==> 0.75sec
        this.firingRate = firingRate * difficulty.Difficulty();
        
        if (isBoss == false) {
        	//?? I NEED TO ADD GETDIFFICULTY LATER ON - JT
            if (difficulty == Difficulty.EASY) {
                enemySprite = new GImage(IMG_PATH + "hunter easy" + IMG_PNG_SUFFIX);
                enemySprite.scale(0.30);
                seconds = 2.75;
                health = 1;
            } else if (difficulty == Difficulty.MEDIUM) {
                enemySprite = new GImage(IMG_PATH + "hunter medium" + IMG_PNG_SUFFIX);
                enemySprite.scale(0.15);
                seconds = 1;
                health = 2;
            } else if (difficulty == Difficulty.HARD) {
                enemySprite = new GImage(IMG_PATH + "hunter hard" + IMG_PNG_SUFFIX);
                enemySprite.scale(0.5);
                seconds = 0.75;
                health = 3;
            } 
        } else {
            enemySprite = new GImage(IMG_PATH + "bossSprite" + IMG_PNG_SUFFIX);
            enemySprite.scale(0.5);
            seconds = 0.15;
            health = 10;
        }
        
        enemySprite.setLocation(enemyLocation.getXAxis(), enemyLocation.getYAxis());
  
    }
    
    public void startTimer() {
    	enemyTimer = new Timer();
    	enemyTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            	if (app.getGame() != null) {
            		player =  app.getGame().getPlayer();
            		GImage playerSprite = app.getGame().getPlayer().getImage();
                    Location playerLocation = new Location((int) playerSprite.getLocation().getX(), (int) playerSprite.getLocation().getY());
                    Location enemyLocation = new Location((int)enemySprite.getX(), (int)enemySprite.getY());
                    Projectiles fireAtPlayer = new Projectiles(app, enemyLocation, playerLocation, 0, "bullet", false);
            	}
            	if (player.getHealth() == 0) {
                	System.out.println("Player is dead");
                	player.hide();
                	enemyTimer.cancel();
                }
            }
        }, 0, (int) Math.abs(seconds * 1000));
    }
    
    public void pauseTimer() {
    	enemyTimer.cancel(); 
    }

    
    public int getHealth() { return health; }
    public void decrementHealth() { health--; }
    
    public boolean isEnemyAlive() {
        return isAlive;
    }
    
    public void moveEnemy(double x, double y) {
		enemySprite.setLocation(x, y);
}
    
    public GImage getImage() {
    	return enemySprite;
    }
    
    public void show() {
    	app.add(enemySprite);
    }
    
    public void hide() {
    	app.remove(enemySprite);
    }
    
//    public static void main(String[] args) {
//        // Test Code
//        new Enemy(1, 1, Difficulty.IMPOSSIBLE, "Dam Eyman", false); //! Delete String Parameter
//        new Enemy(2.5, 1, Difficulty.EASY, "Wow Gene", false); //! Delete String Parameter
//   
//    }
}