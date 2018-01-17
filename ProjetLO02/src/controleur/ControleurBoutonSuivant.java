package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import modele.Partie;
import vue.InterfaceChoixVarianteNouvelleManche;
import vue.InterfaceFinManche;
import vue.InterfaceFinPartie;
/**
 * <b>Contrôleur du Bouton suivant présent sur l'interface de fin de manche</b>
 * <p>Si la partie est terminée, redirige vers l'interface de fin de partie au clic, sinon vers l'interface de choix de variante.</p>
 * @see InterfaceFinManche
 * @author Robin et Charlene
 * @version 1.0
 */
public class ControleurBoutonSuivant {

	private JButton suivant;

	public ControleurBoutonSuivant(JButton choixSuivant, Partie p, JFrame frame) {

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
