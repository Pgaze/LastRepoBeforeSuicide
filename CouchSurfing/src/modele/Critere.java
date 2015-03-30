package modele;

import java.io.Serializable;

public class Critere implements Serializable {

	private static final long serialVersionUID = -8425933680785263266L;

	public enum TypeCritere {
		COMMERCE, SOINS, RESTAURANT, INTERNET, HANDICAPE, TRANSPORT, FUMEUR, ANIMAUX, PARKING
	};

	private TypeCritere type;
	private Boolean valeur;
	private String description;

	public Critere(TypeCritere type, Boolean valeur, String description) {
		this.type = type;
		this.valeur = valeur;
		this.description = description;
	}

	public TypeCritere getType() {
		return type;
	}

	public void setType(TypeCritere type) {
		this.type = type;
	}

	public Boolean getValeur() {
		return valeur;
	}

	public void setValeur(Boolean valeur) {
		this.valeur = valeur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNomCritere() {
		switch (this.type) {
		case COMMERCE:
			return "crCommerce";
		case SOINS:
			return "crSoins";
		case ANIMAUX:
			return "crAnimaux";
		case FUMEUR:
			return  "crFumeur";
		case HANDICAPE:
			return "crHandicape";
		case INTERNET:
			return "crInternet";
		case PARKING:
			return "crParking";
		case RESTAURANT:
			return "crRestaurant";
		case TRANSPORT:
			return "crTransport";
		default:
			return "";
		}

		
		
	}

}
