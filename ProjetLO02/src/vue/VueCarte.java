package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import modele.Carte;


public class VueCarte extends JButton {

	private ImageIcon imageCarte;
	
	//Constructeur de la vue d'une carte. On doit associer à chaque carte réelle du jeu l'image de sa carte.
	public VueCarte(Carte carte ) {
		super();
		// Le programme va chercher l'image associée à la carte dans le dossier Images
		this.imageCarte = new ImageIcon("Images/" + carte.getSymbole() + "/" + carte.getValeur() + ".png");
		this.setIcon(imageCarte);
		this.setMaximumSize(new Dimension(110, 270));
	}
	
	
}
