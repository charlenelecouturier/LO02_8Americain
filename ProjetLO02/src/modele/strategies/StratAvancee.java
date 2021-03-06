package modele.strategies;
import modele.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.ListIterator;
import java.util.Iterator;
import modele.variantes.*;
/**
 * <b> Stratégie plus complexe pour les joueurs virtuels</b>
 * <p> Dans cette stratégie, les joueurs virtuels analysent leurs cartes jouables et sélectionnent 
 * celle qui est la plus optimisée à jouer</p>
 * <p> <b> TO DO </b> </hr>
 * Cette stratégie est perfectible, et devrait notamment s'adapter en fonction de la Variante sélectionnée pour être optimale.
 * </p>
 * @author Robin et Charlene
 * @version 1.0
 *
 */
public class StratAvancee implements Strategie {
	
	/**
	 * <b> Le joueur analyse ses cartes compatibles avec le talon, et choisit la meilleure. </b>
	 * <p> L'algorithme s'effectue dans cet ordre : <ul>
	 * <li> On vérifie si le joueur a une carte qui l'oblige à rejouer. Si c'est le cas <b>et </b> qu'il possède une carte de cette même famille, il la choisit.</li>
	 * <li> Sinon, il pose n'importe quelle carte qui n'ait pas l'effet "changerFamille".</li>
	 * <li> En dernier lieu, il pose une carte changerFamille. </li>
	 * </ul>
	 * 
	 */
	public int choixCarte(ArrayList<Carte> cartesCompatibles) {
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
			return carteChoisie;
		} else {
			System.out.println("Je suis oblige de jouer ma carte 'Changer Famille'...");
			carteChoisie = this.jouer8(joueurEnCours);
			// jouer un 8 contre une attaque dans la variante5
		
				Variante.nombreAs = 0;
			
			return carteChoisie;
		}
	}
	/**
	 * <b> Choisit la famille dans laquelle le joueur a le plus de cartes</b>
	 * <p> Utilisé en cas de choix d'une carte avec l'effet changerFamille.
	 *  Au cas ou le joueur n'a plus qu'une carte, il ne se soucie pas de la couleur à choisir.</p>
	 */
	public void changerFamille() {


		String symbole="";
		int tour = Partie.getPartie().getManche().getTourJoueur();
		Joueur joueurEnCours = Partie.getPartie().getManche().getJoueur().get(tour-1);
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
				symbole ="CARREAU";
				break;
			case 1:
				symbole="PIQUE";
				break;
			case 3:
				symbole="COEUR";
				break;
			case 2:
				symbole="TREFLE";
				break;
			}
		}
		// si le joueur fini par un 8, il choisit une couleur au hasard, il n'a pas a se
		// soucier de ses cartes
		else {
			String[] symboles = new String[] { "TREFLE", "COEUR", "CARREAU", "PIQUE" };
			Random r = new Random();
			int i = r.nextInt(3);
			symbole = symboles[i];
		}

		joueurEnCours.changed();
		joueurEnCours.notifyObservers();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(symbole);
		System.out.println("Symbole choisi: " + symbole);
		joueurEnCours.changed();
		joueurEnCours.notifyObservers(symbole);
	}
	
	/**
	 * <b> Retourne la première carte ayant l'effet "Changer Famille" de la main du joueur</b>
	 * @param joueurEnCours le joueur en cours
	 * @return l'index du premier 8.
	 */
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
	/**
	 * <b> Vérifie que, si le joueur a une carte à l'effet "Oblige a Rejouer", il peut rejouer après.</b>
	 * @param carteRejouer la carte avec l'Effet
	 * @param joueurEnCours le joueur
	 * @param cartesCompatibles l'ensemble des cartes jouables sur le talon
	 * @return un booléen qui indique si il devrait oui ou non jouer son 10
	 */
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
			System.out.println("Mince, je dois jouer ma carte 'Rejouer'... Elle va me forcer a piocher");
			return true;
		}
		return trouveAutreCarteCompatible;
	}
}