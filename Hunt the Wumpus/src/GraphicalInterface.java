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
 *
 */
import java.util.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
 
public class GraphicalInterface extends Application {

int BAT;
int HOLE; 
int WUMPUS;
int pressed;

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

// Displays high score
public void displayHighscore(int highScore)
{
   System.out.println("Player Highscore: "+highScore);
}

public void displayMainMenu(String [] args)
{
	launch(args);
}

//Start() is part of javafx, here I make everything that will be displayed
@Override
public void start(Stage stage) {
    Group root = new Group();
    int wide = 1000;
    int tall = 700;
    
    
    Scene scene = new Scene(root, wide, tall, Color.BLACK);
    
    root.setOnMouseClicked(new EventHandler<MouseEvent>() {
 	    @Override
 	    public void handle(MouseEvent event) {
 	    	pressed = (int)event.getSceneY();;
 	    	//System.out.println(buttonTester(getMainMenuPressed()));
 	    }
 	});
    
    Text playtext = new Text();
    Text exittext = new Text();
    Text scoretext = new Text();
    Text titletext = new Text();
    
    
    Rectangle playButton = new Rectangle();
    Rectangle exitButton = new Rectangle();       
    Rectangle scoreButton = new Rectangle();

    makeGreyRectangle(root, playButton,500,100,(wide/2)-250,(tall/2)-100);
    makeGreyRectangle(root, exitButton,500,100,(wide/2)-250,(tall/2)+50);
    makeGreyRectangle(root, scoreButton,500,100,(wide/2)-250,(tall/2)+200);
    makeText(root, titletext, "HUNT THE WUMPUS", "verdana", 60, (wide/2)-320,150);
    makeText(root, playtext, "PLAY GAME", "verdana", 60, (wide/2)-190,320);
    makeText(root, exittext, "EXIT", "verdana", 60, (wide/2)-80,470);
    makeText(root, scoretext, "HIGH SCORES", "verdana", 60, (wide/2)-235,620);  
    
    
    stage.setTitle("Not_a_virus.exe");
    stage.setScene(scene);
    stage.show();
}

//For me to see if the button is actually getting pressed, prints which button was pressed
private static String buttonTester(int button)
{
	   if(button == 1)
	   {
		   return "PLAY BUTTON";
	   }
	   
	   else if(button == 2)
	   {
		   return "EXIT BUTTON";
	   }
	   
	   else if(button == 3)
	   {
		   return "HIGH SCORE BUTTON";
	   }
	   
	   return "NONE";
}

//Return 0-3 which button was pressed, 0 -> Play Game, 1 -> Exit, 2 -> High Scores, 3 -> None
public int getMainMenuPressed()
{
	   int Y  = pressed;
	   if(Y >= 250 && Y <= 350)
	   	{
		   return 0;
	   	}
	   
	   else if(Y >= 400 && Y <= 500)
		{
		   return 1;   
		}
	   
	   else if(Y >= 550 && Y <= 650)
		{
		   return 2;   
		}
	   
	   return 3;
	   
}

//Methods makes Grey rectangles for homescreen buttons
private static void makeGreyRectangle(Group root, Rectangle rectangle, int width, int height, int x, int y)
{
    Rectangle a = new Rectangle(x,y,width, height);
    rectangle = a;
    rectangle.setFill(Color.GREY);
    
    root.getChildren().add(rectangle);  
}

//Method that makes text for homescreen buttons
private static void makeText(Group root, Text text, String message, String font, int size, int x, int y)
{
    text.setFill(Color.WHITE);
    text.setText(message); 
    text.setFont(Font.font(font, FontWeight.BOLD, FontPosture.REGULAR, size));
    text.setX(x); 
    text.setY(y);
    root.getChildren().add(text);
} 

}
