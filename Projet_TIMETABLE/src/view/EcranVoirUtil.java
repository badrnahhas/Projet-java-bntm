package view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import userController.UserController;

public class EcranVoirUtil extends JFrame{
  JLabel lblTitreVoirUtil = new JLabel("VOIR UTILISATEUR :");
  private JPanel pan12 = new JPanel();
  JList listeUtil = new JList();
  
  public EcranVoirUtil(UserController userController){
	String choixUtil[] = userController.usersToString();
	listeUtil = new JList(choixUtil);
	pan12.setLayout(null);
    this.setTitle("EcranVoirUtil");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreVoirUtil.setBounds(400, 0, 100, 20);
    listeUtil.setBounds(25, 100, 800, 200);
    pan12.add(lblTitreVoirUtil);
    pan12.add(listeUtil);
    this.setContentPane(pan12);
    this.setVisible(false);
  }       
}