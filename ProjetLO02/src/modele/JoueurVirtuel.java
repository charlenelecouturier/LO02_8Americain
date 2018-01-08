package modele;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

import modele.effets.DireContreCarte;
import modele.effets.Effet;
import modele.strategies.*;

/**
 *<b>Classe fille de Joueur, qui représente les joueurs virtuels à l'intérieur de la partie. </b> 
 *<p>
 *Les méthodes de cette classe permettent aux joueurs virtuels de simuler des joueurs réels.
 *Chaque Joueur virtuel se voit attribué une stratégie, simple ou avancée, qui détermine son niveau de difficulté.
 *Ces stratégies sont implémentées via le pattern strategy.
 *</p>
 *@author Charlene et Robin
 *@version 1.0
 *@see Joueur
 *@see Strategie
 */

public class JoueurVirtuel extends Joueur {
	private int strat;
	/**
	 * <b>Constructeur des joueurs virtuels</b>
	 * <p> attribue à chaque joueur son type de stratégie, ainsi qu'un nom. </p>
	 * @throws NiveauJoueurException exception levée lorsque le joueur ne rentre pas un niveau de difficulté acceptable
	 */
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
	/**
	 * <b> Constructeur utilisé par l'interface graphique. </b>
	 * @param niveau la stratégie du joueur.
	 */
	public JoueurVirtuel(int niveau){
		super();
		setName("Joueur " + getNumero());
		this.strat=niveau;
		if (strat == 1) {
			this.strategie = new StrategieDeBase();
		} else {
			this.strategie = new StratAvancee(); 
		}
	}
		
/**
 * <b> Méthode permettant aux joueurs virtuels de choisir la carte qu'ils souhaitent jouer. </b>
 * <p> La méthode construit une liste des cartes jouables par le joueur dans sa main, 
 * puis appelle la méthode <b>choixCarte()</b> associée à sa stratégie, qui choisit une carte pour lui.
 * @return numeroCarte, le numero de la carte choisie.
 */
	public int choisirCarte() {
		
		int numeroCarte;
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		int i;
		System.out.println(this.name + " a " + this.cartes.size()+ " carte(s)");
		System.out.println("Carte du talon : "+ Partie.getPartie().getManche().getTalon().getCarteDessus());
		for (i = 0; i < this.cartes.size(); i++) {
			cartesJouables.add(this.cartes.get(i));
		}
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

	/**
	 * <b> Méthode permettant à un joueur virtuel de dire Carte et contreCarte. </b>
	 */
	public void direCarte() {
		
		boolean contreCarte;
		if (Partie.getPartie().getManche().getJoueur().get(0) instanceof JoueurPhysique) {
			// dire contrecarte en graphique
			
				Effet ditContrecarte = new DireContreCarte(this);
				ditContrecarte.effet();

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
				System.out.println(this.name+ " est contre-carte !");
				this.setContreCarte();
			} else {
				System.out.println(this.name+ " dit carte !");
				this.setaDitcarte();
			}
		}

	}

	/**
	 * Fonction associee a la detection d'exception sur l'entree du niveau du
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

	/**
	 * <b> Méthode permettant à un joueur virtuel de changer de famille, en faisant appel à la méthode
	 *  <i>changerFamille()</i> de sa stratégie. </b>
	 */
	public void changerFamille() {
		this.strategie.changerFamille();
		this.setChanged();
		this.notifyObservers(" a change de famille");
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