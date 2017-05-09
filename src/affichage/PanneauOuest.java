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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class PanneauOuest extends JPanel {
	
	private PanneauNord panNord = null;
	
	private JPanel equipes = new JPanel();
	int nbEquipes = 50;
	
	private MonBouton btnLirePuce = new MonBouton("icones/lance-32.png", "Connecter la station mère");
	private MonBouton btnConnecteStation = new MonBouton("icones/stopOrange-32.png", "Recherche de la station mère");
	private MonBouton btnCherchePuce = new MonBouton("icones/stopRed-32.png", "Lecture des puces");
	
	public PanneauOuest() {
		
		Border cadreCourse = BorderFactory.createTitledBorder("Équipes" );
		this.setBorder(cadreCourse);
		
		this.setLayout(new BorderLayout());

		// début de la première ligne
		JPanel panBoutons = new JPanel();
		
		JLabel lbEquipes = new JLabel(nbEquipes + " Équipes : ");
		panBoutons.add(lbEquipes);

		MonBouton btnAjouteEquipe =  new MonBouton("icones/ajouter-vert-32.png", "Ajouter une équipe");
		btnAjouteEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Ajouter une équipe cliqué");
    		}
    	});
		panBoutons.add(btnAjouteEquipe);

		MonBouton btnModifieEquipe =  new MonBouton("icones/recherche-32.png", "Modifier une équipe");
		btnModifieEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Modifier une équipe cliqué");
    		}
    	});
		panBoutons.add(btnModifieEquipe);

		MonBouton btnSupprimeEquipe =  new MonBouton("icones/stop-32.png", "Supprimer une équipe");
		btnSupprimeEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Supprimer une équipe cliqué");
    		}
    	});
		panBoutons.add(btnSupprimeEquipe);

		MonBouton btnDeplEquipe =  new MonBouton("icones/fleche-32.png", "Déplacer vers un autre parcours");
		btnDeplEquipe.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Déplacer une équipe cliqué");
    		}
    	});
		panBoutons.add(btnDeplEquipe);

		panBoutons.add(new JLabel(" "));

		MonBouton btNumDossards =  new MonBouton("icones/dossard-32.png", "Numéroter les dossards");
		btNumDossards.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Numéroter les dossards cliqué");
    		}
    	});
		panBoutons.add(btNumDossards);

		MonBouton btImportFichier =  new MonBouton("icones/fileimport-32.png", "Importer un fichier CSV");
		btImportFichier.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Importer un fichier CSV cliqué");
    		}
    	});
		panBoutons.add(btImportFichier);

		MonBouton btExportFichier =  new MonBouton("icones/usb-32.png", "Exporter au format CSV");
		btExportFichier.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Exporter vers un fichier CSV cliqué");
    		}
    	});
		panBoutons.add(btExportFichier);

		panBoutons.add(new JLabel(" Auto-puce :"));
		
	    btnConnecteStation.setVisible(false);
	    btnCherchePuce.setVisible(false);
	    btnLirePuce.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Connecter la station mère cliqué");
    			btnLirePuce.setVisible(false);
    			btnConnecteStation.setVisible(true);
    			panNord.activeBtnPuce(false);
    		}
    	});
	    btnConnecteStation.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Recherche de la station mère cliqué");
    			btnConnecteStation.setVisible(false);
    			btnCherchePuce.setVisible(true);
    			panNord.activeBtnPuce(false);
    		}
    	});
	    btnCherchePuce.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Lecture des puces cliqué");
    			btnCherchePuce.setVisible(false);
    			btnLirePuce.setVisible(true);
    			panNord.activeBtnPuce(true);
    		}
    	});
	    
	    panBoutons.add(btnLirePuce);
	    panBoutons.add(btnConnecteStation);
	    panBoutons.add(btnCherchePuce);

	    this.add(panBoutons, BorderLayout.NORTH);
	    
		// fin de la première ligne
	    
		
		
		equipes.setBackground(Color.WHITE);
		
		equipes.add(new JLabel(Integer.toString(nbEquipes) + " équipes"));
		
		equipes.setLayout(new BoxLayout(equipes, BoxLayout.PAGE_AXIS));
		
		JScrollPane fenEquipes = new JScrollPane(equipes);
		this.add(fenEquipes, BorderLayout.CENTER);
		
		int iMax = nbEquipes;
		for(int i=0;i<=iMax;i++){
			equipes.add(new JLabel("Équipe " + i));
		}
		
		fenEquipes.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
	}

	public void activeBtnPuce(boolean actif) {
		btnLirePuce.setEnabled(actif);
	}
	
	public void addPanneauNord(PanneauNord panoNord) {
		panNord = panoNord;
	}

}
