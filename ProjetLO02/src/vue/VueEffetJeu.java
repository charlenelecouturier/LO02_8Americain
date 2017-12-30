package vue;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import modele.Joueur;

public class VueEffetJeu extends JTextField implements Observer{

	public VueEffetJeu()  {
		
		this.setEditable(false);
		this.setBackground(new Color(255,255,255));
		this.setBounds(5, 270, 200, 178);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1.equals("CARTE ! ")) {
			this.setText(((Joueur)arg0).getName()+ "DIT "+ arg1);
			
		}else if(arg1.equals("CONTRE-CARTE ! ")) {
			this.setText(((Joueur)arg0).getName()+ " recoit un "+ arg1);

		}
		
	}

}
