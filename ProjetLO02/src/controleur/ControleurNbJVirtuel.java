package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import modele.JoueurPhysique;
import vue.VueJoueurVirtuel;
/**
 * <b>Contrôle la visibilité des VueJoueurVirtuel.</b>
 * <p>L'interface d'une manche comprend 5 JoueursVirtuels (le maximum). Si il y en a moins, celles en trop sont alors cachées pour éviter l'incompréhension du joueur./n
 * </p>
 * @see VueJoueurVirtuel
 * @author Robin et Charlene
 * @version 1.0
 */
public class ControleurNbJVirtuel {

	private JComboBox choix;

	public ControleurNbJVirtuel(JComboBox nbJoueurs, JPanel panelJ2, JPanel panelJ3, JPanel panelJ4,JPanel panelJ5,JPanel panelJ6, JComboBox comboBoxNbJoueursVirtuels) {

		// public ControleurInterrupteur(Interrupteur itr, JButton jbt){
		this.choix = nbJoueurs;
		choix.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				String select = (String) choix.getSelectedItem();
				int nbSelect =Integer.parseInt(select);
				//Partie.getPartie().setNbJoueursVirtuels(nbSelect);
				switch (nbSelect) {
				case 1 :
					panelJ2.setVisible(true);
					panelJ3.setVisible(false);
					panelJ4.setVisible(false);
					panelJ5.setVisible(false);
					panelJ6.setVisible(false);
				break;
				case 2 :
					panelJ2.setVisible(true);
					panelJ3.setVisible(true);
					panelJ4.setVisible(false);
					panelJ5.setVisible(false);
					panelJ6.setVisible(false);

				break;
				case 3 :
					panelJ2.setVisible(true);
					panelJ3.setVisible(true);
					panelJ4.setVisible(true);
					panelJ5.setVisible(false);
					panelJ6.setVisible(false);

				break;
				case 4 :
					panelJ2.setVisible(true);
					panelJ3.setVisible(true);
					panelJ4.setVisible(true);
					panelJ5.setVisible(true);					
					panelJ6.setVisible(false);

				break;
				case 5 :
					panelJ2.setVisible(true);
					panelJ3.setVisible(true);
					panelJ4.setVisible(true);
					panelJ5.setVisible(true);					
					panelJ6.setVisible(true);

				break;
				}
			}
		});
	}
}