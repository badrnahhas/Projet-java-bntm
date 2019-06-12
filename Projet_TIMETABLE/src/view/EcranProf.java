package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import timeTableController.TimeTableController;
import userController.UserController;

public class EcranProf extends JFrame {
	private JPanel pan3 = new JPanel();
	private JButton boutonAjoutCours = new JButton("Ajouter un cours :'(");
	private JButton boutonSupprCours = new JButton("Supprimer un cours :D");
	public EcranProf(String stringLoginArg, String stringPasswordArg, UserController userController, TimeTableController tTController) {
		
		
		pan3.setLayout(null);
		JLabel lblTitreProf = new JLabel("PROF :"+stringLoginArg);
		this.setTitle("EcranProf");
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		lblTitreProf.setBounds(425, 0, 100, 10);
		boutonAjoutCours.setBounds(200, 15, 100, 25);
		boutonSupprCours.setBounds(600, 15, 100, 25);
		EDTPanel edtpanel = new EDTPanel(stringLoginArg, stringPasswordArg, userController, tTController); 
		edtpanel.setBounds(100,100,900,800);
		pan3.add(boutonAjoutCours);
		pan3.add(boutonSupprCours);
		pan3.add(lblTitreProf);
		pan3.add(edtpanel);
		this.setContentPane(pan3);
		this.setVisible(false);
		repaint();

		boutonAjoutCours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EcranAjouterCours ecranAjouterCours = new EcranAjouterCours(stringLoginArg, userController, tTController);
				ecranAjouterCours.setVisible(true);
			}
		});
		boutonSupprCours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				EcranChoisirGroupeSupp ecranSupprimerCours = new EcranChoisirGroupeSupp(userController, tTController);
				ecranSupprimerCours.setVisible(true);
			}
		});

	}

}