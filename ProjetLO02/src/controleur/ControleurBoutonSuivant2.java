package controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.*;
import modele.*;
import vue.InterfaceManche;
import modele.Partie;

public class ControleurBoutonSuivant2 {
	public ControleurBoutonSuivant2(JFrame frame, JComboBox choixVariante, JButton choixSuivant, Partie p) {

		choixSuivant.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String variante;
				variante = (String) choixVariante.getSelectedItem();
				p.setManche(new Manche(p.getNbJoueursVirtuels(), p.getJoueur(), variante));
				new InterfaceManche(frame,p);
				p.lancerPartieGraphique();
			}
		});
	}
}
