/*
 * @author Raj Sunku
 * Sounds Object
 * 
 * This object will access a library of songs that GameControl can use for sound effects for the game
 * 
 * Revision History
 * 4/18/2019:   Constructor and toString made
 * 4/24/2019:   Studded Methods added
 */
public class Sounds
{
	private int[] themes;
	/*
	 * Will read the config file
	 */
	public Sounds()
	{
		
	}
	
	/*
	 * @return class name
	 */
	public String toString()
	{
		return "Sounds";
	}
	
	/*
	 * @return the possible themes
	 */
	
	public int[] getThemes()
	{
		return themes;
	}
	
	/*
	 * rest of the methods are conditions where sounds can be played
	 */
	
	public void movePlayer(int theme)
	{
		
	}
	
	public void shootArrow(int theme)
	{
		
	}
	
	public void moveWumpus(int theme)
	{
		
	}
	
	public void triviaPopUp(int theme)
	{
		
	}
	
	public void win(int theme)
	{
		
	}
	
	public void lose(int theme)
	{
		
	}
	
	public void bat(int theme)
	{
		
	}
	
	public void pit(int theme)
	{
		
	}
}