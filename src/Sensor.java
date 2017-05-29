import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;



public class Sensor {

		final static int RANGE = 70;
		int x = 0;
		int y = 0; 
		int range = RANGE;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		public Edge out;
		public int cost = 0;
		
		private static final Stroke RANGE_STROKE = 
				new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {2}, 0);
		private static final Color RANGE_COLOR = new Color(255,200,50);
		private static final Color DOT_COLOR = Color.BLUE;

		public static double distance(Sensor left, Sensor right){
			
			int x = Math.abs(left.x - right.x);
			int y = Math.abs(left.y - right.y);
			
			return Math.sqrt((x*x) + (y*y));
		}
		
		public Sensor(){ this(0,0, Sensor.RANGE); 	}
		public Sensor(int X,int Y){ this(X,Y,Sensor.RANGE); }
		
		public Sensor(int X,int Y, int Range){
			x = X;
			y = Y;
			range = Range;
		}

		
		
		public boolean inRange(Sensor s){
			
			return Sensor.distance(this, s) < s.range;
		}

		@Override
		public String toString(){

			return String.format("Sensor (" + x +"," + y + ")");
		}
		
	
		void drawWith(Graphics2D g){
			
			final int DOT_SIZE = 6;

			g.setColor(DOT_COLOR);
			g.fillOval(x -DOT_SIZE/2,  y -DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
			
			g.setColor(RANGE_COLOR);
			g.setStroke(RANGE_STROKE);
			g.drawOval(x - range, y-range, range*2, range*2);
		}

		public void addEdge(Edge e) {
			edges.add(e);
			
		}
		
		public Sensor otherSensor(Edge e){
			
			if(e == null){ return null; }
			
			if( this == e.s1 ){ return e.s2; }
			
			return e.s1;
		}
		
		public Edge getMinEdge(){
			
			if(edges.isEmpty()){ return null; }
			
			Edge min = edges.get(0);
			
				for (int x = 1; x< edges.size(); x++ ){
					if(min.weight > edges.get(x).weight)
						min = edges.get(x);
				}
			
			
			return min;
		}
		
		public void removeEdge(Edge e){
			edges.remove(e);
		}

		public void unlink() {
			
			for(Edge e : edges){
				otherSensor(e).removeEdge(e);				
			}
			edges.clear();
			
		}
}
