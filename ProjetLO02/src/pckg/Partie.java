package pckg;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Random;

public class Partie {

	private static Partie instancePartie;
	private int nbJoueursVirtuels;
	private int nbJoueursEnCours;
	private String etat;
	private int tourJoueur;
	private int sens;
	private Talon talon;
	private LinkedList<Joueur> joueur;
	private LinkedList<Joueur> classementJoueurs;
	private LinkedList<Joueur> classementJoueursPartie;
	private Variante variantePartie;
	private Pioche pioche;
	private String modeComptage;

	private Partie() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisissez le nombre de joueurs virtuels :"); 
		this.nbJoueursVirtuels = sc.nextInt();
		this.classementJoueurs = new LinkedList<Joueur>();
		this.classementJoueursPartie = new LinkedList<Joueur>();
		// instanciation des joueurs
		this.joueur = new LinkedList<Joueur>();
		this.joueur.add(new JoueurPhysique());
		int i;
		for (i = 1; i <= this.nbJoueursVirtuels; i++) {
			this.joueur.add(new JoueurVirtuel());
		}
		// on initialise le classement de la partie
		for (i = 0; i < this.joueur.size(); i++) {
			this.classementJoueursPartie.add(this.joueur.get(i));
		}
		int nbJoueursEnCours = this.nbJoueursVirtuels + 1;
		this.nbJoueursEnCours = nbJoueursEnCours;
		this.etat = "EN COURS";
		Random r = new Random();
		int tourJoueur = 1 + r.nextInt(nbJoueursEnCours - 1); // le joueur qui debute la partie est choisi aleatoirement
		this.tourJoueur = tourJoueur;
		this.sens = 1; // sens des aiguilles d'une montre
		this.talon = new Talon();
		// choix de la variante
		this.variantePartie = choisirVariante();
		// mode de comptage des points
		System.out.println("\nSaisir le mode de comptage des points : 'POSITIF' ou 'NEGATIF'");
		Scanner text = new Scanner(System.in);
		this.modeComptage = text.nextLine();
		if (this.modeComptage.equals("POSITIF")) {
			System.out.println(
					"\nMode de comptage des points choisi : POSITIF ! Le premier joueur arrivé à 60 points gagne la partie ! \nLorsque 3 joueurs ont fini la manche, celle-ci se termine\n");
		} else {
			System.out.println(
					"\nMode de comptage des points choisi : NEGATIF ! Lorsqu'un joueur atteint 100 point, il perd la partie ! \nUne manche se termine dès qu'un joueur a fini !\n");
		}
	}

	public Variante choisirVariante() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisissez la variante :\n1=Variante minimale\n2=Variante 5\n3=Variante 4 ");
		int variante = sc.nextInt();
		Variante choixVariante;
		if (variante == 1) {
			choixVariante = new VarianteMinimale(this.nbJoueursVirtuels);
			System.out.println("Variante minimale choisie");
		} else if (variante == 2) {
			choixVariante = new Variante5(this.nbJoueursVirtuels);
			System.out.println("Variante 5 choisie ");
		} else if (variante == 3) {
			choixVariante = new Variante4(this.nbJoueursVirtuels);
			System.out.println("Variante 4 choisie ");

		} else {
			System.out.println("Erreur : variante inexistante, choisissez à nouveau");
			choixVariante = choisirVariante();
		}
		return choixVariante;
	}

	/**
	 * Singleton 
	 * @return Partie instance unique de la classe Partie
	 */
	public static Partie getPartie() {

		if (Partie.instancePartie == null) {
			Partie.instancePartie = new Partie();
		}
		return Partie.instancePartie;
	}

	public boolean terminerManche() {

		boolean terminer = false;
		if (this.modeComptage.equals("POSITIF")) {
			// s'il y a 3 joueurs qui ont gagné, on s'il ne reste plus qu'un joueur
			if (this.classementJoueurs.size() == 3 || this.nbJoueursEnCours == 1) {
				terminer = true;
				while (!this.joueur.isEmpty()) {
					this.classementJoueurs.add(this.joueur.poll());// on met tous les joueurs restant dans le classement
				}
				System.out.println("Manche terminée !");
			}
		} else { // mode de comptage negatif
			if (this.classementJoueurs.size() == 1) {
				terminer = true;
				while (!this.joueur.isEmpty()) {
					this.classementJoueurs.add(this.joueur.poll());
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
		// on donne le classement général de la partie
		System.out.println("\nClassement général : ");
		for (i = 1; i <= this.classementJoueursPartie.size(); i++) {
			System.out.println(i + "e : " + this.classementJoueursPartie.get(i - 1).getName() + " -- SCORE : "
					+ this.classementJoueursPartie.get(i - 1).getScore());
		}
	}

	public void compterPointsNegatif() {

		int i;
		// mode de comptage négatif
		ListIterator<Joueur> parcourirJoueurs = classementJoueurs.listIterator(); // on parcourt les joueurs
		Joueur joueurSelect;
		do {
			joueurSelect = parcourirJoueurs.next(); // On selectionne un joueur
			joueurSelect.setScoreManche(0);
			int points = 0;// on initialise les points qu'il va prendre à 0
			// on parcourt les cartes restantes du joueur selectionné
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
			// modification du score génaral
			joueurSelect.setScore(joueurSelect.getScore() + points);
			// pour afficher le score de la manche
			joueurSelect.setScoreManche(points);
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
		this.triInsertionScore(this.classementJoueursPartie, "croissant");

		// on donne le classemnet général de la partie
		System.out.println("\nClassement général : ");
		for (i = 1; i <= this.classementJoueursPartie.size(); i++) {
			System.out.println(i + "e : " + this.classementJoueursPartie.get(i - 1).getName() + " -- SCORE : "
					+ this.classementJoueursPartie.get(i - 1).getScore());
		}
	}

	public void changerManche() {
		this.variantePartie = choisirVariante();// posibilité de changer la variante
		int i, j;
		while (this.classementJoueurs.size() > 0) {
			this.joueur.add(this.classementJoueurs.poll());// On remet les joueurs dans le tableau de joueurs :
		}

		// tri des joueurs par insertion, on ajoute le joueur dans l'ordre de leurs numéros croissants
		for (i = 0; i < this.joueur.size(); i++) {
			Joueur joueurJ = this.joueur.get(i);

			j = i;
			while (j > 0 && this.joueur.get(j - 1).getNumero() > joueurJ.getNumero()) {
				this.joueur.set(j, this.joueur.get(j - 1));
				j = j - 1;
			}
			this.joueur.set(j, joueurJ);
		}
		// initialisation de la nouvelle partie
		// on retire les eventuelles cartes restantes des joueurs
		// Les joueur on pour etat "JOUE"
		for (i = 0; i < this.joueur.size(); i++) {
			this.joueur.get(i).getCartes().clear();
		}
		// on crée un nouveau talon
		this.talon = new Talon();
		// sens des aiguilles d'une montre
		this.sens = 1;
		// on garde le même nombre de joueurs
		this.nbJoueursEnCours = this.nbJoueursVirtuels + 1;
		// on crée une nouvelle pioche
		this.pioche = new Pioche();
		// on mélange la pioche
		this.pioche.melanger();
		// on distribue la pioche
		this.pioche.distribuer();
	}

	public boolean terminerPartie() {
		// On initialise terminer a false
		boolean terminer = false;
		// mode de comptage positif
		if (this.modeComptage.equals("POSITIF")) {
			// la partie se joue en 200 points mais on test avec 60
			if (this.classementJoueursPartie.get(0).getScore() >= 60) {
				terminer = true;
				this.etat = "TERMINEE";
				System.out.println("Partie terminée! Un joueur a eu au moins 60 point !");
			}

		} else {// mode de comptage négatif, le premier qui arrive a 100 point a perdu et la partie se termine
			if (this.classementJoueursPartie.get(this.classementJoueurs.size() - 1).getScore() >= 100) {
				terminer = true;
				this.etat = "TERMINEE";
				System.out.println("Partie terminée! Un joueur a eu au moins 100 point !");
			}
		}

		return terminer;
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
	 * @return the talon
	 */
	public Talon getTalon() {
		return talon;
	}

	/**
	 * @return the variantePartie
	 */
	public Variante getVariantePartie() {
		return variantePartie;
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
	 * @return the nbJoueursVirtuels
	 */
	public int getNbJoueursVirtuels() {
		return nbJoueursVirtuels;
	}
	
	/**
	 * @return the classementJoueurs
	 */
	public LinkedList<Joueur> getClassementJoueurs() {
		return classementJoueurs;
	}

	public static void main(String[] args){
		
		System.out.println("JEU DE 8 AMERICAIN \nPAR ROBIN LALLIER ET CHARLENE LECOUTURIER\n");
		// creation d'une partie
		Partie p = Partie.getPartie();
		// creation de la pioche
		p.pioche = new Pioche();
		// on melange la pioche
		p.pioche.melanger();
		// on distribue la pioche
		p.pioche.distribuer();

		while (p.etat.equals("EN COURS")) { // tant que la partie n'est pas terminée, on joue des manches

			while (!p.terminerManche()){ // tant que la manche n'est pas terminée, on joue des tours
				try {// Temps de délais entre chaque tour
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				p.joueur.get(p.tourJoueur - 1).jouerTour();
				System.out.println("\n");
			}
			if (p.modeComptage.equals("POSITIF")) {
				p.compterPointsPositif();
			} else {
				p.compterPointsNegatif();
			}
			// Si la partie n'est pas terminée, on debute une nouvelle manche
			if (!p.terminerPartie()) {
				System.out.println("\nNOUVELLE MANCHE\n");
				p.changerManche();
			}
		}
	}
}