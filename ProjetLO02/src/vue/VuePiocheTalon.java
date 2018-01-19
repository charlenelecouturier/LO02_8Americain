package vue;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import modele.Partie;
import modele.Carte;
import javax.swing.*;

/**
 * <b>Gestionnaire de la vue de la Pioche et du Talon. </b>
 * <p>Affiche une carte retournée en guise de Pioche et la carte du dessus du Talon comme JLabels./n
 * Cette vue est ensuite ajoutée à l'InterfaceManche et rafraichie à chaque notification de la pioche ou du talon.</p>
 * @see InterfaceManche
 * @author Charlene et Robin
 * @version 1.0
 */
public class VuePiocheTalon extends JPanel implements Observer {

	
	private static final long serialVersionUID = 1L;
	protected JLabel carteDessusTalon;
	protected JLabel lblPioche;

	public VuePiocheTalon() {
		/**
		 * Gestion du conteneur de la Pioche et du Talon. On définit sa position et les
		 * composants qu'il contient.
		 */
		this.setBackground(new Color(8, 81, 36));
		initialize();
	}

	public void initialize() {

		lblPioche = new JLabel(new ImageIcon("./Images/dosPioche.jpg"));
		this.add(lblPioche);
		Carte carteDessus = Partie.getPartie().getManche().getTalon().getCarteDessus();
		ImageIcon imageCarte;
		if (carteDessus.getValeur().equals("JOKER")) {
			imageCarte = new ImageIcon("./Images/JOKER/JOKER.png");
		} else {
			imageCarte = new ImageIcon("./Images/" + carteDessus.getSymbole() + "/" + carteDessus.getValeur() + ".png");
		}
		this.carteDessusTalon = new JLabel();
		this.carteDessusTalon.setIcon(imageCarte);
		this.add(carteDessusTalon);
	}

	/**
	 * Change la carte du dessus du Talon en fonction de celle au dessus de l'objet Talon.
	 * @see Talon
	 */
	public void update(Observable o, Object arg) {
		Carte carteDessus = Partie.getPartie().getManche().getTalon().getCarteDessus();
		ImageIcon imageCarte;
		if (carteDessus.getValeur().equals("JOKER")) {
			imageCarte = new ImageIcon("./Images/JOKER/JOKER.png");
		} else {
			imageCarte = new ImageIcon("./Images/" + carteDessus.getSymbole() + "/" + carteDessus.getValeur() + ".png");
		}
		this.carteDessusTalon.setIcon(imageCarte);
		this.add(carteDessusTalon);
		this.carteDessusTalon.setIcon(imageCarte);
	}
}
