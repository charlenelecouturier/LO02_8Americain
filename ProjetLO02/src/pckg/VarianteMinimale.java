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




	public VarianteMinimale(int nbJoueursVirtuels) {
		
		this.carteSpeciale = new LinkedList<Carte>();
		this.carteSpeciale.add(new Carte("8", "NIMPORTE"));
		this.carteSpeciale.add(new Carte("10", "NIMPORTE"));
		
		
		
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
		String[] valeurs=new String[]{"1","2","3","4","5","6","7","8","9","10","VALET","DAME","ROI"};

			for(i=0;i<Carte.symboles.length;i++) {
				
				for(j=0;j<valeurs.length;j++){
					Carte carte = new Carte(valeurs[j],Carte.symboles[i]);
					jeuDeCartes.add(carte);
					}
			}
		}
		return jeuDeCartes;		
	}

	}
	
	

