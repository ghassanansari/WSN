import java.awt.Color;
import java.awt.Graphics2D;


public class BaseStation extends Sensor{
		
	final int offset = 12;
	
	BaseStation(){
		super();
	}
	
	BaseStation(int X, int Y){
		super(X,Y);
	}
		
	void drawWith(Graphics2D g){
		
		
	    g.setColor(Color.BLACK);
	    g.fillOval(x-offset/2, y-offset/2, offset, offset);
	    

	}
}
	
