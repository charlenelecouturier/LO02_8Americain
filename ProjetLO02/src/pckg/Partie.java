package pckg;

import java.util.Scanner;
import java.util.Random;

public class Partie {

	private static Partie instancePartie;
	private int nbJoueursVirtuels ;
	private int nbJoueursEnCours ;
	private String etat ;
	private int tourJoueur ;
	private static int sens ;
	private Talon talon;
	private Joueur[] joueur; //peut-être une collection plus adaptée?
	private Variante variantePartie;
	private Pioche pioche;
	

	
	private Partie() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    System.out.println("Saisissez le nombre de joueurs virtuels (maximum 4) :"); // le joueur physique choisit le nombre de joueurs virtuels
	    int nbJoueursVirtuels = sc.nextInt();
		setNbJoueursVirtuels(nbJoueursVirtuels);
		
		
		this.joueur=new Joueur[this.nbJoueursVirtuels+1];
		
		//instanciation des joueurs
		this.joueur[0]= new JoueurPhysique();
		int i;
		for (i=1;i<=this.nbJoueursVirtuels;i++) {
			this.joueur[i]= new JoueurVirtuel();
		}
		
		int nbJoueursEnCours= this.nbJoueursVirtuels +1;
		this.nbJoueursEnCours = nbJoueursEnCours;
		
		this.etat = "EN COURS";
		
		Random r = new Random();
		int tourJoueur = 1 + r.nextInt(nbJoueursEnCours - 1); // le joueur qui debute la partie est choisi aleatoirement  	
		this.tourJoueur = tourJoueur;
		
		Partie.sens = 1; // sens des aiguilles d'une montre
		this.talon = new Talon();
		
		//choix de la variante
		System.out.println("Saisissez la variante :\n1=Variante minimale ");
		int variante = sc.nextInt();
		if (variante == 1 )
		{
			this.variantePartie= new VarianteMinimale();
			System.out.println("Variante minimale choisie");

		}
		else {
			System.out.println("Erreur : variante inexistante");
		}
		
				
	}
/** Singleton 
 * *
 * 
 * @return Partie instance unique de la classe Partie
 */
	public static Partie getPartie() {
		
		if (Partie.instancePartie==null)
			
		{
			Partie.instancePartie= new Partie();
												}
		
		return Partie.instancePartie;		
	}



	public static void main(String[] args) {
		
	   	 //creation d'une partie
		Partie p = Partie.getPartie();
		//creation de la pioche
		p.pioche =new Pioche();

		// on melange la pioche
	    p.pioche.melanger();
	    // on distribue la pioche
	    p.pioche.distribuer();
	    
	    while (p.etat=="EN COURS") // tant que la partie est en cours
	    	{
	    // P.tourJoueur correspond au numéro du joueur qui doit jouer
	    	p.joueur[p.tourJoueur-1].jouerTour(); // l'incrémentation ou la décrémentation de "tourJoueur" est générée dans la methode "jouerTour()" ,car, selon la carte posée, un tour peut etre sauté ou le sens du jeu peut être changé
	    	//p.joueur[0].jouerTour();//TEST SUR JOUEUR PHYSIQUE
	    	}
	    
	}
	    
	    
	    
	  
	    
		

	

	/**
	 * @return the pioche
	 */
	public Pioche getPioche() {
		return pioche;
	}




	/**
	 * @param pioche the pioche to set
	 */
	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}






	/**
	 * @return the joueur
	 */
	public Joueur[] getJoueur() {
		return joueur;
	}





	/**
	 * @param joueur the joueur to set
	 */
	public void setJoueur(Joueur[] joueur) {
		this.joueur = joueur;
	}





	/**
	 * @return the talon
	 */
	public Talon getTalon() {
		return talon;
	}




	/**
	 * @param talon the talon to set
	 */
	public void setTalon(Talon talon) {
		this.talon = talon;
	}




	/**
	 * @return the variantePartie
	 */
	public Variante getVariantePartie() {
		return variantePartie;
	}




	/**
	 * @param variantePartie the variantePartie to set
	 */
	public void setVariantePartie(Variante variantePartie) {
		this.variantePartie = variantePartie;
	}




	public int getNbJoueursVirtuels() {
		return nbJoueursVirtuels;
	}




	public void setNbJoueursVirtuels( int nbJoueursVirtuels) {

		this.nbJoueursVirtuels = nbJoueursVirtuels;
	}




	public int getNbJoueursEnCours() {
		return nbJoueursEnCours;
	}




	public void setNbJoueursEnCours(int nbJoueursEnCours) {
		
		this.nbJoueursEnCours = nbJoueursEnCours;
	}




	public String getEtat() {
		return etat;
	}




	public void setEtat(String etat) {
		this.etat = etat;
	}




	public int getTourJoueur() {
		return tourJoueur;
	}




	public void setTourJoueur(int tourJoueur) {
		this.tourJoueur = tourJoueur;
	}




	public int getSens() {
		return sens;
	}




	public static void setSens() {
		sens = sens*(-1);
	}

}