package modele.effets;
import modele.Joueur;

import java.util.Observable;

import modele.effets.Effet;

public class DireContreCarte extends Observable implements Runnable, Effet {

	private Joueur jAContrer;

	public DireContreCarte(Joueur jAContrer) {
		this.jAContrer = jAContrer;
	}

	@Override
	public void run() {
		try {// Temps de delais entre chaque tour
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!this.jAContrer.isContreCarte() && this.jAContrer.getCartes().size() == 1 && !jAContrer.isaDitcarte()) {
			// si apres 3 secondes, on n'a pas contré le joueur
			this.jAContrer.setaDitcarte();// il dit carte

		}

	}
	
	@Override
	public void effet() {
		Thread t = new Thread(this);
		t.start();
	}

}
