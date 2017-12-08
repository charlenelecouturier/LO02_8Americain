package pckg;

import java.util.ArrayList;

/**
 * 
 */

/**
 * @author charlene
 *
 */
public abstract class Variante {

	/**
	 * @param args
	 */
	protected int nbCartes;
	protected ArrayList<Carte> jeuDeCartes;

	public abstract ArrayList<Carte> creerJeuDeCartes(int nbPaquet);

	/**
	 * @param nbCartes the nbCartes to set
	 */
	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}

	public boolean estPossibleDeJouer(ArrayList<Carte> carte) {
		int i;
		Joueur joueurActuel = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur() - 1);
		if (joueurActuel.EffetVariante.equals("Aucun")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole().equals(Partie.getPartie().getTalon().getCarteDessus().getSymbole())
						|| carte.get(i).getValeur().equals(Partie.getPartie().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8")|| carte.get(i).getValeur().equals("JOKER"))
				// Si une des cartes a le meme symbole que le talon ou la meme valeur , ou si
				// cette carte est un 8
				{
					return true; // le joueur peut jouer
				}
			}
		} else if (joueurActuel.EffetVariante.equals("Pioche2ouAsou8")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getValeur().equals(Partie.getPartie().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8"))
				// Si une des cartes a le meme valeur que le talon (1) ou la meme valeur , ou si
				// cette carte est un 8, on bloque l'effet
				{
					return true; // le joueur peut jouer
				}
			}
			System.out.println(joueurActuel.getName() + " n'a ni d'As ni de 8 ...");
			joueurActuel.piocher(2 * Variante5.nombreAs);
			Variante5.nombreAs = 0;
		} else if (joueurActuel.EffetVariante.equals("Bloqué")) {

			System.out.println(joueurActuel.getName() + " est bloqué !");

		} else if (joueurActuel.EffetVariante.equals("JouerMêmeCouleur")) {

			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole().equals(Variante4.couleur.getSymbole())) {
					return true; // le joueur peut jouer
				}
			}
		}
		return false; // le joueur ne peut pas jouer
	}

	public boolean estCompatible(Carte carte) {
		Joueur joueurActuel = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur() - 1);
		Carte carteDessusTalon;
		Talon talon = Partie.getPartie().getTalon();
		carteDessusTalon = talon.getCarteDessus();
		String carteSymbole = carte.getSymbole();
		String carteDessusTalonSymbole = carteDessusTalon.getSymbole();
		String carteValeur = carte.getValeur();
		String carteDessusTalonValeur = carteDessusTalon.getValeur();
		if (joueurActuel.EffetVariante.equals("Aucun")) {
			if (carteSymbole.equals(carteDessusTalonSymbole) || carteValeur.equals(carteDessusTalonValeur)
					|| carteValeur.equals("8") || carteValeur.equals("JOKER")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else if (joueurActuel.EffetVariante.equals("JouerMêmeCouleur")) {
			if (carteSymbole.equals(Variante4.couleur.getSymbole())) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else if (joueurActuel.EffetVariante.equals("Pioche2ouAsou8")) {
			if (carteValeur.equals(carteDessusTalonValeur) || carteValeur.equals("8")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		}
		return false;
	}

	public ArrayList<Carte> getCartes() {
		return jeuDeCartes;
	}

	public int getNbCartes() {
		return nbCartes;
	}
}