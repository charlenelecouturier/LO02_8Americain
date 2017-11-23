package pckg;
import java.util.Scanner;


public class JoueurPhysique extends Joueur {
	Scanner sc = new Scanner(System.in);
	// ********** Conctructeur ***********
	public JoueurPhysique() {
		super();
		
		System.out.println("Entrez votre nom svp : ");
		this.name = sc.nextLine();
		System.out.println("OK Joueur1  : "+ this.name);
	}

	public int choisirCarte() { // doit renvoyer un int et non une Carte car sinon on cr�e une nouvelle carte, et on ne peut plus utiliser remove(cartePose) dans jouerTour
		
		
		boolean choix; // variable qui permet la gestion des erreur : si le joueur entre un num�ro trop grand ou trop petit, qui ne correspond � aucun num�ro de carte
		int numero;
		
		
		do{ 
		
			choix= true;
			System.out.println("Carte du talon : "+ Partie.getPartie().getTalon().getCarteDessus());
			System.out.println("Vos cartes : ");
			int i;
			for(i=1;i<=this.cartes.size();i++) {
				System.out.println(i+" : " +this.cartes.get(i-1));	
			}
			System.out.println("Num�ro de la carte choisie ?");
			numero =sc.nextInt();
			if (numero<0|| numero > this.cartes.size()) // verification du num�ro de carte choisi
				{
				choix = false;
				System.out.println("Erreur, num�ro inexistant, faites un nouveau choix");
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
			System.out.println("Vous n'avez pas dit 'CARTE' � temps ! CONTRE-CARTE !");
			this.piocher(1);
		}
		else { 
			System.out.println("Vous dites 'CARTE'! ");
		
                }
        

	}
		


	@Override
	public boolean DireContreCarte() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Vite ce joueur n'a plus qu'une carte ! Dites'CONTRECARTE' :");
		long t = System.currentTimeMillis();// date actuelle en millisecondes
        String reponse = scan.nextLine();
		long t2 = System.currentTimeMillis();
		if (t2-t>8000 || (!reponse.equals("contrecarte") && !reponse.equals("Contrecarte") && !reponse.equals("CONTRECARTE")))
		{
			System.out.println("Ce joueur a dit 'CARTE' !\nVous n'avez pas dit 'CONTRECARTE' � temps !  ");
			return false;
		}
		else { 
			System.out.println("Vous dites 'CONTRECARTE'! ");
			return true;
		
                }

		
	}
	
	
	
}
