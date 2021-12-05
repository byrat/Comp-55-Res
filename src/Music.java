import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class Music {
	private MainApplication App;
	
	
	private static Scanner scanner;
	private static File file;
	private static AudioInputStream audioStream;
	private static Clip clip;
	private static boolean allow = true;
	
	public Music(MainApplication app) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		Scanner scanner = new Scanner(System.in);
		File file = new File("media/music/background.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		
		String r = " ";
//		while(!r.equals("Q")) {
//			play();
//		}
	}
//		Scanner scanner = new Scanner(System.in);
//		File file = new File("media/music/background.wav");
//		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//		Clip clip = AudioSystem.getClip();
//		clip.open(audioStream);
//		
//		String r = " ";
//		clip.start();
//		clip.stop();

//		File file = new File("media/music/Mayahem_Temple_-_Banjo.wav");
//		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//		Clip clip = AudioSystem.getClip();
//		clip.open(audioStream);
//		
//		String r = " ";
			
	public void play() {
		clip.start();
	}
	public static void stop() {
		clip.stop();
		
	}
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
//		String r = " ";
//		while(!r.equals("Q")) {
//			play();
//		}
		 
	}
}
