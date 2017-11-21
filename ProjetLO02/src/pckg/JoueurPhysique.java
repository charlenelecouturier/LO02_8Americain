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
		
		
		boolean choix; // variable qui permet la gestion des erreur : si le joueur entre un numéro trop grand ou trop petit, qui ne correspond à aucun numéro de carte
		int numero;
		
		
		do{ 
		
			choix= true;
			System.out.println("Carte du talon : "+ Partie.getPartie().getTalon().getCarteDessus());
			System.out.println("Vos cartes : ");
			int i;
			for(i=1;i<=this.cartes.size();i++) {
				System.out.println(i+" : " +this.cartes.get(i-1));	
			}
			System.out.println("Numéro de la carte choisie ?");
			numero =sc.nextInt();
			if (numero<0|| numero > this.cartes.size()) // verification du néméo de carte choisi
				{
				choix = false;
				System.out.println("Erreur, numéro inexistant, faites un nouveau choix");
			}
		
		} while(!choix);
		
		
		return numero-1;
	}

	public void changerFamille() {
		// TODO 
	}

	@Override
	public void DireCarte() {
		// TODO Auto-generated method stub
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Vite ! Dites'CARTE' :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
        String reponse = scan.nextLine();
		long t2 = System.currentTimeMillis();
		if (t2-t>8000 || (!reponse.equals("carte") && !reponse.equals("Carte") && !reponse.equals("CARTE")))
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
	
	
	
}
