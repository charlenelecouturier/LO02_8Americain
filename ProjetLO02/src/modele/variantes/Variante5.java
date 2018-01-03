package modele.variantes;
import modele.*;

import java.util.Iterator;

public class Variante5 extends Variante {


	public Variante5(int nbJoueursVirtuels) {

		int nbPaquet = 1;
		if (nbJoueursVirtuels > 4) {
			nbPaquet += (nbJoueursVirtuels + 1) / 5;
		}
		this.nbCartes = 52 * nbPaquet;
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
				carteNext.setEffet("Pioche2OuAsOu8");
			} else if (carteNext.getValeur().equals("7")) {
				carteNext.setEffet("ChangerSens");
			}

		} while (it.hasNext());
	}
}