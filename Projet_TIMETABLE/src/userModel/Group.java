package userModel;
import java.util.Hashtable;

public class Group {
	
	private int id_group;
	private int number_of_students;
	private Hashtable<Integer,Student> Students = new Hashtable<Integer,Student>();
	
	/**
	 * Constructor.
	 * @param aId_group
	 */
	public Group(int aId_group) {
		setId_group(aId_group);
		setNumber_of_students();
	}
	
	/**
	 * returns a student in the hash table "Students".
	 * @param aId_student
	 * @return a student in the hash table "Students" who's id is aId_student
	 */
	public Student getaStudent(int aId_student) {
		return Students.get(aId_student);
	}

	/**
	 * adds a student to the hash table "Students".
	 * @param aStudent
	 */
	public void addaStudent(Student aStudent) {
		if (getaStudent(aStudent.getId_student()) == null) {
			aStudent.setId_group(getId_group());
			Students.put(aStudent.getId_student(),aStudent);
			setNumber_of_students();
		}else {
			System.out.println("there is already a student with that Id in this group");
		}
	}
	
	/**
	 * removes a student from the hash table "Students".
	 * @param aId_student
	 */
	public void removeaStudent(int aId_student) {
		if (getaStudent(aId_student) != null) {
			getaStudent(aId_student).setId_group(-1);
			Students.remove(aId_student);
			setNumber_of_students();
		}else {
			System.out.println("there is no such student with that Id in this group");			
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