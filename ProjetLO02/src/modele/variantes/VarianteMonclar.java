package modele.variantes;

import modele.*;

import java.util.Iterator;

public class VarianteMonclar extends Variante {

	private int nbJoueursVirtuels;

	public VarianteMonclar(int nbJoueursVirtuels) {
		this.nbJoueursVirtuels = nbJoueursVirtuels;
		int nbPaquet = 1;
		if (nbJoueursVirtuels > 4) {
			nbPaquet += (nbJoueursVirtuels + 1) / 5;
		}
		this.nbCartes = 52 * nbPaquet;
		this.jeuDeCartes = this.creerJeuDeCartes(nbPaquet);
		this.assignerEffetCarte();
	}

	public void assignerEffetCarte() {
		Iterator<Carte> it = this.jeuDeCartes.iterator();
		Carte carteNext;
		do {
			carteNext = it.next();
			if (carteNext.getValeur().equals("8")) {
				carteNext.setEffet("Changer Famille");
			} else if (carteNext.getValeur().equals("VALET")) {
				carteNext.setEffet("Changer Sens");
			} else if (carteNext.getValeur().equals("7")) {
				if (this.nbJoueursVirtuels == 1) {
					carteNext.setEffet("Oblige a rejouer");
				} else {
					carteNext.setEffet("Bloquer Suivant");
				}
			} else if (carteNext.getValeur().equals("10")) {
				carteNext.setEffet("Oblige a rejouer");
			} else if (carteNext.getValeur().equals("9")) {
				carteNext.setEffet("Fait piocher 1 carte");
			} else if (carteNext.getValeur().equals("1")) {
				carteNext.setEffet("Pioche 3 cartes ou joue un AS ou un 8");
			}

		} while (it.hasNext());
	}
}