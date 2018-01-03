package modele.variantes;
import modele.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Variante7 extends Variante {

	private int nbJoueursVirtuels;

	public Variante7(int nbJoueursVirtuels) {
		this.nbJoueursVirtuels = nbJoueursVirtuels;
		int nbPaquet = 1;
		if (nbJoueursVirtuels > 2) {
			nbPaquet += (nbJoueursVirtuels + 1) / 3;
		}
		this.nbCartes = 32 * nbPaquet;
		this.jeuDeCartes = this.creerJeuDeCartes(nbPaquet);
		this.assignerEffetCarte();
	}

	@Override
	public void assignerEffetCarte() {
		Iterator<Carte> it = this.jeuDeCartes.iterator();
		Carte carteNext;
		do {
			carteNext = it.next();
			if (carteNext.getValeur().equals("8")) {
				carteNext.setEffet("ChangerFamille");
			} else if (carteNext.getValeur().equals("10")) {
				carteNext.setEffet("ObligeRejouer");
			} else if (carteNext.getValeur().equals("1")) {
				carteNext.setEffet("Pioche 2 cartes ou joue AS");
			} else if (carteNext.getValeur().equals("VALET")) {
				if (this.nbJoueursVirtuels == 1) {
					carteNext.setEffet("ObligeRejouer");
				} else {
					carteNext.setEffet("ChangerSens");
				}
			} else if (carteNext.getValeur().equals("7")) {
				if (this.nbJoueursVirtuels == 1) {
					carteNext.setEffet("ObligeRejouer");
				} else {
					carteNext.setEffet("BloquerSuivant");
				}
			} else if (carteNext.getValeur().equals("DAME") && carteNext.getSymbole().equals("TREFLE")) {
				carteNext.setEffet("Pioche 3 cartes");
			}

		} while (it.hasNext());
	}

	@Override
	public ArrayList<Carte> creerJeuDeCartes(int nbPaquet) {
		ArrayList<Carte> jeuDeCartes = new ArrayList<Carte>();
		int k;
		for (k = 1; k <= nbPaquet; k++) {
			int i, j;
			String[] valeurs = new String[] { "1","7", "8", "9", "10", "VALET", "DAME", "ROI" };
			for (i = 0; i < Carte.symboles.length; i++) {
				for (j = 0; j < valeurs.length; j++) {
					Carte carte = new Carte(valeurs[j], Carte.symboles[i]);
					jeuDeCartes.add(carte);
				}
			}
		}
		System.out.println(jeuDeCartes.size());
		return jeuDeCartes;
	}

}