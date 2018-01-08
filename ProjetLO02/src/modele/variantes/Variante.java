package modele.variantes;

import modele.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * <b> Classe abstraites qui représente les différentes variantes de la partie. </b>
 * <p> Chaque Variante possède ses règles propres. 
 * la classe Variante est donc responsable de faire appliquer ces règles. Elle définit le nombre de cartes à distribuer. 
 * Consultez la page wikipédia du 8 américain pour plus d'informations sur les règles des différentes variantes.</p>
 * @author Charlene et Robin
 * @version 1.0
 * 
 * @see https://fr.wikipedia.org/wiki/8_am%C3%A9ricain
 */
public abstract class Variante {

	protected int nbCartes;
	protected HashSet<Carte> jeuDeCartes;
	public static int nombreAs = 0;

	/**
	 * @param nbCartes
	 *            the nbCartes to set
	 */
	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}
/**
 * <b>Vérifie qu'un joueur peut jouer, c'est à dire qu'une règle ne l'empêche pas de jouer son tour(et non pas qu'il n'a pas de cartes posable.)</b>
 * 
 * @param carte La liste de ses cartes
 * @return true si le joueur peut jouer, false sinon.
 */
	public boolean estPossibleDeJouer(ArrayList<Carte> carte) {
		int i;
		Joueur joueurActuel = Partie.getPartie().getManche().getJoueur()
				.get(Partie.getPartie().getManche().getTourJoueur() - 1);
		if (joueurActuel.getEffetVariante().equals("Aucun")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole()
						.equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getSymbole())
						|| carte.get(i).getValeur()
								.equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8") || carte.get(i).getValeur().equals("JOKER")
						|| Partie.getPartie().getManche().getTalon().getCarteDessus().getSymbole().equals("JOKER")) {
					return true; // le joueur peut jouer
				}

			}
			System.out.println(joueurActuel.getName() + " ne peut pas jouer !");
			joueurActuel.piocher(1);
		} else if (joueurActuel.getEffetVariante().equals("Pioche2ouAsou8")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getValeur()
						.equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8")) {
					return true; // le joueur peut jouer
				}
			}
			System.out.println(joueurActuel.getName() + " n'a ni d'As ni de 8 ...");
			joueurActuel.piocher(2 * Variante.nombreAs);
			Variante.nombreAs = 0;
		} else if (joueurActuel.getEffetVariante().equals("Pioche2ouAs")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getValeur()
						.equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getValeur())) {
					return true; // le joueur peut jouer
				}
			}
			System.out.println(joueurActuel.getName() + " n'a ni d'As ni de 8 ...");
			joueurActuel.piocher(2 * Variante.nombreAs);
			Variante.nombreAs = 0;
		} else if (joueurActuel.getEffetVariante().equals("Pioche3ouAsou8")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getValeur()
						.equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8")) {
					return true; // le joueur peut jouer
				}
			}
			System.out.println(joueurActuel.getName() + " n'a ni d'As ni de 8 ...");
			joueurActuel.piocher(3 * Variante.nombreAs);
			Variante.nombreAs = 0;
		} else if (joueurActuel.getEffetVariante().equals("Bloquer")) {

			System.out.println(joueurActuel.getName() + " est bloque !");

		} else if (joueurActuel.getEffetVariante().equals("JouerMemeCouleur")) {

			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole()
						.equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getSymbole())) {
					return true; // le joueur peut jouer
				}
			}
		}
		return false; // le joueur ne peut pas jouer
	}
/**
 * <b> Vérifie qu'une carte est compatible avec celle posée sur le talon, conformément aux règles de la partie. </b>
 * @param carte la carte en question
 * @return true si la carte est jouable, false sinon.
 */
	public boolean estCompatible(Carte carte) {
		Joueur joueurActuel = Partie.getPartie().getManche().getJoueur()
				.get(Partie.getPartie().getManche().getTourJoueur() - 1);
		Carte carteDessusTalon;
		Talon talon = Partie.getPartie().getManche().getTalon();
		carteDessusTalon = talon.getCarteDessus();
		String carteSymbole = carte.getSymbole();
		String carteDessusTalonSymbole = carteDessusTalon.getSymbole();
		String carteValeur = carte.getValeur();
		String carteDessusTalonValeur = carteDessusTalon.getValeur();
		if (joueurActuel.getEffetVariante().equals("Aucun")) {
			if (carteSymbole.equals(carteDessusTalonSymbole) || carteValeur.equals(carteDessusTalonValeur)
					|| carteValeur.equals("8") || carteValeur.equals("JOKER")
					|| carteDessusTalonSymbole.equals("JOKER")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else if (joueurActuel.getEffetVariante().equals("JouerMemeCouleur")) {
			if (carteSymbole.equals(carteDessusTalon.getSymbole())) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else if (joueurActuel.getEffetVariante().equals("Pioche2ouAsou8")
				|| joueurActuel.getEffetVariante().equals("Pioche3ouAsou8")) {
			if (carteValeur.equals(carteDessusTalonValeur) || carteValeur.equals("8")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else if (joueurActuel.getEffetVariante().equals("Pioche2ouAs")) {
			if (carteValeur.equals(carteDessusTalonValeur)) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * <b> Méthode permettant de créer un ou des jeux de cartes de base (52 cartes sans JOKER). </b>
	 * <p> La méthode est redéfinie dans les variantes qui nécessitent des paquets spéciaux. </p>
	 * @param nbPaquet le nombre de paquet à créer
	 * @return Les jeux de cartes créés, non mélangés.
	 */
	public HashSet<Carte> creerJeuDeCartes(int nbPaquet) {
		HashSet<Carte> jeuDeCartes = new HashSet<Carte>();
		int k;
		for (k = 1; k <= nbPaquet; k++) {
			int i, j;
			String[] valeurs = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "VALET", "DAME",
					"ROI" };
			for (i = 0; i < Carte.symboles.length; i++) {
				for (j = 0; j < valeurs.length; j++) {
					Carte carte = new Carte(valeurs[j], Carte.symboles[i]);
					jeuDeCartes.add(carte);
				}
			}
		}
		return jeuDeCartes;
	}

	public HashSet<Carte> getCartes() {
		return jeuDeCartes;
	}

	public int getNbCartes() {
		return nbCartes;
	}
/**
 * <b>Méthode redéfinie dans chaque Variante, qui assigne à chaque carte des paquets leurs effets en fonction de la variante. </b>
 * 
 * @see Effet
 */
	public abstract void assignerEffetCarte();
}