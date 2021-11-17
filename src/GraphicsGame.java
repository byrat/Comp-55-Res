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
	Player player;
	Timer timer;
	Weapon weapon;
	Location loc;
	MainApplication program;
	private Projectiles projectile;
	
	GImage Background;
	private GRect FLOOR_1,FLOOR_2,FLOOR_3,FLOOR_4, FLOOR_5, FLOOR_6, FLOOR_7;
	private GRect TOP_1,TOP_2,TOP_3,TOP_4, TOP_5, TOP_6, TOP_7;
	private GRect HITBOX;
	
	public static final int MAP_WIDTH = 9174;//800;
	public static final int MAP_HEIGHT = 3018;//600;
	public static final int START_LOCATION_X = -400;//800;
	public static final int START_LOCATION_Y = -1000;//600;
	public int i,j,k,l,m,n,o,p= 0;
	public int i2,j2,k2,l2,m2,n2,o2,p2 = 0;
	public int MOVEMENT, S_MOVEMENT;
	public int GRAVITY = 5;
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
		
		FLOOR_1 = new GRect(0, 610, 375, 100);
		FLOOR_2 = new GRect(375, 640, 3135, 300); //640 - 200 FOR ALL
		FLOOR_3 = new GRect(1560,575, 130,100);
		FLOOR_4 = new GRect(2160,605, 130,100);
		FLOOR_5 = new GRect(3510, 672, 350,100);
		FLOOR_6 = new GRect(3860,702, 325,100);
		FLOOR_7 = new GRect(4110,672, 490,100);
		
		TOP_1 = new GRect(0, 400, 435, 100);
		TOP_2 = new GRect(435, 445, 3135, 100); //640 - 200 FOR ALL
		TOP_3 = new GRect(1560,375, 130,100);
		TOP_4 = new GRect(2160,405, 130,100);
		TOP_5 = new GRect(3510, 472, 350,100);
		TOP_6 = new GRect(3860,502, 325,100);
		TOP_7 = new GRect(4110,472, 490,100);
		
//		FLOOR_1.setVisible(false);
		HITBOX = new GRect(0,510,100,100);
	
		
		
		
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
		// IF BOX B IS LARGER THEN FRST BOX.
		if(minXB > maxX || minX > maxXB) return false;
		if(minYB > maxY || minY > maxYB) return false;
		
		return true;
	}
	public  boolean crash(GRect boxA, GRect boxB) {
        
        // To check if either rectangle is actually a line
       // For example :  l1 ={-1,0}  r1={1,1}  l2={0,-1}  r2={0,1}
		double minX = boxA.getX();
		double minY = boxA.getY();
		double maxX = minX + boxA.getWidth();
		double maxY = minY + boxA.getHeight();
		
		double minXB = boxB.getX();
		double minYB = boxB.getY();
		double maxXB = minX + boxB.getWidth();
		double maxYB = minY + boxB.getHeight();
		if	(minX == maxX || minY  == maxY || minXB == maxXB || minYB  == maxYB) {
        // the line cannot have positive overlap
			return false;
        }
 
 
        // If one rectangle is on left side of other
	     if (minX >= maxXB || minXB >= maxX) {
	         return false;
	     }
	
	     // If one rectangle is above other
	     if (maxY >= minYB || maxYB >= minY) {
	         return false;
	     }
	
	     return true;
	 }
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(Background);
		player.show();
		weapon.show();
		program.add(FLOOR_1);
		program.add(FLOOR_2);
		program.add(FLOOR_3);
		program.add(FLOOR_4);
		program.add(FLOOR_5);
		program.add(FLOOR_6);
		program.add(FLOOR_7);
		
		program.add(TOP_1);
		program.add(TOP_2);
		program.add(TOP_3);
		program.add(TOP_4);
		program.add(TOP_5);
		program.add(TOP_6);
		program.add(TOP_7);
		
		program.add(HITBOX);
		
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
        if(DISTANCE_X > 4670) { 
        	DISTANCE_X = 4670;
        }
        if(DISTANCE_Y < 0) { 
        	DISTANCE_Y = 0;
        }
        if(DISTANCE_Y > MAP_HEIGHT) { 
        	DISTANCE_Y = MAP_HEIGHT;
        }
    }
	public double validMoveLeft(int x) {
		if(DISTANCE_X <= 0 || DISTANCE_X > 4670) {
			return 0;	
		}
		else {
			DISTANCE_X-=5;
		}
		return -7;
	}
	public double validMoveRight(int x) {
		if(DISTANCE_X < 0 || DISTANCE_X >= 4670) {
			return 0;	
		}
		else {
			DISTANCE_X+=5;
		}
		return 7;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//player.update();
		//loc = player.getLocation();
		player.movePlayer(0, GRAVITY);
		HITBOX.move(0,GRAVITY);
		if(collision(HITBOX, FLOOR_1) == true) {
			player.movePlayer(0, -5);
			HITBOX.move(0,-5);
			if(collision(HITBOX, TOP_2) == true ) {
				player.movePlayer(0, GRAVITY);
				HITBOX.move(0,GRAVITY);
				if(collision(HITBOX, FLOOR_2) == true){
					player.movePlayer(0, -5);
					HITBOX.move(0,-5);
					if(collision(HITBOX, TOP_5) == true){
						player.movePlayer(0, GRAVITY);
						HITBOX.move(0,GRAVITY);
						if(collision(HITBOX, FLOOR_5) == true){
							player.movePlayer(0, -5);
							HITBOX.move(0,-5); 
						}
					}
				}
			}
		}
		else if(collision(HITBOX, FLOOR_2) == true){
			player.movePlayer(0, -5);
			HITBOX.move(0,-5);
		}
	}
		
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {	
			checkOOB();
			if (DISTANCE_X < 900 || DISTANCE_X > 4200) {
			
				player.movePlayer(validMoveRight(DISTANCE_X),0);
				player.updateVel(5,0);
				HITBOX.move(validMoveRight(DISTANCE_X),0);
			} 
			
			else {
				player.movePlayer(1,0);
				player.updateVel(5,0);
				HITBOX.move(1,0);
				Background.setLocation(START_LOCATION_X + (i -= 10),START_LOCATION_Y);
				FLOOR_1.setLocation(0 + (j -= 10),610);
				FLOOR_2.setLocation(375 + (k -= 10),640);
				FLOOR_3.setLocation(1560 + (l -= 10),575);
				FLOOR_4.setLocation(2070 + (m -= 10),610);
				FLOOR_5.setLocation(3510 + (n -= 10),672);
				FLOOR_6.setLocation(3860 + (o -= 10),704);
				FLOOR_7.setLocation(4185 + (p -= 10),672);
				
				TOP_1.setLocation(0 + (j2 -= 10),400);
				TOP_2.setLocation(435 + (k2 -= 10),445);
				TOP_3.setLocation(1560 + (l2 -= 10),375);
				TOP_4.setLocation(2070 + (m2 -= 10),410);
				TOP_5.setLocation(3510 + (n2 -= 10),430);
				TOP_6.setLocation(3860 + (o2 -= 10),504);
				TOP_7.setLocation(4185 + (p2 -= 10),472);
				
				DISTANCE_X+=10;
				
			}
		}
			
		else if (key == KeyEvent.VK_LEFT) {
			//player.updateVel(0, -5);
//			player.update();
			checkOOB();
			if (DISTANCE_X < 900 || DISTANCE_X > 4200) {
				player.movePlayer(validMoveLeft(DISTANCE_X),0);
				player.updateVel(5,-5);
				HITBOX.move(validMoveLeft(DISTANCE_X),0);
			}
			else {
				player.movePlayer(-1,0);
				player.updateVel(-5,-5);
				HITBOX.move(-1,0);
				Background.setLocation(START_LOCATION_X + (i += 10),START_LOCATION_Y);
				FLOOR_1.setLocation(0 + (j += 10),610);
				FLOOR_2.setLocation(375 + (k += 10),640);
				FLOOR_3.setLocation(1560 + (l += 10),575);
				FLOOR_4.setLocation(2070 + (m += 10),610);
				FLOOR_5.setLocation(3510 + (n += 10),672);
				FLOOR_6.setLocation(3860 + (o += 10),704);
				FLOOR_7.setLocation(4185 + (p += 10),672);
				
				TOP_1.setLocation(0 + (j2 += 10),400);
				TOP_2.setLocation(435 + (k2 += 10),445);
				TOP_3.setLocation(1560 + (l2 += 10),375);
				TOP_4.setLocation(2070 + (m2 += 10),410);
				TOP_5.setLocation(3510 + (n2 += 10),430);
				TOP_6.setLocation(3860 + (o2 += 10),504);
				TOP_7.setLocation(4185 + (p2 += 10),472);
				DISTANCE_X-=10;
				
			}
			
		}
	
		if (key == KeyEvent.VK_SPACE) {
			//player.updateVel(0,5);
			//if ()
			player.movePlayer(0,-5);
			HITBOX.move(0,-5);
		}
		// CAN ALWAYS CHANGE FROM 'D' to fire. 
		if (key == KeyEvent.VK_D) {
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		player.updateVel(0, 0);
		player.movePlayer(0, 0);
	}
}

