package vue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Data;
import modele.Offre;
import modele.Postule;
import modele.Utilisateur;
import classes.Menu;
import formulaire.FormulaireRechercheAnnonce;

/**
 * Servlet implementation class Recherche
 */
@WebServlet("/Recherche")
public class Recherche extends SuperServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Recherche() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if (request.getSession().getAttribute("sessionUtilisateur") == null) {
		if (request.getSession().getAttribute("sessionUtilisateur") != null) {
			request.setAttribute("menu", Menu.getMenuMembre().getLiensMenu());
			this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Cas du bouton recherche
		if (request.getParameter("btCherche")!=null){
			request.setAttribute("menu", Menu.getMenuMembre().getLiensMenu());
			try{
				FormulaireRechercheAnnonce form= new FormulaireRechercheAnnonce(request.getParameter("ville"),
																				request.getParameter("dateDebut"),
																				request.getParameter("dateFin"));
				List<Offre> lesOffres=form.getListeOffre();
				request.setAttribute("lesOffres", lesOffres);
			}
			catch (Exception e){
				request.setAttribute("erreur", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
		}
		//Appui sur un autre bouton
		else{
			Offre offrePostulee;
			try {
				offrePostulee = Offre.getOffreByIdLogement(getBoutonClique(request));
				Utilisateur user= super.getUtilisateurInSession(request);
				Postule postule = new Postule(user, offrePostulee.getHebergeur(), offrePostulee.getLogement());
				if(!postule.existInBase()){
					postule.postulerAUneOffre();
					Data.BDD_Connection.commit();
					response.sendRedirect("demandes");
					return ;
				}
				else{
					request.setAttribute("erreur", "Vous avez deja postule à cette offre");
					this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private int getBoutonClique(HttpServletRequest request){
		Map<String,String[]> mapParameter = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : mapParameter.entrySet()){
			System.out.println(entry.getKey() + " "+entry.getValue()[0]);
			if(entry.getValue()[0].contentEquals("Postuler")){
				return Integer.parseInt(entry.getKey());
			}
		}
		return -1;
		

	}
}
