package pckg;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Partie {

	private static Partie instancePartie;
	private int nbJoueursVirtuels ;
	private int nbJoueursEnCours ;
	private String etat ;
	private int tourJoueur ;
	private static int sens ;
	private Talon talon;
	private ArrayList<Joueur> joueur = new ArrayList<Joueur>();//Collection plus adaptée qu'un tableau pour gérer les joueurs
	private ArrayList<Joueur> classementJoueurs = new ArrayList<Joueur>();
	private Variante variantePartie;
	private Pioche pioche;
	

	
	private Partie() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    System.out.println("Saisissez le nombre de joueurs virtuels :"); // le joueur physique choisit le nombre de joueurs virtuels
	    int nbJoueursVirtuels = sc.nextInt();
		setNbJoueursVirtuels(nbJoueursVirtuels);
		
		
		
		//this.joueur=new Joueur[this.nbJoueursVirtuels+1];
		
		//instanciation des joueurs
		this.joueur.add( new JoueurPhysique());
		int i;
		for (i=1;i<=this.nbJoueursVirtuels;i++) {
			this.joueur.add(new JoueurVirtuel());
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
		// si il ya plus de 5 joueurs en tout au départ on rentre dans la boucle if(), car on utilise 1 paquet pour 5 joueur
		
		int nombreDePaquet=1;
		if(this.nbJoueursVirtuels>4) {
			
			nombreDePaquet +=(this.nbJoueursVirtuels +1)/5;
		}
			this.variantePartie= new VarianteMinimale(nombreDePaquet);
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

	public boolean gagnePartie() { 
	
		int i;
		for (i=0;i<this.nbJoueursEnCours;i++) {
			if (this.joueur.get(i).getCartes().isEmpty()) {
				System.out.println(this.joueur.get(i).getName()+" a gagné.");
				this.classementJoueurs.add(this.joueur.get(i));
				this.joueur.remove(i);
				this.nbJoueursEnCours=this.nbJoueursEnCours -1;
				int j;
				for (j=0;j<this.nbJoueursEnCours;j++) {
			
					this.joueur.get(j).setNumero(j+1);;
				}
				return true;
			}
	
		}
		return false;	
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
	    
	    while (p.etat=="EN COURS" ) // tant que la partie est en cours
	    	{
	    // temps de délais entre chaque tour
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	// P.tourJoueur correspond au numéro du joueur qui doit jouer
	    	p.joueur.get(p.tourJoueur-1).jouerTour();	    	// l'incrémentation ou la décrémentation de "tourJoueur" est générée dans la methode "jouerTour()" ,car, selon la carte posée, un tour peut etre sauté ou le sens du jeu peut être changé

	    	System.out.println("\n");
	    	
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
	public ArrayList<Joueur> getJoueur() {
		return joueur;
	}





	/**
	 * @param joueur the joueur to set
	 */
	public void setJoueur(ArrayList<Joueur> joueur) {
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