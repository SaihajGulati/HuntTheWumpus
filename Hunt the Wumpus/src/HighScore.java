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
*/
import java.util.*;
import java.io.*;
public class HighScore 
{
        private String fileName;
        private File scoreBoard; //The File that will be used to load the high scores
        private ArrayList<Integer> highScores; /**
                                                * An ArrayList that will represent the high scores after they have
                                                * been loaded into the game; used either when displaying the high scores
                                                * or updating them after a game (when the Player's score qualifies).
                                                */
    
    /**
     * The constructor for the HighScore object that will handle the File processing and fill the
     * HighScores ArrayList with its respective scores.
    */
    public HighScore(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        scoreBoard = new File(fileName); 
        Scanner input = new Scanner(scoreBoard);
        highScores = new ArrayList<Integer>();
        while(input.hasNext()) 
        {
            highScores.add(input.nextInt());
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
    public void resetScores()
    {
        //Again, must learn how to update a file from my code
        for(int i = 0; i < highScores.size(); i++)
        {
            highScores.set(i, 0);
        }
    }
    
    /**
     * The purpose of this method is to take the ArrayList of highScores (the loading from a file will be handled
     * in the constructor) and send them to the GraphicalInterface object so they can be rendered
     * properly when the leaderboard is supposed to be displayed.
     * 
     * @Param: Void (This method doesn't need to take any parameters as it already has the high scores in its fields)
     * 
     * @Return: Void (No need to pass the high scores to gameControl when the scoreboard is being printed). 
     */
    public void  viewScores() 
    {
        
    }
    
    /**
     * The purpose of this method is to take the Player's score after he/she finishes a game (either by dying
     * or shooting the Wumpus with an arrow), and update the scoreboard if the total score is able to qualify
     * for it.
     * 
     * @Param: int totalScore (the final score of the Player, calculated in the Player object) 
     * 
     * @Return: Void
     * 
     * Other methods that updateScoreBoard may call: viewScores (the high scores will be displayed after the
     * Player finishes the game). 
     */
    public void updateScoreBoard(int totalScore) throws FileNotFoundException
    {
        PrintStream output = new PrintStream(new File(fileName));
        //PrintStream output = new PrintStream(new File("testOutput.txt"));
        if(highScores.size() <= 10)
        {            
            for (int i = 0; i < highScores.size(); i++)
            { 
                if(totalScore >= highScores.get(i) && totalScore <= highScores.get(i-1))
                {
                    highScores.add(i, totalScore);
                    i++;
                }
            }
        }
        if(highScores.size() > 10)
        {
            highScores.remove(highScores.size()-1);
        }
        for(int score: highScores)
        {
            output.println(score);
        }
        //GraphicalInterface.displayHighscore(highScores);
    }
    
    //Prints a description of the HighScore object rather than its address in computer's memory.
    public String toString()
    {
        return "HighScore";
    }
    
    //Test method
    public ArrayList<Integer> getHighScores()
    {
        return highScores;
    }
}
