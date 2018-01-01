package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JTable;

public class InterfaceGraphique implements Observer{

	JFrame frame;
	private JTextField txtNombreDeJoueurs;
	private JTextField txtVotreNom;
	private JTextField textField;
	private JButton btnDmarrer;
	private JTextField txtModeDeComptage;
	private JComboBox comboBox_2;
	private JTextField txtVariante;
	private JComboBox comboBox_3;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphique window = new InterfaceGraphique();
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
	public InterfaceGraphique() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(5);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setBounds(333, 40, 170, 36);
		frame.getContentPane().add(comboBox);
		
		btnDmarrer = new JButton("D\u00E9marrer");
		btnDmarrer.setBounds(837, 599, 115, 29);
		frame.getContentPane().add(btnDmarrer);
		
		txtModeDeComptage = new JTextField();
		txtModeDeComptage.setEditable(false);
		txtModeDeComptage.setText("Mode de comptage des points ?");
		txtModeDeComptage.setBounds(57, 472, 243, 46);
		frame.getContentPane().add(txtModeDeComptage);
		txtModeDeComptage.setColumns(10);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"POSITIF", "NEGATIF"}));
		comboBox_2.setBounds(333, 475, 174, 40);
		frame.getContentPane().add(comboBox_2);
		
		txtVariante = new JTextField();
		txtVariante.setEditable(false);
		txtVariante.setText("Variante ?");
		txtVariante.setBounds(57, 534, 243, 40);
		frame.getContentPane().add(txtVariante);
		txtVariante.setColumns(10);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setMaximumRowCount(4);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Minimale", "Monclar", "Variante 4", "Variante 5"}));
		comboBox_3.setBounds(333, 531, 174, 40);
		frame.getContentPane().add(comboBox_3);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(193, 52, 670, 521);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
