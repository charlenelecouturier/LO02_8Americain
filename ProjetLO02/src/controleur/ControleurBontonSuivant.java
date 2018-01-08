package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modele.Partie;
import vue.InterfaceChoixVarianteNouvelleManche;
import vue.InterfaceFinPartie;

public class ControleurBontonSuivant {

	private JButton suivant;

	public ControleurBontonSuivant(JButton choixSuivant, Partie p, JFrame frame) {

		this.suivant = choixSuivant;

		this.suivant.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				if (!p.terminerPartie()) {
					new InterfaceChoixVarianteNouvelleManche(frame);
				} else {
					new InterfaceFinPartie(frame, p);
				}

			}
		});
	}
}
