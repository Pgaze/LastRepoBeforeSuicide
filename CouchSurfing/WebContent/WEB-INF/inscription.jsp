<!DOCTYPE html>
<html>
<%@ include file="entete.jsp"%>
<%@ include file="menu.jsp"%>

<body onload="redirection()"
	style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">

	<div id="container">
		<form id="form_inscription" method="post" action="inscription">
			<h5>Merci de remplir ce formulaire !</h5>
			<div id="form_in_finscription">
				<div id="saisies_finscription">
					<label for="prenom">Prénom :</label>
					<input placeholder="Votre prenom" required name="prenom" id="prenom"
						type="text" value="${infosRetenus[0]}" /></input></br>
					<label for="nom">Nom :</label><input placeholder="Votre nom" required name="nom" id="nom" 
						type="text" value="${infosRetenus[1]}"></input></br>
					<label for="pseudo">Pseudo :</label>
					<input placeholder="Votre pseudo" required name="pseudo" id="pseudo"
						type="text" value="${infosRetenus[2]}"></input></br>
					<label for="mail">Email :</label>
					<input	placeholder="Votre email" required name="mail" id="mail"
						type="email"value="${infosRetenus[4]}"></input></br>
					<label for="tel">Tel :</label>
					<input placeholder="Votre tel" required name="tel" id="tel"
						type="text"value="${infosRetenus[3]}"></input></br>
					<label for="mdp">Mot de passe :</label>
					<input placeholder="Votre mot de passe" required name="mdp" id="mdp"
						type="password" title="Doit contenir 1 majuscule, 1 chiffre et 1 minuscule"></input></br>
					<label for="mdpC">Confirmer le mot de passe :</label>
					<input placeholder="Confirmation du mot de passe" required name="mdpC" id="mailC"
						type="password"></input></br>
					<button id="valI" name="valI">Suite</button>
					<p>
						Retour à <a href="accueil">l'accueil.</a>
					</p>
				</div>
			</div>
		</form>

	</div>
</body>
<%@ include file="basdepage.jsp"%>
</html>
