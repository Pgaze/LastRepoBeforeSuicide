package vue;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Utilisateur;

public abstract class SuperServlet extends HttpServlet {

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

	protected Utilisateur getUtiilisateurInSession(HttpServletRequest request){
		if(request.getSession().getAttribute("sessionUtilisateur") !=null){
			return (Utilisateur)request.getSession().getAttribute("sessionUtilisateur");
		}
		else{
			return null;
		}
	}
	
	protected void redirectIfNotConnected(HttpServletRequest request, HttpServletResponse response){
		
	}
	


}
