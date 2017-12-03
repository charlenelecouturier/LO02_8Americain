package pckg;
import java.util.LinkedList;
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
	private LinkedList<Joueur> joueur;//Collection plus adaptée qu'un tableau pour gérer les joueurs
	private LinkedList<Joueur> classementJoueurs;
	private LinkedList<Joueur> classementJoueursPartie;
	private Variante variantePartie;
	private Pioche pioche;
	private String modeComptage;
	

	
	private Partie() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    System.out.println("Saisissez le nombre de joueurs virtuels :"); // le joueur physique choisit le nombre de joueurs virtuels
	    int nbJoueursVirtuels = sc.nextInt();
		setNbJoueursVirtuels(nbJoueursVirtuels);
		this.classementJoueurs=	 new LinkedList<Joueur>();
		this.classementJoueursPartie=	 new LinkedList<Joueur>();

		//instanciation des joueurs
		this.joueur= new LinkedList<Joueur>();
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
		
		//tourJoueur correspond eu numéro du joueur et est choisit au hasard
		Random r = new Random();
		int tourJoueur = 1 + r.nextInt(nbJoueursEnCours - 1); // le joueur qui debute la partie est choisi aleatoirement  	
		this.tourJoueur = tourJoueur;
		
		this.sens = 1; // sens des aiguilles d'une montre
		this.talon = new Talon();
		
		//choix de la variante
		this.variantePartie= choisirVariante();
		
		
		// mode de comptage des points 
		System.out.println("\nSaisir le mode de comptage des points : 'POSITIF' ou 'NEGATIF'");
		Scanner text = new Scanner(System.in);
		this.modeComptage=text.nextLine();
		if(this.modeComptage.equals("POSITIF")) {
			System.out.println("\nMode de comptage des points choisi : POSITIF ! Le premier joueur arrivé à 60 points gagne la partie ! \nLorsque 3 joueurs ont fini la manche, celle-ci se termine\n");
		}
		else {
			System.out.println("\nMode de comptage des points choisi : NEGATIF ! Lorsqu'un joueur atteint 100 point, il perd la partie ! \nUne manche se termine dès qu'un joueur a fini !\n");

		}
		
				
	}
	
	
	public Variante choisirVariante() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Saisissez la variante :\n1=Variante minimale\n2=Variante 5\n3=Variante 4 ");
		int variante = sc.nextInt();
		Variante choixVariante;
		if (variante == 1 )
		{
		
			choixVariante= new VarianteMinimale(this.nbJoueursVirtuels);
			System.out.println("Variante minimale choisie");

		}
		else if (variante == 2 )
		{
		
			choixVariante= new Variante5(this.nbJoueursVirtuels);
			System.out.println("Variante 5 choisie ");

		}
		
		else if (variante == 3 )
		{
		
			choixVariante= new Variante4(this.nbJoueursVirtuels);
			System.out.println("Variante 4 choisie ");

		}
		else {
			System.out.println("Erreur : variante inexistante, choisissez à nouveau");
			choixVariante =choisirVariante();
			
		}
		
		return choixVariante;
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
	
	
	public boolean terminerManche() {
		
	boolean terminer = false;
	// s'il y a 3 gagnant on arrette la manche  ( comptage positif)
	if (this.modeComptage.equals("POSITIF")) {
		// s'il y a 3 joueurs qui ont gagné, on s'il ne reste plus qu'un joueur
		if( this.classementJoueurs.size()==3 || this.nbJoueursEnCours==1) {
			terminer = true;
			while(!this.joueur.isEmpty()) {
			 
			// on met tous les joueurs restant dans le classement
				this.classementJoueurs.add(this.joueur.poll());
				//this.joueur.remove(0);
			}
			// test
			int i;
			System.out.println("Manche terminée !" );				

		}
	}
	// mode de comptage negatif
	else {
		//si un joueur a gagne, on arrete la manche
		if( this.classementJoueurs.size()==1 ) {
			terminer = true;
			while(!this.joueur.isEmpty()) {
			 
			// on met tous les joueurs restant dans le classement
				this.classementJoueurs.add(this.joueur.poll());
				//this.joueur.remove(0);
			}
			System.out.println("Manche terminée !" );	
		
		}
		
	}
	return terminer;
		
	
	}
	
	public void compterPointsPositif() {
		int i;
		// on ajoute les points correspondant aux 3 premiers
		this.classementJoueurs.get(0).setScore(this.classementJoueurs.get(0).getScore() + 50 );
		this.classementJoueurs.get(1).setScore(this.classementJoueurs.get(1).getScore() + 20 );
		
		if(this.classementJoueurs.size()>2) { // s'il y a plus de 2 joueurs
			this.classementJoueurs.get(2).setScore(this.classementJoueurs.get(2).getScore() + 10 );
		}
			
		// on ordonne la collection classementJoueurPartie : classement général de la partie
		// tri des joueurs par insertion
		//on ajoute le joueur dans l'ordre de leurs scores décroissants
		// (le meilleur est celui qui a le PLUS de points)

		this.triInsertionDecroisScore(this.classementJoueursPartie);
		//On donne le classsement de la manche
		System.out.println("Classement de la manche :");
		for(i=0;i<this.classementJoueurs.size();i++) {
			System.out.println((i+1) +"e : " + this.classementJoueurs.get(i).getName());
		}
		System.out.println("\nLe premier gagne 50 points, le deuxième 20 points, et le troisième 10 points");
		// on donne le classement général de la partie
		System.out.println("\nClassement général : ");
		 for(i= 1;i<=this.classementJoueursPartie.size();i++) {
			 	System.out.println(i + "e : " + this.classementJoueursPartie.get(i-1).getName() + " -- SCORE : " +this.classementJoueursPartie.get(i-1).getScore());
		 }
		
	}
	

	public void compterPointsNegatif() {
	// mode de comptage négatif

		int k,i, h;
		//ON PARCOURT LES JOUEURS
			for (k=0; k<this.classementJoueurs.size();k++) {
				//ON SELECTIONNE UN JOUEUR
				Joueur joueurSelect = this.classementJoueurs.get(k);
				// ON MET LE SCORE INITIAL DE LA MANCHE A 0 POUR LE JOUEUR
				joueurSelect.setScoreManche(0);
				
				// ON PARCOURT LES CARTES RESTANTES DU JOUEUR SELECTIONNE
				// on initialise les points qu'il va prendre à 0
				int points =0;
				for(h=0;h<joueurSelect.getCartes().size();h++) {
					Carte c = joueurSelect.getCartes().get(h);
					
					//ROI OU DAME
					if(c.getValeur().equals("DAME")|| c.getValeur().equals("ROI")) {
						points+=10;
						}
					//CARTE A EFFET FORT
					else if(c.getValeur().equals("1")|| c.getValeur().equals("8")|| c.getValeur().equals("JOKER")) {
						points+=50;
					}
					//CARTE EFFET MOYEN
					else if(c.getValeur().equals("10")|| c.getValeur().equals("7")||c.getValeur().equals("2") ||c.getValeur().equals("VALET")) {
						points+=20;

					}
					//AUTRE CARTE : ON AJOUTE LA VALEUR DE LA CARTE
					else {
						points+=Integer.parseInt(c.getValeur()) ;
					}
				}
				//modification du score génaral
				joueurSelect.setScore(joueurSelect.getScore()+points);
				//pour afficher le score de la manche
				joueurSelect.setScoreManche(points);
				System.out.println(joueurSelect.getName()+ " prend "+ points +" points");
				
			}
			// on ordonne la collection classementJoueurs : classement de la manche
			//on ajoute le joueur dans l'ordre de leurs scores croissants 
			//(le meilleur est celui qui a le MOINS de points)
			this.triInsertionCroisScoreManche(classementJoueurs);
							 
				// on donne le classement de la manche
			System.out.println("\nClassement de la manche : ");
			for(i= 1;i<=this.classementJoueurs.size();i++) {
				System.out.println(i + "e : " + this.classementJoueurs.get(i-1).getName() + " -- SCORE : " +this.classementJoueurs.get(i-1).getScoreManche());
				}
			// on ordonne la collection classementJoueurPartie : classement général  de la partie
			//on ajoute le joueur dans l'ordre de leurs scores croissants 
			//(le meilleur est celui qui a le MOINS de points)
			this.triInsertionCroisScore(this.classementJoueursPartie);
				  
	// on donne le classemnet général de la partie
	System.out.println("\nClassement général : ");
	 for(i= 1;i<=this.classementJoueursPartie.size();i++) {
		 	System.out.println(i + "e : " + this.classementJoueursPartie.get(i-1).getName() + " -- SCORE : " +this.classementJoueursPartie.get(i-1).getScore());
	 }
				
	}
	
	
	
	
	
	
	public void changerManche() {
		//posibilité de changer la variante
		this.variantePartie= choisirVariante();
		// On remet les joueurs dans le tableau de joueurs :
		int i,j;
		while(this.classementJoueurs.size()>0)
		{
			this.joueur.add(this.classementJoueurs.poll());
			//this.classementJoueurs.remove(0);
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
		
		//initialisation de la nouvelle partie
		// on retire les eventuelles cartes restantes des joueurs
		// Les joueur on pour etat "JOUE"
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
		// On initialise terminer a false
		boolean terminer =false;
		// mode de comptage positif
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
			// mode de comptage négatif, le premier qui arrive a 100 point a perdu et la partie se termine
			if(this.classementJoueursPartie.get(this.classementJoueurs.size()-1).getScore()>= 100) {
				terminer= true;
				this.etat="TERMINEE";
				 int i;
				 System.out.println("Partie terminée! Un joueur a eu au moins 100 point !");
			}
			
		}
		
		return terminer;
					
	}
	
	public void triInsertionCroisScore(LinkedList<Joueur> classementJoueurs) {
		int j;
		for(int i=0; i< classementJoueurs.size();i++) {
			  Joueur joueurJ =classementJoueurs.get(i);
			          
			   j=i;
			   while(j> 0 && classementJoueurs.get(j-1).getScore()>joueurJ.getScore()) 
			   {
			        	 classementJoueurs.set(j,classementJoueurs.get(j-1));
			        	  j = j - 1;
			   }
			   classementJoueurs.set(j,joueurJ);
		}
		
	}
	
	public void triInsertionCroisScoreManche(LinkedList<Joueur> classementJoueurs) {
		int j;
		for(int i=0; i< classementJoueurs.size();i++) {
			  Joueur joueurJ =classementJoueurs.get(i);
			          
			   j=i;
			   while(j> 0 && classementJoueurs.get(j-1).getScoreManche()>joueurJ.getScoreManche()) 
			   {
			        	 classementJoueurs.set(j,classementJoueurs.get(j-1));
			        	  j = j - 1;
			   }
			   classementJoueurs.set(j,joueurJ);
		}
		
	}
	public void triInsertionDecroisScore(LinkedList<Joueur> classementJoueurs) {

		int j;
		for(int i=0; i< classementJoueurs.size();i++) {
			  Joueur joueurJ =classementJoueurs.get(i);
			          
			   j=i;
			   while(j> 0 && classementJoueurs.get(j-1).getScore()<joueurJ.getScore()) 
			   {
			        	 classementJoueurs.set(j,classementJoueurs.get(j-1));
			        	  j = j - 1;
			   }
			   classementJoueurs.set(j,joueurJ);
		}
		
	}
	
	
	public static void main(String[] args) 
	
	{
		// présentation du jeu
		System.out.println("JEU DE 8 AMERICAIN \nPAR ROBIN LALLIER ET CHARLENE LECOUTURIER\n");
		
	   	//creation d'une partie
		Partie p = Partie.getPartie();
		//creation de la pioche
		p.pioche =new Pioche();
		// on melange la pioche
	    p.pioche.melanger();
	    // on distribue la pioche
	    p.pioche.distribuer();
	    
	    while(p.etat.equals("EN COURS")) {  // tant que la partie n'est pas terminée, on joue des manches
	    	
			while (! p.terminerManche()) // tant que la manche n'est pas terminée, on joue des tours
	    		{
				
				// Temps de délais entre chaque tour
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				// P.tourJoueur correspond au numéro du joueur qui doit jouer
				p.joueur.get(p.tourJoueur-1).jouerTour();// l'incrémentation ou la décrémentation de "tourJoueur" est générée dans la methode "jouerTour()" ,car, selon la carte posée, un tour peut etre sauté ou le sens du jeu peut être changé

				System.out.println("\n");
	    	
	    		}
				if (p.modeComptage.equals("POSITIF"))
				{
					p.compterPointsPositif();
					}
				else {
					p.compterPointsNegatif();
				}
				
				// Si la partie n'est pas terminée, on debute une nouvelle manche
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
	public LinkedList<Joueur> getJoueur() {
		return joueur;
	}





	/**
	 * @param joueur the joueur to set
	 */
	public void setJoueur(LinkedList<Joueur> joueur) {
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


	/**
	 * @return the classementJoueurs
	 */
	public LinkedList<Joueur> getClassementJoueurs() {
		return classementJoueurs;
	}


	/**
	 * @param classementJoueurs the classementJoueurs to set
	 */
	public void setClassementJoueurs(LinkedList<Joueur> classementJoueurs) {
		this.classementJoueurs = classementJoueurs;
	}


	/**
	 * @return the classementJoueursPartie
	 */
	public LinkedList<Joueur> getClassementJoueursPartie() {
		return classementJoueursPartie;
	}


	/**
	 * @param classementJoueursPartie the classementJoueursPartie to set
	 */
	public void setClassementJoueursPartie(LinkedList<Joueur> classementJoueursPartie) {
		this.classementJoueursPartie = classementJoueursPartie;
	}


	/**
	 * @return the modeComptage
	 */
	public String getModeComptage() {
		return modeComptage;
	}


	/**
	 * @param modeComptage the modeComptage to set
	 */
	public void setModeComptage(String modeComptage) {
		this.modeComptage = modeComptage;
	}

}