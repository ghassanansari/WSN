import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class Box extends JPanel {

	
	private static final long serialVersionUID = 1L;

	//drawing
	private static final Stroke REGULAR_EDGE_STROKE = new BasicStroke(1.5f);
	private static final Stroke THICK_EDGE_STROKE = new BasicStroke(3f);
	private static final Stroke REALLY_THICK_EDGE_STROKE = new BasicStroke(6f);
	
	int X = 0;
	int Y = 0;
	static int HEIGHT = 449;
	static int WIDTH = 449;
//	static int HEIGHT_OVAL = 100;
//	static int WIDTH_OVAL = 100;
	
	JButton hello;
	Border 				loweredetched;
	TitledBorder 		title;
	RightPanel 			rightPanel;	
	static int 	Counter = 0;
	
	//model
	public SensorsNet 	net			= new SensorsNet();
	public EdgesNet 	edges 		= new EdgesNet();
	public BaseStation	baseStation = new BaseStation(HEIGHT/2, WIDTH/2);
	
	public EdgesNet 	mst 		= new EdgesNet("MST");
	public EdgesNet 	wrt 		= new EdgesNet("WRT");
//	public EdgesNet 	temp 		= new EdgesNet("temp");

	public boolean showMST = false; 
	public boolean showWRT = false;
	public boolean showTemp = false; 
	
	Sensor dragSensor = null;
	boolean isDragging = false;
	
	Box(RightPanel right){
		
		rightPanel = right;
		
		
		this.setPreferredSize(new Dimension(450, 450));
	    this.setVisible(true);
	    
	    
	    addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) { 
				
				System.out.println("mousePressed");
				
				//did we hit a Sensor?
				Sensor hit = sensorAt(e.getX(), e.getY());
				if(hit != null){
					
					//We've clicked on a Sensor
					statusMsg("Clicked on Sensor " + hit + "... cost: " + hit.cost);
					dragSensor = hit;
					isDragging = true;
					return;
				}
						
				Sensor s = new Sensor(e.getX(), e.getY());
				// here, we want to check if the new sensor intersects with
				// with other sensors ???
				checkForIntersection(s, net);
			
				net.addSensor(s);
				
				statusMsg(s);
					
				update();
			}
			public void mouseReleased(MouseEvent e)	{
				System.out.println("mouseReleased");
				if( isDragging && dragSensor != null){
					
					dragSensor.x = e.getX();
					dragSensor.y = e.getY();
				
					redoLinks(dragSensor);
				}
				isDragging = false;
				dragSensor = null;
				update();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("mouseDragged");
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
				System.out.println("mouseMoved");
				
				if( isDragging && dragSensor != null){
					dragSensor.x = e.getX();
					dragSensor.y = e.getY();
					
					
				}
			}

		});	
	    
	    reset();
	}
    
	protected void redoLinks(Sensor s) {
		
		EdgesNet toRemove = new EdgesNet();		
		toRemove.addAll(s.edges);
		
		s.unlink();
		
		//s.edges.clear();
		
		edges.removeAll(toRemove);
		
		checkForIntersection(s, net);
		
	}

	private Sensor sensorAt(int x, int y) {
		
		final int TOLERANCE = 2;
		
		return net.sensorNear(x,y,TOLERANCE);
		
	}
	protected void checkForIntersection(Sensor newSensor, SensorsNet net) {
		
		for(Sensor s: net.sensors){
			
			if( s == newSensor ){ continue; }
			
			if( newSensor.inRange(s) ){
				
				Edge e = new Edge(newSensor, s);
				edges.addEdge(e);
				
				//QQQ add neighbour edges to each sensor?
				newSensor.addEdge(e);
				s.addEdge(e);
			}
		  }
		
	}

	public void update(){
		
		//update the test fields
		rightPanel.numberSensors.setText( Integer.toString(net.size() ));
		rightPanel.Radius.setText(Integer.toString(Sensor.RANGE));
		rightPanel.Edges.setText( Integer.toString(edges.size() ));
		
		repaint();
	}
	
	
	public void paintComponent(Graphics g) {
		
	    super.paintComponent(g);
	    
	    Graphics2D g2 = (Graphics2D) g;
	    g.setColor(Color.blue);
	    g.drawRect(X, Y, WIDTH, HEIGHT);    
	    
	    drawEdges(g2);
	    	    
	    if( showWRT ){ drawWRTedges(g2);  }
	    if( showMST ){ drawMSTedges(g2);  }
    
	    drawNodes(g2);
	  
	  }

	private void drawWRTedges(Graphics2D g) {
		
		g.setColor(Color.GREEN);
		g.setStroke(REALLY_THICK_EDGE_STROKE);
		
		wrt.drawWith(g);
	
	}
	
	
	private void drawMSTedges(Graphics2D g) {
		
		g.setColor(Color.RED);
		g.setStroke(THICK_EDGE_STROKE);
		
		mst.drawWith(g);
	}

	private void drawEdges(Graphics2D g) {
		
		
		g.setColor(Color.LIGHT_GRAY);
		g.setStroke(REGULAR_EDGE_STROKE);
		
		edges.drawWith(g);
	}


	private void drawNodes(Graphics2D g) {
		
		for(Sensor s : net.sensors){	
			
			s.drawWith(g);			
			
		}	
	}
	
	public void statusMsg(Sensor s){ 
		statusMsg(s.toString());
	}
	
	public void statusMsg(String str){
		rightPanel.Status.append(str + "\n");
	}
	
	public void clearStatus(){
		rightPanel.Status.setText("");
	}
	
	public void reset(){
		
		net.clear();
		edges.clear();
		
		net.addSensor(baseStation);
		clearStatus();
		
		showMST = false; 
		showWRT = false;
		showTemp = false; 
		
		mst.clear();
		wrt.clear();
		
		isDragging = false;
		dragSensor = null;
	
		statusMsg("Reset.");
		update();
	}

	public void toggleShowMST() {
		showMST = !showMST;	
		statusMsg("show MST: " + showMST);
		update();
	}
	
	public void toggleShowWRT() {
		showWRT = !showWRT;	
		statusMsg("show WRT: " + showWRT);
		update();
		
	}

	//calculates the mst (done) 
	//and the wrt (not yet done)
	//press when done editing graph
	public void calculateTrees(){
		
		statusMsg("Calculate Trees");
		
		statusMsg("Calculating MST...");	
		mst.clear();
		EdgesNet mstEdges = AlgorithmMST.generateMST(this);		
		mst.addAll(mstEdges);
		//mst.printEdges();
		
		
		statusMsg("Calculating WRT...");
		
		wrt.clear();
		EdgesNet wrtEdges = AlgorithmWRT.generateWRT(this);		
		wrt.addAll(wrtEdges);
		
		System.out.println("-------------------");
		wrtEdges.printEdges();
		
		
		
	}

	public void undoLastSensor(){
		if(net.sensors.size() <2) { return; }
		
		Sensor  s = net.sensors.remove(net.sensors.size()-1);
		
		EdgesNet toRemove = new EdgesNet();		
		toRemove.addAll(s.edges);
		
		s.unlink();
		
		//s.edges.clear();
		
		edges.removeAll(toRemove);
		
		update();
	}
	
	
}
