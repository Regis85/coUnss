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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import coUnss.Fichier;
import dialogues.PrefDialogue;

public class Fenetre extends JFrame {
	
	private JPanel container = new JPanel();
	
	private JMenuBar menuBar = new JMenuBar();

	private JMenu fichier = new JMenu("Fichier") ;
	private JMenu courses = new JMenu("C.O.");
	private JMenu preference = new JMenu("Préférence");
	private JMenu help = new JMenu("?");
	
	private JMenuItem nouvelle = new JMenuItem("Nouveau"),
			ouvrir = new JMenuItem("Ouvrir"), 
			enregistre = new JMenuItem("Enregistrer"),
			enregistreSous = new JMenuItem("Enregistrer sous …"),
			sauvegarde = new JMenuItem("Sauvegarder"),
			quitter = new JMenuItem("Quitter");

	private JMenuItem parametreCO = new JMenuItem("Paramètres"),
			visualise = new JMenuItem("Visualiser les parcours"),
			puce = new JMenuItem("Rechercher une puce"),
			penalite = new JMenuItem("Gestion des pénalités");
	
	private JMenuItem parametres = new JMenuItem("Paramètres");

	private JMenuItem aide = new JMenuItem("Aide");
	private JMenuItem aPropos = new JMenuItem("À propos de");
	private JMenuItem licence = new JMenuItem("Licence");
	

	private PanneauOuest panWest = new PanneauOuest();
	private PanneauNord panNord = new PanneauNord(panWest);
	private PanneauCentre parcours = new PanneauCentre();
	private PanneauSud panSud = new PanneauSud();

	private Fichier donneesCourse = new Fichier();
	
	public Fenetre() {		
		
		this.setTitle("Course d'orientation UNSS 2");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	    this.initMenu();
		this.setJMenuBar(menuBar);
		
		this.panWest.addPanneauNord(panNord);
		
	    container.setLayout(new BorderLayout());
		
		container.add(panNord);  

		this.setContentPane(container);
	    
		container.add(panNord, BorderLayout.NORTH);
		container.add(panWest, BorderLayout.WEST);
		container.add(parcours, BorderLayout.CENTER);
		container.add(panSud, BorderLayout.SOUTH);
		
		this.setVisible(true); 
		
	}
	
	private void initMenu() {
	    //Menu Fichier
		java.net.URL imageURL = this.getClass().getResource("nouveau.gif");
		System.out.println(imageURL);
		
		nouvelle.setIcon(new ImageIcon("icones/nouveau.png"));
		nouvelle.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  donneesCourse = new Fichier();
		      }
		    });
		fichier.add(nouvelle);
		
		ouvrir.setIcon(new ImageIcon("icones/ouvrir.png"));
		fichier.add(ouvrir);
		
		enregistre.setIcon(new ImageIcon("icones/save.png"));
		enregistre.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  donneesCourse.enregistrerFichier();
		      }
		    });
		fichier.add(enregistre);
		
		enregistreSous.setIcon(new ImageIcon("icones/saveAs.png"));	
		enregistreSous.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  donneesCourse.enregistreSousFichier();
		      }
		    });	
		fichier.add(enregistreSous);
		
		sauvegarde.setIcon(new ImageIcon("icones/sauvegarde.png"));
		fichier.add(sauvegarde);
		
		fichier.addSeparator();
		
		quitter.setIcon(new ImageIcon("icones/quitter.png"));
		quitter.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  quitter();
		      }
		    });
		fichier.setIcon(new ImageIcon("icones/fichier.png"));
		fichier.add(quitter);
		
		menuBar.add(fichier);
		
		// Menu Course
		parametreCO.setIcon(new ImageIcon("icones/preferences.png"));
		courses.add(parametreCO);
		visualise.setIcon(new ImageIcon("icones/orientation.png"));
		courses.add(visualise);
		puce.setIcon(new ImageIcon("icones/recherche.png"));
		courses.add(puce);
		penalite.setIcon(new ImageIcon("icones/erreur.png"));
		courses.add(penalite);
		courses.setIcon(new ImageIcon("icones/co.png"));
		menuBar.add(courses);
		
		// Menu Préférence
		parametres.setIcon(new ImageIcon("icones/preferences.png"));
		parametres.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				//saisiePref();
				System.out.println("affichage de la boite de dialogue de saisie des préférences");
				PrefDialogue saisiePref1 = new PrefDialogue(null, "Paramètres généraux", true);
			}
		});	
		preference.setIcon(new ImageIcon("icones/preferences.png"));
		preference.add(parametres);
		menuBar.add(preference);
		
		// Menu Aide
		help.add(aide);
		aPropos.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  APropos fenHelp = new APropos();
		      }
		    });
		help.add(aPropos);
		licence.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  Licence fenAPropos = new Licence();
		      }
		    });
		help.add(licence);
		menuBar.add(help);
	}

	private void saisiePref() {
		System.out.println("affichage de la boite de dialogue de saisie des préférences");
		PrefDialogue saisiePref = new PrefDialogue(null, "Coucou les ZérOs", true);
	}

	private void quitter() {
		System.exit(0);
	}
	
	/* mettre tout ça dans la classe du fichier */

}
