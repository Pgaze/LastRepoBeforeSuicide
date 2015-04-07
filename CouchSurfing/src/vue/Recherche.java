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
import formulaire.FormulaireRechercheAnnonce;

/**
 * Servlet implementation class Recherche
 */
@WebServlet("/Recherche")
public class Recherche extends LaBifleDuMoyenAgeANosJours {
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
		super.initAttribut(request, response);
		super.afficherMenu();
		this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		super.afficherMenu();
		//Cas du bouton recherche
		if (request.getParameter("btCherche")!=null){
			try{
				FormulaireRechercheAnnonce form= 
						new FormulaireRechercheAnnonce(this.request.getParameter("ville"),
								this.request.getParameter("dateDebut"),this.request.getParameter("dateFin"));
				List<Offre> lesOffres=form.getListeOffre();
				this.request.setAttribute("lesOffres", lesOffres);
			}
			catch (Exception e){
				this.request.setAttribute("errorMessage",e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(this.request, this.response);
				//this.request.setAttribute("errorMessage",e.getMessage());
				//this.response.sendRedirect("erreur");

			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(this.request, this.response);
		}
		//Appui sur un autre bouton
		else{
			Offre offrePostulee;
			try {
				offrePostulee = Offre.getOffreByIdLogement(getBoutonClique());
				Utilisateur user= super.getUtilisateurInSession();
				Postule postule = new Postule(user, offrePostulee.getHebergeur(), offrePostulee.getLogement());
				if(!postule.existInBase()){
					postule.postulerAUneOffre();
					Data.BDD_Connection.commit();
					this.response.sendRedirect("demandes");
					return ;
				}
				else{
					this.request.setAttribute("erreur", "Vous avez deja postule à cette offre");
					this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(this.request, this.response);
				}
			}
			catch (Exception e) {
				this.request.setAttribute("errorMessage",e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(this.request, this.response);
				//this.request.setAttribute("errorMessage",e.getMessage());
				//this.response.sendRedirect("erreur");

			}
		}
	}
	
	private int getBoutonClique(){
		Map<String,String[]> mapParameter = this.request.getParameterMap();
		for (Map.Entry<String, String[]> entry : mapParameter.entrySet()){
			if(entry.getValue()[0].contentEquals("Postuler")){
				return Integer.parseInt(entry.getKey());
			}
		}
		return -1;
	}
}
