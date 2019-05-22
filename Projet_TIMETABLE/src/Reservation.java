import java.util.Date;

public class Reservation {
	private int number_of_reservations;
	private String teacher_login;
	private Date start_date;
	private Date end_date;
	public Classroom _unnamed_Classroom_;

	public int getNumber_of_reservations() {
		return this.number_of_reservations;
	}

	public void setNumber_of_reservations(int aNumber_of_reservations) {
		this.number_of_reservations = aNumber_of_reservations;
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
}