package timeTableModel;

import java.util.Hashtable;

public class Timetable {
	
	private int id_timetable;
	private Hashtable<Integer, Reservation> Reservations = new Hashtable<Integer, Reservation>();
	
	public Timetable (int aId_timetable) {
		setId_timetable(aId_timetable);
	}
	public int getId_timetable() {
		return this.id_timetable;
	}

	public void setId_timetable(int aId_timetable) {
		this.id_timetable = aId_timetable;
	}

	public Reservation getaReservation (int Key) {
		return Reservations.get(Key);
	}

	public void setReservations(Reservation aReservation) {
		Reservations.put(aReservation.getNumber_of_reservation(), aReservation) ;
	}
	
	public void removeaReservation(int Key) {
		Reservations.remove(Key) ;
	}
	
}