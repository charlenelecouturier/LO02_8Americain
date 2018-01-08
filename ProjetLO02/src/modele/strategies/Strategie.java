
package modele.strategies;
import modele.*;
import java.util.ArrayList;

/**
 * <b>Interface inspirée du pattern Strategy, utilisée pour déterminer la stratégie des Joueurs Virtuels.</b>
 * @author Robin et Charlene
 * @version 1.0
 * 
 * @see StratAvancee
 * @see StrategieDeBase
 * @see JoueurVirtuel
 */
public interface Strategie {
	public int choixCarte(ArrayList<Carte> cartesCompatibles);
	public void changerFamille();
	
}