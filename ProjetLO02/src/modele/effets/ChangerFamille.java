package modele.effets;
import modele.*;

public class ChangerFamille implements Effet{
	public void effet() {
		//On selectionne le joueur a qui c'est le tour (celui qui vient de poser une carte)
		Joueur joueur = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur()-1);
		System.out.println("\nLa carte jouee permet de changer de symbole.\n");
		/* on appelle la fonction changerFamille() du joueur, mais le joueur physique et le joueur virtuel 
		 vont chacun redefinir  la fonction changerFamille() du joueur, en fonction du type r�el du joueur, a l'ex�cution
		on va appeller l'une ou l'autrre des methodes*/
		joueur.changerFamille();
		
	}
}