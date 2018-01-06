package modele.variantes;
import modele.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Variante4 extends Variante {

	public Variante4(int nbJoueursVirtuels) {

		int nbPaquet = 1;
		if (nbJoueursVirtuels > 4) {
			nbPaquet += (nbJoueursVirtuels + 1) / 5;
		}
		this.nbCartes = 54 * nbPaquet;
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
				carteNext.setEffet("Changer Sens");
			} else if (carteNext.getValeur().equals("VALET")) {
				carteNext.setEffet("Bloquer Suivant");
			} else if (carteNext.getValeur().equals("2")) {
				carteNext.setEffet("Fait piocher 2 cartes");
				if (carteNext.getValeur().equals("2") && carteNext.getSymbole().equals("PIQUE")) {
					carteNext.setEffet("Fait piocher 4 cartes");
				}
			} else if (carteNext.getValeur().equals("1")) {
				carteNext.setEffet("Defausser tous les mêmes symboles");
			} else if (carteNext.getValeur().equals("JOKER")) {
				carteNext.setEffet("Changer Famille + Piocher 5 cartes");
			}

		} while (it.hasNext());
	}

	@Override // on doit rajouter les JOKERS
	public LinkedList<Carte> creerJeuDeCartes(int nbPaquet) {
		LinkedList<Carte> jeuDeCartes = new LinkedList<Carte>();
		jeuDeCartes = super.creerJeuDeCartes(nbPaquet);
		int i;
		for (i = 0; i < nbPaquet; i++) {
			Carte carteJok1 = new Carte("JOKER", "JOKER");
			jeuDeCartes.add(carteJok1);
			Carte carteJok2 = new Carte("JOKER", "JOKER");
			jeuDeCartes.add(carteJok2);
		}
		return jeuDeCartes;
	}
}