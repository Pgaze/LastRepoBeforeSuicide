

<!DOCTYPE html>
<html>
<%@ include file="../WEB-INF/entete.jsp"%>
<%@ include file="../WEB-INF/menu.jsp"%>
<body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">

	<div id="container">	        
		<div id="containerInfoPersonnel">
			<div id="blockImage"></div>
			<div id="blockNomPrenom">
				<p class="infoPerso"> Benac</p>
				<p class="infoPerso"> Florian</p>
				<p id="tel"> 0645213645 </p>
			</div>
		</div>
		<div id="containerInfoLogement">
			<div class="unServiceContainer">
				<p class="libelleService"> Adresse : </p>
				<p class="service"> 502 rue du portail vert prés du port</p>
			</div>
			<div class="unServiceContainer">
				<p class="libelleService"> Fumeur :</p>
				<p class="service"> Oui </p>
			</div>
		</div>
		<div id="reservationContainer">
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

