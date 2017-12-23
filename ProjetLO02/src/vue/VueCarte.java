package vue;

import javax.swing.*;
import modele.Carte;


public class VueCarte extends JButton {

	private ImageIcon imageCarte;
	
	//Constructeur de la vue d'une carte. On doit associer à chaque carte réelle du jeu l'image de sa carte.
	public VueCarte(Carte carte ) {
		super();
		//Le programme va chercher l'image associée à la carte dans le dossier Images
		this.imageCarte = new ImageIcon("Images/" + carte.getSymbole() + "/" + carte.getValeur() + ".png");
		this.setIcon(imageCarte);
		System.out.println("Path : " + ("Images/" + carte.getSymbole() + "/" + carte.getValeur()) + ".png");
	}
	
	
}
