package vue;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Data;
import modele.Postule;

/**
 * Servlet implementation class PageValidation
 */
//@WebServlet("/pageValidation")
public class PageValidation extends LaBifleDuMoyenAgeANosJours {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		super.afficherMenu();
		this.request.setAttribute("postule", this.request.getSession().getAttribute("postule"));

		this.getServletContext().getRequestDispatcher("/WEB-INF/pageValidation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		super.afficherMenu();
		try {
			Postule postule= (Postule)this.request.getSession().getAttribute("postule");
			postule.setDateDebut(this.request.getParameter("dateDebut"));
			postule.setDateFin(this.request.getParameter("dateFin"));
			if(postule.postulerAUneOffre()){
				Data.BDD_Connection.commit();
				this.response.sendRedirect("demandes");
				return ;
			}

		} catch (SQLException e) {
			super.afficherPageErreur(e.getMessage());
		}

	}

}
