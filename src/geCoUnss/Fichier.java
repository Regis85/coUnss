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
package geCoUnss;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import lib.SaveFichierXML;
import lib.SimpleErrorHandler;

public class Fichier implements SaveFichierXML {
	
	private Document docCo;
	private Document configDefaut = new Preference().getConfig();
	
	private String nomCheminFichier = ".";
	private String fichierCourse;
	private String fichierXsd = "gecounss.xsd";

	public Fichier() {
		creeFichier();
	}
	
	public Fichier(String cheminFichier) {
		
		System.out.println("ouverture du fichier → " + cheminFichier);
		fichierCourse = cheminFichier;
		litFichier();
		
	}
	
	public void creeFichier() {
		System.out.println("création d'un fichier");
		try {
			 
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
	        
	        // élément de racine
	        docCo = docBuilder.newDocument();
	        Element racine = docCo.createElement("geCO_unss");
	        docCo.appendChild(racine);
	        
	        Element version = docCo.createElement("version");
	        version.setTextContent("0.0.1");
	        racine.appendChild(version);

	        Element nom = docCo.createElement("nom");
	        System.out.println("Nom par défaut : " + configDefaut.getElementsByTagName("nom").item(0).getTextContent());
	        nom.setTextContent(configDefaut.getElementsByTagName("nom").item(0).getTextContent());
	        racine.appendChild(nom);

	        Element entete = docCo.createElement("entete");
	        entete.setTextContent("Course d'orientation");
	        racine.appendChild(entete);
	        
	        Element piedPage = docCo.createElement("piedPage");
	        piedPage.setTextContent("Ces résultats sont provisoires en attente des décisions du jury.");
	        racine.appendChild(piedPage);
	        
	        Element heureZero = docCo.createElement("hzh");
	        heureZero.setTextContent("0");
	        racine.appendChild(heureZero);
	        
	        Element minuteZero = docCo.createElement("hzm");
	        minuteZero.setTextContent("0");
	        racine.appendChild(minuteZero);

	        // Create a duplicate node and transfer ownership of the
	        // new node into the destination document
	        Node newNode = docCo.importNode(configDefaut.getElementsByTagName("categories").item(0), true);
	        // Make the new node an actual item in the target document
	        docCo.getDocumentElement().appendChild(newNode);
	        
            enregistreSousFichier();

		} catch (ParserConfigurationException pce) {
			System.out.println("Erreur de configuration");
			pce.printStackTrace();
		}
		
	}
	
	public void saveFichier() {
		System.out.println("Enregistrer le fichier " + nomCheminFichier);
		enregistreFichier(this.docCo, "gecounss.xsd",nomCheminFichier);
	}
	
	public void saveFichier(String chemin) {
		if(chemin == "") {
			enregistreSousFichier();
		} else{
			System.out.println(chemin);
		}
	}
	
	public void enregistreSousFichier() {
		JFileChooser filechoose = new JFileChooser();
		filechoose.setCurrentDirectory(new File("."));  /* ouvrir la boite de dialogue dans répertoire courant */
		filechoose.setDialogTitle("Enregistrer la rencontre"); /* nom de la boite de dialogue */
				 
		String approve = new String("Enregistrer"); /* Le bouton pour valider l’enregistrement portera la mention Enregistrer */
		int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){ /* Si l’utilisateur clique sur le bouton Enregistrer */
			nomCheminFichier = filechoose.getSelectedFile().getAbsolutePath(); /* pour avoir le chemin absolu */
		    /* ici il faut appeler une méthode pour écrire dans un fichier*/
			enregistreFichier(this.docCo, "gecounss.xsd", nomCheminFichier);
		}		
	}
	
	public String getChemin() {
		return this.nomCheminFichier;
	}
	
	private void litFichier() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			
			//Ces trois lignes servent à informer que la validation se fait via un fichier XSD
			SchemaFactory sfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			//On créé notre schéma XSD
			Schema schema = sfactory.newSchema(new File(fichierXsd));
			//On l'affecte à notre factory afin que le document prenne en compte le fichier XSD
			factory.setSchema(schema);    
	         
			DocumentBuilder builder = factory.newDocumentBuilder();
	         
			//création de notre objet d'erreurs
			ErrorHandler errHandler = new SimpleErrorHandler();
			//Affectation de notre objet au document pour interception des erreurs éventuelles
			builder.setErrorHandler(errHandler);
			File fileXML = new File(fichierCourse);
	         
			//On rajoute un bloc de capture pour intercepter les erreurs au cas où il y en ait
			try {
				docCo = builder.parse(fileXML);
				Element root = docCo.getDocumentElement();
				System.out.println("getNodeName -> " + docCo.getNodeName());
				
				System.out.println("pied de page → " + docCo.getElementsByTagName("piedPage").item(0).getTextContent());
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
		
	}
	
	public int getHeureZero() {
		return Integer.parseInt(docCo.getElementsByTagName("hzh").item(0).getTextContent());
	}
	
	public int getMinuteZero() {
		return Integer.parseInt(docCo.getElementsByTagName("hzm").item(0).getTextContent());
	}
	
	public String getNom() {
		return docCo.getElementsByTagName("nom").item(0).getTextContent();
	}
	

}
