package vue.image;

import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Image;
import modele.Utilisateur;
import classes.Menu;

/**
 * Servlet implementation class ServletImageProfil
 */
@WebServlet("/ServletImageProfil")
public class ServletImageProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletImageProfil() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
		try {
			//Recuperation et affichage de l'image de profil
			int idImageProfil = user.getIdPhotoProfil();
			Image image;
			System.out.println("idImage:"+idImageProfil);
			if(idImageProfil==-1){
				image = Image.getImageById(1);
			}
			else{
				image = Image.getImageById(idImageProfil);
			}
			byte[] img = Files.readAllBytes(image.getImage().toPath());
			response.setContentType("image/png");
			response.setContentLength(img.length);
			response.getOutputStream().write(img);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
