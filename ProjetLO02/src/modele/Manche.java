package modele;

import java.util.ListIterator;
import java.util.Observable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
import modele.variantes.*;

public class Manche extends Observable {
	private Variante varianteManche;
	private LinkedList<Joueur> classementJoueurs;
	private int nbJoueursEnCours;
	private int tourJoueur;
	private int sens;
	private Talon talon;
	private HashMap<String, Variante> variantes;
	private Pioche pioche;
	private LinkedList<Joueur> joueur;

	public Manche(int nbJoueursVirtuels, LinkedList<Joueur> joueur) {

		int nbJoueursEnCours = nbJoueursVirtuels + 1;
		this.nbJoueursEnCours = nbJoueursEnCours;
		this.classementJoueurs = new LinkedList<Joueur>();
		Random r = new Random();
		int tourJoueur = 1 + r.nextInt(nbJoueursEnCours - 1); // le joueur qui debute la partie est choisi aleatoirement
		this.tourJoueur = tourJoueur;
		this.sens = 1; // sens des aiguilles d'une montre
		this.talon = new Talon();
		// choix de la variante
		// initialisation de la HashMap de variantes
		this.variantes = new HashMap<String, Variante>();
		this.variantes.put("Minimale", new VarianteMinimale(nbJoueursVirtuels));
		this.variantes.put("Monclar", new VarianteMonclar(nbJoueursVirtuels));
		this.variantes.put("Variante 7", new Variante7(nbJoueursVirtuels));
		this.variantes.put("Variante 4", new Variante4(nbJoueursVirtuels));
		this.variantes.put("Variante 5", new Variante5(nbJoueursVirtuels));
		choisirVariante(nbJoueursVirtuels);
		ListIterator<Joueur> it = joueur.listIterator();
		this.joueur = new LinkedList<Joueur>();
		while (it.hasNext()) {
			Joueur joueurNext = it.next();
			joueurNext.getCartes().clear();
			joueurNext.setScoreManche(0);
			this.joueur.add(joueurNext);
		}
		Variante.nombreAs = 0;
	}

	public void choisirVariante(int nbJoueursVirtuels) {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Saisissez le nom exact de la variante que vous souhaitez :\nMinimale\nMonclar\nVariante 7\nVariante 4\nVariante 5 ");
		String variante = sc.nextLine();
		if (this.variantes.containsKey(variante)) {
			this.setVarianteManche(variante);
		} else {
			System.out.println("Mauvaise saisie.");
			this.choisirVariante(nbJoueursVirtuels);
		}
	}

	public Manche(int nbJoueursVirtuels, LinkedList<Joueur> joueur, String variante) {

		int nbJoueursEnCours = nbJoueursVirtuels + 1;
		this.nbJoueursEnCours = nbJoueursEnCours;
		this.classementJoueurs = new LinkedList<Joueur>();
		Random r = new Random();
		int tourJoueur = 1 + r.nextInt(nbJoueursEnCours - 1); // le joueur qui debute la partie est choisi aleatoirement
		this.tourJoueur = tourJoueur;
		this.sens = 1; // sens des aiguilles d'une montre
		this.talon = new Talon();
		// On remplie la HashMap , le paramètre en entré "variante" sera la clé
		// qui va permettre d'instancier la nouvelle variante
		this.variantes = new HashMap<String, Variante>();
		this.variantes.put("Minimale", new VarianteMinimale(nbJoueursVirtuels));
		this.variantes.put("Monclar", new VarianteMonclar(nbJoueursVirtuels));
		this.variantes.put("Variante 7", new Variante7(nbJoueursVirtuels));
		this.variantes.put("Variante 4", new Variante4(nbJoueursVirtuels));
		this.variantes.put("Variante 5", new Variante5(nbJoueursVirtuels));
		this.setVarianteManche(variante);// variante est la clé
		ListIterator<Joueur> it = joueur.listIterator();
		this.joueur = new LinkedList<Joueur>();
		while (it.hasNext()) {
			Joueur joueurNext = it.next();
			joueurNext.getCartes().clear();
			this.joueur.add(joueurNext);
		}

		Variante.nombreAs = 0;
	}

	public boolean terminerManche() {

		boolean terminer = false;
		if (Partie.getPartie().getModeComptage().equals("POSITIF")) {
			// s'il y a 3 joueurs qui ont gagne, on s'il ne reste plus qu'un joueur
			if (this.classementJoueurs.size() == 3 || this.nbJoueursEnCours == 1) {
				terminer = true;
				while (!this.joueur.isEmpty()) {
					this.classementJoueurs.add(this.joueur.poll());// on met tous les joueurs restant dans le classement
				}
				System.out.println("Manche terminee !");
			}
		} else { // mode de comptage negatif
			if (this.classementJoueurs.size() == 1) {
				terminer = true;
				while (!this.joueur.isEmpty()) {
					this.classementJoueurs.add(this.joueur.poll());
				}
				System.out.println("Manche terminee !");
			}
		}
		if (terminer) {
			if (Partie.getPartie().getModeComptage().equals("POSITIF")) {
				this.compterPointsPositif();
			} else {
				this.compterPointsNegatif();
			}
			this.setChanged();
			this.notifyObservers("manche terminee");// Si la partie n'est pas terminee, on debute une nouvelle manche
			// pour se faire on entre dans un nouveau Thread qui appelle lancerManche()
		}
		return terminer;
	}

	public void compterPointsPositif() {
		int i;
		// on ajoute les points correspondant aux 3 premiers
		this.classementJoueurs.get(0).setScore(this.classementJoueurs.get(0).getScore() + 50);
		this.classementJoueurs.get(0).setScoreManche(50);
		this.classementJoueurs.get(1).setScoreManche(20);
		this.classementJoueurs.get(1).setScore(this.classementJoueurs.get(1).getScore() + 20);

		if (this.classementJoueurs.size() > 2) { // s'il y a plus de 2 joueurs
			this.classementJoueurs.get(2).setScore(this.classementJoueurs.get(2).getScore() + 10);
			this.classementJoueurs.get(2).setScoreManche(10);
		}
		System.out.println("Classement de la manche :");
		for (i = 0; i < this.classementJoueurs.size(); i++) {
			System.out.println((i + 1) + "e : " + this.classementJoueurs.get(i).getName());
		}
		System.out.println("\nLe premier gagne 50 points, le deuxieme 20 points, et le troisieme 10 points");
		// (le meilleur est celui qui a le PLUS de points)

		this.triInsertionScore(Partie.getPartie().getClassementJoueursPartie(), "decroissant");
		System.out.println("\nClassement general : ");// on donne le classement general de la partie

		for (i = 1; i <= Partie.getPartie().getClassementJoueursPartie().size(); i++) {
			System.out.println(i + "e : " + Partie.getPartie().getClassementJoueursPartie().get(i - 1).getName()
					+ " -- SCORE : " + Partie.getPartie().getClassementJoueursPartie().get(i - 1).getScore());
		}
	}

	public void compterPointsNegatif() {

		int i;
		ListIterator<Joueur> parcourirJoueurs = classementJoueurs.listIterator(); // on parcourt les joueurs
		Joueur joueurSelect;
		do {
			joueurSelect = parcourirJoueurs.next(); // On selectionne un joueur
			joueurSelect.setScoreManche(0);
			int points = 0;// on initialise les points qu'il va prendre a 0
			ListIterator<Carte> parcourirCartesJoueur = joueurSelect.getCartes().listIterator();
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

			joueurSelect.setScore(joueurSelect.getScore() + points); // modification du score gï¿½naral
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

		System.out.println("\nClassement general : ");// on donne le classemnet general de la partie

		for (i = 1; i <= Partie.getPartie().getClassementJoueursPartie().size(); i++) {
			System.out.println(i + "e : " + Partie.getPartie().getClassementJoueursPartie().get(i - 1).getName()
					+ " -- SCORE : " + Partie.getPartie().getClassementJoueursPartie().get(i - 1).getScore());
		}
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

	public void setVarianteManche(String variante) {
		this.varianteManche = this.variantes.get(variante);
		System.out.println("La variante : " + variante + " a ete choisie.");
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
	 * @return the joueur
	 */
	public LinkedList<Joueur> getJoueur() {
		return joueur;
	}

	/**
	 * @param pioche
	 *            the pioche to set
	 */
	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}
}