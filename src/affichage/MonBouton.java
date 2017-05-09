package affichage;



import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MonBouton extends JButton {

    public MonBouton() {
    }

	    public MonBouton(String filename, String aide) {
	    	super(new ImageIcon(filename));
	    	//super("icones/sauvegarde-32.png");
	    	this.setPreferredSize(new Dimension(32, 32));
			this.setToolTipText(aide);
			//this.setText("E");
	    	
	    }
	
}
