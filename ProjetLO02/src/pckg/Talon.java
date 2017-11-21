package pckg;
import java.util.ArrayList;

/**
 * @author Robin et Charlene
 *
 */
public class Talon extends PorteurCarte{

	/**
	 * @param args
	 */
 private Carte carteDessus;
 
 
 /** methode qui permet de mettre toutes les cartes du talon dans la pioche (lorsque cette dernière ne permet
  *  pas au joueur de piocher le bon nombre de cartes), sauf la carte du dessus du talon qui reste, et on 
  *  conserve les dernieres cartes de la pioche au dessus de celle-ci
  *  La methode ne renvoie rien en retour*/
 
 public void devenirPioche() { 
	 int nombreCartesPioche =Partie.getPartie().getPioche().getCartes().size();
	 ArrayList<Carte> cartesRestantes = new ArrayList<Carte>(); // les dernieres cartes qui restaient de la pioche
	 int i;
	 //on stocke les dernieres cartes de la pioche dans une collection, dans l'ordre
	 for (i=0;i<nombreCartesPioche;i++) {
		 cartesRestantes.add(Partie.getPartie().getPioche().getCartes().get(i));
		 Partie.getPartie().getPioche().getCartes().remove(i);
	 }
	 //On met toutes les cartes du talon, sauf la carte du dessus , dans la pioche
	 for ( i=0; i<this.cartes.size()-1;i++) {
		Partie.getPartie().getPioche().getCartes().add(this.cartes.get(i));
		this.cartes.remove(i);
	 }
	 // On melange la pioche
	 Partie.getPartie().getPioche().melanger();
	 // On rajoute les dernieres cartes a piocher (qui n'étaient pas suffisantes pour que le joueur puisse piocher correctement), dans la pioche
	 for (i=0;i<nombreCartesPioche;i++) {
		 Partie.getPartie().getPioche().getCartes().add(cartesRestantes.get(i));
		 cartesRestantes.remove(i);
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

}
