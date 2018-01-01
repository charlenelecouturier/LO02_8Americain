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
	private JTextField txtFinPartie;

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
		
		txtFinPartie = new JTextField();
		txtFinPartie.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 36));
		txtFinPartie.setHorizontalAlignment(SwingConstants.CENTER);
		txtFinPartie.setEditable(false);
		txtFinPartie.setText("Fin de la partie !");
		txtFinPartie.setBounds(200, 76, 508, 100);
		frame.getContentPane().add(txtFinPartie);
		txtFinPartie.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(200, 209, 508, 279);
		frame.getContentPane().add(panel);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
