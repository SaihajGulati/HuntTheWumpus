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
5/23/2019    5            Fully implemented methods to read and return cave information.
5/25/19      6 			  Added findIndex Method
 */                                      

import java.io.*;
import java.util.*;
public class Cave
{
	int[][] caveMap;
	//af
	public Cave(int cave)
	{
		try {
			File file = new File("input/cave" + cave + ".txt");
			Scanner layout = new Scanner(file);
			caveMap = new int[30][7];
			String[][] temp= new String[30][7];
			for (int i = 0; i < 30; i++)
			{
				temp[i] = layout.nextLine().split(" ");
				for(int j=0;j<7;j++)
				{
					caveMap[i][j]=Integer.parseInt(temp[i][j]);
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Error Occured : Cave File Not Found");
			GraphicalInterface.Error();
		}
		

	}
	
	/**
	 * @return caveMap
	 */
	public int[][] getCaveMap()
	{
		if(caveMap.length == 0 || caveMap[0].length == 0) {
			System.out.println("Error Occured : getCaveMap not instantiated");
			GraphicalInterface.Error();
		}
		return caveMap;
	    
	}
	//okay
	
	/**
	 * 
	 * @param location the player is
	 * @return the tunnels/rooms the player can move to as an array
	 */
	public int[] tunnels(int location)
	{
		int[] temp = new int[3];
		if (location > 0)
		{
			for (int i= 0; i < temp.length; i++)
			{
				temp[i] = caveMap[location-1][i];
			}
		}
		return temp;
	}
	
	
	/**
	 * @param array which is the array to search in
	 * @param find which is the value to find
	 * @return index of first value in the array, -1 otherwise
	 */
	
	public int findIndex(int[] array, int find)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] == find)
			{
				return i;
			}
		}
		return -1;
	}
	
	/*
	/**
	 * @param location the player is
	 * @return the rooms adjacent to the room the player is in currently as an array
	public int[] adjacentRooms(int location)
	{
		int[] temp = new int[caveMap[location-1].length-1];
		for(int i = 0; i < findIndex(caveMap[location-1], 0); i++)
		{
			temp[i]=caveMap[location-1][i];
		}
		for(int i = findIndex(caveMap[location-1], 0) + 1; i < caveMap[location-1].length; i++)
		{
			temp[i-1]=caveMap[location-1][i];
		}
			
		return temp;
	}
	*/
	
	
}