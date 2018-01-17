package vue;

import java.awt.Dimension;
import javax.swing.*;
import modele.Carte;

/**
 * <b>Créé un bouton cliquable pour chaque carte dans la main du joueur.</b>
 * <p>Associe une image adaptée à chaque carte du jeu de carte.</p>
 * @author Charlene et Robin
 * @version 1.0
 * @see Carte
 *
 */
public class VueCarte extends JButton {

	
	private static final long serialVersionUID = 1L;
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
