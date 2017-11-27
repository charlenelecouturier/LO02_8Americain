package pckg;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 */

/**
 * @author charlene
 *
 */
public abstract class Variante {

	/**
	 * @param args
	 */
	protected LinkedList<Carte> carteSpeciale;
	protected int nbCartes;
	protected ArrayList<Carte> jeuDeCartes;
	public abstract ArrayList<Carte> creerJeuDeCartes(int nbPaquet);

	/**
	 * @param nbCartes the nbCartes to set
	 */
	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}

	public abstract String effetCarte(Carte carte); 
	

	
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
	
	public boolean estCompatible(Carte carte) {
		Carte carteDessusTalon;
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
	 * @return the carteSpeciale
	 */
	public LinkedList<Carte> getCarteSpeciale() {
		return carteSpeciale;
	}

	/**
	 * @param carteSpeciale the carteSpeciale to set
	 */
	public void setCarteSpeciale(LinkedList<Carte> carteSpeciale) {
		this.carteSpeciale = carteSpeciale;
	}

	public ArrayList<Carte> getCartes() {
		return jeuDeCartes;
	}
	
	public int getNbCartes() {
		return nbCartes;
	}
	
}
