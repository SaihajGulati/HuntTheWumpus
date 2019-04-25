/* Daniel Popa
 * Team: Artesian Code
 *Graphical interface displays what the 
 *user will see and interact with
 *First Created 3/4/2019
 *
 * Date of revision  |  Revision made
 *     3/12/2019     |  Added method headers
 *     3/25/2019     |  Made test window for GP
 *     3/26/2019     |  Made test homescreen with buttons
 *     3/28/2019     |  Did access stuff for goup in javafx, also modded menu methods to return which button was pressed.
 *     4/3/2019      |  Can switch to display scores, still crashes after screen change and functionality isn't complete
 *     4/21/2019     |  Replaced all the main menue code with StdDraw stuff ive been working on in a different file
 *     4/24/2019     | Made navigation a bit easier, no dead ends, added cave selection and return of main menue changed, will duiscus in class
 */
import java.util.*;
import java.awt.Font;
import java.util.ArrayList;
 
public class GraphicalInterface{

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

// return room number player wants to move in
public int playerMoves(Cave cave)
{
 return -1;
}

// return which answer player thought correct (I want to change this, will talk in class)
public  int triviaResponse()
{
 return -1;
}

// Displayer player Items
public void displayItems()
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
  if(hazard == BAT)
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

// returns if player has enough money to buy the item they want
public boolean buyItem(int item, int playerBalance) 
{
 return false;
}

// Displays the outcome of the game
public void Outcome(boolean score)
{
}

// Opens up the canvas for the game to be displayed on and enables double buffering
public static void start()
{
	StdDraw.enableDoubleBuffering();
	StdDraw.setCanvasSize(1000, 700);
}

public static int caveSelection(ArrayList<String> scores)
{	double x = 0.2;
    double y = 0.5;
    double containerx= 0.15;
    double containery= 0.5;
    double shift = 0.2;
     while(true)
    {
		StdDraw.clear();
		StdDraw.setPenColor(0,0,0);
		StdDraw.filledRectangle(0.5, 0.5, 0.5 , 0.5);// background
		//StdDraw.picture(0.5, 0.5, "C:\\Users\\s-dapopa\\Desktop\\cave.jpg",1, 1);
		
	    StdDraw.setPenColor(32,32,32);
		StdDraw.filledRectangle(x , y, containerx, containery);
		title(containerx+0.05,0.9, "Hunt the Wumpus" );
		

	if(button(x,0.85-shift, containerx,0.055,"Cave 1"))
		
		    return 1;	
	
	if(button(x,0.74-shift, containerx,0.055,"Cave 2"))
		    return 2;	
	
	if(button(x,0.63-shift, containerx,0.055,"Cave 3"))
		    return 3;	

	
	if(button(x,0.63-shift, containerx,0.055,"Cave 4"))
		    return 4;

	if(button(x,0.52-shift, containerx,0.055,"Cave 5"))
	        return 5;
	
	if(button(x,0.41-shift, containerx,0.055,"Main Menu"))
		mainmenu(scores);

			
	StdDraw.show();
    }


}

public static void displayHighScores(ArrayList<String> scores)
{
	boolean displayscores = true;
	while(displayscores)
			{
		displayscores = !highScores(scores);
		System.out.println("score");
			}
	mainmenu(scores);
	
}

private static void displayCredits(ArrayList<String> scores)
{
	boolean displaycredits = true;
	while(displaycredits)
			{
		displaycredits = !credits(scores);
		System.out.println("credits");
			}
	mainmenu(scores);
	
}

public static int mainmenu(ArrayList<String> scores)
{	
	int select = 0;
	while(select == 0)
	{
		StdDraw.clear();
		select = menubuttons();
	 }
	
	if(select == 2)
	{
		displayHighScores(scores);
	}
	
	if(select == 3)
	{
		displayCredits(scores);
	}
	
	if(select == 4)
	{
		return 0;
	}
	
	   StdDraw.clear();
	   return caveSelection(scores);
		
}



private static int menubuttons()
{	double x = 0.2;
    double y = 0.5;
    double containerx= 0.15;
    double containery= 0.5;
    double shift = 0.2;
    int toreturn = 0;

		StdDraw.clear();
		StdDraw.setPenColor(0,0,0);
		StdDraw.filledRectangle(0.5, 0.5, 0.5 , 0.5);// background
		//StdDraw.picture(0.5, 0.5, "C:\\Users\\s-dapopa\\Desktop\\cave.jpg",1, 1);
		
	    StdDraw.setPenColor(32,32,32);
		StdDraw.filledRectangle(x , y, containerx, containery);
		title(containerx+0.05,0.9, "Hunt the Wumpus" );
		

	if(button(x,0.745-shift, containerx,0.055,"PLAY"))
		
		    toreturn = 1;	
	
	if(button(x,0.635-shift, containerx,0.055,"HIGH SCORES"))
		    toreturn = 2;	

	
	if(button(x,0.525-shift, containerx,0.055,"CREDITS"))
		    toreturn = 3;	

	
	if(button(x,0.415-shift, containerx,0.055,"EXIT"))
		    toreturn = 4;	

			
	StdDraw.show();
	return toreturn;

}

private static boolean button(double x, double y, double high, double wide, String message)
{
	boolean hovering = inBox(x,y,high,wide);
	StdDraw.setPenColor( 32,32,32);
	if(hovering)
		StdDraw.setPenColor(0,0,128);
	
	StdDraw.filledRectangle(x, y, high , wide);
	StdDraw.setPenColor( 255,255,255);
	Font buttonfont = new Font("Copperplate Gothic Bold",0, 20);
	StdDraw.setFont(buttonfont);
	StdDraw.text(x, y, message);
	return hovering && Clicked();
}

private static void title(double x, double y, String message)
{
	Font titlefont = new Font("Copperplate Gothic Bold",0, 27);
	StdDraw.setPenColor( 255,255,255);
	StdDraw.setFont(titlefont);
	StdDraw.text(x, y, message);
}


private static boolean inBox(double xcenter, double ycenter, double height, double width) {
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        //System.out.println(x+" "+y);
        if(x < xcenter-width*2.7 || x> xcenter+width*2.7)
        {
        	return false;
        }
        
        if(y < ycenter-height/2.7 || y> ycenter+height/2.7)
        {
        	return false;
        }
        //System.out.println("YEE");
        return true;     
        
    }

private static boolean Clicked() 
{	
	if(StdDraw.isMousePressed())
	{
		while(StdDraw.isMousePressed())
		{
			//do nothing
		}
		return true;
	}
  return false;
}

private static boolean highScores(ArrayList<String> scores)
{	boolean toMain;
	StdDraw.clear();
	StdDraw.setPenColor( 0,0,0);
	StdDraw.filledRectangle(0.5, 0.5, 0.5 , 0.5);// background
	
	StdDraw.setPenColor( 32,32,32);
	StdDraw.filledRectangle(0.5, 0.5, 0.30 , 0.5);
	
	title(0.5, 0.9, "High Scores");
	
	for(int i = 0; i< Math.min(10, scores.size()); i++)
	{	Font scoresfont = new Font("Copperplate Gothic Bold",0, 30);
		StdDraw.setPenColor( 255,255,255);
		StdDraw.setFont( scoresfont);
		StdDraw.text(0.5, 0.75-(0.05*i), scores.get(i));		
	}

	toMain = button(0.5, 0.1 , 0.15 , 0.055, "Main Menu");
	StdDraw.show();
	return toMain;
}

private static boolean credits(ArrayList<String> scores)
{	boolean toMain;
	String [] names = {"ME","ME","ME","ME" };
	StdDraw.clear();
	StdDraw.setPenColor( 0,0,0);
	StdDraw.filledRectangle(0.5, 0.5, 0.5 , 0.5);// background
	
	StdDraw.setPenColor( 32,32,32);
	StdDraw.filledRectangle(0.5, 0.5, 0.30 , 0.5);
	
	title(0.5, 0.9, "Credits");
	Font creditsfont = new Font("Copperplate Gothic Bold",0, 30);
	
	for(int i = 0; i< names.length; i++)
	{
		StdDraw.setPenColor( 255,255,255);
		StdDraw.setFont(creditsfont);
		StdDraw.text(0.5, 0.75-(0.1*i), names[i]);		
	}

	toMain = button(0.5, 0.1 , 0.15 , 0.055, "Main Menu");
	StdDraw.show();
	return toMain;
}
}