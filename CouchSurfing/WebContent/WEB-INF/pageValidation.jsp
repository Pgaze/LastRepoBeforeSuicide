

<!DOCTYPE html>
<html>
<%@ include file="../WEB-INF/entete.jsp"%>
<%@ include file="../WEB-INF/menu.jsp"%>
<body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">

	<div id="container">	        
		<div id="containerInfoPersonnel">
			<div id="blockImage">
				<img src="${pageContext.request.contextPath}/ServletImageProfilPageValidation" />
			</div>
			
			<div id="blockNomPrenom">
				<p class="infoPerso"> ${postule.hebergeur.name }</p>
				<p class="infoPerso"> ${postule.hebergeur.firstName }</p>
				<p id="tel"> ${postule.hebergeur.tel } </p>
			</div>
		</div>
		<div id="containerInfoLogement">
			<div class="unServiceContainer">
				<p class="libelleService"> Adresse : </p>
				<p class="service"> ${postule.logement.adresse }</p>
				<p>Disponible du ${offre.dateDebut } au ${offre.dateFin }</p>
			</div>
			<c:forEach items="${postule.logement.lesCriteres }" var="unCritere">
				<div class="unServiceContainer">
					<p class="libelleService ${unCritere.icone}"> ${unCritere.titreCritere }</p>
					<p class="service"> ${unCritere.description } </p>
			</div>
			</c:forEach>
		</div>
		<div id="reservationContainer">
			${erreur }
			<form id="formPostulationValidation" method="post">
				<div id="formPostulationValidation_content">
					<p class="dateLimite"> Date début :</p>
					<input class="date" type="date" name="dateDebut"> 
					<p class="dateLimite"> Date fin :</p>
					<input class="date" type="date" name="dateFin">
					<input type="submit" value="Reserver" id="btn_reserver" name="btn_reserver">
				</div>
			</form>
		</div>
	</div>

</body>

<%@ include file="../WEB-INF/basdepage.jsp"%>
</html>

