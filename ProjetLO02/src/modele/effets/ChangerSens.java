package modele.effets;
import modele.*;

public class ChangerSens implements Effet {

	public void effet() {

		if (Partie.getPartie().getManche().getNbJoueursEnCours() == 2) {
			Effet obligerRejouer = new ObligeRejouer();
			obligerRejouer.effet();
		} else {
			System.out.println("\nLa carte jouee change de sens !\n");
			Partie.getPartie().getManche().setSens();
		}
	}
}