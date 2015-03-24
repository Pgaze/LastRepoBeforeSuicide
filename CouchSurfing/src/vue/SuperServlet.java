package vue;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import modele.Utilisateur;

public abstract class SuperServlet extends HttpServlet {
	
	
	protected String getMailInCookie(HttpServletRequest request){
		Cookie[] lesCookies=request.getCookies();
		for (int i=0;i<lesCookies.length;i++){
			if(lesCookies[i].getName().equals("cookieUtilisateur")){
				return lesCookies[i].getValue();
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


}
