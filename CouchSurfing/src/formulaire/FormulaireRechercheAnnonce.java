package formulaire;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utilitaire.CustomDate;
import modele.Data;
import modele.Logement;
import modele.Offre;
import modele.Utilisateur;

public class FormulaireRechercheAnnonce {
	
	private String ville;
	private String dateDebut;
	private String dateFin;


	public FormulaireRechercheAnnonce(String ville,String dateDebut,String dateFin) {
		this.ville = ville;
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
	}
	
		public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = CustomDate.checkFormatDate(dateDebut);
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = CustomDate.checkFormatDate(dateFin);
	}
	
		/**
	 * @return liste des offres pour une ville donn�e
	 * @throws Exception
	 */
	public List<Offre> getListeOffre() throws Exception {
		List<Offre> result = new ArrayList<Offre>();
		PreparedStatement s = Data.BDD_Connection.prepareStatement(
					"select distinct Logement.IdLogement,Utilisateur.IdUtilisateur,Logement.DateDebut,Logement.DateFin from Utilisateur,Logement "
					+ "where Logement.IdLogement=Utilisateur.IdLogement and Logement.ville = ?"
					+ (this.dateDebut!=null && this.dateFin!=null ? "AND (Logement.DateDebut <= ? AND Logement.DateFin >= ?)" : ""));
		s.setString(1, this.ville);
		if(this.dateDebut!=null && this.dateFin!=null){
			s.setDate(2, Date.valueOf(this.dateFin));
			s.setDate(3, Date.valueOf(this.dateDebut));
		}
		ResultSet rs=s.executeQuery();
		while (rs.next()){
			Logement l=Logement.getLogementById(rs.getInt(1));
			Utilisateur u=Utilisateur.getUtilisateurById(rs.getInt(2));
			result.add(new Offre(l, u, rs.getString(3), rs.getString(4)));
		}
		if (result.isEmpty()){
			throw new Exception("Aucun logement a "+this.ville);
		}
		return result;
	}
	
}
