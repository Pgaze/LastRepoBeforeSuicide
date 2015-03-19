<!DOCTYPE html>
<html>
<%@ include file="entete.jsp" %>
	<%@ include file="menu.jsp" %>

    <body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">
        <div id="container">	        	
			<div class="contentAccueil" id="accueilBlock1" style="background-image:url(${pageContext.request.contextPath}/ImageServlet)">
			</div>      	
			<div class="contentAccueil" id="presentation">
				<a href="evaluation"> Evaluation </a>
			</div>      	
			<div class="contentAccueil" id="connexion">
				<div id="formConnexion">
					<form action="accueil" method="post" id="formConnexion">
						<input required type="text" name="login" placeholder="Votre mail"/>
						<input required type="password" name="mdp" placeholder="Votre mot de passe"/>
						<input required type="Submit" name="valLogin"/>
					</form>
					<p>Vous n'avez pas de compte ? <a href="inscription">Inscrivez vous !</a></p>
				</div>
				${ resultat }
			</div>
        </div>
    </body>
    
	<%@ include file="basdepage.jsp" %>
</html>
