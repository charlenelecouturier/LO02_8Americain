package pckg;

import java.util.ArrayList;

public class JouerMemeCouleur implements Effet {

	public void effet() {
		System.out.println("Le joueur se defausse de toutes ses cartes du même symbole");

		Joueur joueurActuel = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur()-1);

		joueurActuel.setEffetVariante("JouerMêmeCouleur");
		ArrayList<Carte> mainJoueurActuel = joueurActuel.getCartes();
		//lejoueur actuel rejoue jusqu'& ce qu'il se soit debarrassé de toutes ses cartes de la meme couleur que l'AS
		while (Partie.getPartie().getVariantePartie().estPossibleDeJouer(mainJoueurActuel)) {

			joueurActuel.poserCarte();

		}

	}
}
