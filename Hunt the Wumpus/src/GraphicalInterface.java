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
 *     5/26/2019     | Havent been good at documenting edits, main menu is almost completely clean, startng game graphics soon
 */
import java.util.*;
import java.awt.Font;
import java.util.ArrayList;
 
public class GraphicalInterface{
	
	private int BAT;
	private int WUMPUS;
	private int HOLE;
	private BallSprite [] player;
	private FlashingText WARNING;
	
	
public GraphicalInterface(int b, int w, int h)
	{
		BAT = b;
		WUMPUS = w;
		HOLE = h;
		WARNING = new FlashingText(350,180,0,0);
		player = new BallSprite[500];
		for(int i = 0; i<500;i++)
		{
			player[i] = new BallSprite(0.5,0.5,0.01,(0.01/500)*i, 0.3007, 0.3007);
		}
		
	}

// Opens up the canvas for the game to be displayed on and enables double buffering
public static void start()
{
	StdDraw.enableDoubleBuffering();
	StdDraw.setCanvasSize(1000, 700);
}

//returns room or -1 for shoot arrow and -2 for buy a hint

public int getRoom(int room1, int room2, int room3, int [] danger)
{
	int toreturn;
	double[] ballcords = new double [2];
	StdDraw.clear();
	background();
	room();
	WariningSign(danger);
	ballcords = drawPlayer();
	toreturn = doors(room1, room2, room3,ballcords);
	//ballcords = drawPlayer();
	if(button(0.5, 0.1 , 0.15 , 0.055, "Shoot Arrow"))
	{
		toreturn = -1;
	}
	
	if(button(0.2, 0.1 , 0.15 , 0.055, "Buy Hint"))
	{
		toreturn = -2;
	}
	
	if(button(0.8, 0.1 , 0.15 , 0.055, "Exit"))
	{
		java.lang.System.exit(0);	
	}

	
	StdDraw.show();
	//System.out.println(toreturn);
	return toreturn;
}

public int shootArrow(int room1, int room2, int room3, int [] danger)
{
	int toreturn;
	double [] mousecords = {StdDraw.mouseX(), StdDraw.mouseY()};
	StdDraw.clear();
	background();
	room();
	WariningSign(danger);
	toreturn = doors(room1, room2, room3,mousecords);

	Font font = new Font("Copperplate Gothic Bold",0, 40);
	StdDraw.setPenColor( 150,0,0);
	StdDraw.setFont(font);
	StdDraw.text(0.5, 0.25, "Click on a Room to Shoot");	
	//ballcords = drawPlayer();
	
	if(button(0.5, 0.1 , 0.15 , 0.055, "Back"))
	{
		toreturn = -1;
	}
	arrowPath(toreturn);
	StdDraw.show();
	if(ClickedRelease() && toreturn > 0)
	{
	return toreturn;
	}
	
	else if(toreturn<=0)
	{
		return toreturn;
	}
	
	return 0;
}

private void arrowPath(int room)
{
	StdDraw.setPenColor(150,0,0);
	if(room>0)
	{
		StdDraw.setPenColor(250,0,0);
	}
	StdDraw.setPenRadius(0.02);
	StdDraw.line(0.5, 0.5, StdDraw.mouseX(), StdDraw.mouseY());
	StdDraw.setPenRadius();
}

private void WariningSign(int [] danger)
{
	int dangers = 0;
	int size = 0;
	String message = " | ";
	
	for(int i = 0; i< danger.length;i++)
	{
		if(danger[i] > 0)
		{
			dangers++;
			message+= dangerMessage(i)+" | ";
		}
	}
	
	if(dangers == 3)
	{
		size = 30;
	}
	
	else if(dangers == 2)
	{
		size = 40;
	}
	
	else if(dangers == 1)
	{
		size = 60;
	}
	
	WARNING.draw(message,0.5, 0.9, size);	

}

private String dangerMessage (int danger)
{
	String toreturn = "";
	if(danger == BAT)
	{
		toreturn += "There is a bat nearby";
	}
	
	if(danger == WUMPUS)
	{
		toreturn += "I smell a wumpus";
	}
	
	if(danger == HOLE)
	{
		toreturn += "I feel a draft";
	}
	
	return toreturn;
}

private double[] drawPlayer()
{
  	for(int i = 0; i<499;i++)
  	{
  		player[i].draw();
  		//System.out.println("draw"+i);
  	}
  	return player[499].draw();
}

// Return room that player chose
private int doors(int room1, int room2, int room3,double[] ballcords)
{
	int toreturn = 0; 
	//ballcords = drawPlayer();
	
	if(leftdoor(room1, ballcords))
	{
		toreturn = room1;
	}
	
	if(rightdoor(room2, ballcords))
	{
		toreturn = room2;
	}
	
	if(topdoor(room3, ballcords))
	{
		toreturn = room3;
	}
	
	return toreturn;	

}

private static void background()
{
	StdDraw.setPenColor(0,0,0);
	StdDraw.filledRectangle(0.5, 0.5, 0.5 , 0.5);
}

private static void room()
{
	StdDraw.setPenColor( 32,32,32);
	StdDraw.filledRectangle(0.5, 0.5, 0.3 , 0.3);
}

private static boolean leftdoor(int room, double[] ballcords)
{

	StdDraw.setPenColor( 100,100,100);
	
	if(room>0)
	{
	StdDraw.setPenColor( 255,255,255);
	Font lable = new Font("Copperplate Gothic Bold",0, 20);
	StdDraw.setFont(lable);
	StdDraw.text(0.17, 0.5, ""+room);
	StdDraw.setPenColor(150,0,0);
	}
	
	StdDraw.filledRectangle(0.2, 0.5, 0.007 , 0.2);
	
	return inBox(0.2, 0.5, 0.007 , 0.2,ballcords[0], ballcords[1]);
}

private static boolean rightdoor(int room, double[] ballcords)
{
	StdDraw.setPenColor( 100,100,100);
	
	if(room>0)
	{
	StdDraw.setPenColor( 255,255,255);
	Font lable = new Font("Copperplate Gothic Bold",0, 20);
	StdDraw.setFont(lable);
	StdDraw.text(0.83, 0.5, ""+room);
	StdDraw.setPenColor(150,0,0);
	}
	
	StdDraw.filledRectangle(0.8, 0.5, 0.007 , 0.2);
	return inBox(0.8, 0.5, 0.007 , 0.2,ballcords[0], ballcords[1]);
}

private static boolean topdoor(int room, double[] ballcords)
{
	StdDraw.setPenColor( 100,100,100);
	
	if(room>0)
	{
	StdDraw.setPenColor( 255,255,255);
	Font lable = new Font("Copperplate Gothic Bold",0, 20);
	StdDraw.setFont(lable);
	StdDraw.text(0.5, 0.83, ""+room);
	StdDraw.setPenColor(150,0,0);
	}
	
	StdDraw.filledRectangle(0.5, 0.8, 0.2 , 0.007);
	return inBox(0.5, 0.8, 0.2 , 0.007,ballcords[0], ballcords[1]);
	
}

private static void bottomdoor(boolean active)
{

	StdDraw.setPenColor(150,0,0);


	StdDraw.filledRectangle(0.5, 0.2, 0.2 , 0.007);
}

private static String getKeyTyped()
{
	char typed;
	if(StdDraw.hasNextKeyTyped())
	{
		typed = StdDraw.nextKeyTyped();
		//System.out.print((int)(typed));
		if((int)typed == 8)
		{
			return "-1";
		}
		
		return typed+"";
	}
	
	return "";
}

private static String delete(String s)
{
	if(s.length()>0)
	{
		return s.substring(0,s.length()-1);
	}
	
	return "";
}

public static String getName()
{
	String name = "";
	boolean button = true;
	String typed = "";
	int charLimit = 20;
	
	while(button)
	{
	StdDraw.clear();
	StdDraw.setPenColor( 0,0,0);
	StdDraw.filledRectangle(0.5, 0.5, 0.5 , 0.5);// background
	StdDraw.setPenColor( 32,32,32);
	StdDraw.filledRectangle(0.5, 0.5, 0.30 , 0.5);
	title(0.5, 0.9, "Enter Your Name");
	title(0.5, 0.5, name);
	
	typed = getKeyTyped();
	if(typed.equals("-1"))
	{
		name = delete(name);
	}
	else
	{
		name+=typed;
	}
	
	if(name.length()>charLimit)//name length limit
	{
		name = delete(name);
	}
	button = !button(0.5, 0.21 , 0.15 , 0.055, "Play");
	
	if(button(0.5, 0.1 , 0.15 , 0.055, "Main Menu"))
	{
		name = "";
		button = false;
	}
	StdDraw.show();
	}
	
	StdDraw.clear();
	return name;
}

public int caveSelection(ArrayList<String> scores)
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

	if(button(x,0.52-shift, containerx,0.055,"Cave 4"))
	        return 4;
	
	if(button(x,0.41-shift, containerx,0.055,"Cave 5"))
        return 5;
	
	if(button(x,0.30-shift, containerx,0.055,"Main Menu"))
		return mainmenu(scores);

			
	StdDraw.show();
    }


}

public int displayHighScores(ArrayList<String> scores)
{
	boolean displayscores = true;
	while(displayscores)
			{
		displayscores = !highScores(scores);
		//System.out.println("score");
			}
	return mainmenu(scores);
	
}

private int displayCredits(ArrayList<String> scores)
{
	boolean displaycredits = true;
	while(displaycredits)
			{
		displaycredits = !credits(scores);
		//System.out.println("credits");
			}
	return mainmenu(scores);
	
}

public int mainmenu(ArrayList<String> scores)
{	
	//System.out.println(scores);
	int select = 0;
	StdDraw.enableDoubleBuffering();
	while(select == 0)
	{
		StdDraw.clear();
		select = menubuttons();
	 }
	
	if(select == 2)
	{
		return displayHighScores(scores);
		
	}
	
	if(select == 3)
	{
		return  displayCredits(scores);
	}
	
	if(select == 4)
	{
		return 0;
	}
	
	   StdDraw.clear();
	   return caveSelection(scores);		
}
private static int menubuttons()
{	double x = 0.5;
    double y = 0.5;
    double containerx= 0.15;
    double containery= 0.5;
    int toreturn = 0;
    
        //StdDraw.disableDoubleBuffering();
		StdDraw.clear();
		StdDraw.setPenColor(0,0,0);
		StdDraw.filledRectangle(0.5, 0.5, 0.5 , 0.5);// background
		//StdDraw.picture(0.5, 0.5, "C:\\Users\\s-dapopa\\Desktop\\cave.jpg",1, 1);
		
	    StdDraw.setPenColor(32,32,32);
		StdDraw.filledRectangle(x , y, containerx, containery);
		title(x,0.8, "Hunt The Wumpus" );
		

	if(menubutton(x,0.605, containerx,0.055,"PLAY"))
		
		    toreturn = 1;	
	
	if(menubutton(x,0.495, containerx,0.055,"HIGH SCORES"))
		    toreturn = 2;	

	
	if(menubutton(x,0.385, containerx,0.055,"CREDITS"))
		    toreturn = 3;	

	
	if(menubutton(x,0.275, containerx,0.055,"EXIT"))
		java.lang.System.exit(0);	

			
	StdDraw.show();
	return toreturn;

}

private static boolean button(double x, double y, double high, double wide, String message)
{
	boolean hovering = inBox(x,y,high,wide);
	StdDraw.setPenColor( 32,32,32);
	if(hovering)
		StdDraw.setPenColor(150,0,0);
	
	StdDraw.filledRectangle(x, y, high , wide);
	StdDraw.setPenColor( 255,255,255);
	Font buttonfont = new Font("Copperplate Gothic Bold",0, 20);
	StdDraw.setFont(buttonfont);
	StdDraw.text(x, y, message);
	return hovering && ClickedRelease();
}

private static boolean menubutton(double x, double y, double high, double wide, String message)
{
	double thick = 0.015;
	boolean hovering = inBox(x,y,high,wide);
	StdDraw.setPenColor( 32,32,32);
	StdDraw.filledRectangle(x, y, high , wide);
	if(hovering)
	{
		StdDraw.setPenColor(150,0,0);
	    StdDraw.filledRectangle(x, y, high+thick, wide);
	}
	
	StdDraw.setPenColor( 255,255,255);
	Font buttonfont = new Font("Copperplate Gothic Bold",0, 20);
	StdDraw.setFont(buttonfont);
	StdDraw.text(x, y, message);
	
	return hovering && ClickedRelease();
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

private static boolean inBox(double xcenter, double ycenter, double height, double width, double xcord, double ycord) {
    double x = xcord;
    double y = ycord;
    //System.out.println(x+" "+y);
    if(x > xcenter-(width/10) && x< xcenter+(width/10))
    {
    	return true;
    }
    
    if(y > ycenter-(height/10) && y < ycenter+(height/10))
    {
    	return true;
    }
    //System.out.println("YEE");
    return false;     
    
}

private static boolean ClickedRelease() 
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

private static boolean Clicked() 
{	
	return StdDraw.isMousePressed();
}

private static boolean highScores(ArrayList<String> scores)
{
	boolean toMain;
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
	String [] names = {"Daniel Popa","Saihaj Gulati","Joshua Venable","Brian Yang", "Raj Sunku", "Hans Koduri" };
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