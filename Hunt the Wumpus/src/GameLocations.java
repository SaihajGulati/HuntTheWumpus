//Raj Sunku
//GameLocations object
/*
* This object keeps track of where all the hazards, the player, and the wumpus are.
* It also controls arrow shooting, controls the movement of the wumpus, and gives warnings
* 
 * Revision History:
* 3/1/2019:  empty constructor and toString method added
* 3/12/2019: Studded Methods and Fields added
*/
public class GameLocations {
       private int[] batLocations;
       private int[] pitLocations;
       private int originalPlayerLocation;
       private int playerLocation;
       private int wumpusLocation;
	   
       //This constructor does nothing yet
       public GameLocations()
       {
              
       }
       
       //The toString method returns the name of this class
       public String toString()
       {
              return "GameLocations";
       }
       
       //Moves Wumpus if player wins argument or every random amount of time
       public void moveWumpus()
       {
              
       }
       
       //Moves player to desired location if the number is an adjacent room
       public boolean movePlayer(int room)
       {
              return false;
       }
       
       //Warns the player of nearby trap or Wumpus
       public String warning()
       {
              return null;
       }
       
       //Shoots an arrow at player's desired location and returns true if Wumpus is shot
       public boolean shootArrow(int room)
       {
              return false;
       }
       
       //Changes the location of the bat if the player walks in its room, return random player index
       public int triggerBat()
       {
              return 0;
       }
       
       //Changes the location of the player when if the player encounters a botomless pit or triggers endgame
       public void triggerPit()
       {
              
       }
       
       //All "get" methods are accessors
       public int getPlayerLocation()
       {
              return 0;
       }
       
       public int getWumpusLocation()
       {
              return 0;
       }
       
       public int[] getBatLocations()
       {
              return null;
       }
       
       public int[] getPitLocations()
       {
              return null;
       }
}