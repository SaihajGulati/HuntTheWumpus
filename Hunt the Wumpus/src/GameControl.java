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
		//hello testing
		GraphicalInterface GI = new GraphicalInterface(BATS, HOLE, WUMPUS);
		Player player = new Player();
		ArrayList <String> scores = new ArrayList<String>();
		//starts the game
		scores.add(" 1. Cave 1; Bob; 44");
		 scores.add(" 2. Cave 1; Josh; 34");
		 scores.add(" 3. Cave 1; Brian; 64");
		 scores.add(" 4. Cave 1; Okay; 74");
		 scores.add(" 5. Cave 1; Hello; 84");
		 scores.add(" 6. Cave 1; Why; 94");
		 scores.add(" 7. Cave 1; No; 24");
		 scores.add(" 8. Cave 1; Bye; 14");
		 scores.add(" 9. Cave 1; Hello; 84");
		 scores.add("10. Cave 1; Joe; 94");
		GI.start();
		String name = "";
		int caveSelect = 0;
		while(name.equals("")) {
			caveSelect = GI.mainmenu(scores);
			name = GI.getName();
		}
		Cave cave = new Cave(caveSelect);
		GameLocations locations = new GameLocations(cave);
		GI.gameGraphics();
		startGame(GI, player, cave);
	}
			
	/**
	 * Starts up the Game
	 * @param start gets a call from GI to see if player wants to start
	 * @param GI is the Graphical interface
	 * @param player is the player class
	 * @param cave is the cave class
	 */
	public static void startGame(GraphicalInterface GI, Player player, Cave cave) throws FileNotFoundException
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Test");
		boolean inPit = false, inBats = false, inWumpus = false, isAlive = true;
		//loop that runs the whole game while the player is alive;
		while(player.getArrows() > 0 && isAlive) {
			/*for (int i: GameLocations.getBatLocations())
			{
				if(GameLocations.getPlayerLocation() == i) {//
					if(!Trivia.askQuestions(BATS)) {
						break;
					}
				}
			}
			for (int i: GameLocations.getPitLocations())
			{
				if(GameLocations.getPlayerLocation() == i) 
				{
					if(!Trivia.askQuestions(BATS)) {
						break;
					}		
				}
			}
			if (GameLocations.getPlayerLocation() == GameLocations.getWumpusLocation())
			{
				if(!Trivia.askQuestions(WUMPUS)) {
					break;
				}
			} */
			boolean start = false;
			int room = GameLocations.getPlayerLocation();
			System.out.println("Room: " + room);
			int[] rooms = cave.tunnels(room);
			int choice = input.nextInt();
			for(int i : rooms) {
				System.out.println(i);
				if(choice == i) {
					start = true;
				}
			}
			if(start) {
				GameLocations.movePlayer(choice);
			}
			
		}
		//Testing out which oom they are in

		
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
	public static void endGame(GraphicalInterface GI, Player player, HighScore score, GameLocations locations, String Name, int CaveName) throws FileNotFoundException {

			int endScore = player.getScore(GameLocations.shootArrow(GameLocations.getWumpusLocation()));
			HighScore.updateScoreBoard(endScore, Name, CaveName);
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
