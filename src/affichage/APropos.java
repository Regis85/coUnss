package affichage;

import javax.swing.JOptionPane;

public class APropos {
	
	public APropos() {
		String message = "COunss est un logiciel de gestion des courses d'orientation au format UNSS\n"
				+ "Développeur : Bouguin Régis\n"
				+ "Licence GPL 3.0\n"
				+ "version 0.0.1",
				titre = "À propos de";
		
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE);
	}
	
}
