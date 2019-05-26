package userController;

import userModel.UserDB;
/**
 * Cette classe est le contrôleur d'utilisateurs que vous devez implémenter. 
 * Elle contient un attribut correspondant à la base de données utilisateurs que vous allez créer.
 * Elle contient toutes les fonctions de l'interface IUserController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe à modifier

public class UserController implements IUserController
{
	
	/**
	 * Contient une instance de base de données d'utilisateurs
	 * 
	 */
	private UserDB userDB=null;
	
	
	/**
	 * Constructeur de controleur d'utilisateurs créant la base de données d'utilisateurs
	 * 
	 * @param userfile
	 * 		Fichier XML contenant la base de données d'utilisateurs
	 */
	public UserController(String userfile){
		UserDB userDB=new UserDB(userfile);
		this.setUserDB(userDB);
	}

	@Override
	public String getUserName(String userLogin) {
		return userDB.getUserName(userLogin);
	}

	@Override
	public String getUserClass(String userLogin, String userPwd) {
		return userDB.getUserClass(userLogin, userPwd);
	}

	@Override
	public int getStudentGroup(String studentLogin) {
		return userDB.getStudentGroup(studentLogin);
	}

	@Override
	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname, String pwd) {
		return userDB.addanewAdmin(adminLogin, newAdminlogin, adminID, firstname, surname, pwd);
	}

	@Override
	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname, String surname, String pwd) {
		return userDB.addanewTeacher(adminLogin, newteacherLogin, teacherID, firstname, surname, pwd);
	}

	@Override
	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname, String surname, String pwd) {
		return userDB.addanewStudent(adminLogin, newStudentLogin, studentID, firstname, surname, pwd);
	}

	@Override
	public boolean removeUser(String adminLogin, String userLogin) {
		return userDB.removeaUser(adminLogin, userLogin);
	}

	@Override
	public boolean addGroup(String adminLogin, int groupId) {
		return userDB.addanewgroup(adminLogin, groupId);
	}

	@Override
	public boolean removeGroup(String adminLogin, int groupId) {
		return userDB.removeaGroup(adminLogin, groupId);
	}

	@Override
	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		return userDB.addStudToGroup(adminLogin, studentLogin, groupId);
	}

	@Override
	public String[] usersToString() {
		return userDB.usersToString();
	}

	@Override
	public String[] usersLoginToString() {
		return userDB.usersLoginToString();
	}

	@Override
	public String[] studentsLoginToString() {
		return userDB.studentsLoginToString();
	}

	@Override
	public String[] groupsIdToString() {
		return userDB.groupsIdToString();
	}

	@Override
	public String[] groupsToString() {
		return userDB.groupsToString();
	}

	@Override
	public boolean loadDB() {
		return userDB.load_users_groups();
	}

	@Override
	public boolean saveDB() {
		return userDB.save_users_groups();
	}

	public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}

}