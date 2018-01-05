package vue;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import modele.Partie;
import modele.Carte;
import javax.swing.*;

// 1. Creer une classe a part qu'on instanciera dans l'interface 
public class VuePiocheTalon extends JPanel implements Observer {

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

		lblPioche = new JLabel(new ImageIcon("Images/dosPioche.jpg"));
		this.add(lblPioche);
		Carte carteDessus = Partie.getPartie().getManche().getTalon().getCarteDessus();
		ImageIcon imageCarte;
		if (carteDessus.getValeur().equals("JOKER")) {
			imageCarte = new ImageIcon("Images/JOKER/JOKER.png");
		} else {
			imageCarte = new ImageIcon("Images/" + carteDessus.getSymbole() + "/" + carteDessus.getValeur() + ".png");
		}
		this.carteDessusTalon = new JLabel();
		this.carteDessusTalon.setIcon(imageCarte);
		this.add(carteDessusTalon);
	}

	@Override
	public void update(Observable o, Object arg) {
		Carte carteDessus = Partie.getPartie().getManche().getTalon().getCarteDessus();
		ImageIcon imageCarte;
		if (carteDessus.getValeur().equals("JOKER")) {
			imageCarte = new ImageIcon("Images/JOKER/JOKER.png");
		} else {
			imageCarte = new ImageIcon("Images/" + carteDessus.getSymbole() + "/" + carteDessus.getValeur() + ".png");
		}
		this.carteDessusTalon.setIcon(imageCarte);
		this.add(carteDessusTalon);
		this.carteDessusTalon.setIcon(imageCarte);
	}
}
