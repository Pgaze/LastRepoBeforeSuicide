

<!DOCTYPE html>
<html>
<%@ include file="../WEB-INF/entete.jsp"%>
<%@ include file="../WEB-INF/menu.jsp"%>
<body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">
	<div id="container">
		<div id="containerRecherche">
			<h1>Recherche ton canapé</h1>
			<div id="formRecherche">
				<form id="leFormRecherche" method="post">
					<div id="contenuFormRecherche">
						<input id="inputCherche" type="text" name="ville"> <input
							class="date" type="date" name="dateDebut"> <input
							class="date" type="date" name="dateFin"> <input
							type="submit" value="GO !" id="btCherche" name="btCherche">
					</div>
				</form>

			</div>
		</div>

		<div id="containerResultat">
			<p>${ erreur }</p>
			<form method="post">

				<c:forEach items="${lesOffres}" var="uneOffre">
				
					<div id="uneOffre">

						<div id="enTeteOffre">
							<h1 id="intituleEnTeteOffre">
							
							<c:out value="${ uneOffre.hebergeur}" /> 
								<a id="lienProfil" href="${pageContext.request.contextPath}/profil?id=${uneOffre.hebergeur.idUser}"> Voir le profil</a>
							</h1>
						</div>
						
						<div id="corpsOffre">
							<div id="critereAdresse" class="critereOffre">
								<h1 id="intituleCritere">Adresse:</h1>
								<p id="valeurCritere">
									<c:out value="${ uneOffre.logement.adresse }" />
								</p>
								<input type="submit" name="${ uneOffre.logement.idLogement}"
									value="Postuler">
							</div>
						</div>
					</div>	
					
				</c:forEach>
			</form>
		</div>

	</div>

</body>

<%@ include file="../WEB-INF/basdepage.jsp"%>
</html>

