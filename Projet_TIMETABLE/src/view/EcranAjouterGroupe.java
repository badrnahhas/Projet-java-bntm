package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import timeTableController.TimeTableController;
import userController.UserController;

public class EcranAjouterGroupe extends JFrame{
  JLabel lblTitreAjouterGroupe = new JLabel("AJOUTER GROUPE :");
  private JTextField txtIdGroupe = new JTextField("N° groupe");
  private JPanel pan6 = new JPanel();
  private JButton okAjouterGroupe = new JButton ("OK");
  
  public EcranAjouterGroupe(String stringLoginArg, UserController userController, TimeTableController tTController){
	pan6.setLayout(null);
    this.setTitle("EcranAjouterGroupe");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreAjouterGroupe.setBounds(400, 0, 100, 20);
    txtIdGroupe.setBounds(400, 50, 100, 20);
    okAjouterGroupe.setBounds(400, 100, 100, 20);
    pan6.add(lblTitreAjouterGroupe);
    pan6.add(txtIdGroupe);
    pan6.add(okAjouterGroupe);
    this.setContentPane(pan6);
    this.setVisible(false);
    
    okAjouterGroupe.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringAjouterGroupe = txtIdGroupe.getText(); 
        	int valeurAjouterGroupe = 0;
        	try {
        		valeurAjouterGroupe = Integer.parseInt(stringAjouterGroupe); 
	        	if(userController.addGroup(stringLoginArg, valeurAjouterGroupe) == true)
	        	{
	        		tTController.addTimeTable(valeurAjouterGroupe);
	        		JOptionPane.showMessageDialog(okAjouterGroupe, "groupe ajouté");
	        		System.out.println(valeurAjouterGroupe);
	        		setVisible(false);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(okAjouterGroupe, "Le groupe existe déjà");
	        	}
        	}
        	catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(okAjouterGroupe, "Il ne s'agit pas d'un nombre...");
        }
     }
  });       
}
}