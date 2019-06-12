package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import userController.UserController;

public class EcranAssocierEtu extends JFrame{
  JLabel lblTitreAssocierEtu = new JLabel("ASSOCIER UN ETUDIANT A UN GROUPE :");
  private JTextField txtLoginEtu = new JTextField("Login étu");
  private JTextField txtIdGroupe = new JTextField("Id groupe");
  private JPanel pan8 = new JPanel();
  private JButton okAssocierEtu = new JButton ("OK");
  
  public EcranAssocierEtu(String stringLoginArg, UserController userController){
	pan8.setLayout(null);
    this.setTitle("EcranAssocierEtu");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreAssocierEtu.setBounds(400, 0, 250, 20);
    txtLoginEtu.setBounds(400, 50, 100, 20);
    txtIdGroupe.setBounds(400, 100, 100, 20);
    okAssocierEtu.setBounds(400, 150, 100, 20);
    pan8.add(lblTitreAssocierEtu);
    pan8.add(txtLoginEtu);
    pan8.add(txtIdGroupe);
    pan8.add(okAssocierEtu);
    this.setContentPane(pan8);
    this.setVisible(false);
  
    okAssocierEtu.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
      	String stringAssocierLogin = txtLoginEtu.getText(); 
      	String stringAssocierIdGroupe = txtIdGroupe.getText(); 
      	int valeurAssocierIdGroupe = 0;
      	try {
      		valeurAssocierIdGroupe = Integer.parseInt(stringAssocierIdGroupe); 
	        	if(userController.associateStudToGroup(stringLoginArg, stringAssocierLogin, valeurAssocierIdGroupe) == true)
	        	{
	        		JOptionPane.showMessageDialog(okAssocierEtu, "Etudiant associé au groupe " + valeurAssocierIdGroupe);
	        		System.out.println(valeurAssocierIdGroupe);
	        		setVisible(false);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(okAssocierEtu, "L'association n'est pas possible");
	        	}
      	}
      	catch (NumberFormatException e) {
      		JOptionPane.showMessageDialog(okAssocierEtu, "Il ne s'agit pas d'un nombre...");
      }
   }
});
  }
}