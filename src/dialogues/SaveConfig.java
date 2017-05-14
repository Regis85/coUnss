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
package dialogues;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public interface SaveConfig {
	
	public default void enregistreConfig(Document dom) {
		
        try {

	        DOMSource source = new DOMSource(dom);
        	
        	 try {
 	        	SchemaFactory fabrique = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
 	        	InputSource sourceEntree = new InputSource("config.xsd");
 	        	SAXSource sourceXSD = new SAXSource(sourceEntree);

 	        	Schema schema = fabrique.newSchema(sourceXSD);
 	        	Validator validateur = schema.newValidator();

 	        	//validateur.validate(new DOMSource(dom));
 	        	validateur.validate(source);
 			      
 			 } 
 			 catch (SAXException e) {
 				 e.printStackTrace();
 			 }
 		 	catch (IOException e) {
 		 		e.printStackTrace();
 		 	}
        	
	        // write the content into xml file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        //transformerFactory.setAttribute("indent-number", new Integer(2));
	        
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        
	        //DOMSource source = new DOMSource(dom);
	        StreamResult resultat = new StreamResult(new File("config.xml"));
	 
	        transformer.transform(source, resultat);
	 
	        System.out.println("SaveConfig → Fichier config.xml sauvegardé avec succès!");
	
		} catch (TransformerException tfe) {
			System.out.println("SaveConfig → Erreur lors de l'export vers le fichier config.xml");
			tfe.printStackTrace();
		}

		
	}

}
