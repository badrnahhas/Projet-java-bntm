package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import userController.UserController;

public class EcranSupprUtil extends JFrame{
  JLabel lblTitreSupprUtil = new JLabel("SUPPRIMER UTILISATEUR :");
  private JTextField txtLoginSupprUtil = new JTextField();
  private JPanel pan16 = new JPanel();
  private JButton okSupprUtil = new JButton ("OK");
  
  public EcranSupprUtil(String stringLoginArg, UserController userController){
	pan16.setLayout(null);
    this.setTitle("EcranSupprUtil");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreSupprUtil.setBounds(400, 0, 100, 20);
    txtLoginSupprUtil.setBounds(400, 50, 100, 20);
    okSupprUtil.setBounds(400, 100, 100, 20);
    pan16.add(lblTitreSupprUtil);
    pan16.add(txtLoginSupprUtil);
    pan16.add(okSupprUtil);
    this.setContentPane(pan16);
    this.setVisible(false);
    
    okSupprUtil.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringSupprUtil = txtLoginSupprUtil.getText(); 
        	if(userController.removeUser(stringLoginArg, stringSupprUtil) == true)
        	{
        		JOptionPane.showMessageDialog(okSupprUtil, "Utilisateur supprimé");
        		setVisible(false);
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(okSupprUtil, "L'utilisateur n'existe pas");
        	}
        }
     });
  }
  }       