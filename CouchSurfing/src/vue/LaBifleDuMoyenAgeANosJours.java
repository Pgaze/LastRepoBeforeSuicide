package vue;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Menu;
import modele.Utilisateur;

public abstract class LaBifleDuMoyenAgeANosJours extends HttpServlet {
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	private static final long serialVersionUID = -7832776795658834441L;

	protected String getMailInCookie(HttpServletRequest request){
		Cookie[] lesCookies=request.getCookies();
		if(lesCookies!=null){
			for (int i=0;i<lesCookies.length;i++){
				if(lesCookies[i].getName().equals("cookieUtilisateur")){
					return lesCookies[i].getValue();
				}
			}
		}
		return null;
	}
	//TODO Virer le parametre
	protected Utilisateur getUtilisateurInSession(HttpServletRequest request){
		if(request.getSession().getAttribute("sessionUtilisateur") !=null){
			return (Utilisateur)request.getSession().getAttribute("sessionUtilisateur");
		}
		else{
			return null;
		}
	}
	
	protected void redirectIfNotConnected(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	public Menu getMenuMembre(){
		Menu membre = new Menu("membre");
		membre.addLien("Deconnexion", false);
		membre.addLien("Annonces", false);
		membre.addLien("Demandes", false);
		membre.addLien("Profil", false);
		membre.addLien("Messagerie", false);
		membre.addLien("Nouvelle", false);
		membre.addLien("Recherche", false);
		return membre;
	}
	
	public Menu getMenuAcceuil(){
		Menu invite = new Menu("invite");
		invite.addLien("Connexion", true);
		invite.addLien("Presentation", true);
		return invite;
	}
	
	public Menu getMenuInscription(){
		Menu inscription = new Menu("inscription");
		inscription.addLien("Accueil", false);
		return inscription;
	}
	/*
	public HttpServletRequest afficherMenu(HttpServletRequest request,HttpServletResponse response){
		if (this.getUtilisateurInSession(request) != null) {
			request.setAttribute("menu", getMenuMembre().getLiensMenu());
		}
		else{
			request.setAttribute("menu", getMenuAcceuil().getLiensMenu());
		}
		return request;
	}*/
	
	public void afficherMenu(){
		if (this.getUtilisateurInSession(this.request) != null) {
			this.request.setAttribute("menu", getMenuMembre().getLiensMenu());
		}
		else{
			this.request.setAttribute("menu", getMenuAcceuil().getLiensMenu());
		}
	}

	public void initAttribut(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
}



