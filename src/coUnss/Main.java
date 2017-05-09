package coUnss;

import java.util.Vector;

import affichage.Fenetre;
//import parcours.*;
//import equipes.*;

public class Main {
	
	static Preference preferences = null;

	public static void main(String[] args) {
		
		preferences = new Preference();
		
		Fenetre fen = new Fenetre();
/**
		Competition course = new Competition("Académiques de CO - 2017");
		// System.out.println(course.getNom());
		// System.out.println("\t----------");
		
		/**
		
		course.setCategorie(new Categorie("Collège"));
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		
		course.setCategorie(new Categorie( "Collège2"));
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		
		course.setCategorie(new Categorie("Lycée"));
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		
		course.delCategories(1);
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		
		course.delCategories(1);
		
		int nbCoureurs = 4 , nbGarcons = 1 , nbFilles = 1;
		course.setCategorie(new Categorie("Lycée" , nbCoureurs , nbGarcons , nbFilles));
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		
		course.getCategories().elementAt(0).setCoureurs(4);
		course.getCategories().elementAt(0).setFilles(2);
		course.getCategories().elementAt(0).setGarcons(2);
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		
		Relais newEpreuve = new Relais();
		course.getCategories().elementAt(1).setEpreuve(newEpreuve);
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		
		Reseau newEpreuve2 = new Reseau();
		Categorie lycee = course.getCategories().elementAt(1);
		lycee.setEpreuve(newEpreuve2);
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		
		Relais relaiLycee = (Relais) lycee.getEpreuve(0);
		for(int j=1;j<=6;j++) {	
			Vector<parcours.Balise> balises = new Vector<parcours.Balise>();
			for(int i=1;i<5;i++) {
				balises.addElement(new parcours.Balise(i + ((j+2)*10)));
			}
			relaiLycee.addCircuit(new Circuit(Integer.toString(j) , balises));
		}
		// System.out.println(course.toString());
		// System.out.println("\t----------");
		int j = 0;
		for(int i=0;i<=5;i++) {
			j = i % 2;
			relaiLycee.addCombinaison(j, i+1);
		}
		//System.out.println(course.toString());
		//System.out.println("\t----------");
		
		Sexe sexe = Sexe.G;
		Vector<Equipe> equipes = lycee.getEquipes();
		
		for(j=1;j<=6;j++) {
			equipes.addElement(new Equipe("Equipe " + j));
			for(int i=1;i<=4;i++) {
				if(i%2 == 0) {
					sexe = Sexe.G;
				} else {
					sexe = Sexe.F;
				}
				equipes.lastElement().addCoureurs(new Coureur("nom coureur "+j+"-"+i, "prenom coureur "+j+"-"+i, sexe));
			}
		}
		//System.out.println(course.toString());
		//System.out.println("\n***************************************************************************************************************\n");
		
		Epreuve lyceeEpreuveActive = lycee.getEpreuve(1);
		Vector<parcours.Balise> balises = new Vector<parcours.Balise>();
		for(int i=1;i<=10;i++) {
			balises.addElement(new parcours.Balise(i + 30));
		}
		lyceeEpreuveActive.setPourTous(true);
		Circuit pCircuit = new Circuit(balises);
		lyceeEpreuveActive.addCircuit(pCircuit);
		
		System.out.println(lyceeEpreuveActive.toString());
		//System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////\n");
		
		/* *
		lyceeEpreuveActive = lycee.getEpreuve(2);
		balises = new Vector<parcours.Balise>();for(int j=1;j<=6;j++) {	
			Vector<parcours.Balise> balises = new Vector<parcours.Balise>();
			for(int i=1;i<5;i++) {
				balises.addElement(new parcours.Balise(i + ((j+2)*10)));
			}
			relaiLycee.addCircuit(new Circuit(Integer.toString(j) , balises));
		}
		lyceeEpreuveActive.setPourTous(true);
		pCircuit = new Circuit(balises);
		lyceeEpreuveActive.addCircuit(pCircuit);
		System.out.println(lyceeEpreuveActive.toString());
		System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////\n");
		/* *
		
		System.out.println(course.getNom());
		System.out.println("***************************************************************************************************************\n");
		
		Categorie college = course.getCategories().elementAt(0);
		college.setEpreuve(new Epreuve("Relais"));
		
		Categorie categorieActive = new Categorie();
		for(int i=0;i<course.getCategories().size();i++) {
			categorieActive = course.getCategories().elementAt(i) ;
			categorieActive.setEpreuve(new Epreuve("Relais " + categorieActive.getNom()));
			System.out.println(categorieActive.toString());
			System.out.println("\t----------");
		}
		*/
		/**
		Categorie categorieActive = new Categorie();
		Epreuve epreuveActive = new Epreuve();
		Vector<parcours.Balise> balises = new Vector<parcours.Balise>();
		
		course.setCategorie(new Categorie("Collège"));
		categorieActive = course.getCategories().get(0);
		categorieActive.setContraintes(4, 2, 2);
		categorieActive.setEpreuve(new Relais("relais collège"));
		epreuveActive = categorieActive.getEpreuve(0);
		for(int j=1;j<=3;j++) {	
			balises = new Vector<parcours.Balise>();
			for(int i=1;i<=4;i++) {
				balises.addElement(new parcours.Balise(i + ((j+2)*10)));
			}
			epreuveActive.addCircuit(new Circuit(Integer.toString(j) , balises));
		}
		categorieActive.setEpreuve(new Reseau("reseau collège"));
		epreuveActive = categorieActive.getEpreuve(1);
		balises = new Vector<parcours.Balise>();
		for(int i=1;i<=4;i++) {
			balises.addElement(new parcours.Balise(i + 100));
		}
		epreuveActive.addCircuit(new Circuit("1" , balises));
		
		
		
		course.setCategorie(new Categorie("Lycée"));
		categorieActive = course.getCategories().get(1);
		categorieActive.setContraintes(4, 1, 1);
		categorieActive.setEpreuve(new Relais("relais lycée"));
		epreuveActive = categorieActive.getEpreuve(0);
		for(int j=1;j<=2;j++) {	
			balises = new Vector<parcours.Balise>();
			for(int i=1;i<5;i++) {
				balises.addElement(new parcours.Balise(i + ((j+2)*10)));
			}
			epreuveActive.addCircuit(new Circuit(Integer.toString(j) , balises));
		}
		categorieActive.setEpreuve(new Reseau("reseau lycée"));
		epreuveActive = categorieActive.getEpreuve(1);
		balises = new Vector<parcours.Balise>();
		for(int i=1;i<=4;i++) {
			balises.addElement(new parcours.Balise(i + 200));
		}
		epreuveActive.addCircuit(new Circuit("1" , balises));
		
		
		System.out.println(course.getNom());
		for(int i=0;i<course.getCategories().size();i++) {
			categorieActive = course.getCategories().get(i);
			//System.out.println(course.getCategories().get(i));
			System.out.println("\t" + categorieActive);
		}

		for(int i=0;i<course.getCategories().size();i++) {
			categorieActive = course.getCategories().get(i);
			epreuveActive = categorieActive.getEpreuve(0);
			System.out.println(epreuveActive);
			epreuveActive = categorieActive.getEpreuve(1);
			System.out.println(epreuveActive);
		}
		
		
		
		*/
		
	}

}
