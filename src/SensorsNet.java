import java.util.ArrayList;
import java.util.HashSet;


public class SensorsNet {

	ArrayList<Sensor> sensors;
	
	public SensorsNet(){
		sensors = new ArrayList<Sensor>();
	}
	
	
	public String toString(){
		
		return "SensorNet: " + sensors.size() + " sensors"; 
	}
	
	public void addSensor(Sensor s) {
		sensors.add(s);
		
	}
	
	public void printSensors(){
		
		for(Sensor s : sensors){
			System.out.println(s);
		}
	}
	
	public void clear() {

		for(Sensor s : sensors){
			
			s.edges.clear();
		}
		sensors.clear();
	}
	
	public int size(){ return sensors.size(); }


	public EdgesNet getEdges() {
		
		HashSet<Edge> outs = new HashSet<Edge>();	
		
		
		for(Sensor s : sensors){
			outs.addAll( s.edges );
			
		}
		
		EdgesNet out = new EdgesNet("out");		
		out.addAll(outs);
		return out;
	}


	public void addSensorNotAlreadyInNetFromEdge(Edge e) {
		if(e == null){ return; }
		
		if( !sensors.contains(e.s1) ) { addSensor(e.s1); }
		if( !sensors.contains(e.s2) ) { addSensor(e.s2); }
		
	}


	public EdgesNet getOutEdges() {
	
		HashSet<Edge> outs = new HashSet<Edge>();	
		
		
		for(Sensor s : sensors){
			for(Edge e : s.edges){
				if( sensors.contains(s.otherSensor(e)) ){
					continue;
				}
				outs.add(e);
			}			
		}
		
		EdgesNet out = new EdgesNet("out");		
		out.addAll(outs);
		return out;
	}
	
	public boolean contains(Sensor s){
		return sensors.contains(s);
	}
	
	public void addSensorsFromEdge(Edge min) {
		
		sensors.add(min.s1);
		sensors.add(min.s2);
		
	}


	public Sensor sensorNear(int x, int y, int TOLERANCE) {
		
		for(Sensor s : sensors){
			
			if ( 		Math.abs(s.x - x) < TOLERANCE 
					&& 	Math.abs(s.y - y) < TOLERANCE){
				
				return s;
				
			}
			
		}
		
		return null;
	}


	public Sensor getSensorInNetFromEdge(Edge e) {
		if(e == null){ return null; }
	
		if( !sensors.contains(e.s1) && sensors.contains(e.s2)) { return e.s2; }
		if( !sensors.contains(e.s2) && sensors.contains(e.s1)) { return e.s1; }
		
		return null;
	}
	
	public Sensor getSensorNOTInNetFromEdge(Edge e) {
		
		if(e == null){ return null; }
		
		if( !sensors.contains(e.s1) && sensors.contains(e.s2)) { return e.s1; }
		if( !sensors.contains(e.s2) && sensors.contains(e.s1)) { return e.s2; }
		
		return null;
	}

	
}
