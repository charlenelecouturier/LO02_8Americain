package controleur;
import modele.JoueurVirtuel;

import java.awt.event.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JTextArea;
import modele.Joueur;
import modele.JoueurPhysique;
import modele.Partie;

public class ControleurBoutonContreCarte {

	public ControleurBoutonContreCarte(Partie p, JButton Ccarte, JTextArea effetjeu) {
		Ccarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<Joueur> joueur = Partie.getPartie().getManche().getJoueur();
				Iterator<Joueur> it = joueur.iterator();
				boolean trouve = false;
				Joueur jNext;
				while (it.hasNext() && !trouve) {
					jNext = it.next();
					if (jNext.getCartes().size()==1 && jNext instanceof JoueurVirtuel) {
						trouve = true;
					}
					if (trouve&& !jNext.isaDitcarte()) {
						jNext.setContreCarte();
					}
				}
		Partie.getPartie().lancerPartieGraphique();	}
			
		});
		

	}
}
