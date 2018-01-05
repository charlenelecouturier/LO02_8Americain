package modele.effets;
import modele.*;


public class ObligeRejouer implements Effet{

	public void effet() {

		// On selectionne le joueur a qui c'est le tour (celui qui vient de poser une carte)
		Joueur joueur = Partie.getPartie().getManche().getJoueur()
				.get(Partie.getPartie().getManche().getTourJoueur() - 1);
		joueur.setEffetVariante("doit rejouer");
	}
}