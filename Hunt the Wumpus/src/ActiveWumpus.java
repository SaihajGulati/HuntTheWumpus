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
 * Created the ActiveWumpus object and its toString method.
 */
public class ActiveWumpus 
{
	private String status; //The status of the Wumpus (asleep or awake)
	private int turns; //The number of turns
	private int wakeTurn; //The turn when the Wumpus would wake up
	
	/*
	 * The constructor of the ActiveWumpus object, which sets "asleep" as the default value for the status
	 * field (turns will use the default constructor which sets it to 0).
	 */
	public ActiveWumpus()
	{
		status = "asleep";
	}
	
	public void setWakeUpTurn()
	{
		wakeTurn = (int)(Math.random() * 6 + (turns + 5));
	}
	
	public void incrementTurns(LazyWumpus lazy, String condition)
	{
		double teleportChance = Math.random();
		turns++;
		if(teleportChance < 0.05)
		{
			teleport();
		}
		if(turns == wakeTurn)
		{
			status = "awake";
			awakeWumpus(lazy, condition);
		}
		setWakeUpTurn();
	}
	
	/*Maybe have LazyWumpus edit its move method to include movements for every 5 turns, and for when the
	 * Wumpus is defeated in Trivia.
	 * 
	 */
	public void awakeWumpus(LazyWumpus lW, String state)
	{
		for(int i = 0; i < (int)(Math.random() * 3 +1); i++)
		{
			lW.move(state);
		}
	}

	/*possibly have LazyWumpus create a teleport method for this to work, since ActiveWumpus can't
	 * access its location variable.
	 */
	public void teleport()
	{
		
	}
	
	public String toString()
	{
		return "Active Wumpus";
	}
}
