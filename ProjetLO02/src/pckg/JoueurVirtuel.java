/* pour Strategy : 
 * 
 * faire en sorte que changerFamille() soit coh�rent avec les cartes que le joueurVirtuel a en main
 * ex : il choisit 'coeur' si il a le + de coeur dans son jeu
 * 
 * dans choisirCarte : 
 * faire en sorte de jouer un 10 de pique ,par exemple, ( qui Oblige a rejouer) si il possede au moins 
 * un autre pique dans son jeu ou sil ne peut jouer que ca
 * 
 * */

package pckg;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

//package pckg;

public class JoueurVirtuel extends Joueur {

	// *********Constructeur**********
	public JoueurVirtuel() {
		super();
		setName("Joueur" + getNumero());
		System.out.println("\nNiveau du " + this.name + " ? 1 OU 2");
		Scanner sc = new Scanner(System.in);
		int strat = sc.nextInt();
		if (strat == 1) {
			this.strategie = new StrategieDeBase();
		} else {
			this.strategie = new StratAvancee(); // par defaut si l'utilisateur se trompe
		}

	}

	@Override
	public void jouerTour() {
		int tour;
		System.out.println("effet : " + this.EffetVariante);
		boolean gagne = false;
		// Le joueur pose une carte
		this.poserCarte();
		this.EffetVariante = "Aucun";
		// On test si le joueur a gagn�
		gagne = this.gagnePartie();
		// On cherche le tour du joueur suivant
		tour = Partie.getPartie().getTourJoueur();
		// On regarde le sens de la partie
		if (Partie.getPartie().getSens() == 1) {

			if (!gagne) {
				tour++;
			}
			// Si on depasse le num�ro du dernier joueur, on revient au joueur 1 ( joueur
			// physique)
			if (tour > Partie.getPartie().getNbJoueursEnCours()) {
				tour = 1;
			}
		} else {// sens =-1
				// si on trouve un num�ro n�gatif, on revient au tour du dernier joueur (
				// joueur ayant le dernier num�ro)
			tour--;
			if (tour <= 0) {
				tour = Partie.getPartie().getNbJoueursEnCours();
			}
		}
		Partie.getPartie().setTourJoueur(tour);

	}

	@Override
	public void poserCarte() {
		// 1. on verifie si le joueur peut jouer avec les cartes qu'il a dans la main
		if (Partie.getPartie().getVariantePartie().estPossibleDeJouer(this.cartes)) {
			// 2.1. Le joueur choisit la carte qu'il desire poser sur le talon.
			int numeroCarte = this.choisirCarte();
			Carte cartePose = this.cartes.get(numeroCarte);
			// 4.1 Le joueur pose la carte choisie sur le talon.
			Partie.getPartie().getTalon().getCartes().add(cartePose);

			// on change la carte du dessus du Talon qui est un simple attribut de type
			// Carte
			Partie.getPartie().getTalon().getCarteDessus().setSymbole(cartePose.getSymbole());
			Partie.getPartie().getTalon().getCarteDessus().setValeur(cartePose.getValeur());
			System.out.println(this.getName() + " joue " + cartePose);
			// 5.1 Le joueur perd la carte qu'il a pos�e de sa main
			cartes.remove(cartePose);
			// s'il n'a plus qu'une carte il est possible qu'un joueur dise contre carte
			if (this.cartes.size() == 1) {
				boolean contrecarte = Partie.getPartie().getJoueur().get(0).DireContreCarte();
				if (contrecarte) {
					this.piocher(1);
				}
			}

			// 6.2 On regarde si c'est une carte Speciale
			String effet = cartePose.getEffet();
			if (!effet.equals("Aucun")) {
				cartePose.appliquerEffet();
			}

		}

		// 2.2. Le joueur ne peut jouer aucune carte, donc il pioche.
		// ( a condition que la variante n'ai pas entrain� un effet comme 3piocher 2
		// cartes")
		// Dans ce cas, le fait de piocher 2 cartes est g�r� par la variante
		else {
			if (this.EffetVariante.equals("Aucun")) {
				System.out.println(this.getName() + " ne peut pas jouer !");
				this.piocher(1);
			}

		}
	}

	/** choisit la premi�re carte compatible de son jeu */
	public int choisirCarte() {
		int j;
		System.out.println("Cartes de " + this.name + " : ");
		for (j = 1; j <= this.cartes.size(); j++) {
			System.out.println(j + " : " + this.cartes.get(j - 1));
		}
		int numeroCarte;
		// On créé une liste des cartes jouables par le joueur en fonction du talon
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		int i;
		for (i = 0; i < this.cartes.size(); i++) {
			cartesJouables.add(this.cartes.get(i));
		}
		// cartesJouables = this.getCartes();
		ListIterator<Carte> parcourirCarteJouable = cartesJouables.listIterator();
		while (parcourirCarteJouable.hasNext()) {
			// on vérifie à chaque itération que la carte est compatible
			if (!Partie.getPartie().getVariantePartie().estCompatible(parcourirCarteJouable.next())) {
				// on retire les cartes pas compatible de la liste.
				parcourirCarteJouable.remove();
			}
		}
		//

		numeroCarte = this.strategie.choixCarte(cartesJouables);

		/*
		 * //1 . Le Joueur virtuel regarde quel est le prochain joueur à jouer son tour
		 * int tour= Partie.getPartie().getTourJoueur(); //on regarde le sens de la
		 * partie if (Partie.getPartie().getSens()==1) { // Si on depasse le num�ro du
		 * dernier joueur, on revient au joueur 1 ( joueur physique) if( tour >
		 * Partie.getPartie().getNbJoueursEnCours()) { tour=1; } }
		 * 
		 * else {// sens =-1 // si on trouve un num�ro n�gatif, on revient au tour
		 * du dernier joueur ( joueur ayant le dernier num�ro) tour--; if (tour<0) {
		 * tour=Partie.getPartie().getNbJoueursEnCours(); } }
		 * 
		 * //2. on regarde le nombre de cartes du prochain joueur int nbCartesProchain =
		 * Partie.getPartie().getJoueur().get(tour-1).getCartes().size();
		 * 
		 * //3. On compare le nombre de cartes du prochain joueur avec le sien if
		 * (nbCartesProchain <= this.getCartes().size()) { //3.1 Si le prochain joueur a
		 * plus de cartes que le Joueur virtuel, on joue la strat défensive Strategie
		 * stratDef = new StratDefensive(); numeroCarte = stratDef.strategie(); } //3.2
		 * Si le prochain joueur a moins de cartes que le Joueur virtuel, on joue la
		 * strat offensive else { Strategie stratOff = new StratOffensive(); numeroCarte
		 * = stratOff.strategie(); }
		 */

		return numeroCarte;
	}

	@Override
	public void DireCarte() {
		// TODO Auto-generated method stub

	}

	/**
	 * cette methode est appel�e si le joueur physique n'est plus dans le jeu (
	 * s'il a gagn�) un joueur virtuel peut alors avoir une chance sur 4 de dire
	 * contre carte
	 */

	@Override
	public boolean DireContreCarte() {
		// TODO Auto-generated method stub
		System.out.println("Ce joueur n'a plus qu'une carte !");
		// Un joueur virtuel a une chance sur 4 de dire contre-carte
		Random r = new Random();
		int proba1Sur4 = 1 + r.nextInt(3);

		if (proba1Sur4 == 1) {

			// si le joueur a la place 0 est le joueur qui n'a plus qu'une carte
			if (Partie.getPartie().getJoueur().get(Partie.getPartie().getTourJoueur() - 1)
					.equals(Partie.getPartie().getJoueur().get(0))) {
				// On choisi un num�ro de joueur au hasard , sauf celui a la place 0 pour dire
				// carte
				int numJoueurDitContreCarte = 1 + r.nextInt(Partie.getPartie().getJoueur().size() - 2);
				System.out.println(
						Partie.getPartie().getJoueur().get(numJoueurDitContreCarte).getName() + " dit CONTRE-CARTE");
				return true;
			}
			// Sinon c'est le joueur � l'emplacement 0 qui dit carte
			else {
				System.out.println(this.name + " dit CONTRE-CARTE");
				return true;
			}
		}
		// le joueur a 3 chance sur 4 de dire assez rapidemment 'CARTE'
		else {
			System.out.println("Ce joueur dit 'CARTE'");
			return false;
		}

	}

	@Override
	public void changerFamille() {
		this.strategie.changerFamille();
	}

}