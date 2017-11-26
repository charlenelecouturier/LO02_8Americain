package pckg;
import java.util.ArrayList;
import java.util.Random;
import java.util.ListIterator;
import java.util.Iterator;

public class StratAvancee implements Strategie{
	
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
		System.out.println("Oh.. Je n'ai pas de 10.. je joue n'importe quelle carte sauf un 8");
		while(parcourirCartesCompatibles.hasPrevious() && parcourirCartesCompatibles.previous().getValeur().equals("8")) {
			parcourirCartesCompatibles.previous();
		}
		//Si la carte sur laquelle la boucle s'arrête n'est pas un 8, il la pose
		if(!parcourirCartesCompatibles.previous().getValeur().equals("8")){
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
		if (!(carteJoueur.isEmpty())){
			int i;
			int[]  nombreSymbole = {0,0,0,0};
			// 0 : Carreau, 1 : Pique, 2 : Trefle, 3: Coeur	
			
			// on lequel des symboles est en majorité dans les cartes du joueur
			for(i=0;i<carteJoueur.size();i++) {
				
				if(carteJoueur.get(i).getSymbole().equals("CARREAU")) {
					nombreSymbole[0] ++;
				}
				else if(carteJoueur.get(i).getSymbole().equals("PIQUE")) {
					nombreSymbole[1] ++;
				}
				
				else if(carteJoueur.get(i).getSymbole().equals("TREFLE")) {
					nombreSymbole[2] ++;
				}
				
				else
				{
					nombreSymbole[3] ++;
				}
				
			}
			int max = nombreSymbole[0];
			int indice =0;
			for(i=1;i<=3;i++) {
				if(nombreSymbole[i]>max) {
					max= nombreSymbole[i];
					indice =i;
					
				}	
				
			}
			switch (indice) {
	
			case 0:
				//carreau est majoritaire
				Partie.getPartie().getTalon().getCarteDessus().setSymbole("CARREAU");
				System.out.println("Symbole choisi : CARREAU ! ");
				break;
			case 1:
				Partie.getPartie().getTalon().getCarteDessus().setSymbole("PIQUE");
				System.out.println("Symbole choisi : PIQUE! ");
	
				break;
			case 3:
				Partie.getPartie().getTalon().getCarteDessus().setSymbole("COEUR");
				System.out.println("Vous avez choisi comme symbole : COEUR ! ");
	
				break;
			case 2:
				Partie.getPartie().getTalon().getCarteDessus().setSymbole("TREFLE");
				System.out.println("Vous avez choisi comme symbole : TREFLE! ");
	
				break;

			}
		}
		
		
		// si le joueur fini par un 8, il choisit une couleur au hasard, il n'a pas a se soucier de ses cartes
		else {
		    String[] symboles=new String[]{"TREFLE","COEUR","CARREAU","PIQUE"};
		    Random r= new Random();
		    int i = r.nextInt(3);
		    String random = symboles[i];
		    Partie.getPartie().getTalon().getCarteDessus().setSymbole(random);
			System.out.println("Symbole choisi: "+random);

		    		

		}

	}
		
		
	
	
	public boolean jouer8( ArrayList<Carte> cartesCompatibles ) {


		if(cartesCompatibles.size()==1 && cartesCompatibles.get(0).getValeur().equals("8") )
		{	return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean jouer10() {
		
		
		int i;
		//Iterator<Carte> it = cartesCompatibles.iterator();
		return true;
	}
	
	
}
