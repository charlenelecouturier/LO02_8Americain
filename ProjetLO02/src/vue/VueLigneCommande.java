package vue;
import modele.Joueur;
import modele.JoueurVirtuel;
import modele.Partie;
import java.util.*;

public class VueLigneCommande implements Observer ,Runnable{

public VueLigneCommande() {
Partie.getPartie().addObserver(this);
Partie.getPartie().getManche().getTalon().addObserver(this);
ListIterator<Joueur> it = Partie.getPartie().getJoueur().listIterator();
while(it.hasNext()) {
	it.next().addObserver(this);
}
Thread t = new Thread(this);
t.start();
 }

public void run() {

Partie.getPartie().lancerPartie();
}

@Override
public void update(Observable o, Object arg) {
	
}
}