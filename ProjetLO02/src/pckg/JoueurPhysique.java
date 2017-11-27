	package pckg;
	
	import java.util.Scanner;
	
	public class JoueurPhysique extends Joueur {
		Scanner sc = new Scanner(System.in);
	
		// ********** Conctructeur ***********
		public JoueurPhysique() {
			super();
	
			System.out.println("Entrez votre nom svp : ");
			this.name = sc.nextLine();
			System.out.println("OK Joueur1  : " + this.name);
		}
		
	public String poserCarte() {
			
			String effet="AucunEffet";
	
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
				Partie.getPartie().getTalon().getCartes().add(cartePose);
				System.out.println("Test : il y a "+ Partie.getPartie().getTalon().getCartes().size()+" cartes dans le talon");
				// on change la carte du dessus du Talon qui est un simple attribut de type Carte
				//Partie.getPartie().getTalon().setCarteDessus(cartePose);
				Partie.getPartie().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
				Partie.getPartie().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
		//5.1 Le joueur perd la carte qu'il a posée de sa main
				cartes.remove(cartePose);
				
				
		//6.1 si il n'a plus qu'une carte, le joueur a la possibilité de dire Carte
				if(this.cartes.size()==1) {
					this.DireCarte();
					}
	
			
				//6.2 On regarde si c'est une carte Speciale
				if (cartePose.isCarteSpeciale()) {
						effet =Partie.getPartie().getVariantePartie().effetCarte(cartePose);
						cartePose.appliquerEffet(effet);
						}
	
	
			}		
					
		//2.2. Le joueur ne peut jouer aucune carte, donc il pioche.
			//( a condition que la variante n'ai pas entrainé un effet comme 3piocher 2 cartes")
			// Dans ce cas, le fait de piocher 2 cartes est géré par la variante
		else {	
			if(this.EffetVariante.equals("Aucun")) {
			//System.out.println(this.getName() + " ne peut pas jouer, il pioche");
			System.out.println("Vous ne pouvez pas jouer, vous piochez.");
			this.piocher(1);
			}
				
		}
		return effet;
		}
		
	
	
	

	public int choisirCarte() { // doit renvoyer un int et non une Carte car sinon on crée une nouvelle carte,
								// et on ne peut plus utiliser remove(cartePose) dans jouerTour

		boolean choix; // variable qui permet la gestion des erreur : si le joueur entre un numéro trop
						// grand ou trop petit, qui ne correspond à aucun numéro de carte
		int numero;

		do {

			choix = true;
			System.out.println("Carte du talon : " + Partie.getPartie().getTalon().getCarteDessus());
			System.out.println("Vos cartes : ");
			int i;
			for (i = 1; i <= this.cartes.size(); i++) {
				System.out.println(i + " : " + this.cartes.get(i - 1));
			}
			System.out.println("Numéro de la carte choisie ?");
			numero = sc.nextInt();
			if (numero < 0 || numero > this.cartes.size()) // verification du numéro de carte choisi
			{
				choix = false;
				System.out.println("Erreur, numéro inexistant, faites un nouveau choix");
			}

		} while (!choix);
		if(this.cartes.get(numero-1).getValeur().equals("1")&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
			Variante5.nombreAs ++;
		}
		if(this.cartes.get(numero-1).getValeur().equals("8")&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
			Variante5.nombreAs=0;
		}
		return numero - 1;
	}

	@Override
	public void DireCarte() {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.println("\nVite vous n'avez plus qu'une carte ! Dites'CARTE' :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
		String reponse = scan.nextLine();
		long t2 = System.currentTimeMillis();
		if (t2 - t > 8000 || (!reponse.equals("carte") && !reponse.equals("Carte") && !reponse.equals("CARTE"))) {
			System.out.println("Vous n'avez pas dit 'CARTE' à temps ! CONTRE-CARTE !");
			this.piocher(1);
		} else {
			System.out.println("Vous dites 'CARTE'! ");

		}

	}

	@Override
	public boolean DireContreCarte() {

		Scanner scan = new Scanner(System.in);
		System.out.println("\nVite ce joueur n'a plus qu'une carte ! Dites'CONTRECARTE' :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
		String reponse = scan.nextLine();
		long t2 = System.currentTimeMillis();
		if (t2 - t > 8000 || (!reponse.equals("contrecarte") && !reponse.equals("Contrecarte")
				&& !reponse.equals("CONTRECARTE"))) {
			System.out.println("Ce joueur a dit 'CARTE' !\nVous n'avez pas dit 'CONTRECARTE' à temps !\n");
			return false;
		} else {
			System.out.println("Vous dites 'CONTRECARTE'!\n");
			return true;

		}

	}

	// **********Effets************
	@Override
	public void changerFamille() {

		System.out.println("Quel Symbole voulez-vous mettre ?\n1 : TREFLE\n2 : PIQUE\n3 : COEUR\n4 : CARREAU");
		Scanner scan = new Scanner(System.in);
		int rep = scan.nextInt();
		switch (rep) {
		// On change le symbole de la carte du dessus du talon, mais on ne change pas
		// vraiment le paquet
		// de cartes correspondant au talon : on change seulement l'attribut
		// carteDessus,
		// pas l'ArrayList "cartes" du talon
		case 1:
			Partie.getPartie().getTalon().getCarteDessus().setSymbole("TREFLE");
			System.out.println("Vous avez choisi comme symbole : TREFLE ! ");
			break;
		case 2:
			Partie.getPartie().getTalon().getCarteDessus().setSymbole("PIQUE");
			System.out.println("Vous avez choisi comme symbole : PIQUE! ");

			break;
		case 3:
			Partie.getPartie().getTalon().getCarteDessus().setSymbole("COEUR");
			System.out.println("Vous avez choisi comme symbole : COEUR ! ");

			break;
		case 4:
			Partie.getPartie().getTalon().getCarteDessus().setSymbole("CARREAU");
			System.out.println("Vous avez choisi comme symbole : CARREAU ! ");

			break;
		default:
			System.out.println("Mauvaise saisie, recommencez :");
			this.changerFamille();

		}

	}

}
