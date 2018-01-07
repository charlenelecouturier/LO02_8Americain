package modele.effets;
import modele.*;
import modele.variantes.*;

import java.util.ArrayList;

public class JouerMemeCouleur implements Effet {

	public void effet() {

		Joueur joueurActuel = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur() - 1);
		System.out.println("\n" + joueurActuel.getName() + " peut se defausser de toutes ses cartes du meme symbole\n");
		Carte cartePose = new Carte(" ", " ");
		joueurActuel.setEffetVariante("JouerMemeCouleur");
		joueurActuel.changed();
		joueurActuel.notifyObservers("doit se defausser de tous les mêmes symboles");
		ArrayList<Carte> mainJoueurActuel = joueurActuel.getCartes();
		// lejoueur actuel rejoue jusqu'a ce qu'il se soit debarrasse de toutes ses cartes de la meme couleur que l'AS
		// La derniere carte defaussee peut avoir un effet
		while (Partie.getPartie().getManche().getVarianteManche().estPossibleDeJouer(mainJoueurActuel)) {

			// Temps de delais entre chaque carte posze
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int numeroCarte = ((JoueurVirtuel)joueurActuel).choisirCarte();
			cartePose = joueurActuel.getCartes().get(numeroCarte);
			Partie.getPartie().getManche().getTalon().getCartes().add(cartePose);
			System.out.println("Test : il y a " + Partie.getPartie().getManche().getTalon().getCartes().size()
					+ " cartes dans le talon");
			// on change la carte du dessus du Talon qui est un simple attribut de type
			// Carte
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
			Partie.getPartie().getManche().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
			System.out.println(joueurActuel.getName() + " pose " + cartePose);
			// 5.1 Le joueur perd la carte qu'il a posee de sa main
			joueurActuel.getCartes().remove(cartePose);
			// s'il n'a plus qu'une carte il est possible qu'un joueur dise contre carte
			if (joueurActuel.getCartes().size() == 1) {
				joueurActuel.direCarte();
			}

		}
	}
}