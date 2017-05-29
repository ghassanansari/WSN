import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;



public class EdgesNet {
	
	ArrayList<Edge> edges = new ArrayList<Edge>() ;
	String name = "";
	
	//Constructors
	EdgesNet(){ this(""); }	
	EdgesNet(String str){	name = str;	}
	
	public void addEdge(Edge e){
		if(e == null){ return; }
		edges.add(e);
	}
	
	public void clear(){
		edges.clear();
	}
	
	public void printEdges(){
		
		for(Edge e : edges){
			System.out.println(e);
		}
	}

	public String toString(){
		
		return "Edges Net: " + edges.size() + " edges"; 
	}

	public Edge minWeightEdge() {
		
		if(size() == 0){ return null; }
		
		Edge min = edges.get(0);
		
		for(Edge e: edges){
			
			if(e.weight < min.weight){ 	min = e; }
		}
		
		return min;
		
	}
	

	public int size() {
		
		return edges.size();
	}
	
	public void addAll(EdgesNet outs) {
		
		edges.addAll(outs.edges);
		
	}
	
	public void addAll(Collection<Edge>	 outs) {
		
		for(Edge e: outs){ addEdge(e);	}
		
	}
	
	
	//DRAWING
	public void drawWith(Graphics2D g){
		
		for(Edge e : edges){
			e.drawWith(g);
		}
		
	}
	public void removeAll(EdgesNet edgeNet) {
		
		edges.removeAll(edgeNet.edges);
		
	}
}
