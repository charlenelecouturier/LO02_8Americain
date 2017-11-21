package pckg;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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
		System.out.println("Vite : dire 'CARTE'");
		
		long temps = 5000;                      // délai avant de répéter la tache : 5000 = 5 seconde
        long startTime = 0;                    // délai avant la mise en route (0 demarre immediatement)
        Timer timer = new Timer();             // création du timer
        TimerTask tache = new TimerTask() {     // création et spécification de la tache à effectuer
            @Override
                public void run() {
                       String reponse = sc.nextLine();// ici se qui doit être effectué
                }
        };
        timer.scheduleAtFixedRate(tache,startTime,temps);
		
		
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
