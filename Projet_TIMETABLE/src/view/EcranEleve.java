package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import timeTableController.TimeTableController;
import userController.UserController;

public class EcranEleve extends JFrame {
	
	private JButton boutonLogout = new JButton("Déconnexion");
	
	public EcranEleve(String stringLoginArg, String stringPasswordArg, UserController userController, TimeTableController tTController) {
		setSize(1000, 1000);
		setLayout(null);
		EDTPanel edt = new EDTPanel(stringLoginArg, stringPasswordArg, userController, tTController);
		edt.setBounds(150, 50, 1000, 700);
		boutonLogout.setBounds(500, 0, 100, 30);
		add(edt);
		add(boutonLogout);
		JLabel lblTitreProf = new JLabel("ELEVE :"+stringLoginArg);
		
		boutonLogout.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent event){
	        	MainFrame mainframe = new MainFrame(userController, tTController);
	        	dispose();
	        	mainframe.setVisible(true);
	        	}
		});

		setVisible(true);
	}
}