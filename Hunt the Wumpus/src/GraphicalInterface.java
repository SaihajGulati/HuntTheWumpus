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

public class GraphicalInterface {

int BAT;
int HOLE; 
int WUMPUS;

public GraphicalInterface(int bat, int hole, int wumpus)
{
 WUMPUS = wumpus;
 HOLE = hole;
 BAT = bat;
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

/**
*Display on screen what "danger" is near (Wumpus, bat, hole)
* Takes and array of hazards displaying what hazards are nearby
* Prints out to user what hazards are nearby
*/
public void inDanger(int [] hazards)
{
 String batString = "THERE ARE BATS NEARBY";
 String wumpusString = "I SMELL A WUMPUS";
 String holeString = "I FEEL A DRAFT";
 
 for(int hazard : hazards)
 {
  if(hazard == null)
  // do nothing
  
  else if(hazard == BAT)
   System.out.println(batString);
   
  else if(hazard == HOLE)
   System.out.println(holeString);
   
  else if(hazard == WUMPUS)
   System.out.println(wumpusString);
 }
}

//Returns direction that arrow is shot
public int shootArrow (Cave cave)
{
 return -1;
}

// returns if player has enough money to buy the item they want, I will edit this with the implementation
public boolean buyItem(int item, int playerBalance) 
{
 return false;
}

// Displays the outcome of the game
public void Outcome(boolean score)
{
}

// Displays high score
public void displayHighscore(int score)
{
}


}
