import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import java.io.*;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.graphics.GRectangle;


public class GraphicsGame extends GraphicsPane implements KeyListener, ActionListener {
	boolean [] FruitLog = new boolean[4];
	/*STRAWBERRY(""), 
	BANANA(""),
	MELON(""),
	PINEAPPLE(""),*/
	Collection CollectionMenu;
	Player player;
	ArrayList<Enemy> arrayOfEnemies = new ArrayList<Enemy>();
//	boolean flagForEasy, flagForMedium, flagForHard flagForBoss = false;
	boolean flagForEasy = false;
	boolean flagForMedium = false;
	boolean flagForHard = false;
	boolean flagForBoss = false;
	int easyCounter, mediumCounter, hardCounter, bossCounter = 0;
//	Health health;
	Timer timer;
	Weapon weapon;
	Location loc;
	MainApplication program;
	Music music;
	private Projectiles projectile;
	
	private GImage Background;
	private int counter = 0;
	private GRect FLOOR_1,FLOOR_2, FLOOR_2_1, FLOOR_3,FLOOR_4,FLOOR_4_1,FLOOR_4_2, FLOOR_5, FLOOR_6, FLOOR_7;
	private int jumpCool;
	private boolean jumpC = false;
	private GLabel scoreCount;
	public boolean isPlayerDead = false;
	
	
	public static final int MAP_WIDTH = 9174;//800;
	public static final int MAP_HEIGHT = 3018;//600;
	public static final int START_LOCATION_X = -400;//800;
	public static final int START_LOCATION_Y = -1000;//600;
	public static int DISTANCE_X;
	ArrayList<GRect> bounds;
	
	public void UpdateLog(int x) {
		if (FruitLog[x] == false) {
			FruitLog[x] = true;
			CollectionMenu.UpdateCollection(x);
		}
		
	}
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<Enemy> getEnemyArr() { return arrayOfEnemies; }
	
	public boolean [] getLog() {
		return FruitLog;
	}
	
	public GLabel getScoreLabel() { return scoreCount; } 
	
	public void removeEverything() { program.removeAll(); }
	
	GraphicsGame(CollectionMenu collection, MainApplication  program){
		bounds = new ArrayList<GRect>();
		
		this.program = program;
		Background = new GImage("media/fruits/map.png" , 0 ,0);
		Background.setSize((MAP_WIDTH),MAP_HEIGHT );
		Background.setLocation(START_LOCATION_X -300,START_LOCATION_Y);
		
		
		FLOOR_1 = new GRect(0, 643, 1500, 300);
		FLOOR_1.setFilled(true);
		FLOOR_1.setFillColor(Color.blue);
		FLOOR_1.setVisible(false);
		
		FLOOR_2 = new GRect(5000, 643, 1380, 300); //640 - 200 FOR ALL
		FLOOR_2.setFilled(true);
		FLOOR_2.setFillColor(Color.red);
		FLOOR_2.setVisible(false);
		
		FLOOR_2_1 = new GRect(1460, 645, 100, 300);
		
		
		FLOOR_3 = new GRect(1560,580, 130,300);
		FLOOR_3.setFilled(true);
		FLOOR_3.setFillColor(Color.black);
		
		FLOOR_4 = new GRect(1690,645, 385,300);
		FLOOR_4.setFilled(true);
		FLOOR_4.setFillColor(Color.yellow);
		
		FLOOR_4_1 = new GRect(2075, 615, 130,300);
		FLOOR_4_1.setFilled(true);
		FLOOR_4_1.setFillColor(Color.red);
		
		FLOOR_4_2 = new GRect(2205, 645, 1305,300);
		FLOOR_4_2.setFilled(true);
		FLOOR_4_2.setFillColor(Color.white);
		
		FLOOR_5 = new GRect(3510, 675, 350,300);
		FLOOR_5.setFilled(true);
		FLOOR_5.setFillColor(Color.orange);
		
		FLOOR_6 = new GRect(3860,705, 325,300);
		FLOOR_7 = new GRect(4110,672, 490,300);
		
	
		
		FLOOR_1.setVisible(false);
		FLOOR_2.setVisible(false);
		FLOOR_2_1.setVisible(false);
		FLOOR_3.setVisible(false);
		FLOOR_4.setVisible(false);
		FLOOR_4_1.setVisible(false);
		FLOOR_4_2.setVisible(false);
		FLOOR_5.setVisible(false);
		FLOOR_6.setVisible(false);
		FLOOR_7.setVisible(false);
		
		
		bounds.add(FLOOR_1);
		bounds.add(FLOOR_2);
		
		
		///this.CollectionMenu = collection;
		player = new Player (program , 0 ,510,3 ,50);
		weapon = new Weapon(program, 1,"banana.png"); //CHANGE ONCE RESCALED - JT
		scoreCount = new GLabel("Score: " + player.getScore());
		scoreCount.setLocation(10,50);
		scoreCount.setFont(Font.SERIF);
		scoreCount.scale(5, 5);
		try {
			music = new Music(program);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		health = new Health(program, 3, 1100, -88, player);
//		health.playerGetsHitOnce(player);
//		health.playerGetsHitTwice(player);
		
		// ADDING TWO ENEMIES BEWTEEN TWO POINTS
		
//		arrayOfEnemies.add(new Enemy(program, new Location(100, 510), Difficulty.EASY, false));
//		arrayOfEnemies.add(new Enemy(program, new Location(700, 510), Difficulty.EASY, false));
		
		//projectile = new Projectiles(program, "media/fruits/banana.png", Direction.WEST, 1);
		///enemy1 = new Enemy(program, new Location(300, 515), Difficulty.MEDIUM, false);
	}
	public boolean collision(GRectangle boxA, GRectangle boxB) {
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
	
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(Background);
		program.add(FLOOR_1);
		program.add(FLOOR_2);
		program.add(FLOOR_2_1);
		program.add(FLOOR_3);
		program.add(FLOOR_4);
		program.add(FLOOR_4_1);
		program.add(FLOOR_4_2);
		program.add(FLOOR_5);
		program.add(FLOOR_6);
		program.add(FLOOR_7);
		
		program.add(scoreCount);
		
	
		
		player.show();
		weapon.show();
		player.showHealth();
		//player.showScore();
		//arrayOfEnemies.add(new Enemy showScore();
		//need to figure out how to show enemy score
		
//		health.showFirstHealth();
		
		
		timer = new Timer (10 , this);
		timer.start();
		
		///enemy1.show();
		
		for (Enemy e:arrayOfEnemies) {
			e.show();
			e.startTimer();
		}
		
		///Start game loop
		
	
//		for (Enemy enemy:arrayOfEnemies) {
//			enemy.show();
//			
//			enemy.startTimer();
//			while (enemy.isEnemyAlive() == true) {
//				// add check to see if player is still alive here, if not then break out of while loop and for loop and show end game screen
//				;
//			} 
//			enemy.pauseTimer();
//			
//		}
		
		
		

	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
//		program.remove(Background2);
		program.remove(Background);
		program.remove(FLOOR_1);
		program.remove(FLOOR_2);
		program.remove(FLOOR_2_1);
		program.remove(FLOOR_3);
		program.remove(FLOOR_4);
		program.remove(FLOOR_4_1);
		program.remove(FLOOR_4_2);
		program.remove(FLOOR_5);
		program.remove(FLOOR_6);
		program.remove(FLOOR_7);
		

		
		player.hide();
		weapon.hide();
		///enemy1.hide();

	}
	
	private void checkOOB() {
		DISTANCE_X = (int) player.getX();
        if(DISTANCE_X < 0) {
        	player.getImage().setLocation(0, player.getY());
        }
        if(DISTANCE_X > 850) { 
        	player.getImage().setLocation(850, player.getY());
        }
    }
	//
	
	public double validMoveLeft(int x) {
		if(DISTANCE_X <= 0) {
			return 0;	
		}
		return -7;
	}
	public double validMoveRight(int x) {
		if( DISTANCE_X >= 1370) {
			return 0;
		}
		return 7;
	}
	public void wave() {
		// CREATE LEFT SIDE SPAWN 
		
		Random r = new Random();
		int low = 1400;
		int high = 1600;
		int result = r.nextInt(high-low) + low;
		
		//  CREATE RIGHT SIDE SPAWN
		Random r2 = new Random();
		int low2 = 700;
		int high2 = 1000;
		int result2 = r2.nextInt(high2-low2) + low2;
		
		// ADDS NEW ENEMIES TO WAVE
		if (flagForEasy == false) {
			arrayOfEnemies.add(new Enemy(program, new Location(result, 540), Difficulty.EASY, false));
			easyCounter++;
			if (easyCounter == 3) { flagForEasy = true; }
		} else if (flagForMedium == false) {
			arrayOfEnemies.add(new Enemy(program, new Location(result, 540), Difficulty.MEDIUM, false));
			mediumCounter++;
			if (mediumCounter == 3) { flagForMedium = true; }
		} else if (flagForHard == false) {
			arrayOfEnemies.add(new Enemy(program, new Location(result, 540), Difficulty.HARD, false));
			hardCounter++;
			if (hardCounter == 3) { flagForHard = true; }
		} else if (flagForBoss == false) {
			arrayOfEnemies.add(new Enemy(program, new Location(result, 540), Difficulty.EASY, true));
			bossCounter++;
			if (bossCounter == 1) { flagForBoss = true; }
		}
		
		if (flagForEasy == true && flagForMedium == true && flagForHard == true && flagForBoss) {
			;
		}
		
//		arrayOfEnemies.add(new Enemy(program, new Location(result2, 540), Difficulty.EASY, false));
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		music.play();
		counter++;
		player.updateYVel(2);
		getBounds();
		player.update();
		if( counter % 300 == 0 && isPlayerDead == false) {
			wave();
			for (Enemy enem:arrayOfEnemies) {
				enem.show();
				enem.startTimer();
			}
			
		}
		if(player.getHasJumped() == true) {
			if( counter % 38 == 0) {
				player.setHasJumped(false);
			}
			else {
				player.setHasJumped(true);
			}
		}
		return;
	}
		
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (isPlayerDead == true) { return; }
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			player.setPlayerDirectionSprite(Direction.EAST);
			player.movePlayer(5, 0);
			player.updateXVel(2);
			checkOOB();
		}
			
		else if (key == KeyEvent.VK_LEFT) {
			player.setPlayerDirectionSprite(Direction.WEST);
			player.movePlayer(-5, 0);
			player.updateXVel(-2);
			checkOOB();	
		}
		if (key == KeyEvent.VK_SPACE) {
			if(player.getHasJumped() == false) {
				player.updateYVel(-30.0);
				player.setHasJumped(true);	
			}
			return;			
		}
		// CAN ALWAYS CHANGE FROM 'D' to fire. 
		if (key == KeyEvent.VK_F) {
			if (player.getCanShoot() == true) {
				player.setCanShoot(false);
				Location originLocation = new Location((int)player.getImage().getX(), (int)player.getImage().getY());
				if (player.getDirection() == Direction.EAST) {
					Location targetLocation = new Location(1920, (int)player.getImage().getY());
					new Projectiles(program, originLocation, targetLocation, 2, "banana_b", true);
//					System.out.println("Created Monkey Bullet");
//					System.out.println("Monkey Location: x= " + player.getImage().getX() + " y=" + player.getImage().getY());
				} else if (player.getDirection() == Direction.WEST) {
					Location targetLocation = new Location(0, (int)player.getImage().getY());
					new Projectiles(program, originLocation, targetLocation, 2, "banana_b", true);
//					System.out.println("Created Monkey Bullet");
//					System.out.println("Monkey Location: x= " + player.getImage().getX() + " y=" + player.getImage().getY());
					
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//player.updateXVel(0);
		//player.movePlayer(0, 0);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("x: "+ e.getX()+ ",y:" + e.getY());
	}
	public void getBounds() {
		
		GRectangle playerDY = player.getImage().getBounds();
		playerDY.setLocation(playerDY.getX(), playerDY.getY() + player.getYVel());
		
		GRectangle playerDX = player.getImage().getBounds();
		playerDX.setLocation(playerDX.getX() + player.getXVel(), playerDX.getY());
		
		for(int i = 0; i < bounds.size(); i++) {
			if(player.getYVel() == 0) {
				break;
			}
			if(collision(playerDY , bounds.get(i).getBounds())) {
				//check to see if player was above box previously
				if(bounds.get(i).getY() > player.getY() + player.getHeight() ) {
					player.getImage().setLocation(player.getX(), bounds.get(i).getY() - player.getHeight() - 1);
					player.setVelY(0);
					//HITBOX.setLocation(HITBOX.getX(), HITBOX.getY() - player.getHeight() - 1)
//					System.out.println("Hit top of a block\n");
				}
				//player hits box from below
				else if(player.getY() > bounds.get(i).getY() + bounds.get(i).getHeight()) {
					player.getImage().setLocation(player.getX(), bounds.get(i).getY() + bounds.get(i).getHeight() + 1 );
					player.setVelY(0);
//					System.out.println("Hit bottom of block \n");
					
					
				}
			}
		}
		for(int i = 0; i < bounds.size(); i++) {
			if(collision(playerDX , bounds.get(i).getBounds())) {
				if(player.getXVel() == 0) {
					return;
				}
				//player hit box to the right
				if(player.getX() > bounds.get(i).getX() + bounds.get(i).getWidth()) {
					player.getImage().setLocation(bounds.get(i).getX() + bounds.get(i).getWidth()+ 1 + player.getWidth(), player.getY());
					player.setVelX(0);
//					System.out.println("Hit block right \n");
				}
				else if(player.getX() + player.getWidth() < bounds.get(i).getX() -1){
					player.getImage().setLocation (bounds.get(i).getX() - player.getImage().getWidth() -1, playerDX.getY());
					player.setVelX(0);
//					System.out.println("Hit block left \n");
				}
			}
		}
		
	}
	
	
}