package controleur;
import modele.Joueur;
import modele.JoueurPhysique;
import java.awt.event.*;

import modele.Carte;
import modele.Partie;
import vue.VueCarte;

public class ControleurCarte {

	
	public ControleurCarte(Partie p, Carte carteAControler, VueCarte vueCarteAControler) {
		
		vueCarteAControler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p.getManche().getTourJoueur() == 1 && Partie.getPartie().getManche().getVarianteManche().estCompatible(carteAControler)) {
					int indexCarteChoisie =p.getJoueur().get(0).getCartes().indexOf(carteAControler);
					((JoueurPhysique)p.getJoueur().get(0)).jouerTourGraphique(indexCarteChoisie);
					//((JoueurPhysique)p.getJoueur().get(0)).sc.close();

				
					//Partie.getPartie().lancerPartieGraphique();
				}
			}
		});
	}
}
