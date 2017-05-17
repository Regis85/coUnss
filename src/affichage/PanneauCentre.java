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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import lib.MonBouton;

public class PanneauCentre extends JPanel {

	private JPanel pnCentreHaut = new JPanel();
	private JPanel pnCompetition = new JPanel();
	private JPanel pnCategorie = new JPanel();
	private JPanel pnBtnBalises = new JPanel();
	private JPanel pnDescriptionBalise = new JPanel();
	
	private JComboBox<String> jcbCompetition = new JComboBox<String>();
	private JComboBox<String> jcbCategories = new JComboBox<String>();

	private Dimension dimComboBox = new Dimension(300,24);

	private Vector<String> listeCategorie = new Vector<String>();
	private Vector<String> listeRencontre= new Vector<String>();
	
	private int nbBaliseAffiche = 2;
	private JLabel afficheNbBalises = new JLabel(Integer.toString(nbBaliseAffiche));
	
	

	public PanneauCentre() {
		
		super();
		
		chargeRencontre();
		chargeCategorie();
		chargeEpreuves();

		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setLayout(new BorderLayout());
		
		/*===== cadres fixes =====*/
		pnCentreHaut.setLayout(new BoxLayout(pnCentreHaut, BoxLayout.PAGE_AXIS));
		
		/*----- Rencontre -----*/
		
		pnCompetition.setLayout(new FlowLayout(FlowLayout.LEFT));
		Border cadreCourse = BorderFactory.createTitledBorder("Rencontre");
		pnCompetition.setBorder(cadreCourse);
		pnCompetition.add(jcbCompetition);
		jcbCompetition.setPreferredSize(dimComboBox);
		for(String  currentRencontre : listeRencontre){
			jcbCompetition.addItem(currentRencontre);
		}
		pnCentreHaut.add(pnCompetition);
		
		MonBouton btnAjouteCompet =  new MonBouton("icones/ajouter-vert-32.png", "Ajouter une rencontre");
		btnAjouteCompet.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Ajouter une rencontre cliqué");
    		}
    	});
		pnCompetition.add(btnAjouteCompet);

		MonBouton btnModifieCompet =  new MonBouton("icones/recherche-32.png", "Modifier une rencontre");
		btnModifieCompet.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Modifier une rencontre cliqué");
    		}
    	});
		pnCompetition.add(btnModifieCompet);

		MonBouton btnSupprimeCompet =  new MonBouton("icones/stop-32.png", "Supprimer une rencontre");
		btnSupprimeCompet.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Supprimer une rencontre cliqué");
    		}
    	});
		pnCompetition.add(btnSupprimeCompet);

		MonBouton btnDuppliqueCompet =  new MonBouton("icones/dossier-32.png", "Dupliquer une rencontre");
		btnDuppliqueCompet.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Dupliquer une rencontre cliqué");
    		}
    	});
		pnCompetition.add(btnDuppliqueCompet);
		
		selectionneRencontre(1);
		
		/*----- Catégories -----*/
		
		pnCategorie.setLayout(new FlowLayout(FlowLayout.LEFT));
		Border cadreCategorie = BorderFactory.createTitledBorder("Catégorie");
		pnCategorie.setBorder(cadreCategorie);
		pnCategorie.add(jcbCategories);
		jcbCategories.setPreferredSize(dimComboBox);
		for(String  currentCategorie : listeCategorie){
			jcbCategories.addItem(currentCategorie);
		}
		
		
		
		pnCentreHaut.add(pnCategorie);
		
		MonBouton btnAjouteCategorie =  new MonBouton("icones/ajouter-vert-32.png", "Ajouter une catégorie");
		btnAjouteCategorie.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Ajouter une catégorie cliqué");
    		}
    	});
		pnCategorie.add(btnAjouteCategorie);

		MonBouton btnModifieCategorie =  new MonBouton("icones/recherche-32.png", "Modifier une catégorie");
		btnModifieCategorie.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Modifier une catégorie cliqué");
    		}
    	});
		pnCategorie.add(btnModifieCategorie);

		MonBouton btnSupprimeCategorie =  new MonBouton("icones/stop-32.png", "Supprimer une catégorie");
		btnSupprimeCategorie.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Supprimer une catégorie cliqué");
    		}
    	});
		pnCategorie.add(btnSupprimeCategorie);
		
		selectionneCategorie(1);
		
		
		/*----- Épreuve -----*/
		
		JPanel pnEpreuve = new JPanel();
		Border cadreEpreuve = BorderFactory.createTitledBorder("Épreuve");
		pnEpreuve.setLayout(new BorderLayout());
		pnEpreuve.setBorder(cadreEpreuve);
				
		JPanel pnEpreuveContenu = new JPanel();
		pnEpreuveContenu.setLayout(new BoxLayout(pnEpreuveContenu, BoxLayout.LINE_AXIS));
		pnEpreuve.add(pnEpreuveContenu, BorderLayout.WEST);

		JPanel pnEpreuveGauche = new JPanel();
		JPanel pnEpreuveGauche1 = new JPanel();
		pnEpreuveGauche.setLayout(new BoxLayout(pnEpreuveGauche, BoxLayout.PAGE_AXIS));	
		pnEpreuveGauche1.add(pnEpreuveGauche);
		pnEpreuveContenu.add(pnEpreuveGauche1);
		
		MonBouton btnMonteEquipe =  new MonBouton("icones/vers-haut-32.png", "Monter une épreuve");
		btnMonteEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Monter une épreuve cliqué");
    		}
    	});
		pnEpreuveGauche.add(btnMonteEquipe);
		
		MonBouton btnDescendEquipe =  new MonBouton("icones/vers-bas-32.png", "Descendre une épreuve");
		btnDescendEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Descendre une épreuve cliqué");
    		}
    	});
		pnEpreuveGauche.add(btnDescendEquipe);
				
		JPanel pnEpreuvecentre = new JPanel();
		pnEpreuvecentre.setLayout(new BoxLayout(pnEpreuvecentre, BoxLayout.PAGE_AXIS));
		//pnEpreuvecentre.add(new JLabel("Boite centrale"));
		pnEpreuvecentre.setBackground(Color.WHITE);
		JScrollPane fenEpreuves = new JScrollPane(pnEpreuvecentre);
		fenEpreuves.setPreferredSize(new Dimension(260,80));
		chargeEpreuves();
		/*
		int iMax = 15;
		for(int i=0;i<=iMax;i++){
			pnEpreuvecentre.add(new JLabel("Épreuve " + i));
		}
		*/
		
		pnEpreuveContenu.add(fenEpreuves);
		
		JPanel pnEpreuveDroit = new JPanel();
		pnEpreuveDroit.setPreferredSize(new Dimension(140,80));
		MonBouton btnAjouteEpreuve =  new MonBouton("icones/ajouter-vert-32.png", "Ajouter une épreuve");
		btnAjouteEpreuve.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Ajouter une épreuve cliqué");
    		}
    	});
		pnEpreuveDroit.add(btnAjouteEpreuve);

		MonBouton btnModifieEpreuve =  new MonBouton("icones/recherche-32.png", "Modifier une épreuve");
		btnModifieEpreuve.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Modifier une épreuve cliqué");
    		}
    	});
		pnEpreuveDroit.add(btnModifieEpreuve);

		MonBouton btnSupprimeEquipe =  new MonBouton("icones/stop-32.png", "Supprimer une épreuve");
		btnSupprimeEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Supprimer une épreuve cliqué");
    		}
    	});
		pnEpreuveDroit.add(btnSupprimeEquipe);

		MonBouton btImportEquipe =  new MonBouton("icones/fileimport-32.png", "Importer un fichier CSV");
		btImportEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Importer un fichier d'épreuves en CSV cliqué");
    		}
    	});
		pnEpreuveDroit.add(btImportEquipe);

		MonBouton btExportEquipe =  new MonBouton("icones/usb-32.png", "Exporter au format CSV");
		btExportEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Exporter vers un fichier d'épreuves en CSV cliqué");
    		}
    	});
		pnEpreuveDroit.add(btExportEquipe);
		
		pnEpreuveContenu.add(pnEpreuveDroit);		
		
		pnCentreHaut.add(pnEpreuve);
		this.add(pnCentreHaut, BorderLayout.NORTH);
		
		/*===== cadre ajustable =====*/
		JPanel pnBalises = new JPanel();
		Border cadreBalises = BorderFactory.createTitledBorder("Balises");
		pnBalises.setBorder(cadreBalises);
		pnBalises.setLayout(new BorderLayout());

		//----- Menu des balises -----//
		JPanel pnTitreBalises = new JPanel();
		pnTitreBalises.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnBalises.add(pnTitreBalises, BorderLayout.NORTH);
		afficheNbBalises = new JLabel(Integer.toString(nbBaliseAffiche));
		pnTitreBalises.add(afficheNbBalises);
		pnTitreBalises.add(new JLabel(" balises :"));
		
		MonBouton btnAjouteBalise =  new MonBouton("icones/ajouter-vert-32.png", "Ajouter une balise");
		btnAjouteBalise.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			ajouteBalise(1);
    		}
    	});
		pnTitreBalises.add(btnAjouteBalise);

		MonBouton btnModifieBalise =  new MonBouton("icones/recherche-32.png", "Modifier une balise");
		btnModifieBalise.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Modifier une balise cliqué");
    		}
    	});
		pnTitreBalises.add(btnModifieBalise);

		MonBouton btnSupprimeBalise =  new MonBouton("icones/stop-32.png", "Supprimer une balise");
		btnSupprimeBalise.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Supprimer une balise cliqué");
    		}
    	});
		pnTitreBalises.add(btnSupprimeBalise);
		
		//----- Panneau des balises -----//

		pnDescriptionBalise.setLayout(new BorderLayout());
		
		pnBtnBalises.setLayout(new BoxLayout(pnBtnBalises, BoxLayout.PAGE_AXIS));
		JPanel pnEncadreBoutons = new JPanel();

		MonBouton btnMonteBalise =  new MonBouton("icones/vers-haut-32.png", "Monter une balise");
		btnMonteBalise.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Monter une balise cliqué");
    		}
    	});
		pnBtnBalises.add(btnMonteBalise);
		
		MonBouton btnDescendBalise =  new MonBouton("icones/vers-bas-32.png", "Descendre une balise");
		btnDescendBalise.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Descendre une balise cliqué");
    		}
    	});
		pnBtnBalises.add(btnDescendBalise);
		pnEncadreBoutons.add(pnBtnBalises);
		pnBalises.add(pnEncadreBoutons, BorderLayout.WEST);
		pnDescriptionBalise.setLayout(new BoxLayout(pnDescriptionBalise, BoxLayout.PAGE_AXIS));
		pnDescriptionBalise.setBackground(Color.WHITE);
		

		JScrollPane fenBalises = new JScrollPane(pnDescriptionBalise);
		pnBalises.add(fenBalises, BorderLayout.CENTER);
		
		//pnBalises.add(pnDescriptionBalise, BorderLayout.CENTER);
		chargeBalise();
		
		this.add(pnBalises, BorderLayout.CENTER);
		
	}
	
	private void chargeRencontre() {
		/*
		listeRencontre.addElement("Championnat départemental");
		listeRencontre.addElement("Championnat académique");
		*/
	}
	private void selectionneRencontre(int i) {
		//jcbCompetition.setSelectedIndex(i);
	}
	
	private void chargeCategorie() {
		/*
		listeCategorie.addElement("Collège");
		listeCategorie.addElement("Lycée");
		*/
	}
	
	private void chargeEpreuves() {
		
	}

	private void selectionneCategorie(int i) {
		//jcbCategories.setSelectedIndex(i);
	}
	
	private void ajouteBalise(int nbBalisesAjout) {
		nbBaliseAffiche ++;
		this.afficheNbBalises.setText(Integer.toString(nbBaliseAffiche));
		System.out.println("Bouton Ajouter une balise cliqué " + nbBaliseAffiche);
	}
	
	private void chargeBalise() {
		System.out.println("On charge les balises");
		/*
		for(int i=1;i<=30;i++) {
			this.pnDescriptionBalise.add(new JLabel("Balises " + i));
		}
		*/
	}
	
}
