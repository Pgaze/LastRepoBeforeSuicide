package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Utilisateur;

public abstract class SuperServlet extends HttpServlet {
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	private static final long serialVersionUID = -7832776795658834441L;
	
	@Override
	protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
	
	@Override
	protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;

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
	protected Utilisateur getUtilisateurInSession(){
		if(this.request.getSession().getAttribute("sessionUtilisateur") !=null){
			return (Utilisateur)this.request.getSession().getAttribute("sessionUtilisateur");
		}
		else{
			return null;
		}
	}
	
	protected void redirectIfNotConnected(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	public Menu getMenuMembre(){
		Menu membre = new Menu("membre");
		membre.addLien("Deconnexion", "deconnexion");
		membre.addLien("Mes demandes", "demandes");
		membre.addLien("Profil", "profil");
		membre.addLien("Nouvelle annonce", "nouvelle");
		membre.addLien("Recherche", "recherche");
		return membre;
	}
	
	public Menu getMenuAcceuil(){
		Menu invite = new Menu("invite");
		invite.addLien("Connexion", "#connexion");
		invite.addLien("Presentation", "#presentation");
		return invite;
	}
	
	public Menu getMenuInscription(){
		Menu inscription = new Menu("inscription");
		inscription.addLien("Accueil", "accueil");
		return inscription;
	}
	
	public void afficherMenu(){
		if (this.getUtilisateurInSession() != null) {
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
	

	public void afficherPageErreur(String e){
		this.request.setAttribute("errorMessage", e);
		System.err.println(e);
		try {
			this.response.sendRedirect("erreur");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}



