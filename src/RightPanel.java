import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class RightPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int 			HEIGHT = 450;
	static int 			WIDTH = 210;
	Border 				loweredetched;
	TitledBorder 		title;
	JTextField  		numberSensors, Radius, Edges;
	GridBagLayout   	layout;
	GridBagConstraints  constraints;
	JLabel 				numberLabel,radiusLabel,edgesLabel;
	JTextArea			Status;
		
	public RightPanel(){
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setVisible(true);
		
		layout = new GridBagLayout();
        constraints = new GridBagConstraints ();
        setLayout(layout);
        
        numberLabel = new JLabel("Number of Sensors");
        constraints.gridx = 0;
    	constraints.gridy = 0;
    	constraints.gridwidth = 2;
    	constraints.gridheight = 1;
    	constraints.weightx = 1;
    	constraints.weighty = 1;
    	layout.setConstraints(numberLabel, constraints);
        this.add(numberLabel);
        
        radiusLabel = new JLabel("Radius");
        constraints.gridx = 0;
    	constraints.gridy = 1;
    	constraints.gridwidth = 2;
    	constraints.gridheight = 1; 
    	constraints.weightx = 1;
    	constraints.weighty = 1;
    	layout.setConstraints(radiusLabel, constraints);
        this.add(radiusLabel);
        
        edgesLabel = new JLabel("Number of Edges");
        constraints.gridx = 0;
    	constraints.gridy = 2;
    	constraints.gridwidth = 2;
    	constraints.gridheight = 1; 
    	constraints.weightx = 2;
    	constraints.weighty = 1;
    	layout.setConstraints(edgesLabel, constraints);
        this.add(edgesLabel);
        
		numberSensors = new JTextField();
		constraints.gridx = 2;
    	constraints.gridy = 0;
    	constraints.gridwidth = 1;
    	constraints.gridheight = 1;
    	constraints.fill =  GridBagConstraints.HORIZONTAL;
    	constraints.ipadx = 40; 
    	constraints.ipady = 5; 
    	constraints.weightx = 1;
    	constraints.weighty = 1;
    	layout.setConstraints(numberSensors, constraints);
        this.add(numberSensors);
        
        Radius = new JTextField();
        constraints.gridx = 2;
    	constraints.gridy = 1;
    	constraints.gridwidth = 1;
    	constraints.gridheight = 1;
    	constraints.fill =  GridBagConstraints.HORIZONTAL;
    	constraints.ipadx = 40; 
    	constraints.ipady = 5; 
    	constraints.weightx = 1;
    	constraints.weighty = 1;
    	layout.setConstraints(Radius, constraints);
        this.add(Radius);
        
        Edges = new JTextField();
        constraints.gridx = 2;
    	constraints.gridy = 2;
    	constraints.gridwidth = 1;
    	constraints.gridheight = 1;
    	constraints.fill =  GridBagConstraints.HORIZONTAL;
    	constraints.ipadx = 40; 
    	constraints.ipady = 5; 
    	constraints.weightx = 1;
    	constraints.weighty = 1;
    	layout.setConstraints(Edges, constraints);
        this.add(Edges);
        
        Status = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(Status,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
       		 ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        constraints.gridx = 0;
    	constraints.gridy = 3;
    	constraints.gridwidth = 3;
    	constraints.gridheight = 1;
    	constraints.fill =  GridBagConstraints.BOTH;
    	constraints.ipadx = 190; 
    	constraints.ipady = 320; 
    	constraints.weightx = 1;
    	constraints.weighty = 1;
    	layout.setConstraints(scrollPane, constraints);
        this.add(scrollPane);
        
		 title = BorderFactory.createTitledBorder(
                 loweredetched, "Info");
		 title.setTitleJustification(TitledBorder.LEFT);
		 this.setBorder(title);


	}

}
