package view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import userController.UserController;

public class EcranVoirGroupe extends JFrame{
  JLabel lblTitreVoirGroupe = new JLabel("VOIR GROUPE :");
  private JPanel pan5 = new JPanel();
  JList liste = new JList();
  
  public EcranVoirGroupe(UserController userController){
	String choix[] = userController.groupsIdToString();
	liste = new JList(choix);
	pan5.setLayout(null);
    this.setTitle("EcranVoirGroupe");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreVoirGroupe.setBounds(400, 0, 100, 20);
    liste.setBounds(400, 100, 100, 200);
    pan5.add(lblTitreVoirGroupe);
    pan5.add(liste);
    this.setContentPane(pan5);
    this.setVisible(false);
  }       
}