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
Group root = new Group();
Group root2 = new Group();

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

public void Display(String [] args)
{

	launch(args);

}

//Start() is part of javafx, here I make everything that will be displayed
public void start(Stage stage) 
{

		menu(stage); 

}


private void score (Stage stage)
{
	String [] scores = {"Cool Kidz: "+(int)((Math.random()+1)*1000), "AAA: "+(int)((Math.random()+1)*1000), "Your mom: "+(int)((Math.random()+1)*1000), "Reeeee: "+(int)((Math.random()+1)*1000)};
    int wide = 1000;
    int tall = 700;
	   Scene scene = new Scene(root2, wide, tall, Color.BLACK);
	   
	   Text titletext = new Text();
	   makeText(root2, titletext, "HIGH SCORES", "verdana", 60, (wide/2)-200,150);
	   
	   for(int i = 0; i < scores.length; i++)
	   {
		   makeText(root2, new Text() , scores[i], "verdana", 40, (wide/2)-400,200+(i*50));
	   }
    stage.setTitle("Not_a_virus.exe");
    stage.setScene(scene);       
    stage.show();
}


private void menu(Stage stage)
{
    int wide = 1000;
    int tall = 700;
    root.setOnMouseClicked(new EventHandler<MouseEvent>() {
 	    @Override
 	    public void handle(MouseEvent event) {
 	    	getMainMenuePressed(event.getSceneY(), stage);
 	    	//System.out.println(buttonTester(pressed)+" "+pressed);
 	    }
 	});
    
    
    Scene scene = new Scene(root, wide, tall, Color.BLACK);
    
    
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
    
    System.out.println(pressed);

    stage.setTitle("Not_a_virus.exe");
    stage.setScene(scene);       
    stage.show(); 
}

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

private void getMainMenuePressed(double Y, Stage stage)
{
	   if(Y >= 250 && Y <= 350)
	   	{
		   menu(stage);
	   	}
	   
	   else if(Y >= 400 && Y <= 500)
		{
		   menu(stage);  
		}
	   
	   else if(Y >= 550 && Y <= 650)
		{
		   score(stage);  
		}
	   
	   menu(stage);
}

private static void makeGreyRectangle(Group root, Rectangle rectangle, int width, int height, int x, int y)
{
    Rectangle a = new Rectangle(x,y,width, height);
    rectangle = a;
    rectangle.setFill(Color.GREY);
    
    root.getChildren().add(rectangle);  
}

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
