import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;

public class Weapon{ //! Might have to extend Graphics Program
    private static String WEAPON_FOLDER = "media/Weapons/";
    private double firingRate = 0;
    private GImage sprite;
    private MainApplication program;

    // TODO Maybe implement an enum that uses weapon, so instead of placing weapon on the character
    // TODO it just uses the getWeapon() and switches main character sprite to sprite with that weapon


    public Weapon(MainApplication app, double firingRate, String weapon) {
    	program = app;
        this.firingRate = firingRate;
        sprite = new GImage(WEAPON_FOLDER + weapon);
        sprite.setLocation(-1000,-1000);
    }

    public GImage getWeaponSprite() {
        return sprite;
    }

    public void setFiringRate(double firingRate) {
        this.firingRate = firingRate;
    }

    public double getFiringRate() {
        return firingRate;
    }
    public void show() {
    	// CAN SWITCH MONKEY TO MONKEY WITH WEAPON SPRITE - JT
		program.add(sprite);
	}
    public void update(double dx, int i) {
		// TODO Auto-generated method stub
		sprite.move(dx,i);
		
	}
	public void hide() {
		program.remove(sprite);
	}
}
