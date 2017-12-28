package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.*;
import javax.swing.*;
import modele.*;

public class ControleurBoutonDemarrer {

	private JButton choix;
	public int nbJoueursVirtuels;
	ControleurNbJVirtuel cNbJoueurs;
	
	public ControleurBoutonDemarrer(JButton demarrer, JComboBox[] niveaux, JComboBox nbJoueurs,JComboBox comboBoxComptage, JTextField txtVotreNom, JComboBox comboBoxVariante, TestInterface ti) {

		// public ControleurInterrupteur(Interrupteur itr, JButton jbt){
		this.choix = demarrer;
		
		
		choix.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String select = (String) nbJoueurs.getSelectedItem();
				int nbSelect =Integer.parseInt(select);
				int[] niveauxJoueursVirtuels= new int[nbSelect];
				int i;
				for(i=0;i<nbSelect;i++) {
					select = (String)niveaux[i].getSelectedItem();
					int niveauJ = Integer.parseInt(select);
					niveauxJoueursVirtuels[i]=niveauJ;
					}
				String nom =txtVotreNom.getText();
				String variante = (String)comboBoxVariante.getSelectedItem();
				select = (String)comboBoxComptage.getSelectedItem();
					Partie p =new Partie(niveauxJoueursVirtuels, select, nom, variante);
					ti.initializeGame(p);
					//p.lancerPartie();
				

				//ti.initializeGame();
				//if(cNbJoueurs.nbJoueursVirtuels!=0 &&)

			}
		});
	}
}