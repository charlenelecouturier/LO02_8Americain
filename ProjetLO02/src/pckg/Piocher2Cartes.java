package pckg;

public class Piocher2Cartes implements Effet {

	public void effet() {
		System.out.println("Le joueur suivant pioche 2 cartes");
		int tour;
		tour = Partie.getPartie().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getSens() == 1) {
			tour++;
			// Si on depasse le numéro du dernier joueur, on revient au joueur 1 ( joueur physique)
			if (tour > Partie.getPartie().getNbJoueursEnCours()) {
				tour = 1;
			}
		} else {// sens =-1
				// si on trouve un numéro négatif, on revient au tour du dernier joueur ( joueur
				// ayant le dernier numéro)
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getNbJoueursEnCours();
			}
		}
		Joueur joueurSuivant = Partie.getPartie().getJoueur().get(tour - 1);
		joueurSuivant.piocher(2);
		// le joueur suivant ne peut pas jouer
		Effet bloquerSuivant = new BloquerSuivant();
		bloquerSuivant.effet();
	}
}