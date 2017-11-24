package pckg;
import java.util.ArrayList;
import java.util.LinkedList;

public class VarianteMinimale extends Variante {

	@Override
	public String effetCarte(Carte carte) {
		String carteValeur=carte.getValeur();
		if (carteValeur=="10") {
			return "ObligeRejouer";}
		else if (carteValeur=="8") {
			return "ChangerFamille";
		}
		else return  "PasEffet";// pas forcément utile car on a deja checké si c'etait une carte spéciale
		
		
	}

	@Override
	public boolean estPossibleDeJouer(ArrayList<Carte> carte) {
		int i;
		for (i=0;i<carte.size();i++)
		{
			if (carte.get(i).getSymbole().equals(Partie.getPartie().getTalon().getCarteDessus().getSymbole()) || carte.get(i).getValeur().equals(Partie.getPartie().getTalon().getCarteDessus().getValeur()) ||carte.get(i).getValeur().equals("8")) 
			//Si une des cartes a le meme symbole que le talon ou la meme valeur , ou si cette carte est un 8
			{
				return true; // le joueur peut jouer
			}
			}
		return false; // le joueur ne peut pas jouer
	}

	@Override
	public boolean estCompatible(Carte carte) {
		Carte carteDessusTalon;
		//System.out.println(carte);
		Talon talon = Partie.getPartie().getTalon();
		carteDessusTalon = talon.getCarteDessus();
		String carteSymbole= carte.getSymbole();
		String carteDessusTalonSymbole=carteDessusTalon.getSymbole();
		String carteValeur= carte.getValeur();
		String carteDessusTalonValeur=carteDessusTalon.getValeur();
		
		if(carteSymbole.equals(carteDessusTalonSymbole) ||carteValeur.equals(carteDessusTalonValeur)|| carteValeur.equals("8") ) 
		{
			return true; // la carte choisie par le joueur est compatible
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 */
	

		
		
	public VarianteMinimale(int nbJoueursVirtuels) {
		
		this.carteSpeciale = new LinkedList<Carte>();
		this.carteSpeciale.add(new Carte("8", "NIMPORTE"));
		//this.carteSpeciale.add(new Carte("10", "NIMPORTE"));
		
		
		
		int nbPaquet=1;	
		// si il ya plus de 5 joueurs en tout au départ on rentre dans la boucle if(), car on utilise 1 paquet pour 5 joueur

		if(nbJoueursVirtuels>4) { // boucle infinie ici{
					
					nbPaquet +=(nbJoueursVirtuels+1)/5;
				}
		this.nbCartes=52*nbPaquet;
		this.jeuDeCartes=this.creerJeuDeCartes(nbPaquet);
		
	
			
			}

	@Override
	public ArrayList<Carte> creerJeuDeCartes(int nbPaquet) {
		ArrayList<Carte> jeuDeCartes= new ArrayList<Carte>();
		int k;
		//nb cartes definie dans la variante
		for(k=1;k<=nbPaquet; k++) {
		int i,j;
	    String[] symboles=new String[]{"TREFLE","COEUR","CARREAU","PIQUE"};
		String[] valeurs=new String[]{"1","2","3","4","5","6","7","8","9","10","VALET","DAME","ROI"};

			for(i=0;i<symboles.length;i++) {
				
				for(j=0;j<valeurs.length;j++){
					Carte carte = new Carte(valeurs[j],symboles[i]);
					jeuDeCartes.add(carte);
					}
			}
		}
		return jeuDeCartes;		
	}

	}
	
	

