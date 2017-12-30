package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import modele.Joueur;
import modele.JoueurPhysique;
import modele.Partie;

public class ControleurBoutonCarte {
	public ControleurBoutonCarte(Partie p, JButton carte,JTextArea effetjeu) {
		carte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tour = p.getManche().getTourJoueur() - 1;
				Joueur jPhysique = p.getManche().getJoueur().get(0);
				if(jPhysique.getCartes().size()==1 && jPhysique instanceof JoueurPhysique && !jPhysique.isContreCarte()) {
					jPhysique.setaDitcarte();
					
				}
			}
		});
	}
}
