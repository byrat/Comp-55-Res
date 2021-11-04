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
	private final int BUTTON_SIZE = 50;

	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect = new GButton("Next", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2-BUTTON_SIZE/2, BUTTON_SIZE, BUTTON_SIZE);
		rect.setFillColor(Color.RED);
		MAIN_MENU = new GImage("media/MenuScreen/zooHDMENU.jpeg", -20, 0);
		TITLE  = new GImage("media/MenuScreen/Title.png",180,150);
	}

	@Override
	public void showContents() {
		//program.add(rect);
		program.add(MAIN_MENU);
		program.add(TITLE);
	}

	@Override
	public void hideContents() {
		//program.remove(rect);
		program.add(MAIN_MENU);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			program.switchToSome();
		}
	}
}
