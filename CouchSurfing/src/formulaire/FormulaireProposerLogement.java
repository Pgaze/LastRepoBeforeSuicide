package formulaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Adresse;
import modele.Data;
import modele.Logement;
import modele.Utilisateur;

public class FormulaireProposerLogement {
	
	private String batimentEscalier;
	private String numeroEtVoie;
	private String cp;
	private String residence;
	private String complementAdresse;
	private String ville;
	private Utilisateur user;
	private String dateDebut,dateFin;
	
	public FormulaireProposerLogement(String batimentEscalier,
			String numeroEtVoie, String cp, String residence,
			String complementAdresse, String ville, Utilisateur user) {
		this.setBatimentEscalier(batimentEscalier);
		this.setNumeroEtVoie(numeroEtVoie);
		this.setCp(cp);
		this.setResidence(residence);
		this.setComplementAdresse(complementAdresse);
		this.setVille(ville);
		this.setUser(user);
	}
	
	public String getBatimentEscalier() {
		return batimentEscalier;
	}
	public void setBatimentEscalier(String batimentEscalier) {
		this.batimentEscalier = batimentEscalier;
	}
	public String getNumeroEtVoie() {
		return numeroEtVoie;
	}
	public void setNumeroEtVoie(String numeroEtVoie) {
		this.numeroEtVoie = numeroEtVoie;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getComplementAdresse() {
		return complementAdresse;
	}
	public void setComplementAdresse(String complementAdresse) {
		this.complementAdresse = complementAdresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	public boolean verificationCp(){
		return this.cp.matches("[0-9]{5}");
	}
	
	public String procedureAjoutLogement() throws SQLException{
		String result="";
		Logement l = this.getLogement();
		boolean resultatInsertionLogement = l.insererDansLaBase();
		PreparedStatement update = Data.BDD_Connection.prepareStatement("UPDATE Utilisateur SET IdLogement=? WHERE IdUtilisateur=?");
		update.setInt(1, l.getIdLogement());
		update.setInt(2, this.getUser().getIdUser());
		int res = update.executeUpdate();
		if (res==1 && resultatInsertionLogement){
			result="Logement ajoute";
		}else{
			result="Echec création logement";
		}
		return result;
	}
	
	public Logement getLogement(){
		try {
			return new Logement(new Adresse(batimentEscalier, numeroEtVoie, cp, residence, complementAdresse, ville));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
