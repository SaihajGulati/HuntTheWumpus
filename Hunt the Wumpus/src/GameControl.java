/*
 * 
 * @author : Joshua Venable
 * @Class : AP Computer Science A
 * @Object : Game Control : Controls the interaction between every other object
 * 
 * @Date Last Updated: 3/19/19
 * @Version : JavaSE-1.8
 * @Comments : Created stub methods
 */
import java.io.FileNotFoundException;
import java.util.*;
/**
 * MAJOR NOTES:
 * 
 * 	Figure out how to get the adjacent room index?
 * 	Add Wumpus control methods but what do we need for that? 
 *
 */
public class GameControl 
{
	public static final int BATS = 0;
	public static final int HOLE = 1;
	public static final int WUMPUS = 2;
	public static final int SCORE = 0;
	public static void main(String[] args) throws FileNotFoundException
	{
		Cave cave = new Cave();
		GameLocations locations = new GameLocations();
		GraphicalInterface GI = new GraphicalInterface(BATS, HOLE, WUMPUS);
		HighScore score = new HighScore();
		Player player = new Player();
		Trivia trivia = new Trivia("asdf");
		/*
		System.out.println(cave);
		System.out.println(locations);
		System.out.println(GI);
		System.out.println(score);
		System.out.println(player);
		System.out.println(trivia);
		*/
		locations.warning();
		locations.moveWumpus();
		player.movePlayer();
		player.changeCoins(4);
		GI.inDanger(null);
		GI.buyItem(0,0);
		cave.tunnels(0, 0);
		cave.adjacentRooms(0, 0);
		trivia.askQuestions(0);
		trivia.getAnswers();
		
	}
	/**
	 *
	 * @return whether or not you can move there
	 */
	public static boolean movePossible() 
	{	
		//locations.<method to return room number>
		//if there is a door leading there and he is adjacent to it then return true
		return false;
	}
	
	/**
	 * 
	 * @return the map fully created if player decides to play the game
	 */
	public static ArrayList<Integer> createMap()
	{
		//if player clicks 'Play Game'
		//NOTE : Integer is a placeholder and will be replaced with a cave type
		//cave method replaces 'new ArrayList<Integer>();' to grab the generated cave
		ArrayList<Integer> map = new ArrayList<Integer>();
		return map;
	}
	
	/**
	 * 
	 * @return the location of the player as an integer index in the arrayList
	 */
	public static int playerLocation() 
	{
		//get this from a GameLocation method and return the index for the player and the GI 
		return 0;
	}
	
	/**
	 * 
	 * @param 'words' is given by trivia and given to the player when the GI needs it
	 * @return the random questions that are given by trivia
	 */
	public static String[] getQuestions(String[] words)
	{
		// simple passing method so trivia doesn't have to extend
		return words;
	}

	/**
	 * 
	 * @param The Direction is given from GI as a char which will be changed in that class
	 * @return the player index from 'playerLocation' to give to the GameLocation
	 * 			in order to see what adjacent rooms there are
	 */
	public static int movingPlayer(int roomNumber)
	{
		//use the roomNumber which is the room that the player wants to move
		// if 'movePossible' then give the 'playerLocation' index
		//code for inputting 'playerLocation'
		return 0;
	}
	
	/**
	 * 
	 * @param answers are given from GI and passed in order to check with trivia
	 * @return the answers as an array and given to trivia
	 */
	public static String[] giveAnswer(String[] answers)
	{
		//do not add anything else for now this should be a simple pass
		return answers;
	}
	
	/**
	 * 
	 * @param items given from player in order to show GI and gameScore
	 * @return the item list to give to GI and gameScore
	 */
	public static int[] itemList(int[] items)
	{
		//another simple array carry over
		return items;
	}
	
	/**
	 * 
	 * @return the index of the cave they want to shoot for GameLocations
	 */
	public static int shootArrow()
	{
		//Do not include 'shot' boolean as a parameter, do it inside main code
		//code for getting the index (using same rules as the movingPlayer)
		return 0;
	}
	
	/**
	 * 
	 * @return boolean of whether or not Wumpus was shot
	 */
	public static boolean shotWumpus()
	{
		//return true if the room shot has the Wumpus
		//I need the return value from GameLocations after giving it 'shootArrow' method
		return false;
	}
	
	/**
	 * 
	 * @return the total high score for the GI
	 */
	public static int getScore()
	{
		//input the player method that calculates the high score
		return 0;
	}
	/**
	 * 
	 * @param int array of dangers with 0, 1, 2 being the bat, cave, and wumpus
	 * @return the type of the cave given as an integer for easier handling
	 */
	public static int[] Hazard(int[] dangers)
	{
		//returns array of dangers in cave
		return dangers;
	}
	/**
	 * 
	 * @return whether or not the Wumpus is within 2 rooms of the player (for ease of use to the trivia class)
	 */
	public static boolean WumpusIsnear() 
	{
		//code here that asks game location to compare the the distance between the player and wumpus class
		return false;
	}
}
