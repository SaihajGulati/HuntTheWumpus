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
import java.io.*;
import java.util.*;
import java.applet.*;
import java.net.*;
/**
 * MAJOR NOTES:
 * 
 * 	Figure out how to get the adjacent room index?
 * 	Add Wumpus control methods but what do we need for that? 
 *please
 */
public class GameControl
{
	public static final int BATS = 0;
	public static final int HOLE = 1;
	public static final int WUMPUS = 2;
	public static final int SCORE = 0;
	public static final int ROUND = 0;
	//following e-nums are for the start of the game and anywhere else so its easier to identify changes
	public static final int START = 0;
	public static final int HIGHSCORE = 1;
	public static final int QUIT = 2;
	public static final int ARROW = -1;
	public static final int BUY_ITEM = -2;
	public static final int MAIN_MENU = -3;
	
	public static void main(String[] args) throws FileNotFoundException, MalformedURLException
	{
		//start program and builds GI
		GraphicalInterface GI = new GraphicalInterface(BATS, WUMPUS, HOLE);

		//error handling for loading up files
		try {
			HighScore.loadFiles();
			try {
				Sounds sounds = new Sounds(1);
			}
			catch(MalformedURLException e) {
				System.out.println("Error Occured : loading Sounds");
				GraphicalInterface.Error();
			}//
						
		}
		catch(FileNotFoundException error) {
			System.out.println("Error Occured : Loading Files");
			GraphicalInterface.Error();
		}
		String name = "";
		int caveSelect = 0;
		GraphicalInterface.start();
		boolean startNew = false;
		
		while (true)
		{
			Trivia trivia = new Trivia();
			Player player = new Player();
			if (!startNew)
			{
				caveSelect = GI.mainmenu(HighScore.getHighScores(), false);
				name = GI.getName();
			}
			//starts the game
			Cave cave = new Cave(caveSelect);
			GameLocations locations = new GameLocations(cave);
			String reason = startGame(GI, player, cave, trivia);
			//sadf
			int score = 0;
			//fix
			if(!reason.equals(null)) {
				if (reason.equals("won"))
				{
					score = player.getScore(true);
					endGame(caveSelect, GI, name, player, reason, score);
					startNew = false;
				}
				else if (reason.substring(0,4).equals("menu")) {
					caveSelect = Integer.parseInt(reason.substring(4,5));
					name = reason.substring(5);
					startNew = true;
				}
				else
				{
					System.out.println(reason);
					score = player.getScore(false);
					endGame(caveSelect, GI, name, player, reason, score);
					startNew = false;
					System.out.println(startNew);
				}
					
			}
			else
			{
				System.out.println("Error Occured : reason was null");
				GI.Error();
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
	public static String startGame(GraphicalInterface GI, Player player, Cave cave, Trivia triv)
	{
		Scanner input = new Scanner(System.in);
		int response = 0; //response given by player in GI
		int room = 0; //room player is currently in
		int[] rooms = new int[3];//the rooms around the player
		int[] hazards = new int[3]; // 0: bat | 1 : Hole | 2 : Wumpus 
		int[] room_hazards = new int[3]; // same as hazards but for use with hazards in the room not just nearby
		printHazardLocs();
		int[] hazardsSurvived = new int[3];
		boolean survived = false;
		
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
				printHazardLocs();
				GameLocations.movePlayer(response);//
				Sounds.movePlayer();
				room = GameLocations.getPlayerLocation();
				GI.betweenTurns(triv.giveTrivia(), room, player.getTurns(), player.getCoins(), player.getArrows());

				if(GameLocations.getPlayerLocation() == GameLocations.getWumpusLocation()) {
					room_hazards[WUMPUS] = 1;
				}
				for(int i : GameLocations.getPitLocations()) {
					if(room == i) {
						room_hazards[HOLE] = 1;
					}
					
				}
				for(int i : GameLocations.getBatLocations()) {
					if(room == i) {
						room_hazards[BATS] = 1;
					}
					
				}
				GI.displayDanger(room_hazards);
				room_hazards = new int[3];

				if (room == GameLocations.getWumpusLocation())
				{
					Sounds.triviaPopUp();
					if(!trivia(triv, GI, player, 5, 3))
					{
						Sounds.lose();
						return "wumpus";
					}
					else
					{
						Sounds.moveWumpus();
						GameLocations.moveWumpus();
						hazardsSurvived[WUMPUS] = 1;
						survived = true;
					}
				} 
				for (int c: GameLocations.getBatLocations())
				{
					if(room == c) {//
						room = GameLocations.triggerBat();
					}
					else
					{
						hazardsSurvived[BATS] = 1;
						survived = true;
					}
				}
						
				for (int c: GameLocations.getPitLocations())
				{
					if(room == c) 
					{
						Sounds.triviaPopUp();
						if(!trivia(triv, GI, player, 3, 2))
						{
							Sounds.lose();
							return "pits";
						}
						else
						{
							hazardsSurvived[HOLE] = 1;
							survived = true;
						}
						
					}
				}
				if (survived)
				{
					GI.escapedDanger(hazardsSurvived);
				}
				
				
			}
			else if (response == ARROW)
			{
				int arrowShot;
				
				//while the player hasn't chosen a room yet this loops
			     arrowShot = GI.shootArrow(rooms[0], rooms[1], rooms[2], hazards, player.getTurns(), player.getCoins(), player.getArrows());
			     Sounds.shootArrow();
				/*if (arrowShot != 0)
				{
					player.changeArrows(-1);
				}*/
				
				//if the person decides to go back
				
				if(arrowShot != 0) {
					player.changeArrows(-1);
					if (GameLocations.shootArrow(arrowShot))
					{
						Sounds.win();
						GraphicalInterface.arrowHit(true, player.getArrows());
						return "won"; //player has won
					}
					else 
					{
						Sounds.moveWumpus();
						GraphicalInterface.arrowHit(false, player.getArrows());//add code for GI that states you missed
					}	
				}
									
				
			}
			else if (response == MAIN_MENU)
			{
				int caveSelect = GI.mainmenu(HighScore.getHighScores(), true);
				if (caveSelect > 0)
				{
					String name = GI.getName();
					return "menu" + caveSelect + name;
					
				}
				
			}
			else if (response == BUY_ITEM)
			{
				int stuff = 0;
				stuff = GraphicalInterface.buyItem(player.getCoins() > 3);
				
				if (stuff == 1)
				{
					Sounds.triviaPopUp();
					if(trivia(triv, GI, player, 3, 2))
					{

						player.changeArrows(2);
						GraphicalInterface.boughtArrow(player.getArrows());
					}
					else
					{
						GraphicalInterface.goof();
					}
				}
				else if (stuff == 2)
				{
					Sounds.triviaPopUp();
					if(trivia(triv, GI, player, 3, 2))
					{

						GraphicalInterface.tellSecret(triv.returnHint());
					}
					else
					{
						GraphicalInterface.goof();
					}
					
				}
			}
			else {
				//this is while player does not choose an option
			}
			// If the player has chosen to move to a place instead of shooting
			
		}
		if (player.getArrows() == 0)
		{
			Sounds.lose();
			return "arrows";
		}
		Sounds.lose();
		return "coins";
			
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
	public static void endGame(int caveName, GraphicalInterface GI, String Name, Player player, String reason, int score) throws FileNotFoundException{
		 try {
			GraphicalInterface.endGame(reason, score, HighScore.updateScoreBoard(score, Name, caveName));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error Occured : endGame method");
			GI.Error();
		}
		
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
				count++;
				GraphicalInterface.postTrivia(true, count, correct);
			}
			else
			{
				count++;
				GraphicalInterface.postTrivia(false, count, correct);
				wrong++;
			}
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
		try {
			for (int i = 0; i < array.length; i++)
			{
				if (array[i] == find)
				{
					return i;
				}
			}
			return -1;
		}
		catch(NullPointerException e) {
			System.out.println("Error Occured : findIndex");
			GraphicalInterface.Error();
		}
		return -1;
	}
}
