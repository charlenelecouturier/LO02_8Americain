package pckg;
/**
 * 
 */

/**
 * @author Robin et Charlène
 *
 */
import java.util.ArrayList;

public class PorteurCarte {
	
	protected ArrayList<Carte> cartes= new ArrayList<Carte>(); // on fait une arraylist pour les cartes au lieu d'un tableau car cest plus simple à manipuler
	
	int nbCartes;
	/**
	 * @return the cartes
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}
	/**
	 * @param cartes the cartes to set
	 */
	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}
	/**
	 * @return the cartes
	 */
	//public Carte[] getCartes() {
	//	return cartes;
	//}
	/**
	 * @param cartes the cartes to set
	 */
//	public void setCartes(Carte carte, int place) {
//		this.cartes[place] = carte;
//	}
	/**
	 * @return the nbCartes
	 */
	public int getNbCartes() {
		return nbCartes;
	}
	/**
	 * @param nbCartes the nbCartes to set
	 */
	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}
	
	
	
	
	

}
