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
	
	public void addLien(String nom,boolean val){
		String lien = "#";
		if(val) {
			lien += nom.toLowerCase();
		}else lien = nom.toLowerCase();
		this.liensMenu.put(nom, lien);
	}
	
	public String cherch(){
		return "trouve";
	}
	
	
	
	public static Menu getMenuMembre(){
		Menu membre = new Menu("membre");
		membre.addLien("Deconnexion", false);
		membre.addLien("Annonces", false);
		membre.addLien("Demandes", false);
		membre.addLien("Profil", false);
		//membre.addLien("Messagerie", false);
		membre.addLien("Nouvelle", false);
		membre.addLien("Recherche", false);
		return membre;
	}
	
	public static Menu getMenuAcceuil(){
		Menu invite = new Menu("invite");
		invite.addLien("Connexion", true);
		invite.addLien("Presentation", true);
		return invite;
	}
	
	public static Menu getMenuInscription(){
		Menu inscription = new Menu("inscription");
		inscription.addLien("Accueil", false);
		return inscription;
	}
	
	public static HttpServletRequest afficherMenu(HttpServletRequest request,HttpServletResponse response){
		if (request.getSession().getAttribute("sessionUtilisateur") == null) {
			request.setAttribute("menu", Menu.getMenuMembre().getLiensMenu());
		}
		else{
			request.setAttribute("menu", Menu.getMenuAcceuil().getLiensMenu());
		}
		return request;
	}
}
	

