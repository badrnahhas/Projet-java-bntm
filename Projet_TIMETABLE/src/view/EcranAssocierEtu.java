package view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EcranAssocierEtu extends JFrame{
  JLabel lblTitreAssocierEtu = new JLabel("ASSOCIER UN ETUDIANT A UN GROUPE :");
  private JPanel pan8 = new JPanel();
  
  public EcranAssocierEtu(){
	pan8.setLayout(null);
    this.setTitle("EcranAssocierEtu");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreAssocierEtu.setBounds(400, 0, 100, 20);
    pan8.add(lblTitreAssocierEtu);
    this.setContentPane(pan8);
    this.setVisible(false);
  }       
}