package modele;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

/**
 * <b>Pioche est la classe qui repr�sente la pioche.</b>
 *  Elle permet de m�langer le
 * paquet de cartes, de distribuer des cartes al�atoirement aux joueurs.
 * 
 * @author Robin et Charlene
 * @version 1.0
 * @see PorteurCarte
 * 
 */
public class Pioche extends Observable {
	protected LinkedList<Carte> cartes = new LinkedList<Carte>();

	/**
	 * Constructeur de la classe Pioche, il permet de placer le paquet de cartes cr��
	 * dans la variante dans la pioche. Chaque carte appartient � une famille et a
	 * une valeur, et chaque carte est unique.
	 * 
	 * 
	 * @see Variante#creerJeuDeCartes(int nbPaquet)
	 */
	public Pioche() {
		Iterator<Carte> it = Partie.getPartie().getManche().getVarianteManche().getCartes().iterator();
		while (it.hasNext()) {
			Carte carte = it.next();
			this.cartes.add(carte); // on place tout le jeu de cartes cr�� dans la variante dans la pioche
		}
		System.out.println(this.cartes.size());

	}

	/**
	 * <b> Méthode permettant de mélanger le paquet de carte présent dans la pioche.</b>
	 * <p> la méthode place les cartes au hasard dans la liste de cartes.
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
	
	/**
	 * <b>Distribue les cartes aux joueurs de la Partie.</b>
	 * <p>la méthode prend les cartes du dessus de la pioche, les enlève et les place dans la main du joueur.</p>
	 */
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

}