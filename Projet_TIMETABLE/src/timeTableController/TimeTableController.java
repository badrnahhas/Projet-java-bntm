package timeTableController;

import java.util.Date;
import java.util.Hashtable;

import timeTableModel.TimeTableDB;
/**
 * Cette classe est le contrôleur d'emplois du temps que vous devez implémenter. 
 * Elle contient un attribut correspondant à la base de données d'emplois du temps que vous allez créer.
 * Elle contient toutes les fonctions de l'interface ITimeTableController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe à modifier

public class TimeTableController implements ITimeTableController{

	/**
	 * Contient une instance de base de données d'emplois du temps
	 * 
	 */
	TimeTableDB tTDB;
	/**
	 * Constructeur de controleur d'emplois du temps créant la base de données d'emplois du temps
	 * 
	 * @param tTfile
	 * 		Fichier XML contenant la base de données d'emplois du temps
	 */
	public TimeTableController(String tTfile) {
		TimeTableDB tTDB=new TimeTableDB(tTfile);
		settTDB(tTDB);
	}

	@Override
	public String getTeacherLogin(int timeTableId, int bookId) {
		return gettTDB().getTeacherLogin(timeTableId, bookId);
	}

	@Override
	public String[] roomsIdToString() {		
		return gettTDB().classroomsIDsToString();
	}

	@Override
	public String[] roomsToString() {
		return gettTDB().classroomsToString();
		
	}

	@Override
	public String[] timeTablesIDToString() {
		return gettTDB().timeTablesIDsToString();		
	}

	@Override
	public String[] booksIdToString(int timeTableId) {
		return gettTDB().reservationsIDsToSTring(timeTableId);
	}

	@Override
	public boolean addRoom(int roomId, int capacity) {
		return gettTDB().addanewClassroom(roomId, capacity);
	}

	@Override
	public boolean removeRoom(int roomId) {
		return gettTDB().removeaClassroom(roomId);
	}

	@Override
	public int getRoom(int timeTableId, int bookId) {
		return gettTDB().getRoomID(timeTableId, bookId);
	}

	@Override
	public boolean addTimeTable(int timeTableId) {
		return gettTDB().addanewTimeTable(timeTableId);		
	}

	@Override
	public boolean removeTimeTable(int timeTableId) {
		return gettTDB().removeaTimeTable(timeTableId);
	}

	@Override
	public boolean addBooking(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId) {
		return gettTDB().addanewReservation(timeTableId, bookingId, login, dateBegin, dateEnd, roomId);
	}

	@Override
	public void getBookingsDate(int timeTableId, Hashtable<Integer, Date> dateBegin, Hashtable<Integer, Date> dateEnd) {
		gettTDB().getReservationsDate(timeTableId, dateBegin, dateEnd);
	}

	@Override
	public boolean removeBook(int timeTableId, int bookId) {
		return gettTDB().removeaReservation(timeTableId, bookId);
	}

	@Override
	public int getBookingsMaxId(int timeTableId) {
		return gettTDB().getReservationsMaxId(timeTableId);
	}

	@Override
	public boolean saveDB() {
		return gettTDB().save_classrooms_timetables();
	}

	@Override
	public boolean loadDB() {
		return gettTDB().load_classrooms_timetables();
	}

	public TimeTableDB gettTDB() {
		return this.tTDB;
	}

	public void settTDB(TimeTableDB tTDB) {
		this.tTDB = tTDB;
	}

}
