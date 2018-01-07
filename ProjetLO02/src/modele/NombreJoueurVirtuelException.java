package modele;
/**
 *<b>Classe d'exception en cas de mauvais nombre de joueurs virtuels entré par l'utilisateur. </b> 
 *
 *@author Charlene et Robin
 *@version 1.0
 *@see Partie#main(String[])
 */
public class NombreJoueurVirtuelException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NombreJoueurVirtuelException() {
		System.out.println("Erreur : vous devez rentrer un nombre entier s'il vous plait. Veuillez recommencer.");
	}
}
