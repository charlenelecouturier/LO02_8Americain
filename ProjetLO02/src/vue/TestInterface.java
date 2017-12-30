package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.awt.Font;

import javax.swing.*;
import modele.*;
import controleur.*;

public class TestInterface implements Observer, Runnable {

	private ArrayList<VueJoueurVirtuel> vueJVirtuel;

	// Fenêtre principale et conteneurs de l'interface

	private JFrame frame;
	private JPanel panelActionCarte;
	private JPanel panel_JoueurVirtuel;
	private JPanel panel_Main;
	private JPanel panel_Classement;
	private VuePiocheTalon panel_Pioche;
	// Labels et autres presents dans l'Interface
	private JButton btnCarte;
	private JButton btnContreCarte;
	private JLabel lblClassement;
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
	private VueEffetJeu effetsJeu;
	// Objets du modele a observer
	private LinkedList<Joueur> joueur;
	

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { TestInterface window = new
	 * TestInterface(); window.getFrame().setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the application.
	 */
	public TestInterface() {

		// on initialise la partie ( nb joueurs et niveaux joueurs etc)
		this.niveauJVirtuel = new JComboBox[5];
		initialize();
		new ControleurNbJVirtuel(comboBoxNbJoueursVirtuels, this.panelJ2, this.panelJ3, this.panelJ4, this.panelJ5,
				this.panelJ6, this.comboBoxNbJoueursVirtuels);
		new ControleurBoutonDemarrer(this.btnDmarrer, this.niveauJVirtuel, this.comboBoxNbJoueursVirtuels,
				comboBoxComptage, this.textField, comboBoxVariante, this);

	}

	public void run() {

		Partie.getPartie().lancerPartieGraphique();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	// Pas encore opérationnelle
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 700);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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
		comboBoxVariante
				.setModel(new DefaultComboBoxModel(new String[] { "Minimale", "Monclar", "Variante 4", "Variante 5" }));
		comboBoxVariante.setBounds(333, 531, 174, 40);
		frame.getContentPane().add(comboBoxVariante);
	}

	public void initializeGame(Partie p) {

		frame.setVisible(false);
		setFrame(new JFrame());
		Color background = new Color(8, 81, 36);

		frame.getContentPane().setVisible(false);
		frame.getContentPane().removeAll();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		((BorderLayout)frame.getContentPane().getLayout()).setHgap(30);
		((BorderLayout)frame.getContentPane().getLayout()).setVgap(60);
		frame.getContentPane().setBackground(background);
		joueur = Partie.getPartie().getJoueur();
		
		/**
		 * Gestion du conteneur des boutons Carte et Contre-carte. On definit sa
		 * position et les composants qu'il contient.
		 */
		
		JPanel panelWEST = new JPanel();
		panelWEST.setBackground(new Color(8, 81, 36));
		getFrame().getContentPane().add(panelWEST, BorderLayout.WEST);
		panelWEST.setLayout(new BorderLayout());
		panelActionCarte = new JPanel();
		panelActionCarte.setBackground(background);
		panelActionCarte.setLayout(new FlowLayout());
		((FlowLayout)panelActionCarte.getLayout()).setVgap(30);
		((FlowLayout)panelActionCarte.getLayout()).setHgap(30);
		btnCarte = new JButton("Carte");
		panelActionCarte.add(btnCarte);
		btnContreCarte = new JButton("Contre Carte");
		panelActionCarte.add(btnContreCarte);
		new ControleurBoutonContreCarte(Partie.getPartie(), btnContreCarte,this.effetsJeu);
		new ControleurBoutonCarte(Partie.getPartie(), btnCarte,this.effetsJeu);

		panelWEST.add(panelActionCarte,BorderLayout.NORTH);
		this.effetsJeu= new VueEffetJeu();		
		panelWEST.add(this.effetsJeu, BorderLayout.CENTER);
        JScrollPane jp = new JScrollPane(this.effetsJeu);
		panelWEST.add(jp, BorderLayout.CENTER);



		/**
		 * Gestion du conteneur des Joueurs Virtuels. On dï¿½finit sa position et les
		 * composants qu'il contient.
		 */
		panel_JoueurVirtuel = new JPanel();
		getFrame().getContentPane().add(panel_JoueurVirtuel, BorderLayout.NORTH);
		panel_JoueurVirtuel.setBackground(background);
		this.vueJVirtuel = new ArrayList<VueJoueurVirtuel>();

		for (int i = 1; i < joueur.size(); i++) {
			this.vueJVirtuel.add(new VueJoueurVirtuel(Partie.getPartie().getJoueur().get(i).getNumero()));
			panel_JoueurVirtuel.add(vueJVirtuel.get(i - 1));
		}
		/**
		 * Gestion du conteneur du Score. On dï¿½finit sa position et les composants
		 * qu'il contient.
		 */
		panel_Classement = new JPanel();
		GridLayout grid = new GridLayout(this.joueur.size() + 1, 1);
		panel_Classement.setLayout(grid);
		getFrame().getContentPane().add(panel_Classement, BorderLayout.EAST);
		panel_Classement.setBackground(new Color(39, 135, 75));
		lblClassement = new JLabel("Classement general de la partie : ");
		lblClassement.setHorizontalTextPosition(SwingConstants.CENTER );

		Font f = new Font("Serif", Font.BOLD, 24);
		Font f1 = new Font("Serif", Font.BOLD, 20);

		Color c = new Color(0, 0, 0);
		lblClassement.setFont(f);
		lblClassement.setForeground(c);
		lblClassement.setSize(50, 50);
		panel_Classement.add(lblClassement);

		/**
		 * Mise en place des Observers sur les objets de la partie
		 */
		Partie.getPartie().addObserver(this);
		Partie.getPartie().getManche().getTalon().addObserver(this);
		Partie.getPartie().getManche().getPioche().addObserver(this);
		ListIterator<Joueur> it = joueur.listIterator();
		while (it.hasNext()) {
			Joueur jNext = it.next();
			jNext.addObserver(this);
			JLabel lbl = new JLabel(jNext.getName() + " : " + jNext.getScore());
			lbl.setFont(f1);
			lbl.setForeground(c);
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			panel_Classement.add(lbl);
		}
		//VueTourJoueur = new VueTourJoueur;
		/**
		 * Gestion du conteneur de la main du joueur physique. On dï¿½finit sa position
		 * et les composants qu'il contient.
		 */
		panel_Main = new JPanel();
		getFrame().getContentPane().add(panel_Main, BorderLayout.SOUTH);
		GridLayout grid1 = new GridLayout();
		panel_Main.setLayout(grid1);
		panel_Main.setBackground(background);
		// Partie permettant de tester la main
		/*
		 * Carte carte10Pique = new Carte("10", "PIQUE"); VueCarte vueCarte =new
		 * VueCarte(carte10Pique); panel_Main.add(vueCarte);
		 */
		/**
		 * Itï¿½ration qui permet d'afficher les cartes du joueur ï¿½ l'ï¿½cran dans sa
		 * main.
		 */

		// On créé un itérateur qui va parcourir les cartes de notre jeu
		ArrayList<Carte> cartesJoueurPhysique = Partie.getPartie().getJoueur().get(0).getCartes();

		ListIterator<Carte> parcourirCarteJoueur = cartesJoueurPhysique.listIterator();

		while (parcourirCarteJoueur.hasNext()) {
			Carte prochaineCarte = (Carte) parcourirCarteJoueur.next();
			VueCarte vueProchaineCarte = new VueCarte(prochaineCarte);
			new ControleurCarte(Partie.getPartie(), prochaineCarte, vueProchaineCarte);
			panel_Main.add(vueProchaineCarte);
		}



		/**
		 * Gestion du conteneur de la Pioche et du Talon. On dï¿½finit sa position et
		 * les composants qu'il contient.
		 */
		panel_Pioche = new VuePiocheTalon();
		getFrame().getContentPane().add(panel_Pioche, BorderLayout.CENTER);

		getFrame().setVisible(true);
		getFrame().getContentPane().setVisible(true);

		getFrame().pack();

		Thread t = new Thread(this);
		t.start();
	}

	public void update(Observable instanceObservable, Object arg1) {
		if (instanceObservable instanceof Joueur) {
			this.panel_Pioche.update(instanceObservable, arg1);

			if (arg1 != null) {
				if (arg1.equals("CARTE ! ") || arg1.equals("CONTRE-CARTE ! ") || arg1.equals("a pioche")) {
					this.effetsJeu.update(instanceObservable, arg1);
				} 
			}
			if (instanceObservable instanceof JoueurVirtuel) {
				int num = ((Joueur) instanceObservable).getNumero();
				this.vueJVirtuel.get(num - 2).update(instanceObservable, arg1);
				frame.repaint();
				frame.revalidate();
			} else if (instanceObservable instanceof JoueurPhysique) {

				/**
				 * Redefinir les cartes visibles en main en fonction du tour qu'a jouÃ© le
				 * joueur.
				 */
				panel_Main.removeAll();
				ArrayList<Carte> cartesJoueurPhysique = Partie.getPartie().getJoueur().get(0).getCartes();
				ListIterator<Carte> parcourirCarteJoueur = cartesJoueurPhysique.listIterator();

				while (parcourirCarteJoueur.hasNext()) {
					Carte prochaineCarte = (Carte) parcourirCarteJoueur.next();
					VueCarte vueProchaineCarte = new VueCarte(prochaineCarte);
					ControleurCarte controleurProchaineCarte = new ControleurCarte(Partie.getPartie(), prochaineCarte,
							vueProchaineCarte);
					panel_Main.add(vueProchaineCarte);
					frame.repaint();
					frame.revalidate();
				}
			}

		}
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		return this.frame;
	}
}