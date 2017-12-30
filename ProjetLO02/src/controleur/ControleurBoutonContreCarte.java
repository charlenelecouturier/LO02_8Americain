package controleur;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import modele.Joueur;
import modele.Partie;

public class ControleurBoutonContreCarte {
	

	public ControleurBoutonContreCarte(Partie p, JButton Ccarte,JTextField effetjeu) {
		Ccarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tour = p.getManche().getTourJoueur() - 1;
				Joueur jEnCours = p.getManche().getJoueur().get(tour);
				if(jEnCours.getCartes().size()==1) {
					jEnCours.piocher(1);
				}
			}
		});
	}
}

