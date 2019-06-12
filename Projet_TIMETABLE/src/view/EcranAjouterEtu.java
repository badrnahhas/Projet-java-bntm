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

public class EcranAjouterEtu extends JFrame{
  JLabel lblTitreAjouterEtu = new JLabel("AJOUTER ETUDIANT :");
  private JTextField txtLoginEtu = new JTextField("Login");
  private JTextField txtPasswordEtu = new JTextField("Password");
  private JTextField txtIdEtu = new JTextField("ID étudiant");
  private JTextField txtNomEtu = new JTextField("Nom");
  private JTextField txtPrenomEtu = new JTextField("Prénom");
  private JButton okAjouterEtu = new JButton ("OK");
  private JPanel pan13 = new JPanel();
  
  public EcranAjouterEtu(String stringLoginArg, UserController userController){
	pan13.setLayout(null);
    this.setTitle("EcranAjouterEtu");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreAjouterEtu.setBounds(400, 0, 100, 20);
    txtLoginEtu.setBounds(400, 50, 100, 20);
    txtPasswordEtu.setBounds(400, 100, 100, 20);
    txtIdEtu.setBounds(400, 150, 100, 20);
    txtNomEtu.setBounds(400, 200, 100, 20);
    txtPrenomEtu.setBounds(400, 250, 100, 20);
    okAjouterEtu.setBounds(400, 300, 100, 20);
    pan13.add(lblTitreAjouterEtu);
    pan13.add(txtLoginEtu);
    pan13.add(txtPasswordEtu);
    pan13.add(txtIdEtu);
    pan13.add(txtNomEtu);
    pan13.add(txtPrenomEtu);
    pan13.add(okAjouterEtu);
    this.setContentPane(pan13);
    this.setVisible(false);
    
    okAjouterEtu.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringAjouterLoginEtu = txtLoginEtu.getText();
        	String stringAjouterPasswordEtu = txtPasswordEtu.getText();
        	String stringAjouterIdEtu = txtIdEtu.getText();
        	String stringAjouterPrenomEtu = txtNomEtu.getText();
        	String stringAjouterNomEtu = txtPrenomEtu.getText();
        	int valeurAjouterEtu = 0;
        	try {
        		valeurAjouterEtu = Integer.parseInt(stringAjouterIdEtu); 
	        	if(userController.addStudent(stringLoginArg, stringAjouterLoginEtu, valeurAjouterEtu, stringAjouterPrenomEtu, stringAjouterNomEtu, stringAjouterPasswordEtu) == true)
	        	{
	        		
	        		JOptionPane.showMessageDialog(okAjouterEtu, "étudiant ajouté");
	        		System.out.println(valeurAjouterEtu);
	        		setVisible(false);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(okAjouterEtu, "L'étudiant existe déjà");
	        	}
        	}
        	catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(okAjouterEtu, "Il ne s'agit pas d'un nombre...");
        }
     }
  });       
  }       
}