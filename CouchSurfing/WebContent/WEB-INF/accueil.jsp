
<!DOCTYPE html>
<html>
	<%@ include file="entete.jsp" %>
	<%@ include file="menu.jsp" %>
    <body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">
        <div id="container">	        	
			<div class="contentAccueil" id="accueilBlock1" style="background-image:url(${pageContext.request.contextPath}/ImageServlet)">
			</div>      	
			<div class="contentAccueil" id="presentation">
				<p class="text_intro"> CouchSurfing est un service qui relie les membres d'une communauté française de voyageurs.</p>
				<p class="text_intro"> Utilisez CouchSurfing pour trouver un endroit pour rester ou bien, de partager votre maison et ville natale avec les voyageurs.</p>
				<p class="text_intro"> Les CouchSurfeurs organisent régulièrement des événements dans 200 000 villes à travers la France. Il ya toujours quelques choses à découvrir, de nouveaux amis à rencontrer.</p>
				<p class="text_intro"> Venez vite les rencontrer ! </p><a href="erreur"> Oups erreur...!!</a>
			</div>      	
			<div class="contentAccueil" id="connexion">
				<div id="formConnexion">
					<form action="accueil" method="post" id="formConnexion">
						<input required type="text" name="login" placeholder="Votre mail"/>
						<input required type="password" name="mdp" placeholder="Votre mot de passe"/>
						<input required type="Submit" name="valLogin"/>
					</form>
					 Vous n'avez pas de compte ? <a href="inscription">Inscrivez vous !</a>
				</div>
				${ resultat }
			</div>
        </div>
    </body>
    
	<%@ include file="basdepage.jsp" %>
</html>
