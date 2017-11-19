


/**
 * Joueur est la classe qui represente les participants au jeu, qu'ils soient physiques ou virtuels.
 * Il ne sera pas possible de creer un joueur dont le type n'est pas précisé dans le jeu
 * @author Robin et Charlène
 * @see JoueurVirtuel 
 * @see JoueurPhysique
 */
public abstract class Joueur extends PorteurCarte {
	//**********attributs**************
	protected String name ;
	private int classement, numero ; 
	private static int donneurNum = 1;
	
	//**********Constructeur************
/**
 * le Constructeur de Joueur ne doit pas être utilisé directement, il simplifie le code de ses classes filles.
 */
	public Joueur() {
		/*Permet de donner à chaque nouvelle instance de 
		joueur un nouveau numéro */
		this.numero = donneurNum;
		donneurNum++;
	}
	//********** Getter et Setters **********
	/**
	 * Accesseur du classement.
	 * @return la place à laquelle un joueur a fini
	 */
	public int getClassement() {
		return this.classement;
	}
	/**
	 * Accesseur du numéro
	 * @return le numéro du joueur, utiliséepar la partie pour determiner les tours de jeu.
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
	//Pas de setter pour Numero, on ne doit pas pouvoir y toucher une fois instanciÃ©
	/**
	 * Mutateur du classement, détermine la place à laquelle un joueur finit.
	 * @param classe
	 */
	public void setName(String name) {
		this.name = name;
	}
	public void setClassement(int classe) {
		this.classement = classe;
	}
	
	
	/**
	 * Le corps meme de cette classe, jouerTour permet à un joueur physique ou virtuel de
	 * choisir une carte dans son jeu et la poser sur le talon.
	 * @param P
	 */
	public void jouerTour() {
	//1. on vérifie si le joueur peut jouer avec les cartes qu'il a dans la main
		if (Partie.getPartie().getVariantePartie().estPossibleDeJouer(this.cartes)) {
	//2.1. Le joueur choisit la carte qu'il desire poser sur le talon.
			System.out.println("Choisissez la carte que vous souhaitez jouer :");
			int numeroCarte = this.choisirCarte();
			Carte cartePose = this.cartes.get(numeroCarte);
	//3.1. Si le joueur choisit une carte qu'il ne peut pas jouer, 
		// il rentre dans une boucle jusqu'à  ce qu'il choisisse une bonne carte
			while  (!Partie.getPartie().getVariantePartie().estCompatible(cartePose)) {
				System.out.println("Cette carte ne peut être jouée, choisissez en une autre");
				numeroCarte= this.choisirCarte();
				cartePose = this.cartes.get(numeroCarte);
			}
			System.out.println("Vous posez "+ cartePose);
	//4.1 Le joueur pose la carte choisie sur le talon.
			Partie.getPartie().getTalon().setCarteDessus(cartePose);
	//5.1 Le joueur perd la carte qu'il a posée de sa main
			cartes.remove(cartePose);
		}
	//2.2. Le joueur ne peut jouer aucune carte, donc il pioche.
		else {
			System.out.println("Vous ne pouvez pas jouer, vous piochez.");
			this.piocher(1);
		}
		int tour= this.getNumero()+1;
		if( tour > Partie.getPartie().getNbJoueursEnCours()) {
			tour=1;		
		}
		Partie.getPartie().setTourJoueur(tour);
	}
	
	
	public abstract void changerFamille();
	
	public abstract int choisirCarte();
	public abstract void DireCarte();
	public abstract void DireContreCarte();
	
	public abstract void piocher(int nombrePioche); 
}
	

