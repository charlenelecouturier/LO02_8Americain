package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.VuePiocheTalon;
import modele.Partie;
import modele.JoueurPhysique;
import javax.swing.*;


public class ControleurBoutonChoixCouleur {
	public ControleurBoutonChoixCouleur(JButton choixcouleur, String couleur, JPanel choix,Partie p) {
		choixcouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix.setVisible(false);
				p.getManche().getTalon().getCarteDessus().setSymbole(couleur);
				((JoueurPhysique)p.getJoueur().get(0)).setaChangeDeFamille(true);
			}
		});
	}

}
