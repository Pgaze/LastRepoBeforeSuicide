package modele;

public class Critere {

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

}
