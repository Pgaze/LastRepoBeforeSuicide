package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.directory.InvalidAttributeValueException;

import utilitaire.CustomDate;

public class Offre {

	private Logement logement;
	private Utilisateur hebergeur;
	private String dateDebut,dateFin;
	
	/**
	 * @param logement
	 * @param hebergeur
	 * @param dateDebut yyyy-mm-dd
	 * @param dateFin yyyy-mm-dd
	 */
	public Offre(Logement logement, Utilisateur hebergeur, String dateDebut, String dateFin) {
		this.setLogement(logement);
		this.setHebergeur(hebergeur);
		if(dateDebut != null && dateFin != null){
			try {
				CustomDate.checkIntegriteDates(dateDebut,dateFin);
				this.setDateDebut(dateDebut);
				this.setDateFin(dateFin);
			} catch (InvalidAttributeValueException | IllegalArgumentException e) {
				this.setDateDebut(null);
				this.setDateFin(null);
			}
		}		
	}

	/** Supprime les postulation perimees et met les date des logements concernes a null
	 * 
	 */
	public static void cleanAllLogementByPostulePerimees() {
		ArrayList<Integer> listLogements= new ArrayList<>();
		try {
			listLogements = Postule.getPostulationsPerimees();
			for(int idLogement : listLogements){				
				Logement.getLogementById(idLogement).setDateToNull();
				Postule.deletePostulationByIdLogement(idLogement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Logement getLogement() {
		return logement;
	}

	public void setLogement(Logement logement) {
		this.logement = logement;
	}

	public Utilisateur getHebergeur() {
		return hebergeur;
	}

	public void setHebergeur(Utilisateur hebergeur) {
		this.hebergeur = hebergeur;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public static Offre getOffreByIdLogement(int idLogement) throws Exception {
		String sql = "SELECT Utilisateur.idUtilisateur,DateDebut,DateFin "
				+ "FROM Logement,Utilisateur "
				+ "WHERE Utilisateur.IdLogement=Logement.IdLogement "
				+ "AND Logement.IdLogement=?";
		PreparedStatement select=Data.BDD_Connection.prepareStatement(sql);
		select.setInt(1, idLogement);
		ResultSet res=select.executeQuery();
		if(res.next()){
			return new Offre(Logement.getLogementById(idLogement), Utilisateur.getUtilisateurById(res.getInt(1)), res.getString(2), res.getString(3));
		}else{
			return null;
		}
	}
	
	/**
	 * @return Liste des Postulation de l'utilisateur qui sont encore valides
	 * @throws SQLException
	 */
	public static ArrayList<Integer> getAllOffresValide() throws SQLException{
		ArrayList<Integer> tablePostulation = new ArrayList<Integer>();
		PreparedStatement select = Data.BDD_Connection.prepareStatement("SELECT Logement.IdLogement FROM Logement,Postule "
			 + "WHERE Logement.IdLogement=Postule.IdLogement AND Logement.DateDebut <= CURDATE() AND Postule.DateInvalidite < CURDATE() ORDER BY DateDebut");
		ResultSet resultSelect=select.executeQuery();
		while(resultSelect.next()){
			tablePostulation.add(resultSelect.getInt(1));
		}
		return tablePostulation;
	}

}
