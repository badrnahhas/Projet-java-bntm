package userModel;

public class User {
	
	private String login;
	private String password;
	private String first_name;
	private String last_name;
	
	/**
	 * Constructor.
	 * @param aLogin
	 * @param aPassword
	 * @param aFirst_name
	 * @param aLast_name
	 */
	public User(String aLogin, String aPassword, String aFirst_name, String aLast_name) {
		this.setLogin(aLogin);
		this.setPassword(aPassword);
		this.setFirst_name(aFirst_name);
		this.setLast_name(aLast_name);
	}
	
	/**
	 * login getter.
	 * @return login
	 */
	public String getLogin() {
		return this.login;
	}
	
	/**
	 * login setter.
	 * @param aLogin
	 */
	public void setLogin(String aLogin) {
		this.login = aLogin;
	}
	
	/**
	 * first_name getter.
	 * @return first_name
	 */
	public String getFirst_name() {
		return this.first_name;
	}
	
	/**
	 * first_name setter.
	 * @param aFirst_name
	 */
	public void setFirst_name(String aFirst_name) {
		this.first_name = aFirst_name;
	}
	
	/**
	 * last_name getter.
	 * @return last_name
	 */
	public String getLast_name() {
		return this.last_name;
	}
	
	/**
	 * last_name setter.
	 * @param aLast_name
	 */
	public void setLast_name(String aLast_name) {
		this.last_name = aLast_name;
	}
	
	/**
	 * password getter.
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * password setter.
	 * @param aPassword
	 */
	public void setPassword(String aPassword) {
		this.password = aPassword;
	}
	
}