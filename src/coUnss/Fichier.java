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
