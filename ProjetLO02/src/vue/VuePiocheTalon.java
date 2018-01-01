package vue;

import java.awt.*;
import controleur.ControleurBoutonPiocher;
import java.util.Observable;
import java.util.Observer;
import modele.Partie;
import modele.Carte;
import javax.swing.*;

// 1. Creer une classe a part qu'on instanciera dans l'interface 
public class VuePiocheTalon extends JPanel implements Observer {

	protected JLabel carteDessusTalon;
	protected JButton lblPioche;

	public VuePiocheTalon() {
		/**
		 * Gestion du conteneur de la Pioche et du Talon. On définit sa position et les
		 * composants qu'il contient.
		 */
		this.setBackground(new Color(8, 81, 36));
		initialize();
		Carte carteDessus = Partie.getPartie().getManche().getTalon().getCarteDessus();
		ImageIcon imageCarte;
		imageCarte = new ImageIcon("Images/" + carteDessus.getSymbole() + "/" + carteDessus.getValeur() + ".png");
		this.carteDessusTalon = new JLabel();
		this.carteDessusTalon.setIcon(imageCarte);
		this.add(carteDessusTalon);
		new ControleurBoutonPiocher(Partie.getPartie(), lblPioche);

	}

	public void initialize() {
		lblPioche = new JButton("Piocher", new ImageIcon("Images/dosPioche.jpg"));
		this.add(lblPioche);
		Font f = new Font("Arial", Font.BOLD | Font.ITALIC, 26);
		lblPioche.setFont(f);
		lblPioche.setForeground(new Color(0, 0, 0));
		lblPioche.setHorizontalTextPosition(SwingConstants.CENTER);
		JLabel lblTalon = new JLabel();
		this.add(lblTalon);
	}

	@Override
	public void update(Observable o, Object arg) {
		Carte carteDessus = Partie.getPartie().getManche().getTalon().getCarteDessus();
		ImageIcon imageCarte;
		imageCarte = new ImageIcon("Images/" + carteDessus.getSymbole() + "/" + carteDessus.getValeur() + ".png");
		this.carteDessusTalon.setIcon(imageCarte);
		this.add(carteDessusTalon);
		this.carteDessusTalon.setIcon(imageCarte);
	}
}
