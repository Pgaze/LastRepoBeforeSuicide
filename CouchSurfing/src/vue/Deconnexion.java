package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnexion
 */
@WebServlet("/Deconnexion")
public class Deconnexion extends SuperServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Deconnexion() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		destroyCookieAndSession(request);
		HttpSession session = request.getSession();
		
        session.invalidate();
		response.sendRedirect("accueil");
	    return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private void destroyCookieAndSession(HttpServletRequest request){
		request.getSession().invalidate();
		for( Cookie c : request.getCookies()){
			if(c.getName().equals("cookieUtilisateur"));
			c.setMaxAge(-1);
			break;
		}
	}

}
