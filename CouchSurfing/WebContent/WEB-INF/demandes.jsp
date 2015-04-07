
<!DOCTYPE html>
<html>
	<%@ include file="entete.jsp" %>
	<%@ include file="menu.jsp" %>
    <body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">
        <div class="container">
	        <h3>Demande(s) envoyé(s)</h3>
	     	
			<div id="demandes">
			    <div id="infosDemandes">
			    <div id="ical"></div>
				<ol>
					<c:forEach items="${demandeEnvoye }" var="postule">
							<li>
								<div class="uneDemande" id="">
									<div class="detailsAnnonce${postule.status }">
										
										<h3>Logement de ${postule.hebergeur } à ${postule.logement.adresse.ville }</h3>
								<!-- 		<div class="uneImg">Img ?</div>   -->
										<div class="txtDemandesEnvoye"><b>Adresse:</b> ${postule.logement.adresse }</br>
																<b>Date:</b> 31/01/2015-01/02/2015
										</div>
									</div>
								</div>
							</li>
					</c:forEach>
				</ol>
				</div>
			</div>
		</div>
	    <div class="container">
	    <form method="post" action="demandes">
		    <h3>Demande(s) recue(s)</h3>
		        	
			<div class="demandes">
				<div class="infosDemandes">
				<div id="ical"></div>
					<ol>
						<c:forEach items="${demandeRecu }" var="demande">
							<li>
								<div class="uneDemande" id="">
									<div class="detailsAnnonce">
												
										<h3>Demande de ${demande.postulant }</h3>
													
											<div class="txtDemandesRecu">
												<b>Date:</b> 31/01/2015-01/02/2015 <br/>
												<c:if test="${demande.status ==2 }">
													<input style="height:39px;padding-left: 10px;" type="image" src="${pageContext.request.contextPath}/ServletValidationDemande" name="${demande.postulant.idUser }" value="Accepter"/>
													<input style="height:31px;padding-left: 15px;" type="image" src="${pageContext.request.contextPath}/ServletRefusDemande" name="${demande.postulant.idUser }" value="Refuser"/>
												</c:if>
											</div>
									</div>
								</div>		
							</li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</form>
		</div>
	</body>
	<%@ include file="basdepage.jsp" %>
</html>
