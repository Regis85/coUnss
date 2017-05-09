/*
 * Copyright 2017 Régis Bouguin
 * 
 * This file is part of coUnss
 * 
 * coUnss is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * coUnss is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Foobar.  If not, see <http://www.gnu.org/licenses/>
 * 
 */

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
