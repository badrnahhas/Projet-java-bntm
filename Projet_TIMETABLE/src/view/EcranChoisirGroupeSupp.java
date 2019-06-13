package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import timeTableController.TimeTableController;
import userController.UserController;

public class EcranChoisirGroupeSupp extends JFrame {
	public EcranChoisirGroupeSupp(UserController userController, TimeTableController tTController) {
		setSize(800, 600);
		setTitle("Choix du groupe");
		setLocationRelativeTo(null);
		JPanel pan = new JPanel();
		JComboBox<String> listedegroupe = new JComboBox<String>(userController.groupsIdToString());
		JButton choisir = new JButton("Choisir le groupe dont on souhaite supprimer un cours"); 
		listedegroupe.setBounds(400, 200, 100, 100);
		choisir.setBounds(400,400,100,100);
		pan.add(listedegroupe);
		pan.add(choisir);
		setContentPane(pan);
		setVisible(true);
		
		
		choisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int groupId = Integer.parseInt((listedegroupe.getSelectedItem().toString()));
				EcranSupprimerCours ecransupprimergroupe = new EcranSupprimerCours(userController, tTController, groupId);
				dispose();

		}});
		
	}
}
