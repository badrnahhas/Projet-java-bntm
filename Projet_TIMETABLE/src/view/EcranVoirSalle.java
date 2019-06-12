package view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import userController.UserController;
import timeTableController.TimeTableController;

public class EcranVoirSalle extends JFrame{
  JLabel lblTitreVoirSalle = new JLabel("VOIR SALLE :");
  private JPanel pan9 = new JPanel();
  JList liste = new JList();
  
  public EcranVoirSalle(TimeTableController tTControlleruserController){
	String choix[] = tTControlleruserController.roomsIdToString();
	liste = new JList(choix);
	pan9.setLayout(null);
    this.setTitle("EcranVoirSalle");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreVoirSalle.setBounds(400, 0, 100, 20);
    liste.setBounds(400, 100, 100, 200);
    pan9.add(lblTitreVoirSalle);
    pan9.add(liste);
    this.setContentPane(pan9);
    this.setVisible(false);
  }       
}