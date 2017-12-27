package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import modele.Partie;

public class ControleurNbJVirtuel {

	private JComboBox choix;

	public ControleurNbJVirtuel(JComboBox nbJoueurs) {

		// public ControleurInterrupteur(Interrupteur itr, JButton jbt){
		this.choix = nbJoueurs;
		choix.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				String select = (String) choix.getSelectedItem();
				int nbSelect =Integer.parseInt(select);
				Partie.getPartie().setNbJoueursVirtuels(nbSelect);

			}
		});
	}
}