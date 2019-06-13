package timeTableModel;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Cette classe gère la base de données d'emplois du temps. Elle doit permettre de sauvegarder et charger les emplois du temps ainsi que les salles à partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier TimeTableDB.xml.
 * @see <a href="../../TimeTableDB.xml">TimeTableDB.xml</a> 
 * 
 * @author Badreddine NAHHAS - Nicolas TOUSCH
 * @version 06/2019
 * 
 */

public class TimeTableDB {
	/**
	 * 
	 * Le fichier contenant la base de données.
	 * 
	 */
	private String file;
	
	
	/**
	 * Hashtable contenant un ensemble de Timetable avec pour clé leur Id
	 */
	private Hashtable<Integer, Timetable> Timetables = new Hashtable<Integer, Timetable>();
	
	/**
	 * Hashtable contenant un ensemble de Classroom avec pour clé leur Id
	 */
	private Hashtable<Integer, Classroom> Classrooms = new Hashtable<Integer, Classroom>();
	
	/**
	 * 
	 * Constructeur de TimeTableDB. 
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public TimeTableDB(String file){
		super();
		setFile(file);
		load_classrooms_timetables();
	}
	
	/**
	 * Getter de file
	 * 
	 * @return 
	 * 		Le nom du fichier qui contient la base de données.
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
	
	/**
	 * Fonction qui ajoute une nouvelle salle de classe dans la database
	 * @param roomId
	 * @param capacity	 
	 * @return vrai la salle de classe à bien été ajoutée, faux sinon.
	 */	
	public boolean addanewClassroom(int roomId, int capacity) {
		boolean Result = false;
		Classroom aclassroom = new Classroom(roomId, capacity);
		Result = setClassrooms(aclassroom);
		if (Result == true) {
			save_classrooms_timetables(); // Met à jour le fichier XML.
		}
		return Result;
	}
	
	/**
	 * Fonction qui supprime une salle de classe de la database (hashtable Classrooms).			
	 * @param roomId
	 * @return vrai si le la salle a bien été supprimée, faux sinon.
	 */	
	public boolean removeaClassroom(int roomId) {
		boolean Result = false;
		Result = (Classrooms.remove(roomId) != null);
		if (Result == true) {
			save_classrooms_timetables(); // Met à jour le fichier XML
		}
		return Result;
	}
	
	/**
	 * Fonction qui ajoute un nouvel emploi du temps dans la database (Hashtable Timetables).
	 * @param timeTableId
	 * @return vrai si l'emploi du temps a bien été ajouté, faux sinon. 
	 */	
	public boolean addanewTimeTable(int timeTableId) {
		boolean Result = false;
		Timetable atimetable = new Timetable(timeTableId);
		Result = setTimetables(atimetable);
		if (Result == true) {
			save_classrooms_timetables(); //Met à jour dans le fichier XML
		}
		return Result;
	}
	
	/**
	 * Fonction qui supprime l'emploi du temps de la database (Hashtable Timetables).
	 * @param timeTableId
	 * @return vrai si l'emploi du temps a bien été supprimé, faux sinon. 
	 */	
	public boolean removeaTimeTable(int timeTableId) {
		boolean Result = false;
		Result = (Timetables.remove(timeTableId) != null);
		if (Result == true) {
			save_classrooms_timetables(); // Met à jour le fichier XML.
		}
		return Result;
	}

	/**
	 * Fonction qui crée une nouvelle réservation et qui l'ajoute dans la database.	  
	 * La fonction vérifie que l'emploi du temps dans lequel on met la réservation et la salle de classe existent.
	 * @param timeTableId
	 * @param bookingId
	 * @param login
	 * @param dateBegin
	 * @param dateEnd
	 * @param roomId
	 * @return vrai si la réservation a bien été ajouté, faux sinon.
	 */	
	public boolean addanewReservation(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId) {
		boolean Result = false;
		if (getaTimetable(timeTableId) != null) {
			if (getaClassroom(roomId) != null) {
				Reservation aReservation = new Reservation(roomId, login, dateBegin, dateEnd, getaClassroom(roomId));
				getaTimetable(timeTableId).setReservations(aReservation);
				Result = true;
				save_classrooms_timetables();
			}else {
				System.out.println("there is no such Classroom with that ID in the database");
			}
		}else {
			System.out.println("there is no such Timetable with that ID in the database");
		}
		return Result;
	}
	
	/**
	 * Fonction qui supprime une reservation de la database 	  
	 * La fonction vérifie que l'emploi du temps et la reservation existent.
	 * @param timeTableId
	 * @param bookId	 
	 * @return vrai si la reservation a bien été supprimé, faux sinon.
	 */	
	public boolean removeaReservation(int timeTableId, int bookId) {
		boolean Result = false;
		if (getaTimetable(timeTableId) != null) {
			if (getaTimetable(timeTableId).getaReservation(bookId) != null) {
				getaTimetable(timeTableId).removeaReservation(bookId);
				Result = true;
				save_classrooms_timetables(); // Met à jour le fichier XML.
			}else {
				System.out.println("there is no such Reservation with that ID in this Timetable");
			}
		}else {
			System.out.println("there is no such Timetable with that ID in the database");
		}
		return Result;
	}
	
	/**
	 * Procédure qui alimente respectivement deux hashtables dateBegin et dateEnd avec les dates de début et de fin des reservations. 
	 * La fonction vérifie que l'emploi du temps existe.
	 * @param timeTableId
	 * @param dateBegin
	 * @param dateEnd
	 */	
	public void getReservationsDate(int timeTableId, Hashtable<Integer, Date> dateBegin, Hashtable<Integer, Date> dateEnd) {
		if (getaTimetable(timeTableId) != null) {
			for(Entry<Integer,Reservation> entry: getaTimetable(timeTableId).getReservations().entrySet()) {
				Reservation Value = entry.getValue();
				dateBegin.put(Value.getReservation_number(), Value.getStart_date());
				dateEnd.put(Value.getReservation_number(), Value.getEnd_date());
			}
		}else {
			System.out.println("there is no such Timetable with that ID in the database");
		}
	}

	/**
	 * Fonction qui met à jour le fichier XML à partir des données des databases (hashtable). 
	 * @return vrai si le fichier XML a bien été mis à jour, faux sinon.	 
	 */	
	public boolean save_classrooms_timetables() {
		boolean Result = false;
		Element rootElt = new Element("TimeTablesDB");
		org.jdom2.Document document = new Document(rootElt);
		save_classrooms(rootElt);
		save_timetables(rootElt);
		try{ 
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream("timeTableDB.xml"));
			Result = true;
		}
		catch (java.io.IOException e){}
		return Result;
	}
	
	/**
	 * Procédure utilisée dans save_classrooms_timetables qui ajoute les éléments de la hashtable Classrooms dans le fichier XML.	  	 
	 */	
	private void save_classrooms (Element root) {
		Element classroomsElt = new Element("Rooms");
		for(Entry<Integer, Classroom> entry: Classrooms.entrySet()) {
			Element aclassroomElt = new Element("Room");
			Element aIdclassroomElt = new Element("RoomId");
			Element aCapacityElt = new Element("Capacity");
			Classroom Value = entry.getValue();
			aIdclassroomElt.setText(Integer.toString(Value.getId_classroom()));
			aCapacityElt.setText(Integer.toString(Value.getStudent_capacity()));
			aclassroomElt.addContent(aIdclassroomElt);
			aclassroomElt.addContent(aCapacityElt);
			classroomsElt.addContent(aclassroomElt);
		}
		root.addContent(classroomsElt);
	}
	
	/**
	 * Procédure utilisée dans save_classrooms_timetables qui ajoute les éléments Timetable de la hashtable Timetables dans le fichier XML.	  	 
	 */	
	private void save_timetables (Element root) {
		Element timetablesElt = new Element("TimeTables");
		for(Entry<Integer,Timetable> entry: Timetables.entrySet()) {
			Element atimetableElt = new Element("TimeTable");
			Element atimetableIdElt = new Element("GroupId");
			Timetable Value = entry.getValue();
			atimetableIdElt.setText(Integer.toString(Value.getId_timetable()));
			atimetableElt.addContent(atimetableIdElt);
			save_reservations(atimetableElt,Value);
			timetablesElt.addContent(atimetableElt);
		}
		root.addContent(timetablesElt);
	}
	
	/**
	 * Procédure utilisée dans save_classrooms_timetables qui ajoute les éléments Classrooms de la hashtable Timetables dans le fichier XML.	  	 
	 */	
	private void save_reservations (Element root, Timetable ActualTimetable) {
		Element reservationsElt = new Element("Books");
		for(Entry<Integer,Reservation> entry: ActualTimetable.getReservations().entrySet()) {
			Element areservationElt = new Element("Book");
			Element areservation_numberElt = new Element("BookingId");
			Element ateacher_loginElt = new Element("Login");
			Element astart_dateElt = new Element("DateBegin");
			Element aend_dateElt = new Element("DateEnd");
			Element aclassroomIdElt = new Element("RoomId");
			Reservation Value = entry.getValue();
			areservation_numberElt.setText(Integer.toString(Value.getReservation_number()));
			ateacher_loginElt.setText(Value.getTeacher_login());
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			astart_dateElt.setText(formatter.format(Value.getStart_date()));
			aend_dateElt.setText(formatter.format(Value.getEnd_date()));
			aclassroomIdElt.setText(Integer.toString(Value.getClassroom_reserved().getId_classroom()));
			areservationElt.addContent(areservation_numberElt);
			areservationElt.addContent(ateacher_loginElt);
			areservationElt.addContent(astart_dateElt);
			areservationElt.addContent(aend_dateElt);
			areservationElt.addContent(aclassroomIdElt);
			reservationsElt.addContent(areservationElt);
		}
		root.addContent(reservationsElt);
	}
	
	/**
	 * Fonction qui charge le fichier XML en ajoutant ses données dans la database (les différentes hashtable). 
	 * @return vrai si le fichier XML a bien chargé, faux sinon.	 
	 */	
	public boolean load_classrooms_timetables() {
		boolean Result = false;
		org.jdom2.Document document = null ;
		Element rootElt;
		SAXBuilder sxb = new SAXBuilder();
		try{
			document = sxb.build(new File(getFile()));
		}
		catch(Exception e){}
		if(document!=null){
			rootElt = document.getRootElement();
			load_classrooms (rootElt);
			load_timetables (rootElt);
			Result = true;
		}
		return Result;
	}
	
	/**
	 * Procédure utilisée dans load_classrooms_timetables qui ajoute les éléments du fichier XML dans la hashtable Classrooms.	  	 
	 */
	private void load_classrooms (Element root) {
		List<Element> classroomElts = root.getChild("Rooms").getChildren("Room");
		Iterator<Element> itclassroom = classroomElts.iterator();
		while(itclassroom.hasNext()){
			Element aclassroomElt = (Element)itclassroom.next();
			int aIdclassroom = Integer.parseInt(aclassroomElt.getChild("RoomId").getText());
			int aCapacity = Integer.parseInt(aclassroomElt.getChild("Capacity").getText());
			Classroom aClassroom = new Classroom(aIdclassroom, aCapacity);
			setClassrooms(aClassroom);
		}
	}
	
	/**
	 * Procédure utilisée dans load_classrooms_timetables qui ajoute les éléments Timetable du fichier XML dans la hashtable Timetables.	  	 
	 */
	private void load_timetables (Element root) {
		List<Element> timetableElts = root.getChild("TimeTables").getChildren("TimeTable");
		Iterator<Element> ittimetable = timetableElts.iterator();
		while(ittimetable.hasNext()){
			Element atimetableElt = (Element)ittimetable.next();
			int atimetableId = Integer.parseInt(atimetableElt.getChild("GroupId").getText());
			Timetable aTimetable = new Timetable(atimetableId);
			load_reservations(aTimetable, atimetableElt);
			setTimetables(aTimetable);
		}
	}
	
	/**
	 * Procédure utilisée dans load_classrooms_timetables qui ajoute les éléments Reservations du fichier XML dans la hashtable Timetables.	  	 
	 */
	private void load_reservations (Timetable atimetable, Element root) {
		List<Element> reservationElts = root.getChild("Books").getChildren("Book");
		Iterator<Element> itreservation = reservationElts.iterator();
		while(itreservation.hasNext()){
			Element areservationElt = (Element)itreservation.next();
			int aReservation_number = Integer.parseInt(areservationElt.getChild("BookingId").getText());
			String aTeacher_Login = areservationElt.getChild("Login").getText();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date astart_date = new Date();
			try {
				astart_date = formatter.parse(areservationElt.getChild("DateBegin").getText());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date aend_date = new Date();
			try {
				aend_date = formatter.parse(areservationElt.getChild("DateEnd").getText());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int aclassroomID = Integer.parseInt(areservationElt.getChild("RoomId").getText());
			Classroom aclassroom = getaClassroom(aclassroomID);
			Reservation areservation = new Reservation(aReservation_number, aTeacher_Login, astart_date, aend_date, aclassroom);
			atimetable.setReservations(areservation);
		}
	}
	
	/**
	 * Fonction qui retourne en chaîne de caractère le login du professeur.
	 * La fonction vérifie avant que l'emploi du temps et que la réservation existent dans la database.
	 * @param timeTableId	
	 * @param bookId
	 * @return TeacherLogin : chaîne de caractères
	 */	
	public String getTeacherLogin(int timeTableId, int bookId) {
		String TeacherLogin = "";
		if (getaTimetable(timeTableId) != null) {
			if (getaTimetable(timeTableId).getaReservation(bookId) != null) {
				TeacherLogin = getaTimetable(timeTableId).getaReservation(bookId).getTeacher_login();
			}else {
				System.out.println("there is no such reservation with that ID on this Timetable");
			}
		}else {
			System.out.println("there is no such Timetable with that ID in the database");
		}
		return TeacherLogin;
	}
	
	/**
	 * Fonction qui retourne l'ID de la salle de classe. 	  
	 * La fonction vérifie avant que l'emploi du temps et la reservation existent. 
	 * @param timeTableId
	 * @param bookId
	 * @return roomID : l'ID de la salle de classe.
	 */	
	public int getRoomID(int timeTableId, int bookId) {
		int roomID = -1;
		if (getaTimetable(timeTableId) != null) {
			if (getaTimetable(timeTableId).getaReservation(bookId) != null) {
				roomID = getaTimetable(timeTableId).getaReservation(bookId).getClassroom_reserved().getId_classroom();
			}else {
				System.out.println("there is no such reservation with that ID on this Timetable");
			}
		}else {
			System.out.println("there is no such Timetable with that ID in the database");
		}
		return roomID;
	}

	/**
	 * Getter de Classrooms
	 * Fonction qui retourne une salle de classe de la hashtable Classrooms à partir de sa clé. 	 
	 * @param aID_classroom
	 * @return une salle de classe
	 */	
	public Classroom getaClassroom (int aID_classroom) {
		return Classrooms.get(aID_classroom);
	}

	/**
	 * Fonction qui ajoute une salle de classe dans la database (hashtable Classrooms). 
	 * @param aclassroom
	 * @return vrai si la salle de classe a bien été ajoutée, faux sinon.
	 */	
	public boolean setClassrooms(Classroom aclassroom) {
		boolean Result = false;
		if (getaClassroom(aclassroom.getId_classroom()) == null) {
			Result = (Classrooms.put(aclassroom.getId_classroom(), aclassroom) == null);
		}else {
			System.out.println("there is already a Classroom with that ID in the database");
		}
		return Result;
	}
	
	/**
	 * Getter de Timetables
	 * Fonction qui retourne un emploi du temps de la hashtable Timetables à partir de sa clé.
	 * @param aID_timetable
	 * @return un emploi du temps.
	 */	
	public Timetable getaTimetable (int aID_timetable) {
		return Timetables.get(aID_timetable);
	}
	
	/**
	 * Setter de Timetables
	 * Fonction qui ajoute un emploi du temps dans la hashtable Timetables.
	 * @param atimetable
	 * @return vrai si l'emploi du temps a bien été ajouté, faux sinon.
	 */	
	public boolean setTimetables(Timetable atimetable) {
		boolean Result = false;
		if (getaTimetable(atimetable.getId_timetable()) == null) {
			Result = (Timetables.put(atimetable.getId_timetable(), atimetable) == null);	
		}else {
			System.out.println("there is already a Timetable with that ID in the database");
		}
		return Result;
	}		
	
	/**
	 * Fonction qui retourne les IDs des salles de classe sous forme de chaînes de caractères. 	 
	 * @return classroomIDs : tableau de chaînes de caractères.
	 */	
	public String[]classroomsIDsToString() {	
		String[] classroomIDs = new String[Classrooms.size()];
		int i=0;
		for(Entry<Integer, Classroom> entry : Classrooms.entrySet()) {
			classroomIDs[i] = String.valueOf(entry.getValue().getId_classroom());
			i++;
		}
		return classroomIDs;
	}
	
	/**
	 * Fonction qui retourne toutes les informations des salles de classe sous forme de chaînes de caractères. 	 
	 * @return classroomsInfo : tableau de chaînes de caractères.
	 */	
	public String[] classroomsToString() {
		String[] classroomsInfo = new String[Classrooms.size()];
		int i = 0;
		for(Entry<Integer, Classroom> entry : Classrooms.entrySet()) {
			classroomsInfo[i] = entry.getValue().toString();
			i++;
		}
		return classroomsInfo;
	}
	
	/**
	 * Fonction qui retourne les IDs des emploi du temps sous forme de chaînes de caractères. 	 
	 * @return timetableIDs : tableau de chaînes de caractères.
	 */	
	public String[] timeTablesIDsToString() {
		String[] timetableIDs = new String[Timetables.size()];
		int i = 0;
		for(Entry<Integer, Timetable> entry : Timetables.entrySet()) {
			timetableIDs[i]=String.valueOf(entry.getValue().getId_timetable());
			i++;
		}
		return timetableIDs;
	}
	
	/**
	 * Fonction qui retourne les IDs des reservations sous forme de chaînes de caractères. 
	 * @param timeTableId
	 * @return reservationIDs : tableau de chaînes de caractères.
	 */	
	public String[] reservationsIDsToSTring(int timeTableId) {
		String[] reservationIDs = new String[getaTimetable(timeTableId).getReservations().size()];
		int i = 0;
		for(Entry<Integer, Reservation> entry : getaTimetable(timeTableId).getReservations().entrySet()) {
			reservationIDs[i] = String.valueOf(entry.getValue().getReservation_number());
			i++;
		}
		return reservationIDs;
	}
	
	/**
	 * Fonction qui retourne le plus grand ID des reservations. 
	 * @param timeTableId
	 * @return maxID : un int.
	 */	
	public int getReservationsMaxId(int timeTableId) {
		int maxID = 0;
		for(Entry<Integer, Reservation> entry : getaTimetable(timeTableId).getReservations().entrySet()) {
			if (entry.getValue().getReservation_number() > maxID) {
				maxID = entry.getValue().getReservation_number();				
			}
		}
		return maxID;
	}
}