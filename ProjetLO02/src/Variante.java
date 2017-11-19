import java.util.ArrayList;

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
	protected Carte[] carteSpeciale;
	protected int nbCartes;
	protected ArrayList<Carte> jeuDeCartes= new ArrayList<Carte>();

	/**
	 * @param nbCartes the nbCartes to set
	 */
	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}

	public abstract String effetCarte(Carte carte); 
	
	public boolean estCompatible(Carte carte) {
	Carte carteDessusTalon;
	Talon talon = Partie.getPartie().getTalon();
	carteDessusTalon = talon.getCarteDessus();
	String carteSymbole= carte.getSymbole();
	String carteDessusTalonSymbole=carteDessusTalon.getSymbole();
	String carteValeur= carte.getValeur();
	String carteDessusTalonValeur=carteDessusTalon.getValeur();
	
	if(carteSymbole.equals(carteDessusTalonSymbole) ||carteValeur.equals(carteDessusTalonValeur)) 
	{
		return true;
	}
	else {
		return false;
	}
		
	}
	
	public abstract boolean estPossibleDeJouer(ArrayList<Carte> carte);

	
	/**
	 * @return the carteSpeciale
	 */
	public Carte[] getCarteSpeciale() {
		return carteSpeciale;
	}

	/**
	 * @param carteSpeciale the carteSpeciale to set
	 */
	public void setCarteSpeciale(Carte[] carteSpeciale) {
		this.carteSpeciale = carteSpeciale;
	}

	public ArrayList<Carte> getCartes() {
		return jeuDeCartes;
	}
	
	public int getNbCartes() {
		return nbCartes;
	}
	
}
