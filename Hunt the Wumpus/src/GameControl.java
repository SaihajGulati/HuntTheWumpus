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

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	
	public static void main(String[] args) throws MalformedURLException, LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		//start program and builds GI
		GraphicalInterface GI = new GraphicalInterface(BATS, WUMPUS, HOLE);
		//error handling for loading up files
		try {
			HighScore.loadFiles();
			Sounds.setTheme(1);
		
		}
		catch(FileNotFoundException error) {
			System.out.println("Error Occured : Loading Files");
			GraphicalInterface.Error();
		}
		String name = "";
		int caveSelect = 0;
		GraphicalInterface.start();
		boolean startNew = true;
		//asf
		//keeps game running
		while (true)
		{
			//if the game is a new game
			Trivia trivia = new Trivia();
			Player player = new Player();
			Sounds.stop();
			if (startNew)
			{
				caveSelect = GI.mainmenu(HighScore.getCaves(), HighScore.getNames(), HighScore.getScores(), false);
				name = GI.getName();
				if(name.toLowerCase().equals("artesian code")) {
					player.changeCoins(50);
					player.changeArrows(7);
				}
			}
			/*if (caveSelect < 0)
			{
				Sounds.setTheme(-1*caveSelect);
			}*/
			Sounds.movePlayer();
			//starts the game
			Cave cave = new Cave(caveSelect);
			GameLocations locations = new GameLocations(cave);
			String reason = startGame(GI, player, cave, trivia);
			//sadf
			int score = 0;
			//error handling
			if(!reason.equals(null)) {
				//if player wins
				if (reason.equals("won"))
				{
					score = player.getScore(true);
					try {

						endGame(caveSelect, GI, name, player, reason, score);
					}
					catch(FileNotFoundException e) {
						System.out.println("Error Occured : Gamecontrol endGame method call");
						GI.Error();
					}
					startNew = true;
				}
				//if player left and saved game
				else if (reason.substring(0,4).equals("menu")) {
					try {
						caveSelect = Integer.parseInt(reason.substring(4,5));
						name = reason.substring(5);
						startNew = false;
					}
					catch(IllegalArgumentException e) {
						System.out.println("Error Occured : GameControl parsing string");
						GI.Error();
					}
					
				}
				//if player died
				else
				{
					score = player.getScore(false);
					Sounds.lose();
					try {

						endGame(caveSelect, GI, name, player, reason, score);
					}
					catch(FileNotFoundException e) {
						System.out.println("Error Occured : Gamecontrol endGame method");
						GI.Error();
					}
					startNew = true;
				}
					
			}
			//error handling
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
				// simply sets the variables for later use
			room = GameLocations.getPlayerLocation();
			rooms = cave.tunnels(room);
			hazards = GameLocations.warning();
			hazardsSurvived = new int[3];
			//response gathered from player for room/shoot/buy
			response = GI.getRoom(rooms[0], rooms[1], rooms[2], hazards, player.getTurns(), player.getCoins(), player.getArrows()); 
			//if player moves
			if(response > 0)
			{	
				player.movePlayer();
				printHazardLocs();
				GameLocations.movePlayer(response);
				Sounds.movePlayer();
				room = GameLocations.getPlayerLocation();
				GI.betweenTurns(triv.giveTrivia(), room, player.getTurns(), player.getCoins(), player.getArrows(), false, 0);

				//checks if player is in a room with a hazard
				if(GameLocations.getPlayerLocation() == GameLocations.getWumpusLocation()) {
					room_hazards[WUMPUS] = 1;
				}
				for(int i : GameLocations.getPitLocations()) {
					if(room == i) {
						room_hazards[HOLE] = 1;
						Sounds.pit();
					}
					
				}
				for(int i : GameLocations.getBatLocations()) {
					if(room == i) {
						room_hazards[BATS] = 1;
						Sounds.bat();
					}
					
				}
				GI.displayDanger(room_hazards);
				room_hazards = new int[3];
				survived = false;
				//for encountering a wumpus, trivia pops up
				if (room == GameLocations.getWumpusLocation())
				{
					Sounds.triviaPopUp();
					if(!trivia(triv, GI, player, 5, 3))
					{
						//wumpus loss
						
						return "wumpus";
					}
					else
					{
						//wumpus moves
						Sounds.moveWumpus();
						GameLocations.moveWumpus();
						hazardsSurvived[WUMPUS] = 1;
						survived = true;
					}
				} 
				//triggers bat
				for (int c: GameLocations.getBatLocations())
				{
					if(room == c) {//
						room = GameLocations.triggerBat();
					}
				}
						
				//encounter a pit causes trivia
				for (int c: GameLocations.getPitLocations())
				{
					if(room == c) 
					{
						Sounds.triviaPopUp();
						if(!trivia(triv, GI, player, 3, 2))
						{
							//pit loss
							
							return "pits";
						}
						else
						{
							//survived and escape pit
							hazardsSurvived[HOLE] = 1;
							survived = true;
							GameLocations.triggerPit();
						}
						
					}
				}
				//GI shows what dangers escaped
				if (survived)
				{
					GI.escapedDanger(hazardsSurvived);
				}
				
				
			}
			else if (response != 0)
			{
				Sounds.stop();
			
			//if player shoots an arrow
			if (response == ARROW)
			{
				int arrowShot;
				
				//while the player hasn't chosen a room yet this loops
			     arrowShot = GI.shootArrow(rooms[0], rooms[1], rooms[2], hazards, player.getTurns(), player.getCoins(), player.getArrows());
				
				//if the person decides to go back
				
				if(arrowShot != 0) {
					Sounds.shootArrow();
					player.changeArrows(-1);
					//if player hit wumpus
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

				if (player.getArrows() == 0)
				{
					
					return "arrows";
				}
									
				
			}
			
			//if player wants to leave game but not exit
			else if (response == MAIN_MENU)
			{
				int caveSelect = GI.mainmenu(HighScore.getCaves(), HighScore.getNames(), HighScore.getScores(), true);
				if (caveSelect > 0)
				{
					String name = GI.getName();
					return "menu" + caveSelect + name;
					
				}
				
			}
			
			//if player wants to buy an item
			else if (response == BUY_ITEM)
			{
				int stuff = 0;
				stuff = GraphicalInterface.buyItem(player.getCoins() > 3);
				//if they want to buy arrows
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
				//if they want to buy a secret
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
			Sounds.movePlayer();
			}
			
			
		}

		//if player runs out of coins
		
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
			//error handling and shows end game score and updates score board
		try {
			GraphicalInterface.endGame(reason, score, HighScore.updateScoreBoard(score, Name, caveName));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error Occured : GI endGame method");
			GI.Error();
		}
		
	}	
	/**
	 * prints what hazards there are in adjacent rooms
	 */
	//for testing
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
		//while the player has answered less than required questions or required correct questions
		while (correct < numC && count < numQ && wrong < numC)
		{	
			char answer = GI.getAnswer(triv.getQuestion(), triv.getA(), triv.getB(), triv.getC(), triv.getD());
			//if answer is correct
			if (triv.checkAnswer(answer))
			{
				correct++;
				count++;
				GraphicalInterface.postTrivia(true, count, correct);
			}
			//if answer is incorrect
			else
			{
				count++;
				GraphicalInterface.postTrivia(false, count, correct);
				wrong++;
			}
			player.changeCoins(-1);
		}
		//player dies if correct is less than number of num questions
		if (correct < numC)
		{
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
