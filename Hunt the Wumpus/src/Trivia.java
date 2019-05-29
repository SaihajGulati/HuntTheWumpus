/** 
 * Name: Brian Yang
 * Class: APCSA Period 3
 * Teacher: Mrs. Kankelborg
 * The Trivia object handles the trivia questions asked during the game, such as loading them from a file
 * and asking them when the Player's actions trigger them (ex. encountering the Wumpus).
 * 
 * Test Comment 2
 * 
 * 3/4/19: Version 1.0
 * Added the class header, constructor, and toString method.
 * 3/7/19: Version 1.1
 * Added a stubbed class for the HighScore.
 * 3/19/19: Version 1.2
 * Implemented the methods askQuestions and getTrivia, along with the constructor for Trivia.
 * 3/21/19: Version 1.3
 * Made the method static
 * 3/22/19: Version 1.4
 * Added the declaration/instantiation of the secrets ArrayList
 * 3/25/19: Version 1.5
 * Added the body of the returnHint method, and a new method called wumpusNearPlayer.
 * 4/03/19: Version 1.6
 * Made some minor changes to the scenario numbers for the askQuestions method 
 * (started from 0 rather than 1). Also added descriptions for the while loops
 * in the class methods.
 * 4/15/19: Version 1.7
 * Added a comment for the private File trivia
 * 4/24/19: Version 1.8
 * Changed the scenario integers for askQuestions another time (buying hints/arrows is 0; 
 * falling into a pit is 1; encountering the Wumpus is 2)
 * 5/15/19: Version 1.9
 * Changed the constructor to a method called loadFiles.
 * 5/24/19: Version 2.0
 * Changed the trivia answers to multiple-choice, including the modification of the class'
 * associated text file.
 * 5/27/19: Version 2.1
 * Removed an error in the "Wumpus encounter" scenario, which was a ".substring(14)" statement
 * when printing the different answer choices. (the removed error can be found commented out
 * on its respective line in the code. Also added a "Q: " header to the beginning of the questions
 * so that the exceptions regarding the selection from triviaQuestions can be prevented (previously
 * that was a catch-all with just an else statement). 
 * 5/28/19: Version 2.2
 * Changed returnHint to now use the accessor methods of GameLocations when returning
 * the secrets. Also modified wumpusNearPlayer to use GameLocations' methods as well.
 * 5/29/19: Version 2.3
 * Replaced askedQuestions with getQuestion and checkAnswer (gameControl may now need to keep track
 * of the number of correct answers).
*/
import java.util.*;
import java.io.*;
public class Trivia 
{
    private static Scanner input; //The Scanner used for the Trivia object
    private static File trivia; //The File that contains the trivia
    private static ArrayList<String> triviaQuestions; //The trivia questions that will be asked during the game 
    private static ArrayList<String> triviaAnswers;
    										/**
                                              * The answers to the trivia questions; will be updated alongside
                                              * the triviaQuestions ArrayList.
                                              */
    private static ArrayList<String> correctAnswers; /**
                                              * The correct answers to the trivia questions
                                              */
    
    private static ArrayList<String> triviaInformation; /**
                                         * The trivia information that will be given out for every Player turn;
                                         * unlike the triviaAnswers ArrayList this array won't remove values for
                                         * every question asked.
                                         */
    private static ArrayList<String> secrets; //The secrets that will be given to the Player when he/she buys them.
    private static int questionNum; /**The number of the question currently asked by Trivia, along
    * with its corresponding answers.
    
    /**
     * The method for the Trivia class that will handle the File processing and fill the
     * Trivia-related ArrayLists and arrays with their appropriate values, in addition to instantiating the
     * Scanner for user input.
    */
    public static void loadFiles() throws FileNotFoundException
    {
        trivia = new File("input/Trivia.txt");
        input = new Scanner(trivia);
        triviaQuestions = new ArrayList<String>();
        triviaAnswers = new ArrayList<String>();
        correctAnswers = new ArrayList<String>();
        triviaInformation = new ArrayList<String>();
        secrets = new ArrayList<String>();
        /**
         * Scans the Trivia file and determines which array to put its trivia in,
         * by analyzing certain key words and terms in each of the Strings that
         * represent them.
         */
        while(input.hasNext()) 
        {           
            String triviaLine = input.nextLine();
            if(!(triviaLine.equals("")))
            {
                if(triviaLine.substring(0, 19).equals("Ans: The answer is "))
                {
                    correctAnswers.add(triviaLine.substring(19, 20));
                }
                else if(triviaLine.substring(0, 5).equals("Ans: ")) 
                {               
                    triviaAnswers.add(triviaLine.substring(5));
                }
                else if(triviaLine.substring(0, 5).equals("Key: "))
                {
                    triviaInformation.add(triviaLine.substring(5));
                }
                else if(triviaLine.substring(0, 6).equals("Hint: "))
                {
                    secrets.add(triviaLine.substring(6));
                }
                else if(triviaLine.substring(0, 3).equals("Q. "))
                {
                    triviaQuestions.add(triviaLine.substring(3));
                }
            }
        }
    }
    
    /**
     * Gets a random question from the triviaQuestions ArrayList and adds its respective multiple-choice answers
     * (on different lines using "\n"), before returning them to GameControl.
     * 
     * @Param: Void
     * 
     * @return: String (the trivia question)
     * 
     * Please note that GameControl will now have to keep track of the number of correct answers, since Trivia
     * can no longer do that with these two methods rather than askQuestions.
     */
    public static String getQuestion()
    {
    	questionNum = (int)(Math.random() * triviaQuestions.size());
    	String[] answers = triviaAnswers.get(questionNum).split(":");           
    	String returnString = triviaQuestions.get(questionNum) + "\nPlease type either a, b, c, or d.";
    	for(int i = 0; i < answers.length; i++)
        {
          returnString += "\n" + answers[i];
        }
    	return returnString;
    }
    
    /**
     * This method takes a String as a parameter (the user input) and compares it with the correct answer as
     * specified by correctAnswers' respective answer (position of questionNum in the ArrayList).
     * 
     * @param: String answer (the user-inputted answer as a parameter given by GameControl
     * 
     * @return: boolean (whether the Player answered the question correctly)
     * 
     * Pre-Condition: getQuestion has already been called before checkAnswers.
     */
    public static boolean checkAnswer(String answer)
    {
    	String correctAnswer = correctAnswers.get(questionNum);
    	triviaQuestions.remove(questionNum);
        triviaAnswers.remove(questionNum);
        correctAnswers.remove(questionNum);
    	return answer.equalsIgnoreCase(correctAnswer);
    }
    
    /**
     * The purpose of this method is to return a hint or secret if the Player has bought one by successfully
     * answering 2 out of 3 trivia questions correctly. It will pick a random array to choose its return value
     * from (triviaInformation or secrets) and then select a random String from one of those arrays to return
     * to the Player, along with any fields from GameLocations that correspond with the secret.
     * 
     * @Params: Void
     * 
     * @Return: String (the hint that the Player would eventually get from the purchase)
     * 
     * Other methods that returnHint may call: giveTrivia (if the array chosen happens to be triviaInformation where
     * the secret that the Player will receive is a piece of trivia, returnHint will call the giveTrivia method).
     * 
     * Precondition: The Player has answered 2 trivia questions correctly to purchase the secret.
     * 
     * Please note that this method may have to be called from gameControl after askQuestions returns its
     * boolean, as a method can only return one value at a time.
    */
    public String returnHint()
    {
        int numReturn = (int)(Math.random() * 6);
        if(numReturn == 5)
        {
            return giveTrivia();
        }
        if(numReturn == 4)
        {
            return wumpusNearPlayer(numReturn);
        }
        //Work on this
        if(numReturn == 3)
        {
        	int i = 0;
        	String returnString = secrets.get(numReturn);
            int[] pitRooms = GameLocations.getPitLocations();
            for(i = 0; i < pitRooms.length-1; i++)
            {
            	 returnString += " " + pitRooms[i] + ",";
            }
            returnString += " " + pitRooms[i];
            return returnString;
        }
        //Work on this
        if(numReturn == 2)
        {
        	int i = 0;
        	String returnString = secrets.get(numReturn);
            int[] batRooms = GameLocations.getBatLocations();
            for(i = 0; i < batRooms.length-1; i++)
            {
            	 returnString += " " + batRooms[i] + ",";
            }
            returnString += " " + batRooms[i];
            return returnString;
        }
        if(numReturn == 1)
        {
            return secrets.get(numReturn) + " " + GameLocations.getWumpusLocation();
        }
        return secrets.get(numReturn) + " " + GameLocations.getPlayerLocation();
    }
    
    /**
     * The purpose of this method is to determine whether the Wumpus is near the Player, then return the correct String depending on the location of the
     * Wumpus.
     * 
     * @Param:  int num (the random number generated to pick a hint from the secrets ArrayList).
     * 
     * @Return: String (the String telling whether the Wumpus is near the Player or not).
    */
    public String wumpusNearPlayer(int num)
    {
    	//Work on this
    	int wumpLoc = GameLocations.getWumpusLocation();
    	int currLoc = GameLocations.getPlayerLocation();
        if(wumpLoc == currLoc-1 || wumpLoc == currLoc-6 || wumpLoc == currLoc+1 || 
        		wumpLoc == currLoc+5 || wumpLoc == currLoc+6 || wumpLoc == currLoc+7)
        {
            return secrets.get(num);
        }
        return secrets.get(num+1);
    }
    
    /**
     * The purpose of this method is to select a random piece of trivia from the triviaInformation array,
     *and return it (either when the Player purchases a hint/secret or moves forward).
     * 
     * @Param: Void
     * 
     * @Return: String (the piece of trivia that the Player will eventually get)
    */
    public static String giveTrivia()
    {
        int infoNum = (int)(Math.random() * triviaInformation.size());
        String trivia = triviaInformation.get(infoNum);
        return trivia;
    }
    
    //Prints a description of the Trivia object rather than its address in computer's memory.
    // public static String toString()
    // {
        // return "Trivia";
    // }
    
    //test accessor method for triviaQuestions ArrayList
    public static ArrayList<String> getQuestions()
    {
        return triviaQuestions;
    }
    
    //test accessor method for triviaAnswers ArrayList 
    public static ArrayList<String> getAnswers()
    {
        return triviaAnswers;
    }
    
    //test accessor method for triviaInformation ArrayList
    public static ArrayList<String> getInformation()
    {
        return triviaInformation;
    }
    
    //test accessor method for secrets ArrayList
    public static ArrayList<String> accessHints()
    {
        return secrets;
    }
    
    //test accessor method for correctAnswers ArrayList
    public static ArrayList<String> getCorrectAnswers()
    {
        return correctAnswers;
    }
}
