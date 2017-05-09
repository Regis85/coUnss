package affichage;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class PanneauEst extends JPanel {
	
	public PanneauEst() {
		Border cadreCourse = BorderFactory.createTitledBorder("Parcours" );
		this.setBorder(cadreCourse);
	 }


}
