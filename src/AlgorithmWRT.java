


public class AlgorithmWRT {

	static final int R = 9;
	
	public static EdgesNet generateWRT(Box box) {
	
		BaseStation BS = box.baseStation;
		EdgesNet 	wrt = new EdgesNet();
		SensorsNet 	sensors = box.net;
		SensorsNet  wrtSensors = new SensorsNet();
		
		
		//wrtSensors.addSensor(BS);
		//System.out.println( wrtSensors.getOutEdges().minWeightEdge() );
		
		
		Edge min = BS.getMinEdge();
		
		if( min == null) { return wrt; }
		
		wrt.addEdge(min);
		//wrtSensors.addSensor( min.giveMeOtherSensor(BS)  );
		wrtSensors.addSensorsFromEdge( min  );
		System.out.println("wrtSensors:");
		System.out.println(wrtSensors);
		
		while (wrt.size() < sensors.size() - 1){
			
			EdgesNet potentials = wrtSensors.getOutEdges();
			
			Edge e = findNextEdge(potentials, wrtSensors);
			
			
			wrt.addEdge(e);
			Sensor parent = wrtSensors.getSensorInNetFromEdge(e);
			parent.cost += AlgorithmWRT.R;
			Sensor child = 	wrtSensors.getSensorNOTInNetFromEdge(e);
			child.cost = e.weight;
			child.out = e;
			
			wrtSensors.addSensorNotAlreadyInNetFromEdge(e);
			//wrt.printEdges();
			
		}
		
		return wrt;
		
	
	}


	private static Edge findNextEdge(EdgesNet potentials, SensorsNet sensors) {

		
		Edge edge = potentials.edges.get(0);
		
		//we want to find which sensor is in the tree
		Sensor parent = (sensors.contains(edge.s1))?   edge.s1 : edge.s2;
		EdgeCost min = new EdgeCost(edge, parent.cost);
		EdgeCost temp;
		
		
		for(int x = 1; x < potentials.size(); x++){
			
			Edge nextEdge = potentials.edges.get(x);
			
			parent = (sensors.contains(edge.s1))?   edge.s1 : edge.s2;
			
			temp = new EdgeCost (nextEdge, parent.cost + AlgorithmWRT.R);
			
			min = EdgeCost.min(min, temp);
			
			
			
		}
		
		return min.edge;
	}

	
	

	
}

//private class
	class EdgeCost {
	
			Edge edge;
			int parentCost;
			
			public EdgeCost(Edge e, int cost){
				edge = e;
				parentCost = cost;
			}
			
			public static EdgeCost min(EdgeCost left, EdgeCost right) {
				
				if (left == null || right == null ) return null;
				
				// check if 
				if (left.getMaxCost() < right.getMaxCost()) {
					
					return left;
					
				}

				
				return right;
			}

			int getLeafCost()   { return edge.weight; }
			int getParentCost() { return parentCost; }
			int getMaxCost(){
				
				if (getLeafCost() < getParentCost()) return getParentCost();
				return getLeafCost();
				
			}
	}
