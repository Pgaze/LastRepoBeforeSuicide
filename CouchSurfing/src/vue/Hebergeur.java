package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Utilisateur;

/**
 * Servlet implementation class Hebergeur
 */
@WebServlet("/Hebergeur")
public class Hebergeur extends Profil {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hebergeur() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		super.afficherMenu();
		try{
			if(request.getParameter("id") != null){
				int idUrl = Integer.valueOf(this.request.getParameter("id"));
				Utilisateur user = Utilisateur.getUtilisateurById(idUrl);
				this.request.setAttribute("utilisateurProfil", user);
				afficherLogementUser( user);
				this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp")
				.forward(this.request, this.response);
			}
		}catch (Exception e){
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
