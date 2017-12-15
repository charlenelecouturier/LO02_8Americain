package pckg;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Random;

public class Partie {

	private static Partie instancePartie;
	private int nbJoueursVirtuels;
	private String etat;
	private Manche manche;
	private LinkedList<Joueur> joueur;
	private LinkedList<Joueur> classementJoueursPartie;
	private String modeComptage;

	private Partie() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		try
		{
			System.out.println("Saisissez le nombre de joueurs virtuels :");
			this.nbJoueursVirtuels = sc.nextInt();
		}
		catch(InputMismatchException exception)
		{
			//Affiche un message d'erreur si l'utilisateur n'entre pas de chiffre pour les joueurs virtuels 
			System.out.println("Veuillez entre un nombre entier s'il vous plait.");
		}
		
		
		this.classementJoueursPartie = new LinkedList<Joueur>();
		// instanciation des joueurs
		this.joueur = new LinkedList<Joueur>();
		this.joueur.add(new JoueurPhysique());
		int i;
		for (i = 1; i <= this.nbJoueursVirtuels; i++) {
			try {
				this.joueur.add(new JoueurVirtuel());
			} catch (NiveauJoueurException e) {
			}
		}
		// on initialise le classement de la partie
		for (i = 0; i < this.joueur.size(); i++) {
			this.classementJoueursPartie.add(this.joueur.get(i));
		}
		this.manche = new Manche(this.nbJoueursVirtuels);
		this.etat = "EN COURS";
		// mode de comptage des points
		System.out.println("\nSaisir le mode de comptage des points : 'POSITIF' ou 'NEGATIF'");
		Scanner text = new Scanner(System.in);
		this.modeComptage = text.nextLine();
		if (this.modeComptage.equals("POSITIF")) {
			System.out.println(
					"\nMode de comptage des points choisi : POSITIF ! Le premier joueur arriv� � 60 points gagne la partie ! \nLorsque 3 joueurs ont fini la manche, celle-ci se termine\n");
		} else {
			System.out.println(
					"\nMode de comptage des points choisi : NEGATIF ! Lorsqu'un joueur atteint 100 point, il perd la partie ! \nUne manche se termine d�s qu'un joueur a fini !\n");
		}
	}

	/**
	 * Singleton
	 * 
	 * @return Partie instance unique de la classe Partie
	 */
	public static Partie getPartie() {

		if (Partie.instancePartie == null) {
			Partie.instancePartie = new Partie();
		}
		return Partie.instancePartie;
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
				System.out.println("Partie termin�e! Un joueur a eu au moins 60 point !");
			}

		} else {// mode de comptage n�gatif, le premier qui arrive a 100 point a perdu et la
				// partie se termine
			if (this.classementJoueursPartie.get(this.manche.getClassementJoueurs().size() - 1).getScore() >= 100) {
				terminer = true;
				this.etat = "TERMINEE";
				System.out.println("Partie termin�e! Un joueur a eu au moins 100 point !");
			}
		}

		return terminer;
	}

	/**
	 * @return the joueur
	 */
	public LinkedList<Joueur> getJoueur() {
		return joueur;
	}

	/**
	 * @return the classementJoueursPartie
	 */
	public LinkedList<Joueur> getClassementJoueursPartie() {
		return classementJoueursPartie;
	}

	/**
	 * @return the nbJoueursVirtuels
	 */
	public int getNbJoueursVirtuels() {
		return nbJoueursVirtuels;
	}

	/**
	 * @return the modeComptage
	 */
	public String getModeComptage() {
		return modeComptage;
	}

	/**
	 * @return the manche
	 */
	public Manche getManche() {
		return manche;
	}

	/**
	 * @param manche
	 *            the manche to set
	 */
	public void setManche(Manche manche) {
		this.manche = manche;
	}

	public static void main(String[] args) {

		System.out.println("JEU DE 8 AMERICAIN \nPAR ROBIN LALLIER ET CHARLENE LECOUTURIER\n");
		// creation d'une partie
		Partie p = Partie.getPartie();
		// creation de la pioche
		p.manche.setPioche(new Pioche());
		// on melange la pioche
		p.manche.getPioche().melanger();
		// on distribue la pioche
		p.manche.getPioche().distribuer();

		while (p.etat.equals("EN COURS")) { // tant que la partie n'est pas termin�e, on joue des manches

			while (!p.manche.terminerManche()) { // tant que la manche n'est pas termin�e, on joue des tours
				try {// Temps de d�lais entre chaque tour
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				p.joueur.get(p.manche.getTourJoueur() - 1).jouerTour();
				System.out.println("\n");
			}
			if (p.modeComptage.equals("POSITIF")) {
				p.manche.compterPointsPositif();
			} else {
				p.manche.compterPointsNegatif();
			}
			// Si la partie n'est pas termin�e, on debute une nouvelle manche
			if (!p.terminerPartie()) {
				System.out.println("\nNOUVELLE MANCHE\n");
				p.manche.changerManche();
			}
		}
	}
}