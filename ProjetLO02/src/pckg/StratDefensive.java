package pckg;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Iterator;

public class StratDefensive implements Strategie{

	
	public int choixCarte(ArrayList<Carte> cartesCompatibles) {
		Joueur joueurEnCours = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur()-1);
		int carteChoisie;
		ListIterator<Carte> parcourirCartesCompatibles = cartesCompatibles.listIterator();
		//on regarde si le joueur a un 10 jouable : si oui, on regardera si il peut jouer une autre carte
		while (parcourirCartesCompatibles.hasNext()) {
			if(parcourirCartesCompatibles.next().getValeur().equals("10")) {
				if(this.jouer10(parcourirCartesCompatibles.next())) {
					System.out.println("HAHA! JE PEUX POSER DEUX CARTES D'AFFILÉE!");
					carteChoisie = joueurEnCours.getCartes().indexOf(parcourirCartesCompatibles.next());
					return carteChoisie;
				}
			}
			parcourirCartesCompatibles.next();
		}
		//Si le joueur n'a pas de 10, il joue la première carte qu'il aie qui ne soit pas un 8
		while(parcourirCartesCompatibles.hasPrevious() && parcourirCartesCompatibles.previous().getValeur().equals("8")) {
			parcourirCartesCompatibles.previous();
		}
		//Si la carte sur laquelle la boucle s'arrête n'est pas un 8, il la pose
		if(!parcourirCartesCompatibles.previous().getValeur().equals("8")){
			System.out.println("Oh.. Je n'ai pas de 10.. je joue n'importe quelle carte sauf un 8");
			carteChoisie = joueurEnCours.getCartes().indexOf(parcourirCartesCompatibles.previous());
			return carteChoisie;
		}
		else {
			System.out.println("Oh non.. Je suis obligé de jouer mon 8..");
			carteChoisie = joueurEnCours.getCartes().indexOf(parcourirCartesCompatibles.next());
			return carteChoisie;
		}
	}
	
	public void changerFamille() {
		
		int tour = Partie.getPartie().getTourJoueur();
		ArrayList<Carte> carteJoueur = Partie.getPartie().getJoueur().get(tour-1).getCartes();
		int i;
		int nombreCarreau,nombrePique,nombretrefl
		
		
	}
	
	public boolean jouer8( ArrayList<Carte> cartesCompatibles ) {


		if(cartesCompatibles.size()==1 && cartesCompatibles.get(0).getValeur().equals("8") )
		{	return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean jouer10(Carte carte10) {
		
		
		int i;
		Iterator<Carte> it = cartesCompatibles.iterator();
		return true;
	}
	
	
}
