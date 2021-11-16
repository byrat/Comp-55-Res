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
	private int x;
	private double width;
	
	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;
	public static final int SIZE = 50;
	public static final String IMG_FILENAME_PATH = "media/obstacle/crate.png";
	
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setLocation(int x) {
		this.x = x;
	}
	
	public int getLocation() {
		return x;
	}
	
//	public void addAnObstacle() {
//		GImage o = makeObstacle(rgen.nextInt(0, WINDOW_HEIGHT-SIZE/2));
//		obs.add(o);
//		add(o);
//	}
	
	public GImage makeObstacle(double x) {
		GImage temp = new GImage(IMG_FILENAME_PATH, 10, 5);
		return temp;
	}
	
	public void run() {
		obs = new ArrayList <GImage> ();
		rgen = RandomGenerator.getInstance();
		
		GImage o = makeObstacle(rgen.nextInt(0, WINDOW_HEIGHT-SIZE/2));
		obs.add(o);
		add(o);
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
		new Obstacle().start();
	}
}
