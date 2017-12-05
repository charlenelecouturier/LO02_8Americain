package pckg;

public class ChangerFamille implements Effet{
	public void effet() {
		//On selectionne le joueur à qui c'est le tour (celui qui vient de poser une carte)
		Joueur joueur = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur()-1);
		System.out.println("\nLa carte jouée permet de changer de symbole.\n");
		/* on appelle la fonction changerFamille() du joueur, mais le joueur physique et le joueur virtuel 
		 vont chacun redefinir  la fonction changerFamille() du joueur, en fonction du type réel du joueur, a l'exécution
		on va appeller l'une ou l'autrre des méthodes*/
		joueur.changerFamille();
		
	}
}
