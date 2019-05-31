/** Name: Brian Yang
 * Class: APCSA Period 3
 * Teacher: Mrs. Kankelborg
 * The HighScore object manages the high scores by saving new scores on a leaderboard if they qualify for it,
 * and displaying the high scores when necessary (either when the Player wants to see the scores, or when the
 * game has ended, either by dying or defeating the Wumpus).
 * 
 * Test comment
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
 * 4/24/19: Version 1.8
 * Added a names array (String type) and the code to update it alongside highScores,
 * including the retrieval of the names and their output to/from a file
 * 4/30/19: Version 1.9
 * Added a caves array (String type) and the code to update it alongside highScores 
 * and names, including their retrieval and their output to/from a file.
 * 5/15/19: Version 2.0
 * Changed the constructor to a static method called loadFiles.
 * 5/30/19: Version 2.1
 * Changed return type of updateScoreBoard from an ArrayList<Integer> to a boolean.
*/
import java.util.*;
import java.io.*;
public class HighScore 
{
    private static Scanner input;
    private static File scoreBoard; //The File that will be used to load the high scores
    private static ArrayList<Integer> highScores; /**
                                            * An ArrayList that will represent the high scores after they have
                                            * been loaded into the game; used either when displaying the high scores
                                            * or updating them after a game (when the Player's score qualifies).
                                            */
    private static ArrayList<String> names; /**
     * An ArrayList that will represent the names after they have
     * been loaded into the game; used either when displaying the names
     * or updating them after a game (when the Player's score qualifies).
     */
    private static ArrayList<Integer> caves; /**
     * An ArrayList that will represent the caves after they have
     * been loaded into the game; used either when displaying the cave that each Player played in
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
     * The method for the HighScore class that will handle the File processing and fill the
     * highScores ArrayList with its respective scores, the names ArrayList with its respective
     * names, and the caves ArrayList with its respective cave names.
     * 
     * @Param: String filename (The name of the file that will be processed and used by the HighScore object)
    */
    public static void loadFiles() throws FileNotFoundException
    {
        scoreBoard = new File("input/HighScores.txt");    
        //Scanner input = new Scanner(scoreBoard,"utf-8");
        input = new Scanner(scoreBoard);
        String userName = "";
        highScores = new ArrayList<Integer>();
        names = new ArrayList<String>();
        caves = new ArrayList<Integer>();
        //Adds high scores to the highScores ArrayList upon being scanned from the file, along with player names
        //to the names ArrayList and cave names to the caves ArrayList. 
        while(input.hasNext()) 
        {
            String inputLine = input.nextLine();
            System.out.println(inputLine);
            if(!(inputLine.equals("")))
            {
                if(inputLine.substring(0, 5).equals("Name "))
                {
                    names.add(inputLine.substring(5));
                }
                else if(inputLine.substring(0, 6).equals("Score "))
                {
                    highScores.add(Integer.valueOf(inputLine.substring(6)));
                }
                else if(inputLine.substring(0, 5).equals("Cave "))
                {
                	caves.add(Integer.valueOf(inputLine.substring(5)));
                }
                else
                {
                    System.out.println(inputLine);
                }
            }
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
            //Add code that resets names to its default values;
        }
        for(int i = 0; i < names.size(); i++)
        {
            
            //Add code that resets names to its default values;
        }
        for(int i = 0; i < caves.size(); i++)
        {
            
            //Add code that resets caves to its default values;
        }
        updateFile();
    }
        
    /**
     * The purpose of this method is to take the Player's score, name, and cave played in after he/she finishes #
     * a game (either by dying or shooting the Wumpus with an arrow), and update the scoreboard if the total 
     * score is able to qualify for it.
     * 
     * @Param: int totalScore (the final score of the Player, calculated in the Player object), String name (the
     * name of the Player), String caveName (The name of the cave that the Player played in) 
     * 
     * @Return: boolean (whether the scoreBoard has been updated or not)
     * 
     * Pre-Condition: The Player will always give a totalScore, name, and caveName when this method is called.
     * 
     * Post-Condition: The ArrayLists will always be updated simultaneously so no IndexOutOfBounds exceptions
     * would show up under normal circumstances.
     */
    public static boolean updateScoreBoard(int totalScore, String name, int caveName) throws FileNotFoundException
    {
    	boolean isScoreAdded = false;
        //PrintStream output = new PrintStream(new File("testOutput.txt"));
        if(highScores.size() == 0 || totalScore >= highScores.get(0))
        {
            highScores.add(0, totalScore);
            names.add(0, name);
            caves.add(0, caveName);
            isScoreAdded = true;
        }
        else
        {
            for (int i = 1; i < highScores.size(); i++)
            { 
                if(totalScore >= highScores.get(i) && totalScore <= highScores.get(i-1))
                {
                    highScores.add(i, totalScore);
                    names.add(i, name);
                    caves.add(i, caveName);
                    i++;
                    isScoreAdded = true;
                }
            }
        }
        /**
         * As long as highScores, names, and caves have more than 10 elements, the last element 
         * (1 less than the ArrayLists'
         * lengths) will be removed from the list.
         */
        while (highScores.size() > 10)
        {
            highScores.remove(highScores.size()-1);
            names.remove(names.size()-1);
            caves.remove(caves.size()-1);
        }
        updateFile();
        return isScoreAdded;
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
        PrintStream output = new PrintStream(new File("HighScores.txt"));
        /**
         * For each score in the highScores ArrayList, the loop will output the score with its
         * corresponding player name and cave name to a new file
         * with the same fileName as the old file, that replaces the old file in its location on the
         * computer
         */
        for(int score: highScores)
        {
            output.println("Score " + score);
        }
        for(String name: names)
        {
        	output.println("Name " + name);
        }        
        for(int caveNum: caves)
        {
        	output.println("Cave " + caveNum);
        }
    }
    //asdf
   /**
     * The purpose of this method is to take the ArrayLists of highScores, names, and caves (the loading 
     * from a file will be handled in the constructor) and return them to the GraphicalInterface object 
     * (which will be calling this method) so they can be rendered
     * properly when the leaderboard is supposed to be displayed.
     * 
     * @Param: Void (This method doesn't need to take any parameters as it already has the high score data 
     * in its fields)
     * 
     * pushing not working
     * 
     * 
     * @Return: ArrayList<Integer> (the GraphicalInterface needs to be able to access the high score data 
     * from the highScores ArrayList in order to display them).
     */
    public static ArrayList<String> getHighScores()
    {
        ArrayList<String> returnString = new ArrayList<String>();
        for(int i = 0; i < highScores.size(); i++)
        {
        	returnString.add(i+1 + ". Cave " + caves.get(i) + "; " + names.get(i) + "; " + highScores.get(i));
        }
        return returnString;
    }
}