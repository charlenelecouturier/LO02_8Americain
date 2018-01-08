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

	
	public void assignerEffetCarte() {
		Iterator<Carte> it = this.jeuDeCartes.iterator();
		Carte carteNext;
		do {
			carteNext = it.next();
			if (carteNext.getValeur().equals("8")) {
				carteNext.setEffet("Changer Famille");
			} else if (carteNext.getValeur().equals("10")) {
				carteNext.setEffet("Oblige a rejouer");
			} else if (carteNext.getValeur().equals("1")) {
				carteNext.setEffet("+ 2 ou joue AS ou 8");
			} else if (carteNext.getValeur().equals("7")) {
				carteNext.setEffet("Changer Sens");
			}

		} while (it.hasNext());
	}
}