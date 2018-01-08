package modele;
import modele.strategies.*;

import java.util.ArrayList;
import java.util.Observable;

/**
 *<b>Classe définissant les joueurs de la partie </b> 
 *<p>
 *Chaque instance de Joueur provient d'une classe fille, JoueurPhysique ou JoueurVirtuel. un joueur est caractérisé par : <ul> 
 *<li>une ArrayList de cartes représentant sa main</li>
 *<li>son nom</li>
 *<li>un numero, permettant de déterminer le prochain joueur à jouer</li>
 *<li>son score dans la partie et dans la manche.
 *</ul>
 *</p>
 *
 *@author Charlene et Robin
 *@version 1.0
 * @see JoueurVirtuel
 * @see JoueurPhysique
 */
public abstract class Joueur extends Observable{

	protected ArrayList<Carte> cartes = new ArrayList<Carte>(); // on fait une arraylist pour les cartes
	protected String name;
	private int numero; // numero est compris entre 1 et le nombre de joueurs en cours dans la partie
	private static int donneurNum = 1;
	private int score; // score du joueur
	private int scoreManche;
	protected Strategie strategie;
	protected String EffetVariante;
	protected String typeInterface;
	protected boolean aDitcarte;
	protected boolean contreCarte;
	protected boolean rejouer;
	protected boolean ajoue;


	/**
	 * le Constructeur de Joueur ne doit pas etre utilise directement, il simplifie le code de ses classes filles.
	 */
	public Joueur() {
		this.aDitcarte=false;
		this.contreCarte=false;
		this.rejouer=false;
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
	 * Accesseur du numero
	 * 
	 * @return le numero du joueur, utilise par la partie pour determiner les tours de jeu.
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
	}

	/**
	 * @return the effetVariante
	 */
	public String getEffetVariante() {
		return EffetVariante;
	}

	/**
	 * @param effetVariante
	 *            the effetVariante to set
	 */
	public void setEffetVariante(String effetVariante) {
		EffetVariante = effetVariante;
	}

	/**
	 * <b> Méthode principale du Joueur, jouerTour appelle poserCarte puis vérifie
	 * que le joueur n'a pas gagné, avant de passer au tour du prochain joueur.</b>
	 * 
	 * @see Joueur#poserCarte()
	 */
	public void jouerTour() {
		this.setChanged();
		this.notifyObservers("tour");
		if (!this.EffetVariante.equals("Bloquer")) {
			this.setChanged();
			this.notifyObservers("joue");
		}
		this.contreCarte = false;
		this.aDitcarte = false;
		System.out.println("effet : " + this.EffetVariante);
		int tour;
		boolean gagne = false;
		this.poserCarte();
		if (!this.EffetVariante.equals("doit rejouer")) {
			gagne = this.gagnePartie(); // on regarde si le fait d'avoir pose une carte permet au joueur de gagner la
										// manche
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
		this.setChanged();
		this.notifyObservers("a fini");
		this.EffetVariante = "Aucun";


	}
	
	/**
	 * <b> Méthode permettant à un joueur de poser une carte compatible sur le talon. </b>
	 * <p>Dans un premier temps, la méthode vérifie que le joueur peut poser au moins une carte sur le talon de sa main.
	 * La méthode appelle ensuite <b>choisirCarte()</b> (<i> méthode redéfinie selon le type du joueur</i>), avant de remplacer la carte 
	 * du dessus du talon par celle choisie par le joueur. Il l'enlève alors de la main de ce dernier, vérifie si le joueur 
	 * n'a plus qu'une carte (auquel cas il appelle <b>direCarte()</b> ), puis applique les effets de la carte si celle-ci en a.
	 * 
	 * @see JoueurVirtuel#choisirCarte()
	 * @see JoueurPhysique#choisirCarte()
	 * @see JoueurVirtuel#direCarte()
	 * @see JoueurPhysique#direCarte()
	 */
	public void poserCarte() {
		if (Partie.getPartie().getManche().getVarianteManche().estPossibleDeJouer(this.cartes)) {
			int numeroCarte =( (JoueurVirtuel)this).choisirCarte();
				Carte cartePose = this.cartes.get(numeroCarte);
				Partie.getPartie().getManche().getTalon().getCartes().add(cartePose);
				Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
				Partie.getPartie().getManche().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
				System.out.println(this.getName() + " pose " + cartePose);
				cartes.remove(cartePose);
				// On notifie l'interface que la carte a ete retiree de la main du joueur
				this.setChanged();
				this.notifyObservers();

				if (this.cartes.size() == 1) {
					this.direCarte();
				}
				String effet = cartePose.getEffet();
				if (!effet.equals("Aucun")) {
					cartePose.appliquerEffet();
				}
			
		}
	}

	public abstract void direCarte();
	public abstract void changerFamille();

	/**
	 * <b>Méthode permettant d'ajouter un nombre de cartes à la main du joueur.</b>
	 * Retire chaque carte de la pioche, et les ajoute à la main du joueur.
	 * @param nombrePioche le nombre de cartes que le joueur doit piocher
	 */
	public void piocher(int nombrePioche) {
		System.out.println("\n" + this.name + " pioche " + nombrePioche + " carte(s) !");

		if (Partie.getPartie().getManche().getPioche().getCartes().size() < nombrePioche) {
			Partie.getPartie().getManche().getTalon().devenirPioche();
		}
		for (int i = 1; i <= nombrePioche; i++) {
			Carte cartePioche = Partie.getPartie().getManche().getPioche().cartes.pollLast(); 
			cartes.add(cartePioche);
			if (this instanceof JoueurPhysique) {
				System.out.println("\n" + this.name + " a pioche " + cartePioche);
			}
		}
		this.setChanged();
		this.notifyObservers("a pioche "+ nombrePioche + " carte(s)!");
	}

	/**
	 * <b>Méthode permettant de vérifier si le joueur a gagné ou non.</b>
	 * @return Un booléen valant vrai si le joueur n'a plus de cartes, et faux si il lui en reste.
	 */
	public boolean gagnePartie() {
		
		if (this.cartes.isEmpty()) {
			System.out.println(this.name + " a gagne.");
			Partie.getPartie().getManche().getClassementJoueurs().add(this);
			Partie.getPartie().getManche().getJoueur().remove(this);
			Partie.getPartie().getManche().setNbJoueursEnCours(Partie.getPartie().getManche().getNbJoueursEnCours() - 1);
			this.setChanged();
			this.notifyObservers("gagne");
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
	
	
	
	/**
	 * @return the aDitcarte
	 */
	public boolean isaDitcarte() {
		return aDitcarte;
	}

	/**
	 * @param aDitcarte the aDitcarte to set
	 */
	public void setaDitcarte() {
		
		this.aDitcarte = true;
		this.setChanged();
		this.notifyObservers("CARTE ! ");
	}

	/**
	 * @return the contreCarte
	 */
	public boolean isContreCarte() {
		return contreCarte;
	}

	/**
	 * @param contreCarte the contreCarte to set
	 */
	public void setContreCarte() {
		
		this.contreCarte = true;	
		this.setChanged();
		this.notifyObservers("CONTRE-CARTE ! ");
		this.piocher(1);	
	}

	/**
	 * @return the typeInterface
	 */
	public String getTypeInterface() {
		return typeInterface;
	}

	public void changed()
	{
		this.setChanged();
	}
}