package modele.effets;
import modele.Joueur;

import java.util.Observable;

import modele.effets.Effet;
/**
 * <b> Effet gérant les actions Dire ContreCarte des Joueurs Virtuels (lorsqu'un joueur n'a plus qu'une carte.) </b>
 * @author Charlene et Robin
 * @version 1.0
 */
public class DireContreCarte extends Observable implements Runnable, Effet {

	private Joueur jAContrer;

	public DireContreCarte(Joueur jAContrer) {
		this.jAContrer = jAContrer;
	}

	/**
	 * <b> Lors de l'appel d'un effet DireCarte, un nouveau thread est créé et se lance.</b>
	 */
	public void effet() {
		Thread t = new Thread(this);
		t.start();
	}
	/**
	 * <b> Après une pause de 3 secondes, si personne n'a contré le joueur, il dit Carte. </b>
	 */
	public void run() {
		try {// Temps de delais entre chaque tour
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!this.jAContrer.isContreCarte() && this.jAContrer.getCartes().size() == 1 && !jAContrer.isaDitcarte()) {
			// si apres 3 secondes, on n'a pas contr� le joueur
			System.out.println(jAContrer.getName()+ " dit carte !");
			this.jAContrer.setaDitcarte();// il dit carte

		}

	}
	
	

}
