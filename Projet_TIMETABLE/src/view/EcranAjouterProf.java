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

public class EcranAjouterProf extends JFrame{
  JLabel lblTitreAjouterProf = new JLabel("AJOUTER PROFESSEUR :");
  private JTextField txtLoginProf = new JTextField("Login");
  private JTextField txtPasswordProf = new JTextField("Password");
  private JTextField txtIdProf = new JTextField("ID prof");
  private JTextField txtNomProf = new JTextField("Nom");
  private JTextField txtPrenomProf = new JTextField("Prénom");
  private JButton okAjouterProf = new JButton ("OK");
  private JPanel pan14 = new JPanel();
  
  public EcranAjouterProf(String stringLoginArg, UserController userController){
	pan14.setLayout(null);
    this.setTitle("EcranAjouterProf");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreAjouterProf.setBounds(400, 0, 100, 20);
    txtLoginProf.setBounds(400, 50, 100, 20);
    txtPasswordProf.setBounds(400, 100, 100, 20);
    txtIdProf.setBounds(400, 150, 100, 20);
    txtNomProf.setBounds(400, 200, 100, 20);
    txtPrenomProf.setBounds(400, 250, 100, 20);
    okAjouterProf.setBounds(400, 300, 100, 20);
    pan14.add(lblTitreAjouterProf);
    pan14.add(txtLoginProf);
    pan14.add(txtPasswordProf);
    pan14.add(txtIdProf);
    pan14.add(txtNomProf);
    pan14.add(txtPrenomProf);
    pan14.add(okAjouterProf);
    this.setContentPane(pan14);
    this.setVisible(false);
    
    okAjouterProf.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringAjouterLoginProf = txtLoginProf.getText();
        	String stringAjouterPasswordProf = txtPasswordProf.getText();
        	String stringAjouterIdProf = txtIdProf.getText();
        	String stringAjouterPrenomProf = txtNomProf.getText();
        	String stringAjouterNomProf = txtPrenomProf.getText();
        	int valeurAjouterProf = 0;
        	try {
        		valeurAjouterProf = Integer.parseInt(stringAjouterIdProf); 
	        	if(userController.addStudent(stringLoginArg, stringAjouterLoginProf, valeurAjouterProf, stringAjouterPrenomProf, stringAjouterNomProf, stringAjouterPasswordProf) == true)
	        	{
	        		JOptionPane.showMessageDialog(okAjouterProf, "Prof ajouté");
	        		System.out.println(valeurAjouterProf);
	        		setVisible(false);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(okAjouterProf, "Le prof existe déjà");
	        	}
        	}
        	catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(okAjouterProf, "Il ne s'agit pas d'un nombre...");
        }
     }
  });       
  }       
}