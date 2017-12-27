package modele;

import java.awt.EventQueue;
import vue.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Observable;


import vue.TestInterface;


public class Partie extends Observable{

	private static Partie instancePartie;
	private int nbJoueursVirtuels;
	private String etat;
	private Manche manche;
	private LinkedList<Joueur> joueur;
	private LinkedList<Joueur> classementJoueursPartie;
	private String modeComptage;

	private Partie() {
		

		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Saisissez le nombre de joueurs virtuels :");
			this.nbJoueursVirtuels = sc.nextInt();
		}
		catch(InputMismatchException exception)
		{
			//Affiche un message d'erreur si l'utilisateur n'entre pas de chiffre pour les joueurs virtuels 
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
		this.manche = new Manche(this.nbJoueursVirtuels,joueur);
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
		boolean terminer = false;
		if (this.modeComptage.equals("POSITIF")) {
			// la partie se joue en 200 points mais on test avec 60
			if (this.classementJoueursPartie.get(0).getScore() >= 60) {
				terminer = true;
				this.etat = "TERMINEE";
				System.out.println("Partie termin�e! Un joueur a eu au moins 60 point !");
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

	public void lancerPartie() {
		
		while (Partie.getPartie().etat.equals("EN COURS")) { // tant que la partie n'est pas terminee, on joue des manches
			Partie.getPartie().manche.setPioche(new Pioche());// creation de la pioche
			Partie.getPartie().manche.getPioche().melanger();// on melange la pioche
			Partie.getPartie().manche.getPioche().distribuer();// on distribue la pioche
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TestInterface window = new TestInterface(Partie.getPartie());
						window.getFrame().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			//vue concurente : ligne de commande et interface
			System.out.println(Thread.currentThread());

			while (!Partie.getPartie().manche.terminerManche()) { // tant que la manche n'est pas terminee, on joue des tours
				try {// Temps de delais entre chaque tour
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Partie.getPartie().manche.getJoueur().get(Partie.getPartie().manche.getTourJoueur() - 1).jouerTour();
				System.out.println("\n");
			}
			if (Partie.getPartie().modeComptage.equals("POSITIF")) {
				Partie.getPartie().manche.compterPointsPositif();
			} else {
				Partie.getPartie().manche.compterPointsNegatif();
			} // Si la partie n'est pas terminee, on debute une nouvelle manche
			if (!Partie.getPartie().terminerPartie()) {
				System.out.println("\nNOUVELLE MANCHE\n");
				Partie.getPartie().manche= new Manche(Partie.getPartie().nbJoueursVirtuels,Partie.getPartie().joueur);
			}
		}
	}
	public static void main(String[] args) {
		
		System.out.println("JEU DE 8 AMERICAIN \nPAR ROBIN LALLIER ET CHARLENE LECOUTURIER\n");
		Partie p = Partie.getPartie();// creation d'une partie
		VueLigneCommande vLC =new VueLigneCommande();
	}
}