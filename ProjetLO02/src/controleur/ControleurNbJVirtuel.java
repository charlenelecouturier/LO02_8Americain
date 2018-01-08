package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ControleurNbJVirtuel {

	private JComboBox choix;
	private int[] niveauxJoueurs;

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
				//JLabel niveauxJoueurs = new

			}
		});
	}
}