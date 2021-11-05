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
import acm.program.GraphicsProgram;

import java.util.Timer;
import java.util.TimerTask;


public class Enemy { // TODO Implement the other classes this one needs
    private Timer enemyTimer;
    private Location lastKnownLocation;

    private double firingRate;
    private Weapon enemyWeapon;
    private GImage enemySprite;
    String IMG_PATH = "media/characters/";
    String IMG_PNG_SUFFIX = ".png";

    Difficulty difficulty;
    double difficultyWeight;

    String msg; //! Delete this later

    public Enemy(double seconds, double firingRate, Difficulty difficulty, String msg, boolean isBoss) { //! Delete the String MSG Parameter
        // firingRate = 1 * difficulty=0.75 ==> 0.75sec
        this.firingRate = firingRate * difficulty.Difficulty(); 
        this.msg = msg; //! Delete this later
        
        if (isBoss == false) {
            if (difficulty == Difficulty.EASY) {
                enemySprite = new GImage(IMG_PATH + "hunter easy" + IMG_PNG_SUFFIX);
            } else if (difficulty == Difficulty.MEDIUM) {
                enemySprite = new GImage(IMG_PATH + "hunter medium" + IMG_PNG_SUFFIX);
            } else if (difficulty == Difficulty.HARD) {
                enemySprite = new GImage(IMG_PATH + "hunter hard" + IMG_PNG_SUFFIX);
            } 
        } else {
            enemySprite = new GImage(IMG_PATH + "bossSprite" + IMG_PATH);
        }
        
        // Creates a timer for this specific enemy
        // Overrides the run command so that each enemy can function independent of each other
        // Since they function independent of each other, the enemies will not be synced up with one another
        enemyTimer = new Timer(); 
        enemyTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(msg); //! Delete this, just a placeholder
                // TODO Implement actions for enemy here
                // TODO Actions include: UpdatePlayerLocation, ShootAtPlayer, etc.
            }
        }, 0, (int) Math.abs(seconds * 1000));
    }

    boolean isEnemyAlive() {
        // TODO implement logic: If Enemy == dead --> enemyTimer.cancel()
        return true; //! Change this later
    }

    public static void main(String[] args) {
        // Test Code
        new Enemy(1, 1, Difficulty.IMPOSSIBLE, "Dam Eyman", false); //! Delete String Parameter
        new Enemy(2.5, 1, Difficulty.EASY, "Wow Gene", false); //! Delete String Parameter
    }
}
