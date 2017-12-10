package pckg;
import java.util.LinkedList;
import java.util.Random;

/**
 * Pioche est la classe qui représente la pioche. Elle permet de mélanger le
 * paquet de cartes, de distribuer des cartes aléatoirement aux joueurs, et aux
 * joueurs de piocher. Elle hérite de PorteurCarte qui définit les collections
 * de cartes.
 * 
 * @author Robin & Charlène
 * @see PorteurCarte
 * 
 */
public class Pioche {
	protected LinkedList<Carte> cartes = new LinkedList<Carte>();

	/**
	 * Constructeur de la classe Pioche, il permet placer le paquet de cartes créé
	 * dans la variante dans la pioche. Chaque carte appartient à une famille et a
	 * une valeur, et chaque carte est unique.
	 * 
	 * @see Variante
	 */
	public Pioche() {
		// nb cartes definie dans la variante
		int i;
		int nbCartes = Partie.getPartie().getVariantePartie().getNbCartes();
		for (i = nbCartes - 1; i >= 0; i--) {

			Carte carte = Partie.getPartie().getVariantePartie().getCartes().get(i);
			Partie.getPartie().getVariantePartie().getCartes().remove(i);
			this.cartes.add(carte); // on place tout le jeu de cartes créé dans la variante dans la pioche
		}
	}

	/**
	 * Méthode permettant à un joueur de piocher une carte dans la pioche.
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
			// on échange les cartes
			Carte c = this.cartes.get(place); // on met dans la référence c la carte qui est la place "place"
			this.cartes.set(place, this.cartes.get(i)); // on met à la position "place" la carte qui est à la position i
			this.cartes.set(i, c);// on met à la position "i" la carte qui est reférencée par c

		}
	}
	public void distribuer() {
		int nbJoueurs = Partie.getPartie().getNbJoueursVirtuels() + 1;
		int tour = 0;
		int i;
		while (tour < 8) // on distribue 8 cartes chacuns
		{
			for (i = 0; i < nbJoueurs; i++) {
				Partie.getPartie().getJoueur().get(i).getCartes().add(this.cartes.get(this.cartes.size() - 1));
				this.cartes.remove(this.cartes.size() - 1); // on supprime cette carte de la pioche
				// la carte du dessus de la pioche va dans le jeu de carte du joueur "i" au tour
			}
			tour++;
		}
		Partie.getPartie().getTalon().getCartes().add(this.cartes.get(this.cartes.size() - 1));
		Partie.getPartie().getTalon().getCarteDessus().setSymbole(this.cartes.get(this.cartes.size() - 1).getSymbole());
		Partie.getPartie().getTalon().getCarteDessus().setValeur(this.cartes.get(this.cartes.size() - 1).getValeur());
		// On la supprime du paquet de carte de la pioche
		this.cartes.remove(this.cartes.size() - 1);
		System.out.println("Test : nombre de cartes du joueur 1 : " + Partie.getPartie().getJoueur().get(0).getCartes().size());
		System.out.println("Test : nombre de cartes de la pioche : " + this.cartes.size() + "\n");
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