
public class Teacher extends User {
	
	private int id_teacher;

	public Teacher(String aLogin, String aPassword, String aFirst_name, String aLast_name,int aId_teacher) {
		super(aLogin, aPassword, aFirst_name, aLast_name);
		this.setId_teacher(aId_teacher);
	}
	
	public int getId_teacher() {
		return this.id_teacher;
	}

	public void setId_teacher(int aId_teacher) {
		this.id_teacher = aId_teacher;
	}
	
}