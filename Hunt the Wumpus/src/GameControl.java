/*
 * 
 * @author : Joshua Venable
 * @Class : AP Computer Science A
 * @Object : Game Control : Controls the interaction between every other object
 * 
 * Revision History:
 * Version:   Date:     Description:
 * 1.0        3/4/19    Constructor and toString added
 * 2.0        3/8/19    Stubs added (fields and skeleton methods)
 * 3.0        3/19/19   Added calls to methods for testing and implemented a few methods
 * 4.0        3/25/19  Added starting point in main however needs to be fixed with GI
 * 5.0		  4/3/19  Fixed GI problems and added loop for main menu
*/
import java.io.File;
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
	public static int BATS = 0;
	public static int HOLE = 1;
	public static int WUMPUS = 2;
	public static int SCORE = 0;
	public static int ROUND = 0;
	//following e-nums are for the start of the game and anywhere else so its easier to identify changes
	public static int START = 0;
	public static int HIGHSCORE = 1;
	public static int QUIT = 2;
	public static void main(String[] args) throws FileNotFoundException
	{
		String[][] caveArray = new String[30][30];
		Cave cave = new Cave(caveArray);
		GameLocations locations = new GameLocations(cave);
		GraphicalInterface GI = new GraphicalInterface(BATS, HOLE, WUMPUS);
		HighScore score = new HighScore("input/HighScores.txt");
		Player player = new Player();
		Trivia trivia = new Trivia("input/Trivia.txt");
		System.out.println(cave);
		System.out.println(locations);
		System.out.println(GI);
		System.out.println(score);
		System.out.println(player);
		System.out.println(trivia);
		int Player_Choice = 3;
		while(Player_Choice == 3) {
		
			GI.Display(args);
			Player_Choice = 0;
			if(Player_Choice == START){
				startGame(GI, player, cave, locations);
			}
			else if(Player_Choice == HIGHSCORE){
				//GI.displayHighscore(HighScore.getHighScores()); //change param to ArrayList
			}
			else if(Player_Choice == QUIT){
				System.out.println("Thank you for playing!");
				break;
			}
			else {
				System.out.println("ERROR");
			}
		}
		}
			
	/**
	 * Starts up the Game
	 * @param start gets a call from GI to see if player wants to start
	 * @param GI is the Graphical interface
	 * @param player is the player class
	 * @param cave is the cave class
	 */
	public static void startGame(GraphicalInterface GI, Player player, Cave cave, GameLocations locations) throws FileNotFoundException
	{
		boolean inPit = false, inBats = false, inWumpus = false, isAlive = true;
		int i = 0;
		cave.openCaveFile();
		cave.readCaveFile();
		//loop that runs the whole game while the player is alive;
		while(player.getArrows() > 0 && isAlive) {
			while(!inBats && i < GameLocations.getBatLocations().length) {
				if(GameLocations.getPlayerLocation() == GameLocations.getBatLocations()[i]) {
					inBats = true;
					i = 0;
				}
			}
			while(!inPit && i < GameLocations.getPitLocations().length){
				if(GameLocations.getPlayerLocation() == GameLocations.getPitLocations()[i]) {
					inPit = true;
					i = 0;
				}
			}
			if(GameLocations.getPlayerLocation() == GameLocations.getWumpusLocation()) {
				inWumpus = true;
				i = 0;
			}
			
			GI.displayItems();
			
			if(inWumpus) {
				if(!Trivia.askQuestions(WUMPUS)) {
					isAlive = false;
					break;
				}
			}
			if(inBats) {
				Trivia.askQuestions(BATS);
			}
			if(inPit) {
				if(!Trivia.askQuestions(HOLE)) {
					isAlive = false;
					break;
				}
			}
			GI.inDanger(cave.adjacentRooms(GameLocations.getPlayerLocation()));
		}
		
		}
	/**
	 * 
	 * @param end if the game ends
	 * @param GI the GraphicalInterface class
	 * @param player the player class
	 * @param score the HighScore Class
	 * @param locations The GameLocations class
	 * @throws FileNotFoundException 
	 */
	public static void endGame(GraphicalInterface GI, Player player, HighScore score, GameLocations locations) throws FileNotFoundException {

			int endScore = player.getScore(GameLocations.shootArrow(GameLocations.getWumpusLocation()));
			HighScore.updateScoreBoard(endScore);
			//GI.displayHighscore(HighScore.getHighScores());

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
