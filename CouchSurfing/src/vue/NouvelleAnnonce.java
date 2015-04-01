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
public class NouvelleAnnonce extends LaBifleDuMoyenAgeANosJours {
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
		super.initAttribut(request, response);
		super.afficherMenu();
		this.getServletContext().getRequestDispatcher("/WEB-INF/nouvelle.jsp").forward(this.request, this.response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		super.afficherMenu();
		try{
			HttpSession utilisateurSession = this.request.getSession();
			Utilisateur user= super.getUtilisateurInSession(this.request);
			FormulaireProposerLogement form=new FormulaireProposerLogement(
					this.request.getParameter("batimentEscalier"), this.request.getParameter("numeroEtVoie"), 
					this.request.getParameter("cp"), this.request.getParameter("residence"), 
					this.request.getParameter("complementAdresse"), this.request.getParameter("ville"), user);
			if(form.verificationCp()){
				String result = form.procedureAjoutLogement();
				this.request.setAttribute("resultat", result);
				if(result.contentEquals("Logement ajoute")){
					Data.BDD_Connection.commit();
					this.response.sendRedirect("criteres");
				}else {
					this.getServletContext().getRequestDispatcher("/WEB-INF/nouvelle.jsp").forward(this.request, this.response);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

}
