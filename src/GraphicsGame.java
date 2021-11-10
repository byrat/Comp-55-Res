import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import acm.graphics.GImage;
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
	Player player;
	Timer timer;
	
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
		///this.CollectionMenu = collection;
		player = new Player (program , 200 ,200 ,5 ,5);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		player.update();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			player.updateVel( 5, 0);
		}
		else if (key == KeyEvent.VK_LEFT) {
			player.updateVel(-5,0);
		}
		if (key == KeyEvent.VK_SPACE) {
			player.updateVel(0, 5);
		}
	}

}
