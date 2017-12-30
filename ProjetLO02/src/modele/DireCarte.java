/**
 * 
 */
package modele;
import java.util.*;

/**
 * @author charl
 *
 */
public class DireCarte extends Observable  implements Runnable{

	private Manche m;
	/**
	 * 
	 */
	public DireCarte(Manche m) {
		this.m = m;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {// Temps de delais entre chaque tour
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int tour = m.getTourJoueur() - 1;
		Joueur jEnCours = m.getJoueur().get(tour);
		if (!jEnCours.isaDitcarte() && jEnCours.getCartes().size()==1) {
			jEnCours.setContreCarte();
			
		}
		
	}

}
