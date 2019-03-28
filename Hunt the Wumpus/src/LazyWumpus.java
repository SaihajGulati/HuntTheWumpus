/**
 * @author Saihaj Gulati
 * Class: APCSA - 3
 * Object: Lazy Wumpus
 	* Has different functionality for this certain type of wumpus 
 	* Keeps track of the current state the Wumpus is in (asleep, awake, moving)
 	* If the player shoots an arrow and misses while the Wumpus is sleeping
 		the Wumpus wakes up and runs up to two rooms away from current position.
	* If the Wumpus is defeated in trivia, it will run up to three rooms away.
	* The Wumpus is slow and can only move one room per turn.
	* If the Wumpus does not move for two turns, it falls asleep.

 * Revision History:
 * Version:   Date:     Description:
 * 1.0        3/27/19    Constructor and toString added
 */
public class LazyWumpus {
	
	private String state;
	/**
	 * This constructs the Lazy Wumpus object and sets default values of the fields
	 * @return Lazy Wumpus object
	 */
	public LazyWumpus()
	{
	}
	
	/**
	 * This allows for printing out the object nicely
	 * @return Lazy Wumpus as a string
	 */
	public String toString()
	{
		return "Lazy Wumpus";
	}
	
	

}
