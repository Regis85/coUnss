package affichage;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class PanneauNord extends JPanel {
	

	private boolean sauveAuto = false;

	private JComboNombre jcbMnSauve = new JComboNombre(1, 59);
	private int mnSauvegarde = 1;

	private JComboNombre jcbHrDepart = new JComboNombre(0, 23);
	private int heureDebut = 0;
	
	private JComboNombre jcbMnDepart = new JComboNombre(0, 59);
	private int minuteDebut = 0;

	private MonBouton btnLirePuce = new MonBouton("icones/lance-32.png", "Connecter la station mère");
	private MonBouton btnConnecteStation = new MonBouton("icones/stopOrange-32.png", "Recherche de la station mère");
	private MonBouton btnCherchePuce = new MonBouton("icones/stopRed-32.png", "Lecture des puces");
    
/*
	public PanneauNord() {
		
	}
	*/
	public PanneauNord(PanneauOuest panWest ) {

		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
	
		JPanel bgNord1 = new JPanel();
		JPanel bgNord2 = new JPanel();
	
		MonBouton btnEnregistre =  new MonBouton("icones/save-32.png", "Enregistrer la C.O.");
		btnEnregistre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("Bouton Enregistrer la C.O. cliqué");
			}
		});
	    
	    MonBouton btnSauve = new MonBouton("icones/sauvegarde-32.png", "Sauvegarder la Course");
	    btnSauve.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("Sauvegarder la Course");
			}
		});
	    
	    jcbMnSauve.addActionListener(new TpsListener());
	    JLabel lbDecompte = new JLabel("mn");
	
	    MonBouton btnSauveAuto = new MonBouton("icones/fleche-32.png", "Lancer la sauvegarde automatique");
	    MonBouton btnStopAuto = new MonBouton("icones/pauseB-32.png", "Arrêter la sauvegarde automatique");
	    btnSauveAuto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("Bouton Lancé la sauvegarde cliqué " + mnSauvegarde + "mn");
				sauveAuto = true;
				btnSauveAuto.setVisible(false);
				btnStopAuto.setVisible(true);
			}
		});
	
	    btnStopAuto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("Bouton Arrêter la sauvegarde cliqué");
				sauveAuto = false;
				btnSauveAuto.setVisible(true);
				btnStopAuto.setVisible(false);
			}
		});
	
	    Border cadreCourse = BorderFactory.createTitledBorder("COunss" );
	    bgNord1.setBorder(cadreCourse);
	
	    bgNord1.add(btnEnregistre);
	    bgNord1.add(btnSauve);
	    bgNord1.add(jcbMnSauve);
	    bgNord1.add(lbDecompte);
	    bgNord1.add(btnSauveAuto);
	    bgNord1.add(btnStopAuto);
	    
	    if(sauveAuto) {
	    	btnSauveAuto.setVisible(false);
	    	btnStopAuto.setVisible(true);
	    } else {
	    	btnSauveAuto.setVisible(true);
	    	btnStopAuto.setVisible(false);
	    }
	    
	    Border cadreHeure = BorderFactory.createTitledBorder("Heure zéro" );
	    bgNord2.setBorder(cadreHeure);
	    
	    jcbHrDepart.addActionListener(new HrDepartListener());
	    JLabel lbHDepart = new JLabel("h");
	    jcbMnDepart.addActionListener(new MnDepartListener());
	    JLabel lbMnDepart = new JLabel("mn");
	    JLabel lbLitPuce = new JLabel("Lecture des puces");
	    
	    btnConnecteStation.setVisible(false);
	    btnCherchePuce.setVisible(false);
	    btnLirePuce.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("Bouton Lecture des puces cliqué");
				btnLirePuce.setVisible(false);
				btnConnecteStation.setVisible(true);
				jcbHrDepart.setEnabled(false);
				jcbMnDepart.setEnabled(false);
				panWest.activeBtnPuce(false);
			}
		});
	    btnConnecteStation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("Bouton Connecter la station mère cliqué");
				btnConnecteStation.setVisible(false);
				btnCherchePuce.setVisible(true);
				panWest.activeBtnPuce(false);
			}
		});
	    btnCherchePuce.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("Bouton Connecter la station mère cliqué");
				btnCherchePuce.setVisible(false);
				btnLirePuce.setVisible(true);
				jcbHrDepart.setEnabled(true);
				jcbMnDepart.setEnabled(true);
				panWest.activeBtnPuce(true);
			}
		});
	
	    bgNord2.add(jcbHrDepart);
	    bgNord2.add(lbHDepart);
	    bgNord2.add(jcbMnDepart);
	    bgNord2.add(lbMnDepart);
	    bgNord2.add(new JLabel(" "));
	    bgNord2.add(lbLitPuce);
	    bgNord2.add(btnLirePuce);
	    bgNord2.add(btnConnecteStation);
	    bgNord2.add(btnCherchePuce);
	    
	    this.add(bgNord1);
	    this.add(bgNord2);
	}
	
	public void activeBtnPuce(boolean actif) {
		btnLirePuce.setEnabled(actif);
	}


	class TpsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mnSauvegarde = Integer.parseInt(jcbMnSauve.getSelectedItem().toString());
			System.out.println("sauvegarde toutes les : " + jcbMnSauve.getSelectedItem().toString());
		}
	}
	
	class HrDepartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			heureDebut = Integer.parseInt(jcbHrDepart.getSelectedItem().toString());
			System.out.println("Heure de début de la course " + heureDebut + ":" + minuteDebut);
		}
	}
	
	class MnDepartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			minuteDebut = Integer.parseInt(jcbMnDepart.getSelectedItem().toString());
			System.out.println("Heure de début de la course " + heureDebut + ":" + minuteDebut);
		}
	}
	
	
}
