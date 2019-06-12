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

public class EcranSupprGroupe extends JFrame{
  JLabel lblTitreSupprGroupe = new JLabel("SUPPRIMER GROUPE :");
  private JTextField txtIdGroupe = new JTextField();
  private JPanel pan7 = new JPanel();
  private JButton okSupprGroupe = new JButton ("OK");
  
  public EcranSupprGroupe(String stringLoginArg, UserController userController){
	pan7.setLayout(null);
    this.setTitle("EcranSupprGroupe");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreSupprGroupe.setBounds(400, 0, 100, 20);
    txtIdGroupe.setBounds(400, 50, 100, 20);
    okSupprGroupe.setBounds(400, 100, 100, 20);
    pan7.add(lblTitreSupprGroupe);
    pan7.add(txtIdGroupe);
    pan7.add(okSupprGroupe);
    this.setContentPane(pan7);
    this.setVisible(false);
    
    okSupprGroupe.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringAjouterGroupe = txtIdGroupe.getText(); 
        	int valeurAjouterGroupe = 0;
        	try {
        		valeurAjouterGroupe = Integer.parseInt(stringAjouterGroupe); 
	        	if(userController.removeGroup(stringLoginArg, valeurAjouterGroupe) == true)
	        	{
	        		JOptionPane.showMessageDialog(okSupprGroupe, "Groupe supprimé");
	        		System.out.println(valeurAjouterGroupe);
	        		setVisible(false);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(okSupprGroupe, "Ce groupe n'existe pas");
	        	}
        	}
        	catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(okSupprGroupe, "Il ne s'agit pas d'un nombre...");
        }
     }
  });
  }       
}