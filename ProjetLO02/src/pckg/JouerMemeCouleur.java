package pckg;

import java.util.ArrayList;

public class JouerMemeCouleur implements Effet {

	public void effet() {

		Joueur joueurActuel = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur() - 1);
		System.out.println("\n" + joueurActuel.getName() + " peut se defausser de toutes ses cartes du même symbole\n");
		Carte cartePose = new Carte(" ", " ");
		joueurActuel.setEffetVariante("JouerMêmeCouleur");
		ArrayList<Carte> mainJoueurActuel = joueurActuel.getCartes();
		// lejoueur actuel rejoue jusqu'a ce qu'il se soit debarrassé de toutes ses cartes de la meme couleur que l'AS
		// La derniere carte défaussée peut avoir un effet
		while (Partie.getPartie().getVariantePartie().estPossibleDeJouer(mainJoueurActuel)) {

			// Temps de délais entre chaque carte posée
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (joueurActuel instanceof JoueurPhysique) {

				System.out.println("Choisissez la carte que vous souhaitez jouer :");
				int numeroCarte = joueurActuel.choisirCarte();
				cartePose = joueurActuel.cartes.get(numeroCarte);
				// 3.1. Si le joueur choisit une carte qu'il ne peut pas jouer,
				// il rentre dans une boucle jusqu'à  ce qu'il choisisse une bonne carte
				while (!Partie.getPartie().getVariantePartie().estCompatible(cartePose)) {
					System.out.println("Cette carte ne peut être jouée, choisissez en une autre");
					numeroCarte = joueurActuel.choisirCarte();
					cartePose = joueurActuel.cartes.get(numeroCarte);
				}
				System.out.println("Vous posez " + cartePose);
				// 4.1 Le joueur pose la carte choisie sur le talon.
				Partie.getPartie().getTalon().getCartes().add(cartePose);
				// on change la carte du dessus du Talon qui est un simple attribut de type Carte
				// Partie.getPartie().getTalon().setCarteDessus(cartePose);
				Partie.getPartie().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
				Partie.getPartie().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
				// 5.1 Le joueur perd la carte qu'il a posée de sa main
				joueurActuel.getCartes().remove(cartePose);

				// 6.1 si il n'a plus qu'une carte, le joueur a la possibilité de dire Carte
				if (joueurActuel.getCartes().size() == 1) {
					((JoueurPhysique)joueurActuel).DireCarte();
				}
			} else {
				// 2.1. Le joueur choisit la carte qu'il desire poser sur le talon.
				int numeroCarte = joueurActuel.choisirCarte();
				cartePose = joueurActuel.cartes.get(numeroCarte);
				// 4.1 Le joueur pose la carte choisie sur le talon.
				Partie.getPartie().getTalon().getCartes().add(cartePose);
				// on change la carte du dessus du Talon qui est un simple attribut de type
				// Carte
				Partie.getPartie().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
				Partie.getPartie().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
				System.out.println(joueurActuel.getName() + " joue " + cartePose);
				// 5.1 Le joueur perd la carte qu'il a pose de sa main
				joueurActuel.getCartes().remove(cartePose);
				// s'il n'a plus qu'une carte il est possible qu'un joueur dise contre carte
				if (joueurActuel.getCartes().size() == 1) {
					boolean contrecarte = Partie.getPartie().getJoueur().get(0).DireContreCarte();
					if (contrecarte) {
						joueurActuel.piocher(1);
					}
				}
			}
		}
		// 6.2 On regarde si la derniere carte posée est une carte Speciale, on ne prend pas en compte les effets des autres cartes
		String effet = cartePose.getEffet();
		if (!effet.equals("Aucun")) {
			cartePose.appliquerEffet();
		}
	}
}