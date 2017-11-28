package pckg;

import java.util.ArrayList;

public class Variante4 extends Variante {

	public static Carte couleur=new Carte(" "," "); // couleur de l'as qui permet de se defausser de toutes ses cartes de meme couleur

	public Variante4(int nbJoueursVirtuels) {

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
	public boolean estPossibleDeJouer(ArrayList<Carte> carte) {
		Joueur joueurActuel = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur() - 1);

		int i;
		if (joueurActuel.EffetVariante.equals("Aucun")) {
			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole().equals(Partie.getPartie().getTalon().getCarteDessus().getSymbole())
						|| carte.get(i).getValeur().equals(Partie.getPartie().getTalon().getCarteDessus().getValeur())
						|| carte.get(i).getValeur().equals("8") || carte.get(i).getValeur().equals("JOKER"))
				// Si une des cartes a le meme symbole que le talon ou la meme valeur , ou si
				// cette carte est un 8
				{
					return true; // le joueur peut jouer
				}
			}
		} 
		else if (joueurActuel.EffetVariante.equals("Bloqué")) {

			System.out.println(joueurActuel.getName() + " est bloqué !");

		}
		else if (joueurActuel.EffetVariante.equals("JouerMêmeCouleur")) {

			for (i = 0; i < carte.size(); i++) {
				if (carte.get(i).getSymbole().equals(Variante4.couleur.getSymbole()))
				// Si une des cartes a le meme symbole que le talon ou la meme valeur , ou si
				// cette carte est un 8
				{
					return true; // le joueur peut jouer
				}
			}

			
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
					|| carteValeur.equals("8") || carteValeur.equals("JOKER")) {
				return true; // la carte choisie par le joueur est compatible
			} else {
				return false;
			}
		} else if (joueurActuel.EffetVariante.equals("JouerMêmeCouleur")) {

			if (carteSymbole.equals(Variante4.couleur.getSymbole())) {
				return true; // la carte choisie par le joueur est compatible
			}

			else {
				return false;
			}
		}
		return false;

	}

	@Override
	public ArrayList<Carte> creerJeuDeCartes(int nbPaquet) {
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

					} 
					else if (carte.getValeur().equals("10")) {
						carte.setEffet("ChangerSens");
					}
					else if (carte.getValeur().equals("VALET")) {
						carte.setEffet("BloquerSuivant");
					} 
					else if (carte.getValeur().equals("2")) {
						carte.setEffet("Piocher2");
						if (carte.getValeur().equals("2") && carte.getSymbole().equals("PIQUE")) {
							carte.setEffet("Piocher4");
						}

					} 
					else if (carte.getValeur().equals("1")) {
						carte.setEffet("JouerToutesCartesMemeSymbole");
					}

					jeuDeCartes.add(carte);
				}
			}

			Carte carte = new Carte("JOKER", "JOKER");
			carte.setEffet("ChangerFamilleEtPioche5");
			jeuDeCartes.add(carte);


		}
		return jeuDeCartes;
	}
}
