package timeTableController;

import java.util.Date;
import java.util.Hashtable;

/**
 * Cette classe est l'interface du contrôleur que vous devez implémenter. 
 * Elle contient un certains nombre de fonctions qui sont utilisées dans l'interface graphique. 
 * Elle permet à cette dernière d'être indépendante par rapport à votre implémentation de la gestion des emplois du temps.
 * Cette classe doit être implémentée par la classe TimeTableController qui devra implémenter les fonctions décrites ci-dessous.
 * 
 * @author Jose Mennesson
 * @version 04/2016
 * 
 */

public interface ITimeTableController {
	/**
	 * Fonction permettant de récupérer le login du professeur qui a réalisé la réservation dont l'identifiant est bookId dans l'emploi du temps dont l'identifiant est timeTableId. 
	 * @param timeTableId
	 * 		L'identifiant de l'emploi du temps
	 * @param bookId
	 * 		L'identifiant de réservation
	 * @return
	 * 		Le login du professeur qui a fait la réservation.
	 */
	public String getTeacherLogin(int timeTableId, int bookId);
	/**
	 * Fonction qui crée une salle et qui la sauvegarde dans la base de données. 
	 * @param roomId
	 * 		L'identifiant de la salle
	 * @param capacity
	 * 		La capacité de la salle
	 * @return
	 * 		Un boolean indiquant si la salle a bien été créée
	 */
	public boolean addRoom(int roomId, int capacity);
	/**
	 * Fonction qui supprime une salle et qui sauvegarde la base de données. 
	 * @param roomId
	 * 		L'identifiant de la salle
	 * @return
	 * 		Un boolean indiquant si la salle a bien été supprimée
	 */
	public boolean removeRoom(int roomId);
	/**
	 * Fonction qui récupère l'identifiant de la salle réservée dans l'emploi du temps dont l'identifiant est timeTableId et dont l'identifiant de réservation est bookId
	 * @param timeTableId
	 * 		L'identifiant d'emploi du temps
	 * @param bookId
	 * 		L'identifiant de réservation
	 * @return
	 * 		L'identifiant de la salle réservée
	 */
	public int getRoom(int timeTableId, int bookId);
	/**
	 * Fonction qui crée un emploi du temps et qui le sauvegarde dans la base de données
	 * @param timeTableId
	 * 		L'identifiant d'emploi du temps
	 * @return
	 * 		Un boolean indiquant si l'emploi du temps a bien été créé
	 */
	public boolean addTimeTable(int timeTableId);
	/**
	 * Fonction qui supprime un emploi du temps et qui sauvegarde la base de données
	 * @param timeTableId
	 * 		L'identifiant d'emploi du temps
	 * @return
	 * 		Un boolean indiquant si l'emploi du temps a bien été créé
	 */
	public boolean removeTimeTable(int timeTableId);
	/**
	 * Fonction qui ajoute une réservation dans l'emploi du temps TimeTableId et qui la sauvegarde dans la base de données
	 * 
	 * @param timeTableId
	 * 		L'identifiant d'emploi du temps
	 * @param bookingId
	 * 		L'identifiant de réservation
	 * @param login
	 * 		Le login du professeur faisant la réservation
	 * @param dateBegin
	 * 		La date de début de réservation
	 * @param dateEnd
	 * 		La date de fin de réservation
	 * @param roomId
	 * 		L'identifiant de la salle réservée
	 * @return
	 * 		Un boolean indiquant si la réservation a bien été faite
	 */
	public boolean addBooking(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId);
	/**
	 * Fonction qui retourne les dates de début et de fin des réservations de l'emploi du temps dont l'identifiant est timeTableId.
	 * 
	 * @param timeTableId
	 * 		L'identifiant d'emploi du temps
	 * @param dateBegin
	 * 		Hashtable qui contiendra les dates de début des réservations. La clé de la Hashtable correspond à l'identifiant de réservation.
	 * @param dateEnd
	 * 		Hashtable qui contiendra les dates de fin des réservations. La clé de la Hashtable correspond à l'identifiant de réservation.
	 */
	public void getBookingsDate(int timeTableId, Hashtable<Integer, Date> dateBegin, Hashtable<Integer, Date> dateEnd);
	/**
	 * Fonction qui supprime la réservation dont l'identifiant est bookId dans l'emploi du temps timeTableId.
	 * 
	 * @param timeTableId
	 * 		L'identifiant d'emploi du temps
	 * @param bookId
	 * 		L'identifiant de réservation à supprimer
	 * @return
	 * 		Un boolean indiquant si la réservation a bien été supprimée
	 */
	public boolean removeBook(int timeTableId,int bookId);
	/**
	 * Fonction qui récupère le plus grand identifiant de réservation dans l'emploi du temps timeTableId.
	 * 
	 * @param timeTableId
	 * 		L'identifiant d'emploi du temps
	 * @return
	 * 		Le plus grand identifiant de réservation
	 */
	public int getBookingsMaxId(int timeTableId);
	/**
	 * Fonction permettant de récupérer tous les identifiants des salles sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient l'identifiant d'une salle.
	 * 
	 * @return
	 * 		Un tableau de String contenant l'identifiant de toutes les salles.
	 */
	public String[] roomsIdToString();
	/**
	 * Fonction permettant de récupérer toutes les informations des salles sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient les informations d'une salle.
	 * 
	 * @return
	 * 		Un tableau de String contenant toutes les informations de toutes les salles.
	 */
	public String[] roomsToString();
	/**
	 * Fonction permettant de récupérer tous les identifiants des emplois du temps sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient l'identifiant d'un emploi du temps.
	 * 
	 * @return
	 * 		Un tableau de String contenant tous les identifiants de tous les emplois du temps.
	 */
	public String[] timeTablesIDToString();
	/**
	 * Fonction permettant de récupérer tous les identifiants des réservations de l'emploi du temps timeTableId sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient l'identifiant d'une réservation.
	 * 
	 * @param timeTableId
	 * 			Un identifiant d'emploi du temps
	 * @return
	 * 		Un tableau de String contenant tous les identifiants de toutes les r�servations de l'emploi du temps choisi.
	 */
	public String[] booksIdToString(int timeTableId);
	/**
	 * Fonction sauvegardant la base de donnée dans un fichier XML.
	 * @return
	 * 		Un boolean indiquant si la sauvegarde a bien été réalisée.
	 */
	public boolean saveDB();
	/**
	 * Fonction chargeant la base de donnée contenue dans un fichier XML.
	 * @return
	 * 		Un boolean indiquant si le chargement a bien été réalisée.
	 */
	public boolean loadDB();

}
