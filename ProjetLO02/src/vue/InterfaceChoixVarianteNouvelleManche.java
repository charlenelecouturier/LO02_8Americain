package vue;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controleur.ControleurBoutonSuivant2;
import modele.Partie;

/**
 * <b> Interface de choix de la Variante .</b>
 * <p>Apparait une fois une manche terminée pour permettre au joueur de sélectionner facilement sa variante 
 * à la manche suivante. \n
 * Cette fenêtre est composée d'un bouton pour lancer la manche suivante et d'une JComboBox permettant de sélectionner
 * la variante voulue. qui sont ensuite controlés selon le design pattern Model-View-Controler.</p>
 * @author Charlene et Robin
 * @version 1.0
 */
public class InterfaceChoixVarianteNouvelleManche {
	private JFrame frame;
	private JButton btnSuivant;
	private JComboBox choixVariante;
	

	public InterfaceChoixVarianteNouvelleManche(JFrame frame) {
		this.frame = frame;
		this.initialize();	
		new ControleurBoutonSuivant2(this.frame, this.choixVariante, this.btnSuivant, Partie.getPartie());

	}

	private void initialize() {

		this.frame.getContentPane().removeAll();

		frame.setBounds(100, 100, 1000, 720);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(238, 238, 238));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextField txtVariante = new JTextField();
		txtVariante.setHorizontalAlignment(SwingConstants.CENTER);
		txtVariante.setFont(new Font("SansSerif", Font.BOLD, 26));
		txtVariante.setEditable(false);
		txtVariante.setText("Variante ?");
		txtVariante.setBounds(106, 251, 451, 84);
		frame.getContentPane().add(txtVariante);
		txtVariante.setColumns(10);
		txtVariante.setBorder(BorderFactory.createEmptyBorder());

		choixVariante = new JComboBox();
		choixVariante.setFont(new Font("SansSerif", Font.BOLD, 26));
		choixVariante.setMaximumRowCount(4);
		choixVariante.setModel(new DefaultComboBoxModel(new String[] { "Minimale", "Monclar", "Variante 4", "Variante 5","Variante 7" }));
		choixVariante.setBounds(607, 253, 220, 81);
		frame.getContentPane().add(choixVariante);

		btnSuivant = new JButton("Suivant");
		btnSuivant.setBounds(837, 599, 115, 29);
		frame.getContentPane().add(btnSuivant);
		
		JTextField txtNouvelleManche = new JTextField();
		txtNouvelleManche.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 36));
		txtNouvelleManche.setHorizontalAlignment(SwingConstants.CENTER);
		txtNouvelleManche.setEditable(false);
		txtNouvelleManche.setText("Nouvelle manche !");
		txtNouvelleManche.setBorder(BorderFactory.createEmptyBorder());
		txtNouvelleManche.setBounds(200, 76, 508, 100);
		frame.getContentPane().add(txtNouvelleManche);
		txtNouvelleManche.setColumns(10);

		frame.repaint();
		frame.revalidate();
		frame.setVisible(true);
		frame.getContentPane().setVisible(true);
	}
}