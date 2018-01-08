package modele.effets;
import modele.*;

/**
 * <b> Permet au joueur de rejouer immédiatement</b>
 * @author Robin et Charlene
 * @version 1.0
 */
public class ObligeRejouer implements Effet{

	public void effet() {

		// On selectionne le joueur a qui c'est le tour (celui qui vient de poser une carte)
		Joueur joueur = Partie.getPartie().getManche().getJoueur()
				.get(Partie.getPartie().getManche().getTourJoueur() - 1);
		joueur.setEffetVariante("doit rejouer");
		joueur.changed();
		joueur.notifyObservers("doit rejouer ou pioche");
	}
}