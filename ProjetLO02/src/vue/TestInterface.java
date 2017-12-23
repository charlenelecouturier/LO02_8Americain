package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.*;

import modele.Carte;


public class TestInterface {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestInterface window = new TestInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelActionCarte = new JPanel();
		frame.getContentPane().add(panelActionCarte, BorderLayout.WEST);
		panelActionCarte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCarte = new JButton("Carte");
		panelActionCarte.add(btnCarte);
		
		JButton btnContreCarte = new JButton("Contre Carte");
		panelActionCarte.add(btnContreCarte);
		
		JPanel panel_JoueurVirtuel = new JPanel();
		frame.getContentPane().add(panel_JoueurVirtuel, BorderLayout.NORTH);
		
		JPanel panel_Score = new JPanel();
		frame.getContentPane().add(panel_Score, BorderLayout.EAST);
		
		JLabel lblScore = new JLabel("Score :");
		panel_Score.add(lblScore);
		
		JPanel panel_Main = new JPanel();
		frame.getContentPane().add(panel_Main, BorderLayout.SOUTH);
		Carte carte10Pique = new Carte("10", "PIQUE");
		VueCarte vueCarte =new VueCarte(carte10Pique);
		panel_Main.add(vueCarte);
		
		JLabel lblMain = new JLabel("Main");
		panel_Main.add(lblMain);
		
		JPanel panel_Pioche = new JPanel();
		frame.getContentPane().add(panel_Pioche, BorderLayout.CENTER);
		
		JLabel lblPioche = new JLabel("Pioche");
		panel_Pioche.add(lblPioche);
		
		JLabel lblTalon = new JLabel("Talon");
		panel_Pioche.add(lblTalon);
	}

}
