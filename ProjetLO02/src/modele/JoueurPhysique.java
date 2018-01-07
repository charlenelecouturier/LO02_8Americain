package modele;

import modele.effets.DireCarte;
import modele.effets.Effet;
import modele.effets.Piocher5Cartes;
import modele.variantes.*;
import java.util.Scanner;

/**
 *<b>Classe fille de Joueur, qui représente le joueur réel à l'intérieur de la partie. </b> 
 *<p>
 *Les méthodes de cette classe permette au joueur d'interagir avec le moteur 
 *du jeu au même titre que ses adversaires virtuels.
 *</p>
 *@author Charlene et Robin
 *@version 1.0
 *@see Joueur
 */
public class JoueurPhysique extends Joueur {
	private boolean aChangeDeFamille;
	private Scanner sc = new Scanner(System.in);
	private boolean attenteVue = true;
/**
 * Constructeur utilisé pour l'interface en ligne de commande.
 */
	public JoueurPhysique() {
		super();
		System.out.println("Entrez votre nom : ");
		this.name = sc.nextLine();
		this.setaChangeDeFamille(false);
		System.out.println("OK Joueur1  : " + this.name);
	}
/**
 * Constructeur utilisé pour l'interface graphique.
 * @param nom le nom du joueur physique.
 */
	public JoueurPhysique(String nom) {
		super();
		this.typeInterface = "graphique";
		this.name = nom;
	}
/**
 * <b>redéfinition de la méthode jouerTour() de Joueur.</b>
 * @see Joueur
 */
	public void jouerTour() {
		
		if (!Partie.getPartie().getManche().getVarianteManche().estPossibleDeJouer(this.cartes)
				&& !this.getEffetVariante().equals("Changer Famille")) {
			this.EffetVariante = "Aucun";
			this.changerTour();
			this.setChanged();
			this.notifyObservers("a fini");
		} else {
			if (!this.getEffetVariante().equals("Changer Famille")) {

				this.setChanged();
				this.notifyObservers("joue");
			}
			while (attenteVue) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.setAttenteVue(true);
			this.setChanged();
			this.notifyObservers("a fini");
		}
	}
	/**
	 * <b>redéfinition de la méthode jouerTour() de Joueur. (version graphique)</b>
	 * @see Joueur
	 */
	public void jouerTourGraphique(int indexCarteChoisie) {
		
		this.aDitcarte = false;
		this.contreCarte = false;
		this.setaChangeDeFamille(false);
		this.poserCarteGraphique(indexCarteChoisie);

		// effets pour lesquels on ne doit pas changer le tour
		if (!this.EffetVariante.equals("doit rejouer") && !this.EffetVariante.equals("JouerMemeCouleur")
				&& !this.EffetVariante.equals("Changer Famille")) {
			// on regarde si le fait d'avoir pose une carte permet au joueur de gagner la
			// manche
			this.EffetVariante = "Aucun";
			this.changerTour();
		}
		if (this.EffetVariante.equals("doit rejouer")) {
			this.EffetVariante = "Aucun";
		}
		setAttenteVue(false);
	}
	
	
	/**
	 * <b> Méthode permettant au joueur de choisir la carte qu'il souhaite poser sur le talon </b>
	 * <p>
	 * Après lui avoir demandé de choisir la carte via un scanner, 
	 * le jeu vérifie que cette carte soit bien compatible avec le talon.
	 */
	public void choisirCarte() { 
		boolean choix;
		int numero;
		sc=new Scanner(System.in);
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

			if (numero < 0 || numero > this.cartes.size()
					|| !Partie.getPartie().getManche().getVarianteManche().estCompatible(this.cartes.get(numero - 1))
							&& !this.EffetVariante.equals("Changer Famille")) { // verification du num�ro de carte
																				// choisi
				choix = false;
				System.out.println("Impossible, faites un nouveau choix");
			}

		} while (!choix);
	
		if (this.EffetVariante.equals("Changer Famille")) {
			this.setChanged();
			this.notifyObservers("a choisi famille");

			this.setFamille(numero);
		} else {
			if (this.cartes.get(numero - 1).getValeur().equals("1")) {
				Variante.nombreAs++;
			} else if (this.cartes.get(numero - 1).getValeur().equals("8")) {
				Variante.nombreAs = 0;
			}
			jouerTourGraphique(numero - 1);
			this.setChanged();
			this.notifyObservers("a choisi");
		}
	}
	
	public boolean isAttenteVue() {
		return attenteVue;
	}

	public void setAttenteVue(boolean attenteVue) {
		this.attenteVue = attenteVue;
	}

	public void afficherCartes() {
		for (int i = 0; i < cartes.size(); i++) {
			System.out.println(i + 1 + " : " + cartes.get(i));
		}
		System.out.println("Numero de la carte choisie ?");
	}
/**
 * <b> Permet de poser la carte sur le talon dans l'interface graphique 
 * (reprend le principe de <i>poserCarte()</i> de Joueur)
 * 
 * @param numeroCarte le numero de la carte à poser de la main vers le talon.
 * @see Joueur#poserCarte()
 */
	public void poserCarteGraphique(int numeroCarte) {

		if (Partie.getPartie().getManche().getVarianteManche().estPossibleDeJouer(this.cartes)) {

			Carte cartePose = this.cartes.get(numeroCarte);
			Partie.getPartie().getManche().getTalon().getCartes().add(cartePose);
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
			Partie.getPartie().getManche().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
			System.out.println("Vous jouez " + cartePose);
			cartes.remove(cartePose);
			if (cartePose.getValeur().equals("1")) {
				Variante.nombreAs++;
			} else if (cartePose.getValeur().equals("8")) {
				Variante.nombreAs = 0;
			}
			if (this.cartes.size() == 1) {
				this.direCarteGraphique();
			}
			// On notifie l'interface que la carte a ete retiree de la main du joueur
			this.setChanged();
			this.notifyObservers();
			String effet = cartePose.getEffet();
			if (!effet.equals("Aucun") && !effet.equals("Defausser tous les m�mes symboles")
					&& !this.EffetVariante.equals("JouerMemeCouleur")
					&& !effet.equals("Changer Famille + Piocher 5 cartes")&& !effet.equals("Changer Famille")) {
				cartePose.appliquerEffet();
			} else if (effet.equals("Changer Famille") || effet.equals("Changer Famille + Piocher 5 cartes")) {
				if (effet.equals("Changer Famille + Piocher 5 cartes")) {
					Effet piocher5 = new Piocher5Cartes();
					piocher5.effet();
				}
				this.EffetVariante = "Changer Famille";
				this.setChanged();
				this.notifyObservers("a choisi");
				setChanged();
				this.notifyObservers("Changer Famille");

			} else if (effet.equals("Defausser tous les m�mes symboles")) {
				this.EffetVariante = "JouerMemeCouleur";
				this.setChanged();
				this.notifyObservers("doit se defausser de tous les m�mes symboles");
			}
		}		
		
		this.setChanged();
		this.notifyObservers("a fini");
	}

	
	/**
	 * <b>Méthode permettant au joueur physique de dire "Carte!" lorsqu'il n'a plus qu'une carte. (version graphique)</b>
	 */
	public void direCarteGraphique() {
		Effet ditCarte =new DireCarte(this);
		ditCarte.effet();

	}
	
	/**
	 * <b>Méthode permettant au joueur physique de dire "Carte!" lorsqu'il n'a plus qu'une carte.</b>
	 */
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
	/**
	 * <b>Méthode permettant au joueur physique de dire "Contre Carte!" lorsqu'un adversaire n'a plus qu'une carte.</b>
	 */
	public boolean direContreCarte() {

		Scanner scan = new Scanner(System.in);
		System.out.println("\nVite ce joueur n'a plus qu'une carte ! Dites'CONTRE CARTE' en appuyant sur 1 :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
		int reponse = scan.nextInt();
		long t2 = System.currentTimeMillis();
		if (t2 - t > 8000 || (reponse!=1)) {
			System.out.println("Ce joueur a dit 'CARTE' !\nVous n'avez pas dit 'CONTRE CARTE' a temps !\n");
			return false;
		} else {
			System.out.println("Vous dites 'CONTRECARTE'!\n");
			return true;
		}
	}

	/**
	 * <b> Méthode permettant au joueur de changer de famille lorsque la carte jouée possède cet effet. </b>
	 */
	public void changerFamille() {
		System.out.println("on change de famille");
		System.out.println("Quel Symbole voulez-vous mettre ?\n1 : TREFLE\n2 : PIQUE\n3 : COEUR\n4 : CARREAU");
		int rep = sc.nextInt();
		if (this.EffetVariante.equals("Changer Famille")) {
			setFamille(rep);

		} else { // le scanner attendais le numero d'une carte
			if (this.cartes.get(rep - 1).getValeur().equals("1")) {
				Variante.nombreAs++;
			} else if (this.cartes.get(rep - 1).getValeur().equals("8")) {
				Variante.nombreAs = 0;
			}
			jouerTourGraphique(rep - 1);
			this.setChanged();
			this.notifyObservers("a choisi");
		}
	}

	/**
	 * <b>Méthode permettant de changer la famille lorsque le joueur pose une carte possédant cet effet.
	 * @param rep entier qui indique la famille choisie
	 */
	public void setFamille(int rep) {
		switch (rep) {
		case 1:
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("TREFLE");
			System.out.println("Vous avez choisi comme symbole : TREFLE ! ");
			this.setaChangeDeFamille();

			break;
		case 2:
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("PIQUE");
			System.out.println("Vous avez choisi comme symbole : PIQUE! ");
			this.setaChangeDeFamille();

			break;
		case 3:
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("COEUR");
			System.out.println("Vous avez choisi comme symbole : COEUR ! ");
			this.setaChangeDeFamille();

			break;
		case 4:
			Partie.getPartie().getManche().getTalon().getCarteDessus().setSymbole("CARREAU");
			System.out.println("Vous avez choisi comme symbole : CARREAU ! ");
			this.setaChangeDeFamille();

			break;
		default:
			System.out.println("Mauvaise saisie, recommencez la saisie en ligne de commande :");
			this.changerFamille();
		}	
	}
	/**
	 * @param aChangeDeFamille the aChangeDeFamille to set
	 */
	public void setaChangeDeFamille() {
		this.aChangeDeFamille = true;
		this.EffetVariante="Aucun";
		this.setChanged();
		this.notifyObservers(" a change de famille");
		this.changerTour();
		this.setAttenteVue(false);
	}
	
	public void changerTour() {
		boolean gagne = false;
		gagne = this.gagnePartie();
		int tour = Partie.getPartie().getManche().getTourJoueur();
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
	public boolean isaChangeDeFamille() {
		return aChangeDeFamille;
	}
	public void setaChangeDeFamille(boolean aChangeDeFamille) {
		this.aChangeDeFamille = aChangeDeFamille;
	}
	
}