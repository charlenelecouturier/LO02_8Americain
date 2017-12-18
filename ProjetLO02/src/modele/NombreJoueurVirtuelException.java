package modele;

public class NombreJoueurVirtuelException extends Exception {
	
	private static final long serialVersionUID = -6437137032817620746L;

	public NombreJoueurVirtuelException() {
		System.out.println("Erreur : vous devez rentrer un nombre entier s'il vous plait. Veuillez recommencer.");
	}
}
