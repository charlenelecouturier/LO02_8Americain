package modele;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Joueur est la classe qui represente les participants au jeu, qu'ils soient
 * physiques ou virtuels. Il ne sera pas possible de creer un joueur dont le
 * type n'est pas pr�cis� dans le jeu
 * 
 * @author Robin et Charl�ne
 * @see JoueurVirtuel
 * @see JoueurPhysique
 */
public abstract class Joueur extends Observable{

	protected ArrayList<Carte> cartes = new ArrayList<Carte>(); // on fait une arraylist pour les cartes
	protected String name;
	private int numero; // num�ro est compris entre 1 et le nombre de joueurs en cours dans la partie
	private static int donneurNum = 1;
	private int score; // score du joueur
	private int scoreManche;
	protected Strategie strategie;
	protected String EffetVariante;

	/**
	 * le Constructeur de Joueur ne doit pas �tre utilis� directement, il simplifie le code de ses classes filles.
	 */
	public Joueur() {
		this.EffetVariante = "Aucun";
		this.numero = donneurNum;
		donneurNum++;
		this.score = 0; // initialement le joueur a 0 points
	}

	/**
	 * @return the cartes
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	/**
	 * Accesseur du num�ro
	 * 
	 * @return le num�ro du joueur, utilis�epar la partie pour determiner les tours de jeu.
	 * @see Partie
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * Accesseur du nom.
	 * @return le nom d'un joueur.
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		hasChanged();
		notifyObservers();
	}


	/**
	 * @return the effetVariante
	 */
	public String getEffetVariante() {
		return EffetVariante;
	}

	/**
	 * @param effetVariante the effetVariante to set
	 */
	public void setEffetVariante(String effetVariante) {
		EffetVariante = effetVariante;
	}

	/**
	 * Le corps meme de cette classe, jouerTour permet � un joueur physique ou
	 * virtuel de choisir une carte dans son jeu et la poser sur le talon.
	 */
	public void jouerTour() {
		System.out.println("effet : " + this.EffetVariante);
		int tour;
		boolean gagne = false;
		this.poserCarte();
		this.EffetVariante = "Aucun";
		gagne = this.gagnePartie();	// on regarde si le fait d'avoir pos� une carte permet au joueur de gagner la manche
		tour = Partie.getPartie().getManche().getTourJoueur();
		if (Partie.getPartie().getManche().getSens() == 1) {
			if (!gagne) {
				tour++;
			}
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

	
	public void poserCarte() {
		if (Partie.getPartie().getManche().getVarianteManche().estPossibleDeJouer(this.cartes)) {
			int numeroCarte = this.choisirCarte();
			Carte cartePose = this.cartes.get(numeroCarte);
			Partie.getPartie().getManche().getTalon().getCartes().add(cartePose);
			System.out.println(
					"Test : il y a " + Partie.getPartie().getManche().getTalon().getCartes().size() + " cartes dans le talon");
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
			Partie.getPartie().getManche().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
			System.out.println(this.getName() + " pose " + cartePose);
			cartes.remove(cartePose);
			//On notifie à l'interface que la carte a été retirée de la main du joueur
			hasChanged();
			notifyObservers();
			
			if (this.cartes.size() == 1) {
				this.direCarte();
			}
			String effet = cartePose.getEffet();
			if (!effet.equals("Aucun")) {
				cartePose.appliquerEffet();
			}

		} else {
			if (this.EffetVariante.equals("Aucun")) {
				System.out.println(this.getName() + " ne peut pas jouer !");
				this.piocher(1);
			}
		}
	}

	public abstract void direCarte();
	public abstract void changerFamille();

	public void obligeDeRejouer() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (this.cartes.isEmpty()) {
			this.piocher(1);
		} else {

			this.poserCarte();
		}
	}

	public abstract int choisirCarte();

	public void piocher(int nombrePioche) {
		System.out.println("\n" + this.name + " pioche " + nombrePioche + " carte(s) !");

		if (Partie.getPartie().getManche().getPioche().getCartes().size() < nombrePioche) {
			Partie.getPartie().getManche().getTalon().devenirPioche();
		}
		for (int i = 1; i <= nombrePioche; i++) {
			Carte cartePioche = Partie.getPartie().getManche().getPioche().cartes
					.get(Partie.getPartie().getManche().getPioche().cartes.size() - 1); // -1 car indice commence � 0
			cartes.add(cartePioche);
			Partie.getPartie().getManche().getPioche().cartes.remove(Partie.getPartie().getManche().getPioche().cartes.size() - 1);
			System.out.println("\n" + this.name + " a pioch� " + cartePioche);
			System.out.println("\nTest : il reste " + Partie.getPartie().getManche().getPioche().getCartes().size()
					+ " cartes dans la pioche\n");
		}
	}

	public boolean gagnePartie() {
		if (this.cartes.isEmpty()) {
			System.out.println(this.name + " a gagn�.");
			Partie.getPartie().getManche().getClassementJoueurs().add(this);
			Partie.getPartie().getJoueur().remove(this);
			Partie.getPartie().getManche().setNbJoueursEnCours(Partie.getPartie().getManche().getNbJoueursEnCours() - 1);
			return true;
		}
		return false;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the scoreManche
	 */
	public int getScoreManche() {
		return scoreManche;
	}

	/**
	 * @param scoreManche the scoreManche to set
	 */
	public void setScoreManche(int scoreManche) {
		this.scoreManche = scoreManche;
	}
}