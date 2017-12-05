package pckg;

public class ChangerSens implements Effet {

	public void effet() {

		if (Partie.getPartie().getNbJoueursEnCours() == 2) {
			Effet obligerRejouer = new ObligeRejouer();
			obligerRejouer.effet();
		} else {
			System.out.println("\nLa carte jouée change de sens !\n");
			Partie.getPartie().setSens();
		}
	}
}