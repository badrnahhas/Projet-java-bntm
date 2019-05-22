import java.util.ArrayList;

public class Group {
	
	private int id_group;
	private int number_of_students;
	private ArrayList<Student> Students = new ArrayList<Student>();
	
	public Group(int aId_group) {
		setId_group(aId_group);
		setNumber_of_students();
	}
	
	public int getId_group() {
		return this.id_group;
	}

	public void setId_group(int aId_group) {
		this.id_group = aId_group;
	}

	public int getNumber_of_students() {
		return this.number_of_students;
	}

	public void setNumber_of_students() {
		this.number_of_students = Students.size();
	}
	
	public Student getaStudent(int aId_student) {
		Student studentresult = null;
		for(int i =0 ; i < Students.size() ; i++) {
			if (Students.get(i).getId_student() == aId_student) {
				studentresult = Students.get(i);
				break;
			}
		}
		return studentresult;
	}

	public void setStudents(Student aStudent) {
		Students.add(aStudent) ;
		aStudent.setId_group(id_group);
		setNumber_of_students();
	}
	
	public int getStudentPosition(int aId_student) {
		int position = -1;
		for(int i =0 ; i < Students.size() ; i++) {
			if (Students.get(i).getId_student() == aId_student) {
				position = i;
				break;
			}
		}
		return position;
	}
	
	public void removeaStudent(int aId_student) {
		int i = getStudentPosition(aId_student);
		if (i != -1) {
			Students.get(i).setId_group(0);
			Students.remove(i);
			setNumber_of_students();
		}
	}
	
}