package modele.strategies;

import modele.*;
import java.util.ArrayList;
import java.util.Random;
import modele.variantes.*;
/**
 * <b> Stratégie basique pour les joueurs Virtuels</b>
 * <p> Dans cette stratégie, le joueur virtuel choisit ses cartes au hasard parmi les cartes qu'il peut jouer.\n
 *  Cette stratégie n'est donc pas la plus performante. </p>
 * @author Robin et Charlene
 * @version 1.0
 */
public class StrategieDeBase implements Strategie {
	/**
	 * <b>Le joueur virtuel choisit la première carte jouable qu'il possède dans son paquet.</b>
	 * <p> Une boucle permet d'avancer parmi ses cartes jusqu'à trouver une carte compatible avec celle du dessus du talon.</p>
	 */
	public int choixCarte(ArrayList<Carte> cartesCompatibles) {
		Joueur joueurEnCours = Partie.getPartie().getManche().getJoueur().get(Partie.getPartie().getManche().getTourJoueur()-1);
		int i=0;
		while(!Partie.getPartie().getManche().getVarianteManche().estCompatible(joueurEnCours.getCartes().get(i)))
		{
			i++;
		}
		if(joueurEnCours.getCartes().get(i).getValeur().equals("1")) {
			Variante.nombreAs ++;
		}
		if(joueurEnCours.getCartes().get(i).getValeur().equals("8")) {
			Variante.nombreAs=0;
		}
		return i;
	}
	/**
	 * <b> Le joueur choisit une famille au hasard parmi les 4. </b>
	 */
	public void changerFamille() {
		
	    Random r= new Random();
	    int i = r.nextInt(3);
	    String random = Carte.symboles[i];
	    Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(random);
		System.out.println("Symbole choisi: "+random);
		}
}