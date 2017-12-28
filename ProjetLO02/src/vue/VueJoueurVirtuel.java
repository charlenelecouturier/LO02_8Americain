package vue;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import modele.Partie;
import java.awt.FlowLayout;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.BorderFactory;
import modele.*;


// ===========Concernant les joueurs virtuels ============

// 1. Creer une classe a part qu'on instanciera dans l'interface : VueJoueurVirtuel
public class VueJoueurVirtuel extends JPanel implements Observer{
	//1.1 a l'Interieur, mettre une icone de joueur, une carte de dos, le nombre de cartes restantes et le nom du joueur
	protected static Color FOND_JOUEURS = new Color(112, 181, 134);
	
	protected JLabel iconeJoueur;
	protected JLabel niveauJoueur;
	protected JLabel dosCarte;
	protected JLabel nbCartes;
	protected JLabel lblNomJoueur;
	protected int numJoueur;
	
	
	public VueJoueurVirtuel(int numero) {
		this.setLayout(new GridLayout(0,2));
		this.setMaximumSize(new Dimension(160, 140));
		initialize();
		this.numJoueur=numero;
		Joueur j = Partie.getPartie().getJoueur().get(numJoueur -1);
		if(((JoueurVirtuel)j).getStrat()==1) {
			this.niveauJoueur= new JLabel("Niveau : Faible");
		}
		else {
			this.niveauJoueur= new JLabel("Niveau : Eleve");

		}
		
		lblNomJoueur = new JLabel(j.getName()+ " ");
		nbCartes = new JLabel("Cartes restantes: 8  ");
		this.add(iconeJoueur);
		this.add(dosCarte);
		this.add(lblNomJoueur);
		this.add(nbCartes);
		this.add(this.niveauJoueur);
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.nbCartes.setText("Cartes restantes: "+ Partie.getPartie().getJoueur().get(this.numJoueur-1).getCartes().size());
	}
	
   
  
  
	}
	

