package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import timeTableController.TimeTableController;
import userController.UserController;

public class EDTPanel extends Panel {
	private UserController userController;
	private TimeTableController timeTableController;
	int resx = 800;
	int resy = 600;
	int offset = 100;
	int taillecasex = (resx - 2 * offset) / 7;
	int taillecasey = (resy - 2 * offset) / 20;
	private Date firstDayOfWeek;
	private Date lastDayOfWeek;
	private int weekOfTheYear;
	private int groupId;
	Calendar calendar = Calendar.getInstance();

	public EDTPanel(String stringLogin, String stringPassword, UserController uController,
			TimeTableController tTController) {
		setSize(resx, resy);
		userController = uController;
		timeTableController = tTController;
		setLayout(null);
		this.weekOfTheYear = calendar.get(Calendar.WEEK_OF_YEAR);

		// Creer les components
		JButton previous = new JButton("Précédent");
		JButton next = new JButton("Suivant");
		JButton rafraichir = new JButton("Rafraichir");

		// Ajouter les components

		next.setBounds(700, 0, 100, 30);
		previous.setBounds(0, 0, 100, 30);

		add(next);
		add(previous);

		// Ajout conditionel de la partie prof

		if (userController.getUserClass(stringLogin, stringPassword) == "Teacher") {
			JComboBox<String> listegroupe = new JComboBox<String>(uController.groupsIdToString());
			groupId = Integer.parseInt((listegroupe.getSelectedItem().toString()));

			listegroupe.setBounds(250, 0, 60, 30);
			add(listegroupe);

			rafraichir.setBounds(350, 0, 100, 30);
			add(rafraichir);

			rafraichir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					groupId = Integer.parseInt((listegroupe.getSelectedItem().toString()));

					validate();
					repaint();

				}
			});
		} else
			groupId = userController.getStudentGroup(stringLogin);

		// Ajouter les ActionListeners
		//Les boutons next et previous vont parcourir les semaines de l'année
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Si on arrive a la derniere semaine de l'annee on passe a l'annee suivante
				if (weekOfTheYear == calendar.getWeeksInWeekYear()) {
					calendar.set(calendar.get(Calendar.YEAR) + 1, 0, 1);
					weekOfTheYear = 1;
					calendar.set(Calendar.WEEK_OF_YEAR, weekOfTheYear);
				}else {
					weekOfTheYear = weekOfTheYear + 1;
					calendar.set(Calendar.WEEK_OF_YEAR, weekOfTheYear);
				}
				repaint();
			}
		});

		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Si on arrive a la premiere semaine de l'annee on passe a l'annee precedente
				if (weekOfTheYear == 1) {
					calendar.set(calendar.get(Calendar.YEAR) -1 , 11, 1);
					weekOfTheYear = calendar.getWeeksInWeekYear();
					calendar.set(Calendar.WEEK_OF_YEAR, weekOfTheYear);
				}else {
					weekOfTheYear = weekOfTheYear - 1;
					calendar.set(Calendar.WEEK_OF_YEAR, weekOfTheYear);
				}
				repaint();
			}
		});

	}
	//Methode qui dessine l'emploi du temps
	public void paint(Graphics g) {
		super.paintComponents(g);

		// Ecrire les dates sur l'emploi du temps
		//On impose au calendrier la date de chaque jour de la semaine afin qu'on puisse les afficher sur l'EDT
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		firstDayOfWeek = calendar.getTime();
		String date1 = formatter.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		String date2 = formatter.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		String date3 = formatter.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		String date4 = formatter.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		String date5 = formatter.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String date6 = formatter.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		lastDayOfWeek = calendar.getTime();
		String date7 = formatter.format(calendar.getTime());
		g.drawString(date1, offset + (taillecasex / 3), offset - 10);
		g.drawString(date2, offset + ((4 * taillecasex) / 3), offset - 10);
		g.drawString(date3, offset + ((7 * taillecasex) / 3), offset - 10);
		g.drawString(date4, offset + ((10 * taillecasex) / 3), offset - 10);
		g.drawString(date5, offset + ((13 * taillecasex) / 3), offset - 10);
		g.drawString(date6, offset + ((16 * taillecasex) / 3), offset - 10);
		g.drawString(date7, offset + ((19 * taillecasex) / 3), offset - 10);

		// Ecrire les heures

		g.drawString("08H00", offset - 40, offset + 5);
		g.drawString("08H30", offset - 40, (offset + 5) + taillecasey);
		g.drawString("09H00", offset - 40, (offset + 5) + 2 * taillecasey);
		g.drawString("09H30", offset - 40, (offset + 5) + 3 * taillecasey);
		g.drawString("10H00", offset - 40, (offset + 5) + 4 * taillecasey);
		g.drawString("10H30", offset - 40, (offset + 5) + 5 * taillecasey);
		g.drawString("11H00", offset - 40, (offset + 5) + 6 * taillecasey);
		g.drawString("11H30", offset - 40, (offset + 5) + 7 * taillecasey);
		g.drawString("12H00", offset - 40, (offset + 5) + 8 * taillecasey);
		g.drawString("12H30", offset - 40, (offset + 5) + 9 * taillecasey);
		g.drawString("13H00", offset - 40, (offset + 5) + 10 * taillecasey);
		g.drawString("13H30", offset - 40, (offset + 5) + 11 * taillecasey);
		g.drawString("14H00", offset - 40, (offset + 5) + 12 * taillecasey);
		g.drawString("14H30", offset - 40, (offset + 5) + 13 * taillecasey);
		g.drawString("15H00", offset - 40, (offset + 5) + 14 * taillecasey);
		g.drawString("15H30", offset - 40, (offset + 5) + 15 * taillecasey);
		g.drawString("16H00", offset - 40, (offset + 5) + 16 * taillecasey);
		g.drawString("16H30", offset - 40, (offset + 5) + 17 * taillecasey);
		g.drawString("17H00", offset - 40, (offset + 5) + 18 * taillecasey);
		g.drawString("17H30", offset - 40, (offset + 5) + 19 * taillecasey);
		g.drawString("18H00", offset - 40, (offset + 5) + 20 * taillecasey);

		// Dessiner les colonnes

		g.drawLine(offset, offset, offset, resy - offset);
		for (int i = 1; i < 7; i++) {
			g.drawLine((taillecasex * i) + offset, offset, (taillecasex * i) + offset, resy - offset);
		}
		g.drawLine(resx - offset, offset, resx - offset, resy - offset);

		// Dessiner les lignes
		g.drawLine(offset, offset, resx - offset, offset);
		for (int i = 1; i < 20; i++) {
			g.drawLine(offset, (taillecasey * i) + offset, resx - offset, (taillecasey * i) + offset);
		}
		g.drawLine(offset, resy - offset, resx - offset, resy - offset);

		//Recuperer les bookings de la BDD
		
		Hashtable<Integer, Date> dateBegin = new Hashtable<Integer, Date>();
		Hashtable<Integer, Date> dateEnd = new Hashtable<Integer, Date>();

		timeTableController.getBookingsDate(groupId, dateBegin, dateEnd);
		
		// Dessiner les bookings
		
		for (int i = 0; i < timeTableController.getBookingsMaxId(groupId); i++)
			if (!dateBegin.isEmpty()) {
				// Verification de la semaine
				System.out.println(firstDayOfWeek);
				System.out.println(lastDayOfWeek);
				//On verifie que la date de reservation est comprise dans la semaine actuelle
				if ((dateBegin.get(i).before(firstDayOfWeek) == true)
						|| (dateBegin.get(i).after(lastDayOfWeek) == true)) {
				} else {
					// On remplit les reservations
					//On convertit les jours et les heures en coordonnees sur l'EDT
					int l = dateBegin.get(i).getDay();
					int j = (dateBegin.get(i).getHours() * 2) + ((dateBegin.get(i).getMinutes()) / 30);
					//On calcule la longueur de la reservation en coordonnee
					int k = ((dateEnd.get(i).getHours() * 2) + ((dateEnd.get(i).getMinutes()) / 30))
							- ((dateBegin.get(i).getHours() * 2) + ((dateBegin.get(i).getMinutes()) / 30));
					//Le premier jour de la semaine de la classe Calendar correspond a dimanche, on fait donc un decallage des valeurs
					if (l == 0)
						l = 7;
					else
						l--;
					g.fillRect((taillecasex * l) + offset, (taillecasey * (j - 16)) + offset, taillecasex,
							taillecasey * k);
					

				}
			}
		g.setColor(Color.BLACK);
		setVisible(true);
	}

}
