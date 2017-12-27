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

public class InterfaceGraphique implements Observer{

	JFrame frame;
	private JTextField txtNombreDeJoueurs;
	private JTextField txtVotreNom;
	private JTextField textField;

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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(5);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setBounds(337, 113, 170, 34);
		frame.getContentPane().add(comboBox);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
