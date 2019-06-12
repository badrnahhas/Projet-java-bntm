package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import timeTableController.TimeTableController;
import userController.UserController;

public class EcranSupprimerCours extends JFrame {
	public EcranSupprimerCours(UserController userController, TimeTableController tTController, int groupechoisi) {
		setSize(800, 600);
		setLocationRelativeTo(null);
		JPanel pan = new JPanel();
		JLabel lblTitreSupprimerGroupe = new JLabel("SUPPRIMER GROUPE :");
		
		JComboBox<String> listedecours = new JComboBox<String>(tTController.booksIdToString(groupechoisi));
		JButton suppBouton = new JButton("Supprimer");
		
		
		//Placer les elements 
		
		lblTitreSupprimerGroupe.setBounds(400, 0, 100, 20);
		listedecours.setBounds(400, 200, 100, 100);
		suppBouton.setBounds(400, 500, 100, 100);
		
		
		
		//Ajouter les elements
		
		pan.add(lblTitreSupprimerGroupe);
		pan.add(listedecours);
		pan.add(suppBouton);
		setContentPane(pan);
		
		
		
		
		
		
		//Ajouter ActionListener
		
		suppBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(tTController.booksIdToString(groupechoisi).length!=0){
				int bookId = Integer.parseInt(listedecours.getSelectedItem().toString());
				if((tTController.removeBook(groupechoisi, bookId)==true)) {
						JOptionPane.showMessageDialog(suppBouton, "reservation supprimé");
				}
			else JOptionPane.showMessageDialog(suppBouton, "erreur");
				}
		}});
		
		setVisible(true);
	}
}

	
