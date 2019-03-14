/**
 * @author Saihaj Gulati
 * Class: APCSA - 3
 * Object: Player
 	*  keeps track of the player’s inventory (Arrows and Gold coins)
 	*  keeps track of the number of turns the player has taken
 	*  Computes ending score of the player 
 * 
 * Revision History:
 * Version:   Date:     Description:
 * 1.0        3/4/19    Constructor and toString added
 * 2.0        3/8/19    Stubs added (fields and skeleton methods)
*/
public class Player 
{
	private int turns;
	private int arrows;
	private int coins;
	
	/**
	 * This constructs player object
	 * @return Player object
	 */
	public Player()
	{	
	}
	
	/**
	 * This allows for printing out the object nicely
	 * @return Player as a string
	 */
	public String toString()
	{
		return "Player";
	}
	
	/**
	 * This method adds 1 to the number of turns and coins.
	 * @return number of coins
	 */
	public int movePlayer()
	{
		return 0;
	}
	
	/**
	 * This number changes the number of arrows by the parameter.
	 * @param amount to change number of arrows by
	 * @return number of arrows
	 */
	public int changeArrows(int amt)
	{
		return 0;		
	}
	
	/**
	 * This method changes the number of coins by the parameter.
	 * @param amount to change number of coins by
	 * @return number of coins
	 */
	public int changeCoins(int amt)
	{
		return 0;
	}
	
	/**
	 * This method calculates the end score.
	 * @param boolean representing whether the Wumpus was killed
	 * @return score
	 */
	public int getScore(boolean gotWumpus)
	{
		return 0;
	}
	
}