package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Hashtable;

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
		setTitle("Choix du cours à supprimer");
		setLocationRelativeTo(null);
		JPanel pan = new JPanel();

		JComboBox<String> listedecours = new JComboBox<String>(tTController.booksIdToString(groupechoisi));
		JButton suppBouton = new JButton("Choisir le cours à supprimer");
		
		
		//Placer les elements 
		listedecours.setBounds(400, 200, 100, 100);
		suppBouton.setBounds(400, 500, 100, 100);
		
				
		//Ajouter les elements
		pan.add(listedecours);
		pan.add(suppBouton);
		setContentPane(pan);
		

		//Ajouter ActionListener
		
		listedecours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Hashtable<Integer, Date> dateBegin = new Hashtable<Integer, Date>();
				Hashtable<Integer, Date> dateEnd = new Hashtable<Integer, Date>();
				tTController.getBookingsDate(groupechoisi, dateBegin, dateEnd);

				JOptionPane.showMessageDialog(listedecours, 
						"Room : "+tTController.getRoom(groupechoisi, Integer.parseInt(listedecours.getSelectedItem().toString()))+"\n"+
						"Professeur : "+tTController.getTeacherLogin(groupechoisi, Integer.parseInt(listedecours.getSelectedItem().toString()))+"\n"+
						"Date de debut : "+dateBegin.get(Integer.parseInt(listedecours.getSelectedItem().toString()))+"\n"+
						"Date de fin : "+dateEnd.get(Integer.parseInt(listedecours.getSelectedItem().toString())),																										
						"Information Booking",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		suppBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(tTController.booksIdToString(groupechoisi).length!=0){
				int bookId = Integer.parseInt(listedecours.getSelectedItem().toString());
				if((tTController.removeBook(groupechoisi, bookId)==true)) {
						JOptionPane.showMessageDialog(suppBouton, "reservation supprimé");
						dispose();
						
				}
			else JOptionPane.showMessageDialog(suppBouton, "erreur");
				}
		}});
		
		setVisible(true);
	}
}

	
