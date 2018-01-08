package modele.effets;
import modele.*;

public class BloquerSuivant implements Effet {

	public void effet() {
/**
 * <p> S'il n'y a que deux joueurs, l'effet devient ObligeRejouer(). Sinon, le tour du joueur Suivant est bloqué. </p>
 */
		if (Partie.getPartie().getManche().getNbJoueursEnCours() == 2) {
			Effet obligerRejouer = new ObligeRejouer();
			obligerRejouer.effet();
		} else {
			// on cherche la joueur suivant
			int tour;
			tour = Partie.getPartie().getManche().getTourJoueur();
			// On regarde le sens de la partie
			if (Partie.getPartie().getManche().getSens() == 1) {
				tour++;
				// Si on depasse le numero du dernier joueur, on revient au joueur 1 ( joueur
				// physique)
				if (tour > Partie.getPartie().getManche().getNbJoueursEnCours()) {
					tour = 1;
				}
			} else {// sens =-1
					// si on trouve un numero negatif, on revient au tour du dernier joueur ( joueur
					// ayant le dernier numero)
				tour--;
				if (tour <= 0) {
					tour = Partie.getPartie().getManche().getNbJoueursEnCours();
				}
			}
			Joueur joueurSuivant = Partie.getPartie().getManche().getJoueur().get(tour - 1);
			joueurSuivant.setEffetVariante("Bloquer");
		}
	}
}