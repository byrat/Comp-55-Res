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
	Enemy enemy1;
	Timer timer;
	Weapon weapon;
	Location loc;
	Obstacle obstacle;
	MainApplication program;
	private Projectiles projectile;
	
	GImage Background;
	private GRect FLOOR_1,FLOOR_2, FLOOR_2_1, FLOOR_3,FLOOR_4,FLOOR_4_1,FLOOR_4_2, FLOOR_5, FLOOR_6, FLOOR_7;
	private GRect TOP_1,TOP_2,TOP_3, TOP_3_1, TOP_3_2, TOP_4, TOP_4_1, TOP_4_2, TOP_5, TOP_6, TOP_7;
	private GRect WALL_1, WALL_2, WALL_3, WALL_4;
	private GRect HITBOX;
	
	public static final int MAP_WIDTH = 9174;//800;
	public static final int MAP_HEIGHT = 3018;//600;
	public static final int START_LOCATION_X = -400;//800;
	public static final int START_LOCATION_Y = -1000;//600;
	public int i,j,k,l,m,n,o,p,q,r,s= 0;
	public int i2,j2,k2,l2,m2,n2,o2,p2,q2,r2,s2, t2 = 0;
	public int i3,j3,k3,l3,m3,n3,o3,p3,q3,r3,s3 = 0;
	public int MOVEMENT, S_MOVEMENT;
	public int GRAVITY = 10;
	public boolean CAN_SCROLL = false;
	public static int DISTANCE_X,DISTANCE_Y = 0;
	public static int TEMP_D;
	public static int dx,dy;
	
	public void UpdateLog(int x) {
		if (FruitLog[x] == false) {
			FruitLog[x] = true;
			CollectionMenu.UpdateCollection(x);
		}
		
	}
	public Player getPlayer() {
		return player;
	}
	
	public boolean [] getLog() {
		return FruitLog;
	}
	GraphicsGame(CollectionMenu collection, MainApplication  program){
		this.program = program;
		Background = new GImage("media/fruits/map.png" , 0 ,0);
		Background.setSize((MAP_WIDTH),MAP_HEIGHT );
		Background.setLocation(START_LOCATION_X,START_LOCATION_Y);
		
		FLOOR_1 = new GRect(0, 610, 375, 300);
		FLOOR_2 = new GRect(375, 645, 1085, 300); //640 - 200 FOR ALL
		FLOOR_2_1 = new GRect(1460, 645, 100, 300);
		FLOOR_3 = new GRect(1560,580, 130,300);
		FLOOR_4 = new GRect(1690,645, 385,300);
		FLOOR_4_1 = new GRect(2075, 615, 130,300);
		FLOOR_4_1.setFilled(true);
		FLOOR_4_1.setFillColor(Color.red);
		FLOOR_4_2 = new GRect(2205, 645, 1305,300);
		FLOOR_5 = new GRect(3510, 675, 350,300);
		FLOOR_6 = new GRect(3860,705, 325,300);
		FLOOR_7 = new GRect(4110,672, 490,300);
		
		TOP_1 = new GRect(0,0,455, 400);
		TOP_2 = new GRect(455, 0, 1005, 400); //640 - 200 FOR ALL
		TOP_3 = new GRect(1460,0, 100,400);
		TOP_3_1 = new GRect(1560,0, 200,400);
		TOP_3_2 = new GRect(1760,0, 100,400);
		TOP_4 = new GRect(1860,0, 200,400);
		
		TOP_4_1 = new GRect(2060,0,220,400);
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
		
//		FLOOR_1.setVisible(false);
		HITBOX = new GRect(0,510,100,100);
	
		
		
		
		///this.CollectionMenu = collection;
		player = new Player (program , 0 ,515 ,5 ,5);
		weapon = new Weapon(program, 1,"banana.png"); //CHANGE ONCE RESCALED - JT
		obstacle = new Obstacle(program, 0, 0);
		//projectile = new Projectiles(program, "media/fruits/banana.png", Direction.WEST, 1);
		enemy1 = new Enemy(program, new Location(300, 515), Difficulty.MEDIUM, false);
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
		
		program.add(TOP_1);
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
		
		player.show();
		weapon.show();
		enemy1.show();
		program.add(HITBOX);
		obstacle.show();
		
		
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
        if(DISTANCE_X > 4650) { 
        	DISTANCE_X = 4650;
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
		if(DISTANCE_X <= 0 || DISTANCE_X > 4650) {
			return 0;	
		}
		else {
			DISTANCE_X-=5;
		}
		return -7;
	}
	public double validMoveRight(int x) {
		if(DISTANCE_X < 0 || DISTANCE_X >= 4650) {
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

		DISTANCE_X-=5;
		return -7;
		
	}
	public double validWallMR(int x) {
		if(DISTANCE_X <= 1660  || DISTANCE_X > 1675) {
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
		GRAVITY = 10;
//		player.movePlayer(0, GRAVITY);
//		HITBOX.move(0,GRAVITY);
//		if(collision(HITBOX, FLOOR_1) == true) {
//			player.movePlayer(0, -GRAVITY);
//			HITBOX.move(0,-GRAVITY);
//			if(collision(HITBOX, TOP_2) == true ) {
//				player.movePlayer(0, GRAVITY);
//				HITBOX.move(0,GRAVITY);
//				if(collision(HITBOX, FLOOR_2) == true){
//					player.movePlayer(0, -GRAVITY);
//					HITBOX.move(0,-GRAVITY);
//					if(collision(HITBOX, TOP_5) == true){
//						player.movePlayer(0, GRAVITY);
//						HITBOX.move(0,GRAVITY);
//						if(collision(HITBOX, FLOOR_5) == true){
//							player.movePlayer(0, -GRAVITY);
//							HITBOX.move(0,-GRAVITY); 
//							if(collision(HITBOX, TOP_6) == true){
//								player.movePlayer(0, GRAVITY);
//								HITBOX.move(0,GRAVITY);
//								if(collision(HITBOX, FLOOR_6) == true){
//									player.movePlayer(0, -GRAVITY);
//									HITBOX.move(0,-GRAVITY);
//									if(collision(HITBOX, TOP_7) == true){
//										player.movePlayer(0, GRAVITY);
//										HITBOX.move(0,GRAVITY);
//										if(collision(HITBOX, FLOOR_7) == true){
//											player.movePlayer(0,-GRAVITY);
//											HITBOX.move(0,-GRAVITY);
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		player.movePlayer(0, GRAVITY);
		HITBOX.move(0,GRAVITY);
		if(collision(HITBOX, FLOOR_1) == true) {
			player.movePlayer(0, -GRAVITY);
			HITBOX.move(0,-GRAVITY);
			if(collision(HITBOX, TOP_2) == true) {
				player.movePlayer(0, GRAVITY);
				HITBOX.move(0,GRAVITY);
				if(collision(HITBOX, FLOOR_2) == true) {
					player.movePlayer(0, -GRAVITY);
					HITBOX.move(0, -GRAVITY);
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
					if(collision(HITBOX, TOP_4) == true) {
						player.movePlayer(0, GRAVITY);
						HITBOX.move(0, GRAVITY);
						if(collision(HITBOX, FLOOR_4) == true) {
							player.movePlayer(0, -GRAVITY);
							HITBOX.move(0, -GRAVITY);
							if(collision(HITBOX, TOP_4_1) == true) {
								player.movePlayer(0, GRAVITY);
								HITBOX.move(0, GRAVITY);
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
		System.out.println(DISTANCE_X);
	}
		
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {	
			checkOOB();
			checkWall();
			if (DISTANCE_X < 900 || DISTANCE_X > 4200) {
			
				player.movePlayer(validMoveRight(DISTANCE_X),0);
				player.updateVel(7);
				HITBOX.move(validMoveRight(DISTANCE_X),0);
			} 
			
			// MOVEMENT RIGHT
			else {
				if(collision(HITBOX, WALL_3) == true && collision(HITBOX, WALL_4) != true) {
					TEMP_D = DISTANCE_X;
					player.movePlayer(validWallMR(DISTANCE_X),0);
					HITBOX.move(validWallMR(DISTANCE_X), 0);
					player.updateVel(0);
//					System.out.println("reached ");
//					System.out.println(DISTANCE_X);
//					System.out.println(validWallMR(TEMP_D));
//					System.out.println(validWallMR(DISTANCE_X));
			
				}
				else {
					player.movePlayer(1,0);
					player.updateVel(7);
					HITBOX.move(1,0);
					//
					
					
					
					Background.setLocation(START_LOCATION_X + (i -= 10),START_LOCATION_Y);
					FLOOR_1.setLocation(0 + (j -= 10),610);
					FLOOR_2.setLocation(375 + (k -= 10),645);
					FLOOR_2_1.setLocation(1460 + (q -= 10),645);
					FLOOR_3.setLocation(1560 + (l -= 10),575);
					FLOOR_4.setLocation(1690 + (m -= 10),645);
					FLOOR_4_1.setLocation(2075 + (r -= 10), 610);
					FLOOR_4_2.setLocation(2205 + (s -= 10), 645);
					FLOOR_5.setLocation(3510 + (n -= 10),675);
					FLOOR_6.setLocation(3860 + (o -= 10),710);
					FLOOR_7.setLocation(4185 + (p -= 10),675);
					
					TOP_1.setLocation((j2 -= 10),0);
					TOP_2.setLocation(455 + (k2 -= 10),0);
					TOP_3.setLocation(1460 + (l2 -= 10),0);
					TOP_3_1.setLocation(1560 + (q2 -= 10),0);
					TOP_3_2.setLocation(1760 + (r2 -= 10),0);
					TOP_4.setLocation(1860 + (m2 -= 10),0);
					TOP_4_1.setLocation(2060 + (s2 -= 10),0);
					TOP_4_2.setLocation(2280+ ( t2 -= 10), 0);
					TOP_5.setLocation(3590 + (n2 -= 10),0);
					TOP_6.setLocation(3940 + (o2 -= 10), 0);
					TOP_7.setLocation(4185 + (p2 -= 10),0);
					
					
					WALL_1.setLocation(365 + (j3 -= 10), 610);
					WALL_2.setLocation(465 + (k3 -= 10), 610);
					WALL_3.setLocation(1560 + (l3 -= 10), 610);
					WALL_4.setLocation(1680 + (m3 -= 10), 610);
					
					
					DISTANCE_X+=10;
				}
				
			}
		}
			
		else if (key == KeyEvent.VK_LEFT) {
			//player.updateVel(0, -5);
//			player.update();
			checkOOB();
			if (DISTANCE_X < 900 || DISTANCE_X > 4200) {
//				player.movePlayer(validMoveLeft(DISTANCE_X),0);
//				player.updateVel(5,-5);
//				HITBOX.move(validMoveLeft(DISTANCE_X),0);
				//
				if(collision(HITBOX, WALL_1) == true & collision(HITBOX, WALL_2) != true) {
					player.movePlayer(validWallML(DISTANCE_X),0);
					HITBOX.move(validWallML(DISTANCE_X), 0);
					player.updateVel(0);
					
				}
				else {// if(collision(HITBOX, WALL_2) == true){
					player.movePlayer(validMoveLeft(DISTANCE_X),0);
					player.updateVel(-7);
					HITBOX.move(validMoveLeft(DISTANCE_X),0);
					//
				}
			}
			else {
				player.movePlayer(-1,0);
				player.updateVel(-7);
				HITBOX.move(-1,0);
				
				Background.setLocation(START_LOCATION_X + (i += 10),START_LOCATION_Y);
				FLOOR_1.setLocation(0 + (j += 10),610);
				FLOOR_2.setLocation(375 + (k += 10),645);
				FLOOR_2_1.setLocation(1460 + (q += 10),645);
				FLOOR_3.setLocation(1560 + (l += 10),575);
				FLOOR_4.setLocation(1690 + (m += 10),645);
				FLOOR_4_1.setLocation(2075 + (r += 10), 610);
				FLOOR_4_2.setLocation(2205 + (s += 10), 645);
				FLOOR_5.setLocation(3510 + (n += 10),675);
				FLOOR_6.setLocation(3860 + (o += 10),710);
				FLOOR_7.setLocation(4185 + (p += 10),675);
				
				TOP_1.setLocation((j2 += 10),0);
				TOP_2.setLocation(455 + (k2 += 10),0);
				TOP_3.setLocation(1460 + (l2 += 10),0);
				TOP_3_1.setLocation(1560 + (q2 += 10),0);
				TOP_3_2.setLocation(1760 + (r2 += 10),0);
				TOP_4.setLocation(1860 + (m2 += 10),0);
				TOP_4_1.setLocation(2060 + (s2 += 10),0);
				TOP_4_2.setLocation(2280+ ( t2 += 10), 0);
				TOP_5.setLocation(3590 + (n2 += 10),0);
				TOP_6.setLocation(3940 + (o2 += 10), 0);
				TOP_7.setLocation(4185 + (p2 += 10),0);
				
				
				WALL_1.setLocation(365 + (j3 += 10), 610);
				WALL_2.setLocation(465 + (k3 += 10), 610);
				WALL_3.setLocation(1560 + (l3 += 10), 610);
				WALL_4.setLocation(1680 + (m3 += 10), 610);

				
				DISTANCE_X-=10;
				
			}
			
		}
	
		if (key == KeyEvent.VK_SPACE) {
			DISTANCE_Y = 1;
			GRAVITY = 0;
			for (int counter = 10; counter < 70; counter+= 10) {
				player.movePlayer(2*player.getVel(),-counter);
				HITBOX.move(2*player.getVel(),-counter);
				DISTANCE_Y +=10;
			}
			GRAVITY = 10;
			DISTANCE_Y -= 70;
			
		}
		// CAN ALWAYS CHANGE FROM 'D' to fire. 
		if (key == KeyEvent.VK_D) {
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		player.updateVel(0);
		player.movePlayer(0, 0);
	}
}

