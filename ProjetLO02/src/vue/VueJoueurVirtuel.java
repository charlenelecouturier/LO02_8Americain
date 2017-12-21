package vue;

import java.awt.*;

import javax.swing.*;

// ===========Concernant les joueurs virtuels ============

// 1. Créer une classe à part qu'on instanciera dans l'interface : VueJoueurVirtuel
public class VueJoueurVirtuel extends JPanel {
	//1.1 À l'Intérieur, mettre une icone de joueur, une carte de dos, le nombre de cartes restantes et le nom du joueur
	//1.1.1 Dos de la carte
	public VueJoueurVirtuel() {
	public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("../../Images/dosCarte.jpg"));
	      g.drawImage(img, 0, 0, this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }  
    }              
    //1.1.2 Icône de joueur
    public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("../../Images/joueur.png"));
	      g.drawImage(img, 0, 0, this);
	      //Pour une image de fond
	      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }                
    //1.1.3 Nombre de cartes restantes

	//1.1.4 Nom du Joueur    
	}
	}
}

// 2. Réserver aux Joueurs Virtuels une place en haut de la fenêtre
// IMPORTANT : Il faut choisir un Layout pour le positionnement dans la fenêtre des composants. 
// Le FlowLayout semble une bonne solution pour l'intérieur de la partie JoueurVirtuel!


// 3. Ajouter dans l'Interface graphique un élément VueJoueurVirtuel pour chaque Joueur Virtuel ajouté à la partie