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
	double X;
	double Y;
	double BOUNDX;
	double BOUNDY;
	
	public BallSprite(double x, double y, double size, double shift, double boundX, double boundY)
	{
		BALLX = x;
		BALLY = y;
		RATE = 0.0001;
		MAXSIZE = size;
		SIZE = shift;
		COLOR = 250;
		Y=0.5;
		X= 0.5;
		BOUNDX = boundX;
		BOUNDY = boundY;
	}
	
	public void draw()
	{

		Y=Y+moveY(Y);
		X=X+moveX(X);
		
		
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
			BALLX = X;
			BALLY = Y;
			SIZE = MAXSIZE;
			COLOR = 250;
		}
		
	}
	
	private double moveX( double x)
	{
		if(SIZE == MAXSIZE);
		//System.out.println(x);
		double mouse = StdDraw.mouseX();
		double rate = 0.002;
		
		if(x <= 0.5-BOUNDX)
		{
			return rate;
		}
		
		if(x<mouse+0.01 && x>mouse-0.01)	
		{
			return 0;
		}
		//yeet
		if(x > mouse || x >= 0.5+BOUNDX)
		{
			return -rate;
		}
		
		else if(x < mouse+0.01)
		{
			return rate;
		}
		
		return 0;
	}

	private double moveY(double y)
	{
		double mouse = StdDraw.mouseY();
		double rate = 0.002;
		
		if(y <= 0.5-BOUNDY)
		{
			return rate;
		}
		
		if(y<mouse+0.01 && y>mouse-0.01)	
		{
			return 0;
		}
		if(y > mouse || y>= 0.5+BOUNDY)
		{
			return -rate;
		}
		
		else if(y < mouse)
		{
			return rate;
		}
		
		return 0;
	}
}
	
	