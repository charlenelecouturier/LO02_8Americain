package modele;

/**
 *<b>Classe d'exception en cas de mauvais niveau de joueur virtuel entré par l'utilisateur. </b> 
 *
 *@author Charlene et Robin
 *@version 1.0
 *@see JoueurVirtuel#JoueurVirtuel()
 */
public class NiveauJoueurException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NiveauJoueurException() {
		System.out.println("Erreur : vous devez rentrer un niveau egal a� 1 ou 2. Veuillez recommencer.");
		
		
	}
}
