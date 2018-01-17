package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JTextArea;

import modele.Joueur;
import modele.JoueurVirtuel;
import modele.Partie;
/**
 * <b>Contrôleur du Bouton de l'action dire Contre-carte selon le pattern MVC.</b>
 * <p>Fait la transition lors d'un clic sur le bouton Contre-carte vers l'action <i>setContreCarte() </i>du joueur suivant. </p>
 * @see JoueurVirtuel#setContreCarte()
 * @author Robin et Charlene
 * @version 1.0
 */
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