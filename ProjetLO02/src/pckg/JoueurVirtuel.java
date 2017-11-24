	/* pour Strategy : 
	 * 
	 * faire en sorte que changerFamille() soit cohérent avec les cartes que le joueurVirtuel a en main
	 * ex : il choisit 'coeur' si il a le + de coeur dans son jeu
	 * 
	 * dans choisirCarte : 
	 * faire en sorte de jouer un 10 de pique ,par exemple, ( qui Oblige a rejouer) si il possede au moins 
	 * un autre pique dans son jeu ou sil ne peut jouer que ca
	 * 
	 * */




package pckg;
import java.util.Random;


//package pckg;

public class JoueurVirtuel extends Joueur{

	//*********Constructeur**********
	public JoueurVirtuel() {
		super();
		setName("Joueur" + getNumero());
	}
	
	
	@Override
	public void jouerTour() {
		String effet="AucunEffet";
		boolean gagne = false;
		//Le joueur pose une carte
		effet=this.poserCarte();
		//On test si le joueur a gagné
		gagne = this.gagnePartie();

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
					// Si on depasse le numéro du dernier joueur, on revient au joueur 1 ( joueur physique)
					if( tour > Partie.getPartie().getNbJoueursEnCours()) {
						tour=1;		
					}
				}
				else {// sens =-1
					// si on trouve un numéro négatif, on revient au tour du dernier joueur ( joueur ayant le dernier numéro)
					tour--;
					if (tour<0) {
						tour=Partie.getPartie().getNbJoueursEnCours();
					}
				}
				Partie.getPartie().setTourJoueur(tour);
			}
		}
	
	@Override
	public String poserCarte() {
		String effet="AucunEffet";
		//1. on vérifie si le joueur peut jouer avec les cartes qu'il a dans la main
		if (Partie.getPartie().getVariantePartie().estPossibleDeJouer(this.cartes)) {
	//2.1. Le joueur choisit la carte qu'il desire poser sur le talon.
			int numeroCarte = this.choisirCarte();
			Carte cartePose = this.cartes.get(numeroCarte);
	//4.1 Le joueur pose la carte choisie sur le talon.
			Partie.getPartie().getTalon().getCartes().add(cartePose);

			// on change la carte du dessus du Talon qui est un simple attribut de type Carte
			//Partie.getPartie().getTalon().setCarteDessus(cartePose);
			Partie.getPartie().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
			Partie.getPartie().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
			System.out.println(this.getName() + " joue " + cartePose);
	//5.1 Le joueur perd la carte qu'il a posée de sa main
			cartes.remove(cartePose);
			// s'il n'a plus qu'une carte il est possible qu'un joueur dise contre carte
			if(this.cartes.size()==1) {
				boolean contrecarte =Partie.getPartie().getJoueur().get(0).DireContreCarte();
				if(contrecarte) {
					this.piocher(1);
				}
			}
		
			//6.2 On regarde si c'est une carte Speciale
			if (cartePose.isCarteSpeciale()) {
					effet =Partie.getPartie().getVariantePartie().effetCarte(cartePose);
					cartePose.appliquerEffet(effet);
					}


		}		
				
	//2.2. Le joueur ne peut jouer aucune carte, donc il pioche.
		else {
			System.out.println(this.getName() + " ne peut pas jouer, il pioche");
			this.piocher(1);
		}
	return effet;
	}
	
	
	
	
	/** choisit la première carte compatible de son jeu*/
	public int choisirCarte() {
		int i=0;
		System.out.println(this.name + " a " + this.cartes.size() + " carte(s)");
		System.out.println("Carte du talon : "+ Partie.getPartie().getTalon().getCarteDessus());
		while(!Partie.getPartie().getVariantePartie().estCompatible(this.cartes.get(i)))
		{
			i++;
		}
		
		return i;
	}


	@Override
	public void DireCarte() {
		// TODO Auto-generated method stub
		
	}

	
	/** cette methode est appelée si le joueur physique n'est plus dans le jeu ( s'il a gagné)
	 * un joueur virtuel peut alors avoir une chance sur 4 de dire contre carte*/

	@Override
	public boolean DireContreCarte() {
		// TODO Auto-generated method stub
		System.out.println("Ce joueur n'a plus qu'une carte !");
		// Un joueur virtuel a une chance sur 4 de dire contre-carte
		Random r = new Random();
		int proba1Sur4 = 1 + r.nextInt(3);
		
		if (proba1Sur4 ==1) {
			// si le joueur a la place 0 est le joueur qui n'a plus qu'une carte
			if(Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur()-1).equals(Partie.getPartie().getJoueur().get(0))){
				
			// on choisi un numéro de joueur au hasard , sauf celui a la place 0 pour dire carte
			int numJoueurDitContreCarte =1+r.nextInt(Partie.getPartie().getJoueur().size()-2);
			System.out.println(Partie.getPartie().getJoueur().get(numJoueurDitContreCarte).getName()+ " dit CONTRE-CARTE");	
			return true;}
			//sinon c'est le joueur à l'emplacement 0 qui dit carte
			else {
				System.out.println(this.name + " dit CONTRE-CARTE");	
				return true;
			}
		}
		else {
			System.out.println("Ce joueur dit 'Carte'");
			return false;
		}
		
		
	}
	 @Override
	public void changerFamille() {
		
	}


	
	
	
}