package pckg;

public class Pioche2OuASOu8 implements Effet {

	public void effet() {
		System.out.println("Si le joueur suivant n'a pas de 8 ou d'AS alors : + "+ 2* Variante5.nombreAs+" cartes");
		// on cherche la joueur suivant
		int tour;
		tour = Partie.getPartie().getManche().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getManche().getSens() == 1) {
			tour++;
			// Si on depasse le numéro du dernier joueur, on revient au joueur 1 ( joueur
			// physique)
			if (tour > Partie.getPartie().getManche().getNbJoueursEnCours()) {
				tour = 1;
			}
		} else {// sens =-1
				// si on trouve un numéro négatif, on revient au tour du dernier joueur ( joueur
				// ayant le dernier numéro)
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getManche().getNbJoueursEnCours();
			}
		}
		Joueur joueurSuivant = Partie.getPartie().getJoueur().get(tour - 1);
		joueurSuivant.setEffetVariante("Pioche2ouAsou8");
	}
}