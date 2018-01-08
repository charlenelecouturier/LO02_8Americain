package modele.effets;
import java.util.*;
import modele.*;
import modele.effets.Effet;

/**
 * <b> Effet gérant les actions Dire Carte de la Partie (lorsqu'un joueur n'a plus qu'une carte.) </b>
 * @author Charlene et Robin
 * @version 1.0
 */
public class DireCarte extends Observable  implements Runnable,Effet{

	private Joueur jDoitDireCarte;

	public DireCarte( Joueur j) {
		this.jDoitDireCarte=j;		
	}

	/**
	 * <b> Lors de l'appel d'un effet DireCarte, un nouveau thread est créé et se lance.</b>
	 */
	public void effet() {
		Thread t = new Thread(this);
		t.start();
	}
	/**
	 * <b> Après un temps de 3 secondes, si le joueur n'a pas dit carte, appelle la fonction setContreCarte(). </b>
	 * @see Joueur#setContreCarte()
	 */
	public void run() {
		try {// Temps de delais entre chaque tour
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!this.jDoitDireCarte.isaDitcarte() &&this.jDoitDireCarte.getCartes().size()==1) {
			System.out.println(jDoitDireCarte.getName()+ " est contre-carte !");
			this.jDoitDireCarte.setContreCarte();		
		}
		this.jDoitDireCarte.changed();
		this.jDoitDireCarte.notifyObservers("a fini");
		
	}

}
