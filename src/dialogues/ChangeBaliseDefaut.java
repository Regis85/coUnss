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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import affichage.JComboNombre;
import lib.MonBouton;
import lib.SaveFichierXML;

public class ChangeBaliseDefaut extends JDialog implements SaveFichierXML {

	private Document balise;
	
	private JRadioButton jr1 = new JRadioButton("Bonification");
	private JRadioButton jr2 = new JRadioButton("Pénalité");

	private JRadioButton jr1PM= new JRadioButton("Bonification");
	private JRadioButton jr2PM = new JRadioButton("Pénalité");

	private JComboNombre jcbPtsBonif = new JComboNombre(0, 1000);
	private JComboNombre jcbHBonif = new JComboNombre(0, 23);
	private JComboNombre jcbMnBonif = new JComboNombre(0, 59);
	private JComboNombre jcbSBonif = new JComboNombre(0, 59);

	private JComboNombre jcbPtsPenal = new JComboNombre(0, 1000);
	private JComboNombre jcbHPenal = new JComboNombre(0, 23);
	private JComboNombre jcbMnPenal = new JComboNombre(0, 59);
	private JComboNombre jcbSPenal = new JComboNombre(0, 59);

	//private ButtonGroup bg = new ButtonGroup();

	public ChangeBaliseDefaut(JFrame parent, String title, boolean modal){

		//On appelle le construteur de JDialog correspondant
		super(parent, title, modal);
		//On spécifie une taille
		this.setSize(300, 350);
		//La position
		this.setLocationRelativeTo(null);
		//La boîte ne devra pas être redimensionnable
		this.setResizable(false);
		
		getBaliseDefaut();
		
		System.out.println("Réglages par défaut des balises");
		
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		
		/*===== Panneau haut =====*/
		JPanel pnHaut = new JPanel();
		pnHaut.setLayout(new BoxLayout(pnHaut, BoxLayout.PAGE_AXIS));
		container.add(pnHaut, BorderLayout.NORTH);

		/*----- Si découvert -----*/
		JPanel pnDecouvert = new JPanel();
		pnDecouvert.setLayout(new BoxLayout(pnDecouvert, BoxLayout.PAGE_AXIS));
		Border cadreCategories = BorderFactory.createTitledBorder("Si découvert" );
		pnDecouvert.setBorder(cadreCategories);
		JPanel pnDcv1 = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		bg.add(jr1);
		bg.add(jr2);
		if(balise.getElementsByTagName("trouvee").getLength() > 0) {
			Element trouvee = (Element) balise.getElementsByTagName("trouvee").item(0);
			if(Integer.parseInt(trouvee.getElementsByTagName("bonif").item(0).getTextContent().toString()) == 1) {
				jr1.setSelected(true);
				jr2.setSelected(false);
			} else {
				jr1.setSelected(false);
				jr2.setSelected(true);
			}
			jcbPtsBonif.setSelectedItem(Integer.parseInt(trouvee.getElementsByTagName("points").item(0).getTextContent().toString()));
			jcbHBonif.setSelectedItem(getHeure(Integer.parseInt(trouvee.getElementsByTagName("temps").item(0).getTextContent().toString()))); 
			jcbMnBonif.setSelectedItem(getMinutes(Integer.parseInt(trouvee.getElementsByTagName("temps").item(0).getTextContent().toString()))); 
			jcbSBonif.setSelectedItem(getSecondes(Integer.parseInt(trouvee.getElementsByTagName("temps").item(0).getTextContent().toString()))); 
			
		}
		pnDcv1.add(jr1);
		pnDcv1.add(jr2);
		pnDecouvert.add(pnDcv1);

		JPanel pnDcv2 = new JPanel();
		pnDcv2.add(new JLabel("Points :"));
		jcbPtsBonif.setPreferredSize(new Dimension(60,17));
		pnDcv2.add(jcbPtsBonif);
		pnDecouvert.add(pnDcv2);

		JPanel pnDcv3 = new JPanel();
		pnDcv3.add(new JLabel("Temps :"));
		pnDcv3.add(jcbHBonif);
		pnDcv3.add(new JLabel("H"));
		pnDcv3.add(jcbMnBonif);
		pnDcv3.add(new JLabel("mn"));
		pnDcv3.add(jcbSBonif);
		pnDcv3.add(new JLabel("s"));
		
		pnDecouvert.add(pnDcv3);
		
		pnHaut.add(pnDecouvert);

		/*----- Si poste manquant -----*/
		JPanel pnPenalite = new JPanel();
		pnPenalite.setLayout(new BoxLayout(pnPenalite, BoxLayout.PAGE_AXIS));
		Border cadrePenalite = BorderFactory.createTitledBorder("Si poste manquant" );
		pnPenalite.setBorder(cadrePenalite);
		JPanel pnPM1 = new JPanel();
		ButtonGroup bgPM = new ButtonGroup();
		bgPM.add(jr1PM);
		bgPM.add(jr2PM);
		
		
		
		if(balise.getElementsByTagName("pm").getLength() > 0) {
			Element posteManquant = (Element) balise.getElementsByTagName("pm").item(0);
			if(Integer.parseInt(posteManquant.getElementsByTagName("bonif").item(0).getTextContent().toString()) == 1) {
				jr1PM.setSelected(true);
				jr2PM.setSelected(false);
			} else {
				jr1PM.setSelected(false);
				jr2PM.setSelected(true);
			}
			jcbPtsPenal.setSelectedItem(Integer.parseInt(posteManquant.getElementsByTagName("points").item(0).getTextContent().toString()));
			jcbHPenal.setSelectedItem(getHeure(Integer.parseInt(posteManquant.getElementsByTagName("temps").item(0).getTextContent().toString()))); 
			jcbMnPenal.setSelectedItem(getMinutes(Integer.parseInt(posteManquant.getElementsByTagName("temps").item(0).getTextContent().toString()))); 
			jcbSPenal.setSelectedItem(getSecondes(Integer.parseInt(posteManquant.getElementsByTagName("temps").item(0).getTextContent().toString()))); 
			
		}
		
		
		
		pnPM1.add(jr1PM);
		pnPM1.add(jr2PM);
		pnPenalite.add(pnPM1);

		JPanel pnPM2 = new JPanel();
		pnPM2.add(new JLabel("Points :"));
		jcbPtsPenal.setPreferredSize(new Dimension(60,17));
		pnPM2.add(jcbPtsPenal);
		pnPenalite.add(pnPM2);

		JPanel pnPM3 = new JPanel();
		pnPM3.add(new JLabel("Temps :"));
		pnPM3.add(jcbHPenal);
		pnPM3.add(new JLabel("H"));
		pnPM3.add(jcbMnPenal);
		pnPM3.add(new JLabel("mn"));
		pnPM3.add(jcbSPenal);
		pnPM3.add(new JLabel("s"));
		
		pnPenalite.add(pnPM3);
		
		pnHaut.add(pnPenalite);


		/*----- Validation -----*/
		JPanel conteneurSud = new JPanel();

		MonBouton btnPrefOk =  new MonBouton("icones/ok-32.png", "Enregistrer ces préférences");
		btnPrefOk.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			System.out.println("Bouton Enregistrer ces préférences cliqué");
    			try {
					saveData();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

		container.add(conteneurSud, BorderLayout.SOUTH);
		
		
		
		this.add(container);
		//Enfin on l'affiche
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		System.out.println("Réglages par défaut des balises");
	}
	
	private void getBaliseDefaut() {
		 this.balise = new geCoUnss.Preference().getConfig();
	}

	private void saveData() throws TransformerException {

		Element racine = balise.getDocumentElement();
		
		/*----- balise trouvée -----*/
		System.out.println("Si decouvert → " + jr1.isSelected() + " - " + jr2.isSelected());
		
		Element baliseTrouvee;
		Element baliseTrouveeBonif;
		Element baliseTrouveePoints;
		Element baliseTrouveeTps;
		
		if(racine.getElementsByTagName("trouvee").getLength() == 0){
			baliseTrouvee = this.balise.createElement("trouvee");
			baliseTrouveeBonif = this.balise.createElement("bonif");
			baliseTrouveePoints = this.balise.createElement("points");
			baliseTrouveeTps = this.balise.createElement("temps");
		} else {
			baliseTrouvee = (Element) racine.getElementsByTagName("trouvee").item(0);
			baliseTrouveeBonif = (Element) baliseTrouvee.getElementsByTagName("bonif").item(0);
			baliseTrouveePoints = (Element) baliseTrouvee.getElementsByTagName("points").item(0);
			baliseTrouveeTps = (Element) baliseTrouvee.getElementsByTagName("temps").item(0);
		}
				
		String choixBaliseTrouvee = "0";
		
		if (jr1.isSelected()) {
			choixBaliseTrouvee = "1";
		}
		System.out.println("\tBonification : " + choixBaliseTrouvee);
		baliseTrouveeBonif.setTextContent(choixBaliseTrouvee);
		baliseTrouvee.appendChild(baliseTrouveeBonif);
		
		System.out.println("\tPoint : " + jcbPtsBonif.getSelectedItem().toString());
		baliseTrouveePoints.setTextContent(jcbPtsBonif.getSelectedItem().toString());	
		baliseTrouvee.appendChild(baliseTrouveePoints);
		
		System.out.print("\tTemps : ");
		System.out.print(jcbHBonif.getSelectedItem().toString() + "h ");
		System.out.print(jcbMnBonif.getSelectedItem().toString() + "mn ");
		System.out.println(jcbSBonif.getSelectedItem().toString() + "s");
		String tpsDecouvert = jcbHBonif.getSelectedItem().toString();
		if(jcbMnBonif.getSelectedItem().toString().length() == 1) {
			tpsDecouvert += "0";
		}
		tpsDecouvert +=	jcbMnBonif.getSelectedItem().toString();
		if(jcbSBonif.getSelectedItem().toString().length() == 1) {
			tpsDecouvert += "0";
		}
		tpsDecouvert +=	jcbSBonif.getSelectedItem().toString();
		System.out.println("\t" + tpsDecouvert);
		baliseTrouveeTps.setTextContent(tpsDecouvert);
		baliseTrouvee.appendChild(baliseTrouveeTps);
		

		racine.appendChild(baliseTrouvee);

		/*----- balise non trouvée -----*/
		System.out.println("Si PM → " + jr1PM.isSelected() + " - " + jr2PM.isSelected());
		
		Element balisePM;
		Element balisePMBonif;
		Element balisePMPoints;
		Element balisePMTps;

		if(racine.getElementsByTagName("pm").getLength() == 0){
			balisePM = this.balise.createElement("pm");
			balisePMBonif = this.balise.createElement("bonif");
			balisePMPoints = this.balise.createElement("points");
			balisePMTps = this.balise.createElement("temps");
		} else {
			balisePM = (Element) racine.getElementsByTagName("pm").item(0);
			balisePMBonif = (Element) balisePM.getElementsByTagName("bonif").item(0);
			balisePMPoints = (Element) balisePM.getElementsByTagName("points").item(0);
			balisePMTps = (Element) balisePM.getElementsByTagName("temps").item(0);
		}
		
		
		
		

		String choixBalisePM = "0";
		
		if (jr1PM.isSelected()) {
			choixBalisePM = "1";
		}
		System.out.println("\tBonification : " + choixBalisePM);
		balisePMBonif.setTextContent(choixBalisePM);
		balisePM.appendChild(balisePMBonif);
		
		System.out.println("\tPoint : " + jcbPtsPenal.getSelectedItem().toString());
		balisePMPoints.setTextContent(jcbPtsPenal.getSelectedItem().toString());	
		balisePM.appendChild(balisePMPoints);
		
		System.out.print("\tTemps : ");
		System.out.print(jcbHPenal.getSelectedItem().toString() + "h ");
		System.out.print(jcbMnPenal.getSelectedItem().toString() + "mn ");
		System.out.println(jcbSPenal.getSelectedItem().toString() + "s");
		
		String tpsPenalite = jcbHPenal.getSelectedItem().toString();
		if(jcbMnPenal.getSelectedItem().toString().length() == 1) {
			tpsPenalite += "0";
		}
		tpsPenalite +=	jcbMnPenal.getSelectedItem().toString();
		if(jcbSPenal.getSelectedItem().toString().length() == 1) {
			tpsPenalite += "0";
		}
		tpsPenalite +=	jcbSPenal.getSelectedItem().toString();
		System.out.println("\t" + tpsPenalite);		
		balisePMTps.setTextContent(tpsPenalite);
		balisePM.appendChild(balisePMTps);
		
		racine.appendChild(balisePM);
		
		parcoursDom(balise);
		
		enregistreFichier(balise, "config.xsd", "config.xml");
		
		

		this.setVisible(false);
	}
	
	private int getHeure(int temps) {
		int retour;
		retour = temps / 10000;
		return retour;
	}
	private int getMinutes(int temps) {
		int retour;
		temps = temps % 10000;
		retour = temps / 100;
		return retour;
	}
	private int getSecondes(int temps) {
		int retour;
		retour = temps % 100;
		return retour;
	}
	
	private void parcoursDom(Document doc) {
		Element racine = doc.getDocumentElement();
		System.out.println("Élément racine : " + racine.getNodeName());
		NodeList racineNoeuds = racine.getChildNodes();
		int nbRacineNoeuds = racineNoeuds.getLength();
		
		for (int i = 0; i<nbRacineNoeuds; i++) {
		    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        final Node personne = racineNoeuds.item(i);
		        
		        System.out.println(personne.getNodeName()+" - " + personne.getTextContent());
		    }               
		}
		
	}
}
