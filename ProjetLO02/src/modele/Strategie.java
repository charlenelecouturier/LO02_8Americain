
package modele;
import java.util.ArrayList;
import modele.*;


public interface Strategie {
	public int choixCarte(ArrayList<Carte> cartesCompatibles);
	public void changerFamille();
	
}