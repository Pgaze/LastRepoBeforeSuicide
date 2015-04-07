package formulaire;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributeValueException;

import modele.Data;
import modele.Logement;
import modele.Offre;
import modele.Utilisateur;
import utilitaire.CustomDate;

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
	 * @return liste des offres pour une ville donnï¿½e
	 * @throws Exception
	 */
	public List<Offre> getListeOffre() throws Exception {
		List<Offre> result = new ArrayList<Offre>();
		boolean dateSpecifiee = this.dateDebut!=null && this.dateFin!=null;
		
		result.addAll(getOffreSansPostulation(dateSpecifiee));
		//result.addAll(getOffreAvecPostulation(dateSpecifiee));
		if (result.isEmpty()){
			throw new Exception("Aucun logement a "+this.ville);
		}
		return result;
	}

	private List<Offre> getOffreSansPostulation(boolean dateSpecifiee) throws Exception {
		List<Offre> result = new ArrayList<Offre>();
		String strReq;
		strReq = "SELECT Logement.IdLogement,Utilisateur.IdUtilisateur,Logement.DateDebut,Logement.DateFin "
				+ "FROM Utilisateur,Logement "
				+ "WHERE Logement.IdLogement=Utilisateur.IdLogement AND Logement.ville = ? "
				+ "AND (Logement.IdLogement NOT IN "
				+ "(SELECT Postule.IdLogement FROM Postule)) ";
		if(dateSpecifiee){
			strReq += "AND Logement.DateDebut <= ? AND Logement.DateFin >= ? ";
		}
		PreparedStatement s = Data.BDD_Connection.prepareStatement(strReq);
		s.setString(1, this.ville);
		if(dateSpecifiee){
			s.setDate(2, Date.valueOf(this.dateFin));
			s.setDate(3, Date.valueOf(this.dateDebut));
		}

		ResultSet rs=s.executeQuery();
		while (rs.next()){
			Logement l=Logement.getLogementById(rs.getInt(1));
			Utilisateur u=Utilisateur.getUtilisateurById(rs.getInt(2));
			result.add(new Offre(l, u, rs.getDate(3), rs.getDate(4)));
		}
		return result;
	}
	
	private List<Offre> getOffreAvecPostulation(boolean dateSpecifiee) throws Exception {
		List<Offre> result = new ArrayList<Offre>();
		List<Offre> resultNonAccepte = new ArrayList<Offre>();
		List<Offre> resultAccepte = new ArrayList<Offre>();

		String strReqAccepte = "SELECT IdLogement,IdUtilisateur,dateDebut,dateFin "
						+ "FROM Postule "
						+ "WHERE (Status = 1) "
						+ "ORDER BY IdLogement DESC, dateDebut ASC";
		if(dateSpecifiee){
			strReqAccepte += "AND Logement.DateDebut <= ? AND Logement.DateFin >= ? ";
		}
		PreparedStatement sAccepte = Data.BDD_Connection.prepareStatement(strReqAccepte);
		if(dateSpecifiee){
			sAccepte.setDate(2, Date.valueOf(this.dateFin));
			sAccepte.setDate(3, Date.valueOf(this.dateDebut));
		}
		ResultSet rsAccepte=sAccepte.executeQuery();
		while (rsAccepte.next()){
			Logement l=Logement.getLogementById(rsAccepte.getInt(1));
			Utilisateur u=Utilisateur.getUtilisateurById(rsAccepte.getInt(2));
			resultAccepte.add(new Offre(l, u, rsAccepte.getDate(3), rsAccepte.getDate(4)));
		}
		List<Offre> resultAccepteReste = calculReste(resultAccepte);
		
		
		return result;
	}

	private List<Offre> calculReste(List<Offre> resultAccepte) throws InvalidAttributeValueException {
		List<Offre> resultAccepteReste = new ArrayList<Offre>();
		Logement tempLogement = null;

		for(Offre offre : resultAccepte){
			resultAccepteReste.get(resultAccepteReste.size()).getLogement();
			if(offre.getLogement().getIdLogement() == tempLogement.getIdLogement()){
				//comparer les dates
				Date debutPlusUnJ = resultAccepteReste.get(resultAccepteReste.size()).getDateDebut();
				int[] tabDate = CustomDate.splitDate(debutPlusUnJ.toString());
				debutPlusUnJ = Date.valueOf(CustomDate.creerStringDate(tabDate[0],tabDate[1],tabDate[2]+1));
				//compacter les offres
				//mettre a jour la liste
				if(offre.getDateDebut() == debutPlusUnJ) {
					offre.setDateFin(debutPlusUnJ);
				}
			} else {
				//MaJ des données temporaires
				tempLogement=offre.getLogement();
				resultAccepteReste.add(offre);
			}
		}
		return resultAccepteReste;
	}
}
