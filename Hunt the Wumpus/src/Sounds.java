/*
 * @author Raj Sunku
 * Sounds Object
 * 
 * This object will access a library of songs that GameControl can use for sound effects for the game
 * 
 * Revision History
 * 4/18/2019:   Constructor and toString made
 * 4/24/2019:   Studded Methods added 
 * 5/03/2019:   All methods implemented
 */

import java.applet.*;
import java.net.*;

import javax.sound.sampled.*;

import java.io.*;
public class Sounds
{
	/*
	 * index 0 is move player, 1 is shoot arrow, 2 is moveWumpus, 3 is trivia pop-up, 4 is win, 5 is lose, 6 is bat, and 7 is pit
	 */
	private static AudioClip[] currentSounds;
	private static AudioInputStream ais;
	private static Clip clip;
	private static FloatControl gainControl;
	private static int themeNum;
	/*
	 * Will read the config file
	 */
	public Sounds(int theme) throws LineUnavailableException, MalformedURLException, UnsupportedAudioFileException, IOException 
	{
		themeNum = theme;
	}
	
	/*
	 * @return class name
	 */
	public String toString()
	{
		return "Sounds";
	}
	
	/*
	 * @return the Sounds
	 */
	public static AudioClip[] getSounds()
	{
		return currentSounds;
	}
	
	/*
	 * rest of the methods are conditions where sounds can be played 
	 */
	public static void movePlayer()
	{
		play("res/movePlayer" + themeNum + ".wav", 0.0);
	}
	
	public static void shootArrow()
	{
		play("res/shootArrow" + themeNum + ".wav", 0.0);
	}
	
	public static void moveWumpus()
	{
		play("res/moveWumpus"+themeNum+".wav",0);
	}
	
	public static void triviaPopUp()
	{
		play("res/trivia" + themeNum + ".wav", -20.0);
	}
	
	public static void win()
	{
		play("res/win"+themeNum+".wav",0);
	}
	
	public static void lose()
	{
		play("res/lose"+themeNum+".wav",0);
	}
	
	public static void bat()
	{
		play("res/bat"+themeNum+".wav",0);
	}
	
	public static void pit()
	{
		play("res/pit" + themeNum + ".wav", 0);
	}
	
	//plays the background music in a continuous loop
	public static void background()
	{//sdf
		try {
			ais = AudioSystem.getAudioInputStream(new File("res/background" + themeNum + ".wav").toURI().toURL());
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {

			System.out.println("Error : playing background music");
			GraphicalInterface.Error();
		}
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * this is the method that is called to play a sound
	 * @param file, the name/path of the file as a string
	 * @param volChange, the volume to change the sound by
	 */
	public static void play(String file, double volChange)
	{
		try {
			ais = AudioSystem.getAudioInputStream(new File(file).toURI().toURL());
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			System.out.println("Error : playing file");
			GraphicalInterface.Error();
		}
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float)volChange);
		clip.start();
		clip.drain();
	}
}