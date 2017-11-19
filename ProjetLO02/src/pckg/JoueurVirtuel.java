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
				}
			//2.2. Le joueur ne peut jouer aucune carte, donc il pioche.
				else {
					System.out.println(this.getName() + " ne peut pas jouer, il pioche");
					this.piocher(1);
				}
				int tour= this.getNumero()+1;
				if( tour > Partie.getPartie().getNbJoueursEnCours()) {
					tour=1;		
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

	public void piocher(int nombrePioche) {
		//la boucle tourne autant de fois que le joueur doit piocher
		for (int i=1;i<=nombrePioche;i++) {
			//Ajoute aux cartes du joueur la dernière carte de la pioche
			Carte cartePioche =Partie.getPartie().getPioche().cartes.get(Partie.getPartie().getPioche().cartes.size()-1); // -1 car indice commence à 0
			cartes.add(cartePioche);
			//Retire cette carte de la pioche
			Partie.getPartie().getPioche().cartes.remove(Partie.getPartie().getPioche().cartes.size()-1);
			System.out.println(this.name+ " a pioché " + cartePioche); // on mettra juste "a pioché" quand on n'aura plus de problèmes dans le programme
		}
		
	}
}