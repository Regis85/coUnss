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
package coUnss;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import dialogues.SaveConfig;






public class Preference implements SaveConfig {
	
	private String dossierTravail;
	private String fichierPref = "config.xml";
	private String fichierPrefXsd = "config.xsd";
	
	private Document xml = null;
	private NodeList nomList;
	
	private String nomAS  = "Nom de l'association";
	private String portCom;
	
	public Preference() {
		
		dossierTravail = System.getProperty("user.dir");
		
		System.out.println(System.getProperty("user.home"));
		
		System.out.println("Lecture du fichier → " + fichierPref);
		System.out.println("Dossier de travail → " + dossierTravail);
		
		File monFichier = new File(fichierPref);
		if(!monFichier.exists())
		{
			System.out.println("Le fichier " + fichierPref + " n'existe pas, on le crée");
			creePref();
		}
		System.out.println("Lecture de " + fichierPref);
		litPref();
		
	}
	
	private void creePref() {
		try {
			 
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
	        
	        // élément de racine
	        Document doc = docBuilder.newDocument();
	        Element racine = doc.createElement("config");
	        doc.appendChild(racine);

	        // élément nom
	        Element nom = doc.createElement("nom");
	        nom.setTextContent(nomAS);
	        racine.appendChild(nom);

	        // élément port
	        Element port = doc.createElement("port");
	        port.setTextContent("");
	        racine.appendChild(port);

	        // élément dossier
	        Element dossier = doc.createElement("dossier");
	        dossier.setTextContent(System.getProperty("user.dir"));
	        racine.appendChild(dossier);

	        // élément sauvegarde
	        Element dossierSauvegarde = doc.createElement("sauvegarde");
	        dossierSauvegarde.setTextContent(System.getProperty("user.dir")+"/sauvegarde");
	        racine.appendChild(dossierSauvegarde);
	        
	        // créer collège et lycée
	        Element categories = doc.createElement("categories");
	        racine.appendChild(categories);
	        
	        Element colleges = doc.createElement("categorie");
	        categories.appendChild(colleges);
	        Element nomLongCollege = doc.createElement("nomLong");
	        nomLongCollege.setTextContent("Collèges");
	        colleges.appendChild(nomLongCollege);
	        Element nomCourtCollege = doc.createElement("nomCourt");
	        nomCourtCollege.setTextContent("COL");
	        colleges.appendChild(nomCourtCollege);
	        
	        Element lycee = doc.createElement("categorie");
	        categories.appendChild(lycee);
	        Element nomLongLycee = doc.createElement("nomLong");
	        nomLongLycee.setTextContent("Lycées");
	        lycee.appendChild(nomLongLycee);
	        Element nomCourtLycee = doc.createElement("nomCourt");
	        nomCourtLycee.setTextContent("LYC");
	        lycee.appendChild(nomCourtLycee);

            enregistreConfig(doc);

		} catch (ParserConfigurationException pce) {
			System.out.println("Erreur de configuration");
			pce.printStackTrace();
		}
	}
	
	
	private void litPref() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			
			//Ces trois lignes servent à informer que la validation se fait via un fichier XSD
			SchemaFactory sfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			//On créé notre schéma XSD
			Schema schema = sfactory.newSchema(new File(fichierPrefXsd));
			//On l'affecte à notre factory afin que le document prenne en compte le fichier XSD
			factory.setSchema(schema);    
	         
			DocumentBuilder builder = factory.newDocumentBuilder();
	         
			//création de notre objet d'erreurs
			ErrorHandler errHandler = new SimpleErrorHandler();
			//Affectation de notre objet au document pour interception des erreurs éventuelles
			builder.setErrorHandler(errHandler);
			File fileXML = new File(fichierPref);
	         
			//On rajoute un bloc de capture pour intercepter les erreurs au cas où il y en ait
			try {
				xml = builder.parse(fileXML);
				Element root = xml.getDocumentElement();
				System.out.println(root.getNodeName());
			} catch (SAXParseException e) {} 
	           
		} catch (ParserConfigurationException e) {
			System.out.println("Erreur de configuration");
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Erreur de validation du fichier xml");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur de lecture");
			e.printStackTrace();
		}
		
		NodeList nomList = xml.getElementsByTagName("nom");
		nomAS = nomList.item(0).getTextContent();
		portCom = xml.getElementsByTagName("port").item(0).getTextContent();
		
	}
	
	public Document getConfig() {
		return this.xml;
	}

	public String getNomAS() {
		return this.nomAS;
	}
	public String getPortCom() {
		return this.portCom;
	}
	
	
	

}
