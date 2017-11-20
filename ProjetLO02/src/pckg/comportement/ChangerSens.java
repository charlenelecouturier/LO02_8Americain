package comportement;
import pckg.Partie;


public class ChangerSens implements Effet{
	public void effet(){
		Partie.setSens();
	}
}
