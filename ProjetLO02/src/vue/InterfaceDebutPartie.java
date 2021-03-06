package vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.*;
import controleur.*;
/**
 * <b> Interface du début de la partie.
 * Permet au joueur de sélectionner facilement les paramètres de la partie avant de lancer celle-ci. </b>
 * Cette fenêtre est composée de boutons qui sont ensuite controlés selon le design pattern Model-View-Controler.
 * @author Charlene et Robin
 * @version 1.0
 */
public class InterfaceDebutPartie {
private JMenuBar menuBar;
private JMenu variantes;
private JMenuItem item1 ;
private JMenuItem item2 ;
private JMenuItem item3 ;
private JMenuItem item4;
private JMenuItem item5;


	// Fen�tre principale et conteneurs de l'interface

	private JFrame frame;

	// Labels et autres presents dans l'Interface

	private JComboBox comboBoxNbJoueursVirtuels;
	private JTextField txtNombreDeJoueurs;
	private JTextField txtVotreNom;
	private JTextField textField;
	private JPanel panelJ2;
	private JPanel panelJ3;
	private JPanel panelJ4;
	private JPanel panelJ5;
	private JPanel panelJ6;
	private JComboBox[] niveauJVirtuel;
	private JButton btnDmarrer;
	private JTextField txtModeDeComptage;
	private JComboBox comboBoxComptage;
	private JComboBox comboBoxVariante;
	
	/**
	 * Create the application.
	 */
	public InterfaceDebutPartie() {

		// on initialise la partie ( nb joueurs et niveaux joueurs etc)
		this.niveauJVirtuel = new JComboBox[5];
		initialize();
		new ControleurNbJVirtuel(comboBoxNbJoueursVirtuels, this.panelJ2, this.panelJ3, this.panelJ4, this.panelJ5,
				this.panelJ6, this.comboBoxNbJoueursVirtuels);
		new ControleurBoutonDemarrer(this.btnDmarrer, this.niveauJVirtuel, this.comboBoxNbJoueursVirtuels,
				comboBoxComptage, this.textField, comboBoxVariante, this.frame);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		frame.setBounds(100, 100, 1000, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("8 Americain_Robin LALLIER_Charlene LECOUTURIER");
		menuBar = new JMenuBar();
		variantes = new JMenu("Variantes");
		menuBar.add(variantes);
		variantes.add(item1 = new JMenuItem("Minimale"));
		// Ajout de ce que doit faire le "?"
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				String mess = "8 : Changer de famille \n 10 : Oblige a rejouer\n";
				JOptionPane.showMessageDialog(null, mess, "Variante Minimale", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		
		variantes.add(item2 = new JMenuItem("Monclar"));
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				String mess = "8 : Changer de famille \n10 : Oblige a rejouer\nVALET : Changer de sens  ou oblige a rejouer s'il n'y a que 2 joueurs\n7 : Bloquer le joueur suivant ou oblige a rejouer s'il n'y a que 2 joueurs\n9 : Fait piocher 1 carte\nAS : Fait piocher 3 cartes au suivant a moins qu'il rajoute un AS ou qu'il contre l'attaque avec un 8";
				JOptionPane.showMessageDialog(null, mess, "Variante de Monclar", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		variantes.add(item3 = new JMenuItem("Variante 4"));
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				String mess = "8 : Changer de famille \n10 : Changer de sens ou oblige a rejouer s'il n'y a que 2 joueurs\nVALET : Bloquer le joueur suivant\n2 : Fait piocher 2 cartes\n2 de PIQUE : Fait piocher 4 cartes\nAS : Permet au joueur de se defausser de toutes ses cartes du meme symbole\nJOKER : Fait piocher 5 cartes au suivant et permet de changer de famille";
				JOptionPane.showMessageDialog(null, mess, "Variante 4", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		variantes.add(item4 = new JMenuItem("Variante 5"));
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				String mess = "8 : Changer de famille \n10 : Oblige a rejouer\n7 : Changer de sens ou oblige a rejouer s'il n'y a que 2 joueurs\nAS : Fait piocher 2 cartes au suivant a moins qu'il rajoute un AS ou qu'il contre l'attaque avec un 8";
				JOptionPane.showMessageDialog(null, mess, "Variante 5", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		variantes.add(item5 = new JMenuItem("Variante 7"));
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				String mess = "8 : Changer de famille \n10 : Oblige a rejouer\n7 : Bloquer le joueur suivant ou oblige a rejouer s'il n'y a que 2 joueurs\nVALET : Changer de sens ou oblige a rejouer s'il n'y a que 2 joueurs\nDAME de TREFLE : Fait piocher 3 cartes\nAS : Fait piocher 2 cartes au suivant a moins qu'il rajoute un AS ";
				JOptionPane.showMessageDialog(null, mess, "Variante 7", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		frame.setJMenuBar(menuBar);
		txtNombreDeJoueurs = new JTextField();
		txtNombreDeJoueurs.setEditable(false);
		txtNombreDeJoueurs.setText("Nombre de joueurs virtuels ?");
		txtNombreDeJoueurs.setBounds(57, 35, 215, 46);
		frame.getContentPane().add(txtNombreDeJoueurs);
		txtNombreDeJoueurs.setColumns(10);

		txtVotreNom = new JTextField();
		txtVotreNom.setEditable(false);
		txtVotreNom.setText("Votre Nom ?");
		txtVotreNom.setBounds(57, 410, 215, 46);
		frame.getContentPane().add(txtVotreNom);
		txtVotreNom.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(607, 426, -114, 22);
		frame.getContentPane().add(textArea);

		textField = new JTextField();
		textField.setBounds(333, 413, 174, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		comboBoxNbJoueursVirtuels = new JComboBox();
		comboBoxNbJoueursVirtuels.setMaximumRowCount(5);
		comboBoxNbJoueursVirtuels.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		comboBoxNbJoueursVirtuels.setBounds(337, 40, 170, 34);
		frame.getContentPane().add(comboBoxNbJoueursVirtuels);
		panelJ2 = new JPanel();
		panelJ2.setBounds(57, 96, 454, 46);
		frame.getContentPane().add(panelJ2);
		panelJ2.setLayout(new GridLayout(0, 1, 0, 0));

		JTextField txtNiveauJoueur2 = new JTextField();
		txtNiveauJoueur2.setEditable(false);
		txtNiveauJoueur2.setText("Niveau joueur 2 ?");
		panelJ2.add(txtNiveauJoueur2);
		txtNiveauJoueur2.setColumns(10);

		JComboBox comboBox_j2 = new JComboBox();
		comboBox_j2.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		comboBox_j2.setMaximumRowCount(2);
		this.niveauJVirtuel[0] = comboBox_j2;
		panelJ2.add(comboBox_j2);
		// panelJ2.setVisible(false);

		panelJ3 = new JPanel();
		panelJ3.setBounds(57, 150, 454, 46);
		frame.getContentPane().add(panelJ3);
		panelJ3.setLayout(new GridLayout(0, 1, 0, 0));

		JTextField txtNiveauJoueur3 = new JTextField();
		txtNiveauJoueur3.setEditable(false);
		txtNiveauJoueur3.setText("Niveau joueur 3 ?");
		panelJ3.add(txtNiveauJoueur3);
		txtNiveauJoueur3.setColumns(10);

		JComboBox comboBox_j3 = new JComboBox();
		comboBox_j3.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		comboBox_j3.setMaximumRowCount(2);
		this.niveauJVirtuel[1] = comboBox_j3;
		panelJ3.add(comboBox_j3);
		panelJ3.setVisible(false);

		panelJ4 = new JPanel();
		panelJ4.setBounds(57, 199, 454, 46);
		frame.getContentPane().add(panelJ4);
		panelJ4.setLayout(new GridLayout(0, 1, 0, 0));
		JTextField txtNiveauJoueur4 = new JTextField();
		txtNiveauJoueur4.setEditable(false);
		txtNiveauJoueur4.setText("Niveau joueur 4 ?");
		panelJ4.add(txtNiveauJoueur4);
		txtNiveauJoueur4.setColumns(10);
		JComboBox comboBox_j4 = new JComboBox();
		comboBox_j4.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		comboBox_j4.setMaximumRowCount(2);
		this.niveauJVirtuel[2] = comboBox_j4;
		panelJ4.add(comboBox_j4);
		panelJ4.setVisible(false);

		panelJ5 = new JPanel();
		panelJ5.setBounds(57, 247, 454, 46);
		frame.getContentPane().add(panelJ5);
		panelJ5.setLayout(new GridLayout(0, 1, 0, 0));
		JTextField txtNiveauJoueur5 = new JTextField();
		txtNiveauJoueur5.setEditable(false);
		txtNiveauJoueur5.setText("Niveau joueur 5 ?");
		panelJ5.add(txtNiveauJoueur5);
		txtNiveauJoueur3.setColumns(10);
		JComboBox comboBox_j5 = new JComboBox();
		comboBox_j5.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		comboBox_j5.setMaximumRowCount(2);
		this.niveauJVirtuel[4] = comboBox_j5;
		panelJ5.add(comboBox_j5);
		panelJ5.setVisible(false);

		panelJ6 = new JPanel();
		panelJ6.setBounds(57, 295, 454, 46);
		frame.getContentPane().add(panelJ6);
		panelJ6.setLayout(new GridLayout(0, 1, 0, 0));
		JTextField txtNiveauJoueur6 = new JTextField();
		txtNiveauJoueur6.setEditable(false);
		txtNiveauJoueur6.setText("Niveau joueur 6 ?");
		panelJ6.add(txtNiveauJoueur6);
		txtNiveauJoueur3.setColumns(10);

		JComboBox comboBox_j6 = new JComboBox();
		comboBox_j6.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		comboBox_j6.setMaximumRowCount(2);
		this.niveauJVirtuel[3] = comboBox_j5;
		panelJ6.add(comboBox_j6);
		panelJ6.setVisible(false);

		btnDmarrer = new JButton("D\u00E9marrer");
		btnDmarrer.setBounds(837, 599, 115, 29);
		frame.getContentPane().add(btnDmarrer);

		txtModeDeComptage = new JTextField();
		txtModeDeComptage.setEditable(false);
		txtModeDeComptage.setText("Mode de comptage des points ?");
		txtModeDeComptage.setBounds(57, 472, 215, 46);
		frame.getContentPane().add(txtModeDeComptage);
		txtModeDeComptage.setColumns(10);

		comboBoxComptage = new JComboBox();
		comboBoxComptage.setModel(new DefaultComboBoxModel(new String[] { "POSITIF", "NEGATIF" }));
		comboBoxComptage.setMaximumRowCount(2);

		comboBoxComptage.setBounds(333, 475, 174, 40);
		frame.getContentPane().add(comboBoxComptage);

		JTextField txtVariante = new JTextField();
		txtVariante.setEditable(false);
		txtVariante.setText("Variante ?");
		txtVariante.setBounds(57, 534, 215, 46);
		frame.getContentPane().add(txtVariante);
		txtVariante.setColumns(10);

		comboBoxVariante = new JComboBox();
		comboBoxVariante.setMaximumRowCount(4);
		comboBoxVariante.setModel(new DefaultComboBoxModel(new String[] { "Minimale", "Monclar", "Variante 4", "Variante 5","Variante 7" }));
		comboBoxVariante.setBounds(333, 531, 174, 40);
		frame.getContentPane().add(comboBoxVariante);
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}
}