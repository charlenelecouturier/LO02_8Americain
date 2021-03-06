package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ListIterator;
import javax.swing.*;
import controleur.ControleurBoutonSuivant;
import modele.Joueur;
import modele.Partie;
/**
 * <b> Fenêtre ouverte lors de la fin d'une manche. Permet de consulter les scores de chaque joueur.</b>
 * <p> Comporte du texte pour les classements des joueurs et un bouton pour passer à l'écran de sélection de la variante.</p>
 * @author Charlene et Robin
 * @version 1.0
 * @see InterfaceChoixVarianteNouvelleManche
 */
public class InterfaceFinManche {

	private JFrame frame;
	private JButton btnSuivant;
	private JPanel classement;
	
	public InterfaceFinManche(JFrame frame){
		this.frame=frame;
		this.initialize();
		new ControleurBoutonSuivant(this.btnSuivant, Partie.getPartie(), frame);
	}
	
	private void initialize() {
		this.frame.getContentPane().removeAll();

		frame.setBounds(100, 100, 1000, 720);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(255,255,255));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.classement=new JPanel();
		classement = new JPanel();
		classement.setBorder(BorderFactory.createLineBorder( Color.BLACK));	
		classement.setBackground(new Color(0,0,0));
		classement.setBounds(150, 53, 670, 521);
		GridLayout grid = new GridLayout(0, 1);
		classement.setLayout(grid);
		Font f = new Font("Serif",Font.BOLD, 30);
		Font f1 = new Font("Serif", Font.BOLD|Font.ITALIC, 24);

		JLabel lblClassement = new JLabel("Manche terminee !");
		lblClassement.setForeground(new Color(255,255,255));
		lblClassement.setHorizontalAlignment(SwingConstants.CENTER );
		lblClassement.setFont(f);
		classement.add(lblClassement);	
		ListIterator<Joueur> it = Partie.getPartie().getManche().getClassementJoueurs().listIterator();
		while (it.hasNext()) {
			Joueur jNext = it.next();
			JLabel lbl = new JLabel(jNext.getName() + " marque : " + jNext.getScoreManche() +" points");
			lbl.setFont(f1);
			lbl.setForeground(new Color(255,255,255));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			classement.add(lbl);
		}
		
		btnSuivant = new JButton("Suivant");
		btnSuivant.setBounds(837, 599, 115, 29);
		frame.getContentPane().add(btnSuivant);
		
		frame.getContentPane().add(classement);
		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);
		frame.getContentPane().setVisible(true);
	}
}
