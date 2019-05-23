package userModel;
public class Teacher extends User {
	
	private int id_teacher;
	
	/**
	 * Constructor.
	 * @param aLogin
	 * @param aPassword
	 * @param aFirst_name
	 * @param aLast_name
	 * @param aId_teacher
	 */
	public Teacher(String aLogin, String aPassword, String aFirst_name, String aLast_name,int aId_teacher) {
		super(aLogin, aPassword, aFirst_name, aLast_name);
		this.setId_teacher(aId_teacher);
	}
	
	/**
	 * id_teacher getter.
	 * @return id_teacher
	 */
	public int getId_teacher() {
		return this.id_teacher;
	}
	
	/**
	 * id_teacher setter.
	 * @param aId_teacher
	 */
	public void setId_teacher(int aId_teacher) {
		this.id_teacher = aId_teacher;
	}
	
}