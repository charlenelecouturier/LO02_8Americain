package modele.effets;
import modele.*;


public class Piocher4Cartes implements Effet {

	public void effet() {
		System.out.println("Le joueur suivant pioche 4 cartes");
		int tour;
		tour = Partie.getPartie().getManche().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getManche().getSens() == 1) {
			tour++;
			// Si on depasse le num�ro du dernier joueur, on revient au joueur 1 ( joueur physique)
			if (tour > Partie.getPartie().getManche().getNbJoueursEnCours()) {
				tour = 1;
			}
		} else {// sens =-1
				// si on trouve un numero negatif, on revient au tour du dernier joueur (joueur ayant le dernier num�ro)
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getManche().getNbJoueursEnCours();
			}
		}
		Joueur joueurSuivant = Partie.getPartie().getManche().getJoueur().get(tour - 1);
		joueurSuivant.piocher(4);
		// le joueur suivant ne peut pas jouer
		Effet bloquerSuivant = new BloquerSuivant();
		bloquerSuivant.effet();
	}
}