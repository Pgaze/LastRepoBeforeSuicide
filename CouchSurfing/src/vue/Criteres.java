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
import classes.Menu;
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
		request=Menu.afficherMenu(request, response);
		System.out.println(super.getUtilisateurInSession(request));
		this.getServletContext().getRequestDispatcher("/WEB-INF/criteres.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormulaireCritere form = new FormulaireCritere(
				request.getParameter("crCommerce"), request.getParameter("crHopitaux"), 
				request.getParameter("crRestaurants"), request.getParameter("crTransports"), 
				request.getParameter("crAnimaux"), request.getParameter("crInternet"), 
				request.getParameter("crHandicapes"), request.getParameter("crFumeur"), 
				request.getParameter("crParking"),request.getParameter("dateDebut"),
				request.getParameter("dateFin"));
		Utilisateur user= super.getUtilisateurInSession(request);
		try {
			boolean result=false ;
			Logement l = Logement.getLogementById(user.getIdLogement());
			if(!(request.getParameter("dateDebut").equals("") && request.getParameter("dateFin").equals(""))){
				form.setDateOnLogement(l);
				if(l.updateDates()){
					result=true;
				}
			}
			form.setCritereOnLogement(l);
			if(l.updateListCritere()){
				result= result && true;
			}
			if(result){
				Data.BDD_Connection.commit();
				response.sendRedirect("profil");
				return;
			}
			else{
				System.out.print("Erreur");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
