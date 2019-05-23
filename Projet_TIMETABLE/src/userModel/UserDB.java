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
	 * Hashtable contenant un ensemble d'utilisateurs
	 */
	private Hashtable<Integer, User> Users = new Hashtable<Integer, User>();
	
	/**
	 *  Hashtable contenant un ensemble de groupes
	 */
	private Hashtable<Integer, Group> Groups = new Hashtable<Integer, Group>();
	
	/**
	 * 
	 * Constructeur de UserDB. 
	 * 
	 * !!!!!!!!!!!! PENSEZ Ã€ AJOUTER UN ADMINISTRATEUR (su par exemple) QUI VOUS PERMETTRA DE CHARGER LA BASE DE DONNÃ‰ES AU DEMARRAGE DE L'APPLICATION !!!!!!
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de donnÃ©es.
	 */
	public UserDB(String file){
		super();
		this.setFile(file);
		load_users_groups();
	}
	
	public void save_users_groups() {
		Element rootElt = new Element("UsersDB");
		org.jdom2.Document document = new Document(rootElt);
		save_groups(rootElt);
		save_students(rootElt);
		save_teachers(rootElt);
		save_admins(rootElt);
		try{ 
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream("test_UserDB.xml"));
		}
		catch (java.io.IOException e){}
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
	
	public void load_users_groups() {
		org.jdom2.Document document = null ;
		Element rootElt;
		SAXBuilder sxb = new SAXBuilder();
		try{
			document = sxb.build(new File(this.file));
		}
		catch(Exception e){}
		if(document!=null){
			rootElt = document.getRootElement();
			List<Element> groupElts = rootElt.getChild("Groups").getChildren("Group");
			Iterator<Element> itgroup = groupElts.iterator();
			while(itgroup.hasNext()){
				Element agroupElt = (Element)itgroup.next();
				int aIdgroup = Integer.parseInt(agroupElt.getChild("groupId").getText());
				Group aGroup = new Group(aIdgroup);
				setGroups(aGroup);
			}
			List<Element> studentElts = rootElt.getChild("Students").getChildren("Student");
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
			List<Element> teacherElts = rootElt.getChild("Teachers").getChildren("Teacher");
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
			List<Element> adminElts = rootElt.getChild("Administrators").getChildren("Administrator");
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
	
	public User getaUser (int Key) {
		return Users.get(Key);
	}

	public void setUsers(User aUser) {
		if (aUser instanceof Admin){
			Users.put(((Admin) aUser).getId_admin(), aUser);
		}else if(aUser instanceof Teacher){
			Users.put(((Teacher) aUser).getId_teacher(), aUser);
		}else if(aUser instanceof Student){
			Users.put(((Student) aUser).getId_student(), aUser);
		}
	}

	public void removeaUser(int Key) {
		Users.remove(Key) ;
	}
	
	public Group getaGroup (int Key) {
		return Groups.get(Key);
	}

	public void setGroups(Group aGroup) {
		Groups.put(aGroup.getId_group(), aGroup);
	}

	public void removeaGroup(int Key) {
		Groups.remove(Key) ;
	}

}