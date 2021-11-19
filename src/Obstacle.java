import java.awt.Color;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


// should we have moving obstacles? 

public class Obstacle extends GraphicsProgram{
	private GImage obstacle;
	private RandomGenerator rgen;
	private ArrayList<GImage> obs;
	private MainApplication app;
	private int x;
	private double width;
	
	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;
	public static final double SCALE = 1; // Change this later to something
	public static final int SIZE = 50;
	public static final String IMG_FILENAME_PATH = "media/obstacle/crate.png";
	// make width static && height;
	
	
	public Obstacle(MainApplication app, double xCoord, double yCoord) {
		this.app = app;
		obstacle = new GImage(IMG_FILENAME_PATH);
		obstacle.setSize(xCoord, yCoord);
//		GImage o = makeObstacle( xCoord, yCoord);
//		app.add(o);
	}
	
//	public void addAnObstacle() {
//		GImage o = makeObstacle(rgen.nextInt(0, WINDOW_HEIGHT-SIZE/2));
//		obs.add(o);
//		add(o);
//	}
	
	public GImage makeObstacle(double x, double y) {
		GImage temp = new GImage(IMG_FILENAME_PATH, x, y);
		temp.scale(SCALE);
		return temp;
	}
	
	public void run() {
		obs = new ArrayList <GImage> ();
		rgen = RandomGenerator.getInstance();
		
		GImage o = makeObstacle(rgen.nextInt(WINDOW_WIDTH, WINDOW_WIDTH + 500),rgen.nextInt(150, WINDOW_HEIGHT-SIZE/2));
		obs.add(o);
		add(o);
		
	}
	
	public void show() {
		app.add(obstacle);
	}
	public void hide() {
		app.remove(obstacle);
	}
	
//	public void run() {
//		balls = new ArrayList<GOval>();
//		enemies = new ArrayList<GRect>();
//		rgen = RandomGenerator.getInstance();
//		text = new GLabel(""+enemies.size(), 0, WINDOW_HEIGHT);
//		add(text);
//		
//		movement = new Timer(MS, this);
//		movement.start();
//		addMouseListeners();
//	}
//	
//	public void actionPerformed(ActionEvent e) {
//		moveAllBallsOnce();
//		moveAllEnemiesOnce();
//		if(numTimes % 40 == 0) {
//			addAnEnemy();
//		}
//		numTimes++;
//	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public static void main(String args[]) {
		new Obstacle(null, SIZE, SCALE);
	}
}

