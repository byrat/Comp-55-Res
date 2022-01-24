import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

// TESTING COMMIT FOR NEW GIT
public class SettingsMenu extends GraphicsPane {
	// you will use program to get access to all of the GraphicsProgram calls
	Enemy enemy;
	Difficulty d;
	

	private MainApplication program; 
	private GImage img;
	private GParagraph para;
	private GImage SCREEN;
	private GImage SETTINGS_TITLE;
	private GImage DIFFICULTY;
	private GButton EASY;
	private GButton MEDIUM;
	private GButton HARD;
	private GImage SOUND_FX;
	private GButton FX_ON;
	private GButton FX_OFF;
	private GImage MUSIC;
	private GButton MC_ON;
	private GButton MC_OFF;
	private GImage RETURN;
	private GButton RETURN_BUTTON;


	public SettingsMenu(MainApplication app) {
		this.program = app;
		// BACkGOURND
		SCREEN =  new GImage("media/MenuScreen/BACKGROUND.jpg", -250, 0);
		SCREEN.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT);
		// TItle
		SETTINGS_TITLE  = new GImage("media/MenuScreen/SETTINGS_TITLE.png",500,175);
		//Difficulty
		DIFFICULTY = new GImage("media/MenuScreen/DIFFICULTY.png",175, 350);
		EASY = new GButton(" ",505,355,60,60);
		EASY.setFillColor(new Color(255,225,53));
		MEDIUM = new GButton(" ",735,355,60,60);
		HARD = new GButton(" ",1025,355,60,60);
		//Sound
		SOUND_FX = new GImage("media/MenuScreen/SOUND_FX.png", 175, 450);		
		FX_ON = new GButton(" ",465,455,60,60);
		FX_ON.setFillColor(new Color(255,225,53));
		FX_OFF = new GButton(" ",630,455,60,60);
		// Music
		MUSIC = new GImage("media/MenuScreen/MUSIC.png", 175, 550);		
		MC_ON = new GButton(" ",365,555,60,60);
		MC_ON.setFillColor(new Color(255,225,53));
		MC_OFF = new GButton(" ",530,555,60,60);
		// Return
		RETURN = new GImage("media/MenuScreen/RETURN.png",560 ,650);
		RETURN_BUTTON = new GButton(" ",560 ,650,250,65);
		RETURN_BUTTON.setVisible(false);
		
		
//		img = new GImage("robot head.jpg", 100, 100);
//		para = new GParagraph("welcome\nto my\nsecret room!", 150, 300);
//		para.setFont("Arial-24");
	}

	@Override
	public void showContents() {
		program.add(SCREEN);
		program.add(SETTINGS_TITLE);
		program.add(DIFFICULTY);
		program.add(EASY);
		program.add(MEDIUM);
		program.add(HARD);
		program.add(SOUND_FX);
		program.add(FX_ON);
		program.add(FX_OFF);
		program.add(MUSIC);
		program.add(MC_ON);
		program.add(MC_OFF);
		program.add(RETURN);
		program.add(RETURN_BUTTON);
//		program.add(img);
//		program.add(para);
	}

	@Override
	public void hideContents() {
//		program.remove(img);
//		program.remove(para);
		program.remove(SCREEN);
		program.remove(SETTINGS_TITLE);
		program.remove(DIFFICULTY);
		program.remove(EASY);
		program.remove(MEDIUM);
		program.remove(HARD);
		program.remove(SOUND_FX);
		program.remove(FX_ON);
		program.remove(FX_OFF);
		program.remove(MUSIC);
		program.remove(MC_ON);
		program.remove(MC_OFF);
		program.remove(RETURN);
		program.remove(RETURN_BUTTON);
	}
	
	public void setDifficulty(Difficulty d) {
		this.d = d;
	}
	public Difficulty getDifficulty(){
		return d;
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		para.setText("you need\nto click\non the eyes\nto go back");
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == RETURN_BUTTON) {
			program.switchToMenu();
		}
		if(obj == EASY) {
			MEDIUM.setFillColor(Color.WHITE);
			HARD.setFillColor(Color.WHITE);
			EASY.setFillColor(new Color(255,225,53));
			// PASS THIS VALUE TO WHERE DIFFICULTY IS DETERMINED
			setDifficulty(Difficulty.EASY);
		}
		else if(obj == MEDIUM) {
			HARD.setFillColor(Color.WHITE);
			EASY.setFillColor(Color.WHITE);
			MEDIUM.setFillColor(new Color(255,225,53));
			// PASS THIS VALUE TO WHERE DIFFICULTY IS DETERMINED
			setDifficulty(Difficulty.MEDIUM);

		}
		else if(obj == HARD) {
			MEDIUM.setFillColor(Color.WHITE);
			EASY.setFillColor(Color.WHITE);
			HARD.setFillColor(new Color(255,225,53));
			// PASS THIS VALUE TO WHERE DIFFICULTY IS DETERMINED
			setDifficulty(Difficulty.HARD);
		}
		if(obj == FX_ON) {
			FX_OFF.setFillColor(Color.WHITE);
			FX_ON.setFillColor(new Color(255,225,53));

			// PASS THIS VALUE TO WHERE SOUNDS IS DETERMINED
		}
		else if(obj == FX_OFF) {
			FX_ON.setFillColor(Color.WHITE);
			FX_OFF.setFillColor(new Color(255,225,53));

			// PASS THIS VALUE TO WHERE SOUNDS IS DETERMINED
		}
		if(obj == MC_ON) {
			MC_OFF.setFillColor(Color.WHITE);
			MC_ON.setFillColor(new Color(255,225,53));

			// PASS THIS VALUE TO WHERE MUSIC IS DETERMINED
		}
		else if(obj == MC_OFF) {
			MC_ON.setFillColor(Color.WHITE);
			MC_OFF.setFillColor(new Color(255,225,53));

			// PASS THIS VALUE TO WHERE MUSIC IS DETERMINED
		}
	
	}
}
