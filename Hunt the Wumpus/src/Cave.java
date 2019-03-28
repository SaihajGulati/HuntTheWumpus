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
	private int row;
	private int column;
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
		column = 0;
		row = 0;
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
	
	/*this method will sort out the adjacent rooms next to the room the player is in (in progress)
	 */
	public int[] adjacentRooms(int roomNum)
	{
		int[] adjRooms = new int[2];
		if(column == 0)
		{
			adjRooms[0] = row * caveType[0].length + column + 1;
		}
		else
		{
			adjRooms[0] = roomNum - 1;
		}
		
		if(column == caveType[0].length - 1)
		{
			adjRooms[1] = row * caveType[0].length + column + 1;
		}
		else
		{
			adjRooms[1] = roomNum + 1;
		}
		return adjRooms;
	}
}

