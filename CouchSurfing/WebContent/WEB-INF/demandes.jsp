
<!DOCTYPE html>
<html>
	<%@ include file="entete.jsp" %>
	<%@ include file="menu.jsp" %>
    <body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">
        <div id="container">
	        <form action="demandes" method="post">
	        	<h3>Demande(s) envoyé(s)</h3>
	        
				<div class="demandes">
				    <div class="infosDemandes">
				    <div id="ical"></div>
					<ol>
						<c:forEach items="${demandeEnvoye }" var="postule">
								<li>
									<div class="uneDemande" id="">
										<div class="detailsAnnonce">
											
											<h3>Logement de ${postule.hebergeur } à ${postule.logement.adresse.ville }</h3>
									<!-- 		<div class="uneImg">Img ?</div>   -->
											<div class="txt">Adresse: ${postule.logement.adresse }</br>
																	Date: 31/01/2015-01/02/2015
											</div>
										</div>
									</div>
								</li>
						</c:forEach>
					</ol>
				    </div>
				</div>
	        
		        <h3>Demande(s) recu(s)</h3>
		        	
				<div class="demandes">
				    <div class="infosDemandes">
				    <div id="ical"></div>
					<ol>
						<c:forEach items="${demandeRecu }" var="demande">
								<li>
									<div class="uneDemande" id="">
										<div class="detailsAnnonce">
											
											<h3>Demande de ${demande.postulant }</h3>
									<!-- 		<div class="uneImg">Img ?</div>   -->
											<div class="txt">
																	Date: 31/01/2015-01/02/2015
											</div>
										</div>
									</div>
								</li>
						</c:forEach>
					</ol>
				    </div>
                    <input type="image" src="images/suppphoto.png" name="valide" value="ID_DEMANDE"/>
                    <input type="image" src="images/suppphoto.png" name="refuse" value="ID_DEMANDE"/>
				</div>
			</form>
		</div>
	       
    </body>
    
	<%@ include file="basdepage.jsp" %>
</html>
