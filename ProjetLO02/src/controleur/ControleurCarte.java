package controleur;
import modele.JoueurPhysique;
import java.awt.event.*;

import modele.Carte;
import modele.Partie;
import vue.VueCarte;
/**
 * <b>Contrôleur du Bouton des cartes selon le pattern MVC.</b>
 * <p>Fait la transition lors d'un clic sur l'une des cartes de la main vers l'action <i>jouerTourGraphique(indexCarteChoisie) </i> du JoueurPhysique. </p>
 * @see JoueurPhysique#jouerTourGraphique(int)
 * @author Robin et Charlene
 * @version 1.0
 */
public class ControleurCarte {

	public ControleurCarte(Partie p, Carte carteAControler, VueCarte vueCarteAControler) {
		
		vueCarteAControler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p.getManche().getTourJoueur() == 1 && Partie.getPartie().getManche().getVarianteManche().estCompatible(carteAControler)) {
					int indexCarteChoisie =p.getJoueur().get(0).getCartes().indexOf(carteAControler);
					((JoueurPhysique)p.getJoueur().get(0)).jouerTourGraphique(indexCarteChoisie);
					
				}
			}
		});
	}
}
