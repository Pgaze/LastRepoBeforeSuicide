package modele;

public class Adresse {
	
	private String batimentEscalier;
	private String numeroEtVoie;
	private String cp;
	private String residence;
	private String complementAdresse;
	private String ville;
	
	/**
	 * @param batimentEscalier
	 * @param numeroEtVoie
	 * @param cp
	 * @param residence
	 * @param complementAdresse
	 * @param ville
	 */
	public Adresse(String batimentEscalier, String numeroEtVoie, String cp, 
			String residence, String complementAdresse, String ville) {
		
		this.setBatimentEscalier(batimentEscalier);
		this.setNumeroEtVoie(numeroEtVoie);
		this.setCp(cp);
		this.setResidence(residence);
		this.setComplementAdresse(complementAdresse);
		this.setVille(ville);
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
		
	@Override
	public String toString() {
		String res= numeroEtVoie +"<br/>"+cp+" "+ville;
		res += (residence.equals("") ? "" : "<br/> Residence: "+ residence);
		res += (batimentEscalier.equals("") ? "" : "<br/> Batiment/Escalier: "+batimentEscalier);
		res += (complementAdresse.equals("") ? "" : "<br/> "+complementAdresse);
		return res;
	}
}
