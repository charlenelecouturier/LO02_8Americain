package modele.variantes;
import modele.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

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
				carteNext.setEffet("Changer Famille");
			} else if (carteNext.getValeur().equals("10")) {
				carteNext.setEffet("Oblige a rejouer");
			} else if (carteNext.getValeur().equals("1")) {
				carteNext.setEffet("Pioche 2 cartes ou joue AS");
			} else if (carteNext.getValeur().equals("VALET")) {
				if (this.nbJoueursVirtuels == 1) {
					carteNext.setEffet("Oblige a rejouer");
				} else {
					carteNext.setEffet("Changer Sens");
				}
			} else if (carteNext.getValeur().equals("7")) {
				if (this.nbJoueursVirtuels == 1) {
					carteNext.setEffet("Oblige a rejouer");
				} else {
					carteNext.setEffet("Bloquer Suivant");
				}
			} else if (carteNext.getValeur().equals("DAME") && carteNext.getSymbole().equals("TREFLE")) {
				carteNext.setEffet("Pioche 3 cartes");
			}
		} while (it.hasNext());
	}

	@Override
	public HashSet<Carte> creerJeuDeCartes(int nbPaquet) {
		HashSet<Carte> jeuDeCartes = new HashSet<Carte>();
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
		return jeuDeCartes;
	}

}