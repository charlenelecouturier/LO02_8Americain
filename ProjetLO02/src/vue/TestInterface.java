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

import controleur.ControleurCarte;
import modele.*;



public class TestInterface implements Observer {
	
	private ArrayList<VueJoueurVirtuel> vueJVirtuel;
	// Fenêtre principale et conteneurs de l'interface
	private JFrame frame;
	private JPanel panelActionCarte; 
	private JPanel panel_JoueurVirtuel; 
	private JPanel panel_Main;
	private JPanel panel_Classement;
	private VuePiocheTalon panel_Pioche; 
	// Labels et autres présents dans l'Interface
	private JButton btnCarte;
	private JButton btnContreCarte;
	private JLabel lblClassement;
	// Objets du modèle à observer
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
		joueur = p.getJoueur();
		initialize();
		
		
		/**
		 * Mise en place des Observers sur les objets de la partie
		 */

		p.getManche().getTalon().addObserver(this); //observateur Talon
		p.getManche().getPioche().addObserver(this); //observateur Pioche
		ListIterator<Joueur> it = joueur.listIterator();
		while(it.hasNext()) {
			it.next().addObserver(this); //observateur Joueur
		}
		
		this.vueJVirtuel=new ArrayList();

		for(int iterator = 1; iterator < joueur.size(); iterator++) {
			this.vueJVirtuel.add(new VueJoueurVirtuel(p.getJoueur().get(iterator).getNumero()));
			panel_JoueurVirtuel.add(vueJVirtuel.get(iterator-1));
		}
		/**
		 * Itération qui permet d'afficher les cartes du joueur à l'écran dans sa main.
		 */
		//On créé un itérateur qui va parcourir les cartes de notre jeu
		ArrayList<Carte> cartesJoueurPhysique = p.getJoueur().get(0).getCartes() ; 
		ListIterator parcourirCarteJoueur = cartesJoueurPhysique.listIterator(); 
		
		while(parcourirCarteJoueur.hasNext()) {
			Carte prochaineCarte = (Carte) parcourirCarteJoueur.next();
			VueCarte vueProchaineCarte = new VueCarte(prochaineCarte);
			ControleurCarte controleurProchaineCarte = new ControleurCarte(p, prochaineCarte, vueProchaineCarte);
			panel_Main.add(vueProchaineCarte);
		}
		

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 700);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * Gestion du conteneur des boutons Carte et Contre-carte. On définit sa position et les composants qu'il contient.
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
		 * Gestion du conteneur des Joueurs Virtuels. On définit sa position et les composants qu'il contient.
		 */
		panel_JoueurVirtuel = new JPanel();
		getFrame().getContentPane().add(panel_JoueurVirtuel, BorderLayout.NORTH);
		panel_JoueurVirtuel.setBackground(new Color(8, 81, 36));
		
		/**
		 * Gestion du conteneur du Score. On définit sa position et les composants qu'il contient.
		 */
		panel_Classement = new JPanel();
	    GridLayout grid = new GridLayout(this.joueur.size()+1, 1);
		panel_Classement.setLayout(grid);
		getFrame().getContentPane().add(panel_Classement, BorderLayout.EAST);
		panel_Classement.setBackground(new Color(8, 81, 36));	
		lblClassement= new JLabel("Classement général de la partie");
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
		 * Gestion du conteneur de la main du joueur physique. On définit sa position et les composants qu'il contient.
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
		 * Gestion du conteneur de la Pioche et du Talon. On définit sa position et les composants qu'il contient.
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