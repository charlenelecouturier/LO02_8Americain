package modele.effets;
import modele.*;
/**
 * <b> Si il n'y a que 2 joueurs, appelle obligerRejouer(). Sinon, change le sens de la partie. </b>
 * @author Robin et Charlene
 * @version 1.0
 */
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