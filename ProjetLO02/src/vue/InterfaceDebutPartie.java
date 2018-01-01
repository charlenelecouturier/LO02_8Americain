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

public class InterfaceDebutPartie {

	private ArrayList<VueJoueurVirtuel> vueJVirtuel;

	// Fenêtre principale et conteneurs de l'interface

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
	// Pas encore opérationnelle
	private void initialize() {
		setFrame(new JFrame());
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("8 Americain_Robin LALLIER_Charlene LECOUTURIER");

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