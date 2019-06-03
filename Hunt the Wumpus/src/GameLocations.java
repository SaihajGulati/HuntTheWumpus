//Raj Sunku
//GameLocations object
/*
 * This object keeps track of where all the hazards, the player, and the wumpus are.
 * It also controls arrow shooting, controls the movement of the wumpus, and gives warnings
 * 
 * Revision History:
 * 3/01/2019: empty constructor and toString method added
 * 3/12/2019: Studded Methods and Fields added
 * 3/19/2019: warning, movePlayer, and constructor implemented
 * 3/26/2019: triggerBat, triggerPit, and shootArrow implemented
 * 4/03/2019: moveWumpus implemented, so class is finished
 */
public class GameLocations {
       private static int[] batLocations;
       private static int[] pitLocations;
       private static int originalPlayerLocation;
       private static int playerLocation;
       private static int wumpusLocation;
       private static Cave cave;
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
              wumpusLocation=(int)(Math.random()*30+1);//;
              while(wumpusLocation==playerLocation)
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
        * Moves Wumpus 2-4 rooms away, if beaten in fight with player
        */
       public static void moveWumpus()
       {
    	  int[] x = {0,0,0,0,0,0};
    	  for(int i=0;i<(int)(Math.random()*3+2);i++)
    	  {
    		  x[i]=wumpusLocation;
    		  boolean z = true;
    		  while(z)
    		  {
    			  boolean y = false;
	    		  int moveLocation=cave.tunnels(wumpusLocation)[(int)(Math.random()*cave.tunnels(wumpusLocation).length)];
	    		  wumpusLocation=moveLocation;
	    		  for(int j = 0;j<6;j++)
	    		  {
	    			  if(wumpusLocation==x[i])
	    				  y=true;
	    			  z=y;
	    				  
	    		  }
    		  }
    	  }
       }
       
       /*
        * Moves the wumpus to the next room in a random direction
        */
       public static void moveWumpusOne()
       {
    	   int moveLocation=cave.tunnels(wumpusLocation)[(int)(Math.random()*cave.tunnels(wumpusLocation).length)];
 		   wumpusLocation=moveLocation;
       }
       /* 
        * Moves Player to room if possible
        * @param room is the desired location to move to
        * @return a boolean value indicating if it was possible to move to room
        */
       public static boolean movePlayer(int room)
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
       public static int[] warning()
       {
    	   int[] warnings= {0,0,0};
    	   int[] sides = cave.tunnels(getPlayerLocation());
    	   for(int i=0; i<sides.length;i++)
    	   {
    		   if(batLocations[0]==sides[i]||batLocations[1]==sides[i])
    			   warnings[0]++;
    		   if(pitLocations[0]==sides[i]||pitLocations[1]==sides[i])
    			   warnings[1]++;
    		   if(wumpusLocation==sides[i])
    			   warnings[2]++;
    	   }
              return warnings;
       }
       
       /*
        * @param room is the desired location to shoot the arrow
        * @return true if the Wumpus if shot, false if not
        */
       public static boolean shootArrow(int room)
       {
    	   boolean properRoom=false;
    	   int[] x=cave.tunnels(playerLocation);
    	   for(int i=0;i<x.length;i++)
    	   {
    		   if(x[i]==room)
    			   properRoom=true;
    	   }
    	   if(room==wumpusLocation&&properRoom)
    		  return true;
    	   else
    	   {
    		   if (Math.random() < 0.75)
    		   {
    			   moveWumpusOne();
    		   }
              return false;
    	   }
       }
       
       /*
        * Changes the location of the bat if the player walks in its room
        * @return the position the player moved to
        */
       public static int triggerBat()
       {
    	   int i;
    	   int x = playerLocation;
    	   if(playerLocation==batLocations[0])
    		   i=0;
    	   else
    		   i=1;
    	   while(batLocations[i]==pitLocations[0]||batLocations[i]==pitLocations[1]||
    			   batLocations[i]==batLocations[(i+1)%2]||batLocations[i]==originalPlayerLocation||
    			   batLocations[i]==playerLocation||batLocations[i]==x)
           {
    		   batLocations[i]=(int)(Math.random()*30+1);
           }
    	   while(playerLocation==batLocations[0]||playerLocation==batLocations[1]||playerLocation==x)
           {
         	  playerLocation=(int)(Math.random()*30+1);
           }
              return playerLocation;
       }
       
       /*
        * Changes the location of the player when if the player encounters a botomless pit or triggers endgame
        * @param answeredQuestions is a boolean indicating if the Player properly answered the questions
        */
       public static void triggerPit()
       {
    	   playerLocation=originalPlayerLocation;
       }
       
       /*
        * All "get" methods are accessors
        */
       public static int getPlayerLocation()
       {
              return playerLocation;
       }
       
       public static int getWumpusLocation()
       {
              return wumpusLocation;
       }
       
       public static int[] getBatLocations()
       {
              return batLocations;
       }
       
       public static int[] getPitLocations()
       {
              return pitLocations;
       }
}