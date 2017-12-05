package pckg;

public class ChangerFamilleEtPioche5 implements Effet {
	public void effet() {
		// On selectionne le joueur à qui c'est le tour (celui qui vient de poser une carte)
		Joueur joueur = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur() - 1);
		System.out.println("\nLa carte jouée permet de changer de symbole. et fait piocher 5 cartes au suivant\n");
		/*
		 * on appelle la fonction changerFamille() du joueur, mais le joueur physique et
		 * le joueur virtuel vont chacun redefinir la fonction changerFamille() du
		 * joueur, en fonction du type réel du joueur, a l'exécution on va appeller
		 * l'une ou l'autrre des méthodes
		 */
		joueur.changerFamille();
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
				// si on trouve un numéro négatif, on revient au tour du dernier joueur ( joueur ayant le dernier numéro)
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getNbJoueursEnCours();
			}
		}
		Joueur joueurSuivant = Partie.getPartie().getJoueur().get(tour - 1);
		joueurSuivant.piocher(5);
		// le joueur suivant ne peut pas jouer
		Effet bloquerSuivant = new BloquerSuivant();
		bloquerSuivant.effet();
	}
}