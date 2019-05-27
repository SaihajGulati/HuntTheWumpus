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

	public GraphicalInterface(int b, int w, int h)
	{
		BAT = b;
		WUMPUS = w;
		HOLE = h;
		
	}

// Opens up the canvas for the game to be displayed on and enables double buffering
public static void start()
{
	StdDraw.enableDoubleBuffering();
	StdDraw.setCanvasSize(1000, 700);
}

public static void gameGraphics()
{
	int balls = 500;
	double x = 0.5;
    double y = 0.5;
	//System.out.println("check0");
	BallSprite [] player = new BallSprite[balls];
	for(int i = 0; i<balls;i++)
	{
		player[i] = new BallSprite(x,y,0.01,i+1,balls);
	}
	

	//System.out.println("check1");
	while(true)
	{
		StdDraw.clear();
		String to = getKeyTyped();
		y= y+moveY(to,y);
		x=x+moveX(to,x);
	  //System.out.println("check2");
      StdDraw.clear();
      StdDraw.setPenColor(0,0,0);
      StdDraw.filledRectangle(0.5, 0.5, 1, 1);
  	for(int i = 0; i<balls;i++)
  	{
  		player[i].draw(x, y);
  		//System.out.println("draw"+i);
  	}
  	StdDraw.setPenColor(250,250,250);
  	StdDraw.filledCircle(x, y, 0.01);
  	
  	if(x > 1)
  	{
  		x = 0;
  	}
  	
  	else if(x<0)
  	{
  		x = 1;
  	}
  	
  	if(y > 1)
  	{
  		y = 0;
  	}
  	
  	else if(y<0)
  	{
  		y = 1;
  	}
      StdDraw.show();
      //StdDraw.pause(200);

	}
}

private static void room()
{
	
}

private static double moveX(String in, double x)
{
	 double mouse = StdDraw.mouseX();
	double rate = 0.002;
	
	if(x<mouse+0.01 && x>mouse-0.01)	
	{
		return 0;
	}
	//yeet
	if(x > mouse)
	{
		return -rate;
	}
	
	else if(x < mouse+0.01)
	{
		return rate;
	}
	
	return 0;
}

private static double moveY(String in, double y)
{
	double mouse = StdDraw.mouseY();
	double rate = 0.002;
	
	if(y<mouse+0.01 && y>mouse-0.01)	
	{
		return 0;
	}
	if(y > mouse)
	{
		return -rate;
	}
	
	else if(y < mouse)
	{
		return rate;
	}
	
	return 0;
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

	if(button(x,0.52-shift, containerx,0.055,"Cave 4"))
	        return 4;
	
	if(button(x,0.41-shift, containerx,0.055,"Cave 5"))
        return 5;
	
	if(button(x,0.30-shift, containerx,0.055,"Main Menu"))
		return mainmenu(scores);

			
	StdDraw.show();
    }


}

public static int displayHighScores(ArrayList<String> scores)
{
	boolean displayscores = true;
	while(displayscores)
			{
		displayscores = !highScores(scores);
		//System.out.println("score");
			}
	return mainmenu(scores);
	
}

private static int displayCredits(ArrayList<String> scores)
{
	boolean displaycredits = true;
	while(displaycredits)
			{
		displaycredits = !credits(scores);
		//System.out.println("credits");
			}
	return mainmenu(scores);
	
}

public static int mainmenu(ArrayList<String> scores)
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