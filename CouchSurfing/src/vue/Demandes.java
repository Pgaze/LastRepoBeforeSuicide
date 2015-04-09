
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
import modele.Postule;
import modele.Utilisateur;

/**
 * Servlet implementation class Demandes
 */
@WebServlet("/Demandes")
public class Demandes extends SuperServlet {
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/erreur.jsp").forward(this.request, this.response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.initAttribut(request, response);
		this.afficherMenu();
		try {
			List<Postule> demandeRecu = Postule.getDemandeRecuByUser(getUtilisateurInSession());
			int i=0;
			while (demandeRecu.get(i).getPostulant().getIdUser()!= this.getIdUserClique()){
				i++;
			}
			Postule result=demandeRecu.get(i);
			if(this.getReponse()){
				result.updateStatus(1);
			}
			else{
				result.updateStatus(0);
			}
			Data.BDD_Connection.commit();
			Utilisateur user=super.getUtilisateurInSession();
			List<Postule> demandeEnvoye,demandeRecues;
			demandeEnvoye = Postule.getDemandeEnvoyeByUser(user);
			this.request.setAttribute("demandeEnvoye", demandeEnvoye);
			demandeRecues = Postule.getDemandeRecuByUser(user);
			this.request.setAttribute("demandeRecu", demandeRecues);
			this.getServletContext().getRequestDispatcher("/WEB-INF/demandes.jsp").forward(this.request, this.response);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/demandes.jsp").forward(this.request, this.response);
	}
	
	private int getIdUserClique(){
		Map<String,String[]> mapParameter = this.request.getParameterMap();
		for (Map.Entry<String, String[]> entry : mapParameter.entrySet()){
			if(entry.getValue()[0].contentEquals("Accepter") || entry.getValue()[0].contentEquals("Refuser")){
				String nameSubmit = entry.getKey();
				return Integer.parseInt(nameSubmit);
			}
		}
		return -1;

	}
	
	private boolean getReponse(){
		Map<String,String[]> mapParameter = this.request.getParameterMap();
		for (Map.Entry<String, String[]> entry : mapParameter.entrySet()){
			if(entry.getValue()[0].contentEquals("Accepter")){
				return true;
			}
		}
		return false;
		
	}
}


