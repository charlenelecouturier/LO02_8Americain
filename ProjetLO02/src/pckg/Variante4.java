package pckg;

import java.util.ArrayList;

public class Variante4 extends Variante {

	public static Carte couleur = new Carte(" ", " "); // couleur de l'as qui permet de se defausser de toutes ses
														// cartes de meme couleur
	public Variante4(int nbJoueursVirtuels) {

		int nbPaquet = 1;
		// si il ya plus de 5 joueurs en tout au départ on rentre dans la boucle if(),
		// car on utilise 1 paquet pour 5 joueur
		if (nbJoueursVirtuels > 4) { // boucle infinie ici{
			nbPaquet += (nbJoueursVirtuels + 1) / 5;
		}
		this.nbCartes = 53 * nbPaquet;
		this.jeuDeCartes = this.creerJeuDeCartes(nbPaquet);
	}
	
	@Override
	public ArrayList<Carte> creerJeuDeCartes(int nbPaquet) {
		ArrayList<Carte> jeuDeCartes = new ArrayList<Carte>();
		int k;
		for (k = 1; k <= nbPaquet; k++) {
			int i, j;
			String[] valeurs = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "VALET", "DAME","ROI" };
			for (i = 0; i < Carte.symboles.length; i++) {
				for (j = 0; j < valeurs.length; j++) {
					Carte carte = new Carte(valeurs[j], Carte.symboles[i]);
					if (carte.getValeur().equals("8")) {
						carte.setEffet("ChangerFamille");
					} else if (carte.getValeur().equals("10")) {
						carte.setEffet("ChangerSens");
					} else if (carte.getValeur().equals("VALET")) {
						carte.setEffet("BloquerSuivant");
					} else if (carte.getValeur().equals("2")) {
						carte.setEffet("Piocher2");
						if (carte.getValeur().equals("2") && carte.getSymbole().equals("PIQUE")) {
							carte.setEffet("Piocher4");
						}
					} else if (carte.getValeur().equals("1")) {
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