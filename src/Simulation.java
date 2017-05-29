import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFrame;



public class Simulation extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	Box					box;
	GridBagLayout		layout;
	GridBagConstraints  constraints;
	RightPanel			right;
	LeftPanel			left;
	JButton 			MSTButton;
	JButton 			WRTButton;
	JButton 			ResetButton;
	JButton 			CalculateButton;
	JButton 			UndoButton;
	
	public Simulation (String title){
        super(title);
        setSize(900,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        setLayout(new BorderLayout(2,2));
        
        left = new LeftPanel();
        add(BorderLayout.WEST,left);
        
       
        right = new RightPanel();
        right.numberSensors.setEditable(false);
        right.Radius.setEditable(false);
        right.Status.setEditable(false);
        right.Edges.setEditable(false);
        add(BorderLayout.EAST, right);
        
        box = new Box(right);
        add(BorderLayout.CENTER,box);
        
        MSTButton = new JButton("MST");
        MSTButton.addActionListener(this);
        left.add(MSTButton);
        
        WRTButton = new JButton("WRT");
        WRTButton.addActionListener(this);
        left.add(WRTButton);
        
        ResetButton = new JButton("Reset");
        ResetButton.addActionListener(this);
        left.add(ResetButton);
        
        CalculateButton = new JButton("Make Trees");
        CalculateButton.addActionListener(this);
        left.add(CalculateButton);
        
        UndoButton = new JButton("Undo Last Sensor");
        UndoButton.addActionListener(this);
        left.add(UndoButton);
        
	}
	
	public static void main(String args[]){
		Simulation sim = new Simulation ("The Simulation");
		sim.setVisible(true);
		sim.box.update();
		
	}
	
	//handle button clicks
	public void actionPerformed(ActionEvent event) {
		
		final Object obj = event.getSource();
		
		if( obj == MSTButton)		{	box.toggleShowMST();  }else 
		if( obj == WRTButton)		{   box.toggleShowWRT();  }else 
		if( obj == ResetButton)		{ 	box.reset();          }else 
		if( obj == CalculateButton)	{	box.calculateTrees(); }else
		if( obj == UndoButton)	    {	box.undoLastSensor(); }
		
	}

}
