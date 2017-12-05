package pckg;

import java.util.ArrayList;

public class Variante5 extends Variante {

	public static int nombreAs = 0;

	@Override
	public boolean estPossibleDeJouer(ArrayList<Carte> carte) {
		Joueur joueurActuel = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur() - 1);

		int i;
		if (joueurActuel.EffetVariante.equals("Aucun")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole().equals(Partie.getPartie().getTalon().getCarteDessus().getSymbole())
						|| carte.get(i).getValeur().equals(Partie.getPartie().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8"))
				// Si une des cartes a le meme symbole que le talon ou la meme valeur , ou si
				// cette carte est un 8
				{
					return true; // le joueur peut jouer
				}
			}
		} 
		else if (joueurActuel.EffetVariante.equals("Pioche2ouAsou8")) {
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
		}

		return false; // le joueur ne peut pas jouer
	}

	@Override
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
					|| carteValeur.equals("8")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else /* if (joueurActuel.EffetVariante.equals("Variante5")) */ {

			if (carteValeur.equals(carteDessusTalonValeur) || carteValeur.equals("8")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		}

	}

	public Variante5(int nbJoueursVirtuels) {

		int nbPaquet = 1;
		// si il ya plus de 5 joueurs en tout au départ on rentre dans la boucle if(),
		// car on utilise 1 paquet pour 5 joueur

		if (nbJoueursVirtuels > 4) { // boucle infinie ici{

			nbPaquet += (nbJoueursVirtuels + 1) / 5;
		}
		this.nbCartes = 52 * nbPaquet;
		this.jeuDeCartes = this.creerJeuDeCartes(nbPaquet);

	}

	@Override
	public ArrayList<Carte> creerJeuDeCartes(int nbPaquet) {
		// TODO Auto-generated method stub

		ArrayList<Carte> jeuDeCartes = new ArrayList<Carte>();
		int k;
		// nb cartes definie dans la variante
		for (k = 1; k <= nbPaquet; k++) {
			int i, j;
			String[] valeurs = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "VALET", "DAME",
					"ROI" };

			for (i = 0; i < Carte.symboles.length; i++) {

				for (j = 0; j < valeurs.length; j++) {
					Carte carte = new Carte(valeurs[j], Carte.symboles[i]);
					if (carte.getValeur().equals("8")) {
						carte.setEffet("ChangerFamille");
					} else if (carte.getValeur().equals("10")) {
						carte.setEffet("ObligeRejouer");
					} else if (carte.getValeur().equals("1")) {
						carte.setEffet("Pioche2OuAsOu8");
					} else if (carte.getValeur().equals("7")) {
						carte.setEffet("ChangerSens");
					}
					jeuDeCartes.add(carte);
				}
			}
		}
		return jeuDeCartes;

	}
}