package dialogues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import affichage.MonBouton;

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

		MonBouton btnModifieCategorie =  new MonBouton("icones/recherche-32.png", "Modifier une catégorie");
		if(categorieSelect == ""){
			btnModifieCategorie.setEnabled(false);
			System.out.println("Bouton Modifier une catégorie désactivé");
		} else {
			btnModifieCategorie.setEnabled(true);
			System.out.println("Bouton Modifier une catégorie actif");
		}
		btnModifieCategorie.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Modifier une catégorie cliqué");
    		}
    	});
		panCategoriesBtn.add(btnModifieCategorie);

		MonBouton btnSupprimeCategorie =  new MonBouton("icones/stop-32.png", "Supprimer une catégorie");
		if(categorieSelect == ""){
			btnSupprimeCategorie.setEnabled(false);
			System.out.println("Bouton Supprimer une catégorie désactivé");
		} else {
			btnSupprimeCategorie.setEnabled(true);
			System.out.println("Bouton Modifier une catégorie actif");
		}
		btnSupprimeCategorie.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent event){
    			System.out.println("Bouton Supprimer une catégorie cliqué");
    		}
    	});
		
		panCategoriesBtn.add(btnSupprimeCategorie);
		panCategories.add(panCategoriesBtn, BorderLayout.NORTH);
		
		JPanel panCategoriesTable = new JPanel();
		String[] entetes = {"Nom long", "Nom court"};

		String[][] donnees = {
                {"Collège", "COL"},
                {"Lycée", "LYC"},
        };

		NodeList nList = config.getElementsByTagName("categorie");
		 
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				donnees[temp][0] = new String(eElement
	                        .getElementsByTagName("nomLong").item(0)
	                        .getTextContent());
				donnees[temp][1] = new String(eElement
	                        .getElementsByTagName("nomCourt").item(0)
	                        .getTextContent());
	            }

	        }		
		
		JPanel conteneurTableau = new JPanel();
		conteneurTableau.setLayout(new BorderLayout());
		tableau = new TableauPreferences(donnees, entetes);
		tableau.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableau.getColumnModel().getColumn(1).setPreferredWidth(30);
		panCategoriesTable.add(new JScrollPane(tableau));
		conteneurTableau.add(panCategoriesTable, BorderLayout.WEST);
		panCategories.add(conteneurTableau);
		
		
		/*----- Validation -----*/
		JPanel conteneurSud = new JPanel();
		
		


		MonBouton btnPrefOk =  new MonBouton("icones/ok-32.png", "Enregistrer ces préférences");
		btnPrefOk.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
    			System.out.println("Bouton Enregistrer ces préférences cliqué");
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
		//for(String[] ligneTableau : tableau) {
		for	(int i=0;i<tableau.getRowCount();i++) {
			ArrayList<String> l= new ArrayList<String>();
			l.add(tableau.getModel().getValueAt(i,0).toString());
			System.out.println("\t" + tableau.getValueAt(i,0) + " → " + tableau.getModel().getValueAt(i,1));
			l.add(tableau.getModel().getValueAt(i,1).toString());
			listeCategories.add(i,l);			
		}
		for(ArrayList<String> p:listeCategories)
        {
            for(int j=0;j<p.size();j=+2)
        System.out.println("\t" + p.get(j) + " → " + p.get(j+1) );
         
        ;}
		
            enregistreConfig(config);

		
		setVisible(false);
	}
	
}
