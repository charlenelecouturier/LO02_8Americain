package vue;
import java.util.*;
import java.awt.Font;
import javax.swing.*;

import java.awt.Color;
import modele.Joueur;

public class VueEffetJeu extends JTextArea implements Observer {

	public VueEffetJeu() {
		Font f = new Font("Serif", Font.BOLD, 18);
		this.setFont(f);
		this.setLineWrap(true);
		this.setForeground(new Color(0, 0, 0));
		this.setEditable(false);		
		this.setBackground(new Color(39, 135, 75));
		this.setBounds(5, 270, 200, 178);	
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1.equals("CARTE ! ")) {
			this.append("\n"+((Joueur) arg0).getName() + " dit " + arg1);
		} else if (arg1.equals("CONTRE-CARTE ! ")) {
			this.append("\n"+ ((Joueur) arg0).getName() + " recoit un " + arg1);

		} else if (arg1.equals("a pioche")) {
			this.append("\n"+ ((Joueur) arg0).getName() + " " + arg1);
		}
	}
}