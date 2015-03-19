package formulaire;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modele.Data;
import modele.Utilisateur;


public class FormulaireInscription {
	
	//Valeur des champs du formulaire
	private String nom;
	private String prenom;
	private String mail;
	private String mdp;
	private String confirmMdp;
	private String pseudo;
	
	/**
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @param mdp
	 * @param confirmMdp
	 * @param pseudo
	 */
	public FormulaireInscription(String nom, String prenom, String mail,
			String mdp, String confirmMdp, String pseudo) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setMail(mail);
		this.setMdp(mdp);
		this.setConfirmMdp(confirmMdp);
		this.setPseudo(pseudo);
	}

	public FormulaireInscription() {}

	/**
	 * @param prenom
	 * @param nom
	 * @param pseudo
	 * @param mail
	 * @param motDePasse
	 * @return status
	 * @throws SQLException
	 */
	public boolean verificationDonnesInscription(String prenom,String nom,String pseudo,String mail,String motDePasse) throws SQLException{
		boolean result = true;
		Utilisateur aTester=new Utilisateur(mail, motDePasse, nom, prenom, pseudo);
		result=this.testMotDePasseValide(aTester.getPassword());
		result=this.testMailValide(aTester.getMail());
		return result;
	}

	public boolean testMailValide(String mailATester) {
		return mailATester.matches("^[a-zA-Z0-9]+[\\.]*[a-zA-Z0-9]*+(@){1}[a-z]+(\\.){1}([a-z]{2,4})");
	}

	/**
	 * @param motDePasseATester
	 * @return status
	 */
	public boolean testMotDePasseValide(String motDePasseATester) {
		boolean contientMaj =motDePasseATester.matches(".*[A-Z].*");
		boolean contientMin =motDePasseATester.matches(".*[a-z].*");
		boolean contientChiffre =motDePasseATester.matches(".*[0-9].*");
		boolean longueurOk = motDePasseATester.length()>7 && motDePasseATester.length()<30;
		return  longueurOk && contientMaj && contientMin && contientChiffre;
	}

	public String procedureInscription() throws SQLException {
			if(!this.confirmMdp.contentEquals(this.mdp)){
				return "Probleme confirmation mot de passe";
			}
			else if(!this.testMailValide(this.mail)){
				return "Adresse mail invalide";
			}
			else if (this.testUtilisateurExistant(this.mail)){
				return "Utilisateur existant";
			}
			else if(!this.testMotDePasseValide(mdp)){
				return "Mot de passe invalide";
			}
			else{
				this.getUtilisateur().insererDansLaBase();
				Data.BDD_Connection.commit();
				//String s= "Bienvenue sur machin";
				//GestionMail.send("clicknsleep@gmail.com", this.getUtilisateur().getMail(), "Inscription à ClickAndSleep.co.uk réussie", s);
				//GestionMail.send(this.getUtilisateur().getMail(),"clicknsleep@gmail.com" , "Nouvelle inscription sur le site", this.getUtilisateur().getFirstName()+" "+this.getUtilisateur().getName() +"s'est inscrit");
				return "Inscription reussie";
			}
	}

	
	
	/**
	 * @param mail
	 * @return status
	 * @throws SQLException
	 */
	private boolean testUtilisateurExistant(String mail) throws SQLException {
		PreparedStatement s=Data.BDD_Connection.prepareStatement("select nom from Utilisateur where mail=?");
		s.setString(1, mail);
		ResultSet r=s.executeQuery();
		return r.next();
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getMail() {
		return mail;
	}

	public String getMdp() {
		return mdp;
	}

	public String getConfirmMdp() {
		return confirmMdp;
	}

	public String getPseudo() {
		return pseudo;
	}


	private Utilisateur getUtilisateur() throws SQLException {
		return new Utilisateur(this.mail, this.mdp, this.nom, this.prenom, this.pseudo);
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setConfirmMdp(String confirmMdp) {
		this.confirmMdp = confirmMdp;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
}
