package modele.effets;
import modele.*;
/**
 * <b> Appelle les redéfinitions des méthodes changerFamille() des types de joueurs. </b>
 * @author Robine et Charlene
 * @version 1.0
 */
public class ChangerFamille implements Effet{
	public void effet() {
		//On selectionne le joueur a qui c'est le tour (celui qui vient de poser une carte)
		Joueur joueur = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur()-1);
		System.out.println("\nLa carte jouee permet de changer de symbole.\n");
		/* on appelle la fonction changerFamille() du joueur, mais le joueur physique et le joueur virtuel 
		 vont chacun redefinir  la fonction changerFamille() du joueur, en fonction du type reel du joueur, a l'ex�cution
		on va appeller l'une ou l'autrre des methodes*/

		joueur.setEffetVariante("Changer Famille");
		joueur.changed();
		joueur.notifyObservers("Changer Famille");
		if(joueur instanceof JoueurVirtuel) {
			joueur.changerFamille();
			
		}
		
	}

	
	
}