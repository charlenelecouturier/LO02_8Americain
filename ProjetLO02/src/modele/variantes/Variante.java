package modele.variantes;
import modele.*;

import java.util.ArrayList;

/**
 * @author Charlene et Robin
 *
 */
public abstract class Variante {

	/**
	 * @param args
	 */
	protected int nbCartes;
	protected ArrayList<Carte> jeuDeCartes;
	public static int nombreAs = 0;
	/**
	 * @param nbCartes the nbCartes to set
	 */
	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}

	public boolean estPossibleDeJouer(ArrayList<Carte> carte) {
		int i;
		Joueur joueurActuel = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur() - 1);
		if (joueurActuel.getEffetVariante().equals("Aucun")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole().equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getSymbole())
						|| carte.get(i).getValeur().equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8")|| carte.get(i).getValeur().equals("JOKER")){
					return true; // le joueur peut jouer
				}
			}
		} else if (joueurActuel.getEffetVariante().equals("Pioche2ouAsou8")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getValeur().equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8"))
				{
					return true; // le joueur peut jouer
				}
			}
			System.out.println(joueurActuel.getName() + " n'a ni d'As ni de 8 ...");
			joueurActuel.piocher(2 * Variante.nombreAs);
			Variante.nombreAs = 0;
		} else if (joueurActuel.getEffetVariante().equals("Pioche2ouAs")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getValeur().equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getValeur()))
				{
					return true; // le joueur peut jouer
				}
			}
			System.out.println(joueurActuel.getName() + " n'a ni d'As ni de 8 ...");
			joueurActuel.piocher(2 * Variante.nombreAs);
			Variante.nombreAs = 0;
		} else if (joueurActuel.getEffetVariante().equals("Pioche3ouAsou8")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getValeur().equals(Partie.getPartie().getManche().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8"))
				{
					return true; // le joueur peut jouer
				}
			}
			System.out.println(joueurActuel.getName() + " n'a ni d'As ni de 8 ...");
			joueurActuel.piocher(3 * Variante.nombreAs);
			Variante.nombreAs = 0;
		}else if (joueurActuel.getEffetVariante().equals("Bloquer")) {

			System.out.println(joueurActuel.getName() + " est bloque !");

		} else if (joueurActuel.getEffetVariante().equals("JouerMemeCouleur")) {

			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole().equals(Variante4.couleur.getSymbole())) {
					return true; // le joueur peut jouer
				}
			}
		}
		return false; // le joueur ne peut pas jouer
	}

	public boolean estCompatible(Carte carte) {
		Joueur joueurActuel = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur() - 1);
		Carte carteDessusTalon;
		Talon talon = Partie.getPartie().getManche().getTalon();
		carteDessusTalon = talon.getCarteDessus();
		String carteSymbole = carte.getSymbole();
		String carteDessusTalonSymbole = carteDessusTalon.getSymbole();
		String carteValeur = carte.getValeur();
		String carteDessusTalonValeur = carteDessusTalon.getValeur();
		if (joueurActuel.getEffetVariante().equals("Aucun")) {
			if (carteSymbole.equals(carteDessusTalonSymbole) || carteValeur.equals(carteDessusTalonValeur)
					|| carteValeur.equals("8") || carteValeur.equals("JOKER")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else if (joueurActuel.getEffetVariante().equals("JouerMemeCouleur")) {
			if (carteSymbole.equals(Variante4.couleur.getSymbole())) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else if (joueurActuel.getEffetVariante().equals("Pioche2ouAsou8")||joueurActuel.getEffetVariante().equals("Pioche3ouAsou8")) {
			if (carteValeur.equals(carteDessusTalonValeur) || carteValeur.equals("8")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		}else if (joueurActuel.getEffetVariante().equals("Pioche2ouAs")) {
			if (carteValeur.equals(carteDessusTalonValeur)) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		}
		return false;
	}
	
// Méthode permettant de créer un jeu de cartes de base (52 cartes sans JOKER)
	public ArrayList<Carte> creerJeuDeCartes(int nbPaquet) {
		ArrayList<Carte> jeuDeCartes = new ArrayList<Carte>();
		int k;
		for (k = 1; k <= nbPaquet; k++) {
			int i, j;
			String[] valeurs = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "VALET", "DAME","ROI" };
			for (i = 0; i < Carte.symboles.length; i++) {
				for (j = 0; j < valeurs.length; j++) {
					Carte carte = new Carte(valeurs[j], Carte.symboles[i]);
					jeuDeCartes.add(carte);
				}
			}
		}
		return jeuDeCartes;
	}

	public ArrayList<Carte> getCartes() {
		return jeuDeCartes;
	}

	public int getNbCartes() {
		return nbCartes;
	}
	
	public abstract void assignerEffetCarte();
}