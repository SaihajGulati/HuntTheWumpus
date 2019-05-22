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

import java.io.FileNotFoundException;
public class Cave
{
	public Rooms[] readFile(int cave) throws FileNotFoundException
	{
		File file = new File(".//input//cave1.txt");
		Rooms[] roomLayout = new Rooms[30];
		Scanner layout = new Scanner(file);
		int tunnelsWalls = 0;
		int currentRoom = layout.nextInt();
		ArrayList<Integer> tunnels = new ArrayList<Integer>(0);
		ArrayList<Integer> walls = new ArrayList<Integer>(0);
		
		while(layout.hasNextInt())
		{
			tunnelsWalls = layout.nextInt();
			while(tunnelsWalls > 0)
			{
				tunnels.add(tunnelsWalls);
				tunnelsWalls = layout.nextInt();
			}
			tunnelsWalls = layout.nextInt();
			while(tunnelsWalls != 31)
			{
				walls.add(tunnelsWalls);
				tunnelsWalls = layout.nextInt();
			}
			
			roomLayout[currentRoom - 1] = new Rooms(currentRoom, tunnels, walls);
			walls.clear();
			tunnels.clear();
			if(layout.hasNextInt())
			{
				currentRoom = layout.nextInt();
			}
		}
		return roomLayout;
	}
}
