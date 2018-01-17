package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import modele.Joueur;
import modele.JoueurPhysique;
import modele.Partie;
/**
 * <b>Contrôleur du Bouton de l'action dire carte selon le pattern MVC.</b>
 * <p>Fait la transition lors d'un clic sur le bouton carte vers l'action <i>jPhysique.setaDitcarte() </i> </p>
 * @see JoueurPhysique#setaDitcarte()
 * @author Robin et Charlene
 * @version 1.0
 */
public class ControleurBoutonCarte {
	public ControleurBoutonCarte(Partie p, JButton carte, JTextArea effetjeu) {
		carte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Joueur jPhysique = p.getManche().getJoueur().get(0);
				if (jPhysique.getCartes().size() == 1 && jPhysique instanceof JoueurPhysique
						&& !jPhysique.isContreCarte() && !jPhysique.isaDitcarte()) {
					System.out.println(jPhysique.getName()+ " dit carte !");

					jPhysique.setaDitcarte();

				}
			}
			

		});
		
	}
}
