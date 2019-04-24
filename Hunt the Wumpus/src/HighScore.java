/** Name: Brian Yang
 * Class: APCSA Period 3
 * Teacher: Mrs. Kankelborg
 * The HighScore object manages the high scores by saving new scores on a leaderboard if they qualify for it,
 * and displaying the high scores when necessary (either when the Player wants to see the scores, or when the
 * game has ended, either by dying or defeating the Wumpus).
 * 
 * 3/4/19: Version 1.0
 * Added the class header, constructor, and toString method.
 * 3/7/19: Version 1.1
 * Added a stubbed class for the HighScore.
 * 3/15/19: Version 1.2
 * Implemented the constructors, resetScore, and highScore
 * 3/21/19: Version 1.3
 * Modified updateScoreBoard and made the method static
 * 3/22/19: Version 1.4
 * Added test case in updateScoreBoard if the Player gets a higher
 * score than any of the pre-existing scores on the scoreboard.
 * 3/25/19: Version 1.5
 * Removed the stubbed class viewScores, as my getHighScores method
 * already does the same intended function as viewScores.
 * 4/03/19: Version 1.6
 * Added comments to the while and for/for-each loops explaining their purposes.
 * 4/15/19: Version 1.7
 * Added a comment for the String fileName
*/
import java.util.*;
import java.io.*;
public class HighScore 
{
        private static String fileName; //The name of the File used to load the scores
        private static File scoreBoard; //The File that will be used to load the high scores
        private static ArrayList<Integer> highScores; /**
                                                * An ArrayList that will represent the high scores after they have
                                                * been loaded into the game; used either when displaying the high scores
                                                * or updating them after a game (when the Player's score qualifies).
                                                */
        private static ArrayList<String> names; /**
         * An ArrayList that will represent the high scores after they have
         * been loaded into the game; used either when displaying the high scores
         * or updating them after a game (when the Player's score qualifies).
         */
    
        /*
         * How to cast a String to an int:
         * int bob = Integer.valueOf("30");
         */
        /*
         * How to split a String (returns a String array)
         * "Saihaj:42".split(":");
         */
        
    /**
     * The constructor for the HighScore object that will handle the File processing and fill the
     * HighScores ArrayList with its respective scores.
    */
    public HighScore(String filename) throws FileNotFoundException
    {
    	String[] temp;
        this.fileName = filename;
        scoreBoard = new File(fileName); 
        Scanner input = new Scanner(scoreBoard);
        String userName = "";
        highScores = new ArrayList<Integer>();
        names = new ArrayList<String>();
        String inputLine = "";
        //Adds high scores to the highScores ArrayList upon being scanned from the file
        while(input.hasNext()) 
        {
        	inputLine = input.nextLine();
        	temp = inputLine.split(":");
            //highScores.add(input.nextInt());
        }
    }
    
    /**
     * The purpose of this method is to reset the HighScore data to its default state when the Player
     * decides to reset the game and start a new one, with the names of
     * the project's participants, zero scores, and arbitrary cave names.
     * 
     * @Param: Void (I don't think any parameters are needed when resetting the scoreboard).
     * @Return: Void
     * 
     * Pre-Condition: The leaderboard isn't in its default state when the Player chooses to reset the game. 
     */
    public static void resetScores() throws FileNotFoundException
    {
        //Resets all high scores in highScores to 0, before updating their file through updateFile.
        for(int i = 0; i < highScores.size(); i++)
        {
            highScores.set(i, 0);
        }
        updateFile();
    }
        
    /**
     * The purpose of this method is to take the Player's score after he/she finishes a game (either by dying
     * or shooting the Wumpus with an arrow), and update the scoreboard if the total score is able to qualify
     * for it.
     * 
     * @Param: int totalScore (the final score of the Player, calculated in the Player object) 
     * 
     * @Return: Void
     */
    public static ArrayList<Integer> updateScoreBoard(int totalScore, String name) throws FileNotFoundException
    {
        //PrintStream output = new PrintStream(new File("testOutput.txt"));
        if(totalScore >= highScores.get(0))
        {
            highScores.add(0, totalScore);
            names.add(0, name);
        }
        else
        {
            for (int i = 1; i < highScores.size(); i++)
            { 
                if(totalScore >= highScores.get(i) && totalScore <= highScores.get(i-1))
                {
                    highScores.add(i, totalScore);
                    names.add(i, name);
                    i++;
                }
            }
        }
        /**
         * As long as highScores has more than 10 elements, the last element (1 less than the ArrayList's
         * length) will be removed from the list.
         */
        while (highScores.size() > 10)
        {
            highScores.remove(highScores.size()-1);
        }
        updateFile();
        return highScores;
    }
    
    /**
     * Updates the File after the scores are reset or updated.
     * 
     * @Param: Void (no need to accept parameters from other classes)
     * 
     * @Return: Void (no need to return stuff)
     */
    public static void updateFile() throws FileNotFoundException
    {
        PrintStream output = new PrintStream(new File(fileName));
        /**
         * For each score in the highScores ArrayList, the loop will output the score to a new file
         * with the same fileName as the old file, that replaces the old file in its location on the
         * computer
         */
        for(int score: highScores)
        {
            output.println(score);
        }
    }
    
   /**
     * The purpose of this method is to take the ArrayList of highScores (the loading from a file will be handled
     * in the constructor) and return them to the GraphicalInterface object (which will be calling this method) so they can be rendered
     * properly when the leaderboard is supposed to be displayed.
     * 
     * @Param: Void (This method doesn't need to take any parameters as it already has the high scores in its fields)
     * 
     * @Return: ArrayList<Integer> (the GraphicalInterface needs to be able to access the high scores from the highScores ArrayList in order to display them).
     */
    public static ArrayList<Integer> getHighScores()
    {
        return highScores;
    }
}