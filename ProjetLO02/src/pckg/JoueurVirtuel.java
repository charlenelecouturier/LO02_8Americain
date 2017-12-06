/* pour Strategy : 
 * 
 * faire en sorte que changerFamille() soit cohï¿½rent avec les cartes que le joueurVirtuel a en main
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



	/** choisit la premiï¿½re carte compatible de son jeu */
	public int choisirCarte() {
		int j;
		System.out.println("Cartes de " + this.name + " : ");
		for (j = 1; j <= this.cartes.size(); j++) {
			System.out.println(j + " : " + this.cartes.get(j - 1));
		}
		int numeroCarte;
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		int i;
		for (i = 0; i < this.cartes.size(); i++) {
			cartesJouables.add(this.cartes.get(i));
		}
		// cartesJouables = this.getCartes();
		ListIterator<Carte> parcourirCarteJouable = cartesJouables.listIterator();
		while (parcourirCarteJouable.hasNext()) {
			// on vrifie à chaque iteration que la carte est compatible
			if (!Partie.getPartie().getVariantePartie().estCompatible(parcourirCarteJouable.next())) {
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
		if (Partie.getPartie().getJoueur().get(0) instanceof JoueurPhysique) {
			contreCarte = ((JoueurPhysique) Partie.getPartie().getJoueur().get(0)).direContreCarte();
		}

		else {

			System.out.println("Ce joueur n'a plus qu'une carte !");
			// Un joueur virtuel a une chance sur 4 de dire contre-carte
			Random r = new Random();
			int proba1Sur4 = 1 + r.nextInt(3);

			if (proba1Sur4 == 1) {

				// si le joueur a la place 0 est le joueur qui n'a plus qu'une carte
				if (this.equals(Partie.getPartie().getJoueur().get(0))) {
					// On choisi un numero de joueur au hasard , sauf celui a la place 0 pour dire carte
					int numJoueurDitContreCarte =(int)(Math.random() * (Partie.getPartie().getJoueur().size() - 2)) + 1;
					System.out.println(Partie.getPartie().getJoueur().get(numJoueurDitContreCarte).getName()
							+ " dit CONTRE-CARTE");
				}else {// Sinon c'est le joueur à l'emplacement 0 qui dit carte
					System.out.println(Partie.getPartie().getJoueur().get(0).getName() + " dit CONTRE-CARTE");
				}
				contreCarte = true;
			} else { // le joueur a 3 chance sur 4 de dire assez rapidemment 'CARTE'
				System.out.println("Ce joueur dit 'CARTE'");
				contreCarte = false;
			}

		}
		if (contreCarte) {
			this.piocher(1);
		}
	}

	@Override
	public void changerFamille() {
		this.strategie.changerFamille();
	}
}