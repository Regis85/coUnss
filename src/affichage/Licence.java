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

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JPanel;
import javax.swing.text.Document;


public class Licence {
	JOptionPane jop = new JOptionPane();
	
	public Licence() {
		
		JEditorPane jep = new JEditorPane("text/html", 
				"Icones issues de <a href='http://icones.pro'>icones.pro</a>");
	    jep.setEditable(false);
	    jep.setOpaque(false);
	    jep.addHyperlinkListener(new HyperlinkListener() {
	    	public void hyperlinkUpdate(HyperlinkEvent hle) {
	    		if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
	    			//afficheLicence(hle);
	    		  	URI uri = URI.create("http://icones.pro");
	    		  	BrowserControl(uri);
	    		}
	    	}
	    });
		JEditorPane jep2 = new JEditorPane("text/html", 
				"code sous <a href='file:./licence/gpl-3.0-standalone.html'=>licence GNU GPL 3.0</a");
	    jep2.setEditable(false);
	    jep2.setOpaque(false);
	    jep2.addHyperlinkListener(new HyperlinkListener() {
	    	public void hyperlinkUpdate(HyperlinkEvent hle2) {
	    		if (HyperlinkEvent.EventType.ACTIVATED.equals(hle2.getEventType())) {
	    			//afficheLicence(hle2);
	    		  	URI uri = new File("Licence/gpl-3.0-standalone.html").toURI();
	    		  	BrowserControl(uri);
	    		}
	    	}
	    });
	    
	    JPanel panneau = new JPanel();
	    panneau.setLayout(new GridLayout(2, 1));	    
	    panneau.add(jep2);
	    panneau.add(jep);
	    
	    String titre = "Licences";
		
		JOptionPane.showMessageDialog(null, panneau , titre, JOptionPane.INFORMATION_MESSAGE);
	}

	public void BrowserControl(URI uri)
	{
	  	try {
	  		Desktop.getDesktop().browse(uri);
	  	}
	  	catch (Exception ex) {
	  		System.out.println (ex.toString());
	  		System.out.println(ex.getMessage());
	  	}
	  	
	      //displayURL("http://icones.pro");
	}
	
}

class ActivatedHyperlinkListener implements HyperlinkListener {

  Frame frame;

  JEditorPane editorPane;

  public ActivatedHyperlinkListener(Frame frame, JEditorPane editorPane) {
    this.frame = frame;
    this.editorPane = editorPane;
  }

  public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
    HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
    final URL url = hyperlinkEvent.getURL();
    if (type == HyperlinkEvent.EventType.ENTERED) {
      System.out.println("URL: " + url);
    } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
      System.out.println("Activated");
      Runnable runner = new Runnable() {
        public void run() {
          // Retain reference to original
          Document doc = editorPane.getDocument();
          try {
            editorPane.setPage(url);
          } catch (IOException ioException) {
            JOptionPane.showMessageDialog(frame,
                "Error following link", "Invalid link",
                JOptionPane.ERROR_MESSAGE);
            editorPane.setDocument(doc);
          }
        }
      };
      SwingUtilities.invokeLater(runner);
    }
  }
  
  
}

