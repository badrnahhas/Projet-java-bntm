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

public class EcranAjouterAdmin extends JFrame{
  JLabel lblTitreAjouterAdmin = new JLabel("AJOUTER ADMINISTRATEUR :");
  private JTextField txtLoginAdmin = new JTextField("Login");
  private JTextField txtPasswordAdmin = new JTextField("Password");
  private JTextField txtIdAdmin = new JTextField("ID admin");
  private JTextField txtNomAdmin = new JTextField("Nom");
  private JTextField txtPrenomAdmin = new JTextField("Prénom");
  private JButton okAjouterAdmin = new JButton ("OK");
  private JPanel pan15 = new JPanel();
  
  public EcranAjouterAdmin(String stringLoginArg, UserController userController){
	pan15.setLayout(null);
    this.setTitle("EcranAjouterAdmin");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreAjouterAdmin.setBounds(400, 0, 100, 20);
    txtLoginAdmin.setBounds(400, 50, 100, 20);
    txtPasswordAdmin.setBounds(400, 100, 100, 20);
    txtIdAdmin.setBounds(400, 150, 100, 20);
    txtNomAdmin.setBounds(400, 200, 100, 20);
    txtPrenomAdmin.setBounds(400, 250, 100, 20);
    okAjouterAdmin.setBounds(400, 300, 100, 20);
    pan15.add(lblTitreAjouterAdmin);
    pan15.add(txtLoginAdmin);
    pan15.add(txtPasswordAdmin);
    pan15.add(txtIdAdmin);
    pan15.add(txtNomAdmin);
    pan15.add(txtPrenomAdmin);
    pan15.add(okAjouterAdmin);
    this.setContentPane(pan15);
    this.setVisible(false);
    
    okAjouterAdmin.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringAjouterLoginAdmin = txtLoginAdmin.getText();
        	String stringAjouterPasswordAdmin = txtPasswordAdmin.getText();
        	String stringAjouterIdAdmin = txtIdAdmin.getText();
        	String stringAjouterPrenomAdmin = txtNomAdmin.getText();
        	String stringAjouterNomAdmin = txtPrenomAdmin.getText();
        	int valeurAjouterAdmin = 0;
        	try {
        		valeurAjouterAdmin = Integer.parseInt(stringAjouterIdAdmin); 
	        	if(userController.addStudent(stringLoginArg, stringAjouterLoginAdmin, valeurAjouterAdmin, stringAjouterPrenomAdmin, stringAjouterNomAdmin, stringAjouterPasswordAdmin) == true)
	        	{
	        		JOptionPane.showMessageDialog(okAjouterAdmin, "Administrateur ajouté");
	        		System.out.println(valeurAjouterAdmin);
	        		setVisible(false);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(okAjouterAdmin, "L'administrateur existe déjà");
	        	}
        	}
        	catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(okAjouterAdmin, "Il ne s'agit pas d'un nombre...");
        }
     }
  });
  }       
}