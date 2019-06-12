package view;

import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import timeTableController.TimeTableController;
import userController.UserController;

public class EcranEleve extends JFrame {
	public EcranEleve(String stringLoginArg, String stringPasswordArg, UserController userController, TimeTableController tTController) {
		setSize(1000, 1000);
		setLayout(null);
		EDTPanel edt = new EDTPanel(stringLoginArg, stringPasswordArg, userController, tTController);
		edt.setBounds(150, 50, 1000, 700);
		add(edt);
		JLabel lblTitreProf = new JLabel("ELEVE :"+stringLoginArg);

		/*
		 * Calendar calendar = Calendar.getInstance(); int month =
		 * calendar.get(Calendar.MONTH)+1; int dayOfMonth =
		 * calendar.get(Calendar.DAY_OF_MONTH); JLabel lblTitreEleve = new
		 * JLabel("ELEVE :"); JLabel jour0 = new JLabel(dayOfMonth - 6 + "/" +
		 * month); JLabel jour1 = new JLabel(dayOfMonth - 5 + "/" + month);
		 * JLabel jour2 = new JLabel(dayOfMonth - 4 + "/" + month); JLabel jour3
		 * = new JLabel(dayOfMonth - 3 + "/" + month); JLabel jour4 = new
		 * JLabel(dayOfMonth - 2 + "/" + month); JLabel jour5 = new
		 * JLabel(dayOfMonth - 1 + "/" + month); JLabel jour6 = new
		 * JLabel(dayOfMonth + "/" + month); JLabel jour7 = new
		 * JLabel(dayOfMonth + 1 + "/" + month); JLabel jour8 = new
		 * JLabel(dayOfMonth + 2 + "/" + month); JLabel jour9 = new
		 * JLabel(dayOfMonth + 3 + "/" + month); JLabel jour10 = new
		 * JLabel(dayOfMonth + 4 + "/" + month); JLabel jour11 = new
		 * JLabel(dayOfMonth + 5 + "/" + month); JLabel jour12 = new
		 * JLabel(dayOfMonth + 6 + "/" + month);
		 * 
		 * JLabel huitheures = new JLabel("08H00 "); JLabel huitheuresetdemi =
		 * new JLabel("08H30 "); JLabel neufheures = new JLabel("09H00 ");
		 * JLabel neufheuresetdemi = new JLabel("09H30 "); JLabel dixheures =
		 * new JLabel("10H00 "); JLabel dixheuresetdemi = new JLabel("10H30 ");
		 * JLabel onzeheures = new JLabel("11H00 "); JLabel onzesheuresetdemi =
		 * new JLabel("11H30 "); JLabel midi = new JLabel("12H00 "); JLabel
		 * midietdemi = new JLabel("12H30 "); JLabel treizeheures = new
		 * JLabel("13H00 "); JLabel treizeheurestrente = new JLabel("13H30 ");
		 * JLabel quatorzeheures = new JLabel("14H00 "); JLabel
		 * quatorzesheurestrente = new JLabel("14H30 "); JLabel quinzeheures =
		 * new JLabel("15H00 "); JLabel quinzeheurestrente = new
		 * JLabel("15H30 "); JLabel seizeheures = new JLabel("16H00 "); JLabel
		 * seizeheurestrente = new JLabel("16H30 "); JLabel dixseptheures = new
		 * JLabel("17H00 "); JLabel dixseptheurestrente = new JLabel("17H30 ");
		 * JLabel dixhuitheures = new JLabel("18H00 ");
		 * 
		 * 
		 * 
		 * public EcranEleve(String stringLoginArg, String stringPasswordArg) {
		 * pan2.setLayout(null); this.setTitle("EcranEleve"); this.setSize(1000,
		 * 1000); this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * this.setLocationRelativeTo(null);
		 * 
		 * lblTitreEleve.setBounds(425, 0, 50, 10);
		 * 
		 * if (calendar.get(Calendar.DAY_OF_WEEK) == calendar.MONDAY) {
		 * jour6.setBounds(95, 10, 40, 40); jour7.setBounds(210, 10, 40, 40);
		 * jour8.setBounds(325, 10, 40, 40); jour9.setBounds(440, 10, 40, 40);
		 * jour10.setBounds(555, 10, 40, 40); jour11.setBounds(670, 10, 40, 40);
		 * jour12.setBounds(785, 10, 40, 40);
		 * 
		 * pan2.add(jour6); pan2.add(jour7); pan2.add(jour8); pan2.add(jour9);
		 * pan2.add(jour10); pan2.add(jour11); pan2.add(jour12); } if
		 * (calendar.get(Calendar.DAY_OF_WEEK) == calendar.TUESDAY) {
		 * jour5.setBounds(95, 10, 40, 40); jour6.setBounds(210, 10, 40, 40);
		 * jour7.setBounds(325, 10, 40, 40); jour8.setBounds(440, 10, 40, 40);
		 * jour9.setBounds(555, 10, 40, 40); jour10.setBounds(670, 10, 40, 40);
		 * jour11.setBounds(785, 10, 40, 40);
		 * 
		 * pan2.add(jour5); pan2.add(jour6); pan2.add(jour7); pan2.add(jour8);
		 * pan2.add(jour9); pan2.add(jour10); pan2.add(jour11); } if
		 * (calendar.get(Calendar.DAY_OF_WEEK) == calendar.WEDNESDAY) {
		 * jour4.setBounds(95, 10, 50, 50); jour5.setBounds(210, 10, 40, 40);
		 * jour6.setBounds(325, 10, 40, 40); jour7.setBounds(440, 10, 40, 40);
		 * jour8.setBounds(555, 10, 40, 40); jour9.setBounds(670, 10, 40, 40);
		 * jour10.setBounds(785, 10, 40, 40);
		 * 
		 * pan2.add(jour4); pan2.add(jour5); pan2.add(jour6); pan2.add(jour7);
		 * pan2.add(jour8); pan2.add(jour9); pan2.add(jour10); }
		 * 
		 * if (calendar.get(Calendar.DAY_OF_WEEK) == calendar.THURSDAY) {
		 * jour3.setBounds(95, 10, 40, 40); jour4.setBounds(210, 10, 40, 40);
		 * jour5.setBounds(325, 10, 40, 40); jour6.setBounds(440, 10, 40, 40);
		 * jour7.setBounds(555, 10, 40, 40); jour8.setBounds(670, 10, 40, 40);
		 * jour9.setBounds(785, 10, 40, 40);
		 * 
		 * pan2.add(jour3); pan2.add(jour4); pan2.add(jour5); pan2.add(jour6);
		 * pan2.add(jour7); pan2.add(jour8); pan2.add(jour9); }
		 * 
		 * if (calendar.get(Calendar.DAY_OF_WEEK) == calendar.FRIDAY) {
		 * jour2.setBounds(95, 10, 40, 40); jour3.setBounds(210, 10, 40, 40);
		 * jour4.setBounds(325, 10, 40, 40); jour5.setBounds(440, 10, 40, 40);
		 * jour6.setBounds(555, 10, 40, 40); jour7.setBounds(670, 10, 40, 40);
		 * jour8.setBounds(785, 10, 40, 40);
		 * 
		 * pan2.add(jour2); pan2.add(jour3); pan2.add(jour4); pan2.add(jour5);
		 * pan2.add(jour6); pan2.add(jour7); pan2.add(jour8); }
		 * 
		 * if (calendar.get(Calendar.DAY_OF_WEEK) == calendar.SATURDAY) {
		 * jour1.setBounds(95, 10, 40, 40); jour2.setBounds(210, 10, 40, 40);
		 * jour3.setBounds(325, 10, 40, 40); jour4.setBounds(440, 10, 40, 40);
		 * jour5.setBounds(555, 10, 40, 40); jour6.setBounds(670, 10, 40, 40);
		 * jour7.setBounds(785, 10, 40, 40);
		 * 
		 * pan2.add(jour1); pan2.add(jour2); pan2.add(jour3); pan2.add(jour4);
		 * pan2.add(jour5); pan2.add(jour6); pan2.add(jour7);
		 * 
		 * }
		 * 
		 * if (calendar.get(Calendar.DAY_OF_WEEK) == calendar.SUNDAY) {
		 * jour0.setBounds(95, 10, 40, 40); jour1.setBounds(210, 10, 40, 40);
		 * jour2.setBounds(325, 10, 40, 40); jour3.setBounds(440, 10, 40, 40);
		 * jour4.setBounds(555, 10, 40, 40); jour5.setBounds(670, 10, 40, 40);
		 * jour6.setBounds(785, 10, 40, 40);
		 * 
		 * pan2.add(jour0); pan2.add(jour1); pan2.add(jour2); pan2.add(jour3);
		 * pan2.add(jour4); pan2.add(jour5); pan2.add(jour6);
		 * 
		 * } huitheures.setBounds(10, 30, 40, 40);
		 * huitheuresetdemi.setBounds(10, 60, 40, 40); neufheures.setBounds(10,
		 * 90, 40, 40); neufheuresetdemi.setBounds(10, 120, 40, 40);
		 * dixheures.setBounds(10, 150, 40, 40); dixheuresetdemi.setBounds(10,
		 * 180, 40, 40); onzeheures.setBounds(10, 210, 40, 40);
		 * onzesheuresetdemi.setBounds(10, 240, 40, 40); midi.setBounds(10, 270,
		 * 40, 40); midietdemi.setBounds(10, 300, 40, 40);
		 * treizeheures.setBounds(10, 330, 40, 40);
		 * treizeheurestrente.setBounds(10, 360, 40, 40);
		 * quatorzeheures.setBounds(10, 390, 40, 40);
		 * quatorzesheurestrente.setBounds(10, 420, 40, 40);
		 * quinzeheures.setBounds(10, 450, 40, 40);
		 * quinzeheurestrente.setBounds(10, 480, 40, 40);
		 * seizeheures.setBounds(10, 510, 40, 40);
		 * seizeheurestrente.setBounds(10, 540, 40, 40);
		 * dixseptheures.setBounds(10, 570, 40, 40);
		 * dixseptheurestrente.setBounds(10, 600, 40, 40);
		 * dixhuitheures.setBounds(10, 630, 40, 40);
		 * 
		 * edt.setBounds(50, 50, 1000, 1000); pan2.add(edt);
		 * 
		 * pan2.add(huitheures); pan2.add(huitheuresetdemi);
		 * pan2.add(neufheures); pan2.add(neufheuresetdemi);
		 * pan2.add(dixheures); pan2.add(dixheuresetdemi); pan2.add(onzeheures);
		 * pan2.add(onzesheuresetdemi); pan2.add(midi); pan2.add(midietdemi);
		 * pan2.add(treizeheures); pan2.add(treizeheurestrente);
		 * pan2.add(quatorzeheures); pan2.add(quatorzesheurestrente);
		 * pan2.add(quinzeheures); pan2.add(quinzeheurestrente);
		 * pan2.add(seizeheures); pan2.add(seizeheurestrente);
		 * pan2.add(dixseptheures); pan2.add(dixseptheurestrente);
		 * pan2.add(dixhuitheures);
		 */

		setVisible(true);
	}
}