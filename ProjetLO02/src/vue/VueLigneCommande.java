package vue;

import java.util.Observable;
import java.util.Observer;

import modele.JoueurPhysique;
import modele.Partie;
/**
 * <b> Gestionnaire de l'interface en ligne de commande. </b>
 * <p>Instancie un singleton de vue en Ligne de commande, qui permet au joueur de jouer dans la console s'il le souhaite.</p>
 * @author Charlene et Robin
 * @version 1.0
 */
public class VueLigneCommande implements Runnable, Observer {
	private static VueLigneCommande instanceVueLDC;
	private JoueurPhysique jPhysique;
	public static boolean attendre = false;

	private VueLigneCommande() {

		this.jPhysique = (JoueurPhysique) Partie.getPartie().getJoueur().get(0);
		this.jPhysique.addObserver(this);

	}

	public static VueLigneCommande getLDC() {

		if (VueLigneCommande.instanceVueLDC == null) {
			VueLigneCommande.instanceVueLDC = new VueLigneCommande();
		}
		return VueLigneCommande.instanceVueLDC;
	}

	public void run() {
		attendre = true;
		this.afficherCartes();
		attendre = false;
	}

	public void afficherCartes() {
		
		if (jPhysique.getEffetVariante().equals("Changer Famille")) {
			jPhysique.changerFamille();
		}else  {
			jPhysique.choisirCarte();
		}
		
	}
	/**
	 * <p>Met à jour la partie en fonction de l'avancée du modèle lorsqu'un objet du modèle envoie une notification à VueLigneCommande.</p>
	 */
	public void update(Observable arg0, Object arg1) {

		if (arg1 != null) {
			if (arg1.equals("a choisi")) {
				attendre = false;

			} else if (arg1.equals("joue")) {
				if (attendre != true) {
					Thread t = new Thread(this);
					t.start();
				} else {
					this.jPhysique.afficherCartes();
				}

			} else if (arg1.equals("a choisi famille")) {
				attendre = false;
			} else if (arg1.equals("Changer Famille")) {
				if (attendre==false) {
					Thread t = new Thread(this);
					t.start();
				} else {
					System.out.println(
							"Quel Symbole voulez-vous mettre ?\n1 : TREFLE\n2 : PIQUE\n3 : COEUR\n4 : CARREAU");

				}
			}

		}
	}
}
