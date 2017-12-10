package pckg;

import java.util.ArrayList;
import java.util.Random;

public class StrategieDeBase implements Strategie
{
	public int choixCarte(ArrayList<Carte> cartesCompatibles) {
		Joueur joueurEnCours = Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur()-1);
		int i=0;
		System.out.println(joueurEnCours.getName() + " a " + joueurEnCours.getCartes().size() + " carte(s)");
		System.out.println("Carte du talon : "+ Partie.getPartie().getTalon().getCarteDessus());
		while(!Partie.getPartie().getVariantePartie().estCompatible(joueurEnCours.getCartes().get(i)))
		{
			i++;
		}
		if(joueurEnCours.getCartes().get(i).getValeur().equals("1")&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
			Variante5.nombreAs ++;
		}
		if (joueurEnCours.getCartes().get(i).getValeur().equals("1") && Partie.getPartie().getVariantePartie() instanceof Variante4) {
			Variante4.couleur.setSymbole(joueurEnCours.getCartes().get(i).getSymbole());
		}
		if(joueurEnCours.getCartes().get(i).getValeur().equals("8")&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
			Variante5.nombreAs=0;
		}
		return i;
	}
	
	public void changerFamille() {
		
	    Random r= new Random();
	    int i = r.nextInt(3);
	    String random = Carte.symboles[i];
	    Partie.getPartie().getTalon().getCarteDessus().setSymbole(random);
		System.out.println("Symbole choisi: "+random);
		}
}