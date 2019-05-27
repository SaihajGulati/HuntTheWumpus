/* Daniel Popa
 * Team: Artesian Code
 */
import java.util.*;
import java.awt.Font;
import java.util.ArrayList;
 
public class BallSprite{
	
	private double BALLX;
	private double BALLY;
	private double SIZE;
	private double MAXSIZE;
	private double RATE;
	private int COLOR;
	
	public BallSprite(double x, double y, double size, double number, double ballnumber)
	{
		BALLX = x;
		BALLY = y;
		RATE = 0.0001;
		MAXSIZE = size;
		SIZE = ballnumber*(MAXSIZE/number);
		COLOR = 250;
	}
	
	public void draw(double x, double y)
	{
		
		if(SIZE>MAXSIZE+0.1)
		{
			SIZE = 0;
		}
		
		if(COLOR < 0)
		{
			COLOR = 0;
		}		

		double rate = RATE;
		int colorrate = 3;
				
		StdDraw.setPenColor(COLOR,COLOR,COLOR);
		StdDraw.filledCircle(BALLX, BALLY, SIZE);
		SIZE-= rate;
		COLOR -= colorrate;
		
		if(SIZE <= 0)
		{
			BALLX = x;
			BALLY = y;
			SIZE = MAXSIZE;
			COLOR = 250;
		}
		
	}
}
	
	