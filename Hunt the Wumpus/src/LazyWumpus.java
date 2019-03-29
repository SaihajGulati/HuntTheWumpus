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
 * 1.0        3/24/19   Constructor and toString added
 * 2.0        3/28/19   Added fields, stubs, and implemented Constructor and accessors   
 */
public class LazyWumpus {
	
	private String state;
	private int location;
	/**
	 * This constructs the Lazy Wumpus object and sets default values of the fields
	 * @return Lazy Wumpus object
	 */
	public LazyWumpus()
	{
		state = "asleep";
		location = (int)(Math.random()*30+1);
	}
	
	/**
	 * This allows for printing out the object nicely
	 * @return Lazy Wumpus as a string
	 */
	public String toString()
	{
		return "Lazy Wumpus";
	}
	
	/**
	 * This method is an accessor for state
	 * @return state of the Wumpus
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * This method is a setter for state
	 */
	public void setState(String newState)
	{
		state = newState;
	}
	
	/**
	 * This method is an accessor for location
	 * @return location of this object
	 */
	public int getLocation()
	{
		return location;
	}
	
	/**
	 * This method moves the wumpus
	 * @param reason
	 * Based on the reason it moves the wumpus 
	 */
	public void move(String reason)
	{
	}	
	
}
