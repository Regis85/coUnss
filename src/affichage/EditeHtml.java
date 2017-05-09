package affichage;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;

public class EditeHtml extends JDialog {

	public EditeHtml(JFrame parent, String title, boolean modal) {
        //On appelle le construteur de JDialog correspondant
        super(parent, title, modal);
        //On spécifie une taille
        this.setSize(640, 480);
        //La position
        this.setLocationRelativeTo(null);
        //La boîte ne devra pas être redimensionnable
        this.setResizable(false);
        //Enfin on l'affiche
        this.setVisible(true);
        //Tout ceci ressemble à ce que nous faisons depuis le début avec notre JFrame.
	    
		System.out.println("Édite du HTML");
	    
	    /*
	    JScrollPane scrollPane = new JScrollPane();
	    content.add(scrollPane);
	    */
	    /*
	    frame.setSize(640, 480);
	    frame.setVisible(true);
	    */

	    /*
	    public ZDialog(JFrame parent, String title, boolean modal){
	        //On appelle le construteur de JDialog correspondant
	        super(parent, title, modal);
	        //On spécifie une taille
	        this.setSize(200, 80);
	        //La position
	        this.setLocationRelativeTo(null);
	        //La boîte ne devra pas être redimensionnable
	        this.setResizable(false);
	        //Enfin on l'affiche
	        this.setVisible(true);
	        //Tout ceci ressemble à ce que nous faisons depuis le début avec notre JFrame.
	      }
	    */
	    
	}

	

}
