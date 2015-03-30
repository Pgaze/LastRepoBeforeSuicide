package vue;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Utilisateur;
import classes.Menu;
import formulaire.FormulaireConnexion;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends SuperServlet {
	private static final long serialVersionUID = 1L;


	public Accueil() {
		super();
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(super.getMailInCookie(request)!= null){
			try{
				Utilisateur user = Utilisateur.getUtilisateurParMail(super.getMailInCookie(request));
				request.getSession().setAttribute("sessionUtilisateur", user);
				response.sendRedirect( "recherche" );
				return;	
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		if (super.getUtiilisateurInSession(request) == null ) {
			request.setAttribute("menu", Menu.getMenuAcceuil().getLiensMenu());
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}else {
			response.sendRedirect( "recherche" );
			return;	
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request=Menu.afficherMenu(request, response);
		HttpSession sessionUtilisateur = request.getSession();
		try{
			String logA = request.getParameter("login");
			String mdpA = request.getParameter("mdp");
			FormulaireConnexion form =new FormulaireConnexion(logA,mdpA);

			if (form.verificationCoupleMailMotDePasse()){
				Utilisateur user= Utilisateur.getUtilisateurParMail(logA);
				sessionUtilisateur.setAttribute("sessionUtilisateur", user);
				response.addCookie(new Cookie("cookieUtilisateur", logA));
				response.sendRedirect( "recherche" );
				return;				

			} else {
				sessionUtilisateur.setAttribute("sessionUtilisateur", null);
				request.setAttribute("resultat","Echec authentification" );
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
}


