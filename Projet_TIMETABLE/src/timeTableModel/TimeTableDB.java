package timeTableModel;

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
 * Cette classe gére la base de données d'emplois du temps. Elle doit permettre de sauvegarder et charger les emplois du temps ainsi que les salles à partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier TimeTableDB.xml.
 * @see <a href="../../TimeTableDB.xml">TimeTableDB.xml</a> 
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe à modifier

public class TimeTableDB {
	/**
	 * 
	 * Le fichier contenant la base de données.
	 * 
	 */
	private String file;
	/**
	 * 
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
	
	private void save_reservations (Element root, Timetable Value) {
		Element reservationsElt = new Element("Books");
		for(Entry<Integer,Reservation> entry: Value.getReservations().entrySet()) {
			Element areservationElt = new Element("Book");
			Element areservation_numberElt = new Element("BookingId");
			Element ateacher_loginElt = new Element("Login");
			Element astart_dateElt = new Element("DateBegin");
			Element aend_dateElt = new Element("DateEnd");
			Element aclassroomIdElt = new Element("RoomId");
			Reservation Value = entry.getValue();
			areservation_numberElt.setText(Integer.toString(Value.getReservation_number()));
			ateacher_loginElt.setText(Value.getTeacher_login());
			astart_dateElt.setText(Value.getStart_date());// � modifier ne fonctionne peut-�tre pas
			aend_dateElt.setText(Value.getEnd_date());// � modifier ne fonctionne peut-�tre pas
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
			load_reservations(aTimetable, timetableElts);
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
			Date astart_date = ;/////////////////////////////////////
			Date aend_date = ;///////////////////////////////////////
			int aclassroomID = Integer.parseInt(areservationElt.getChild("RoomId").getText());
			Classroom aclassroom = getaClassroom(aclassroomID);
			Reservation areservation = new Reservation(aReservation_number, aTeacher_Login, astart_date, aend_date, aclassroom);
			atimetable.setReservations(areservation);
		}
	}
	
	public boolean addRoom(int roomId, int capacity) {
		private boolean b = false;
		Classroom aclassroom = new Classroom(roomId, capacity);
		setClassrooms(aclassroom);
		b = saveDB();
		return b;
	}
	
	public boolean removeRoom(int roomId) {
		private boolean b = false;
		Classrooms.remove(roomId);
		b = saveDB();
		return b;
	}
	
	public boolean addTimeTable(int timeTableId) {
		private boolean b = false;
		TimeTable atimetable = new TimeTable(timeTableId);
		setTimetables(timeTableId);
		b = saveDB();
		return b;
	}
	
	public boolean removeTimeTable(int timeTableId) {
		private boolean b = false;
		TimeTables.remove(timeTableId);
		b = saveDB();
		return b;
	}
	
	
	public Classroom getaClassroom (int aID_classroom) {
		return Classrooms.get(aID_classroom);
	}

	public void setClassrooms(Classroom aclassroom) {
		Classrooms.put(aclassroom.getId_classroom(), aclassroom);
	}
	
	public Timetable getaTimetable (int aID_timetable) {
		return Timetables.get(aID_timetable);
	}
	
	public void setTimetables(Timetable atimetable) {
		Timetables.put(atimetable.getId_timetable(), atimetable);
	}
	
	//___________________
		
	
	public String[]roomsIdToString() {	
		String[] classroomsIDs = new String[Classrooms.size()];
		int i=0;
		for(Entry<Integer, Classroom> entry : Classrooms.entrySet()) {
			classroomsIDs[i] = String.ValueOf(entry.getValue().getId_classroom());
			i++;
		}
		return classroomsIDs;
	}
	
	public String[] roomsToSTring() {
		String[] classroomsInfo = new String[Groups.size()];
		int i = 0;
		for(Entry<Integer, Classroom> entry : Classrooms.entrySet()) {
			classroomsInfo[i] = entry.getValue().toString();
			i++;
		}
		return classroomsInfo;
	}
	
	public String timeTablesIDToString() {
		String[] timetablesIDs = new String[Timetables.size()];
		int i=0;
		for(Entry<Integer, TimeTable> entry : Timetables.entrySet()) {
			timetablesIDs[i]=String.ValueOf(entry.getValue().getId_timetable());
			i++
		}
		return timetablesIDs;
	}
	
	public String booksIdToSTring(int timeTableId) {		// a revoir entr�e dans une deuxi�me table
		String[] reservationIDs = new String[Reservations.size()];
		int i=0;
		for(Entry<Integer, TimeTable> entry : Reservations.entrySet()) {
			reservationIDs[i]=String.ValueOf(entry.getValue().getNumber_of_reservation());
			i++
		}
		return reservationIDs;
	}
	
	public String getTeacherLogin(int timeTableId, int bookId) { // a revoir 
		return this.getaTimetable(timeTableId).getaReservation(bookId).
	}
	
}
