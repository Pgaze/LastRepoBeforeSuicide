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
	//TODO s'assurer que les dates de reservation soient incluses dans celle de la recherche
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		super.afficherMenu();
		this.request.setAttribute("postule", this.request.getSession().getAttribute("postule"));

		this.getServletContext().getRequestDispatcher("/WEB-INF/pageValidation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		super.afficherMenu();
		try {
			Postule postule= (Postule)this.request.getSession().getAttribute("postule");
			String dateDebut = this.request.getParameter("dateDebut");
			String dateFin = this.request.getParameter("dateFin");
			if (!dateDebut.equals("") && !dateFin.equals("")){
				postule.setDateDebut(dateDebut);
				postule.setDateFin(dateFin);	
				if(postule.postulerAUneOffre()){
					Data.BDD_Connection.commit();
					this.response.sendRedirect("demandes");
					return ;
				}
			}
		} catch (SQLException e) {
			super.afficherPageErreur(e.getMessage());
		}

	}

}
