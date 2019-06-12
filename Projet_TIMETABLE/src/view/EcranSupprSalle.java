package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import timeTableController.TimeTableController;

public class EcranSupprSalle extends JFrame{
  JLabel lblTitreSupprSalle = new JLabel("SUPPRIMER SALLE :");
  private JTextField txtIdSupprSalle = new JTextField();
  private JPanel pan11 = new JPanel();
  private JButton okSupprSalle = new JButton ("OK");
  
  public EcranSupprSalle(TimeTableController tTControlleruserController){
	pan11.setLayout(null);
    this.setTitle("EcranSupprSalle");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreSupprSalle.setBounds(400, 0, 100, 20);
    txtIdSupprSalle.setBounds(400, 50, 100, 20);
    okSupprSalle.setBounds(400, 100, 100, 20);
    pan11.add(lblTitreSupprSalle);
    pan11.add(txtIdSupprSalle);
    pan11.add(okSupprSalle);
    this.setContentPane(pan11);
    this.setVisible(false);
    
    okSupprSalle.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringSupprSalle = txtIdSupprSalle.getText(); 
        	int valeurSupprSalle = 0;
        	try {
        		valeurSupprSalle = Integer.parseInt(stringSupprSalle); 
	        	if(tTControlleruserController.removeRoom(valeurSupprSalle) == true)
	        	{
	        		JOptionPane.showMessageDialog(okSupprSalle, "Groupe supprimé");
	        		System.out.println(valeurSupprSalle);
	        		setVisible(false);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(okSupprSalle, "Le groupe n'existe pas");
	        	}
        	}
        	catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(okSupprSalle, "Il ne s'agit pas d'un nombre...");
        }
     }
  });
  }       
}