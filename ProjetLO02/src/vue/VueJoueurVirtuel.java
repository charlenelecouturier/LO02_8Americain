package vue;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.BorderFactory;

// ===========Concernant les joueurs virtuels ============

// 1. Créer une classe à part qu'on instanciera dans l'interface : VueJoueurVirtuel
public class VueJoueurVirtuel extends JPanel {
	//1.1 À l'Intérieur, mettre une icone de joueur, une carte de dos, le nombre de cartes restantes et le nom du joueur
	
	public void paintComponent(Graphics g){
		 Image imgJoueur = null;
		 Image imgDosCarte = null;
		try { 
			imgDosCarte = ImageIO.read(new File("Images/dosCarte.jpg"));
			imgJoueur = ImageIO.read(new File("Images/joueur.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}  
		//1.1.2 Icône de joueur
		g.drawImage(imgJoueur, 10, 10, 50, 65,  this);
		//1.1.2 Dos de la carte
		g.drawImage(imgDosCarte, 80, 25, 50, 72, this);
		g.drawString("Adversaire", 80, 20);
		g.drawString("Cartes Restantes : ", 5, 105);
	}
	
	
	public void changerCouleurBordure(VueJoueurVirtuel joueurEnCours) {
		//Si le joueur est en train de jouer , on applique une bordure simple autour de son personnage
		if(!joueurEnCours.getBorder().isBorderOpaque()) {
			joueurEnCours.setBorder(BorderFactory.createLineBorder( Color.BLACK));
		}
		//S'il ne joue plus, on applique une bordure invisible autour
		else {
			joueurEnCours.setBorder(BorderFactory.createEmptyBorder());
		}
	}
	
   
  
  
	}
	

