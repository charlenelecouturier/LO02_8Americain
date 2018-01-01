package modele;

import java.util.Observable;

public class DireContreCarte extends Observable implements Runnable {

	private Manche m;
	private Joueur jAContrer;

	public DireContreCarte(Manche m, Joueur jAContrer) {
		this.m = m;
		this.jAContrer = jAContrer;
		Thread t = new Thread(this);
		t.start();
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

}
