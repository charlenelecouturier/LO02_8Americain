package pckg;

import java.util.ArrayList;

public class VarianteMinimale extends Variante {

	public VarianteMinimale(int nbJoueursVirtuels) {

		int nbPaquet = 1;
		if (nbJoueursVirtuels > 4) { // boucle infinie ici{
			nbPaquet += (nbJoueursVirtuels + 1) / 5;
		}
		this.nbCartes = 52 * nbPaquet;
		this.jeuDeCartes = this.creerJeuDeCartes(nbPaquet);
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
					} else if (carte.getValeur().equals("10")) {
						carte.setEffet("ObligeRejouer");
					}
					jeuDeCartes.add(carte);
				}
			}
		}
		return jeuDeCartes;
	}
}