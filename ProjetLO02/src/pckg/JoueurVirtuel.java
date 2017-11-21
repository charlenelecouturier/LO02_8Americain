package pckg;

//package pckg;

public class JoueurVirtuel extends Joueur{

	//*********Constructeur**********
	public JoueurVirtuel() {
		super();
		setName("Joueur" + getNumero());
	}
	
	
	public void changerFamille() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jouerTour() {
		boolean gagne = false;
		//1. on vérifie si le joueur peut jouer avec les cartes qu'il a dans la main
				if (Partie.getPartie().getVariantePartie().estPossibleDeJouer(this.cartes)) {
			//2.1. Le joueur choisit la carte qu'il desire poser sur le talon.
					int numeroCarte = this.choisirCarte();
					Carte cartePose = this.cartes.get(numeroCarte);
			//4.1 Le joueur pose la carte choisie sur le talon.
					Partie.getPartie().getTalon().setCarteDessus(cartePose);
					System.out.println(this.getName() + " joue " + cartePose);
			//5.1 Le joueur perd la carte qu'il a posée de sa main
					cartes.remove(cartePose);
					// on test si le joueur a gagné
					gagne = Partie.getPartie().gagnePartie();
				}
			//2.2. Le joueur ne peut jouer aucune carte, donc il pioche.
				else {
					System.out.println(this.getName() + " ne peut pas jouer, il pioche");
					this.piocher(1);
				}
				// c'est au tour du joueur suivant de jouer

				// On cherche le tour du joueur suivant
				int tour;
				tour= Partie.getPartie().getTourJoueur();

				// On regarde le sens de la partie
				if (Partie.getPartie().getSens()==1) {
					if(!gagne) {
						tour ++;
					}// si le joueur a gagné, on a décalé les joueurs dans la collection Partie.joueur donc ca reste le tour du joueur à la place actuelle
					// Si on depasse le numéro du dernier joueur, on revient au joueur 1 ( joueur physique)
					if( tour > Partie.getPartie().getNbJoueursEnCours()) {
						tour=1;		
					}
				}
				else {
					// si on trouve un numéro négatif, on revient au tour du dernier joueur ( joueur ayant le dernier numéro)
			
					tour--;
					if (tour<0) {
						tour=Partie.getPartie().getNbJoueursEnCours();
					}
				}
				Partie.getPartie().setTourJoueur(tour);
			}
	
	/** choisit la première carte compatible de son jeu*/
	public int choisirCarte() {
		int i=0;
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


	@Override
	public void DireContreCarte() {
		// TODO Auto-generated method stub
		
	}


	
	
	
}