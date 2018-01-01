package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ListIterator;
import java.util.Observable;
import javax.swing.*;

import controleur.ControleurBontonSuivant;
import modele.Joueur;
import modele.Partie;

import java.util.Observer;

public class VueFinManche implements Observer {

	private JFrame frame;
	private JButton btnSuivant;
	private JPanel classement;
	private InterfaceManche im;
	
	public VueFinManche(InterfaceManche im){
		this.frame=im.getFrame();
		this.im=im;
		this.initialize();
		new ControleurBontonSuivant(this.btnSuivant, Partie.getPartie(), frame);
	}
	
	private void initialize() {
		this.frame.getContentPane().removeAll();

		frame.setBounds(100, 100, 1000, 700);
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
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
