package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import timeTableController.TimeTableController;
import userController.UserController;

public class EcranAjouterCours extends JFrame {
	String[] heureStrings = { "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
			"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00" };

	JComboBox heuredebut = new JComboBox(heureStrings);
	JComboBox heurefin = new JComboBox(heureStrings);
	JLabel choixgroupe = new JLabel("Choisissez le groupe:");
	JLabel choixdate = new JLabel("Choisissez une date:");
	JLabel choixsalle = new JLabel("Choisissez une salle:");
	JLabel choixheuredebut = new JLabel("Choisissez l'heure de début:");
	JLabel choixheurefin = new JLabel("Choisissez une heure de fin:");
	JButton boutonAdd = new JButton("Ajouter cours");
	private JPanel pan6 = new JPanel();

	public EcranAjouterCours(String stringLoginArg, UserController userController, TimeTableController tTController) {

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		String[] groupString = userController.groupsIdToString();
		String[] salleString = tTController.roomsIdToString();
		JComboBox<String> listegroupe = new JComboBox<String>(groupString);
		JComboBox<String> listesalle = new JComboBox<String>(salleString);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		setTitle("EcranAjouterCours");
		setSize(900, 500);
		setLocationRelativeTo(null);
		choixdate.setBounds(225, 10, 200, 100);
		choixgroupe.setBounds(225, 280, 200, 100);
		choixsalle.setBounds(625, 280, 200, 100);
		listegroupe.setBounds(425, 320, 100, 20);
		listesalle.setBounds(625, 320, 100, 20);
		datePicker.setBounds(425, 50, 150, 50);
		choixheuredebut.setBounds(225, 115, 200, 100);
		heuredebut.setBounds(425, 150, 150, 25);
		choixheurefin.setBounds(225, 215, 200, 100);
		heurefin.setBounds(425, 250, 150, 25);
		boutonAdd.setBounds(365, 400, 120, 40);
		pan6.setLayout(null);
		pan6.add(choixdate);
		pan6.add(choixgroupe);
		pan6.add(choixsalle);
		pan6.add(listegroupe);
		pan6.add(listesalle);

		pan6.add(datePicker);
		pan6.add(choixheuredebut);
		pan6.add(heuredebut);
		pan6.add(choixheurefin);
		pan6.add(heurefin);
		pan6.add(boutonAdd);
		this.setContentPane(pan6);
		this.setVisible(true);

		boutonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy HH:mm");
				String jour = String.valueOf(datePicker.getModel().getDay());
				String mois = String.valueOf(datePicker.getModel().getMonth() + 1);
				String an = String.valueOf(datePicker.getModel().getYear());
				String hourbegin = heuredebut.getSelectedItem().toString();
				String hourfinish = heurefin.getSelectedItem().toString();
				String datecompletedebut = jour + "/" + mois + "/" + an + " " + hourbegin;
				String datecompletefin = jour + "/" + mois + "/" + an + " " + hourfinish;
				Date startdate = new Date();
				Date enddate = new Date();
				try {
					startdate = formatter.parse(datecompletedebut);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					enddate = formatter.parse(datecompletefin);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println(startdate.toLocaleString());
				System.out.println((listegroupe.getSelectedItem().toString()));
				int groupselected = Integer.parseInt((listegroupe.getSelectedItem().toString()));
				int bookingid = tTController.getBookingsMaxId(groupselected);
				int roomid = Integer.parseInt(listesalle.getSelectedItem().toString());
				if (tTController.addBooking(groupselected, bookingid, stringLoginArg,startdate, enddate, roomid) == true) {

					JOptionPane.showMessageDialog(boutonAdd, "reservation ajouté");
				}
				else JOptionPane.showMessageDialog(boutonAdd, "erreur");

			}
		});

	}
}
