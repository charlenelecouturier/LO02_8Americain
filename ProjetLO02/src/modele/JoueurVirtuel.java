package modele;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

import modele.effets.DireContreCarte;
import modele.effets.Effet;
import modele.strategies.*;

//package pckg;

public class JoueurVirtuel extends Joueur {
	private int strat;
	
	public JoueurVirtuel() throws NiveauJoueurException {
		super();
		setName("Joueur " + getNumero());
		
		// =======Gestion des Exceptions sur les entrées clavier========
		//Initialisation des variables servant à la découverte d'une exception
		strat = 0;
		int userInput;
		
		// On vient tester si le niveau du joueur entre est bien 1 ou 2, on capture des erreurs sinon
		do {
			System.out.println("\nNiveau du " + this.name + " ? 1 OU 2");
			Scanner sc = new Scanner(System.in);
			userInput = sc.nextInt();
			try {
			setStrategy(userInput);
			} catch(NiveauJoueurException e) {
	
			}
		}while (strat == 0);
		
		if (strat == 1) {
			this.strategie = new StrategieDeBase();
		} else {
			this.strategie = new StratAvancee(); 
		}
		
	}
	
	public JoueurVirtuel(int niveau){
		super();
		this.typeInterface="graphique";
		setName("Joueur " + getNumero());
		this.strat=niveau;
		if (strat == 1) {
			this.strategie = new StrategieDeBase();
		} else {
			this.strategie = new StratAvancee(); 
		}
		}
		

	public int choisirCarte() {
		int j;
		int numeroCarte;
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		int i;
		System.out.println(this.name + " a " + this.cartes.size()+ " carte(s)");
		System.out.println("Carte du talon : "+ Partie.getPartie().getManche().getTalon().getCarteDessus());
		for (i = 0; i < this.cartes.size(); i++) {
			cartesJouables.add(this.cartes.get(i));
		}
		// cartesJouables = this.getCartes();
		ListIterator<Carte> parcourirCarteJouable = cartesJouables.listIterator();
		while (parcourirCarteJouable.hasNext()) {
			// on verifie a chaque iteration que la carte est compatible
			if (!Partie.getPartie().getManche().getVarianteManche().estCompatible(parcourirCarteJouable.next())) {
				// on retire les cartes pas compatible de la liste.
				parcourirCarteJouable.remove();
			}
		}
		numeroCarte = this.strategie.choixCarte(cartesJouables);

		return numeroCarte;
	}

	@Override
	public void direCarte() {
		
		boolean contreCarte;
		if (Partie.getPartie().getManche().getJoueur().get(0) instanceof JoueurPhysique) {
			// dire contrecarte en graphique
			if (!Partie.getPartie().getManche().getJoueur().get(0).typeInterface.equals("LDC")) {
				Effet ditContrecarte = new DireContreCarte(this);
				ditContrecarte.effet();
			}
			// en ligne de commande
			else {
				contreCarte = ((JoueurPhysique) Partie.getPartie().getManche().getJoueur().get(0)).direContreCarte();
				if (contreCarte) {
					this.setContreCarte();
				} else {
					this.setaDitcarte();
				}
			}
		} else {
			System.out.println("Ce joueur n'a plus qu'une carte !");
			// Un joueur virtuel a une chance sur 4 de dire contre-carte
			Random r = new Random();
			int proba1Sur4 = 1 + r.nextInt(3);
			if (proba1Sur4 == 1) {
				// si le joueur a la place 0 est le joueur qui n'a plus qu'une carte
				if (this.equals(Partie.getPartie().getManche().getJoueur().get(0))) {
					// On choisi un numero de joueur au hasard , sauf celui a la place 0 pour dire
					// carte
					int numJoueurDitContreCarte = (int) (Math.random()
							* (Partie.getPartie().getManche().getJoueur().size() - 2)) + 1;
					System.out.println(Partie.getPartie().getManche().getJoueur().get(numJoueurDitContreCarte).getName()
							+ " dit CONTRE-CARTE");
				} else {// Sinon c'est le joueur a l'emplacement 0 qui dit carte
					System.out
							.println(Partie.getPartie().getManche().getJoueur().get(0).getName() + " dit CONTRE-CARTE");
				}
				contreCarte = true;
			} else { // le joueur a 3 chance sur 4 de dire assez rapidemment 'CARTE'
				System.out.println("Ce joueur dit 'CARTE'");
				contreCarte = false;
			}
			if (contreCarte) {
				this.setContreCarte();
			} else {
				this.setaDitcarte();
			}
		}

	}

	/**
	 * Fonction assocee a la detection d'exception sur l'entree du niveau du
	 * joueur
	 * 
	 * @param userInput
	 *            l'entree du joueur
	 * @throws NiveauJoueurException
	 *             Renvoie le message d'erreur associe à une mauvaise entree.
	 */
	public void setStrategy(int userInput) throws NiveauJoueurException {
		if (userInput == 1 || userInput == 2) {
			strat = userInput;
			System.out.println(strat);
		} else {
			throw new NiveauJoueurException();
		}
	}

	@Override
	public void changerFamille() {
		this.strategie.changerFamille();
	}
	
	/**
	 * @return the strat
	 */
	public int getStrat() {
		return strat;
	}

	/**
	 * @param strat the strat to set
	 */
	public void setStrat(int strat) {
		this.strat = strat;
	}

}