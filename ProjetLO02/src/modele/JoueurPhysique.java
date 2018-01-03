package modele;
import modele.*;
import modele.variantes.*;
import java.util.ListIterator;
import java.util.Scanner;

public class JoueurPhysique extends Joueur {
	Scanner sc = new Scanner(System.in);

	public JoueurPhysique() {
		super();
		System.out.println("Entrez votre nom : ");
		this.name = sc.nextLine();
		System.out.println("OK Joueur1  : " + this.name);
	}
	
	public JoueurPhysique(String nom) {
		super();
		this.typeInterface="graphique";
		this.name=nom;
	}
	
	public int choisirCarte(Carte carteChoisie) {
		int indexCarteChoisie = -1;
		ListIterator<Carte> parcourirCarteJoueur = this.getCartes().listIterator();
		while(parcourirCarteJoueur.hasNext()) {
			int index = parcourirCarteJoueur.nextIndex();
			if(parcourirCarteJoueur.next() == carteChoisie &&
			   Partie.getPartie().getManche().getVarianteManche().estCompatible(carteChoisie)
				) {
				indexCarteChoisie =index;
			}
		}
		return indexCarteChoisie;
	}
	
	public void jouerTourGraphique(int indexCarteChoisie) {
		int tour;
		this.aDitcarte=false;
		this.contreCarte=false;
		boolean gagne = false;
		this.poserCarteGraphique(indexCarteChoisie);
		this.EffetVariante = "Aucun";
		gagne = this.gagnePartie();	// on regarde si le fait d'avoir pose une carte permet au joueur de gagner la manche
		tour = Partie.getPartie().getManche().getTourJoueur();
		if (Partie.getPartie().getManche().getSens() == 1) {
			if (!gagne) {
				tour++;
			}
			if (tour > Partie.getPartie().getManche().getNbJoueursEnCours()) {
				tour = 1;
			}
		} else {
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getManche().getNbJoueursEnCours();
			}
		}
		Partie.getPartie().getManche().setTourJoueur(tour);

	}

	public void poserCarteGraphique(int numeroCarte) {
		
		if (numeroCarte ==-2) { // le joueur pioche 1 carte
			this.piocher(1);
		} else if (Partie.getPartie().getManche().getVarianteManche().estPossibleDeJouer(this.cartes)) {

			Carte cartePose = this.cartes.get(numeroCarte);
			Partie.getPartie().getManche().getTalon().getCartes().add(cartePose);
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
			Partie.getPartie().getManche().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
			System.out.println("vous jouezz " + cartePose);
			cartes.remove(cartePose);
			// On notifie l'interface que la carte a ete retiree de la main du joueur

			if (this.cartes.size() == 1) {
				this.direCarteGraphique();
			}
			String effet = cartePose.getEffet();
			if (!effet.equals("Aucun")) {
				//cartePose.appliquerEffet();
			}

		}
		this.setChanged();
		this.notifyObservers();
	}

	public int choisirCarte() { 
		boolean choix;
		int numero;
		System.out.println("Choisissez la carte que vous souhaitez jouer :");
		do {
			choix = true;
			System.out.println("Carte du talon : " + Partie.getPartie().getManche().getTalon().getCarteDessus());
			System.out.println("Vos cartes : ");
			int i;
			for (i = 1; i <= this.cartes.size(); i++) {
				System.out.println(i + " : " + this.cartes.get(i - 1));
			}
			
			System.out.println("Numero de la carte choisie ?");
			numero = sc.nextInt();
			
			if (numero < 0 || numero > this.cartes.size()||!Partie.getPartie().getManche().getVarianteManche().estCompatible(this.cartes.get(numero-1))){ // verification du num�ro de carte choisi	
				choix = false;
				System.out.println("Impossible, faites un nouveau choix");
			}

		} while (!choix);
		if (this.cartes.get(numero - 1).getValeur().equals("1")) {
			Variante.nombreAs++;			
		} else if (this.cartes.get(numero - 1).getValeur().equals("8")) {
			Variante.nombreAs = 0;
		}
		if (this.cartes.get(numero - 1).getValeur().equals("1")
				&& Partie.getPartie().getManche().getVarianteManche() instanceof Variante4) {
			Variante4.couleur.setSymbole(this.cartes.get(numero - 1).getSymbole());
		}
		return numero - 1;
	}
	public void direCarteGraphique() {
		new DireCarte(Partie.getPartie().getManche(),this);		
	}
	
	public void direContreCarteGraphique(Joueur j) {
		new DireContreCarte(Partie.getPartie().getManche(),j);
	}
	@Override
	public void direCarte() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\nVite vous n'avez plus qu'une carte ! Dites'CARTE' :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
		String reponse = scan.nextLine();
		long t2 = System.currentTimeMillis();
		if (t2 - t > 8000 || (!reponse.equals("carte") && !reponse.equals("Carte") && !reponse.equals("CARTE"))) {
			System.out.println("Vous n'avez pas dit 'CARTE' a temps ! CONTRE-CARTE !");
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
			System.out.println("Ce joueur a dit 'CARTE' !\nVous n'avez pas dit 'CONTRECARTE' a temps !\n");
			return false;
		} else {
			System.out.println("Vous dites 'CONTRECARTE'!\n");
			return true;
		}
	}

	@Override
	public void changerFamille() {

		System.out.println("Quel Symbole voulez-vous mettre ?\n1 : TREFLE\n2 : PIQUE\n3 : COEUR\n4 : CARREAU");
		Scanner scan = new Scanner(System.in);
		int rep = scan.nextInt();
		switch (rep) {
		case 1:
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("TREFLE");
			System.out.println("Vous avez choisi comme symbole : TREFLE ! ");
			break;
		case 2:
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("PIQUE");
			System.out.println("Vous avez choisi comme symbole : PIQUE! ");
			break;
		case 3:
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("COEUR");
			System.out.println("Vous avez choisi comme symbole : COEUR ! ");
			break;
		case 4:
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("CARREAU");
			System.out.println("Vous avez choisi comme symbole : CARREAU ! ");
			break;
		default:
			System.out.println("Mauvaise saisie, recommencez :");
			this.changerFamille();
		}
	}
}