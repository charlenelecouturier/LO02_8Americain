package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import modele.*;
import javax.swing.*;
import controleur.ControleurBoutonCarte;
import controleur.ControleurBoutonChoixCouleur;
import controleur.ControleurBoutonContreCarte;
import controleur.ControleurCarte;

import java.util.*;
/**
 * <b> Interface graphique principale, s'ouvre à chaque manche et contient 5 panneaux organisés en Border Layout : </b> <ul>
 * <li>Le panneau des Joueurs Virtuels, qui contient des VueJoueurVirtuel pour chaque adversaire</li>
 * <li>Le panneau de la Pioche et du Talon, qui contient les vues du Talon et de la Pioche</li>
 * <li>Le panneau ActionCarte, qui contient les boutons Carte, contrecarte ainsi que le log des actions</li>
 * <li>Le panneau de la Main, qui contient les cartes en main du joueur Physique</li>
 * <li>Le panneau des scores, qui contient les scores de chaque joueur.</li>
 * </ul>
 * @author Charlene et Robin
 * @version 1.0
 *
 */
public class InterfaceManche implements Observer {

	private ArrayList<VueJoueurVirtuel> vueJVirtuel;

	// Fen�tre principale et conteneurs de l'interface
	private JFrame frame;
	private JPanel panelActionCarte;
	private JPanel panel_JoueurVirtuel;
	private JPanel panel_Main;
	private JPanel panel_Classement;
	private JPanel panelChoixFamille;
	private VuePiocheTalon panel_Pioche;
	// Labels et autres presents dans l'Interface
	private JButton btnCarte;
	private JButton btnContreCarte;
	private JButton btnCoeur;
	private JButton btnCarreau;
	private JButton btnTrefle;
	private JButton btnPique;
	
	private JLabel lblClassement;
	private VueEffetJeu effetsJeu;
	// Objets du modele a observer
	private LinkedList<Joueur> joueur;
	

	public InterfaceManche(JFrame frame, Partie p) {
		this.frame = frame;
		this.initializeGame(p);
	}
/**
 * <b> Initialisation de la fenêtre de la Manche. Chaque panneau composant de la fenêtre est créé, placé dans le Layout,
 *  et les objets qui le composent définis. </b>
 * @param p la Partie en cours.
 */
	public void initializeGame(Partie p) {

		frame.getContentPane().setVisible(false);
		frame.getContentPane().removeAll();

		Color background = new Color(8, 81, 36);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		((BorderLayout) frame.getContentPane().getLayout()).setHgap(30);
		((BorderLayout) frame.getContentPane().getLayout()).setVgap(60);
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
		((FlowLayout) panelActionCarte.getLayout()).setVgap(30);
		((FlowLayout) panelActionCarte.getLayout()).setHgap(30);
		btnCarte = new JButton("Carte");
		panelActionCarte.add(btnCarte);
		btnContreCarte = new JButton("Contre Carte");
		panelActionCarte.add(btnContreCarte);
		new ControleurBoutonContreCarte(Partie.getPartie(), btnContreCarte, this.effetsJeu);
		new ControleurBoutonCarte(Partie.getPartie(), btnCarte, this.effetsJeu);

		panelWEST.add(panelActionCarte, BorderLayout.NORTH);
		this.effetsJeu = new VueEffetJeu();
		panelWEST.add(this.effetsJeu, BorderLayout.CENTER);
		JScrollPane jp = new JScrollPane(this.effetsJeu);
		panelWEST.add(jp, BorderLayout.CENTER);
		this.panelChoixFamille=new JPanel();
		this.panelChoixFamille.setLayout(new GridLayout(0,1));
		JTextField choixFamille =new JTextField("Quelle famille voulez vous ?");
		choixFamille.setEditable(false);
		this.panelChoixFamille.add(choixFamille);
		this.panelChoixFamille.add(this.btnCarreau= new JButton("Carreau"));
		this.panelChoixFamille.add(this.btnCoeur= new JButton("Coeur"));
		this.panelChoixFamille.add(this.btnPique= new JButton("Pique"));
		this.panelChoixFamille.add(this.btnTrefle= new JButton("Trefle"));
		this.panelChoixFamille.setVisible(false);
		new ControleurBoutonChoixCouleur(btnCarreau, "CARREAU", this.panelChoixFamille, p);
		new ControleurBoutonChoixCouleur(btnCoeur, "COEUR", this.panelChoixFamille, p);
		new ControleurBoutonChoixCouleur(btnPique, "PIQUE", this.panelChoixFamille, p);
		new ControleurBoutonChoixCouleur(btnTrefle, "TREFLE", this.panelChoixFamille, p);
		panelWEST.add(this.panelChoixFamille, BorderLayout.SOUTH);

		/**
		 * Gestion du conteneur des Joueurs Virtuels. On d�finit sa position et les
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
		 * Gestion du conteneur du Score. On d�finit sa position et les composants
		 * qu'il contient.
		 */
		panel_Classement = new JPanel();
		GridLayout grid = new GridLayout(this.joueur.size() + 1, 1);
		panel_Classement.setLayout(grid);
		getFrame().getContentPane().add(panel_Classement, BorderLayout.EAST);
		panel_Classement.setBackground(new Color(39, 135, 75));
		lblClassement = new JLabel("Classement general de la partie : ");
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

		/**
		 * Mise en place des Observers sur les objets de la partie
		 */
		p.addObserver(this);
		p.getManche().addObserver(this);
		p.getManche().getTalon().addObserver(this);
		if(p.getManche().getPioche()==null)
		{p.getManche().setPioche(new Pioche());// creation de la pioche
		p.getManche().getPioche().melanger();// on melange la pioche
		p.getManche().getPioche().distribuer();}// on distribue la pioche
		p.getManche().getPioche().addObserver(this);
		ListIterator<Joueur> it = joueur.listIterator();
		while (it.hasNext()) {
			Joueur jNext = it.next();
			jNext.addObserver(this);
		}

		/**
		 * Gestion du conteneur de la main du joueur physique. On d�finit sa position
		 * et les composants qu'il contient.
		 */
		panel_Main = new JPanel();
		getFrame().getContentPane().add(panel_Main, BorderLayout.SOUTH);
		GridLayout grid1 = new GridLayout();
		panel_Main.setLayout(grid1);
		panel_Main.setBackground(background);
		
		/**
		 * Iteration qui permet d'afficher les cartes du joueur � l'�cran dans sa
		 * main.
		 */
		// On cr�� un it�rateur qui va parcourir les cartes de notre jeu
		ArrayList<Carte> cartesJoueurPhysique = Partie.getPartie().getJoueur().get(0).getCartes();

		ListIterator<Carte> parcourirCarteJoueur = cartesJoueurPhysique.listIterator();

		while (parcourirCarteJoueur.hasNext()) {
			Carte prochaineCarte = (Carte) parcourirCarteJoueur.next();
			VueCarte vueProchaineCarte = new VueCarte(prochaineCarte);
			new ControleurCarte(Partie.getPartie(), prochaineCarte, vueProchaineCarte);
			panel_Main.add(vueProchaineCarte);
		}

		/**
		 * Gestion du conteneur de la Pioche et du Talon. On d�finit sa position et
		 * les composants qu'il contient.
		 */
		panel_Pioche = new VuePiocheTalon();
		getFrame().getContentPane().add(panel_Pioche, BorderLayout.CENTER);

		frame.repaint();
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
		frame.revalidate();
		getFrame().pack();
		getFrame().setVisible(true);
		getFrame().getContentPane().setVisible(true);
	}

	/**
	 * <b>implémentation de l'interface Observer, permet de mettre à jour les composants 
	 * de la manche lorsque le Modèle notifie des changements. </b>
	 * <p> Consulter le design pattern Observer pour plus d'informations.</p>
	 * @see modele
	 */
	public void update(Observable instanceObservable, Object arg1) {

		if (instanceObservable instanceof Joueur) {
			this.panel_Pioche.update(instanceObservable, arg1);

			if (arg1 != null) {
				if (!arg1.equals("Changer Famille") && !arg1.equals("tour") && !arg1.equals("a fini")&&!arg1.equals(" a change de famille") ) {
					this.effetsJeu.update(instanceObservable, arg1);
				}
			}
			if (instanceObservable instanceof JoueurVirtuel) {
				int num = ((Joueur) instanceObservable).getNumero();
				this.vueJVirtuel.get(num - 2).update(instanceObservable, arg1);
				frame.repaint();
				frame.revalidate();
			} else if (instanceObservable instanceof JoueurPhysique) {

				if (arg1 != null) {
					if (arg1.equals("Changer Famille")) {
						this.panelChoixFamille.setVisible(true);
					} else if (arg1.equals(" a change de famille")) {
						this.panelChoixFamille.setVisible(false);
					}
				 else if (arg1.equals("a fini")) {
					/**
					 * Redefinir les cartes visibles en main en fonction du tour qu'a joué le
					 * joueur.
					 */
					panel_Main.removeAll();
					ArrayList<Carte> cartesJoueurPhysique = Partie.getPartie().getJoueur().get(0).getCartes();
					ListIterator<Carte> parcourirCarteJoueur = cartesJoueurPhysique.listIterator();

					while (parcourirCarteJoueur.hasNext()) {
						Carte prochaineCarte = (Carte) parcourirCarteJoueur.next();
						VueCarte vueProchaineCarte = new VueCarte(prochaineCarte);
						ControleurCarte controleurProchaineCarte = new ControleurCarte(Partie.getPartie(),
								prochaineCarte, vueProchaineCarte);
						panel_Main.add(vueProchaineCarte);
						frame.repaint();
						frame.revalidate();
					}}
				}
			}

		} else if (instanceObservable instanceof Manche) {
			if (arg1 != null) {
				if (arg1.equals("Le sens a change !")) {
					this.effetsJeu.update(instanceObservable, arg1);
				}
			}
		} else if (instanceObservable instanceof Partie) {
			if (arg1 != null) {
				if (arg1.equals("manche terminee")) {
					new InterfaceFinManche(this.frame);
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

	/**
	 * @return the joueur
	 */
	public LinkedList<Joueur> getJoueur() {
		return joueur;
	}
}