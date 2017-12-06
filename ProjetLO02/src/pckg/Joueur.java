package pckg;

import java.util.ArrayList;

/**
 * Joueur est la classe qui represente les participants au jeu, qu'ils soient
 * physiques ou virtuels. Il ne sera pas possible de creer un joueur dont le
 * type n'est pas précisé dans le jeu
 * 
 * @author Robin et Charlène
 * @see JoueurVirtuel
 * @see JoueurPhysique
 */
public abstract class Joueur {
	// **********attributs**************
	protected ArrayList<Carte> cartes = new ArrayList<Carte>(); // on fait une arraylist pour les cartes
	protected String name;
	private int numero; // numéro est compris entre 1 et le nombre de joueurs en cours dans la partie
	private static int donneurNum = 1;
	private int score; // score du joueur
	private int scoreManche;
	protected Strategie strategie;
	protected String EffetVariante;

	// **********Constructeur************
	/**
	 * le Constructeur de Joueur ne doit pas être utilisé directement, il simplifie le code de ses classes filles.
	 */
	public Joueur() {
		this.EffetVariante = "Aucun";
		/*
		 * Permet de donner à chaque nouvelle instance de joueur un nouveau numéro
		 */
		this.numero = donneurNum;
		donneurNum++;
		this.score = 0; // initialement le joueur a 0 points
	}
	// ********** Getter et Setters **********

	/**
	 * @return the cartes
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	/**
	 * @param cartes the cartes to set
	 */
	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}

	/**
	 * Accesseur du numéro
	 * 
	 * @return le numéro du joueur, utiliséepar la partie pour determiner les tours de jeu.
	 * @see Partie
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * Accesseur du nom.
	 * 
	 * @return le nom d'un joueur.
	 */
	public String getName() {
		return this.name;
	}
	// Pas de setter pour Numero, on ne doit pas pouvoir y toucher une fois
	// instancié

	public void setName(String name) {
		this.name = name;
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
	 * Le corps meme de cette classe, jouerTour permet à un joueur physique ou
	 * virtuel de choisir une carte dans son jeu et la poser sur le talon.
	 */
	public void jouerTour() {
		System.out.println("effet : " + this.EffetVariante);
		int tour;
		boolean gagne = false;
		this.poserCarte();
		this.EffetVariante = "Aucun";
		gagne = this.gagnePartie();	// on regarde si le fait d'avoir posé une carte permet au joueur de gagner la manche
		// On cherche le tour du joueur suivant
		tour = Partie.getPartie().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getSens() == 1) {

			if (!gagne) {
				tour++;
			}

			if (tour > Partie.getPartie().getNbJoueursEnCours()) {
				tour = 1;
			}
		} else {
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getNbJoueursEnCours();
			}
		}
		Partie.getPartie().setTourJoueur(tour);

	}

	
	public void poserCarte() {
		if (Partie.getPartie().getVariantePartie().estPossibleDeJouer(this.cartes)) {
			// Le joueur choisit la carte qu'il desire poser sur le talon.
			int numeroCarte = this.choisirCarte();
			Carte cartePose = this.cartes.get(numeroCarte);
			Partie.getPartie().getTalon().getCartes().add(cartePose);
			System.out.println(
					"Test : il y a " + Partie.getPartie().getTalon().getCartes().size() + " cartes dans le talon");
			// on change la carte du dessus du Talon 
			Partie.getPartie().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
			Partie.getPartie().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
			System.out.println(this.getName() + " pose " + cartePose);
			// 5.1 Le joueur perd la carte qu'il a posï¿½e de sa main
			cartes.remove(cartePose);
			// s'il n'a plus qu'une carte il est possible qu'un joueur dise contre carte
			if (this.cartes.size() == 1) {
				this.direCarte();
			}

			// On regarde si c'est une carte Speciale
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

		// Temps de délais pour le joueur qui rejoue
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

		if (Partie.getPartie().getPioche().getCartes().size() < nombrePioche) {
			Partie.getPartie().getTalon().devenirPioche();
		}
		// la boucle tourne autant de fois que le joueur doit piocher
		for (int i = 1; i <= nombrePioche; i++) {
			// Ajoute aux cartes du joueur la dernière carte de la pioche
			Carte cartePioche = Partie.getPartie().getPioche().cartes
					.get(Partie.getPartie().getPioche().cartes.size() - 1); // -1 car indice commence à 0
			cartes.add(cartePioche);
			// Retire cette carte de la pioche
			Partie.getPartie().getPioche().cartes.remove(Partie.getPartie().getPioche().cartes.size() - 1);
			System.out.println("\n" + this.name + " a pioché " + cartePioche);
			System.out.println("\nTest : il reste " + Partie.getPartie().getPioche().getCartes().size()
					+ " cartes dans la pioche\n");
		}
	}

	public boolean gagnePartie() {
		if (this.cartes.isEmpty()) {
			System.out.println(this.name + " a gagné.");
			Partie.getPartie().getClassementJoueurs().add(this);
			Partie.getPartie().getJoueur().remove(this);
			Partie.getPartie().setNbJoueursEnCours(Partie.getPartie().getNbJoueursEnCours() - 1);
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