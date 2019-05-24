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
 */                                      

import java.io.*;
import java.util.*;
public class Cave
{
	int[][] caveMap;
	public Cave(int cave) throws FileNotFoundException
	{
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
	public int[][] getCaveMap()
	{
	    return caveMap;
	}
	public int[] tunnels(int location)
	{
		int[] temp = new int[findIndex(caveMap[location-1], 0)];
		for (int i= 0; i < temp.length; i++)
		{
			temp[i] = caveMap[location-1][i];
		}
		return temp;
	}
	
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
	public int[] adjacentRooms(int location)
	{
		int[] temp = new int[caveMap[location-1].length-1];
		for(int i = 0; i < Arrays.asList(caveMap[location-1]).indexOf(0); i++)
		{
			temp[i]=caveMap[location-1][i];
		}
		for(int i = findIndex(caveMap[location-1], 0) + 1; i < caveMap[location-1].length; i++)
		{
			temp[i-1]=caveMap[location-1][i];
		}
			
		return temp;
	}
	
}