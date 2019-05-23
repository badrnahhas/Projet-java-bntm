package userModel;

public class Admin extends User {
	
	private int id_admin;
	
	/**
	 * Constructor.
	 * @param aLogin
	 * @param aPassword
	 * @param aFirst_name
	 * @param aLast_name
	 * @param aId_admin
	 */
	public Admin(String aLogin, String aPassword, String aFirst_name, String aLast_name, int aId_admin) {
		super(aLogin, aPassword, aFirst_name, aLast_name);
		this.setId_admin(aId_admin);
	}
	
	/**
	 * id_admin getter.
	 * @return id_admin
	 */
	public int getId_admin() {
		return this.id_admin;
	}
	
	/**
	 * id_admin setter.
	 * @param aId_admin
	 */
	public void setId_admin(int aId_admin) {
		this.id_admin = aId_admin;
	}
	
}