package userModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * 
 * Cette classe gère la base de données d'utilisateurs. Elle doit permettre de sauvegarder et charger les utilisateurs et les groupes à  partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier userDB.xml.
 * @see <a href="../../userDB.xml">userDB.xml</a> 
 * 
 * @author Badreddine NAHHAS - Nicolas TOUSCH
 * @version 22/05/2019
 * 
 */

public class UserDB {
	
	/**
	 * Le fichier contenant la base de données.
	 */
	private String file;

	/**
	 * Hashtable contenant un ensemble d'Id avec pour clé un login
	 * pour éviter d'avoir deux même Id et/ou deux même login
	 */
	private Hashtable<String, Integer> IDs = new Hashtable<String, Integer>();
	
	/**
	 * Hashtable contenant un ensemble d'utilisateurs avec pour clé leur Id
	 */
	private Hashtable<Integer, User> Users = new Hashtable<Integer, User>();
	
	/**
	 *  Hashtable contenant un ensemble de groupes avec pour clé leur Id
	 */
	private Hashtable<Integer, Group> Groups = new Hashtable<Integer, Group>();
	
	/**
	 * 
	 * Constructeur de UserDB. 
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public UserDB(String file){
		super();
		this.setFile(file);
		Admin FirstAdmin = new Admin("su", "123456", "First", "Admin", 0);
		setUsers(FirstAdmin);
	}
	
	public boolean addStudToGroup(String adminLogin, String studentLogin, int groupId) {
		boolean Result = false;
		if (getaUser(adminLogin) instanceof Admin) {
			if (getaGroup(groupId) != null) {
				if(getaUser(studentLogin) != null){
					if (getaUser(studentLogin) instanceof Student) {
						getaGroup(groupId).addaStudent(((Student) getaUser(studentLogin)));
						save_users_groups();
						Result = true;
					}else {
						System.out.println("this user is not a student");
					}
				}else {
					System.out.println("there is no such user with that login in the database");			
				}
			}else {
				System.out.println("there is no such group with that ID in the database");
			}
		}else {
			System.out.println("you are not an admin, therefore, you can not associate a student to a group");
		}
		return Result;
	}
	
	public boolean addanewgroup(String adminLogin, int groupId) {
		boolean Result = false;
		if (getaUser(adminLogin) instanceof Admin) {
			Result = setGroups(groupId);
			if (Result == true) {
				save_users_groups();	
			}
		}else {
			System.out.println("you are not an admin, therefore, you can not add a new group");
		}
		return Result;
	}
	
	public boolean removeaGroup(String adminLogin, int groupId) {
		boolean Result = false;
		if (getaUser(adminLogin) instanceof Admin) {
			Result = (Groups.remove(groupId) != null);
			if (Result == true) {
				save_users_groups();	
			}
		}else {
			System.out.println("you are not an admin, therefore, you can not remove any group");
		}
		return Result;
	}
	
	public boolean addanewStudent(String adminLogin, String newStudentLogin, int studentID, String firstname, String surname, String pwd) {
		boolean Result = false;
		if (getaUser(adminLogin) instanceof Admin) {
			Student aStudent = new Student(newStudentLogin, pwd, firstname, surname, studentID, -1);
			Result = setUsers(aStudent);
			if (Result == true) {
				save_users_groups();	
			}
		}else {
			System.out.println("you are not an admin, therefore, you can't add a new student");
		}
		return Result;
	}
	
	public boolean addanewTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname, String surname, String pwd) {
		boolean Result = false;
		if (getaUser(adminLogin) instanceof Admin) {
			Teacher aTeacher = new Teacher(newteacherLogin, pwd, firstname, surname, teacherID);
			Result = setUsers(aTeacher);
			if (Result == true) {
				save_users_groups();	
			}
		}else {
			System.out.println("you are not an admin, therefore, you can not add a new teacher");
		}
		return Result;
	}
	
	public boolean addanewAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname, String pwd) {
		boolean Result = false;
		if (getaUser(adminLogin) instanceof Admin) {
			Admin aAdmin = new Admin(newAdminlogin, pwd, firstname, surname, adminID);
			Result = setUsers(aAdmin);
			if (Result == true) {
				save_users_groups();	
			}
		}else {
			System.out.println("you are not an admin, therefore, you can not add a new Administrator");
		}
		return Result;
	}
	
	public boolean removeaUser(String adminLogin, String userLogin) {
		boolean Result = false;
		if (getaUser(adminLogin) instanceof Admin) {
			if(getaUser(userLogin) != null){
				if (getaUser(userLogin) instanceof Student) {
					if(((Student)getaUser(userLogin)).getId_group() != -1) {
						int Id_group = ((Student)getaUser(userLogin)).getId_group();
						getaGroup(Id_group).removeaStudent(userLogin);
					}
				}
				Result = (Users.remove(getaID(userLogin)) != null);
				if (Result == true) {
					removeaID(userLogin);
					save_users_groups();
				}
			}else {
				System.out.println("there is no such user with that login in the database");			
			}	
		}else {
			System.out.println("you are not an admin, therefore, you can not remove any user");
		}
		return Result;
	}
	
	public boolean save_users_groups() {
		boolean Result = false;
		Element rootElt = new Element("UsersDB");
		org.jdom2.Document document = new Document(rootElt);
		save_groups(rootElt);
		save_students(rootElt);
		save_teachers(rootElt);
		save_admins(rootElt);
		try{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream("test_UserDB.xml"));
			Result = true;
		}
		catch (java.io.IOException e){}
		return Result;
	}
	
	private void save_groups (Element root) {
		Element groupsElt = new Element("Groups");
		for(Entry<Integer,Group> entry: Groups.entrySet()) {
			Element agroupElt = new Element("Group");
			Element aIdgroupElt = new Element("groupId");
			Group Value = entry.getValue();
			aIdgroupElt.setText(Integer.toString(Value.getId_group()));
			agroupElt.addContent(aIdgroupElt);
			groupsElt.addContent(agroupElt);
		}
		root.addContent(groupsElt);
	}
	
	private void save_students (Element root) {
		Element studentsElt = new Element("Students");
		for(Entry<Integer,User> entry: Users.entrySet()) {
			User Value = entry.getValue();
			if (Value instanceof Student){
				Element astudentElt = new Element("Student");
				Element aloginElt = new Element("login");
				Element afirst_nameElt = new Element("firstname");
				Element alast_nameElt = new Element("surname");
				Element apasswordElt = new Element("pwd");
				Element astudentIdElt = new Element("studentId");
				Element aIdgroupElt = new Element("groupId");
				aloginElt.setText(Value.getLogin());
				afirst_nameElt.setText(Value.getFirst_name());
				alast_nameElt.setText(Value.getLast_name());
				apasswordElt.setText(Value.getPassword());
				astudentIdElt.setText(Integer.toString(((Student) Value).getId_student()));
				aIdgroupElt.setText(Integer.toString(((Student)Value).getId_group()));
				astudentElt.addContent(aloginElt);
				astudentElt.addContent(afirst_nameElt);
				astudentElt.addContent(alast_nameElt);
				astudentElt.addContent(apasswordElt);
				astudentElt.addContent(astudentIdElt);
				astudentElt.addContent(aIdgroupElt);
				studentsElt.addContent(astudentElt);
			}
		}
		root.addContent(studentsElt);
	}
	
	private void save_teachers (Element root) {
		Element teachersElt = new Element("Teachers");
		for(Entry<Integer,User> entry: Users.entrySet()) {
			User Value = entry.getValue();
			if (Value instanceof Teacher){
				Element ateacherElt = new Element("Teacher");
				Element aloginElt = new Element("login");
				Element afirst_nameElt = new Element("firstname");
				Element alast_nameElt = new Element("surname");
				Element apasswordElt = new Element("pwd");
				Element ateacherIdElt = new Element("teacherId");
				aloginElt.setText(Value.getLogin());
				afirst_nameElt.setText(Value.getFirst_name());
				alast_nameElt.setText(Value.getLast_name());
				apasswordElt.setText(Value.getPassword());
				ateacherIdElt.setText(Integer.toString(((Teacher) Value).getId_teacher()));
				ateacherElt.addContent(aloginElt);
				ateacherElt.addContent(afirst_nameElt);
				ateacherElt.addContent(alast_nameElt);
				ateacherElt.addContent(apasswordElt);
				ateacherElt.addContent(ateacherIdElt);
				teachersElt.addContent(ateacherElt);
			}
		}
		root.addContent(teachersElt);
	}
	
	private void save_admins (Element root) {
		Element adminsElt = new Element("Administrators");
		for(Entry<Integer,User> entry: Users.entrySet()) {
			User Value = entry.getValue();
			if (Value instanceof Admin){
				Element aadminElt = new Element("Administrator");
				Element aloginElt = new Element("login");
				Element afirst_nameElt = new Element("firstname");
				Element alast_nameElt = new Element("surname");
				Element apasswordElt = new Element("pwd");
				Element aadminIdElt = new Element("adminId");
				aloginElt.setText(Value.getLogin());
				afirst_nameElt.setText(Value.getFirst_name());
				alast_nameElt.setText(Value.getLast_name());
				apasswordElt.setText(Value.getPassword());
				aadminIdElt.setText(Integer.toString(((Admin) Value).getId_admin()));
				aadminElt.addContent(aloginElt);
				aadminElt.addContent(afirst_nameElt);
				aadminElt.addContent(alast_nameElt);
				aadminElt.addContent(apasswordElt);
				aadminElt.addContent(aadminIdElt);
				adminsElt.addContent(aadminElt);
			}
		}
		root.addContent(adminsElt);
	}
	
	public boolean load_users_groups() {
		boolean Result = false;
		org.jdom2.Document document = null ;
		Element rootElt;
		SAXBuilder sxb = new SAXBuilder();
		try{
			document = sxb.build(new File(this.file));
		}
		catch(Exception e){}
		if(document!=null){
			rootElt = document.getRootElement();
			load_groups (rootElt);
			load_students (rootElt);
			load_teachers (rootElt);
			load_admins (rootElt);
			Result = true;
		}
		return Result;
	}
	
	private void load_groups (Element root) {
		List<Element> groupElts = root.getChild("Groups").getChildren("Group");
		Iterator<Element> itgroup = groupElts.iterator();
		while(itgroup.hasNext()){
			Element agroupElt = (Element)itgroup.next();
			int aIdgroup = Integer.parseInt(agroupElt.getChild("groupId").getText());
			setGroups(aIdgroup);
		}
	}
	
	private void load_students (Element root) {
		List<Element> studentElts = root.getChild("Students").getChildren("Student");
		Iterator<Element> itstudent = studentElts.iterator();
		while(itstudent.hasNext()){
			Element astudentElt = (Element)itstudent.next();
			String aLogin = astudentElt.getChild("login").getText();
			String aFirst_name = astudentElt.getChild("firstname").getText();
			String aLast_name = astudentElt.getChild("surname").getText();
			String aPassword = astudentElt.getChild("pwd").getText();				
			int aId_student = Integer.parseInt(astudentElt.getChild("studentId").getText());
			int aId_group = Integer.parseInt(astudentElt.getChild("groupId").getText());
			Student aStudent = new Student(aLogin, aPassword, aFirst_name, aLast_name, aId_student, aId_group);
			setUsers(aStudent);
		}
	}
	
	private void load_teachers (Element root) {
		List<Element> teacherElts = root.getChild("Teachers").getChildren("Teacher");
		Iterator<Element> itteacher = teacherElts.iterator();
		while(itteacher.hasNext()){
			Element ateacherElt = (Element)itteacher.next();
			String aLogin = ateacherElt.getChild("login").getText();
			String aFirst_name = ateacherElt.getChild("firstname").getText();
			String aLast_name = ateacherElt.getChild("surname").getText();
			String aPassword = ateacherElt.getChild("pwd").getText();				
			int aId_teacher = Integer.parseInt(ateacherElt.getChild("teacherId").getText());
			Teacher aTeacher = new Teacher(aLogin, aPassword, aFirst_name, aLast_name, aId_teacher);
			setUsers(aTeacher);
		}
	}
	
	private void load_admins (Element root) {
		List<Element> adminElts = root.getChild("Administrators").getChildren("Administrator");
		Iterator<Element> itadmin = adminElts.iterator();
		while(itadmin.hasNext()){
			Element aadminElt = (Element)itadmin.next();
			String aLogin = aadminElt.getChild("login").getText();
			String aFirst_name = aadminElt.getChild("firstname").getText();
			String aLast_name = aadminElt.getChild("surname").getText();
			String aPassword = aadminElt.getChild("pwd").getText();				
			int aId_admin = Integer.parseInt(aadminElt.getChild("adminId").getText());
			Admin aAdmin = new Admin(aLogin, aPassword, aFirst_name, aLast_name, aId_admin);
			setUsers(aAdmin);
		}
	}
	
	/**
	 * Getter de file
	 * 
	 * @return 
	 * 		Le nom du fichier qui contient la base de donnÃ©es.
	 */
	
	public String getFile() {
		return file;
	}
	
	/**
	 * Setter de file
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	
	public void setFile(String file) {
		this.file = file;
	}

	public int getStudentGroup(String studentLogin) {
		int Result = -1;
		if(getaUser(studentLogin) != null) {
			if(getaUser(studentLogin) instanceof Student){
				Result = ((Student) getaUser(studentLogin)).getId_group();
				if (Result == -1) {
					System.out.println("this student has not been associated to a group");
				}
			}else {
				System.out.println("this user is not a student");
			}
		}else {
			System.out.println("there is no such user with that login in the database");
		}
		return Result;
	}

	public String[] groupsIdToString() {
		String[] groupsIDs = new String[Groups.size()];
		int i=0;
		for(Entry<Integer, Group> entry : Groups.entrySet()) {
			groupsIDs[i]= String.valueOf(entry.getValue().getId_group());
			i++;
		}
		return groupsIDs;
	}
	
	public String[] groupsToString() {
		String[] groupsInfo = new String[Groups.size()];
		int i=0;
		for(Entry<Integer, Group> entry : Groups.entrySet()) {
			groupsInfo[i]= entry.getValue().toString();
			i++;
		}
		return groupsInfo;
	}
	
	public Group getaGroup (int aId_group) {
		return Groups.get(aId_group);
	}
	
	public boolean setGroups(int aIdgroup) {
		boolean Result = false;
		if (getaGroup(aIdgroup) == null) {
			Group aGroup = new Group(aIdgroup);
			Result = (Groups.put(aIdgroup, aGroup) == null);
		}else {
			System.out.println("there is already a group with that ID in the database");
		}
		return Result;
	}

	public String getUserClass(String userLogin, String userPwd) {
		String Userclass = "";
		if(getaUser(userLogin) != null){
			if(getaUser(userLogin).getPassword().equals(userPwd)) {
				if (getaUser(userLogin) instanceof Admin){
					Userclass = "Administrator";
				}else if(getaUser(userLogin) instanceof Teacher){
					Userclass = "Teacher";
				}else if(getaUser(userLogin) instanceof Student){
					Userclass = "Student";
				}
				System.out.println("user connected");
			}else {
				System.out.println("wrong password");
			}
		}else {
			System.out.println("wrong login");			
		}	
		return Userclass;
	}
	
	public String getUserName(String userLogin) {
		String fullname = "";
		if(getaUser(userLogin) != null){
			fullname = getaUser(userLogin).getFirst_name() + " " + getaUser(userLogin).getLast_name();
		}else {
			System.out.println("there is no such user with that login in the database");
		}	
		return fullname;
	}

	public String[] usersToString() {
		String[] USersInfo = new String[Users.size()];
		int i=0;
		for(Entry<Integer, User> entry : Users.entrySet()) {
			USersInfo[i]= entry.getValue().toString();
			i++;
		}
		return USersInfo;
	}

	public String[] usersLoginToString() {
		String[] USersLogins = new String[Users.size()];
		int i=0;
		for(Entry<Integer, User> entry : Users.entrySet()) {
			USersLogins[i]= entry.getValue().getLogin();
			i++;
		}
		return USersLogins;
	}
	
	public String[] studentsLoginToString() {
		int i=0;
		for(Entry<Integer, User> entry : Users.entrySet()) {
			if (entry.getValue() instanceof Student) {
				i++;				
			}
		}
		String[] StudentsLogins = new String[i];
		i = 0;
		for(Entry<Integer, User> entry : Users.entrySet()) {
			if (entry.getValue() instanceof Student) {
				StudentsLogins[i]= entry.getValue().getLogin();
				i++;				
			}
		}
		return StudentsLogins;
	}
	
	public User getaUser (String aLogin) {
		int idfound = -1;
		if (getaID(aLogin) != null) {
			idfound = getaID(aLogin);
		}
			return Users.get(idfound);
	}
	
	public boolean setUsers(User aUser) {
		boolean Result = false;
		if(getaUser(aUser.getLogin()) == null) {
			int ID = -1;
			if (aUser instanceof Admin){
				ID = ((Admin) aUser).getId_admin();
			}else if(aUser instanceof Teacher){
				ID = ((Teacher) aUser).getId_teacher();
			}else if(aUser instanceof Student){
				ID = ((Student) aUser).getId_student();
			}
			if (Users.get(ID) == null) {
				Result = (Users.put(ID, aUser) == null);
				setIDs(aUser);
			}else {
				System.out.println("there is already a user with that ID in the database");
			}
		}else {
			System.out.println("there is already a user with that login in the database");
		}
		return Result;
	}
	
	public Integer getaID (String aLogin) {
		return IDs.get(aLogin);		
	}
	
	public void setIDs (User aUser) {
		if (aUser instanceof Admin){
			IDs.put(((Admin) aUser).getLogin(), ((Admin) aUser).getId_admin());
		}else if(aUser instanceof Teacher){
			IDs.put(((Teacher) aUser).getLogin(), ((Teacher) aUser).getId_teacher());
		}else if(aUser instanceof Student){
			IDs.put(((Student) aUser).getLogin(), ((Student) aUser).getId_student());
		}
	}
	
	public void removeaID(String aLogin) {
		IDs.remove(aLogin);
	}
	
}