package affichage;

import java.awt.Dimension;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;

public class JComboNombre extends JComboBox {
	

	
	public JComboNombre() {
		
	}
	
	public JComboNombre(int min , int max) {
		super();

	    this.setPreferredSize(new Dimension(45, 20));
	    for(int i=min;i<=max;i++) {
	    	this.addItem(i);
	    }
	    DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
	    dlcr.setHorizontalAlignment(DefaultListCellRenderer.RIGHT);
	    this.setRenderer(dlcr);    
	}

}
