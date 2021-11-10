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
	private GButton QUIT_BUTTON;
	private GImage QUIT;
	private final int BUTTON_SIZE = 50;
	private int OPTION;

	public MenuPane(MainApplication app) {
		super();
		program = app;
//		rect = new GButton("Next", app.getWidth()/2-BUTTON_SIZE/2, app.getHeight()/2-BUTTON_SIZE/2, BUTTON_SIZE, BUTTON_SIZE);
//		rect.setFillColor(Color.RED);
		// Background
		MAIN_MENU = new GImage("media/MenuScreen/zooHDMENU.jpeg", -20, 0);
		MAIN_MENU.sendToBack();
		//Title
		TITLE  = new GImage("media/MenuScreen/Title.png",180,175);
		//Play with button.
		PLAY = new GImage("media/MenuScreen/playButton.png",605, 450);
		PLAY_BUTTON = new GButton(" ",605,450,150,65);
		PLAY_BUTTON.setFillColor(new Color(255,225,53));
		PLAY_BUTTON.setVisible(false);
		// COllections
		COLLECTIONS_BUTTON = new GButton(" ", 500,555, 360,65);
		COLLECTIONS_BUTTON.setFillColor(new Color(255,225,53));
		COLLECTIONS_BUTTON.setVisible(false);
		COLLECTIONS = new GImage("media/MenuScreen/collections.png", 500,550);
		// Settings  with button.
		SETTINGS_BUTTON = new GButton(" ", 560,650, 260,65);
		SETTINGS_BUTTON.setFillColor(new Color(255,225,53));
		SETTINGS_BUTTON.setVisible(false);
		SETTINGS = new GImage("media/MenuScreen/settings.png", 560, 650);
		// OPT OUT
		QUIT_BUTTON = new GButton(" ", 560, 750, 260,65);
		QUIT_BUTTON.setVisible(false);
		QUIT = new GImage("media/MenuScreen/QUIT.png",625, 750);
		
		
		
	}

	@Override
	public void showContents() {
//		program.add(rect);	
		program.add(MAIN_MENU);
		program.add(TITLE);
		program.add(PLAY);
		program.add(PLAY_BUTTON);
		program.add(COLLECTIONS);
		program.add(COLLECTIONS_BUTTON);
		program.add(SETTINGS);
		program.add(SETTINGS_BUTTON);
		program.add(QUIT);
		program.add(QUIT_BUTTON);
		
		
	}

	@Override
	public void hideContents() {
//		program.remove(rect);
		program.remove(MAIN_MENU);
		program.remove(TITLE);
		program.remove(PLAY_BUTTON);
		program.remove(PLAY);
		program.remove(COLLECTIONS_BUTTON);
		program.remove(COLLECTIONS);
		program.remove(SETTINGS_BUTTON);
		program.remove(SETTINGS);
		program.remove(QUIT);
		program.remove(QUIT_BUTTON);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
//		if (obj == rect) {
//			program.switchToSome();
//		}
		if (obj == PLAY_BUTTON && obj instanceof GButton) {
			program.switchToSome();
			setOption(1);
		}
		if (obj == COLLECTIONS_BUTTON && obj instanceof GButton) {
			program.switchToSome();
			setOption(2);
		}
		if (obj == SETTINGS_BUTTON && obj instanceof GButton) {
			setOption(3);
			program.switchToSome();
			
		}
		if (obj == QUIT_BUTTON && obj instanceof GButton) {
			setOption(4);
			program.switchToSome();
			
		}
		
	}
	public void setOption(int option) {
		this.OPTION = option;
	}
	
	public int getOption() {
		return OPTION;
	}
}
