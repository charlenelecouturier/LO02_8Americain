package modele.effets;
import java.util.*;
import modele.*;
import modele.effets.Effet;

/**
 * @author charl
 *
 */
public class DireCarte extends Observable  implements Runnable,Effet{

	private Joueur jDoitDireCarte;
	/**
	 * 
	 */
	public DireCarte( Joueur j) {
		this.jDoitDireCarte=j;
		
	}

	@Override
	public void run() {
		try {// Temps de delais entre chaque tour
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!this.jDoitDireCarte.isaDitcarte() &&this.jDoitDireCarte.getCartes().size()==1) {
			this.jDoitDireCarte.setContreCarte();		
		}
		this.jDoitDireCarte.changed();
		this.jDoitDireCarte.notifyObservers("a fini");
		
	}

	@Override
	public void effet() {
		Thread t = new Thread(this);
		t.start();
	}

}
