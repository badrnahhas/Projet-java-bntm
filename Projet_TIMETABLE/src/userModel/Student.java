package userModel;
public class Student extends User {

	private int id_student;
	private int id_group;
	
	/**
	 * Constructor.
	 * @param aLogin
	 * @param aPassword
	 * @param aFirst_name
	 * @param aLast_name
	 * @param aId_student
	 * @param aId_group
	 */
	public Student(String aLogin, String aPassword, String aFirst_name, String aLast_name, int aId_student, int aId_group) {
		super(aLogin, aPassword, aFirst_name, aLast_name);
		this.setId_student(aId_student);
		this.setId_group(aId_group);		
	}

	/**
	 * id_student getter.
	 * @return id_student
	 */
	public int getId_student() {
		return this.id_student;
	}

	/**
	 * id_student setter.
	 * @param aId_student
	 */
	public void setId_student(int aId_student) {
		this.id_student = aId_student;
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
	
}