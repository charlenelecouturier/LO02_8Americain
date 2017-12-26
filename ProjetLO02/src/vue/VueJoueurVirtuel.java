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
	protected static Color FOND_JOUEURS = new Color(112, 181, 134);
	
	JLabel iconeJoueur;
	JLabel dosCarte;
	JLabel nbCartes;
	JLabel lblNomJoueur;
	
	
	public VueJoueurVirtuel(String nomJoueur) {
		this.setMaximumSize(new Dimension(160, 140));
		initialize();
		
		lblNomJoueur = new JLabel(nomJoueur);
		nbCartes = new JLabel("Cartes restantes:");
		this.add(iconeJoueur);
		this.add(dosCarte);
		this.add(nbCartes);
		this.add(lblNomJoueur);
		this.setBackground(FOND_JOUEURS);
		
	}
	
	public void initialize() {
		iconeJoueur = new JLabel();
		dosCarte = new JLabel();
		iconeJoueur.setIcon(new ImageIcon("Images/joueur.png"));
		dosCarte.setIcon(new ImageIcon("Images/dosCarte.jpg"));
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
	

