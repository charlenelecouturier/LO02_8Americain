package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.*;
import javax.swing.*;
import modele.*;
/**
 * <b>Contrôleur du Bouton Démarrer de l'interface de début de partie</b>
 * <p>Transmet au modèle les choix sélectionnés par le Joueur lors d'un clic sur le bouton démarrer. </p>
 * @see InterfaceDebutPartie
 * @author Robin et Charlene
 * @version 1.0
 */
public class ControleurBoutonDemarrer {

	private JButton choix;
	public int nbJoueursVirtuels;
	ControleurNbJVirtuel cNbJoueurs;

	public ControleurBoutonDemarrer(JButton demarrer, JComboBox[] niveaux, JComboBox nbJoueurs,
			JComboBox comboBoxComptage, JTextField txtVotreNom, JComboBox comboBoxVariante, JFrame frame) {
		this.choix = demarrer;

		choix.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String select = (String) nbJoueurs.getSelectedItem();
				int nbSelect = Integer.parseInt(select);
				int[] niveauxJoueursVirtuels = new int[nbSelect];
				int i;
				for (i = 0; i < nbSelect; i++) {
					select = (String) niveaux[i].getSelectedItem();
					int niveauJ = Integer.parseInt(select);
					niveauxJoueursVirtuels[i] = niveauJ;
				}
				String nom = txtVotreNom.getText();
				String variante = (String) comboBoxVariante.getSelectedItem();
				select = (String) comboBoxComptage.getSelectedItem();
				Partie p = Partie.getPartie(niveauxJoueursVirtuels, select, nom, variante);
				new InterfaceManche(frame, p);
				p.lancerPartieGraphique();
			}
		});
	}
}