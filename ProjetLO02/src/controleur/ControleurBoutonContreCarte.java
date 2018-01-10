package controleur;
import modele.JoueurVirtuel;

import java.awt.event.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JTextArea;
import modele.Joueur;
import modele.Partie;

public class ControleurBoutonContreCarte {

	public ControleurBoutonContreCarte(Partie p, JButton Ccarte, JTextArea effetjeu) {
		Ccarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<Joueur> joueur = Partie.getPartie().getManche().getJoueur();
				Iterator<Joueur> it = joueur.iterator();
				boolean trouve = false;
				Joueur jNext;
				do {
					jNext = it.next();
					if (jNext.getCartes().size() == 1 && jNext instanceof JoueurVirtuel) {
						trouve = true;
					}
				} while (it.hasNext() && !trouve);
				if (trouve && !jNext.isaDitcarte()) {
					System.out.println(jNext.getName()+ " est contre-carte !");
					jNext.setContreCarte();
				}
			}

		});
	}
}