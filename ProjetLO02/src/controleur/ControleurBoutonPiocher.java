package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.JoueurPhysique;
import modele.Carte;
import modele.Partie;
import vue.VueCarte;
import javax.swing.*;

public class ControleurBoutonPiocher {

	public ControleurBoutonPiocher(Partie p, JButton pioche) {
		pioche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (p.getManche().getTourJoueur() == 1
						&& Partie.getPartie().getManche().getJoueur().get(0) instanceof JoueurPhysique
						&& !p.getManche().getVarianteManche()
								.estPossibleDeJouer(p.getManche().getJoueur().get(0).getCartes())
						&& p.getManche().getJoueur().get(0).getEffetVariante().equals("Aucun")) {
					System.out.println("\nPIOCHEEEE\n");

					((JoueurPhysique) p.getManche().getJoueur().get(0)).jouerTourGraphique(-2);
					// on envoie -2 en paramètre : le joueur pioche
				}
			}
		});
	}
}