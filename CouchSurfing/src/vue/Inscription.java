package vue;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Menu;
import formulaire.FormulaireInscription;

/**
 * Servlet implementation class incription
 */
@WebServlet("/incription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Inscription() {
		super();
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request=Menu.afficherMenu(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request=Menu.afficherMenu(request, response);
		try{
			String nom=request.getParameter("nom");
			String prenom=request.getParameter("prenom");
			String mail=request.getParameter("mail");
			String mdp=request.getParameter("mdp");
			String confirmMdp=request.getParameter("mdpC");
			String pseudo = request.getParameter("pseudo");
			String tel = request.getParameter("tel");
			FormulaireInscription form=new FormulaireInscription(nom,prenom,mail,mdp,confirmMdp,pseudo,tel);
			String resultatInscription=form.procedureInscription();
			List<String> infosRetenus = form.getRetourInfos();
			request.setAttribute("infosRetenus", infosRetenus);
			request.setAttribute("resultat", resultatInscription);
			this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
