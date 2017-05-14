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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.CellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import affichage.MonBouton;
import lib.XTableRenderer;

public class PrefDialogue extends JDialog implements SaveConfig {
	
	private JTextField textNom = new JTextField();
	
	private JComboBox<String> textPort;
	
	private Document config;
	
	private String txtDossier;
	private JLabel lblDossier = new JLabel("");
	
	private String txtDossierSauv;
	private JLabel lblDossierSauv = new JLabel("");
	
	private String categorieSelect = "";
	private JTable tableau;
	
	private MonBouton btnSupprimeCategorie = null;


	public PrefDialogue(JFrame parent, String title, boolean modal){
		
		//On appelle le construteur de JDialog correspondant
		super(parent, title, modal);
		//On spécifie une taille
		this.setSize(450, 400);
		//La position
		this.setLocationRelativeTo(null);
		//La boîte ne devra pas être redimensionnable
		this.setResizable(false);
		
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		JPanel containerHaut = new JPanel();
		containerHaut.setLayout(new BoxLayout(containerHaut, BoxLayout.PAGE_AXIS));
		//container.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		config = new coUnss.Preference().getConfig(); //le xml sous la forme Document
		
		/*----- nom de l'association -----*/
		JPanel panNom = new JPanel();
		panNom.setLayout(new FlowLayout(FlowLayout.LEFT));
		textNom = new JTextField(new coUnss.Preference().getNomAS());
		textNom.setColumns(20);
		panNom.add(new JLabel("Nom de l'association :"));
		panNom.add(textNom);
		containerHaut.add(panNom);

		/*----- port de la station -----*/
		JPanel panPort = new JPanel();
		panPort.setLayout(new FlowLayout(FlowLayout.LEFT));
		panPort.add(new JLabel("Port station maitre :"));
		textPort = new JComboBox<String>(getListePort());
		textPort.setSelectedItem(getPortActif());
		panPort.add(textPort);
		containerHaut.add(panPort);
		
		/*----- dossier CO -----*/
		JPanel panDossier = new JPanel();
		panDossier.setLayout(new BorderLayout());
		JPanel panDossierOuest = new JPanel();
		panDossierOuest.setLayout(new FlowLayout(FlowLayout.LEFT));
		panDossierOuest.add(new JLabel("Dossiers CO :"));
		txtDossier = new String(config.getElementsByTagName("dossier").item(0).getTextContent());
		MonBouton btnDossier = new MonBouton("icones/dossier2-32.png", "Dossier d'enregistrement de la course d'orientation");
		btnDossier.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			System.out.print("Bouton Dossier d'enregistrement du programme cliqué → ");
    			String dossierChoisi = btnChoixDossier(event, txtDossier);
    			System.out.println(dossierChoisi);
    			if(dossierChoisi != "") {
    				txtDossier = dossierChoisi;
    				lblDossier.setText(txtDossier);
    			}
    		}
    	});
		panDossierOuest.add(btnDossier);
		panDossier.add(panDossierOuest, BorderLayout.WEST);
		lblDossier = new JLabel(txtDossier);
		panDossier.add(lblDossier, BorderLayout.CENTER);
		containerHaut.add(panDossier);
		
		/*----- dossier sauvegarde -----*/
		JPanel panDossierSauv = new JPanel();
		panDossierSauv.setLayout(new BorderLayout());
		JPanel panDossierSauvOuest = new JPanel();
		panDossierSauvOuest.setLayout(new FlowLayout(FlowLayout.LEFT));
		panDossierSauvOuest.add(new JLabel("Sauvegarde :"));
		txtDossierSauv = new String(config.getElementsByTagName("sauvegarde").item(0).getTextContent());
		MonBouton btnDossierSauv = new MonBouton("icones/dossier2-32.png", "Dossier de sauvegarde de la course d'orientation");
		btnDossierSauv.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			System.out.print("Bouton Dossier d'enregistrement de sauvegarde de la course d'orientation cliqué → ");
    			String dossierSauvChoisi = btnChoixDossier(event, txtDossierSauv);
    			System.out.println(dossierSauvChoisi);
    			if(dossierSauvChoisi != "") {
    				txtDossierSauv = dossierSauvChoisi;
    				lblDossierSauv.setText(txtDossierSauv);
    			}
    		}
    	});
		panDossierSauvOuest.add(btnDossierSauv);
		panDossierSauv.add(panDossierSauvOuest, BorderLayout.WEST);
		lblDossierSauv = new JLabel(txtDossierSauv);
		panDossierSauv.add(lblDossierSauv, BorderLayout.CENTER);
		containerHaut.add(panDossierSauv);

		/*----- Modifier les balises -----*/
		JPanel panBaliseDefaut = new JPanel();
		panBaliseDefaut.add(new JLabel("Paramètre par défaut des balises :"));
		panBaliseDefaut.setLayout(new FlowLayout(FlowLayout.LEFT));
		MonBouton btnChangeBaliseDefaut = new MonBouton("icones/balise-32.png", "Modifier les caractéristiques par défaut des balises");
		btnChangeBaliseDefaut.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Modifier les balises cliqué");
    			new ChangeBaliseDefaut(null, "Balise par défaut", true);
    		}
    	});
		panBaliseDefaut.add(btnChangeBaliseDefaut);
		containerHaut.add(panBaliseDefaut);
		
		/*----- Catégories -----*/
		JPanel panCategories = new JPanel();
		panCategories.setLayout(new BorderLayout());
		Border cadreCategories = BorderFactory.createTitledBorder("Catégories" );
		panCategories.setBorder(cadreCategories);

		JPanel panCategoriesBtn = new JPanel();
		panCategoriesBtn.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		MonBouton btnAjouteCategorie =  new MonBouton("icones/ajouter-vert-32.png", "Ajouter une catégorie");
		btnAjouteCategorie.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Ajouter une catégorie cliqué");
    		}
    	});
		panCategoriesBtn.add(btnAjouteCategorie);

		btnSupprimeCategorie = new MonBouton("icones/stop-32.png", "Supprimer une catégorie");
		if(categorieSelect == ""){
			btnSupprimeCategorie.setEnabled(false);
		} else {
			btnSupprimeCategorie.setEnabled(true);
			System.out.println("Bouton Supprimer une catégorie actif");
		}
		btnSupprimeCategorie.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Supprimer une catégorie cliqué → " + tableau.getSelectedRow());
    			delCategorie(tableau.getSelectedRow());
    		}
    	});
		
		panCategoriesBtn.add(btnSupprimeCategorie);
		panCategories.add(panCategoriesBtn, BorderLayout.NORTH);
		
		JPanel panCategoriesTable = new JPanel();

		Vector<String> entetes = new Vector<String>();
		entetes.add("Nom long");
		entetes.add("Nom court");
		entetes.add("Coureurs");
		entetes.add("Filles");
		entetes.add("Garçons");
		
		Vector<Vector<String>> donnees = new Vector<Vector<String>>();
		
		NodeList nList = config.getElementsByTagName("categorie");
		 
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				Vector<String> ligne = new Vector<String>();
				ligne.add(new String(eElement
                        .getElementsByTagName("nomLong").item(0)
                        .getTextContent()));
				ligne.add(new String(eElement
                        .getElementsByTagName("nomCourt").item(0)
                        .getTextContent()));
				if(eElement.getElementsByTagName("coureurs").getLength() > 0)
					ligne.add(new String(eElement
                        .getElementsByTagName("coureurs").item(0)
                        .getTextContent()));
				if(eElement.getElementsByTagName("filles").getLength() > 0)
					ligne.add(new String(eElement
                        .getElementsByTagName("filles").item(0)
                        .getTextContent()));
				if(eElement.getElementsByTagName("garcons").getLength() > 0)
					ligne.add(new String(eElement
                        .getElementsByTagName("garcons").item(0)
                        .getTextContent()));
				donnees.add(ligne);
	            }
	        }		
		
		JPanel conteneurTableau = new JPanel();
		conteneurTableau.setLayout(new BorderLayout());
		tableau = new TableauPreferences(donnees, entetes);
		
		btnAjouteCategorie.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Ajouter une catégorie cliqué");
    			addCategorie();
    		}
    	});

		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
	            System.out.println("ligne sélectionnée → " + tableau.getSelectedRow());
	            btnSupprimeCategorie.setEnabled(true);
	        }
	    });
		
		tableau.getColumnModel().getColumn(0).setPreferredWidth(250);
		tableau.getColumnModel().getColumn(1).setPreferredWidth(50);
		XTableRenderer rend = new XTableRenderer();
		for(int i=2;i<5;i++) 
			tableau.getColumnModel().getColumn(i).setCellRenderer(rend);

		JScrollPane jpane = new JScrollPane(tableau);
		jpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		conteneurTableau.add(jpane, BorderLayout.CENTER);
		panCategories.add(conteneurTableau);
		
		/*----- Validation -----*/
		JPanel conteneurSud = new JPanel();
		
		MonBouton btnPrefOk =  new MonBouton("icones/ok-32.png", "Enregistrer ces préférences");
		btnPrefOk.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			System.out.println("Bouton Enregistrer ces préférences cliqué");
    			CellEditor c = tableau.getCellEditor();
    			if (c!=null) {
    				c.stopCellEditing();
    			}
				saveData();
    		}
    	});
		conteneurSud.add(btnPrefOk);

		MonBouton btnPrefEchap =  new MonBouton("icones/annuler-32.png", "Annuler les changements");
		btnPrefEchap.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			System.out.println("Bouton Annuler les changements cliqué");
    			setVisible(false);
    		}
    	});
		conteneurSud.add(btnPrefEchap);		
		
		container.add(containerHaut, BorderLayout.NORTH);
		container.add(panCategories, BorderLayout.CENTER);
		container.add(conteneurSud, BorderLayout.SOUTH);
		this.add(container);
		//Enfin on l'affiche
		this.setVisible(true);
		
	}
	
	public String[] getListePort() 
	{
		return new String[]{"COM1", "COM2", "COM9"};
	}
	
	public String getPortActif() 
	{
		String retour = config.getElementsByTagName("port").item(0).getTextContent();
		return retour;
	} 
	

	public String btnChoixDossier( ActionEvent evt, String dossier)
	{
		JFileChooser choix = new JFileChooser(dossier);
		choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int retour = choix.showOpenDialog(new JFrame());
		if(retour == JFileChooser.APPROVE_OPTION) {
			// un fichier a été choisi ( sortie par OK)
			return choix.getSelectedFile().getAbsolutePath();// chemin absolu du dossier choisi
		} else {
			System.out.print("Le dossier n'a pas été choisi !");
			return ""; 
		}
	}
	
	public void saveData() {
		System.out.println("\n===== Sauvegarde des préférences =====");
		config.getElementsByTagName("nom").item(0).setTextContent(this.textNom.getText());
		System.out.println("nom de l'association → " + config.getElementsByTagName("nom").item(0).getTextContent() + " - ");
		config.getElementsByTagName("port").item(0).setTextContent(textPort.getSelectedItem().toString());
		System.out.println("port → " + textPort.getSelectedItem().toString() + " - " + config.getElementsByTagName("port").item(0).getTextContent());
		config.getElementsByTagName("dossier").item(0).setTextContent(txtDossier.toString());
		System.out.println("Dossier C.O. → " + txtDossier + " - " + config.getElementsByTagName("dossier").item(0).getTextContent());
		config.getElementsByTagName("sauvegarde").item(0).setTextContent(txtDossierSauv.toString());
		System.out.println("Dossier C.O. → " + txtDossierSauv + " - " + config.getElementsByTagName("sauvegarde").item(0).getTextContent());
		ArrayList<ArrayList<String>> listeCategories = new ArrayList<ArrayList<String>>();
		System.out.println("Catégories → ");

		for (int i=0;i<config.getElementsByTagName("categorie").getLength();i++) {
			Element categorie = (Element) config.getElementsByTagName("categorie").item(i);
			categorie.getParentNode().removeChild(categorie);
		}
		
		for	(int i=0;i<tableau.getRowCount();i++) {
			Element nomLong = null;
			Element nomCourt = null;
			Element coureurs = null;
			Element filles = null;
			Element garcons = null;
			
			if(config.getElementsByTagName("categorie").getLength() > i) {
				Element CategorieActive = (Element) config.getElementsByTagName("categorie").item(i);
				nomLong = (Element) CategorieActive.getElementsByTagName("nomLong").item(0);
				nomCourt = (Element) CategorieActive.getElementsByTagName("nomCourt").item(0);
				if(CategorieActive.getElementsByTagName("coureurs").getLength() > 0) {
					coureurs = (Element) CategorieActive.getElementsByTagName("coureurs").item(0);
				} else {
					coureurs = (Element) CategorieActive.appendChild(config.createElement("coureurs"));
				}	
				if(CategorieActive.getElementsByTagName("filles").getLength() > 0) {
					filles = (Element) CategorieActive.getElementsByTagName("filles").item(0);
				} else {
					filles = (Element) CategorieActive.appendChild(config.createElement("filles"));
				}	
				if(CategorieActive.getElementsByTagName("garcons").getLength() > 0) {
					garcons = (Element) CategorieActive.getElementsByTagName("garcons").item(0);
				} else {
					garcons = (Element) CategorieActive.appendChild(config.createElement("garcons"));	
				}
			} else {
				Element Categories = (Element) config.getElementsByTagName("categories").item(0);
				Element CategorieActive = (Element) Categories.appendChild(config.createElement("categorie"));
				nomLong = (Element) CategorieActive.appendChild(config.createElement("nomLong"));
				nomCourt = (Element) CategorieActive.appendChild(config.createElement("nomCourt"));
				coureurs = (Element) CategorieActive.appendChild(config.createElement("coureurs"));
				filles = (Element) CategorieActive.appendChild(config.createElement("filles"));
				garcons = (Element) CategorieActive.appendChild(config.createElement("garcons"));
			}
			
			nomLong.setTextContent(tableau.getValueAt(i,0).toString());
			nomCourt.setTextContent(tableau.getValueAt(i,1).toString());
			if(tableau.getValueAt(i,2) != null) {
				System.out.println("Lignes → " + i + " Colonnes → " + tableau.getColumnCount() + " Contenu → " + tableau.getValueAt(i,2));
				coureurs.setTextContent(Integer.toString(Integer.parseInt(tableau.getValueAt(i,2).toString())));
			} else {
				coureurs.setTextContent("0");
			}
			if(tableau.getValueAt(i,3) != null) {
				filles.setTextContent(Integer.toString(Integer.parseInt(tableau.getValueAt(i,3).toString())));
			} else {
				filles.setTextContent("0");
			}
			if(tableau.getValueAt(i,4) != null) {
				garcons.setTextContent(Integer.toString(Integer.parseInt(tableau.getValueAt(i,4).toString())));
			} else {
				garcons.setTextContent("0");
			}
			
			ArrayList<String> l= new ArrayList<String>();
			l.add(tableau.getValueAt(i,0).toString());
			System.out.println("\t" + tableau.getValueAt(i,0) + " → " + tableau.getValueAt(i,1));
			l.add(tableau.getValueAt(i,1).toString());
			listeCategories.add(i,l);			
		}
		
            enregistreConfig(config);
		
		setVisible(false);
	}
	
	public void delCategorie(int numCategorie) {
		System.out.println("Catégories à supprimer → " + numCategorie + " - " + tableau.getValueAt(1,0));
		DefaultTableModel model = (DefaultTableModel) tableau.getModel();
		model.removeRow(numCategorie);
		tableau.revalidate();
		btnSupprimeCategorie.setEnabled(false);
	}
	
	public void addCategorie() {
		DefaultTableModel model = (DefaultTableModel) tableau.getModel();
		config.createElement("nomLong");
		config.createElement("nomCourt");
		
		Vector<String> ligne = new Vector<String>();
		ligne.add("");
		ligne.add("");
		
		model.addRow(ligne);
		tableau.revalidate();
	}
	
}
