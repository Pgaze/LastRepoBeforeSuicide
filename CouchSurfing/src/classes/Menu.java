package classes;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Menu {
	private String nom;
	private Map<String,String> liensMenu;
	
	public Menu(String nom){
		this.setNom(nom);
		this.liensMenu = new HashMap<String,String>();
	}
	
	public Map<String,String> getLiensMenu(){
		return this.liensMenu;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void addLien(String nom,String lien){
		/*String lien = "#";
		if(val) {
			lien += nom.toLowerCase();
		}else lien = nom.toLowerCase();
		this.liensMenu.put(nom, lien);*/
		this.liensMenu.put(nom, lien);
	}
	
	public String cherch(){
		return "trouve";
	}
}
	

