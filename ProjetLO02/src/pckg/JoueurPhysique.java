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


	public int choisirCarte() { // doit renvoyer un int et non une Carte car sinon on cr�e une nouvelle carte,
								// et on ne peut plus utiliser remove(cartePose) dans jouerTour

		boolean choix; // variable qui permet la gestion des erreur : si le joueur entre un num�ro trop
						// grand ou trop petit, qui ne correspond � aucun num�ro de carte
		int numero;
		System.out.println("Choisissez la carte que vous souhaitez jouer :");
		do {

			choix = true;
			System.out.println("Carte du talon : " + Partie.getPartie().getTalon().getCarteDessus());
			System.out.println("Vos cartes : ");
			int i;
			for (i = 1; i <= this.cartes.size(); i++) {
				System.out.println(i + " : " + this.cartes.get(i - 1));
			}
			System.out.println("Num�ro de la carte choisie ?");
			numero = sc.nextInt();

			if (numero < 0 || numero > this.cartes.size()||!Partie.getPartie().getVariantePartie().estCompatible(this.cartes.get(numero-1))){ // verification du num�ro de carte choisi	
				choix = false;
				System.out.println("Impossible, faites un nouveau choix");
			}

		} while (!choix);
		if (this.cartes.get(numero - 1).getValeur().equals("1")
				&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
			Variante5.nombreAs++;
		}
		if (this.cartes.get(numero - 1).getValeur().equals("1")
				&& Partie.getPartie().getVariantePartie() instanceof Variante4) {
			Variante4.couleur.setSymbole(this.cartes.get(numero - 1).getSymbole());
		}
		if (this.cartes.get(numero - 1).getValeur().equals("8")
				&& Partie.getPartie().getVariantePartie() instanceof Variante5) {
			Variante5.nombreAs = 0;
		}
		return numero - 1;
	}

	@Override
	public void direCarte() {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.println("\nVite vous n'avez plus qu'une carte ! Dites'CARTE' :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
		String reponse = scan.nextLine();
		long t2 = System.currentTimeMillis();
		if (t2 - t > 8000 || (!reponse.equals("carte") && !reponse.equals("Carte") && !reponse.equals("CARTE"))) {
			System.out.println("Vous n'avez pas dit 'CARTE' � temps ! CONTRE-CARTE !");
			this.piocher(1);
		} else {
			System.out.println("Vous dites 'CARTE'! ");

		}
	}

	public boolean direContreCarte() {

		Scanner scan = new Scanner(System.in);
		System.out.println("\nVite ce joueur n'a plus qu'une carte ! Dites'CONTRECARTE' :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
		String reponse = scan.nextLine();
		long t2 = System.currentTimeMillis();
		if (t2 - t > 8000 || (!reponse.equals("contrecarte") && !reponse.equals("Contrecarte")
				&& !reponse.equals("CONTRECARTE"))) {
			System.out.println("Ce joueur a dit 'CARTE' !\nVous n'avez pas dit 'CONTRECARTE' � temps !\n");
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
		// vraiment le paquet de cartes correspondant au talon : on change seulement
		// l'attribut carteDessus, pas l'ArrayList "cartes" du talon
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