/* Daniel Popa
 * Team: Artesian Code
 */
import java.util.*;
import java.awt.Font;
import java.util.ArrayList;
 
public class FlashingText{
	
	private double X;
	private double Y;
	private int SIZE;
	private double RATE;
	private double COUNTER;
	private int [] COLOR;
	private String MESSAGE;
	
	public FlashingText(int rate, int colorR, int colorG, int colorB)
	{
		COUNTER = 0;
		RATE = rate;
		COLOR = new int [3];
		COLOR [0] = colorR;
		COLOR [1] = colorG;
		COLOR [2] = colorB;
	}
	
	public void draw( String message, double x, double y, int size)
	{
		SIZE = size;
		X =x;
		Y=y;
		MESSAGE = message;
		
		if(COUNTER > RATE/2)
		{
		StdDraw.setPenColor(COLOR[0],COLOR[1],COLOR[2]);
		
		Font font = new Font("Copperplate Gothic Bold",0, SIZE);
		StdDraw.setFont(font);
		StdDraw.text(X, Y, MESSAGE);
		}
		
		COUNTER++;
		
		if(COUNTER > RATE)
		{
			COUNTER =0;
		}

	}
	
}	