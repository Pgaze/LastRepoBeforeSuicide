package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Postule {
	
	private Utilisateur postulant;
	private Utilisateur hebergeur;
	private Logement logement;
	
	public Postule(Utilisateur postulant, Utilisateur hebergeur, Logement logement) {
		super();
		this.postulant = postulant;
		this.hebergeur = hebergeur;
		this.logement = logement;
	}

	/**
	 * @return Liste des Postulation de l'utilisateur qui sont encore valides
	 * @throws Exception 
	 */
	public static ArrayList<Postule> getPostulationsEnCoursByUser(Utilisateur user) throws Exception{
		ArrayList<Postule> tablePostulation = new ArrayList<Postule>();
		PreparedStatement select = Data.BDD_Connection.prepareStatement("SELECT IdLogement FROM Postule "
				+ "WHERE IdUtilisateur=?  ");
		select.setInt(1, user.getIdUser());
		ResultSet resultSelect=select.executeQuery();
		
		while(resultSelect.next()){
			Logement logement=Logement.getLogementById(resultSelect.getInt("IdLogement"));
			Utilisateur hebergeur = Utilisateur.getUtilisateurByIdLogement(resultSelect.getInt("IdLogement"));
			tablePostulation.add(new Postule(user, hebergeur, logement));
		}
		return tablePostulation;
	}
	
	/*public static ArrayList<Integer> deletePostulationsPerimees() throws SQLException{
		ArrayList<Integer> table = getPostulationsPerimees();
		//suppresion
		PreparedStatement delete = Data.BDD_Connection.prepareStatement("DELETE FROM Postule WHERE DateInvalidite < CURDATE()");
		delete.executeUpdate();
		return table;
	}*/
	
	/**
	 * @return Liste des logements donc les postulations sont perimees
	 */
	public static ArrayList<Integer> getPostulationsPerimees() {
		ArrayList<Integer> tablePostulation = new ArrayList<Integer>();
		//Selection des entrées a supprimer	
		PreparedStatement select;
		try {
			select = Data.BDD_Connection.prepareStatement("SELECT IdLogement FROM Postule WHERE DateInvalidite < CURDATE() ORDER BY DateInvalidite");
			ResultSet resultSelect=select.executeQuery();
			while(resultSelect.next()){
				tablePostulation.add(resultSelect.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tablePostulation;
	}
	
	/**
	 * @return element supprimé
	 * @throws SQLException
	 */
	public static void deletePostulationByIdLogement(int idLogement) throws SQLException{
		//suppresion
		PreparedStatement delete = Data.BDD_Connection.prepareStatement("DELETE FROM Postule WHERE IdLogement=?");
		delete.setInt(1, idLogement);
		delete.executeUpdate();
	}
	
	/**
	 * @param theIdOffre
	 * @return status
	 * @throws SQLException
	 */
	public  boolean postulerAUneOffre() throws SQLException {
		Date today = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String myDate = sdf.format(today);
		PreparedStatement ps=Data.BDD_Connection.prepareStatement("INSERT INTO Postule (IdUtilisateur,IdLogement,DatePostule,Status) values(?,?,?,?)");
		ps.setInt(1, this.postulant.getIdUser());
		ps.setInt(2, this.logement.getIdLogement());
		ps.setString(3, myDate);
		ps.setInt(4, 3);
		
		if(ps.executeUpdate() == 1){
			return true;
		}		
		return false;
	}

	public Utilisateur getPostulant() {
		return postulant;
	}

	public Utilisateur getHebergeur() {
		return hebergeur;
	}

	public Logement getLogement() {
		return logement;
	}

	public static List<Postule> getDemandeRecuByUser(Utilisateur user) throws Exception {
		List<Postule> result = new ArrayList<Postule>();
		String sql = "select Postule.IdUtilisateur,Postule.IdLogement from Postule,Utilisateur "
				+ "where Postule.IdLogement=Utilisateur.IdLogement "
				+ "and Utilisateur.IdUtilisateur = ?";
		PreparedStatement select = Data.BDD_Connection.prepareStatement(sql);
		select.setInt(1, user.getIdUser());
		ResultSet resultSelect = select.executeQuery();
		while(resultSelect.next()){
			Utilisateur postulant = Utilisateur.getUtilisateurById(resultSelect.getInt(1));
			Logement logement = Logement.getLogementById(resultSelect.getInt(2));
			result.add(new Postule(postulant, user, logement));
		}
		
		
		return result;
	}
	
	public boolean existInBase() throws SQLException{
		String sql = "SELECT count(*) from Postule where IdUtilisateur=? and IdLogement=? ";
		PreparedStatement count = Data.BDD_Connection.prepareStatement(sql);
		count.setInt(1,this.postulant.getIdUser());
		count.setInt(2, this.logement.getIdLogement());
		ResultSet resultcount = count.executeQuery();
		resultcount.next();
		if(resultcount.getInt(1)!=0){
			return true;
		}
		else{
			return false;
		}
	}
}
