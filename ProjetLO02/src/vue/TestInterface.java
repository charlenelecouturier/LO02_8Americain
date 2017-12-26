package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controleur.ControleurCarte;
import modele.*;



public class TestInterface implements Observer {
	
	private ArrayList<VueJoueurVirtuel> vueJVirtuel;
	// Fen�tre principale et conteneurs de l'interface
	private JFrame frame;
	private JPanel panelActionCarte; 
	private JPanel panel_JoueurVirtuel; 
	private JPanel panel_Main;
	private JPanel panel_Score;
	private JPanel panel_Pioche; 
	// Labels et autres pr�sents dans l'Interface
	private JButton btnCarte;
	private JButton btnContreCarte;
	private JLabel lblScore;
	// Objets du mod�le � observer
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
	public TestInterface(Partie p) {
		initialize();
		
		joueur = p.getJoueur();
		/**
		 * Mise en place des Observers sur les objets de la partie
		 */
		p.getManche().getTalon().addObserver(this);
		p.getManche().getPioche().addObserver(this);
		
		this.vueJVirtuel=new ArrayList();
		for(int iterator = 1; iterator < joueur.size(); iterator++) {
			this.vueJVirtuel.add(new VueJoueurVirtuel(p.getJoueur().get(iterator).getNumero()));
			panel_JoueurVirtuel.add(vueJVirtuel.get(iterator-1));
		}
		/**
		 * It�ration qui permet d'afficher les cartes du joueur � l'�cran dans sa main.
		 */
		//On cr�� un it�rateur qui va parcourir les cartes de notre jeu
		ArrayList<Carte> cartesJoueurPhysique = p.getJoueur().get(0).getCartes() ; 
		ListIterator parcourirCarteJoueur = cartesJoueurPhysique.listIterator(); 
		
		while(parcourirCarteJoueur.hasNext()) {
			Carte prochaineCarte = (Carte) parcourirCarteJoueur.next();
			VueCarte vueProchaineCarte = new VueCarte(prochaineCarte);
			ControleurCarte controleurProchaineCarte = new ControleurCarte(p, prochaineCarte, vueProchaineCarte);
			panel_Main.add(vueProchaineCarte);
		}
		
		JLabel carteTalon= new JLabel();
		VueCarte vueCarteDessusTalon = new VueCarte(Partie.getPartie().getManche().getTalon().getCarteDessus());
		panel_Pioche.add(vueCarteDessusTalon);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 700);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * Gestion du conteneur des boutons Carte et Contre-carte. On d�finit sa position et les composants qu'il contient.
		 */
		panelActionCarte = new JPanel();
		getFrame().getContentPane().add(panelActionCarte, BorderLayout.WEST);
		panelActionCarte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelActionCarte.setBackground(new Color(8, 81, 36));
		
		btnCarte = new JButton("Carte");
		panelActionCarte.add(btnCarte);
		
		btnContreCarte = new JButton("Contre Carte");
		panelActionCarte.add(btnContreCarte);
		
		/**
		 * Gestion du conteneur des Joueurs Virtuels. On d�finit sa position et les composants qu'il contient.
		 */
		panel_JoueurVirtuel = new JPanel();
		getFrame().getContentPane().add(panel_JoueurVirtuel, BorderLayout.NORTH);
		panel_JoueurVirtuel.setBackground(new Color(8, 81, 36));
		
		/**
		 * Gestion du conteneur du Score. On d�finit sa position et les composants qu'il contient.
		 */
		panel_Score = new JPanel();
		getFrame().getContentPane().add(panel_Score, BorderLayout.EAST);
		panel_Score.setBackground(new Color(8, 81, 36));
		
		lblScore = new JLabel("Score :");
		panel_Score.add(lblScore);
		
		
		/**
		 * Gestion du conteneur de la main du joueur physique. On d�finit sa position et les composants qu'il contient.
		 */
		panel_Main = new JPanel();
		getFrame().getContentPane().add(panel_Main, BorderLayout.SOUTH);
		panel_Main.setLayout(new FlowLayout());
		panel_Main.setBackground(new Color(8, 81, 36));
		//Partie permettant de tester la main
		/*Carte carte10Pique = new Carte("10", "PIQUE");
		VueCarte vueCarte =new VueCarte(carte10Pique);
		panel_Main.add(vueCarte);*/
		
		
		
		/**
		 * Gestion du conteneur de la Pioche et du Talon. On d�finit sa position et les composants qu'il contient.
		 */
		panel_Pioche = new JPanel();
		getFrame().getContentPane().add(panel_Pioche, BorderLayout.CENTER);
		panel_Pioche.setBackground(new Color(8, 81, 36));
		
		JLabel lblPioche = new JLabel("Pioche");
		JLabel dosPioche= new JLabel();
		dosPioche.setIcon(new ImageIcon("Images/dosPioche.jpg"));
		panel_Pioche.add(lblPioche);
		panel_Pioche.add(dosPioche);
		
		JLabel lblTalon = new JLabel("Talon");
		panel_Pioche.add(lblTalon);
	}

	
	public void update(Observable instanceObservable, Object arg1) {
		if (instanceObservable instanceof JoueurVirtuel) {
			int num =((JoueurVirtuel) instanceObservable).getNumero();
			this.vueJVirtuel.get(num-2).update(instanceObservable, arg1);
		}
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return this.frame;
	}

}