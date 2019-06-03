package timeTableModel;

import java.util.Hashtable;

public class Timetable {
	
	private int id_timetable;
	private Hashtable<Integer, Reservation> Reservations = new Hashtable<Integer, Reservation>();
	
	public Timetable (int aId_timetable) {
		setId_timetable(aId_timetable);
	}

	public Reservation getaReservation (int reservation_number) {
		return getReservations().get(reservation_number);
	}

	public void setReservations(Reservation aReservation) {
		getReservations().put(aReservation.getReservation_number(), aReservation) ;
	}
	
	public void removeaReservation(int reservation_number) {
		getReservations().remove(reservation_number) ;
	}
	
	public int getId_timetable() {
		return this.id_timetable;
	}

	public void setId_timetable(int aId_timetable) {
		this.id_timetable = aId_timetable;
	}
	
	public Hashtable<Integer, Reservation> getReservations() {
		return this.Reservations;
	}
	
}