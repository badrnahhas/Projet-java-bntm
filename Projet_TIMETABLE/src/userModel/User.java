package userModel;

public class User {
	
	private String login;
	private String password;
	private String first_name;
	private String last_name;
	
	public User(String aLogin, String aPassword, String aFirst_name, String aLast_name) {
		this.setLogin(aLogin);
		this.setPassword(aPassword);
		this.setFirst_name(aFirst_name);
		this.setLast_name(aLast_name);
	}
	
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String aLogin) {
		this.login = aLogin;
	}

	public String getFirst_name() {
		return this.first_name;
	}

	public void setFirst_name(String aFirst_name) {
		this.first_name = aFirst_name;
	}

	public String getLast_name() {
		return this.last_name;
	}

	public void setLast_name(String aLast_name) {
		this.last_name = aLast_name;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String aPassword) {
		this.password = aPassword;
	}
	
}