package vue;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import modele.Partie;
import modele.Carte;


import javax.imageio.ImageIO;
import javax.swing.*;

// 1. Creer une classe a part qu'on instanciera dans l'interface 
public class VuePiocheTalon extends JPanel implements Observer{
	
	protected JLabel carteDessusTalon;
	protected JLabel dosPioche;
	
	
	public VuePiocheTalon() {
		/**
		 * Gestion du conteneur de la Pioche et du Talon. On définit sa position et les composants qu'il contient.
		 */
		this.setBackground(new Color(8, 81, 36));
		initialize();
		Carte carteDessus =Partie.getPartie().getManche().getTalon().getCarteDessus();
		ImageIcon imageCarte;
		imageCarte = new ImageIcon("Images/" + carteDessus.getSymbole() + "/" + carteDessus.getValeur() + ".png");
		this.carteDessusTalon.setIcon(imageCarte);
		this.add(carteDessusTalon);

	}
	
	public void initialize() {
		JLabel lblPioche = new JLabel("Pioche");
		dosPioche= new JLabel();
		dosPioche.setIcon(new ImageIcon("Images/dosPioche.jpg"));
		this.add(lblPioche);
		this.add(dosPioche);
		JLabel lblTalon = new JLabel("Talon");
		this.add(lblTalon);
		carteDessusTalon = new JLabel();
	}
	
	

	@Override
	public void update(Observable o, Object arg) {
		Carte carteDessus =Partie.getPartie().getManche().getTalon().getCarteDessus();
		ImageIcon imageCarte;
		imageCarte = new ImageIcon("Images/" + carteDessus.getSymbole() + "/" + carteDessus.getValeur() + ".png");
		this.carteDessusTalon.setIcon(imageCarte);
		this.add(carteDessusTalon);
		this.carteDessusTalon.setIcon(imageCarte);
		if(Partie.getPartie().getManche().getPioche().getCartes().isEmpty()) {
			this.dosPioche.removeAll();
		}
	}
	
   
  
  
	}
	

