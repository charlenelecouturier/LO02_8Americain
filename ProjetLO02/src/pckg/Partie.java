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
	private int sens ;
	private Talon talon;
	private ArrayList<Joueur> joueur;//Collection plus adaptée qu'un tableau pour gérer les joueurs
	private ArrayList<Joueur> classementJoueurs;
	private ArrayList<Joueur> classementJoueursPartie;
	private Variante variantePartie;
	private Pioche pioche;
	private String modeComptage;
	

	
	private Partie() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    System.out.println("Saisissez le nombre de joueurs virtuels :"); // le joueur physique choisit le nombre de joueurs virtuels
	    int nbJoueursVirtuels = sc.nextInt();
		setNbJoueursVirtuels(nbJoueursVirtuels);
		this.classementJoueurs=	 new ArrayList<Joueur>();
		this.classementJoueursPartie=	 new ArrayList<Joueur>();

		//instanciation des joueurs
		this.joueur= new ArrayList<Joueur>();
		this.joueur.add( new JoueurPhysique());
		int i;
		for (i=1;i<=this.nbJoueursVirtuels;i++) {
			this.joueur.add(new JoueurVirtuel());
		}
		// on initialise le classement de la partie 
		
		for (i=0;i<this.joueur.size();i++) {
			this.classementJoueursPartie.add(this.joueur.get(i));
		}
		int nbJoueursEnCours= this.nbJoueursVirtuels +1;
		this.nbJoueursEnCours = nbJoueursEnCours;
		
		this.etat = "EN COURS";
		
		Random r = new Random();
		int tourJoueur = 1 + r.nextInt(nbJoueursEnCours - 1); // le joueur qui debute la partie est choisi aleatoirement  	
		this.tourJoueur = tourJoueur;
		
		this.sens = 1; // sens des aiguilles d'une montre
		this.talon = new Talon();
		
		//choix de la variante
		
		System.out.println("Saisissez la variante :\n1=Variante minimale ");
		int variante = sc.nextInt();
		if (variante == 1 )
		{
		
			this.variantePartie= new VarianteMinimale(this.nbJoueursVirtuels);
			System.out.println("Variante minimale choisie");

		}
		else {
			System.out.println("Erreur : variante inexistante");
		}
		
		// mode de comptage des points 
		System.out.println("Saisir le mode de comptage : 'POSITIF' ou 'NEGATIF'");
		Scanner text = new Scanner(System.in);
		this.modeComptage=text.nextLine();
		
				
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
				return true;
			}
	
		}
		return false;	
	}
	
	
	
	public boolean terminerManche() {
		
	boolean terminer = false;
	// s'il y a 3 gagnant on arrette la partie  ( comptage positif)
	if (this.modeComptage.equals("POSITIF")) {
		// s'il y a 3 joueurs qui ont gagné, on s'il ne reste plus qu'un joueur
		if( this.classementJoueurs.size()==3 || this.nbJoueursEnCours==1) {
			terminer = true;
			while(!this.joueur.isEmpty()) {
			 
			// on met tous les joueurs restant dans le classement
				this.classementJoueurs.add(this.joueur.get(0));
				this.joueur.remove(0);
			}
			// test
			int i;
			System.out.println("Manche terminée !" );
							
			//test : verification du classement
			System.out.println("Classement de la manche :");
			for(i=0;i<this.classementJoueurs.size();i++) {
				System.out.println((i+1) +"e : " + this.classementJoueurs.get(i).getName());
			}
			System.out.println("\nLe premier gagne 50 points, le deuxième 20 points, et le troisième 10 points");
							

		}
	}
	return terminer;
		
	
	}
	
	public void compterPoints() {
		
	if (this.modeComptage.equals("POSITIF")) {
		
		int i;
		// on ajoute les points correspondant aux 3 premiers
		this.classementJoueurs.get(0).setScore(this.classementJoueurs.get(0).getScore() + 50 );
		this.classementJoueurs.get(1).setScore(this.classementJoueurs.get(1).getScore() + 20 );
		
		if(this.classementJoueurs.size()>2) { // s'il y a plus de 2 joueurs
			this.classementJoueurs.get(2).setScore(this.classementJoueurs.get(2).getScore() + 10 );
		}
			
		// on ordonne la collection classementJoueurPartie : classement général 
		// tri des joueurs par insertion
		//on ajoute le joueur dans l'ordre de leurs scores décroissants
		int j;
		for(i=0; i< this.classementJoueursPartie.size();i++) {
			  Joueur joueurJ =this.classementJoueursPartie.get(i);
			          
			   j=i;
			   while(j> 0 && this.classementJoueursPartie.get(j-1).getScore()<joueurJ.getScore()) 
			   {
			        	  this.classementJoueursPartie.set(j,this.classementJoueursPartie.get(j-1));
			        	  j = j - 1;
			   }
			   this.classementJoueursPartie.set(j,joueurJ);
		}
		
	}
	int i;
	System.out.println("\nClassement général : ");
	 for(i= 1;i<=this.classementJoueursPartie.size();i++) {
		 	System.out.println(i + "e : " + this.classementJoueursPartie.get(i-1).getName() + " -- SCORE : " +this.classementJoueursPartie.get(i-1).getScore());
	 }
				
	}
	
	
	public void changerManche() {
		//posibilité de changer la variante
		System.out.println("Saisissez la variante :\n1=Variante minimale ");
		Scanner scanner = new Scanner(System.in);
		int variante = scanner.nextInt();
		if (variante == 1 ) // variante minimale choisie
		{
			this.variantePartie= new VarianteMinimale(this.nbJoueursVirtuels);
			System.out.println("Variante minimale choisie");

			}
		else {
			System.out.println("Erreur : variante inexistante");
			}
		// On remet les joueurs dans le tableau de joueurs :
		int i,j;
		while(this.classementJoueurs.size()>0)
		{
			this.joueur.add(this.classementJoueurs.get(0));
			this.classementJoueurs.remove(0);
			}
		
		// tri des joueurs par insertion
		//on ajoute le joueur dans l'ordre de leurs numéros croissants
	   for(i=0; i< this.joueur.size();i++) {
	    	 Joueur joueurJ =this.joueur.get(i);
	          
	          j=i;
	          while(j> 0 && this.joueur.get(j-1).getNumero()>joueurJ.getNumero()) {
	        	  this.joueur.set(j,this.joueur.get(j-1));
	        	  j = j - 1;
	          }
	          this.joueur.set(j,joueurJ);
	     }
			  	
		System.out.println(this.joueur.size());//TEST
		System.out.println(this.classementJoueurs.size());//TEST
		
		//initialisation de la nouvelle partie
		// on retire les eventuelles cartes restantes des joueurs
		for(i=0; i< this.joueur.size();i++) {
			this.joueur.get(i).getCartes().clear();
		}
		// on crée un nouveau talon
		this.talon= new Talon();
		// sens des aiguilles d'une montre
		this.sens=1;
		// on garde le même nombre de joueurs
		this.nbJoueursEnCours=this.nbJoueursVirtuels + 1;
		// on crée une nouvelle pioche
		this.pioche= new Pioche();
		// on mélange la pioche
		this.pioche.melanger();
		// on distribue la pioche
		this.pioche.distribuer();
		
	}
		
	
	public boolean terminerPartie(){
		boolean terminer =false;
		if (this.modeComptage.equals("POSITIF")) {
			// la partie se joue en 200 points mais on test avec 60			
			if(this.classementJoueursPartie.get(0).getScore()>= 60) {
				terminer= true;
				this.etat="TERMINEE";
				 int i;
				 System.out.println("Partie terminée! Un joueur a eu au moins 60 point !");
			}
			
		}
		
		else {
			
		}
		return terminer;
		
		
		
	}

	public static void main(String[] args) {
		
		System.out.println("JEU DE 8 AMERICAIN \nPAR ROBIN LALLIER ET CHARLENE LECOUTURIER\n");
	   	//creation d'une partie
		Partie p = Partie.getPartie();
		//creation de la pioche
		p.pioche =new Pioche();
		// on melange la pioche
	    p.pioche.melanger();
	    // on distribue la pioche
	    p.pioche.distribuer();
	    
	    while(p.etat.equals("EN COURS")) { 
			while (! p.terminerManche()) // tant que la partie est en cours
	    		{
				// temps de délais entre chaque tour
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// P.tourJoueur correspond au numéro du joueur qui doit jouer
				p.joueur.get(p.tourJoueur-1).jouerTour();// l'incrémentation ou la décrémentation de "tourJoueur" est générée dans la methode "jouerTour()" ,car, selon la carte posée, un tour peut etre sauté ou le sens du jeu peut être changé

				System.out.println("\n");
	    	
	    		}
			
				p.compterPoints();
				if(!p.terminerPartie())
				{	
					System.out.println("\nNOUVELLE MANCHE\n");
					p.changerManche();
				}
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




	public void setSens() {
		sens = sens*(-1);
	}

}