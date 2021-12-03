import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import acm.graphics.GImage;
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
	Health health;
	Timer timer;
	Weapon weapon;
	Location loc;
	MainApplication program;
	private Projectiles projectile;
	
	private GImage Background, Background2;
	private GRect FLOOR_1,FLOOR_2, FLOOR_2_1, FLOOR_3,FLOOR_4,FLOOR_4_1,FLOOR_4_2, FLOOR_5, FLOOR_6, FLOOR_7;
	private GRect TOP_1,TOP_2,TOP_3, TOP_3_1, TOP_3_2, TOP_4, TOP_4_1, TOP_4_2, TOP_5, TOP_6, TOP_7;
	private GRect WALL_1, WALL_2, WALL_3, WALL_4, WALL_5, WALL_6, WALL_7,WALL_8, WALL_9, WALL_10, WALL_11, WALL_12, WALL_13, WALL_14, WALL_15, WALL_16, WALL_17, WALL_18;
	private GRect HITBOX , GOAL;
	
	public static final int MAP_WIDTH = 9174;//800;
	public static final int MAP_HEIGHT = 3018;//600;
	public static final int START_LOCATION_X = -400;//800;
	public static final int START_LOCATION_Y = -1000;//600;
	public int i,j,k,l,m,n,o,p,q,r,s= 0;
	public int i2,j2,k2,l2,m2,n2,o2,p2,q2,r2,s2, t2, u2, v2 = 0;
	public int i3,j3,k3,l3,m3,n3,o3,p3,q3,r3,s3, t3, u3, v3, w3, x3, y3, z3 = 0;
	public int e1, e2, e3 = 0;
	public int MOVEMENT, S_MOVEMENT;
	public int GRAVITY = 10;
	public boolean CAN_SCROLL = false;
	public static int DISTANCE_X,DISTANCE_Y = 0;
	public static int TEMP_D;
	public static int dx,dy;
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
	GraphicsGame(CollectionMenu collection, MainApplication  program){
		bounds = new ArrayList<GRect>();
		
		this.program = program;
		Background = new GImage("media/fruits/map.png" , 0 ,0);
		//Background.setSize((MAP_WIDTH),MAP_HEIGHT );
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
		
		TOP_1 = new GRect(0,0,455, 400);
		TOP_2 = new GRect(455, 0, 1005, 400); //640 - 200 FOR ALL
		TOP_3 = new GRect(1460,0, 100,400);
		TOP_3_1 = new GRect(1560,0, 200,400);
		TOP_3_2 = new GRect(1760,0, 100,400);
		TOP_4 = new GRect(1860,0, 100,400);
		
		TOP_4_1 = new GRect(1960,0,220,400);
		TOP_4_1.setFilled(true);
		TOP_4_1.setFillColor(Color.red);
		TOP_4_2 = new GRect(2280,0,1310,400);
		TOP_5 = new GRect(3590, 0, 350,450);
		TOP_5.setFilled(true);
		TOP_6 = new GRect(3860,502, 245,100);
		TOP_7 = new GRect(4185,0, 490,100);
		
		WALL_1 = new GRect(375,625, 10,40);
		WALL_2 = new GRect(465,625, 10,40);
		WALL_3 = new GRect(1560,625, 10,40);
		WALL_4 = new GRect(1680,625, 10,40);
		WALL_4.setFilled(true);
		WALL_4.setFillColor(Color.red);
		WALL_5 = new GRect(1780,625, 10,40);
		WALL_5.setFilled(true);
		WALL_5.setFillColor(Color.GREEN);
		WALL_6 = new GRect(1680,625, 10,40);
		WALL_6.setFilled(true);
		WALL_6.setFillColor(Color.cyan);
		WALL_7 = new GRect(2060,625, 10,40);
		WALL_7.setFilled(true);
		WALL_7.setFillColor(Color.yellow);
		WALL_8 = new GRect(2180,625, 10,40);
		WALL_9 = new GRect(1880,625, 10,40);
		WALL_10 = new GRect(1680,625, 10,40);
		WALL_11 = new GRect(5000,625, 10,40);
		WALL_12 = new GRect(5000,625, 10,40);
		WALL_13 = new GRect(5000,625, 10,40);
		WALL_14 = new GRect(5000,625, 10,40);
		WALL_15 = new GRect(5000,625, 10,40);
		WALL_16 = new GRect(5000,625, 10,40);
		WALL_17 = new GRect(5000,625, 10,40);
		WALL_18 = new GRect(5000,625, 10,40);
		
		FLOOR_1.setVisible(true);
		FLOOR_2.setVisible(true);
		FLOOR_2_1.setVisible(true);
		FLOOR_3.setVisible(true);
		FLOOR_4.setVisible(true);
		FLOOR_4_1.setVisible(true);
		FLOOR_4_2.setVisible(true);
		FLOOR_5.setVisible(true);
		FLOOR_6.setVisible(true);
		FLOOR_7.setVisible(true);
		TOP_1.setVisible(true);
		TOP_2.setVisible(true);
		TOP_3.setVisible(true);
		TOP_3_1.setVisible(true);
		TOP_3_2.setVisible(true);
		TOP_4.setVisible(true);
		
		TOP_4_1.setVisible(true);
		TOP_4_2.setVisible(true);
		TOP_5.setVisible(true);
		TOP_6.setVisible(true);
		TOP_7.setVisible(true);
		
		WALL_1.setVisible(true);
		WALL_2.setVisible(true);
		WALL_3.setVisible(true);
		WALL_4.setVisible(true);
		WALL_5.setVisible(true);
		WALL_6.setVisible(true);
		WALL_7.setVisible(true);
		WALL_8.setVisible(true);
		WALL_9.setVisible(true);
		WALL_10.setVisible(false);
		WALL_11.setVisible(false);
		WALL_12.setVisible(false);
		WALL_13.setVisible(false);
		WALL_14.setVisible(false);
		WALL_15.setVisible(false);
		WALL_16.setVisible(false);
		WALL_17.setVisible(false);
		WALL_18.setVisible(false);
		
		bounds.add(FLOOR_1);
		bounds.add(FLOOR_2);
		//bounds.add(FLOOR_2_1);
		/*bounds.add(FLOOR_3);
		bounds.add(FLOOR_4);
		bounds.add(FLOOR_4_1);
		bounds.add(FLOOR_4_2);
		bounds.add(FLOOR_6);
		bounds.add(FLOOR_7);*/
		
		
	
		
//		FLOOR_1.setVisible(false);
		HITBOX = new GRect(500,510,100,100);
		GOAL = new GRect(4200, 0, 100, 9000);
	
		
		
		
		///this.CollectionMenu = collection;
		player = new Player (program , 0 ,510 ,600 ,50);
		weapon = new Weapon(program, 1,"banana.png"); //CHANGE ONCE RESCALED - JT
		health = new Health(program, 3, 1100, -88, player);
//		health.playerGetsHitOnce(player);
//		health.playerGetsHitTwice(player);
		
		arrayOfEnemies.add(new Enemy(program, new Location(1600, 510), Difficulty.EASY, false));
//		arrayOfEnemies.add(new Enemy(program, new Location(900, 510), Difficulty.HARD, false));
		
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
//		program.add(Background2);
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
		
		/*program.add(TOP_1);
		program.add(TOP_2);
		program.add(TOP_3);
		program.add(TOP_3_1);
		program.add(TOP_3_2);
		program.add(TOP_4);
		program.add(TOP_4_1);
		program.add(TOP_4_2);
		program.add(TOP_5);
		program.add(TOP_6);
		program.add(TOP_7);
		
		program.add(WALL_1);
		program.add(WALL_2);
		program.add(WALL_3);
		program.add(WALL_4);
		program.add(WALL_5);
		program.add(WALL_6);
		program.add(WALL_7);
		program.add(WALL_8);
		program.add(WALL_9);
		program.add(WALL_10);
		program.add(WALL_11);
		program.add(WALL_12);
		program.add(WALL_13);
		program.add(WALL_14);
		program.add(WALL_15);
		program.add(WALL_16);*/
		
		player.show();
		weapon.show();
		health.show();
		///enemy1.show();
		program.add(HITBOX);
		program.add(GOAL);
		
		for (Enemy e:arrayOfEnemies) {
			e.show();
			e.startTimer();
		}
		
		///Start game loop
		timer = new Timer (10 , this);
		timer.start();
		
	
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
		
		program.remove(TOP_1);
		program.remove(TOP_2);
		program.remove(TOP_3);
		program.remove(TOP_3_1);
		program.remove(TOP_3_2);
		program.remove(TOP_4);
		program.remove(TOP_4_1);
		program.remove(TOP_4_2);
		program.remove(TOP_5);
		program.remove(TOP_6);
		program.remove(TOP_7);
		
		program.remove(WALL_1);
		program.remove(WALL_2);
		program.remove(WALL_3);
		program.remove(WALL_4);
		program.remove(WALL_5);
		program.remove(WALL_6);
		program.remove(WALL_7);
		program.remove(WALL_8);
		program.remove(WALL_9);
		program.remove(WALL_10);
		program.remove(WALL_11);
		program.remove(WALL_12);
		program.remove(WALL_13);
		program.remove(WALL_14);
		program.remove(WALL_15);
		program.remove(WALL_16);
		
		player.hide();
		weapon.hide();
		///enemy1.hide();
		program.remove(HITBOX);
		program.remove(GOAL);

	}
	
	private void checkOOB() {
        if(DISTANCE_X < 0) {
        	DISTANCE_X = 0;
        }
        if(DISTANCE_X > 4190) { 
        	DISTANCE_X = 4190;
        }
        if(DISTANCE_Y < 0) { 
        	DISTANCE_Y = 0;
        }
        if(DISTANCE_Y > MAP_HEIGHT) { 
        	DISTANCE_Y = MAP_HEIGHT;
        }
    }
	//
	
	public double validMoveLeft(int x) {
		if(DISTANCE_X <= 0 || DISTANCE_X >= 4190) {
			return 0;	
		}
		else {
			DISTANCE_X-=5;
		}
		return -7;
	}
	public double validMoveRight(int x) {
		if(DISTANCE_X < 0 || DISTANCE_X >= 4180) {
			return 0;
		}
		else {
			DISTANCE_X+=5;
		}
		return 7;
	}
	
	///////////////
	private void checkWall() {
        
    }
	public double validWallML(int x) {
		if(DISTANCE_X <= 370 || DISTANCE_X > 380) {
			return 0;
		}
		if(DISTANCE_X <= 1860  || DISTANCE_X > 1865) {
			return 0;
		}
		if(DISTANCE_X <= 2210  || DISTANCE_X > 2215) {
			return 0;
		}
		if(DISTANCE_X <= 3510  || DISTANCE_X > 3515) {
			return 0;
		}
		if(DISTANCE_X <= 3850  || DISTANCE_X > 3865) {
			return 0;
		}

		DISTANCE_X-=5;
		return -7;
		
	}
	public double validWallMR(int x) {
		if(DISTANCE_X <= 1660  || DISTANCE_X > 1675) {
			return 0;
		}
		if(DISTANCE_X <= 2060  || DISTANCE_X > 2065) {
			return 0;
		}
		if(DISTANCE_X <= 3840  || DISTANCE_X > 3845) {
			return 0;
		}
		DISTANCE_X+=5;
		return 7;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//player.update();
		//loc = player.getLocation();
		player.updateYVel(2);
		getBounds();
		player.update();
		return;
		/*GRAVITY = 2;
		player.movePlayer(0, player.getYVel());
		HITBOX.move(0,player.getYVel());
		player.updateYVel(player.getYVel() + GRAVITY);
		if(collision(HITBOX, FLOOR_1) == true) {
			
			player.movePlayer(0, -GRAVITY);
			HITBOX.move(0,-GRAVITY);
			player.updateYVel(0);
			player.setHasJumped(false);
			DISTANCE_Y -= 70;
			
			if(collision(HITBOX, TOP_2) == true) {
				player.movePlayer(0, GRAVITY);
				HITBOX.move(0,GRAVITY);
				player.updateYVel(0);
				player.setHasJumped(false);
				
				if(collision(HITBOX, FLOOR_2) == true) {
					player.movePlayer(0, -GRAVITY);
					HITBOX.move(0, -GRAVITY);
					player.updateYVel(0);
					
					if(collision(HITBOX, TOP_3) == true) {
						player.movePlayer(0, GRAVITY);
						HITBOX.move(0,GRAVITY);
						
						if(collision(HITBOX, FLOOR_2) == true ) {
							player.movePlayer(0, -GRAVITY);
							HITBOX.move(0,-GRAVITY);
							
							if(collision(HITBOX, TOP_3) == true) {
								player.movePlayer(0, GRAVITY);
								HITBOX.move(0,GRAVITY);
								
								if(collision(HITBOX, FLOOR_2_1) == true) {
									player.movePlayer(0, -GRAVITY);
									HITBOX.move(0,-GRAVITY);
									if(collision(HITBOX, TOP_3_1) == true) {
										player.movePlayer(0, GRAVITY);
										HITBOX.move(0, GRAVITY);			
									}
								}	
							}
						}
					}
				}
			}
		}
		if(collision(HITBOX, FLOOR_3) == true) {
			player.movePlayer(0, -GRAVITY);
			HITBOX.move(0, -GRAVITY);
			if(collision(HITBOX, TOP_3_2) == true) {
				player.movePlayer(0, GRAVITY);
				HITBOX.move(0, GRAVITY);
				if(collision(HITBOX, FLOOR_4) == true) {
					player.movePlayer(0, -GRAVITY);
					HITBOX.move(0, -GRAVITY);
					if(collision(HITBOX, TOP_4)) {
						player.movePlayer(0, GRAVITY);
						HITBOX.move(0, GRAVITY);
						if(collision(HITBOX, FLOOR_4) == true) {
							player.movePlayer(0, -GRAVITY);
							HITBOX.move(0, -GRAVITY);
							if(collision(HITBOX, TOP_4_1) == true) {
								player.movePlayer(0, GRAVITY);
								HITBOX.move(0, GRAVITY);
								if(collision(HITBOX, FLOOR_4) == true) {
									player.movePlayer(0, -GRAVITY);
									HITBOX.move(0, -GRAVITY);
									if(collision(HITBOX, TOP_5) == true) {
										player.movePlayer(0, GRAVITY);
										HITBOX.move(0, GRAVITY);
									}
								}
							}
						}
					}
				}
			}
		}
		if(collision(HITBOX, FLOOR_4_1) == true) {
			player.movePlayer(0, -GRAVITY);
			HITBOX.move(0, -GRAVITY);
			if(collision(HITBOX, TOP_4_2) == true) {
				player.movePlayer(0, GRAVITY);
				HITBOX.move(0, GRAVITY);
				if(collision(HITBOX, FLOOR_4_2) == true) {
					player.movePlayer(0, -GRAVITY);
					HITBOX.move(0, -GRAVITY);
					if(collision(HITBOX, TOP_5) == true) {
						player.movePlayer(0, GRAVITY);
						HITBOX.move(0, GRAVITY);
						if(collision(HITBOX, FLOOR_5) == true) {
							player.movePlayer(0, -GRAVITY);
							HITBOX.move(0, -GRAVITY);
							if(collision(HITBOX, TOP_6) == true) {
								player.movePlayer(0, GRAVITY);
								HITBOX.move(0, GRAVITY);
								if(collision(HITBOX, FLOOR_6) == true) {
									player.movePlayer(0, -GRAVITY);
									HITBOX.move(0, -GRAVITY);
									if(collision(HITBOX, TOP_7) == true) {
										player.movePlayer(0, GRAVITY);
										HITBOX.move(0, GRAVITY);
									}
								}
							}
						}
					}
				}
			}
		}
		if(collision(HITBOX, FLOOR_7) == true) {
			player.movePlayer(0, -GRAVITY);
			HITBOX.move(0, -GRAVITY);
		}
//		System.out.println(DISTANCE_X);*/
	}
		
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			player.setPlayerDirectionSprite(Direction.EAST);
			player.updateXVel(2);
			checkOOB();
			checkWall();
			if (DISTANCE_X < 900 || DISTANCE_X > 4190) {
				
				player.movePlayer(validMoveRight(DISTANCE_X),0);
				player.updateXVel(7);
				HITBOX.move(validMoveRight(DISTANCE_X),0);
				//System.out.println("OKAY");
			} 
			// MOVEMENT RIGHT
//			else {
//				
//				if(collision(HITBOX, WALL_3) == true && collision(HITBOX, WALL_4) != true) {
//					player.movePlayer(validWallMR(DISTANCE_X),0);
//					HITBOX.move(validWallMR(DISTANCE_X), 0);
//					player.updateXVel(0);
//					//System.out.println("HERE ---11");
//				}
//				else if(collision(HITBOX, WALL_7) == true && collision(HITBOX, WALL_8) != true) {
//					player.movePlayer(validWallMR(DISTANCE_X),0);
//					HITBOX.move(validWallMR(DISTANCE_X), 0);
//					player.updateXVel(0);
//					//System.out.println("HERE --1");
//				}
//				else if(collision(HITBOX, WALL_15) == true && collision(HITBOX, WALL_16) != true) {
//					player.movePlayer(validWallMR(DISTANCE_X),0);
//					HITBOX.move(validWallMR(DISTANCE_X), 0);
//					player.updateXVel(0);
//					//System.out.println("HERE 1");
//				}
//				else if(collision(HITBOX, GOAL) == true) {
//					hideContents();
//				}
				
				
//				else {
//				
//					//System.out.println("OVER HERE 1");
//					player.movePlayer(1,0);
//					player.updateXVel(7);
//					HITBOX.move(1,0);
//					//				
//					Background.setLocation(START_LOCATION_X + (i -= 10),START_LOCATION_Y);
//					Background2.setLocation((v2 -= 10)+ 4400,START_LOCATION_Y);
//					FLOOR_1.setLocation(0 + (j -= 10),610);
//					FLOOR_2.setLocation(375 + (k -= 10),645);
//					FLOOR_2_1.setLocation(1460 + (q -= 10),645);
//					FLOOR_3.setLocation(1560 + (l -= 10),585);
//					FLOOR_4.setLocation(1690 + (m -= 10),645);
//					FLOOR_4_1.setLocation(2075 + (r -= 10), 615);
//					FLOOR_4_2.setLocation(2205 + (s -= 10), 645);
//					FLOOR_5.setLocation(3510 + (n -= 10),675);
//					FLOOR_6.setLocation(3860 + (o -= 10),715);
//					FLOOR_7.setLocation(4185 + (p -= 10),675);
//					
//					TOP_1.setLocation((j2 -= 10),0);
//					TOP_2.setLocation(455 + (k2 -= 10),0);
//					TOP_3.setLocation(1460 + (l2 -= 10),0);
//					TOP_3_1.setLocation(1560 + (q2 -= 10),0);
//					TOP_3_2.setLocation(1760 + (r2 -= 10),0);
//					TOP_4.setLocation(1860 + (m2 -= 10),0);
//					TOP_4_1.setLocation(1960 + (s2 -= 10),0);
//					TOP_4_2.setLocation(2280+ ( t2 -= 10), 0);
//					TOP_5.setLocation(3590 + (n2 -= 10),0);
//					TOP_6.setLocation(3940 + (o2 -= 10), 0);
//					TOP_7.setLocation(4185 + (p2 -= 10),0);
//					
//					GOAL.setLocation(4740 + (u2 -= 10),0);
//					
//					
//					WALL_1.setLocation(365 + (j3 -= 10), 610);
//					WALL_2.setLocation(465 + (k3 -= 10), 610);
//					WALL_3.setLocation(1560 + (l3 -= 10), 610);
//					WALL_4.setLocation(1680 + (m3 -= 10), 610);
//					WALL_5.setLocation(1680 + (n3 -= 10), 610);
//					WALL_6.setLocation(1780 + (o3 -= 10), 610);
//					WALL_7.setLocation(2065 + (p3 -= 10), 615);
//					WALL_8.setLocation(2165 + (q3 -= 10), 615);
//					WALL_9.setLocation(2205 + (r3 -= 10), 615);
//					WALL_10.setLocation(2305 + (s3 -= 10), 615);
//					WALL_11.setLocation(3500 + (t3 -= 10), 645);
//					WALL_12.setLocation(3600 + (u3 -= 10), 645);
//					WALL_13.setLocation(3850 + (w3 -= 10), 675);
//					WALL_14.setLocation(3950 + (x3 -= 10), 675);
//					WALL_15.setLocation(4185 + (y3 -= 10), 675);
//					WALL_16.setLocation(4285 + (z3 -= 10), 675);
//					
//					///enemy1.moveEnemy(300 + (e1 -= 10), 515);
//					
//					
//					DISTANCE_X+=10;
//				}
				
			}
			
		else if (key == KeyEvent.VK_LEFT) {
			player.setPlayerDirectionSprite(Direction.WEST);
			player.updateXVel(-2);
			//player.updateVel(0, -5);
//			player.update();
			checkOOB();
			checkWall();
			if (DISTANCE_X < 900 || DISTANCE_X > 4190) {
				if(collision(HITBOX, WALL_1) == true & collision(HITBOX, WALL_2) != true) {
					player.movePlayer(validWallML(DISTANCE_X),0);
					HITBOX.move(validWallML(DISTANCE_X), 0);
					player.updateXVel(0);
				}
				
				else {// if(collision(HITBOX, WALL_2) == true){
					player.movePlayer(validMoveLeft(DISTANCE_X),0);
					player.updateXVel(-7);
					HITBOX.move(validMoveLeft(DISTANCE_X),0);
					//
				}
			}
			else {
				if(collision(HITBOX, WALL_4) == true & collision(HITBOX, WALL_6) != true) {
					player.movePlayer(validWallML(DISTANCE_X),0);
					HITBOX.move(validWallML(DISTANCE_X), 0);
					player.updateXVel(0);	
				}
				else if(collision(HITBOX, WALL_9) == true & collision(HITBOX, WALL_10) != true) {
					player.movePlayer(validWallML(DISTANCE_X),0);
					HITBOX.move(validWallML(DISTANCE_X), 0);
					player.updateXVel(0);
				}
				else if(collision(HITBOX, WALL_11) == true & collision(HITBOX, WALL_12) != true) {
					player.movePlayer(validWallML(DISTANCE_X),0);
					HITBOX.move(validWallML(DISTANCE_X), 0);
					player.updateXVel(0);
				}
				else if(collision(HITBOX, WALL_13) == true & collision(HITBOX, WALL_14) != true) {
					player.movePlayer(validWallML(DISTANCE_X),0);
					HITBOX.move(validWallML(DISTANCE_X), 0);
					player.updateXVel(0);
				}
			}
		}
//		else if(collision(HITBOX, WALL_15) == true & collision(HITBOX, WALL_16) != true) {
//					player.movePlayer(validWallML(DISTANCE_X),0);
//					HITBOX.move(validWallML(DISTANCE_X), 0);
//					player.updateVel(0);
//				}
//				else {
//					player.movePlayer(-1,0);
//					player.updateXVel(-7);
//					HITBOX.move(-1,0);
//					
//					Background.setLocation(START_LOCATION_X + (i += 10),START_LOCATION_Y);
//					Background2.setLocation((v2 += 10)+ 4400,START_LOCATION_Y);
//					
//					FLOOR_1.setLocation(0 + (j += 10),610);
//					FLOOR_2.setLocation(375 + (k += 10),645);
//					FLOOR_2_1.setLocation(1460 + (q += 10),645);
//					FLOOR_3.setLocation(1560 + (l += 10),585);
//					FLOOR_4.setLocation(1690 + (m += 10),645);
//					FLOOR_4_1.setLocation(2075 + (r += 10), 615);
//					FLOOR_4_2.setLocation(2205 + (s += 10), 645);
//					FLOOR_5.setLocation(3510 + (n += 10),675);
//					FLOOR_6.setLocation(3860 + (o += 10),715);
//					FLOOR_7.setLocation(4185 + (p += 10),675);
//					
//					TOP_1.setLocation((j2 += 10),0);
//					TOP_2.setLocation(455 + (k2 += 10),0);
//					TOP_3.setLocation(1460 + (l2 += 10),0);
//					TOP_3_1.setLocation(1560 + (q2 += 10),0);
//					TOP_3_2.setLocation(1760 + (r2 += 10),0);
//					TOP_4.setLocation(1860 + (m2 += 10),0);
//					TOP_4_1.setLocation(1960 + (s2 += 10),0);
//					TOP_4_2.setLocation(2280+ ( t2 += 10), 0);
//					TOP_5.setLocation(3590 + (n2 += 10),0);
//					TOP_6.setLocation(3940 + (o2 += 10), 0);
//					TOP_7.setLocation(4185 + (p2 += 10),0);
//					
//					GOAL.setLocation(4740 + (u2 += 10),0);
//					
//					
//					WALL_1.setLocation(365 + (j3 += 10), 610);
//					WALL_2.setLocation(465 + (k3 += 10), 610);
//					WALL_3.setLocation(1560 + (l3 += 10), 610);
//					WALL_4.setLocation(1680 + (m3 += 10), 610);
//					WALL_5.setLocation(1680 + (n3 += 10), 610);
//					WALL_6.setLocation(1780 + (o3 += 10), 610);
//					WALL_7.setLocation(2065 + (p3 += 10), 615);
//					WALL_8.setLocation(2165 + (q3 += 10), 615);
//					WALL_9.setLocation(2205 + (r3 += 10), 615);
//					WALL_10.setLocation(2305 + (s3 += 10), 615);
//					WALL_11.setLocation(3500 + (t3 += 10), 645);
//					WALL_12.setLocation(3600 + (u3 += 10), 645);
//					WALL_13.setLocation(3850 + (w3 += 10), 675);
//					WALL_14.setLocation(3950 + (x3 += 10), 675);
//					WALL_15.setLocation(4185 + (y3 += 10), 675);
//					WALL_16.setLocation(4285 + (z3 += 10), 675);
//					
//					DISTANCE_X-=10;
//
//				}
//			}
		
//		}
	
		if (key == KeyEvent.VK_SPACE) {
			if(!player.getHasJumped()) {
				player.updateYVel(-20.0);
				player.setHasJumped(true);
			}
			return;
			
	/*	DISTANCE_Y = 1;
			GRAVITY = 0;
			playerYVelocity = 40;
			for (int counter = 10; counter < 70; counter+= 10) {
				player.movePlayer(2*player.getVel(),-counter);
			HITBOX.move(2*player.getVel(),-counter);
			DISTANCE_Y +=10;
			}
			GRAVITY = 10;
			DISTANCE_Y -= 70;*/
			
		}
		// CAN ALWAYS CHANGE FROM 'D' to fire. 
		if (key == KeyEvent.VK_F) {
			Location originLocation = new Location((int)player.getImage().getX(), (int)player.getImage().getY());
			if (player.getDirection() == Direction.EAST) {
				Location targetLocation = new Location(1920, (int)player.getImage().getY());
				new Projectiles(program, originLocation, targetLocation, 2, "banana_b", true);
				System.out.println("Created Monkey Bullet");
				System.out.println("Monkey Location: x= " + player.getImage().getX() + " y=" + player.getImage().getY());
			} else if (player.getDirection() == Direction.WEST) {
				Location targetLocation = new Location(0, (int)player.getImage().getY());
				new Projectiles(program, originLocation, targetLocation, 2, "banana_b", true);
				System.out.println("Created Monkey Bullet");
				System.out.println("Monkey Location: x= " + player.getImage().getX() + " y=" + player.getImage().getY());
				
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
//		System.out.println("x: "+ e.getX()+ ",y:" + e.getY());
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