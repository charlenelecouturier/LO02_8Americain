package modele.strategies;

import modele.*;
import java.util.ArrayList;
import java.util.Random;
import modele.variantes.*;
public class StrategieDeBase implements Strategie
{
	public int choixCarte(ArrayList<Carte> cartesCompatibles) {
		Joueur joueurEnCours = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur()-1);
		int i=0;
		System.out.println(joueurEnCours.getName() + " a " + joueurEnCours.getCartes().size() + " carte(s)");
		System.out.println("Carte du talon : "+ Partie.getPartie().getManche().getTalon().getCarteDessus());
		while(!Partie.getPartie().getManche().getVarianteManche().estCompatible(joueurEnCours.getCartes().get(i)))
		{
			i++;
		}
		if(joueurEnCours.getCartes().get(i).getValeur().equals("1")) {
			Variante.nombreAs ++;
		}
		if (joueurEnCours.getCartes().get(i).getValeur().equals("1") && Partie.getPartie().getManche().getVarianteManche() instanceof Variante4) {
			Variante4.couleur.setSymbole(joueurEnCours.getCartes().get(i).getSymbole());
		}
		if(joueurEnCours.getCartes().get(i).getValeur().equals("8")) {
			Variante.nombreAs=0;
		}
		return i;
	}
	
	public void changerFamille() {
		
	    Random r= new Random();
	    int i = r.nextInt(3);
	    String random = Carte.symboles[i];
	    Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(random);
		System.out.println("Symbole choisi: "+random);
		}
}