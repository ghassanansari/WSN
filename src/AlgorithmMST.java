
public class AlgorithmMST {

	public static EdgesNet generateMST(Box box){
		
		EdgesNet mstEdges = new EdgesNet("MST");
		SensorsNet mstSensors = new SensorsNet();
		
		//start with the base station
		mstSensors.addSensor(box.baseStation);
		
		EdgesNet outs = mstSensors.getOutEdges();
		
		Edge min = outs.minWeightEdge();		
		
		while(min != null){
			
			mstEdges.addEdge(min);			
			mstSensors.addSensorNotAlreadyInNetFromEdge(min);
			
			outs = mstSensors.getOutEdges();			
			min = outs.minWeightEdge();
			
		}
		
		return mstEdges;
	}

}
