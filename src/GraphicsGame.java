import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLine;
import acm.graphics.GRect;

public class GraphicsGame extends GraphicsPane implements KeyListener, ActionListener {
	boolean [] FruitLog = new boolean[4];
	/*STRAWBERRY(""), 
	BANANA(""),
	MELON(""),
	PINEAPPLE(""),*/
	Collection CollectionMenu;
	GImage Background;
	MainApplication program;
	private Projectiles projectile;
	Player player;
	Timer timer;
	Weapon weapon;
	Location loc;
	public static final int MAP_WIDTH = 9174;//800;
	public static final int MAP_HEIGHT = 3018;//600;
	public static final int START_LOCATION_X = -400;//800;
	public static final int START_LOCATION_Y = -1000;//600;
	public int i = 0;
	public boolean CAN_SCROLL = false;
	public static int DISTANCE_X,DISTANCE_Y = 0;
	public static int dx,dy;
	
	public void UpdateLog(int x) {
		if (FruitLog[x] == false) {
			FruitLog[x] = true;
			CollectionMenu.UpdateCollection(x);
		}
		
	}
	
	public boolean [] getLog() {
		return FruitLog;
	}
	GraphicsGame(CollectionMenu collection, MainApplication  program){
		this.program = program;
		Background = new GImage("media/fruits/map.png" , 0 ,0);
		Background.setSize((MAP_WIDTH),MAP_HEIGHT );
		Background.setLocation(START_LOCATION_X,START_LOCATION_Y);
		
		
		///this.CollectionMenu = collection;
		player = new Player (program , 0 ,510 ,5 ,5);
		weapon = new Weapon(program, 1,"banana.png"); //CHANGE ONCE RESCALED - JT
		//projectile = new Projectiles(program, "media/fruits/banana.png", Direction.WEST, 1);
		
	}
	public boolean collision(GRect boxA, GRect boxB) {
		double minX = boxA.getX();
		double minY = boxA.getY();
		double maxX = minX + boxA.getWidth();
		double maxY = minY + boxA.getHeight();
		
		double minXB = boxB.getX();
		double minYB = boxB.getY();
		double maxXB = minX + boxB.getWidth();
		double maxYB = minY + boxB.getHeight();
		if(minXB > maxX || minX > maxXB) return false;
		if(minYB > maxY || minY > maxYB) return false;
		
		return true;
	}
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(Background);
		player.show();
		weapon.show();
		
		///Start game loop
		timer = new Timer (10 , this);
		timer.start();
		
		

	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(Background);
		player.hide();

	}
	
	private void checkOOB() {
        if(DISTANCE_X < 0) {
        	DISTANCE_X = 0;
        }
        if(DISTANCE_X > MAP_WIDTH) { 
        	DISTANCE_X = MAP_WIDTH;
        }
        if(DISTANCE_Y < 0) { 
        	DISTANCE_Y = 0;
        }
        if(DISTANCE_Y > MAP_HEIGHT) { 
        	DISTANCE_Y = MAP_HEIGHT;
        }
    }
	public double validMoveLeft(int x) {
		if(DISTANCE_X <= 0 || DISTANCE_X > 5000) {
			return 0;	
		}
		else {
			DISTANCE_X-=10;
		}
		return -7;
	}
	public double validMoveRight(int x) {
		if(DISTANCE_X < 0 || DISTANCE_X > 4670) {
			return 0;	
		}
		else {
			DISTANCE_X+=10;
		}
		return 7;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		player.update();
		//loc = player.getLocation();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {	
			if (DISTANCE_X < 900 || DISTANCE_X > 4200) {
				player.movePlayer(validMoveRight(DISTANCE_X),0);
			}
			else {
				player.movePlayer(1,0);
				Background.setLocation(START_LOCATION_X + (i -= 10),START_LOCATION_Y);
				DISTANCE_X+=10;
				
			}
			checkOOB();
		}
		else if (key == KeyEvent.VK_LEFT) {
			//player.updateVel(0, -5);
//			player.update();
			checkOOB();
			if (DISTANCE_X < 900 || DISTANCE_X > 4200) {
				player.movePlayer(validMoveLeft(DISTANCE_X),0);
			}
			else {
				player.movePlayer(-1,0);
				Background.setLocation(START_LOCATION_X + (i += 10),START_LOCATION_Y);
				DISTANCE_X-=10;
				
			}
			
		}
	
		if (key == KeyEvent.VK_SPACE) {
			player.updateVel(0, -5);
			//if ()
		}
		// CAN ALWAYS CHANGE FROM 'D' to fire. 
		if (key == KeyEvent.VK_D) {
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		player.updateVel(0, 0);
	}
}

