
<!DOCTYPE html>
<html>
	<%@ include file="entete.jsp" %>
	<%@ include file="menu.jsp" %>
    <body style="background-image:url(${pageContext.request.contextPath}/ServletBackground)">
        <div id="container">
	        <form id="form_nouvelleAnnonce" method="post" action="nouvelle">
	    		<h5>Nouvelle annonce !</h5>
	    		<div id="form_in_propositionLogement">
	    		${ resultat }
			    	<div id="saisies_propositionLogement">
				        <div id="propoLogLeft">
				        	<label class="alignLeft" for="batimentEscalier">Batiment/Escalier :</label><input name="batimentEscalier" id="batimentEscalier" type="text"></input>
				        	<label class="alignLeft" for="numeroEtVoie">N° et voie :</label><input required name="numeroEtVoie" id="numeroEtVoie" type="text"></input>
				        	<label class="alignLeft" for="cp">Code Postal :</label><input required name="cp" id="cp" type="text"></input></br>
				 		</div>
				 		<div id="propoLogMidddle">
				 			<label class="alignMiddle" for="residence">Residence :</label><input name="residence" id="residence" type="text"></input></br>
					        <label class="alignMiddle" for="complementAdresse">Complément d'adresse :</label><input name="complementAdresse" id="complementAdresse" type="text"></input></br>
					        <label class="alignMiddle" for="ville">Ville :</label><input required name="ville" id="ville" type="text"></input></br>
				 		</div>
			    	</div>
			    	<button id="valNouvelleAnnonce" name="val_nouvelleAnnonce">Suite</button>
			    </div>
			</form>
        </div>
    </body>
	<%@ include file="basdepage.jsp" %>
