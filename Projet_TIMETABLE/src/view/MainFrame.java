package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import timeTableController.TimeTableController;
import userController.UserController;

public class MainFrame extends JFrame{
  private JPanel pan = new JPanel();
  JLabel lblLogin = new JLabel("login :");
  private JTextField txtVotreLogin = new JTextField();
  JLabel lblPassword = new JLabel("Password :");
  private JPasswordField passwordField = new JPasswordField();
  private JButton bouton = new JButton("Connexion");
  
  public MainFrame(UserController userController, TimeTableController tTControlleruserController){
	pan.setLayout(null);
    this.setTitle("Timetable app v.5.2 - Badreddine NAHHAS - Thomas BELAIR - Francois MACKENZIE - Nicolas TOUSCH");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    lblLogin.setBounds(50, 50, 150, 25);
    txtVotreLogin.setBounds(200, 50, 150, 25);
    lblPassword.setBounds(500, 50, 150, 25);
    passwordField.setBounds(700, 50, 150, 25);
    bouton.setBounds(425, 225, 50, 50);
    pan.add(lblLogin);
    pan.add(txtVotreLogin);
    pan.add(lblPassword);
    pan.add(passwordField);
    pan.add(bouton);
    this.setContentPane(pan);
    this.setVisible(true);
    
    bouton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringLogin = txtVotreLogin.getText(); 
        	String stringPassword = String.valueOf(passwordField.getPassword());
        	System.out.println(stringLogin);
      	    System.out.println(stringPassword);
        	if (userController.getUserClass(stringLogin, stringPassword) == "Student") {
				JOptionPane.showMessageDialog(bouton, "Connecté en tant qu'étudiant");
				setVisible(false);
				EcranEleve ecraneleve = new EcranEleve(stringLogin, stringPassword, userController, tTControlleruserController);
				ecraneleve.setVisible(true);
			}

			else if (userController.getUserClass(stringLogin, stringPassword) == "Teacher") {
				JOptionPane.showMessageDialog(bouton, "Connecté en tant que professeur");
				setVisible(false);
				EcranProf ecranprof = new EcranProf(stringLogin, stringPassword, userController, tTControlleruserController);
				ecranprof.setVisible(true);
				
			} else if (userController.getUserClass(stringLogin, stringPassword) == "Administrator") {
				JOptionPane.showMessageDialog(bouton, "Connecté en tant que administrateur");
				setVisible(false);
				EcranAdmin ecranAdmin = new EcranAdmin(stringLogin, stringPassword, userController, tTControlleruserController);
				ecranAdmin.setVisible(true);
				
			} else {

				JOptionPane.showMessageDialog(bouton, "Mauvais identifiant ou mot de passe!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		};
	});
  }
}