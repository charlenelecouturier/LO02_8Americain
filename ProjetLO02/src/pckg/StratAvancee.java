package pckg;
import java.util.ArrayList;
import java.util.Random;
import java.util.ListIterator;
import java.util.Iterator;

public class StratAvancee implements Strategie{
	
	public int choixCarte(ArrayList<Carte> cartesCompatibles) {
		System.out.println("Carte du talon : "+ Partie.getPartie().getTalon().getCarteDessus());
		System.out.println("Nombre de cartes compatibles : "+ cartesCompatibles.size());

		Joueur joueurEnCours = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur()-1);
		int carteChoisie;
		ListIterator<Carte> parcourirCartesCompatibles = cartesCompatibles.listIterator();
		
		//On regarde si le joueur a un 10 jouable : si oui, on regardera si il peut jouer une autre carte
		Carte carteNext;

		do {
			carteNext =parcourirCartesCompatibles.next();
			if(carteNext.getValeur().equals("10") && this.jouer10(carteNext, joueurEnCours,cartesCompatibles)) {
					carteChoisie = joueurEnCours.getCartes().indexOf(carteNext);
					return carteChoisie;
			}
			
		}while (parcourirCartesCompatibles.hasNext());

		
		System.out.println("Oh.. Je n'ai pas de 10 qui me permetterait de rejouer.. je joue n'importe quelle carte sauf un 8");

		//Si le joueur n'a pas de 10, il joue la premiere carte dans sa main qui n'est pas un 8
		while(parcourirCartesCompatibles.hasPrevious() ) {

			
			//Si la carte sur laquelle la boucle s'arrête n'est pas un 8, il la pose
			if(!carteNext.getValeur().equals("8")&&!carteNext.getValeur().equals("10")){
				carteChoisie = joueurEnCours.getCartes().indexOf(carteNext);
				if(joueurEnCours.getCartes().get(carteChoisie).getValeur().equals("1")&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
					Variante5.nombreAs ++;
				}
				if(joueurEnCours.getCartes().get(carteChoisie).getValeur().equals("8")&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
					Variante5.nombreAs=0;
				}
				return carteChoisie;
			}
			carteNext =parcourirCartesCompatibles.previous();


		} 
// la premiere carte na pas ete etudiee dans la precedente boucle, on s'est arret�e a celle d'avant
		if(!carteNext.getValeur().equals("8")&&!carteNext.getValeur().equals("10")){
			carteChoisie = joueurEnCours.getCartes().indexOf(carteNext);
			if(joueurEnCours.getCartes().get(carteChoisie).getValeur().equals("1")&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
				Variante5.nombreAs ++;
			}
			if(joueurEnCours.getCartes().get(carteChoisie).getValeur().equals("8")&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
				Variante5.nombreAs=0;
			}
			return carteChoisie;
		}
		
		else {
			System.out.println("Je suis oblige de jouer mon 8...");
			carteChoisie = this.jouer8(joueurEnCours);
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
			
			// on lequel des symboles est en majorit� dans les cartes du joueur
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
				System.out.println("Symbole choisi : COEUR ! ");
	
				break;
			case 2:
				Partie.getPartie().getTalon().getCarteDessus().setSymbole("TREFLE");
				System.out.println("Symbole choisi : TREFLE! ");
	
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
		
		
	
	
	public int jouer8(Joueur joueurEnCours) {


		int i=0;
		boolean trouve8=false;
		while(!trouve8) {
			
			if (joueurEnCours.getCartes().get(i).getValeur().equals("8")) {
				trouve8=true;	
			}
			else {
				i++;
			}
		}
		return i;
	}
	
	
	public boolean jouer10(Carte carte10, Joueur joueurEnCours,ArrayList<Carte> cartesCompatibles) {
		String symbole10 = carte10.getSymbole();
		Iterator<Carte> it = joueurEnCours.getCartes().iterator();
		
		
		
		
		boolean trouveAutreCarteCompatible=false;
		//on parcourt les cartes du joueur, tant qu'il reste des cartes, et quon a pas trouve de compatibilit�
		// on return true ( jouer un 10 ) si la prochaine carte est de la m�me famille que le 10 ( et que ce n'est pas la 10) ou alors si c'est un autre 10
		Carte carteNext =it.next();
		while (it.hasNext()) {
			
			
			if(((carteNext.getSymbole().equals(symbole10) && !(carteNext.getValeur().equals("10")))
					
				|| (!(carteNext.getSymbole().equals(symbole10)) && (carteNext.getValeur().equals("10")))
				|| (carteNext.getValeur().equals("8"))))
			
			
			{
					System.out.println("HAHA! JE PEUX POSER DEUX CARTES D'AFFILLEE!");
					trouveAutreCarteCompatible=true;
					return trouveAutreCarteCompatible;
			}
			
			
			carteNext =it.next();
		}
		
		
		
		
		// on regarde aussi la derniere carte de la main
		if(
				((carteNext.getSymbole().equals(symbole10) && !(carteNext.getValeur().equals("10")))
				
				|| (!(carteNext.getSymbole().equals(symbole10)) && (carteNext.getValeur().equals("10")))
				|| (carteNext.getValeur().equals("8")))
				
				)
		
		{
					System.out.println("HAHA! JE PEUX POSER DEUX CARTES D'AFFILLEE!");
				trouveAutreCarteCompatible=true;
				return trouveAutreCarteCompatible;
			}
		if(cartesCompatibles.size()==1) {
			// le joueur n'a plus qu'un 10 il doit le jouer
			System.out.println("Mince, je dois jouer mon 10...Il va me forcer � piocher");	
			return true;
		}
		
		
			return trouveAutreCarteCompatible;
			
	}
	
	
	
}