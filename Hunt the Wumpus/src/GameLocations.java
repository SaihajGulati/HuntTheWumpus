//Raj Sunku
//GameLocations object
/*
* This object keeps track of where all the hazards, the player, and the wumpus are.
* It also controls arrow shooting, controls the movement of the wumpus, and gives warnings
* 
* Revision History:
* 3/1/2019:  empty constructor and toString method added
* 3/12/2019: Studded Methods and Fields added
* 3/19/2019: warning, movePlayer, and constructor implemented
*/
public class GameLocations {
       private int[] batLocations;
       private int[] pitLocations;
       private int originalPlayerLocation;
       private int playerLocation;
       private int wumpusLocation;
       private Cave cave;
       
       /* 
        * This constructor sets random values for all of the locations
        * @param c is the cave that helps find adjacent rooms for the player  
        */
       public GameLocations(Cave c)
       {
    	   	  batLocations=new int[2];
    	   	  pitLocations=new int[2];
              batLocations[0]=(int)(Math.random()*30+1);
              batLocations[1]=(int)(Math.random()*30+1);
              //All while loops are there to make sure that nothing is in the same location
              while(batLocations[1]==batLocations[0])
              {
            	  batLocations[1]=(int)(Math.random()*30+1);
              }
              pitLocations[0]=(int)(Math.random()*30+1);
              while(pitLocations[0]==batLocations[1]||pitLocations[0]==batLocations[0])
              {
            	  pitLocations[0]=(int)(Math.random()*30+1);
              }
              pitLocations[1]=(int)(Math.random()*30+1);
              while(pitLocations[1]==pitLocations[0]||pitLocations[1]==batLocations[0]||pitLocations[1]==batLocations[1])
              {
            	  pitLocations[1]=(int)(Math.random()*30+1);
              }
              originalPlayerLocation=(int)(Math.random()*30+1);
              playerLocation=originalPlayerLocation;
              while(originalPlayerLocation==pitLocations[0]||originalPlayerLocation==pitLocations[1]||
            		  originalPlayerLocation==batLocations[0]||originalPlayerLocation==batLocations[1])
              {
            	  originalPlayerLocation=(int)(Math.random()*30+1);
            	  playerLocation=originalPlayerLocation;
              }
              wumpusLocation=(int)(Math.random()*30+1);
              while(wumpusLocation==pitLocations[0]||wumpusLocation==pitLocations[1]||
            		  wumpusLocation==batLocations[0]||wumpusLocation==batLocations[1]||
            		  wumpusLocation==playerLocation)
              {
            	  wumpusLocation=(int)(Math.random()*30+1);
              }
              cave = c;
       }
       
       /*
        * @return name of this class
        */
       public String toString()
       {
              return "GameLocations";
       }
       
       /*
        * Moves Wumpus if player wins argument or every random amount of time
        */
       public void moveWumpus()
       {
              
       }
       
       /*
        * Moves Player to room if possible
        * @param room is the desired location to move to
        * @return a boolean value indicating if it was possible to move to room
        */
       public boolean movePlayer(int room)
       {
    	   int[] playerTunnels = cave.tunnels(playerLocation);
    	   for(int i=0; i<playerTunnels.length;i++)
    	   {
    		   if(playerTunnels[i]==room)
    		   {
    			   playerLocation=room;
    			   return true;
    		   }
    	   }
              return false;
       }
       
       /*
        * Warns the player of nearby trap or Wumpus
        * @return an array with the number of each hazard/wumpus in adjacent rooms
        * index 0: # of Bats, index 1: # of Pits, index 2: # of Wumpus
        */
       public int[] warning()
       {
    	   int[] warnings= {0,0,0};
    	   int[] sides = cave.adjacentRooms(getPlayerLocation());
    	   for(int i=0; i<sides.length;i++)
    	   {
    		   if(batLocations[0]==sides[i]||batLocations[1]==sides[i])
    			   warnings[0]++;
    		   else if(pitLocations[0]==sides[i]||pitLocations[1]==sides[i])
    			   warnings[1]++;
    		   else if(wumpusLocation==sides[i])
    			   warnings[2]++;
    	   }
              return warnings;
       }
       
       /*
        * @param room is the desired location to shoot the arrow
        * @return true if the Wumpus if shot, false if not
        */
       public boolean shootArrow(int room)
       {
    	   moveWumpus();
              return false;
       }
       
       /*
        * Changes the location of the bat if the player walks in its room
        * @return the position the player moved to
        */
       public int triggerBat()
       {
              return 0;
       }
       
       /*
        * Changes the location of the player when if the player encounters a botomless pit or triggers endgame
        */
       public void triggerPit()
       {
              
       }
       
       /*
        * All "get" methods are accessors
        */
       public int getPlayerLocation()
       {
              return playerLocation;
       }
       
       public int getWumpusLocation()
       {
              return wumpusLocation;
       }
       
       public int[] getBatLocations()
       {
              return batLocations;
       }
       
       public int[] getPitLocations()
       {
              return pitLocations;
       }
}