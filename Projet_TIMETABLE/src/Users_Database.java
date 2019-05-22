import java.util.Hashtable;

public class Users_Database {

	private Hashtable<Integer, User> Users = new Hashtable<Integer, User>();
	private Hashtable<Integer, Group> Groups = new Hashtable<Integer, Group>();
	
	public Hashtable<Integer, User> getUsers() {
		return this.Users;
	}
	
	public void setUsers(Hashtable<Integer, User> users) {
		this.Users = users;
	}
	
	public Hashtable<Integer, Group> getGroups() {
		return this.Groups;
	}
	
	public void setGroups(Hashtable<Integer, Group> groups) {
		this.Groups = groups;
	}
	
}