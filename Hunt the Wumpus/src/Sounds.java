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
	
	private static AudioInputStream ais2;
	private static Clip clip2;
	private static FloatControl gainControl2;
	/*
	 * Will read the config file
	 */
	public Sounds(int theme) throws LineUnavailableException, MalformedURLException, UnsupportedAudioFileException, IOException 
	{
		currentSounds = new AudioClip[9];
		ais = AudioSystem.getAudioInputStream(new File("res/background1.wav").toURI().toURL());
		clip = AudioSystem.getClip();
		clip.open(ais);
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);
		
		ais2 = AudioSystem.getAudioInputStream(new File("res/trivia1.wav").toURI().toURL());
		clip2 = AudioSystem.getClip();
		clip2.open(ais2);
		FloatControl gainControl2 = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl2.setValue(-20.0f);
		try {
			try {
				if (theme==1)
				{
					currentSounds[0] = Applet.newAudioClip(new File("res/movePlayer1.wav").toURI().toURL());
					currentSounds[1] = Applet.newAudioClip(new File("res/shootArrow1.wav").toURI().toURL());
					currentSounds[2] = Applet.newAudioClip(new File("res/moveWumpus1.wav").toURI().toURL());
					currentSounds[3] = Applet.newAudioClip(new File("res/trivia1.wav").toURI().toURL());
					currentSounds[4] = Applet.newAudioClip(new File("res/win1.wav").toURI().toURL());
					currentSounds[5] = Applet.newAudioClip(new File("res/lose1.wav").toURI().toURL());
					currentSounds[6] = Applet.newAudioClip(new File("res/bat1.wav").toURI().toURL());
					currentSounds[7] = Applet.newAudioClip(new File("res/pit1.wav").toURI().toURL());
					currentSounds[8] = Applet.newAudioClip(new File("res/background1.wav").toURI().toURL());
				}
				else
				{
					currentSounds[0] = Applet.newAudioClip(new File("res/movePlayer2.wav").toURI().toURL());
					currentSounds[1] = Applet.newAudioClip(new File("res/shootArrow2.wav").toURI().toURL());
					currentSounds[2] = Applet.newAudioClip(new File("res/moveWumpus2.wav").toURI().toURL());
					currentSounds[3] = Applet.newAudioClip(new File("res/trivia2.wav").toURI().toURL());
					currentSounds[4] = Applet.newAudioClip(new File("res/win2.wav").toURI().toURL());
					currentSounds[5] = Applet.newAudioClip(new File("res/lose2.wav").toURI().toURL());
					currentSounds[6] = Applet.newAudioClip(new File("res/bat2.wav").toURI().toURL());
					currentSounds[7] = Applet.newAudioClip(new File("res/pit2.wav").toURI().toURL());
					currentSounds[8] = Applet.newAudioClip(new File("res/background1.wav").toURI().toURL());
				}
			}
			catch(NullPointerException e) {
				System.out.println("Error Occured : Sound constuction : NullPointer");
				GraphicalInterface.Error();
			}
			
		}
		catch(MalformedURLException e) {
			System.out.println("Error Occured : Sound construction : MalformedURL");
			GraphicalInterface.Error();
		}

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
		currentSounds[0].play();
	}
	
	public static void shootArrow()
	{
		currentSounds[1].play();
	}
	
	public static void moveWumpus()
	{
		currentSounds[2].play();
	}
	
	public static void triviaPopUp()
	{
		clip2.start();
		clip2.drain();
	}
	
	public static void win()
	{
		currentSounds[4].play();
	}
	
	public static void lose()
	{
		currentSounds[5].play();
	}
	
	public static void bat()
	{
		currentSounds[6].play();
	}
	
	public static void pit()
	{
		currentSounds[7].play();
	}
	
	public static void background()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}