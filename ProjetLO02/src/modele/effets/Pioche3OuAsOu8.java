package modele.effets;
import modele.*;
import modele.variantes.*;

public class Pioche3OuAsOu8 implements Effet {

	public void effet() {
		Joueur jActuel = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur()-1);
		System.out.println("Si le joueur suivant n'a pas de 8 ou d'AS alors : + "+ 3* Variante.nombreAs+" cartes");
		// on cherche la joueur suivant
		int tour;
		tour = Partie.getPartie().getManche().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getManche().getSens() == 1) {
			tour++;
			// Si on depasse le num�ro du dernier joueur, on revient au joueur 1 ( joueur
			// physique)
			if (tour > Partie.getPartie().getManche().getNbJoueursEnCours()) {
				tour = 1;
			}
		} else {// sens =-1
				// si on trouve un num�ro n�gatif, on revient au tour du dernier joueur ( joueur
				// ayant le dernier num�ro)
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getManche().getNbJoueursEnCours();
			}
		}
		Joueur joueurSuivant = Partie.getPartie().getManche().getJoueur().get(tour - 1);
		joueurSuivant.setEffetVariante("Pioche3ouAsou8");
		jActuel.changed();
		jActuel.notifyObservers("pose un + 3, le joueur suivant doit jouer un AS ou un 8");
	}
}