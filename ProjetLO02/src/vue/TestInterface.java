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
	// Labels et autres prï¿½sents dans l'Interface
	private JButton btnCarte;
	private JButton btnContreCarte;
	private JLabel lblClassement;
	private JComboBox comboBoxNbJoueursVirtuels;
	private JTextField txtNombreDeJoueurs;
	private JTextField txtVotreNom;
	private JTextField textField;

	// Objets du modï¿½le ï¿½ observer
	private LinkedList<Joueur> joueur;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestInterface window = new TestInterface();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public TestInterface() {
		//Thread t = new Thread(this);
		//t.start();
		// on initialise la partie ( nb joueurs et niveaux joueurs  etc)
		joueur = Partie.getPartie().getJoueur();
		//initialize();
		//new ControleurNbJVirtuel(comboBoxNbJoueursVirtuels);
		// on initialise le jeu
		initializeGame();
		
		
		/**
		 * Mise en place des Observers sur les objets de la partie
		 */

		Partie.getPartie().getManche().getTalon().addObserver(this);
		Partie.getPartie().getManche().getPioche().addObserver(this);
		ListIterator<Joueur> it = joueur.listIterator();
		while(it.hasNext()) {
			it.next().addObserver(this); //observateur Joueur
		}
		Partie.getPartie().addObserver(this);
		
		this.vueJVirtuel=new ArrayList<VueJoueurVirtuel>();

		for(int iterator = 1; iterator < joueur.size(); iterator++) {
			this.vueJVirtuel.add(new VueJoueurVirtuel(Partie.getPartie().getJoueur().get(iterator).getNumero()));
			panel_JoueurVirtuel.add(vueJVirtuel.get(iterator-1));
		}
		/**
		 * Itï¿½ration qui permet d'afficher les cartes du joueur ï¿½ l'ï¿½cran dans sa main.
		 */

		//On créé un itérateur qui va parcourir les cartes de notre jeu
		ArrayList<Carte> cartesJoueurPhysique = Partie.getPartie().getJoueur().get(0).getCartes() ; 

		ListIterator parcourirCarteJoueur = cartesJoueurPhysique.listIterator(); 
		
		while(parcourirCarteJoueur.hasNext()) {
			Carte prochaineCarte = (Carte) parcourirCarteJoueur.next();
			VueCarte vueProchaineCarte = new VueCarte(prochaineCarte);
			ControleurCarte controleurProchaineCarte = new ControleurCarte(Partie.getPartie(), prochaineCarte, vueProchaineCarte);
			panel_Main.add(vueProchaineCarte);
		}
		

		
	}
	


	public void run() {

	Partie.getPartie().lancerPartie();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	// Pas encore opérationnelle
	private void initialize() {
		//setFrame(new JFrame());
		//getFrame().setBounds(100, 100, 1000, 700);
		//getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtNombreDeJoueurs = new JTextField();
		txtNombreDeJoueurs.setEditable(false);
		txtNombreDeJoueurs.setText("Nombre de joueurs virtuels ?");
		txtNombreDeJoueurs.setBounds(57, 101, 215, 46);
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
		
		comboBoxNbJoueursVirtuels= new JComboBox();
		comboBoxNbJoueursVirtuels.setMaximumRowCount(5);
		comboBoxNbJoueursVirtuels.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBoxNbJoueursVirtuels.setBounds(337, 113, 170, 34);
		frame.getContentPane().add(comboBoxNbJoueursVirtuels);
	}
	
	private void initializeGame() {

		
		/**
		 * Gestion du conteneur des boutons Carte et Contre-carte. On dï¿½finit sa position et les composants qu'il contient.
		 */
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 700);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelActionCarte = new JPanel();
		getFrame().getContentPane().add(panelActionCarte, BorderLayout.WEST);
		panelActionCarte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelActionCarte.setBackground(new Color(8, 81, 36));
		
		btnCarte = new JButton("Carte");
		panelActionCarte.add(btnCarte);
		
		btnContreCarte = new JButton("Contre Carte");
		panelActionCarte.add(btnContreCarte);
		
		/**
		 * Gestion du conteneur des Joueurs Virtuels. On dï¿½finit sa position et les composants qu'il contient.
		 */
		panel_JoueurVirtuel = new JPanel();
		getFrame().getContentPane().add(panel_JoueurVirtuel, BorderLayout.NORTH);
		panel_JoueurVirtuel.setBackground(new Color(8, 81, 36));
		
		/**
		 * Gestion du conteneur du Score. On dï¿½finit sa position et les composants qu'il contient.
		 */
		panel_Classement = new JPanel();
	    GridLayout grid = new GridLayout(this.joueur.size()+1, 1);
		panel_Classement.setLayout(grid);
		getFrame().getContentPane().add(panel_Classement, BorderLayout.EAST);
		panel_Classement.setBackground(new Color(8, 81, 36));	
		lblClassement= new JLabel("Classement gï¿½nï¿½ral de la partie");
		lblClassement.setSize(50, 50);
		panel_Classement.add(lblClassement);
		ListIterator<Joueur> it = joueur.listIterator();
		while(it.hasNext()) {
			Joueur jNext = it.next();
			JLabel lbl = new JLabel(jNext.getName()+" : "+ jNext.getScore());
			
			panel_Classement.add(lbl);
		}
		//panel_Classement.getLayout().minimumLayoutSize(panel_Classement);
		
		
		
		/**
		 * Gestion du conteneur de la main du joueur physique. On dï¿½finit sa position et les composants qu'il contient.
		 */
		panel_Main = new JPanel();
		getFrame().getContentPane().add(panel_Main, BorderLayout.SOUTH);
	    GridLayout grid1 = new GridLayout();
		panel_Main.setLayout(grid1);
		panel_Main.setBackground(new Color(8, 81, 36));
		//Partie permettant de tester la main
		/*Carte carte10Pique = new Carte("10", "PIQUE");
		VueCarte vueCarte =new VueCarte(carte10Pique);
		panel_Main.add(vueCarte);*/
		
		
		
		/**
		 * Gestion du conteneur de la Pioche et du Talon. On dï¿½finit sa position et les composants qu'il contient.
		 */
		panel_Pioche = new VuePiocheTalon();
		getFrame().getContentPane().add(panel_Pioche, BorderLayout.CENTER);	
	}

	
	public void update(Observable instanceObservable, Object arg1) {
		if (instanceObservable instanceof Joueur) {
			this.panel_Pioche.update(instanceObservable, arg1);
		}
		if (instanceObservable instanceof JoueurVirtuel) {
			int num =((Joueur) instanceObservable).getNumero();
			this.vueJVirtuel.get(num-2).update(instanceObservable, arg1);
		}
		else if (instanceObservable instanceof JoueurPhysique) {
			//Main
			/**
			 * RedÃ©finir les cartes visibles en main en fonction du tour qu'a jouÃ© le joueur.
			 */
			panel_Main.removeAll();
			ArrayList<Carte> cartesJoueurPhysique = Partie.getPartie().getJoueur().get(0).getCartes() ; 
			ListIterator parcourirCarteJoueur = cartesJoueurPhysique.listIterator(); 
			
			while(parcourirCarteJoueur.hasNext()) {
				Carte prochaineCarte = (Carte) parcourirCarteJoueur.next();
				VueCarte vueProchaineCarte = new VueCarte(prochaineCarte);
				ControleurCarte controleurProchaineCarte = new ControleurCarte(Partie.getPartie(), prochaineCarte, vueProchaineCarte);
				panel_Main.add(vueProchaineCarte);
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