
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
import classes.Menu;

/**
 * Servlet implementation class Demandes
 */
@WebServlet("/Demandes")
public class Demandes extends HttpServlet {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request=Menu.afficherMenu(request, response);
		Utilisateur user=(Utilisateur)request.getSession().getAttribute("sessionUtilisateur");
		List<Postule> demandeEnvoye,demandeRecu;
		try {
			demandeEnvoye = Postule.getPostulationsEnCoursByUser(user);
			request.setAttribute("demandeEnvoye", demandeEnvoye);
			demandeRecu = Postule.getDemandeRecuByUser(user);
			request.setAttribute("demandeRecu", demandeRecu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/demandes.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request=Menu.afficherMenu(request, response);
		response.sendRedirect("demandes");

	}
}


