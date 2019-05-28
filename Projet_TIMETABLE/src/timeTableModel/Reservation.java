package timeTableModel;
import java.util.Date;

public class Reservation {
	
	private int reservation_number;
	private String teacher_login;
	private Date start_date;
	private Date end_date;
	private Classroom classroom_reserved;
	
	public Reservation (int aReservation_number, String aTeacher_login, Date aStart_date, Date aEnd_date, Classroom aClassroom_reserved) {
		setReservation_number(aReservation_number);
		setTeacher_login(aTeacher_login);
		setStart_date(aStart_date);
		setEnd_date(aEnd_date);
		setClassroom_reserved(aClassroom_reserved);
	}

	public int getReservation_number() {
		return this.reservation_number;
	}

	public void setReservation_number(int aNumber_of_reservation) {
		this.reservation_number = aNumber_of_reservation;
	}

	public String getTeacher_login() {
		return this.teacher_login;
	}

	public void setTeacher_login(String aTeacher_login) {
		this.teacher_login = aTeacher_login;
	}

	public Date getStart_date() {
		return this.start_date;
	}

	public void setStart_date(Date aStart_date) {
		this.start_date = aStart_date;
	}

	public Date getEnd_date() {
		return this.end_date;
	}

	public void setEnd_date(Date aEnd_date) {
		this.end_date = aEnd_date;
	}

	public Classroom getClassroom_reserved() {
		return classroom_reserved;
	}

	public void setClassroom_reserved(Classroom aClassroom_reserved) {
		this.classroom_reserved = aClassroom_reserved;
	}
	
}