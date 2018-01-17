package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ListIterator;

import javax.swing.*;

import modele.Joueur;
import modele.Partie;
/**
 * <b> Interface de la fin d'une partie.</b>
 * <p>Affiche au sein d'une nouvelle fenêtre le classement final de la partie jouée au préalable.</p>
 * @author Charlene et Robin
 * @version 1.0
 */
public class InterfaceFinPartie {
	private JFrame frame;
	private Partie p;

	public InterfaceFinPartie(JFrame frame, Partie p) {
		this.frame = frame;
		this.p = p;
		this.initialize();

	}

	private void initialize() {

		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().removeAll();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(238, 238, 238));

		JTextField txtFinPartie = new JTextField();
		txtFinPartie.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 36));
		txtFinPartie.setHorizontalAlignment(SwingConstants.CENTER);
		txtFinPartie.setEditable(false);
		txtFinPartie.setText("Fin de la partie !");
		txtFinPartie.setBounds(200, 76, 508, 100);
		frame.getContentPane().add(txtFinPartie);
		txtFinPartie.setColumns(10);
		txtFinPartie.setBorder(BorderFactory.createEmptyBorder());

		JPanel panel_Classement = new JPanel();
		GridLayout grid = new GridLayout(0, 1);
		panel_Classement.setLayout(grid);
		panel_Classement.setBounds(200, 209, 508, 279);
		frame.getContentPane().add(panel_Classement);
		JLabel lblClassement = new JLabel("Classement general de la partie : ");
		lblClassement.setHorizontalAlignment(SwingConstants.CENTER);

		Font f = new Font("Serif", Font.BOLD, 24);
		Font f1 = new Font("Serif", Font.BOLD, 20);

		Color c = new Color(0, 0, 0);
		lblClassement.setFont(f);
		lblClassement.setForeground(c);
		lblClassement.setSize(50, 50);
		panel_Classement.add(lblClassement);
		ListIterator<Joueur> iteratorClassement = p.getClassementJoueursPartie().listIterator();
		while (iteratorClassement.hasNext()) {
			Joueur jNext = iteratorClassement.next();
			JLabel lbl = new JLabel(jNext.getName() + " : " + jNext.getScore());
			lbl.setFont(f1);
			lbl.setForeground(c);
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			panel_Classement.add(lbl);
		}
		frame.repaint();
		frame.revalidate();
	}
}
