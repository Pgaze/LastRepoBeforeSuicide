
package vue;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Postule;
import modele.Utilisateur;

/**
 * Servlet implementation class Demandes
 */
@WebServlet("/Demandes")
public class Demandes extends LaBifleDuMoyenAgeANosJours {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Demandes() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		this.afficherMenu();
		Utilisateur user=super.getUtilisateurInSession();
		List<Postule> demandeEnvoye,demandeRecu;
		try {
			demandeEnvoye = Postule.getDemandeEnvoyeByUser(user);
			this.request.setAttribute("demandeEnvoye", demandeEnvoye);
			demandeRecu = Postule.getDemandeRecuByUser(user);
			this.request.setAttribute("demandeRecu", demandeRecu);
			this.getServletContext().getRequestDispatcher("/WEB-INF/demandes.jsp").forward(this.request, this.response);
		} catch (Exception e) {
			this.request.setAttribute("errorMessage",e.getMessage());
			this.response.sendRedirect("erreur");
			return ;
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}


