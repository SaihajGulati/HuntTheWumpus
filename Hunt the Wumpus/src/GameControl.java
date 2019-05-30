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
		Trivia trivia = new Trivia();
		HighScore.loadFiles();
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
		boolean gotWumpus = startGame(GI, player, cave, trivia);
		int score = player.getScore(gotWumpus);
		endGame(caveSelect, GI, name, score, gotWumpus);
	}
			
	/**
	 * Starts up the Game
	 * @param start gets a call from GI to see if player wants to start
	 * @param GI is the Graphical interface
	 * @param player is the player class
	 * @param cave is the cave class
	 */
	public static boolean startGame(GraphicalInterface GI, Player player, Cave cave, Trivia triv) throws FileNotFoundException
	{
		
		Scanner input = new Scanner(System.in);
		System.out.println("Test");
		int response = 0; //response given by player in GI
		int room; //room player is currently in
		int[] rooms = new int[3];//the rooms around the player
		int[] hazards = new int[3]; // 0: bat | 1 : Hole | 2 : Wumpus 
		int[] room_hazards = new int[3]; // same as hazards but for use with hazards in the room not just nearby
		printHazardLocs();
		
		//the while loop that runs the game while the player is alive, has arrows, and has coins
		
		while(player.getArrows() > 0 && player.getCoins() > 0 || player.getTurns() == 0) {
			//while player hasn't chosen in GI yet
			//while(true) {
				// simply sets the variables for later use
				room = GameLocations.getPlayerLocation();
				rooms = cave.tunnels(room);
				hazards = GameLocations.warning();
				response = GI.getRoom(rooms[0], rooms[1], rooms[2], hazards, player.getTurns(), player.getCoins(), player.getArrows()); //response gathered from player
				if(response > 0)
				{
					player.movePlayer();
					GI.betweenTurns(triv.giveTrivia(), room, player.getTurns(), player.getCoins(), player.getArrows());
					for(int i : GameLocations.getPitLocations()) {
						room_hazards[0] = 1;
					}
					for(int i : GameLocations.getBatLocations()) {
						room_hazards[1] = 1;
					}
					if(GameLocations.getPlayerLocation() == GameLocations.getWumpusLocation()) {
						room_hazards[2] = 1;
					}
					GI.displayDanger(room_hazards);
					room_hazards = new int[3];
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

							if(!trivia(triv, GI, player, 3, 2))
							{
								return false;
							}
							
						}
					}
					if (room == GameLocations.getWumpusLocation())
					{
						if(!trivia(triv, GI, player, 5, 3))
						{
							return false;
						}
					} 
					
				}
				else if (response == -1)
				{
					int arrowShot = 0;
					
					//while the player hasn't chosen a room yet, if -1 then it will go back
					
					while(arrowShot == 0 && arrowShot != -1) {
						arrowShot = GI.shootArrow(rooms[0], rooms[1], rooms[2], hazards, player.getTurns(), player.getCoins(), player.getArrows());
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
				else if (response == -2)
				{
					int stuff = 0;
					stuff = GI.buyItem(player.getCoins() > 3);
					
					if (stuff == 1)
					{
						
						if(trivia(triv, GI, player, 3, 2))
						{

							player.changeArrows(1);
							GI.boughtArrow(player.getArrows());
						}
						else
						{
							GI.goof();
						}
					}
					else if (stuff == 2)
					{
						if(trivia(triv, GI, player, 3, 2))
						{

							GI.tellSecret(triv.returnHint());
						}
						else
						{
							GI.goof();
						}
						
					}
				}
			//}
			//printHazardLocs(); //for testing purposes
			// If the player has chosen to move to a place instead of shooting
			
		}
		System.out.print("Josh code ass");
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
	public static void endGame(int caveName, GraphicalInterface GI, String Name, int score, boolean win) throws FileNotFoundException{
		GI.endGame(win);
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
	
	/**
	 * handles asking the user for trivia
	 * @param numQ the number of questions to ask
	 * @param numc the number of qs that need to be answered correct
	 */
	public static boolean trivia(Trivia triv, GraphicalInterface GI, Player player, int numQ, int numC)
	{
		int correct = 0;
		int count = 0;
		int wrong = 0;
		while (correct < numC && count < numQ && wrong < numC)
		{	
			char answer = GI.getAnswer(triv.getQuestion(), triv.getA(), triv.getB(), triv.getC(), triv.getD());
			if (triv.checkAnswer(answer))
			{
				correct++;
				GI.postTrivia(true);
			}
			else
			{
				GI.postTrivia(false);
				wrong++;
			}
			count++;
			player.changeCoins(-1);
		}
		if (correct < numC)
		{
			System.out.println("You died");
			return false;
		}
		return true;
	}
	
	public static void printHazardLocs()
	{
		System.out.print("For testing:");
		System.out.println();
		System.out.println("Bats are located in rooms " + arrayString(GameLocations.getBatLocations(), "and"));
		System.out.println("Pits are located in rooms " + arrayString(GameLocations.getPitLocations(), "and"));
		System.out.println("Wumpus is located in room " + GameLocations.getWumpusLocation());
		System.out.println();
	}
	
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
