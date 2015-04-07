

<!DOCTYPE html>
<html>
<%@ include file="../WEB-INF/entete.jsp"%>
<%@ include file="../WEB-INF/menu.jsp"%>
<body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">

	<div id="container">	        
		<div id="containerInfoPersonnel">
			<div id="blockImage"></div>
			<div id="blockNomPrenom">
				<p id="nom"></p>
				<p id="prenom"></p>
				<p id="numTel"></p>
			</div>
		</div>
		<div id="containerInfoLogement">
			<div id="adresseContainer">
				<p id="libelleAdresse"> Adresse : </p>
				<p id="adresse"> 502 rue du portail vert prés du port</p>
			</div>
			<div id="unServiceContainer">
				<p id="libelleService"> Fumeur :</p>
				<p id="service"> Oui </p>
			</div>
		</div>
		<div id="reservationContainer">
			<form id="formPostulationValidation" method="post">
				<div id="formPostulationValidation_content">
					<p id="dateDebutLibelle"></p>
					<input class="date" type="date" name="dateDebut"> 
					<p id="dateFinLibelle"></p>
					<input class="date" type="date" name="dateFin">
					<input type="submit" value="Reserver" id="btn_reserver" name="btn_reserver">
				</div>
			</form>
		</div>
	</div>

</body>

<%@ include file="../WEB-INF/basdepage.jsp"%>
</html>

