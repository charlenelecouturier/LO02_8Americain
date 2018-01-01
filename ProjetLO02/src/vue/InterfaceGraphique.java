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
import java.awt.Font;
import javax.swing.SwingConstants;

public class InterfaceGraphique implements Observer{

	JFrame frame;
	private JButton btnDmarrer;
	private JTextField txtVariante;
	private JComboBox comboBox_3;
	private JTextField txtNouvelleManche;

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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(607, 426, -114, 22);
		frame.getContentPane().add(textArea);
		
		btnDmarrer = new JButton("D\u00E9marrer");
		btnDmarrer.setBounds(837, 599, 115, 29);
		frame.getContentPane().add(btnDmarrer);
		
		txtVariante = new JTextField();
		txtVariante.setHorizontalAlignment(SwingConstants.CENTER);
		txtVariante.setFont(new Font("SansSerif", Font.BOLD, 26));
		txtVariante.setEditable(false);
		txtVariante.setText("Variante ?");
		txtVariante.setBounds(106, 299, 451, 84);
		frame.getContentPane().add(txtVariante);
		txtVariante.setColumns(10);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("SansSerif", Font.BOLD, 26));
		comboBox_3.setMaximumRowCount(4);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Minimale", "Monclar", "Variante 4", "Variante 5"}));
		comboBox_3.setBounds(607, 299, 220, 81);
		frame.getContentPane().add(comboBox_3);
		
		txtNouvelleManche = new JTextField();
		txtNouvelleManche.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 36));
		txtNouvelleManche.setHorizontalAlignment(SwingConstants.CENTER);
		txtNouvelleManche.setEditable(false);
		txtNouvelleManche.setText("Nouvelle manche !");
		txtNouvelleManche.setBounds(200, 76, 508, 100);
		frame.getContentPane().add(txtNouvelleManche);
		txtNouvelleManche.setColumns(10);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
