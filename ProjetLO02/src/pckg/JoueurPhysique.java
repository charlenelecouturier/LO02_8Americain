package pckg;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Object;


public class JoueurPhysique extends Joueur {
	Scanner sc = new Scanner(System.in);
	// ********** Conctructeur ***********
	public JoueurPhysique() {
		super();
		
		System.out.println("Entrez votre nom svp : ");
		this.name = sc.nextLine();
		System.out.println("OK "+ this.name);
	}

	public int choisirCarte() { // doit renvoyer un int et non une Carte car sinon on crée une nouvelle carte, et on ne peut plus utiliser remove(cartePose) dans jouerTour
		
		//System.out.println("Quelle carte voulez-vous poser?");
		System.out.println("Carte du talon : "+ Partie.getPartie().getTalon().getCarteDessus());
		System.out.println("Vos cartes : ");
		int i;
		for(i=1;i<=this.cartes.size();i++) {
			System.out.println(i+" : " +this.cartes.get(i-1));	
		}
		System.out.println("Numéro de la carte choisie ?");
		int numero =sc.nextInt()-1;
		
		
		return numero;
	}

	public void changerFamille() {
		// TODO 
	}

	@Override
	public void DireCarte() {
		// TODO Auto-generated method stub
		
		
		
		System.out.println("Vite ! Dites'CARTE' :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
        String reponse = sc.nextLine();
		long t2 = System.currentTimeMillis();
		if (t2-t>3800 || (!reponse.equals("carte") && !reponse.equals("Carte") && !reponse.equals("CARTE")))
		{
			System.out.println("Vous n'avez pas dit 'CARTE' à temps ! CONTRE-CARTE !");
			this.piocher(1);
		}
		else { 
			System.out.println("Vous dites 'CARTE'! ");
		
                }
        

	}
		


	@Override
	public void DireContreCarte() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void piocher(int nombrePioche) {
		//la boucle tourne autant de fois que le joueur doit piocher
		for (int i=1;i<=nombrePioche;i++) {
			//Ajoute aux cartes du joueur la dernière carte de la pioche
			Carte cartePioche =Partie.getPartie().getPioche().cartes.get(Partie.getPartie().getPioche().cartes.size()-1); // -1 car indice commence à 0
			cartes.add(cartePioche);
			//Retire cette carte de la pioche
			Partie.getPartie().getPioche().cartes.remove(Partie.getPartie().getPioche().cartes.size()-1);
			System.out.println(this.name+ " a pioché " + cartePioche);
		}
		
	}
}
