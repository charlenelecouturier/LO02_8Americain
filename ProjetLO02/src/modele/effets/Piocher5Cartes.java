package modele.effets;

import modele.Joueur;
import modele.Partie;

public class Piocher5Cartes implements Effet {

	@Override
	public void effet() {
		
			int tour;
			tour = Partie.getPartie().getManche().getTourJoueur();
			System.out.println("Le joueur suivant pioche 5 cartes");
			if (Partie.getPartie().getManche().getSens() == 1) {
				tour++;
				if (tour > Partie.getPartie().getManche().getNbJoueursEnCours()) {
					tour = 1;
				}
			} else {
				tour--;
				if (tour <= 0) {
					tour = Partie.getPartie().getManche().getNbJoueursEnCours();
				}
			}
			Joueur joueurSuivant = Partie.getPartie().getManche().getJoueur().get(tour - 1);
			joueurSuivant.piocher(5);
			Effet bloquerSuivant = new BloquerSuivant();
			bloquerSuivant.effet();
		}
		
	}


