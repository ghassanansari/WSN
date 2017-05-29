import java.awt.Graphics2D;


public class Edge {

	Sensor s1, s2;
	int weight;
	
	//public boolean isHighlit = false;
//	public boolean isAlreadyChecked = false;
	
	Edge(){
		s1 = null;
		s2 = null;
		weight = 0;
	}
	
	Edge(Sensor S1, Sensor S2){
		s1 = S1;
		s2 = S2;
		weight = (int) Sensor.distance(S1, S2);
	}
/*
	public boolean isHighlit() {
		// TODO Auto-generated method stub
		return isHighlit;
	}
	
	public boolean isAlreadyChecked() {
		// TODO Auto-generated method stub
		return isAlreadyChecked;
	}
	*/
	public String toString() {
		
		return "Edge[" + s1 + ", " + s2 + ", " + weight  +  " ]";
	}
	
	public void drawWith(Graphics2D g){
		g.drawLine(s1.x , s1.y, s2.x, s2.y);
	}
	
}
