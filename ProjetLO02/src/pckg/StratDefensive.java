package pckg;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Iterator;

public class StratDefensive implements Strategie{

	
	public int choixCarte(ArrayList<Carte> cartesCompatibles) {
		
		
		boolean jouer8= jouer8(cartesCompatibles);
		if(jouer8) {
			return 0;
		
		}
		
		return 0;
				
		
				
	
		
		
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
		

		
		
		
		
		return false;
	}
	
	public boolean jouer10() {
		
		
		int i;
		Iterator<Carte> it = cartesCompatibles.iterator();
		return true;
	}
	
	
}
