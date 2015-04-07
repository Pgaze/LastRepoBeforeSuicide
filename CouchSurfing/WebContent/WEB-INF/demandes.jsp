
<!DOCTYPE html>
<html>
	<%@ include file="entete.jsp" %>
	<%@ include file="menu.jsp" %>
    <body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">
        <div id="container">
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
     <form method=post>     
              <div id="container">
	        <h3>Demande(s) recu(s)</h3>
	        	
			<div id="demandes">
			    <div id="infosDemandes">
			    <div id="ical"></div>
				<ol>
					<c:forEach items="${demandeRecu }" var="demande">
							<li>
								<div class="uneDemande" id="">
									<div class="detailsAnnonce${demande.status }">
										
										<h3>Demande de ${demande.postulant }</h3>
								<!-- 		<div class="uneImg">Img ?</div>   -->
										<div class="txtDemandesRecu">
																<b>Date:</b> 31/01/2015-01/02/2015 <br/>
																<c:if test="${demande.status ==2 }">
																 <input type="submit" value="Accepter" name="${demande.postulant.idUser}">
																 <input type="submit" value="Refuser" name="${demande.postulant.idUser}">
																</c:if>
										</div>
									</div>
								</div>
							</li>
					</c:forEach>
				</ol>
			    </div>
			</div>
        </div>
        </form>
    </body>
    
	<%@ include file="basdepage.jsp" %>
</html>
