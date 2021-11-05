import java.awt.Color;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	
	// you will use program to get access to all of the GraphicsProgram calls
	private MainApplication program; 
								
	private GButton rect;
	private GImage MAIN_MENU;
	private GImage TITLE;
	private GButton PLAY_BUTTON;
	private GImage PLAY;
	private GButton COLLECTIONS_BUTTON;
	private GImage COLLECTIONS;
	private GButton SETTINGS_BUTTON;
	private GImage SETTINGS;
	private final int BUTTON_SIZE = 50;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect = new GButton("Next", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2-BUTTON_SIZE/2, BUTTON_SIZE, BUTTON_SIZE);
		rect.setFillColor(Color.RED);
		// Background
		MAIN_MENU = new GImage("media/MenuScreen/zooHDMENU.jpeg", -20, 0);
		//Title
		TITLE  = new GImage("media/MenuScreen/Title.png",180,175);
		//Play with button.
		PLAY = new GImage("media/MenuScreen/playButton.png",605, 450);
		PLAY_BUTTON = new GButton("",605,450,150,65);
		// COllections
		COLLECTIONS_BUTTON = new GButton("", 500,555, 360,65);
		COLLECTIONS = new GImage("media/MenuScreen/collections.png", 500,550);
		// Settings  with button.
		SETTINGS_BUTTON = new GButton("", 560,650, 260,65);
		SETTINGS = new GImage("media/MenuScreen/settings.png", 560, 650);
	
		
	}

	@Override
	public void showContents() {
		//program.add(rect);
		program.add(MAIN_MENU);
		program.add(TITLE);
		program.add(PLAY_BUTTON);
		program.add(PLAY);
		program.add(COLLECTIONS_BUTTON);
		program.add(COLLECTIONS);
		program.add(SETTINGS_BUTTON);
		program.add(SETTINGS);
		
		
	}

	@Override
	public void hideContents() {
		//program.remove(rect);
		program.add(MAIN_MENU);
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		GObject obj = program.getElementAt(e.getX(), e.getY());
//		if (obj == rect) {
//			program.switchToSome();
//		}
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == PLAY_BUTTON) {
			program.switchToSome();
		}
		
	}
}
