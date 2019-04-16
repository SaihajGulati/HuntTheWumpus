/**Name: Brian Yang
 * Class: APCSA Period 3
 * Teacher: Mrs. Kankelborg
 * The ActiveWumpus object handles a different set of movement rules for the Wumpus than the LazyWumpus
 * object. It keeps track of the Wumpus' state and the number of turns, where every 5-10 turns the Wumpus
 * will move 1 room per turn for up to three turns before sleeping. However, there is a 5% chance of the Wumpus
 * teleporting to a new random location every turn, and it may run up to two rooms away per turn for a max of 3
 * turns if defeated in trivia.
 * 
 * 4/15/19: Version 1.0
 * Created the ActiveWumpus object.
 */
public class ActiveWumpus 
{
	private String status; //The status of the Wumpus (asleep or awake)
	private int turns; //The number of turns
	
	public ActiveWumpus()
	{
		
	}
}
