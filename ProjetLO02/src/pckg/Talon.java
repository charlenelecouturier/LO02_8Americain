package pckg;

import java.util.ArrayList;

/**
 * @author Robin et Charlene
 *
 */
public class Talon {

	/**
	 * @param args
	 */
	protected ArrayList<Carte> cartes= new ArrayList<Carte>(); // on fait une arraylist pour les cartes 
	private Carte carteDessus;

	
	
	/** Constructeur de la classe Talon
	 * il permet de créer un reférence vers une carteDuDessus du talon inconnue, qui ne correspondra pas a la référence 
	 * de la carte réellement placé au dessus du talon dans la collection cartes ( à la place talon.cartes.size()-1 ).
	 * Ainsi, lorsque qu'une carte ayant pour effet de changer le symbole de la carte du dessus est posée, on pourra 
	 * changer le symbole de l'attribut carte du dessus, sans transformer réellement la carte du Talon
	 * ( à la place Talon.carte.size()-1 ).
	 * Quand on ajoute une carte au talon, on va faire un cartes.add() pour ajouter une carte à la collection carte
	 *  correspondant au paquet, puis on va changer à part la carte à la ref carteDessus en faisant : 
	 * carteDessus.setSymbole(carteAjoutée.getSymbole()) 
	 * et carteDessus.setValeur(carteAjoutée.getValeur())
	 * */
	
	public Talon() {
		this.carteDessus= new Carte("?","?"); // initialisation carte du dessus pour pas renvoyer un pointeur null
	}
	
	/**
	 * methode qui permet de mettre toutes les cartes du talon dans la pioche
	 * (lorsque cette dernière ne permet pas au joueur de piocher le bon nombre de
	 * cartes), sauf la carte du dessus du talon qui reste, et on conserve les
	 * dernieres cartes de la pioche au dessus de celle-ci La methode ne renvoie
	 * rien en retour
	 */

	public void devenirPioche() {
		
		int nombreCartesPioche = Partie.getPartie().getPioche().getCartes().size();
		System.out.println("\nIl reste " + nombreCartesPioche + " cartes dans la pioche, on tranfère le talon dans la pioche !\n");
		ArrayList<Carte> cartesRestantes = new ArrayList<Carte>(); // les dernieres cartes qui restaient de la pioche

		// on stocke les dernieres cartes de la pioche dans une collection, dans l'ordre
		while(Partie.getPartie().getPioche().getCartes().size()>0) {
			cartesRestantes.add(Partie.getPartie().getPioche().getCartes().get(0));
			Partie.getPartie().getPioche().getCartes().remove(0);
		}
		// On met toutes les cartes du talon, sauf la carte du dessus , dans la pioche
		while(this.cartes.size()>1) {
			Partie.getPartie().getPioche().getCartes().add(this.cartes.get(0));
			this.cartes.remove(0);
		}
		// On melange la pioche
		Partie.getPartie().getPioche().melanger();
		// On rajoute les dernieres cartes a piocher (qui n'étaient pas suffisantes pour
		// que le joueur puisse piocher correctement), dans la pioche
		while (cartesRestantes.size()>0) {
			Partie.getPartie().getPioche().getCartes().add(cartesRestantes.get(0));
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
