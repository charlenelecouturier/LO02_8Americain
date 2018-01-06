package modele;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

/**
 * Pioche est la classe qui repr�sente la pioche. Elle permet de m�langer le
 * paquet de cartes, de distribuer des cartes al�atoirement aux joueurs, et aux
 * joueurs de piocher. Elle h�rite de PorteurCarte qui d�finit les collections
 * de cartes.
 * 
 * @author Robin & Charl�ne
 * @see PorteurCarte
 * 
 */
public class Pioche extends Observable {
	protected LinkedList<Carte> cartes = new LinkedList<Carte>();

	/**
	 * Constructeur de la classe Pioche, il permet placer le paquet de cartes cr��
	 * dans la variante dans la pioche. Chaque carte appartient � une famille et a
	 * une valeur, et chaque carte est unique.
	 * 
	 * @see Variante
	 */
	public Pioche() {
		// nb cartes definie dans la variante
		int i;
		int nbCartes = Partie.getPartie().getManche().getVarianteManche().getNbCartes();
		for (i = nbCartes - 1; i >= 0; i--) {

			Carte carte = Partie.getPartie().getManche().getVarianteManche().getCartes().get(i);
			Partie.getPartie().getManche().getVarianteManche().getCartes().remove(i);
			this.cartes.add(carte); // on place tout le jeu de cartes cr�� dans la variante dans la pioche
		}
		
	}

	/**
	 * M�thode permettant � un joueur de piocher une carte dans la pioche.
	 * @param cartes donne la main du joueur, pour y ajouter une carte
	 * @param nombrePioche indique combien de cartes le joueur doit piocher
	 * @see Joueur
	 */

	public void melanger() {

		int place;
		int i;

		for (i = 0; i < this.cartes.size(); i++) {

			Random r = new Random();
			place = 0 + r.nextInt(this.cartes.size() - 1);// on choisit une place aleatoirement dans le paquet (nombre
															// choisi entre 0 et nbCartes-1)
			// on �change les cartes
			Carte c = this.cartes.get(place); // on met dans la r�f�rence c la carte qui est la place "place"
			this.cartes.set(place, this.cartes.get(i)); // on met � la position "place" la carte qui est � la position i
			this.cartes.set(i, c);// on met � la position "i" la carte qui est ref�renc�e par c

		}
	}
	public void distribuer() {
		int nbJoueurs = Partie.getPartie().getNbJoueursVirtuels() + 1;
		int tour = 0;
		int i;
		while (tour < 8) // on distribue 8 cartes chacuns
		{
			for (i = 0; i < nbJoueurs; i++) {
				Partie.getPartie().getManche().getJoueur().get(i).getCartes().add(this.cartes.get(this.cartes.size() - 1));
				this.cartes.remove(this.cartes.size() - 1); // on supprime cette carte de la pioche
				// la carte du dessus de la pioche va dans le jeu de carte du joueur "i" au tour
			}
			tour++;
		}
		Partie.getPartie().getManche().getTalon().getCartes().add(this.cartes.get(this.cartes.size() - 1));
		Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(this.cartes.get(this.cartes.size() - 1).getSymbole());
		Partie.getPartie().getManche().getTalon().getCarteDessus().setValeur(this.cartes.get(this.cartes.size() - 1).getValeur());
		// On la supprime du paquet de carte de la pioche
		this.cartes.remove(this.cartes.size() - 1);
	}
	/**
	 * @return the cartes
	 */
	public LinkedList<Carte> getCartes() {
		return cartes;
	}

	/**
	 * @param cartes
	 *            the cartes to set
	 */
	public void LinkedCartes(LinkedList<Carte> cartes) {
		this.cartes = cartes;
	}
}