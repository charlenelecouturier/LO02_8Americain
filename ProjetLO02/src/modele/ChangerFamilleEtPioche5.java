package modele;

public class ChangerFamilleEtPioche5 implements Effet {
	public void effet() {
		// On selectionne le joueur � qui c'est le tour (celui qui vient de poser une carte)
		Joueur joueur = Partie.getPartie().getJoueur().get(Partie.getPartie().getManche().getTourJoueur() - 1);
		System.out.println("\nLa carte jou�e permet de changer de symbole. et fait piocher 5 cartes au suivant\n");
		/*
		 * on appelle la fonction changerFamille() du joueur, mais le joueur physique et
		 * le joueur virtuel vont chacun redefinir la fonction changerFamille() du
		 * joueur, en fonction du type r�el du joueur, a l'ex�cution on va appeller
		 * l'une ou l'autrre des m�thodes
		 */
		joueur.changerFamille();
		int tour;
		tour = Partie.getPartie().getManche().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getManche().getSens() == 1) {
			tour++;
			// Si on depasse le num�ro du dernier joueur, on revient au joueur 1 ( joueur physique)
			if (tour > Partie.getPartie().getManche().getNbJoueursEnCours()) {
				tour = 1;
			}
		} else {// sens =-1
				// si on trouve un num�ro n�gatif, on revient au tour du dernier joueur ( joueur ayant le dernier num�ro)
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getManche().getNbJoueursEnCours();
			}
		}
		Joueur joueurSuivant = Partie.getPartie().getJoueur().get(tour - 1);
		joueurSuivant.piocher(5);
		// le joueur suivant ne peut pas jouer
		Effet bloquerSuivant = new BloquerSuivant();
		bloquerSuivant.effet();
	}
}