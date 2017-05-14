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

import javax.swing.JFileChooser;

public class Fichier {
	
	private String nomCheminFichier;
	private String nomChemin;
	private String nomFichier;
	
	public Fichier() {
		
		if(nomCheminFichier == null) {
			creeFichier();
		} else {
			System.out.println("ouverture d'un fichier");
		}
		
		
		
		
	}
	
	public void creeFichier() {
		System.out.println("création d'un fichier");
		
		
		
	}
	
	public void enregistrerFichier() {
		enregistrerFichier("");
	}
	
	public void enregistrerFichier(String chemin) {
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
			String chemin = filechoose.getSelectedFile().getAbsolutePath(); /* pour avoir le chemin absolu */
		    /* ici il faut appeler une méthode pour écrire dans un fichier*/
			enregistrerFichier(chemin);
		}		
	}

}
