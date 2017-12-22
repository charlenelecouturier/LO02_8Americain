package vue;
import javax.swing.JFrame;

public class TestJoueurVirtuel extends JFrame {
  public TestJoueurVirtuel(){                
    this.setTitle("Ma première fenêtre Java");
    this.setSize(400, 400);
    this.setLocationRelativeTo(null);               
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(new VueJoueurVirtuel());

    this.setVisible(true);
  }     
  
  public static void main(String[] args) {
	  TestJoueurVirtuel fenetre = new TestJoueurVirtuel();
  }
}