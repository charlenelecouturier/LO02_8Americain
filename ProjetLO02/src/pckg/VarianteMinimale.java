package pckg;

import java.util.Iterator;

public class VarianteMinimale extends Variante {

	public VarianteMinimale(int nbJoueursVirtuels) {
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
				carteNext.setEffet("ChangerFamille");
			} else if (carteNext.getValeur().equals("10")) {
				carteNext.setEffet("ObligeRejouer");
			}
		} while (it.hasNext());
	}
}