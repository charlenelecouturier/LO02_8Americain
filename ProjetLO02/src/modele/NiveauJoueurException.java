package modele;




public class NiveauJoueurException extends Exception{
	public NiveauJoueurException() {
		System.out.println("Erreur : vous devez rentrer un niveau égal à 1 ou 2. Veuillez recommencer.");
		
		
	}
}
