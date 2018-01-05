package modele;
import vue.VueLigneCommande;

import java.awt.EventQueue;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Observable;
import vue.InterfaceDebutPartie;

public class Partie extends Observable implements Runnable {

	private static Partie instancePartie;
	private int nbJoueursVirtuels;
	private String etat;
	private Manche manche;
	private LinkedList<Joueur> joueur;
	private LinkedList<Joueur> classementJoueursPartie;
	private String modeComptage;

	public Partie(int nbJoueursVirtuels[], String modeComptage, String nom, String variante) {
		this.nbJoueursVirtuels = nbJoueursVirtuels.length;
		int i;
		this.classementJoueursPartie = new LinkedList<Joueur>();
		this.joueur = new LinkedList<Joueur>();
		this.joueur.add(new JoueurPhysique(nom));

		for (i = 0; i < this.nbJoueursVirtuels; i++) {

			this.joueur.add(new JoueurVirtuel(nbJoueursVirtuels[i]));
		}

		for (i = 0; i < this.joueur.size(); i++) {// on initialise le classement de la partie
			this.classementJoueursPartie.add(this.joueur.get(i));
		}
		this.manche = new Manche(this.nbJoueursVirtuels, joueur, variante);
		this.etat = "EN COURS";
		this.modeComptage = modeComptage;
		Partie.instancePartie = this;
	}

	private Partie() {

		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Saisissez le nombre de joueurs virtuels :");
			this.nbJoueursVirtuels = sc.nextInt();
		} catch (InputMismatchException exception) {
			// Affiche un message d'erreur si l'utilisateur n'entre pas de chiffre pour les
			// joueurs virtuels
			System.out.println("Veuillez recommencer en entrant un nombre entier s'il vous plait.");
		}
		this.classementJoueursPartie = new LinkedList<Joueur>();
		this.joueur = new LinkedList<Joueur>();
		this.joueur.add(new JoueurPhysique());
		int i;
		for (i = 1; i <= this.nbJoueursVirtuels; i++) {
			try {
				this.joueur.add(new JoueurVirtuel());
			} catch (NiveauJoueurException e) {
			}
		}
		for (i = 0; i < this.joueur.size(); i++) {// on initialise le classement de la partie
			this.classementJoueursPartie.add(this.joueur.get(i));
		}
		this.manche = new Manche(this.nbJoueursVirtuels, joueur);
		this.etat = "EN COURS";
		// mode de comptage des points
		System.out.println("\nSaisir le mode de comptage des points : 'POSITIF' ou 'NEGATIF'");
		Scanner text = new Scanner(System.in);
		this.modeComptage = text.nextLine();
		if (this.modeComptage.equals("POSITIF")) {
			System.out.println(
					"\nMode de comptage des points choisi : POSITIF ! Le premier joueur qui arrive 60 points gagne la partie ! \nLorsque 3 joueurs ont fini la manche, celle-ci se termine\n");
		} else {
			System.out.println(
					"\nMode de comptage des points choisi : NEGATIF ! Lorsqu'un joueur atteint 100 point, il perd la partie ! \nUne manche se termine lorsqu'un joueur a fini !\n");
		}
		Partie.instancePartie = this;
	}

	/**
	 * Singleton
	 * 
	 * @return Partie instance unique de la classe Partie
	 */
	/*
	 * public static Partie getPartie() {
	 * 
	 * if (Partie.instancePartie == null) { Partie.instancePartie = new Partie(); }
	 * return Partie.instancePartie; }
	 */

	public static Partie getPartie() {
		return Partie.instancePartie;
	}

	public boolean terminerPartie() {
		boolean terminer = false;
		if (this.modeComptage.equals("POSITIF")) {
			// la partie se joue en 100 points
			if (this.classementJoueursPartie.get(0).getScore() >= 100) {
				terminer = true;
				this.etat = "TERMINEE";
				System.out.println("Partie termininee! Un joueur a eu au moins 60 point !");
			}

		} else {// mode de comptage negatif, le premier qui arrive a 100 point a perdu et la
				// partie se termine
			if (this.classementJoueursPartie.get(this.manche.getClassementJoueurs().size() - 1).getScore() >= 100) {
				terminer = true;
				this.etat = "TERMINEE";
				System.out.println("Partie terminee! Un joueur a eu au moins 100 point !");
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
	 * @param nbJoueursVirtuels
	 *            the nbJoueursVirtuels to set
	 */
	public void setNbJoueursVirtuels(int nbJoueursVirtuels) {
		this.nbJoueursVirtuels = nbJoueursVirtuels;
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

	public void lancerManche() {
		while (!this.manche.terminerManche()) {
			try {// Temps de delais entre chaque tour
				Thread.sleep(950);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int tour = Partie.getPartie().manche.getTourJoueur() - 1;
			Joueur jTour = this.manche.getJoueur().get(tour);
			// au tour d'un joueur virtuel
			if (!(jTour instanceof JoueurPhysique)) {
				Partie.getPartie().manche.getJoueur().get(tour).jouerTour();
			} else { // Au tour du joueur physique : on attend qu'il fasse une action(poser carte ou
						// piocher)
				// mais sil ne peut pas jouer ET qu'il a reçu un effet, on applique cet effet
				// avec l'appel a estPossibleDeJouer() puis on passe au joueur suivant
				if (!this.manche.getVarianteManche().estPossibleDeJouer(this.manche.getJoueur().get(0).getCartes())
						&& !jTour.getEffetVariante().equals("Changer Famille")) {
					this.manche.getJoueur().get(0).changed();
					this.manche.getJoueur().get(0).notifyObservers();
					tour = Partie.getPartie().getManche().getTourJoueur();
					jTour.setEffetVariante("Aucun");
					if (Partie.getPartie().getManche().getSens() == 1) {
						tour++;
						if (tour > Partie.getPartie().getManche().getNbJoueursEnCours()) {
							tour = 1;
						}
					} else {
						tour--;
						if (tour <= 0) {
							tour = Partie.getPartie().getManche().getNbJoueursEnCours();
						}
					}
					Partie.getPartie().getManche().setTourJoueur(tour);
				}
			}
		}

	}

	public void lancerPartieGraphique() {

		Thread t = new Thread(this);
		t.start();
	}

	public void run() {

		this.lancerManche();
	}

	public void lancerPartie() {

		while (Partie.getPartie().etat.equals("EN COURS")) {

			// tant que la partie n'est pas terminee, on joue des manches
			 Partie.getPartie().manche.setPioche(new Pioche());// creation de la pioche
			 Partie.getPartie().manche.getPioche().melanger();// on melange la pioche
			 Partie.getPartie().manche.getPioche().distribuer();// on distribue la pioche
			// vue concurente : ligne de commande et interface
			System.out.println(Thread.currentThread());

			while (!Partie.getPartie().manche.terminerManche()) { // tant que la manche n'est pas terminee, on joue des
																	// tours
				Partie.getPartie().manche.getJoueur().get(Partie.getPartie().manche.getTourJoueur() - 1).jouerTour();
				System.out.println("\n");
				try {// Temps de delais entre chaque tour
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (!Partie.getPartie().terminerPartie()) {
				System.out.println("\nNOUVELLE MANCHE\n");
				Partie.getPartie().manche = new Manche(Partie.getPartie().nbJoueursVirtuels, Partie.getPartie().joueur);
			}
		}
	}

	/**
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}

	public static void main(String[] args) {
		System.out.println("JEU DE 8 AMERICAIN \nPAR ROBIN LALLIER ET CHARLENE LECOUTURIER\n");
		//Partie p =new Partie();
		//new VueLigneCommande();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceDebutPartie window = new InterfaceDebutPartie();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}