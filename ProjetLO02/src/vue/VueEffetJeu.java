package vue;

import java.util.*;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;
import modele.Joueur;
/**
 * <b>Créé les messages des effets dans la boite de dialogue du jeu</b>
 * <p>Permet au joueur de se repérer dans l'avancement de la partie.</p>
 * @author Charlene et Robin
 * @version 1.0
 */
public class VueEffetJeu extends JTextArea implements Observer {
	
	private static final long serialVersionUID = 1L;
	public VueEffetJeu() {

		Font f = new Font("Serif", Font.BOLD, 18);
		this.setFont(f);
		this.setLineWrap(true);
		this.setForeground(new Color(0, 0, 0));
		this.setEditable(false);
		this.setBackground(new Color(39, 135, 75));
		this.setBounds(5, 270, 200, 178);
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1.equals("CARTE ! ")) {
			this.insert(((Joueur) arg0).getName() + " dit " + arg1 + "\n", 0);
		} else if (arg1.equals("CONTRE-CARTE ! ")) {
			this.insert(((Joueur) arg0).getName() + " recoit un " + arg1 + "\n", 0);

		} else if (arg1.equals("gagne")) {
			this.insert(((Joueur) arg0).getName() + " a " + arg1 + " !\n", 0);
		} else if (arg1.equals("Le sens a change !")) {
			this.insert(arg1 + " \n", 0);
		} else if (arg1.equals("CARREAU") || arg1.equals("COEUR") || arg1.equals("PIQUE") || arg1.equals("TREFLE")) {
			this.insert(((Joueur) arg0).getName() + " a change de famille : " + arg1 + " \n", 0);
		} else {

			this.insert(((Joueur) arg0).getName() + " " + arg1 + " \n", 0);
		}

	}
}
