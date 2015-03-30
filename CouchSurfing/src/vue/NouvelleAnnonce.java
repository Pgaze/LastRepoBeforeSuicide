package vue;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Data;
import modele.Utilisateur;
import classes.Menu;
import formulaire.FormulaireProposerLogement;

/**
 * Servlet implementation class Nouvelle
 */
@WebServlet("/Nouvelle")
public class NouvelleAnnonce extends SuperServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NouvelleAnnonce() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request=Menu.afficherMenu(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/nouvelle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request=Menu.afficherMenu(request, response);
		try{
			HttpSession utilisateurSession = request.getSession();
			Utilisateur user= (Utilisateur)utilisateurSession.getAttribute("sessionUtilisateur");
			FormulaireProposerLogement form=new FormulaireProposerLogement(
					request.getParameter("batimentEscalier"), request.getParameter("numeroEtVoie"), 
					request.getParameter("cp"), request.getParameter("residence"), 
					request.getParameter("complementAdresse"), request.getParameter("ville"), user);
			if(form.verificationCp()){
				String result = form.procedureAjoutLogement();
				if(result.contentEquals("Logement ajoute")){
					Data.BDD_Connection.commit();
					response.sendRedirect("criteres");
				}else {
					request.setAttribute("resultat", result);
					this.getServletContext().getRequestDispatcher("/WEB-INF/nouvelle.jsp").forward(request, response);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

}
