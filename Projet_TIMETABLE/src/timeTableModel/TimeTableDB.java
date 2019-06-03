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
	 * Le fichier contenant la base de données.
	 * 
	 */
	private String file;
	/**
	 * Constructeur de TimeTableDB. 
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	
	private Hashtable<Integer, Timetable> Timetables = new Hashtable<Integer, Timetable>();
	private Hashtable<Integer, Classroom> Classrooms = new Hashtable<Integer, Classroom>();
	
	public TimeTableDB(String file){
		
		super();
		this.setFile(file);
		
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
	
	public boolean addanewClassroom(int roomId, int capacity) {
		boolean Result = false;
		Classroom aclassroom = new Classroom(roomId, capacity);
		Result = setClassrooms(aclassroom);
		if (Result == true) {
			save_classrooms_timetables();
		}
		return Result;
	}
	
	public boolean removeaClassroom(int roomId) {
		boolean Result = false;
		Result = (Classrooms.remove(roomId) != null);
		if (Result == true) {
			save_classrooms_timetables();
		}
		return Result;
	}
	
	public boolean addanewTimeTable(int timeTableId) {
		boolean Result = false;
		Timetable atimetable = new Timetable(timeTableId);
		Result = setTimetables(atimetable);
		if (Result == true) {
			save_classrooms_timetables();
		}
		return Result;
	}
	
	public boolean removeaTimeTable(int timeTableId) {
		boolean Result = false;
		Result = (Timetables.remove(timeTableId) != null);
		if (Result == true) {
			save_classrooms_timetables();
		}
		return Result;
	}

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
	
	public boolean removeaReservation(int timeTableId, int bookId) {
		boolean Result = false;
		if (getaTimetable(timeTableId) != null) {
			if (getaTimetable(timeTableId).getaReservation(bookId) != null) {
				getaTimetable(timeTableId).removeaReservation(bookId);
				Result = true;
				save_classrooms_timetables();
			}else {
				System.out.println("there is no such Reservation with that ID in this Timetable");
			}
		}else {
			System.out.println("there is no such Timetable with that ID in the database");
		}
		return Result;
	}
	
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

	public boolean save_classrooms_timetables() {
		boolean Result = false;
		Element rootElt = new Element("TimeTablesDB");
		org.jdom2.Document document = new Document(rootElt);
		save_classrooms(rootElt);
		save_timetables(rootElt);
		try{ 
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream("test_timeTableDB.xml"));
			Result = true;
		}
		catch (java.io.IOException e){}
		return Result;
	}
	
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
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
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
	
	public boolean load_classrooms_timetables() {
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
			load_classrooms (rootElt);
			load_timetables (rootElt);
			Result = true;
		}
		return Result;
	}
	
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
	
	private void load_reservations (Timetable atimetable, Element root) {
		List<Element> reservationElts = root.getChild("Books").getChildren("Book");
		Iterator<Element> itreservation = reservationElts.iterator();
		while(itreservation.hasNext()){
			Element areservationElt = (Element)itreservation.next();
			int aReservation_number = Integer.parseInt(areservationElt.getChild("BookingId").getText());
			String aTeacher_Login = areservationElt.getChild("Login").getText();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
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

	public Classroom getaClassroom (int aID_classroom) {
		return Classrooms.get(aID_classroom);
	}

	public boolean setClassrooms(Classroom aclassroom) {
		boolean Result = false;
		if (getaClassroom(aclassroom.getId_classroom()) == null) {
			Result = (Classrooms.put(aclassroom.getId_classroom(), aclassroom) == null);
		}else {
			System.out.println("there is already a Classroom with that ID in the database");
		}
		return Result;
	}
	
	public Timetable getaTimetable (int aID_timetable) {
		return Timetables.get(aID_timetable);
	}
	
	public boolean setTimetables(Timetable atimetable) {
		boolean Result = false;
		if (getaTimetable(atimetable.getId_timetable()) == null) {
			Result = (Timetables.put(atimetable.getId_timetable(), atimetable) == null);	
		}else {
			System.out.println("there is already a Timetable with that ID in the database");
		}
		return Result;
	}		
	
	public String[]classroomsIDsToString() {	
		String[] classroomIDs = new String[Classrooms.size()];
		int i=0;
		for(Entry<Integer, Classroom> entry : Classrooms.entrySet()) {
			classroomIDs[i] = String.valueOf(entry.getValue().getId_classroom());
			i++;
		}
		return classroomIDs;
	}
	
	public String[] classroomsToString() {
		String[] classroomsInfo = new String[Classrooms.size()];
		int i = 0;
		for(Entry<Integer, Classroom> entry : Classrooms.entrySet()) {
			classroomsInfo[i] = entry.getValue().toString();
			i++;
		}
		return classroomsInfo;
	}
	
	public String[] timeTablesIDsToString() {
		String[] timetableIDs = new String[Timetables.size()];
		int i = 0;
		for(Entry<Integer, Timetable> entry : Timetables.entrySet()) {
			timetableIDs[i]=String.valueOf(entry.getValue().getId_timetable());
			i++;
		}
		return timetableIDs;
	}
	
	public String[] reservationsIDsToSTring(int timeTableId) {
		String[] reservationIDs = new String[getaTimetable(timeTableId).getReservations().size()];
		int i = 0;
		for(Entry<Integer, Reservation> entry : getaTimetable(timeTableId).getReservations().entrySet()) {
			reservationIDs[i] = String.valueOf(entry.getValue().getReservation_number());
			i++;
		}
		return reservationIDs;
	}
	
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
