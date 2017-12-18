package modele;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Robin et Charlene
 *
 */
public class Talon extends Observable {

	/**
	 * @param args
	 */
	protected ArrayList<Carte> cartes= new ArrayList<Carte>(); // on fait une arraylist pour les cartes 
	private Carte carteDessus;

	
	
	/** Constructeur de la classe Talon
	 * il permet de cr�er un ref�rence vers une carteDuDessus du talon inconnue, qui ne correspondra pas a la r�f�rence 
	 * de la carte r�ellement plac� au dessus du talon dans la collection cartes ( � la place talon.cartes.size()-1 ).
	 * Ainsi, lorsque qu'une carte ayant pour effet de changer le symbole de la carte du dessus est pos�e, on pourra 
	 * changer le symbole de l'attribut carte du dessus, sans transformer r�ellement la carte du Talon
	 * ( � la place Talon.carte.size()-1 ).
	 * Quand on ajoute une carte au talon, on va faire un cartes.add() pour ajouter une carte � la collection carte
	 *  correspondant au paquet, puis on va changer � part la carte � la ref carteDessus en faisant : 
	 * carteDessus.setSymbole(carteAjout�e.getSymbole()) 
	 * et carteDessus.setValeur(carteAjout�e.getValeur())
	 * */
	
	public Talon() {
		this.carteDessus= new Carte("?","?"); // initialisation carte du dessus pour pas renvoyer un pointeur null
		
	}
	
	/**
	 * methode qui permet de mettre toutes les cartes du talon dans la pioche
	 * (lorsque cette derni�re ne permet pas au joueur de piocher le bon nombre de
	 * cartes), sauf la carte du dessus du talon qui reste, et on conserve les
	 * dernieres cartes de la pioche au dessus de celle-ci La methode ne renvoie
	 * rien en retour
	 */

	public void devenirPioche() {
		
		int nombreCartesPioche = Partie.getPartie().getManche().getPioche().getCartes().size();
		System.out.println("\nIl reste " + nombreCartesPioche + " cartes dans la pioche, on tranf�re le talon dans la pioche !\n");
		ArrayList<Carte> cartesRestantes = new ArrayList<Carte>(); // les dernieres cartes qui restaient de la pioche

		// on stocke les dernieres cartes de la pioche dans une collection, dans l'ordre
		while(Partie.getPartie().getManche().getPioche().getCartes().size()>0) {
			cartesRestantes.add(Partie.getPartie().getManche().getPioche().getCartes().get(0));
			Partie.getPartie().getManche().getPioche().getCartes().remove(0);
		}
		// On met toutes les cartes du talon, sauf la carte du dessus , dans la pioche
		while(this.cartes.size()>1) {
			Partie.getPartie().getManche().getPioche().getCartes().add(this.cartes.get(0));
			this.cartes.remove(0);
		}
		// On melange la pioche
		Partie.getPartie().getManche().getPioche().melanger();
		// On rajoute les dernieres cartes a piocher (qui n'�taient pas suffisantes pour
		// que le joueur puisse piocher correctement), dans la pioche
		while (cartesRestantes.size()>0) {
			Partie.getPartie().getManche().getPioche().getCartes().add(cartesRestantes.get(0));
			cartesRestantes.remove(0);
		}
	}

	public Carte getCarteDessus() {
		return this.carteDessus;
	}

	/**
	 * @param carteDessus
	 */

	public void setCarteDessus(Carte carteDessus) {
		this.carteDessus = carteDessus;
		setChanged();
		notifyObservers();
	}

	
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
}