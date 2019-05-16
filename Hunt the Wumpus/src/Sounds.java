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
import java.io.*;
public class Sounds
{
	/*
	 * index 0 is move player, 1 is shoot arrow, 2 is moveWumpus, 3 is trivia pop-up, 4 is win, 5 is lose, 6 is bat, and 7 is pit
	 */
	private AudioClip[] currentSounds;
	/*
	 * Will read the config file
	 */
	public Sounds(int theme) throws MalformedURLException, FileNotFoundException
	{
		if (theme==1)
		{
			currentSounds[0] = Applet.newAudioClip(new File("movePlayer1.wav").toURI().toURL());
			currentSounds[1] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\shootArrow1.wav"));
			currentSounds[2] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\moveWumpus1.wav"));
			currentSounds[3] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\trivia1.wav"));
			currentSounds[4] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\win1.wav"));
			currentSounds[5] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\lose1.wav"));
			currentSounds[6] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\bat1.wav"));
			currentSounds[7] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\pit1.wav"));
		}
		else
		{
			currentSounds[0] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\movePlayer2.wav"));
			currentSounds[1] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\shootArrow2.wav"));
			currentSounds[2] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\moveWumpus2.wav"));
			currentSounds[3] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\trivia2.wav"));
			currentSounds[4] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\win2.wav"));
			currentSounds[5] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\lose2.wav"));
			currentSounds[6] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\bat2.wav"));
			currentSounds[7] = Applet.newAudioClip(new URL("C:\\Users\\s-rsunku\\OneDrive - Lake Washington School District\\APCSA\\Sounds\\pit2.wav"));
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
	public AudioClip[] getSounds()
	{
		return currentSounds;
	}
	
	/*
	 * rest of the methods are conditions where sounds can be played
	 */
	public void movePlayer()
	{
		currentSounds[0].play();
	}
	
	public void shootArrow()
	{
		currentSounds[1].play();
	}
	
	public void moveWumpus()
	{
		currentSounds[2].play();
	}
	
	public void triviaPopUp()
	{
		currentSounds[3].play();
	}
	
	public void win()
	{
		currentSounds[4].play();
	}
	
	public void lose()
	{
		currentSounds[5].play();
	}
	
	public void bat()
	{
		currentSounds[6].play();
	}
	
	public void pit()
	{
		currentSounds[7].play();
	}
}