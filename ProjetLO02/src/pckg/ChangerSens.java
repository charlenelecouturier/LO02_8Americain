package pckg;


public class ChangerSens implements Effet{
	public void effet(){
		

		System.out.println("\nLa carte jou�e change de sens !\n");

			Partie.getPartie().setSens();
						
		}
}