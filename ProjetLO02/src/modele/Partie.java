package modele;
import vue.VueLigneCommande;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import java.util.Observable;
import vue.InterfaceDebutPartie;
/**
 *<b>Classe définissant la Partie en tant que singleton </b> 
 *<p> La partie est créé lors de l'execution du programme.\n 
 *Elle est une composition de Manches, et possède une liste de Joueurs ainsi qu'un mode de comptage des points.
 *
 *@author Charlene et Robin
 *@version 1.0
 *@see Manche
 *@see Joueur
 */
public class Partie extends Observable implements Runnable {

	private static Partie instancePartie;
	private int nbJoueursVirtuels;
	private String etat;
	private Manche manche;
	private LinkedList<Joueur> joueur;
	private LinkedList<Joueur> classementJoueursPartie;
	private String modeComptage;
/**
 * <b>Le constructeur de la Partie (mode graphique)</b>
 * <p>
 * Lors de la construction de la partie, la liste des joueurs de celle-ci est créée. 
 * On initialise également le classement de ceux-ci.
 * </p>
 * @param nbJoueursVirtuels le nombre de joueurs virtuels tel que demandé par l'utilisateur
 * @param modeComptage le mode de comptage des points
 * @param nom le nom du Joueur Physique
 * @param variante la Variante choisie par l'utilisateur pour commencer la Partie.
 */
	private Partie(int nbJoueursVirtuels[], String modeComptage, String nom, String variante) {
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
		this.modeComptage = modeComptage;
		//Partie.instancePartie = this;	
		this.etat = "EN COURS";

	}
	/**
	 * <b>Le constructeur de la Partie </b>
	 * <p>
	 * Lors de la construction de la partie, la liste des joueurs de celle-ci est créée. 
	 * On initialise également le classement de ceux-ci.
	 * </p>
	 * @param nbJoueursVirtuels le nombre de joueurs virtuels tel que demandé par l'utilisateur
	 * @param modeComptage le mode de comptage des points
	 * @param nom le nom du Joueur Physique
	 * @param variante la Variante choisie par l'utilisateur pour commencer la Partie.
	 */
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
		//Partie.instancePartie = this;

	}

	/**
	 * <b> Implémentation du pattern Singleton</b>
	 * <p> Permet de s'assurer de l'unicité d'une Partie. </p>
	 * 
	 * @return Partie instance unique de la classe Partie
	 */
	
	public static Partie getPartie() {

		if (Partie.instancePartie == null) {
			Partie.instancePartie = new Partie();
		}
		return Partie.instancePartie;
	}
	
	public static Partie getPartie(int nbJoueursVirtuels[], String modeComptage, String nom, String variante) {

		if (Partie.instancePartie == null) {
			Partie.instancePartie = new Partie(nbJoueursVirtuels, modeComptage, nom, variante);
		}
		return Partie.instancePartie;
	}
/**
 * <b> Méthode permettant de vérifier qu'un Joueur a terminé la partie </b>
 * <p> Cette méthode différencie deux cas en fonction du mode de comptage de points. </p>
 * @return true si la partie est terminée, false sinon.
 */
	public boolean terminerPartie() {
		boolean terminer = false;
		if (this.modeComptage.equals("POSITIF")) {
			// la partie se joue en 100 points
			if (this.classementJoueursPartie.get(0).getScore() >= 100) {
				terminer = true;
				this.etat = "TERMINEE";
				System.out.println("Partie termininee! Un joueur a eu au moins 100 point !");
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

/**
 * <b> Méthode lançant le thread de la partie Graphique. </b>
 */
	public void lancerPartieGraphique() {

		Thread t = new Thread(this);
		t.start();
	}

	public void run() {

		this.lancerPartie();
	}
/**
 * <b> Méthode permettant de lancer la partie. </b>
 */
	public void lancerPartie() {

		while (Partie.getPartie().etat.equals("EN COURS")) {
			VueLigneCommande.getLDC();
			if(this.manche.getPioche()==null)// tant que la partie n'est pas terminee, on joue des manches
			{ Partie.getPartie().manche.setPioche(new Pioche());// creation de la pioche
			 Partie.getPartie().manche.getPioche().melanger();// on melange la pioche
			 Partie.getPartie().manche.getPioche().distribuer();}// on distribue la pioche
			while (!Partie.getPartie().manche.terminerManche()) { // tant que la manche n'est pas terminee, on joue des
			try {// Temps de delais entre chaque tour
					Thread.sleep(700);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Partie.getPartie().manche.getJoueur().get(Partie.getPartie().manche.getTourJoueur() - 1).jouerTour();
				System.out.println("\n");
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
	
				try {
					InterfaceDebutPartie window = new InterfaceDebutPartie();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				};
			}
			
		
	
}