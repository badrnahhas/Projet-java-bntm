package timeTableModel;

public class Classroom {
	
	private int id_classroom;
	private int student_capacity;
	
	public Classroom (int aId_classroom, int aStudent_capacity) {
		setId_classroom(aId_classroom);
		setStudent_capacity(aStudent_capacity);
	}

	public int getId_classroom () {
		return this.id_classroom;
	}

	public void setId_classroom (int aId_classroom) {
		this.id_classroom = aId_classroom;
	}

	public int getStudent_capacity () {
		return this.student_capacity;
	}

	public void setStudent_capacity (int aStudent_capacity) {
		this.student_capacity = aStudent_capacity;
	}

	@Override
	public String toString() {
		return "Classroom [id_classroom = " + id_classroom + ", student_capacity = " + student_capacity + "]";
	}
	
}