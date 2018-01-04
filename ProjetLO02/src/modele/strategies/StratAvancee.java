package modele.strategies;
import modele.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.ListIterator;
import java.util.Iterator;
import modele.variantes.*;
public class StratAvancee implements Strategie {

	public int choixCarte(ArrayList<Carte> cartesCompatibles) {
		System.out.println("Carte du talon : " + Partie.getPartie().getManche().getTalon().getCarteDessus());
		System.out.println("Nombre de cartes compatibles : " + cartesCompatibles.size());
		Joueur joueurEnCours = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur() - 1);
		int carteChoisie;
		ListIterator<Carte> parcourirCartesCompatibles = cartesCompatibles.listIterator();
		// On regarde si le joueur a une carte "Rejouer" jouable : si oui, on regardera si elle lui permet de rejouer ou si il n'a que celleci de compatible
		Carte carteNext;
		do {
			carteNext = parcourirCartesCompatibles.next();
			if (carteNext.getEffet().equals("Oblige a rejouer")
					&& this.jouer10(carteNext, joueurEnCours, cartesCompatibles)) {
				carteChoisie = joueurEnCours.getCartes().indexOf(carteNext);
				return carteChoisie;
			}

		} while (parcourirCartesCompatibles.hasNext());

		System.out.println(
				"Oh.. Je n'ai pas de carte qui me permetterait de rejouer.. je joue n'importe quelle carte sauf une carte Changer de Famille");

		// Si le joueur ne joue pas de carte qui l'oblige a rejouer, il joue la premiere carte dans sa main qui n'est pas une carte changer de couleur
		while (parcourirCartesCompatibles.hasPrevious()) {

			// Si la carte sur laquelle la boucle s'arrette n'est pas une carte changer de
			// famille, il la pose
			if (!carteNext.getEffet().equals("Changer Famille") && !carteNext.getEffet().equals("Oblige a rejouer")) {
				carteChoisie = joueurEnCours.getCartes().indexOf(carteNext);
				if (joueurEnCours.getCartes().get(carteChoisie).getValeur().equals("1")) {
					Variante.nombreAs++;
				}
				if (joueurEnCours.getCartes().get(carteChoisie).getValeur().equals("1")
						&& Partie.getPartie().getManche().getVarianteManche() instanceof Variante4) {
					Variante4.couleur.setSymbole(joueurEnCours.getCartes().get(carteChoisie).getSymbole());
				}
				return carteChoisie;
			}
			carteNext = parcourirCartesCompatibles.previous();
		}
		// la premiere carte na pas ete etudiee dans la precedente boucle, on s'est arrete a celle d'avant
		if (!carteNext.getValeur().equals("Changer Famille") && !carteNext.getValeur().equals("Oblige a rejouer")) {
			carteChoisie = joueurEnCours.getCartes().indexOf(carteNext);
			if (joueurEnCours.getCartes().get(carteChoisie).getValeur().equals("1")) {
				Variante.nombreAs++;
			}
			 if (joueurEnCours.getCartes().get(carteChoisie).getValeur().equals("1")
					&& Partie.getPartie().getManche().getVarianteManche() instanceof Variante4) {
				Variante4.couleur.setSymbole(joueurEnCours.getCartes().get(carteChoisie).getSymbole());
			}

			return carteChoisie;
		} else {
			System.out.println("Je suis oblige de jouer ma carte 'Changer Famille'...");
			carteChoisie = this.jouer8(joueurEnCours);
			// jouer un 8 contre une attaque dans la variante5
		
				Variante.nombreAs = 0;
			
			return carteChoisie;
		}
	}

	public void changerFamille() {

		int tour = Partie.getPartie().getManche().getTourJoueur();
		ArrayList<Carte> carteJoueur = Partie.getPartie().getManche().getJoueur().get(tour - 1).getCartes();
		if (!(carteJoueur.isEmpty())) {
			int i;
			int[] nombreSymbole = { 0, 0, 0, 0 };
			// 0 : Carreau, 1 : Pique, 2 : Trefle, 3: Coeur

			// on cherche lequel des symboles est en majorite dans les cartes du joueur
			for (i = 0; i < carteJoueur.size(); i++) {

				if (carteJoueur.get(i).getSymbole().equals("CARREAU")) {
					nombreSymbole[0]++;
				} else if (carteJoueur.get(i).getSymbole().equals("PIQUE")) {
					nombreSymbole[1]++;
				} else if (carteJoueur.get(i).getSymbole().equals("TREFLE")) {
					nombreSymbole[2]++;
				} else {
					nombreSymbole[3]++;
				}
			}
			int max = nombreSymbole[0];
			int indice = 0;
			for (i = 1; i <= 3; i++) {
				if (nombreSymbole[i] > max) {
					max = nombreSymbole[i];
					indice = i;
				}
			}
			switch (indice) {

			case 0:
				// carreau est majoritaire
				Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("CARREAU");
				System.out.println("Symbole choisi : CARREAU ! ");
				break;
			case 1:
				Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("PIQUE");
				System.out.println("Symbole choisi : PIQUE! ");
				break;
			case 3:
				Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("COEUR");
				System.out.println("Symbole choisi : COEUR ! ");
				break;
			case 2:
				Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("TREFLE");
				System.out.println("Symbole choisi : TREFLE! ");
				break;
			}
		}
		// si le joueur fini par un 8, il choisit une couleur au hasard, il n'a pas a se
		// soucier de ses cartes
		else {
			String[] symboles = new String[] { "TREFLE", "COEUR", "CARREAU", "PIQUE" };
			Random r = new Random();
			int i = r.nextInt(3);
			String random = symboles[i];
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(random);
			System.out.println("Symbole choisi: " + random);
		}
	}

	public int jouer8(Joueur joueurEnCours) {

		int i = 0;
		boolean trouve8 = false;
		while (!trouve8) {

			if (joueurEnCours.getCartes().get(i).getEffet().equals("Changer Famille")) {
				trouve8 = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public boolean jouer10(Carte carteRejouer, Joueur joueurEnCours, ArrayList<Carte> cartesCompatibles) {

		String symboleCarteRejouer = carteRejouer.getSymbole();
		String valeurCarteRejouer = carteRejouer.getValeur();
		Iterator<Carte> it = joueurEnCours.getCartes().iterator();
		boolean trouveAutreCarteCompatible = false;
		/*
		 * On parcourt les cartes du joueur, tant qu'il reste des cartes, et quon a
		 * pas trouve de compatibilite. On return true ( jouer un 10 ) si la prochaine
		 * carte est de la meme famille que le 10 (et que ce n'est pas la 10) ou alors
		 * si c'est un autre 10
		 */
		Carte carteNext;
		while (it.hasNext()) {	
			carteNext = it.next();
			if (((carteNext.getSymbole().equals(symboleCarteRejouer)
					&& !(carteNext.getValeur().equals(valeurCarteRejouer)))

					|| (!(carteNext.getSymbole().equals(symboleCarteRejouer))
							&& (carteNext.getValeur().equals(valeurCarteRejouer)))
					|| (carteNext.getValeur().equals("8"))))

			{
				System.out.println("HAHA! JE PEUX POSER DEUX CARTES D'AFFILLEE!");
				trouveAutreCarteCompatible = true;
				return trouveAutreCarteCompatible;
			}
		}

		if (cartesCompatibles.size() == 1) {
			// le joueur n'a plus qu'un 10 il doit le jouer
			System.out.println("Mince, je dois jouer mon ma carte 'Rejouer'... Elle va me forcer a piocher");
			return true;
		}
		return trouveAutreCarteCompatible;
	}
}