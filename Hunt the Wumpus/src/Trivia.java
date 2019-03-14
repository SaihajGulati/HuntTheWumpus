
/* Name: Brian Yang
 * Class: APCSA Period 3
 * Teacher: Mrs. Kankelborg
 * The Trivia object handles the trivia questions asked during the game, such as loading them from a file
 * and asking them when the Player's actions trigger them (ex. encountering the Wumpus).
 * 
 * 3/4/19: Version 1.0
 * Added the class header, constructor, and toString method.
 * 3/7/19: Version 1.1
 * Added a stubbed class for the HighScore.
*/
import java.util.*;
import java.io.*;
public class Trivia 
{
	Scanner input;
	private ArrayList<String> triviaQuestions; //The trivia questions that will be asked during the game 
	private ArrayList<String> triviaAnswers; /*The answers to the trivia questions; will be updated alongside
											 *the triviaQuestions ArrayList.
											 */
	private String[] triviaInformation; /*The trivia information that will be given out for every Player turn;
										*unlike the triviaAnswers ArrayList this array won't remove values for
										*every question asked.
										*/
	private String[] secrets; //The secrets that will be given to the Player when he/she buys them.
	
	/*The constructor for the Trivia object that will handle the File processing and fill the
	 * Trivia-related ArrayLists and arrays with their appropriate values, in addition to instantiating the
	 * Scanner for user input.
	*/
	public Trivia()
	{
		
	}
	
	/*The purpose of this method is to ask the Player trivia questions and have him/her answer them; it will
	 * then compare the Player's response to the question's corresponding answer String in the triviaAnswers
	 * ArrayList and return a boolean of whether the Player has answered the minimum number of questions correctly
	 * (depending on the scenario).
	 * 
	 * Parameter: scenario (The scenario that caused the Player to be asked the trivia, such as a Wumpus
	 * encounter for example).
	 * 
	 * Return Value: boolean (Whether the Player has answered the minimum number of questions correctly, 
	 * depending on their scenario)
	 * 
	 * Precondition: the Player's scenario is either buying arrows/hints, falling into a bottomless pit,
	 * or encountering the Wumpus.
	 * 
	 * Postcondition: Any question asked in the method will never be asked again.
	*/
	public boolean askQuestions(String scenario)
	{
		return false;
	}
	
	/*The purpose of this method is to return a hint or secret if the Player has bought one by successfully
	 * answering 2 out of 3 trivia questions correctly. It will pick a random array to choose its return value
	 * from (triviaInformation or secrets) and then select a random String from one of those arrays to return
	 * to the Player.
	 * 
	 * Parameters: int currentRoom (the number of the room that the Player is currently in), int wumpusRoom
	 * (the number of the room that the Wumpus is currently in), int batRoom (the number of one of the rooms
	 * that currently has a bat), int pitRoom (the number of one of the rooms with a bottomless pit), boolean
	 * wumpusNear (whether the Wumpus is within 2 rooms of the Player).
	 * 
	 * Return Value: String (the hint that the Player would eventually get from the purchase)
	 * 
	 * Other methods that returnHint may call: giveTrivia (if the array chosen happens to be triviaInformation where
	 * the secret that the Player will receive is a piece of trivia, returnHint will call the giveTrivia method).
	 * 
	 * Precondition: The Player has answered 2 trivia questions correctly to purchase the secret.
	 * 
	 * Please note that this method may have to be called from gameControl after askQuestions returns its
	 * boolean, as a method can only return one value at a time.
	*/
	public String returnHint(int currentRoom, int wumpusRoom, int batRoom, int pitRoom, boolean wumpusNear)
	{
		return "a";
	}
	
	/*The purpose of this method is to select a random piece of trivia from the triviaInformation array,
	 *and return it (either when the Player purchases a hint/secret or moves forward).
	 * 
	 * Parameter: Void
	 * 
	 * Return Value: String (the piece of trivia that the Player will eventually get)
	*/
	public String giveTrivia()
	{
		return "a";
	}
	
	//Prints a description of the Trivia object rather than its address in computer's memory.
	public String toString()
	{
		return "Trivia";
	}
}
