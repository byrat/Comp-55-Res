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
    MainApplication program;
    GraphicsGame game;
    double range = 40;
    double dx;
    double min;
    double max;
    double vision;
    boolean shooting = false;
    double cooldown;

    Difficulty difficulty;
    double difficultyWeight;

    String msg; //! Delete this later

    public Enemy(MainApplication program, GraphicsGame game,double x, double y, boolean StartingDirection, Difficulty difficulty, boolean isBoss) { //! Delete the String MSG Parameter
  
        this.firingRate = firingRate * difficulty.Difficulty();
        
        if (isBoss == false) {
        	//?? I NEED TO ADD GETDIFFICULTY LATER ON - JT
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
        enemySprite.setLocation(x, y);
        if(StartingDirection) {
        	dx = 1;
        	max = x + range;
        	min = x;
        	
        }
        else {
        	dx = -1;
        	min  = x - range;
        	max = x;
        }
        
        
       
    }

    boolean isEnemyAlive() {
        // TODO implement logic: If Enemy == dead --> enemyTimer.cancel()
        return true; //! Change this later
    }
    void show() {
    	program.add(enemySprite);
    	enemyWeapon.show();
    }
    void hide() {
    	program.remove(enemySprite);
    	enemyWeapon.hide();
    }
    public void update() {
    	if(dx > 0) {
    		double visonScore = game.getPlayer().getX() - enemySprite.getX();
    		if(visonScore > 0 && visonScore < vision) {
    			shooting = true;
    			dx = 0;
    		}
    	}
    	else {
    		double visonScore = game.getPlayer().getX() - enemySprite.getX();
			if(visonScore > 0 && visonScore < vision) {
				shooting = true;
				dx = 0;
	}
    	}
    	enemySprite.move(dx, 0);
    	enemyWeapon.update(dx,0);
    }
    {
    if (shooting) {
    	if(cooldown == 0) {
    		shoot();
    		cooldown = firingRate;
    	}
  
    }
    
  
   
    }
    public void shoot() {
    	double pX, pY, eX, eY;
    	pX = game.getPlayer().getX() + (game.getPlayer().getWidth() / 2);
    	pY = game.getPlayer().getY() + (game.getPlayer().getHeight() / 2);
    	
    	eX = enemySprite.getX() + (enemySprite.getWidth() / 2);
    	eY = enemySprite.getY() + (enemySprite.getHeight() / 2);
    	if(pX < eX) {
    		float slope = (float) ((pX - eX) / (pY - eY));
    	}
    	else {
    		float slope = (float) ((eX - pX) / (eY - pY));
    	}
    	///game.createProjectile(program);
  
    }
}

