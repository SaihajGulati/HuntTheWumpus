/*
Class Header:
Hans Koduri
Class: Cave
Description: The Cave is the main setting for the game, and controls many aspects including
keeping track of which rooms the player moves through, the connections to adjacent rooms 
through storage of internal data, where the player enters when the game starts, and appropriately
displaying the content of any necessary methods for other objects in the game. 

Revision History Table:
Date:        Version:    Comments:
3/2/2019     1            Assignment 1: empty constructor and toString method  
3/13/2019    2            Assignment 2: stubbed methods
3/19/2019    3            Assignment 3: Implementation of rooms, cave map, along with row and column setup.
3/27/2019    4            Assignment 4: Implementation of room to return adjacent rooms, and a method
                                        to return whether the room is an edge room or inner room
                                        within the cave.
 */                                      

import java.io.*;
import java.util.*;
public class Cave
{
	private int roomNum;
	private boolean hasHazard;
	private boolean useArrow;
	private Scanner x;
	private String[][] caveType;
	
	/* This is the constructor method which initializes the fields for the current room
	 *  and the array for caveType, as well as booleans if the room has a hazard or if the
	 *  player uses an arrow.
	 * 
	 */
	public Cave(String[][] type)
	{
		caveType = type;
		roomNum = (int)(Math.random()*30+1);
		hasHazard = false;
		useArrow = false;
	}
	
	/*
	 * returns "Cave" when this object is printed
	 */
	public String toString()
	{
		return "Cave";
	}
	
	
	
	/* This method opens and reads a file which gives each room along with its adjacent rooms
	 * and wall rooms, or rooms that cannot be entered from the current room. Currently in progress.
	 * 
	 */
	public void openCaveFile() throws FileNotFoundException
	{
		x= new Scanner(new File("Cave1.txt"));
	}
	
	public void readCaveFile() throws FileNotFoundException
	{
		while(x.hasNext())
		{
			int room = x.nextInt();
			int tunnel = x.nextInt();
			int adjRoom = x.nextInt();
		}
	}
	
	public void closeCaveFile()
	{
		x.close();
	}
	
	/*this method will return an array with the locations of tunnels in the room that the player is currently in.
	 * (In progress)
	 */
	public int[] tunnels(int roomNum)
	{
		return null;
	}
	
	
	//Assingment 4 methods
	/*this method will sort out the adjacent rooms next to the room the player is in (in progress)
	 */
	public int[] adjacentRooms(int roomNum)
	{
		int[] adjRooms = new int[2];
		if(roomColumn == 0)
		{
			adjRooms[0] = row * caveType[0].length + column + 1;
		}
		else
		{
			adjRooms[0] = roomNum - 1;
		}
		
		if(roomColumn == caveType[0].length - 1)
		{
			adjRooms[1] = row * caveType[0].length + column + 1;
		}
		else
		{
			adjRooms[1] = roomNum + 1;
		}
		return adjRooms;
	}
	
	public int roomLayout()
	{
		int[] edgeRooms = new int{1, 2, 3, 4, 5, 6, 12, 18, 24, 30, 29, 28, 27,
			26, 25, 19, 13, 7};
		int[] innerRooms = new int{8, 9, 10, 11, 17, 23, 22, 21, 20, 14, 8, 15, 21, 10, 16, 22};
		for(int i = 0; i < edgeRooms.length; i++)
		{
			if(edgeRooms[i] == roomNum)
			{
				return roomNum;
				wrapAround(int roomNum);
			}
			else
			{
				return 0;
				adjacentRooms();
			}
		}
	}

	
	/*this method sorts out the configuration of the rooms in each row, according to the current
	 * room number the player is in using the 2D array.
	 */
	public int roomRow(int roomNum)
	{
		int row = roomNum / caveType[0].length;
		if (roomNum % caveType[0].length == 0) 
		{
			return row - 1;
		}
		return row;
	}
	
	/*this method sorts out the configuration of the rooms in each column, according to the 
	 * current room the player is in using the 2D array.
	 * 
	 */
	public int roomColumn(int roomNum)
	{
		int column = roomNum - 1 -(roomRow(roomNum) * caveType[0].length);
		return column;
	}
	
	public int wrapAround()
	{
		return 0;
	}
	/*This method will keep track of when the player changes rooms and the new room number
	according to the layout of the cave, using the room number and then the number of 
	tunnels in the new room.*/
	
	public int changeRooms(int roomNum, int numTunnels)
	{
		return 0;
	}
	
	/*This method will manage the shooting of arrows between adjacent rooms, making sure the 
	 player has enough arrows to shoot first, returning true if they do, false otherwise.*/
	public boolean shootArrows(int numArrows)
	{
		return false;
	}
	
	/*This method will be for the very start of the game when the player is choosing which cave
	 map they would like to use in the game, assigning each cave a number with the parameter
	 cave type, and returning true if they choose a specific cave, or false if they 
	 want to quit or go with a random cave.
	 */
	public boolean typeOfCave(int caveType)
	{
		return false;
	}
	
	/*This method will return the location of the Wumpus encounter, which will likely spring
	 from another method that returned true if the Wumpus was in the room. This method uses
	 the room number according to the layout of the cave for the exact location.
	 */
	
	public int encounterWumpus(int roomNum)
	{
		return 0;
	}
	
	/*This method will reveal in every room if there is a hazard or not, according to the room
	 number and the boolean hasHazard, which will return true if the room does have bats
	 or the bottomless pit, and false otherwise.
	 */
	
	public boolean encounterHazard(int roomNum, boolean hasHazard)
	{
		return false;
	}
	
	/*This method applies strictly to the hazards, and will return the location, or room number
	 where the player was moved to by the bats or in the case of the bottomless pit, it will 
	 return them to the start. 
	 */
	
	public int playerRelocation(int roomNum, int caveType)
	{
		return 0;
	}
	
	
}