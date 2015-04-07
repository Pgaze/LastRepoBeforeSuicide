package utilitaire;

import java.sql.Date;

import javax.naming.directory.InvalidAttributeValueException;

public class CustomDate {

	/** Verifie l'integrite des dates debut et fin renseignées
	 * @param dateDebut
	 * @param dateFin
	 * @throws InvalidAttributeValueException
	 */
	public static void checkIntegriteDates(Date dateDebut, Date dateFin) throws InvalidAttributeValueException {
		//verifications
		if(!dateFin.after(dateDebut))
			throw new InvalidAttributeValueException("La date de fin n'est pas posterieure a la date de debut");
		Date dateCourante = new Date(System.currentTimeMillis());
		if(dateDebut.before(dateCourante)) {
			throw new InvalidAttributeValueException("La date de debut est anterieure ou egale a la date actuelle");
		}
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 * @return "yyyy-mm-dd"
	 * @throws InvalidAttributeValueException
	 */
	public static String creerStringDate(int year, int month, int day) throws InvalidAttributeValueException {
		String res = null;
		//vérification sommaire
		if(month <= 0 || day <= 0 || year <= 0 || day > 31 || month > 12){
			throw new InvalidAttributeValueException("La date avant changement de format est invalide");
		}else{
			res = year + "-" ;
			res += (month<10 ? "0" : "");
			res += month + "-";
			res += (day < 10 ? "0" : "");
			res += day;
		}
		return res;
	}
	
	public static int[] splitDate(String date) {
		int[] tabRes = new int[3];
		String[] tabSplit = date.split("-");
		for(int i=0 ; i<3 ; i++){
			tabRes[i]= Integer.valueOf(tabSplit[i]);
		}
		return tabRes;
	}

	/** Reformate si necessaire
	 * @param date
	 * @return yyyy-mm-dd
	 */
	public static String checkFormatDate(String date) {
		if(date.length()==0) return null;
		String res;
		String[] splited = null;
		if(date.contains("/")){
			splited = date.split("/");
		}else if (date.contains("-")){
			splited = date.split("-");
		}
		if(splited[0].length() < 4){
			res = splited[2] + "-" + splited[0] + "-" + splited[1];
		}else{
			res = splited[0] + "-" + splited[1] + "-" + splited[2];
		}
			
		return res;
	}

}
