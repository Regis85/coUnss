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
	 
	        System.out.println("IOPreference → Fichier config.xml sauvegardé avec succès!");
	
		} catch (TransformerException tfe) {
			System.out.println("IOPreference → Erreur lors de l'export vers le fichier config.xml");
			tfe.printStackTrace();
		}

		
	}

}
