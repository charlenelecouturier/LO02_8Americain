package controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.*;
import modele.*;
import modele.variantes.Variante;
import vue.InterfaceChoixVarianteNouvelleManche;
import vue.InterfaceManche;
/**
 * <b>Contrôleur du Bouton Suivant lors du choix d'une variante entre 2 manches.</b>
 * <p>Sélectionne la variante choisie par le joueur dans le modele, créé une nouvelle manche et une nouvelle interface graphique de manche. </p>
 * @see Manche
 * @see Variante
 * @see InterfaceChoixVarianteNouvelleManche
 * @author Robin et Charlene
 * @version 1.0
 */
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
