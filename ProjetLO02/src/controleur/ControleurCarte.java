package controleur;

import java.awt.event.*;

import modele.Carte;
import modele.Partie;
import vue.VueCarte;

public class ControleurCarte {

	
	public ControleurCarte(Partie p, Carte carteAControler, VueCarte vueCarteAControler) {
		vueCarteAControler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p.getManche().getTourJoueur() == 0) {
					int indexCarte = p.getJoueur().get(0).choisirCarte(carteAControler);
				}
			}
		});
	}
}
