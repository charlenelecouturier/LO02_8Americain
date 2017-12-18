
package modele;
import java.util.ArrayList;


public interface Strategie {
	public int choixCarte(ArrayList<Carte> cartesCompatibles);
	public void changerFamille();
	
}