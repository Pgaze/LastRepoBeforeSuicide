package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Data;
import modele.Logement;
import modele.Utilisateur;
import formulaire.FormulaireCritere;

/**
 * Servlet implementation class Criteres
 */
@WebServlet("/Criteres")
public class Criteres extends SuperServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Criteres() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		this.afficherMenu();
		this.getServletContext().getRequestDispatcher("/WEB-INF/criteres.jsp").forward(this.request, this.response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		FormulaireCritere form = new FormulaireCritere(
				this.request.getParameter("crCommerce"), this.request.getParameter("crHopitaux"), 
				this.request.getParameter("crRestaurants"), this.request.getParameter("crTransports"), 
				this.request.getParameter("crAnimaux"), this.request.getParameter("crInternet"), 
				this.request.getParameter("crHandicapes"), this.request.getParameter("crFumeur"), 
				this.request.getParameter("crParking"),this.request.getParameter("dateDebut"),
				this.request.getParameter("dateFin"));
		Utilisateur user= super.getUtilisateurInSession();
		try {
			boolean result=false ;
			Logement l = Logement.getLogementById(user.getIdLogement());
			if(!(this.request.getParameter("dateDebut").equals("") && this.request.getParameter("dateFin").equals(""))){
				form.setDateOnLogement(l);
				if(l.updateDates()){
					result=true;
				}
			}
			form.setCritereOnLogement(l);
			result = result && l.updateListCritere();
			if(result){
				Data.BDD_Connection.commit();
				this.response.sendRedirect("profil");
				return;
			}
			else{
				super.afficherPageErreur("Probleme base de donnees");
				return;

			}
		} catch (Exception e) {
			super.afficherPageErreur(e.getMessage());
			return;
		}
	}

}
