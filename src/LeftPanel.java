import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class LeftPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int HEIGHT = 450;
	static int WIDTH = 210;
	Border loweredetched;
	TitledBorder title;
	
	
	public LeftPanel(){
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setVisible(true);
		this.setLocation(10, 30);
		
		 title = BorderFactory.createTitledBorder(
                 loweredetched, "Options");
		 title.setTitleJustification(TitledBorder.LEFT);
		 this.setBorder(title);
	}

}
