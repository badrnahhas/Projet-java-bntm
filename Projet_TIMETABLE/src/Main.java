import userModel.Group;
import userModel.Student;
/*import timeTableModel.Classroom;
import timeTableModel.Reservation;
import timeTableModel.Timetable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;*/



public class Main {

	public static void main(String[] args) {
		
		Student S1 = new Student("login2", "motdepasse3", "Badreddine", "NAHHAS", 2695, 0);
		Group G1 = new Group(1);
		//tests sur le group
		System.out.println("Dans ce groupe il y'a : " + G1.getNumber_of_students() + " étudiants");
		System.out.println("L'id_group de l'étudiant est : " + S1.getId_group() );
		G1.setStudents(S1);
		System.out.println("L'étudiant s'appelle : " + G1.getaStudent(2695).getFirst_name() + " " + G1.getaStudent(2695).getLast_name());
		System.out.println("Dans ce groupe il y'a : " + G1.getNumber_of_students() + " étudiants");
		System.out.println("L'id_group de l'étudiant est : " + S1.getId_group() );
		G1.removeaStudent(2695);
		System.out.println("Dans ce groupe il y'a : " + G1.getNumber_of_students() + " étudiants");
		System.out.println("L'id_group de l'étudiant est : " + S1.getId_group() );
/*
		Classroom B12S = new Classroom (120, 30);
		Classroom B12N = new Classroom (121, 10);
		Calendar calendar_start_date = new GregorianCalendar(2019, 05 -1, 22, 15, 0);
		Date start_date = calendar_start_date.getTime() ;
		Calendar calendar_end_date = new GregorianCalendar(2019, 05 -1, 22, 16, 15);
		Date end_date = calendar_end_date.getTime() ;
		
		Reservation R1 = new Reservation (1, "samuelmath", start_date, end_date, B12S);
		Reservation R2 = new Reservation (2, "fredericTEC", start_date, end_date, B12N);

		Timetable Planningsenechal = new Timetable(456);
		Planningsenechal.setReservations(R1);
		Planningsenechal.setReservations(R2);
		System.out.println(Planningsenechal.getaReservation(1).getStart_date());
*/
	}

}
