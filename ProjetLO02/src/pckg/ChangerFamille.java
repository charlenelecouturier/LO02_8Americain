package pckg;


public class ChangerFamille implements Effet{
	public void effet() {
		Joueur joueur = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur()-1);
		System.out.println("Cette carte permet de changer de symbole.");
		joueur.changerFamille();
		
	}
}
