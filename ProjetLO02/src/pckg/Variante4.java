package pckg;

import java.util.ArrayList;
import java.util.Iterator;

public class Variante4 extends Variante {

	public static Carte couleur = new Carte(" ", " ");

	public Variante4(int nbJoueursVirtuels) {

		int nbPaquet = 1;
		if (nbJoueursVirtuels > 4) {
			nbPaquet += (nbJoueursVirtuels + 1) / 5;
		}
		this.nbCartes = 53 * nbPaquet;
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
				carteNext.setEffet("ChangerSens");
			} else if (carteNext.getValeur().equals("VALET")) {
				carteNext.setEffet("BloquerSuivant");
			} else if (carteNext.getValeur().equals("2")) {
				carteNext.setEffet("Piocher2");
				if (carteNext.getValeur().equals("2") && carteNext.getSymbole().equals("PIQUE")) {
					carteNext.setEffet("Piocher4");
				}
			} else if (carteNext.getValeur().equals("1")) {
				carteNext.setEffet("JouerToutesCartesMemeSymbole");
			} else if (carteNext.getValeur().equals("JOKER")) {
				carteNext.setEffet("ChangerFamilleEtPioche5");
			}

		} while (it.hasNext());
	}

	@Override // on doit rajouter les JOKERS
	public ArrayList<Carte> creerJeuDeCartes(int nbPaquet) {
		ArrayList<Carte> jeuDeCartes = new ArrayList<Carte>();
		jeuDeCartes = super.creerJeuDeCartes(nbPaquet);
		int i;
		for(i=0;i<nbPaquet;i++) {
		Carte carte = new Carte("JOKER", "JOKER");
		jeuDeCartes.add(carte);
		}
		return jeuDeCartes;
	}
}