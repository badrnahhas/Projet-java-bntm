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

public class EcranAjouterSalle extends JFrame{
  JLabel lblTitreAjouterSalle = new JLabel("AJOUTER SALLE :");
  private JTextField txtIdSalle = new JTextField();
  private JTextField txtCapaciteSalle = new JTextField();
  private JPanel pan10 = new JPanel();
  private JButton okAjouterSalle = new JButton ("OK");
  
  public EcranAjouterSalle(String stringLoginArg, TimeTableController tTControlleruserController){
	pan10.setLayout(null);
    this.setTitle("EcranAjouterSalle");
    this.setSize(900, 500);
    this.setLocationRelativeTo(null);
    lblTitreAjouterSalle.setBounds(400, 0, 100, 20);
    txtIdSalle.setBounds(400, 50, 100, 20);
    txtCapaciteSalle.setBounds(400, 100, 100, 20);
    okAjouterSalle.setBounds(400, 150, 100, 20);
    pan10.add(lblTitreAjouterSalle);
    pan10.add(txtIdSalle);
    pan10.add(txtCapaciteSalle);
    pan10.add(okAjouterSalle);
    this.setContentPane(pan10);
    this.setVisible(false);
    
    okAjouterSalle.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event){
        	String stringAjouterSalle = txtIdSalle.getText(); 
        	String stringAjouterSalleCapacite = txtCapaciteSalle.getText(); 
        	
        	int valeurAjouterSalle = 0;
        	int valeurAjouterSalleCapacite = 0;
        	try {
        		valeurAjouterSalle = Integer.parseInt(stringAjouterSalle);
        		valeurAjouterSalleCapacite = Integer.parseInt(stringAjouterSalleCapacite); 
	        	if(tTControlleruserController.addRoom(valeurAjouterSalle, valeurAjouterSalleCapacite) == true)
	        	{
	        		JOptionPane.showMessageDialog(okAjouterSalle, "Salle ajoutés");
	        		System.out.println(valeurAjouterSalle);
	        		setVisible(false);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(okAjouterSalle, "La salle existe déjà");
	        	}
        	}
        	catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(okAjouterSalle, "Il ne s'agit pas d'un nombre...");
        }
     }
  });       
}
}