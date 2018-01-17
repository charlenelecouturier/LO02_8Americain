package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Partie;
import modele.JoueurPhysique;
import javax.swing.*;
/**
 * <b>Contrôleur du Bouton de choix d'une famille lorsque le joueur pose un 8.</b>
 * <p>Fait la transition lors d'un clic sur l'un des boutons famille vers le symbole de la carte du dessus du Talon</p>
 * @see Talon#getCarteDessus()#setSymbole(String couleur)
 * @author Robin et Charlene
 * @version 1.0
 */
public class ControleurBoutonChoixCouleur {
	public ControleurBoutonChoixCouleur(JButton choixcouleur, String couleur, JPanel choix,Partie p) {
		choixcouleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix.setVisible(false);
				System.out.println(couleur); 
				p.getManche().getTalon().getCarteDessus().setSymbole(couleur);
				((JoueurPhysique)p.getJoueur().get(0)).changed();
				((JoueurPhysique)p.getJoueur().get(0)).notifyObservers(couleur);
				((JoueurPhysique)p.getJoueur().get(0)).setaChangeDeFamille();
			}
		});
	}

}
