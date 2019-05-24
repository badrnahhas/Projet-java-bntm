package userModel;
import java.util.Hashtable;

public class Group {
	
	private int id_group;
	private int number_of_students;
	private Hashtable<String,Student> Students = new Hashtable<String,Student>();
	
	public Group(int aId_group) {
		setId_group(aId_group);
		setNumber_of_students();
	}
	
	/**
	 * returns a student in the hash table "Students".
	 * @param aId_student
	 * @return a student in the hash table "Students" who's id is aId_student
	 */
	public Student getaStudent(String aLogin_student) {
		return Students.get(aLogin_student);
	}

	/**
	 * adds a student to the hash table "Students".
	 * @param aStudent
	 */
	public void addaStudent(Student aStudent) {
		if (getaStudent(aStudent.getLogin()) == null) {
			aStudent.setId_group(getId_group());
			Students.put(aStudent.getLogin(),aStudent);
			setNumber_of_students();
		}else {
			System.out.println("there is already a student with that login in this group");
		}
	}
	
	/**
	 * removes a student from the hash table "Students".
	 * @param aId_student
	 */
	public void removeaStudent(String aLogin_student) {
		if (getaStudent(aLogin_student) != null) {
			getaStudent(aLogin_student).setId_group(-1);
			Students.remove(aLogin_student);
			setNumber_of_students();
		}else {
			System.out.println("there is no such student with that login in this group");			
		}
	}
	
	/**
	 * id_group getter.
	 * @return id_group
	 */
	public int getId_group() {
		return this.id_group;
	}
	
	/**
	 * id_group setter.
	 * @param aId_group
	 */
	public void setId_group(int aId_group) {
		this.id_group = aId_group;
	}
	
	/**
	 * number_of_students getter.
	 * @return number_of_students
	 */
	public int getNumber_of_students() {
		return this.number_of_students;
	}
	
	/**
	 * number_of_students setter.
	 */
	public void setNumber_of_students() {
		this.number_of_students = Students.size();
	}
	
}