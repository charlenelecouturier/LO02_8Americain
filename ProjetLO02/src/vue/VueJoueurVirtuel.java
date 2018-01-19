package vue;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import modele.Partie;
import javax.swing.*;

import modele.*;
/**
 * <b> Panneau dédié à la représentation graphique d'un joueur virtuel</b>
 * <p>Affiche une icone de personnage, le nom du joueur, une icone de carte retournée ainsi que le niveau du Joueur et le nombre de cartes lui restant./n
 * Cette représentation est ensuite insérée pour chaque adversaire (maximum 5) dans l'<b>InterfaceManche</b></p>
 * 
 * @see InterfaceManche
 * @author Charlene et Robin
 * @version 1.0
 */
public class VueJoueurVirtuel extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;

	
	protected static Color FOND_JOUEURS = new Color(112, 181, 134);

	protected JLabel iconeJoueur;
	protected JLabel niveauJoueur;
	protected JLabel dosCarte;
	protected JLabel nbCartes;
	protected JLabel lblNomJoueur;
	protected int numJoueur;

	public VueJoueurVirtuel(int numero) {
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new GridLayout(0, 2));
		this.setMaximumSize(new Dimension(160, 140));
		initialize();
		this.numJoueur = numero;
		Joueur j = Partie.getPartie().getJoueur().get(numJoueur - 1);
		if (((JoueurVirtuel) j).getStrat() == 1) {
			this.niveauJoueur = new JLabel("Niveau : Faible");
		} else {
			this.niveauJoueur = new JLabel("Niveau : Eleve");

		}
		niveauJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomJoueur = new JLabel(j.getName() + " ");
		lblNomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		nbCartes = new JLabel("Cartes restantes: 8  ");
		nbCartes.setHorizontalAlignment(SwingConstants.CENTER);
		Font f = new Font("Serif", Font.BOLD, 16);
		Font f2 = new Font("Serif", Font.BOLD|Font.ITALIC, 18);
		nbCartes.setFont(f2);
		niveauJoueur.setFont(f);
		lblNomJoueur.setFont(f);
		Color noir = new Color(0, 0, 0);
		nbCartes.setForeground(noir);
		niveauJoueur.setForeground(noir);
		lblNomJoueur.setForeground(noir);
		this.add(iconeJoueur);
		this.add(dosCarte);
		this.add(lblNomJoueur);
		this.add(nbCartes);
		this.add(this.niveauJoueur);
		this.setBackground(FOND_JOUEURS);

	}

	public void initialize() {
		iconeJoueur = new JLabel();
		dosCarte = new JLabel();
		iconeJoueur.setIcon(new ImageIcon("Images/joueur.png"));
		iconeJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		dosCarte.setIcon(new ImageIcon("Images/dosCarte.jpg"));
		dosCarte.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void changerCouleurBordure(String type) {
		// Si le joueur est en train de jouer , on applique une bordure rouge autour de
		// son personnage
		if (type.equals("RED")) {
			this.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}
		// S'il ne joue plus, on applique une bordure invisible autour
		else {
			this.setBorder(BorderFactory.createEmptyBorder());
		}
	}

	/**
	 * <p>Met à jour la VueJoueurVirtuel à chaque notification de celui-ci, en entourant le joueur actuel d'un cadre rouge et en actualisant le nombre de cartes restantes à la fin du tour.</p>
	 */
	public void update(Observable o, Object arg) {
		this.nbCartes.setText(
				"Cartes restantes: " + Partie.getPartie().getJoueur().get(this.numJoueur - 1).getCartes().size());
		if (arg != null) {
			if (arg.equals("tour")) {
				this.changerCouleurBordure("RED");
				try {// Temps de delais entre chaque tour
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (arg.equals("a fini")) {
				try {// Temps de delais entre chaque tour
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.changerCouleurBordure("NORMAL");

			} else if (arg.equals("gagne")) {
				this.setBackground(new Color(149, 172, 157));
			}
		}
	}
}
