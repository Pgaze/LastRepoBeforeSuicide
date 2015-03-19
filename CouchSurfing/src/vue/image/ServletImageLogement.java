package vue.image;

import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Image;
import modele.Logement;
import modele.Utilisateur;

public class ServletImageLogement extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ServletImageLogement() {
		super();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
		try {
			//Recuperation et affichage de l'image de profil
			int idImageLogementl = Logement.getLogementById(user.getIdLogement()).getIdPhotoLogement();
			Image image;
			if(idImageLogementl==-1){
				image = Image.getImageById(1);
			}
			else{
				image = Image.getImageById(idImageLogementl);
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

}
