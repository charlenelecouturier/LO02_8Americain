package pckg;

import java.util.ArrayList;

public class JouerMemeCouleur implements Effet {

	public void effet() {

		Joueur joueurActuel = Partie.getPartie().getJoueur().get(Partie.getPartie().getManche().getTourJoueur() - 1);
		System.out.println("\n" + joueurActuel.getName() + " peut se defausser de toutes ses cartes du même symbole\n");
		Carte cartePose = new Carte(" ", " ");
		joueurActuel.setEffetVariante("JouerMêmeCouleur");
		ArrayList<Carte> mainJoueurActuel = joueurActuel.getCartes();
		// lejoueur actuel rejoue jusqu'a ce qu'il se soit debarrassé de toutes ses cartes de la meme couleur que l'AS
		// La derniere carte défaussée peut avoir un effet
		while (Partie.getPartie().getManche().getVarianteManche().estPossibleDeJouer(mainJoueurActuel)) {

			// Temps de délais entre chaque carte posée
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
					int numeroCarte = joueurActuel.choisirCarte();
					cartePose = joueurActuel.getCartes().get(numeroCarte);
					Partie.getPartie().getManche().getTalon().getCartes().add(cartePose);
					System.out.println("Test : il y a " + Partie.getPartie().getManche().getTalon().getCartes().size() + " cartes dans le talon");
					// on change la carte du dessus du Talon qui est un simple attribut de type Carte
					Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
					Partie.getPartie().getManche().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
					System.out.println(joueurActuel.getName() + " pose " + cartePose);
					// 5.1 Le joueur perd la carte qu'il a posï¿½e de sa main
					joueurActuel.getCartes().remove(cartePose);
					// s'il n'a plus qu'une carte il est possible qu'un joueur dise contre carte
					if (joueurActuel.getCartes().size() == 1) {
						joueurActuel.direCarte();
						}

		}
		// 6.2 On regarde si la derniere carte posée est une carte Speciale, on ne prend pas en compte les effets des autres cartes
		String effet = cartePose.getEffet();
		if (!effet.equals("Aucun")) {
			cartePose.appliquerEffet();
		}
	}
}