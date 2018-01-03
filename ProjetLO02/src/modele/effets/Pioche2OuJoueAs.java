package modele.effets;
import modele.*;
import modele.variantes.*;
public class Pioche2OuJoueAs implements Effet {

	public void effet() {
		System.out.println("Si le joueur suivant n'a pas d'AS alors : + "+ 2* Variante.nombreAs+" cartes");
		// on cherche la joueur suivant
		int tour;
		tour = Partie.getPartie().getManche().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getManche().getSens() == 1) {
			tour++;
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
		joueurSuivant.setEffetVariante("Pioche2ouAs");
	}
}