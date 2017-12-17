package pckg;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class Manche {
	private Variante varianteManche;
	private LinkedList<Joueur> classementJoueurs;
	private int nbJoueursEnCours;
	private int tourJoueur;
	private int sens;
	private Talon talon;
	private Pioche pioche;


	public Manche(int nbJoueursVirtuels) {		
		
		int nbJoueursEnCours = nbJoueursVirtuels + 1;
		this.nbJoueursEnCours = nbJoueursEnCours;
		this.classementJoueurs = new LinkedList<Joueur>();
		Random r = new Random();
		int tourJoueur = 1 + r.nextInt(nbJoueursEnCours - 1); // le joueur qui debute la partie est choisi aleatoirement
		this.tourJoueur = tourJoueur;
		this.sens = 1; // sens des aiguilles d'une montre
		this.talon = new Talon();
		// choix de la variante
		this.varianteManche = choisirVariante(nbJoueursVirtuels);
		}

	public boolean terminerManche() {

		boolean terminer = false;
		if (Partie.getPartie().getModeComptage().equals("POSITIF")) {
			// s'il y a 3 joueurs qui ont gagné, on s'il ne reste plus qu'un joueur
			if (this.classementJoueurs.size() == 3 || this.nbJoueursEnCours == 1) {
				terminer = true;
				while (!Partie.getPartie().getJoueur().isEmpty()) {
					this.classementJoueurs.add(Partie.getPartie().getJoueur().poll());// on met tous les joueurs restant dans le classement
				}
				System.out.println("Manche terminée !");
			}
		} else { // mode de comptage negatif
			if (this.classementJoueurs.size() == 1) {
				terminer = true;
				while (!Partie.getPartie().getJoueur().isEmpty()) {
					this.classementJoueurs.add(Partie.getPartie().getJoueur().poll());
				}
				System.out.println("Manche terminée !");
			}
		}
		return terminer;
	}

	public void compterPointsPositif() {
		int i;
		// on ajoute les points correspondant aux 3 premiers
		this.classementJoueurs.get(0).setScore(this.classementJoueurs.get(0).getScore() + 50);
		this.classementJoueurs.get(1).setScore(this.classementJoueurs.get(1).getScore() + 20);

		if (this.classementJoueurs.size() > 2) { // s'il y a plus de 2 joueurs
			this.classementJoueurs.get(2).setScore(this.classementJoueurs.get(2).getScore() + 10);
		}
		System.out.println("Classement de la manche :");
		for (i = 0; i < this.classementJoueurs.size(); i++) {
			System.out.println((i + 1) + "e : " + this.classementJoueurs.get(i).getName());
		}
		System.out.println("\nLe premier gagne 50 points, le deuxième 20 points, et le troisième 10 points");
		// (le meilleur est celui qui a le PLUS de points)
		this.triInsertionScore(Partie.getPartie().getClassementJoueursPartie(), "décroissant");
		System.out.println("\nClassement général : ");// on donne le classement général de la partie
		for (i = 1; i <= Partie.getPartie().getClassementJoueursPartie().size(); i++) {
			System.out.println(i + "e : " + Partie.getPartie().getClassementJoueursPartie().get(i - 1).getName() + " -- SCORE : "
					+ Partie.getPartie().getClassementJoueursPartie().get(i - 1).getScore());
		}
	}

	public void compterPointsNegatif() {

		int i;
		ListIterator<Joueur> parcourirJoueurs = classementJoueurs.listIterator(); // on parcourt les joueurs
		Joueur joueurSelect;
		do {
			joueurSelect = parcourirJoueurs.next(); // On selectionne un joueur
			joueurSelect.setScoreManche(0);
			int points = 0;// on initialise les points qu'il va prendre à 0
			ListIterator<Carte> parcourirCartesJoueur = joueurSelect.getCartes().listIterator();// on parcourt les cartes restantes du joueur selectionné
			Carte c;
			while (parcourirCartesJoueur.hasNext()) {
				c = parcourirCartesJoueur.next();

				if (c.getValeur().equals("DAME") || c.getValeur().equals("ROI")) {
					points += 10;
				} else if (c.getValeur().equals("1") || c.getValeur().equals("8") || c.getValeur().equals("JOKER")) {
					points += 50;
				} else if (c.getValeur().equals("10") || c.getValeur().equals("7") || c.getValeur().equals("2")
						|| c.getValeur().equals("VALET")) {
					points += 20;

				} else {
					points += Integer.parseInt(c.getValeur());
				}
			}
			joueurSelect.setScore(joueurSelect.getScore() + points);			// modification du score génaral
			joueurSelect.setScoreManche(points);// pour afficher le score de la manche
			System.out.println(joueurSelect.getName() + " prend " + points + " points");
		} while (parcourirJoueurs.hasNext());
		
		// (le meilleur est celui qui a le MOINS de points)
		this.triInsertionCroisScoreManche(classementJoueurs);

		// on donne le classement de la manche
		System.out.println("\nClassement de la manche : ");
		for (i = 1; i <= this.classementJoueurs.size(); i++) {
			System.out.println(i + "e : " + this.classementJoueurs.get(i - 1).getName() + " -- SCORE : "
					+ this.classementJoueurs.get(i - 1).getScoreManche());
		}
		// (le meilleur est celui qui a le MOINS de points)
		this.triInsertionScore(Partie.getPartie().getClassementJoueursPartie(), "croissant");
		System.out.println("\nClassement général : ");// on donne le classemnet général de la partie
		for (i = 1; i <= Partie.getPartie().getClassementJoueursPartie().size(); i++) {
			System.out.println(i + "e : " + Partie.getPartie().getClassementJoueursPartie().get(i - 1).getName() + " -- SCORE : "
					+ Partie.getPartie().getClassementJoueursPartie().get(i - 1).getScore());
		}
	}

	public void changerManche() {
		this.varianteManche = choisirVariante(Partie.getPartie().getNbJoueursVirtuels());// posibilité de changer la variante
		int i, j;
		while (this.classementJoueurs.size() > 0) {
			Partie.getPartie().getJoueur().add(this.classementJoueurs.poll());// On remet les joueurs dans le tableau de joueurs :
		}
		// tri des joueurs par insertion, on ajoute le joueur dans l'ordre de leurs numéros croissants
		for (i = 0; i < Partie.getPartie().getJoueur().size(); i++) {
			Joueur joueurJ = Partie.getPartie().getJoueur().get(i);

			j = i;
			while (j > 0 && Partie.getPartie().getJoueur().get(j - 1).getNumero() > joueurJ.getNumero()) {
				Partie.getPartie().getJoueur().set(j, Partie.getPartie().getJoueur().get(j - 1));
				j = j - 1;
			}
			Partie.getPartie().getJoueur().set(j, joueurJ);
		}
		// initialisation de la nouvelle manche
		for (i = 0; i < Partie.getPartie().getJoueur().size(); i++) {
			Partie.getPartie().getJoueur().get(i).getCartes().clear();
		}
		this.talon = new Talon();// on crée un nouveau talon
		this.sens = 1;// sens des aiguilles d'une montre
		this.nbJoueursEnCours = Partie.getPartie().getNbJoueursVirtuels() + 1;// on garde le même nombre de joueurs
		this.pioche = new Pioche();		// on crée une nouvelle pioche
		this.pioche.melanger();// on mélange la pioche
		this.pioche.distribuer();// on distribue la pioche
	}
	
	public void triInsertionScore(LinkedList<Joueur> classementJoueurs, String ordre) {
		int j;
		for (int i = 0; i < classementJoueurs.size(); i++) {
			Joueur joueurJ = classementJoueurs.get(i);
			j = i;
			if (ordre.equals("croissant")) {
				while (j > 0 && classementJoueurs.get(j - 1).getScore() > joueurJ.getScore()) {
					classementJoueurs.set(j, classementJoueurs.get(j - 1));
					j = j - 1;
				}
			} else {
				while (j > 0 && classementJoueurs.get(j - 1).getScore() < joueurJ.getScore()) {
					classementJoueurs.set(j, classementJoueurs.get(j - 1));
					j = j - 1;
				}
			}
			classementJoueurs.set(j, joueurJ);
		}
	}

	public void triInsertionCroisScoreManche(LinkedList<Joueur> classementJoueurs) {
		int j;
		for (int i = 0; i < classementJoueurs.size(); i++) {
			Joueur joueurJ = classementJoueurs.get(i);
			j = i;
			while (j > 0 && classementJoueurs.get(j - 1).getScoreManche() > joueurJ.getScoreManche()) {
				classementJoueurs.set(j, classementJoueurs.get(j - 1));
				j = j - 1;
			}
			classementJoueurs.set(j, joueurJ);
		}
	}
	
	public Variante choisirVariante(int nbJoueursVirtuels) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisissez la variante :\n1=Variante minimale\n2=Variante 5\n3=Variante 4 ");
		int variante = sc.nextInt();
		Variante choixVariante;
		if (variante == 1) {
			choixVariante = new VarianteMinimale(nbJoueursVirtuels);
			System.out.println("Variante minimale choisie");
		} else if (variante == 2) {
			choixVariante = new Variante5(nbJoueursVirtuels);
			System.out.println("Variante 5 choisie ");
		} else if (variante == 3) {
			choixVariante = new Variante4(nbJoueursVirtuels);
			System.out.println("Variante 4 choisie ");

		} else {
			System.out.println("Erreur : variante inexistante, choisissez à nouveau");
			choixVariante = choisirVariante(nbJoueursVirtuels);
		}
		return choixVariante;
	}

	/**
	 * @return the classementJoueurs
	 */
	public LinkedList<Joueur> getClassementJoueurs() {
		return classementJoueurs;
	}
	/**
	 * @return the talon
	 */
	public Talon getTalon() {
		return talon;
	}

	/**
	 * @return the variantePartie
	 */
	public Variante getVarianteManche() {
		return varianteManche;
	}

	public int getNbJoueursEnCours() {
		return nbJoueursEnCours;
	}

	public void setNbJoueursEnCours(int nbJoueursEnCours) {

		this.nbJoueursEnCours = nbJoueursEnCours;
	}

	public int getTourJoueur() {
		return tourJoueur;
	}

	public void setTourJoueur(int tourJoueur) {
		this.tourJoueur = tourJoueur;
	}

	public int getSens() {
		return sens;
	}

	public void setSens() {
		sens = sens * (-1);
	}

	/**
	 * @return the pioche
	 */
	public Pioche getPioche() {
		return pioche;
	}

	/**
	 * @param pioche the pioche to set
	 */
	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}
}