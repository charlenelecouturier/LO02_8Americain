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
	private Joueur jDoitDireCarte;
	/**
	 * 
	 */
	public DireCarte(Manche m, Joueur j) {
		this.jDoitDireCarte=j;
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
		if (!this.jDoitDireCarte.isaDitcarte() &&this.jDoitDireCarte.getCartes().size()==1) {
			this.jDoitDireCarte.setContreCarte();		
		}
		
	}

}
