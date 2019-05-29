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
 * 5.0		  4/3/19   Fixed GI problems and added loop for main menu
 * 6.0        5/15/19  Fixed alot of bugs with the main menu
 * 7.0        5/25/19  Added code for printing to the console for checkpoints 2 and 3
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
		Trivia.loadFiles();
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
		//GI.gameGraphics();
		boolean gotWumpus = startGame(GI, player, cave);
		int score = player.getScore(gotWumpus);
		endGame(caveSelect, name, score);
	}
			
	/**
	 * Starts up the Game
	 * @param start gets a call from GI to see if player wants to start
	 * @param GI is the Graphical interface
	 * @param player is the player class
	 * @param cave is the cave class
	 */
	public static boolean startGame(GraphicalInterface GI, Player player, Cave cave) throws FileNotFoundException
	{
		
		Scanner input = new Scanner(System.in);
		System.out.println("Test");
		int response = 0; //response given by player in GI
		int room; //room player is currently in
		int[] temp; 
		int[] rooms = new int[3];//the rooms around the player
		int[] hazards = new int[3]; // 0: bat | 1 : Hole | 2 : Wumpus 
		
		//the while loop that runs the game while the player is alive, has arrows, and has coins
		
		while(player.getArrows() > 0 && player.getCoins() > 0 || player.getTurns() == 0) {
			//while player hasn't chosen in GI yet
			//while(true) {
				// simply sets the variables for later use
				room = GameLocations.getPlayerLocation();
				rooms = cave.tunnels(room);
				hazards = GameLocations.warning();
				response = GI.getRoom(rooms[0], rooms[1], rooms[2], hazards); //response gathered from player
				if(response > 0)
				{
							player.movePlayer();
							System.out.println(Trivia.giveTrivia()); 
							//commit
							GameLocations.movePlayer(response);//
							room = GameLocations.getPlayerLocation();
							for (int c: GameLocations.getBatLocations())
							{
								if(room == c) {//
									player.changeCoins(-1);
									room = GameLocations.triggerBat(); //hola
									}
								}
							
							for (int c: GameLocations.getPitLocations())
							{
								if(room == c) 
								{
									player.changeCoins(-1);
									/*if(!Trivia.askQuestions(HOLE)) {
										return false;
									}		
								}
								else {
									break;
								*/}
							}
							if (room == GameLocations.getWumpusLocation())
							{
								player.changeCoins(-1);
								/*if(!Trivia.askQuestions(WUMPUS)) {
									return false;
								}
								else {
									break;
								}*/
							} 
						
					}
				else if (response == -1)
				{
					int arrowShot = 0;
					
					//while the player hasn't chosen a room yet, if -1 then it will go back
					
					while(arrowShot == 0 && arrowShot != -1) {
						arrowShot = GI.shootArrow(rooms[0], rooms[1], rooms[2], hazards);
					}
						player.changeArrows(-1);
						
						//if the person decides to go back
						
						if(arrowShot != -1) {
							if (GameLocations.shootArrow(arrowShot))
							{
								return true; //player has won
							}
							else 
							{
								//add code for GI that states you missed
							}	
						}
										
					
				}
			//}
			//printHazardLocs(); //for testing purposes
			//saihaj is bad
			
			// If the player has chosen to move to a place instead of shooting
			
		}
		return false;
			
	}
		//Testing out which oom they are in

		
	/**
	 * 
	 * @param end if the game ends
	 * @param GI the GraphicalInterface class
	 * @param player the player class
	 * @param score the HighScore Class
	 * @param locations The GameLocations class
	 * @throws FileNotFoundException 
	 */
	public static void endGame(int caveName, String Name, int score) throws FileNotFoundException{
		HighScore.updateScoreBoard(score, Name, caveName);
	}	
	/**
	 * prints what hazards there are in adjacent rooms
	 */
	public static boolean printHazardWarnings()
	{
		int[] hazards = GameLocations.warning();
		if (hazards[0] > 0)
		{
			System.out.println("Bats Nearby");
		}
		if (hazards[1] > 0)
		{
			System.out.println("I feel a draft nearby");
		}
		if (hazards[2] > 0)
		{
			System.out.println("I smell a Wumpus!");
		}
		return false;
	}
	
	/*public static void printHazardLocs()
	{
		System.out.print("For testing:");
		System.out.println();
		System.out.println("Bats are located in rooms " + arrayString(GameLocations.getBatLocations(), "and"));
		System.out.println("Pits are located in rooms " + arrayString(GameLocations.getPitLocations(), "and"));
		System.out.println("Wumpus is located in room " + GameLocations.getWumpusLocation());
		System.out.println();
	}*/
	
	/**
	 * prints out an array in normal english
	 * @param array which is the array to print
	 * @param the string representing the combining clause
	 */
	public static String arrayString(int[] array, String andor)
	{
		String word = "";
		if (array.length > 2)
		{
			for (int i = 0; i < array.length-1 ; i++)
			{
				word += (array[i] + "," + " ");
			}
			word += (andor + " " + array[array.length-1] + ". ");
		}
		else 
		{
			word += (array[0] + " " + andor + " " + array[1] + ".");
		}
		return word;
	}
	
	/**
	 * @param array which is the array to search in
	 * @param find which is the value to find
	 * @return index of first value in the array, -1 otherwise
	 */
	
	public static int findIndex(int[] array, int find)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == find)
			{
				return i;
			}
		}
		return -1;
	}
}
