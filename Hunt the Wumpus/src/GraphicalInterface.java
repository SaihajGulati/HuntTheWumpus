/* Daniel Popa
 * Team: Artesian Code
 *Graphical interface displays what the 
 *user will see and interact with
 *First Created 3/4/2019
 *
 * Date of revision  |  Revision made
 *     3/12/2019      |  Added method headers
 *
 */
import java.util.*;
public class GraphicalInterface {

public GraphicalInterface()
{
 
}

public String toString()
{
 return "Graphical Interface";
}

// Display main menu, return if player wants to start game
public boolean mainMenu()
{
 return true;
}

// return room number player wants to move in
public int playerMoves(Cave cave)
{
 return -1;
}

// return which answer player thought correct (I want to change this, will talk in class)
public  int triviaResponse(Trivia trivia)
{
 return -1;
}

// Displayer player Items
public void displayItems(Player player)
{ 
}

// Display on screen what "danger" is near (Wumpus, bat, hole)
public void inDanger(int danger)
{
}

//Returns direction that arrow is shot
public int shootArrow (Cave cave)
{
 return -1;
}

// returns if player has enough money to buy the item they want
public boolean buyItem(String item) 
{
 return false;
}

// Displays the outcome of the game
public void Outcome(boolean score)
{
}

// Displays high score
public void displayHighscore(int highScore)
{
}


}
