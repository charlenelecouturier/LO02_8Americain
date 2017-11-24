package pckg;
/**
 * Joueur est la classe qui represente les participants au jeu, qu'ils soient physiques ou virtuels.
 * Il ne sera pas possible de creer un joueur dont le type n'est pas pr�cis� dans le jeu
 * @author Robin et Charl�ne
 * @see JoueurVirtuel 
 * @see JoueurPhysique
 */
public abstract class Joueur extends PorteurCarte {
	//**********attributs**************
	protected String name ;
	private int classement, numero ;  // num�ro est compris entre 1 et le nombre de joueurs en cours dans la partie
	private static int donneurNum = 1;
	private int score; // score du joueur
	private int scoreManche;
	
	//**********Constructeur************
/**
 * le Constructeur de Joueur ne doit pas �tre utilis� directement, il simplifie le code de ses classes filles.
 */
	public Joueur() {
		/*Permet de donner � chaque nouvelle instance de 
		joueur un nouveau num�ro */
		this.numero = donneurNum;
		donneurNum++;
		this.score=0; // initialement le joueur a 0 points
	}
	//********** Getter et Setters **********
	/**
	 * Accesseur du classement.
	 * @return la place � laquelle un joueur a fini
	 */
	public int getClassement() {
		return this.classement;
	}
	/**
	 * Accesseur du num�ro
	 * @return le num�ro du joueur, utilis�epar la partie pour determiner les tours de jeu.
	 * @see Partie
	 */
	public int getNumero() {
		return this.numero;
	}
	/**
	 * Accesseur du nom.
	 * @return le nom d'un joueur.
	 */
	public String getName() {
		return this.name;
	}
	//Pas de setter pour Numero, on ne doit pas pouvoir y toucher une fois instanci�
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Mutateur du classement, d�termine la place � laquelle un joueur finit.
	 * @param classe
	 */
	public void setClassement(int classe) {
		this.classement = classe;
	}
	
	
	/**
	 * Le corps meme de cette classe, jouerTour permet � un joueur physique ou virtuel de
	 * choisir une carte dans son jeu et la poser sur le talon.
	 */
	public void jouerTour() {
		
		boolean gagne=false;
		String effet="AucunEffet";
		effet=this.poserCarte();
		// on regarde si le fait d'avoir pos� une carte permet au joueur de gagner la manche
		gagne =this.gagnePartie();
		// si il n'y a pas eu d'effet modifiant le tour du joueur suivant
		if((!effet.equals("BloquerSuivant") &&  !effet.equals("ChangerSens"))|| effet.equals("AucunEffet")) {
			// On cherche le tour du joueur suivant
			int tour;
			tour= Partie.getPartie().getTourJoueur();
			// On regarde le sens de la partie
			if (Partie.getPartie().getSens()==1) {
				
				if(!gagne) {
					tour++;
				}
				// Si on depasse le num�ro du dernier joueur, on revient au joueur 1 ( joueur physique)
				if( tour > Partie.getPartie().getNbJoueursEnCours()) {
					tour=1;		
				}
			}
			else {// sens =-1
				// si on trouve un num�ro n�gatif, on revient au tour du dernier joueur ( joueur ayant le dernier num�ro)
				tour--;
				if (tour<0) {
					tour=Partie.getPartie().getNbJoueursEnCours();
				}
			}
			Partie.getPartie().setTourJoueur(tour);
		}
	}
	
	
	public abstract String poserCarte();
	
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public abstract void changerFamille();
	
	
	
	public void obligeDeRejouer() {
		
		if(this.cartes.isEmpty())
		{
			this.piocher(1);
		}
		else {
			
			this.poserCarte();
					
		}
		
	}
	
	
	

	public abstract int choisirCarte();
	public abstract void DireCarte();
	public abstract boolean DireContreCarte();
	
	public void piocher(int nombrePioche) {
		
		if(Partie.getPartie().getPioche().getCartes().size()< nombrePioche) 
		{
			Partie.getPartie().getTalon().devenirPioche();
		}
		//la boucle tourne autant de fois que le joueur doit piocher
		for (int i=1;i<=nombrePioche;i++) {
			//Ajoute aux cartes du joueur la derni�re carte de la pioche
			Carte cartePioche =Partie.getPartie().getPioche().cartes.get(Partie.getPartie().getPioche().cartes.size()-1); // -1 car indice commence � 0
			cartes.add(cartePioche);
			//Retire cette carte de la pioche
			Partie.getPartie().getPioche().cartes.remove(Partie.getPartie().getPioche().cartes.size()-1);
			System.out.println(this.name+ " a pioch� " + cartePioche);
			System.out.println("\nTest : il reste "+ Partie.getPartie().getPioche().getCartes().size()+" cartes dans la pioche");

		}
		
	}
	
	
	
	
	
	public boolean gagnePartie() { 
		if (this.cartes.isEmpty()) {
			System.out.println(this.name+" a gagn�.");
			Partie.getPartie().getClassementJoueurs().add(this);
			Partie.getPartie().getJoueur().remove(this);
			Partie.getPartie().setNbJoueursEnCours(Partie.getPartie().getNbJoueursEnCours()-1);
			return true;
				
		}
		return false;	
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * @return the scoreManche
	 */
	public int getScoreManche() {
		return scoreManche;
	}
	/**
	 * @param scoreManche the scoreManche to set
	 */
	public void setScoreManche(int scoreManche) {
		this.scoreManche = scoreManche;
	}
	
	
}
	

